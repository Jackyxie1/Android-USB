package com.jacky.labelprintertest.printer

/**
 * @Classname Printer
 * @Description TODO
 * @Date 2020/6/8 11:22
 * @Created by jacky
 */

abstract class Printer {
    /**
     * 连接状态
     */
    var isConnected: Boolean = false

    /*连接打印机*/
    abstract fun connect(): Boolean

    /*断开打印机*/
    abstract fun disConnect(): Boolean

    /*写入数据*/
    abstract fun write(data: ByteArray): Int

    /*读取数据*/
    abstract fun read(data: ByteArray): Int

    fun printTest(count: Int) {
        commandSize(40.0, 30.0)
        commandGap(2.0, 0.0)
        commandDirection()
        commandDensity(7)
        commandReference(0, 0)
        commandCls()
        commandText(80, 10, "TSS24.BF2", 0, 1, 1, "#客如云欢迎您#$count")
        commandText(50, 40, "TSS24.BF2", 0, 2, 2, "客如云深圳")
        commandBarcode(40, 90, "128", 120, 1, 0, 2, 2, "abcdef1234")
        print(1)
    }

    private fun commandSize(m: Double, n: Double) {
        val str = "SIZE $m mm,$n mm\n"
        write(str.toByteArray(charset("GBK")))
    }

    private fun commandGap(m: Double, n: Double) {
        val str = "GAP $m mm,$n mm\n"
        write(str.toByteArray(charset("GBK")))
    }

    private fun commandDirection() {
        val str = "DIRECTION 0 \n"
        write(str.toByteArray(charset("GBK")))
    }

    private fun commandDensity(m: Int) {
        val str = "DENSITY $m\n"
        write(str.toByteArray(charset("GBK")))
    }

    private fun commandReference(x: Int, y: Int) {
        val str = "REFERENCE $x,$y\n"
        write(str.toByteArray(charset("GBK")))
    }

    private fun commandCls() {
        val str = "CLS\n"
        write(str.toByteArray(charset("GBK")))
    }

    private fun commandText(
        x: Int,
        y: Int,
        font: String,
        rotation: Int,
        xMultiplication: Int,
        yMultiplication: Int,
        content: String
    ) {
        val str = "TEXT $x,$y,\"$font\",$rotation,$xMultiplication,$yMultiplication,\"$content\"\n"
        write(str.toByteArray(charset("GBK")))
    }

    private fun commandBarcode(
        x: Int,
        y: Int,
        codeType: String,
        height: Int,
        human: Int,
        rotation: Int,
        narrow: Int,
        wide: Int,
        content: String
    ) {
        val str =
            "BARCODE $x,$y,\"$codeType\",$height,$human,$rotation,$narrow,$wide,\"$content\"\n"
        write(str.toByteArray(charset("GBK")))
    }

    private fun print(m: Int) {
        val str = "PRINT $m\n"
        write(str.toByteArray(charset("GBK")))
    }


}