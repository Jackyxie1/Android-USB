package com.jacky.labelprintertest.printer

import android.hardware.usb.*
import java.lang.Exception

/**
 * @Classname UsbPrinter
 * @Description TODO
 * @Date 2020/6/8 13:59
 * @Created by jacky
 */

class UsbPrinter(private val usbManager: UsbManager, private val usbDevice: UsbDevice) : Printer() {

    private lateinit var usbInterface: UsbInterface
    private lateinit var inEndPoint: UsbEndpoint
    private lateinit var outEndpoint: UsbEndpoint
    private var connection: UsbDeviceConnection? = null

    override fun connect(): Boolean {
        isConnected = false
        try {
            if (usbManager.hasPermission(usbDevice)) {
                usbInterface = usbDevice.getInterface(0)
                for (i in 0 until usbInterface.endpointCount) {
                    val endpoint: UsbEndpoint? = usbInterface.getEndpoint(i)
                    if (endpoint?.type == UsbConstants.USB_ENDPOINT_XFER_BULK) {
                        if (endpoint.direction == UsbConstants.USB_DIR_OUT) {
                            outEndpoint = endpoint
                        } else {
                            inEndPoint = endpoint
                        }
                    }
                }
                connection = usbManager.openDevice(usbDevice)
                if (null != connection) {
                    isConnected = connection!!.claimInterface(usbInterface, true)
                }
            }
        } catch (e: Exception) {
            isConnected = false
        }

        return isConnected
    }

    override fun disConnect(): Boolean {
        if (isConnected) {
            connection?.releaseInterface(usbInterface)
            connection?.close()
            connection = null
            isConnected = false
            return true
        }
        return false
    }

    override fun write(data: ByteArray): Int {
        return connection!!.bulkTransfer(outEndpoint, data, data.size, 3000)
    }

    override fun read(data: ByteArray): Int {
        if (connection == null) {
            return -1
        }
        return connection!!.bulkTransfer(inEndPoint, data, data.size, 3000)
    }
}