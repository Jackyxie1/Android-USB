package com.jacky.labelprintertest.printer

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager

/**
 * @Classname PrinterManager
 * @Description TODO
 * @Date 2020/6/8 14:36
 * @Created by jacky
 */

class PrinterManager(val context: Context) {

    private var printer: Printer? = null

    fun usbPrinterConnect(usbManager: UsbManager, usbDevice: UsbDevice): Boolean {
        printer = UsbPrinter(usbManager, usbDevice)
        return printer!!.connect()
    }

    fun disConnect(): Boolean {
        if (null != printer) {
            return printer!!.disConnect()
        }
        return false
    }

    fun isConnected(): Boolean {
        return printer?.isConnected!!
    }

    fun printer() {
        printer?.printTest(0)
    }
}