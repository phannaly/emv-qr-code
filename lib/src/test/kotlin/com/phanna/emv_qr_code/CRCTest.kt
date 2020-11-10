package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class CRCTest {
    @Test fun `calculate CRC`() {
        val data = "00020101021252044131153137210116372101160123"
        val crc = CRC.calculate(data)

        assertEquals("78C7", crc)
    }

    @Test fun `verify CRC`() {
        val data = "00020101021252044131153137210116372101160123"
        val isValid = CRC.verify(payload = data, crc = "78C7")

        assertEquals(true, isValid)
    }
}
