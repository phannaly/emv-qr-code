package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class MerchantPresentedDecoderTest {
    @Test
    fun decodeQRCodePayload() {
        val merchantPresentedMode = MerchantPresentedDecoder.decode(
            "00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304A0BA"
        )

        assertEquals(true, merchantPresentedMode.isDynamic)
        assertEquals("01", merchantPresentedMode.payloadFormatIndicator)
        assertEquals("12", merchantPresentedMode.pointOfInitiationMethod)
        assertEquals("37210116372101160123456789ABCDE", merchantPresentedMode.merchantAccountInformation)
        assertEquals("4131", merchantPresentedMode.merchantCategoryCode)
        assertEquals("840", merchantPresentedMode.transactionCurrency)
        assertEquals("USD", merchantPresentedMode.currency)
        assertEquals("10.00", merchantPresentedMode.transactionAmount)
        assertEquals("KH", merchantPresentedMode.countryCode)
        assertEquals("Merchant Shop", merchantPresentedMode.merchantName)
        assertEquals("Phnom Penh", merchantPresentedMode.merchantCity)
        assertEquals("0108123456780209012444444", merchantPresentedMode.additionalDataFieldTemplate)
        assertEquals("A0BA", merchantPresentedMode.crc)
    }
}
