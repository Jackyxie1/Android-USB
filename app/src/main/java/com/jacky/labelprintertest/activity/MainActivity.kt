package com.jacky.labelprintertest.activity

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.getSystemService
import com.jacky.labelprintertest.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE = 1000
        const val ACTION_USB_PERMISSION = "com.android.usb.USB_PERMISSION"
    }
    private lateinit var device: UsbDevice
    private lateinit var usbManager: UsbManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usbManager = getSystemService(Context.USB_SERVICE) as UsbManager
        btn.setOnClickListener { startActivityForResult<UsbDeviceActivity>(REQUEST_CODE) }
    }

    private inline fun <reified T : Activity> Activity.startActivityForResult(requestCode: Int) {
        startActivityForResult(Intent(this, T::class.java), requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val device: UsbDevice = data?.getParcelableExtra(UsbManager.EXTRA_DEVICE) as UsbDevice
                this.device = device
                val pi = PendingIntent.getBroadcast(this, 0, Intent(ACTION_USB_PERMISSION), 0)
                usbManager.requestPermission(this.device, pi)
                if (!usbManager.hasPermission(this.device)) {
                    usbManager.requestPermission(this.device, pi)
                }
            }
        }
    }
}