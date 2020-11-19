package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class CRCTest {
    @Test
    fun calculateCRC() {
        val crc = CRC.calculate("00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304")
        assertEquals("A0BA", crc)
    }

    @Test
    fun verifyCRC() {
        val isValid = CRC.validate("00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304A0BA")
        assertEquals(true, isValid)
    }
}
