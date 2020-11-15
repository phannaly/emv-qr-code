package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class CRCTest {
    @Test
    fun calculateCRC() {
        val crc = CRC.calculate("00020101021252044131153137210116372101160123456789ABCDE6016Banteay Meanchey5913Kuntheas Shop5802KH540510.00530384062120108123456786304")

        assertEquals("1FF2", crc)
    }

    @Test
    fun verifyCRC() {
        val isValid = CRC.verify("00020101021252044131153137210116372101160123456789ABCDE6016Banteay Meanchey5913Kuntheas Shop5802KH540510.005303840621201081234567863041FF2")

        assertEquals(true, isValid)
    }
}
