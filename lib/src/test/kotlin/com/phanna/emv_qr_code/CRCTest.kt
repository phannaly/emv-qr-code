package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class CRCTest {
    @Test fun `calculate CRC`() {
        val crc = CRC.calculate("00020101021252044131153137210116372101160123")

        assertEquals("78C7", crc)
    }

    @Test fun `verify CRC`() {
        val isValid = CRC.verify(payload = "00020101021252044131153137210116372101160123", crc = "78C7")

        assertEquals(true, isValid)
    }
}
