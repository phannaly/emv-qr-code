package com.phanna.emv_qr_code

object CRC {
    const val ID = "63"
    const val VALUE_LENGTH = "04"

    fun calculate(bytes: String): String {
        var crc = 0xFFFF // initial value
        val polynomial = 0x1021 // 0001 0000 0010 0001  (0, 5, 12)

        for (b in bytes.toByteArray()) {
            for (i in 0..7) {
                val bit = b.toInt() shr 7 - i and 1 == 1
                val c15 = crc shr 15 and 1 == 1
                crc = crc shl 1
                if (c15 xor bit) crc = crc xor polynomial
            }
        }
        crc = crc and 0xffff

        return Integer.toHexString(crc).toString().toUpperCase()
    }

    fun validate(payload: String): Boolean {
        val calculatedCRC = calculate(payload.dropLast(4))

        if (calculatedCRC == payload.takeLast(4)) {
            return true
        }

        throw InvalidCRC("CRC is invalid")
    }
}

class InvalidCRC(message: String) : Exception(message)
