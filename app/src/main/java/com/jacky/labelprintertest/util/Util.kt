package com.jacky.labelprintertest.util

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import androidx.core.content.getSystemService

/**
 * @Classname Util
 * @Description TODO
 * @Date 2020/6/5 16:19
 * @Created by jacky
 */

fun Context.getUsbDevice(): ArrayList<UsbDevice> {
    val manager: UsbManager = getSystemService(Context.USB_SERVICE) as UsbManager
    val map: HashMap<String, UsbDevice> = manager.deviceList
    val usbList: ArrayList<UsbDevice> = arrayListOf()
    for (usb: UsbDevice in map.values) {
        usbList.add(usb)
    }
    return usbList
}