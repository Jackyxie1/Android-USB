package com.jacky.labelprintertest.adapter

import android.hardware.usb.UsbDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jacky.labelprintertest.R
import java.lang.StringBuilder

/**
 * @Classname UsbAdapter
 * @Description TODO
 * @Date 2020/6/5 15:34
 * @Created by jacky
 */

class UsbAdapter(private val listener: OnItemClickListener?): RecyclerView.Adapter<UsbAdapter.ViewHolder>() {

    private var usbList: ArrayList<UsbDevice> = arrayListOf()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usbInfo: TextView = itemView.findViewById(R.id.usbInfo)
    }

    fun setUsbDevices(usbList: ArrayList<UsbDevice>) {
        this.usbList.clear()
        this.usbList.addAll(usbList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usbList.size
    }

    fun getItem(position: Int): UsbDevice {
        return usbList[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usbDevice: UsbDevice = usbList[position]
        val usbInfo: StringBuilder = StringBuilder()
        usbInfo.append("Usb Device").append(usbDevice.deviceName).append("\t ").append("ProductId is ")
            .append(Integer.toHexString(usbDevice.productId)).append("\t ").append("VendorId is ")
            .append(Integer.toHexString(usbDevice.vendorId))
        holder.usbInfo.text = usbInfo.toString()
        holder.itemView.setOnClickListener { v -> listener?.onclick(v, position) }
    }

    interface OnItemClickListener {
        fun onclick(view: View?, position: Int): Unit
    }
}