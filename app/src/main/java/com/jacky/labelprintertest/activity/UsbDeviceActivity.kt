package com.jacky.labelprintertest.activity

import android.app.Activity
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jacky.labelprintertest.R
import com.jacky.labelprintertest.adapter.UsbAdapter
import com.jacky.labelprintertest.util.getUsbDevice
import kotlinx.android.synthetic.main.activity_usb_device.*

class UsbDeviceActivity : AppCompatActivity() {

    private lateinit var adapter: UsbAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usb_device)
        initView()
    }

    private fun initView() {
        adapter = UsbAdapter(object: UsbAdapter.OnItemClickListener{
            override fun onclick(view: View?, position: Int) {
                resultToMain(adapter.getItem(position))
            }

        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setUsbDevices(getUsbDevice())
        refresh.setOnClickListener { adapter.setUsbDevices(getUsbDevice()) }
    }

    private fun resultToMain(usb: UsbDevice) {
        val intent = Intent()
        val bundle = Bundle()
        bundle.putParcelable(UsbManager.EXTRA_DEVICE, usb)
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}