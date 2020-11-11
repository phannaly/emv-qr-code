package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class MerchantPresentedDecoderTest {
    @Test fun `decode qrcode data`() {
        val merchantPresentedMode = MerchantPresentedDecoder.decode(
            "00020101021252044131153137210116372101160123456789ABCDE6016Banteay Meanchey5913Kuntheas Shop5802KH540510.005303840621201081234567863041FF2"
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
        assertEquals("Kuntheas Shop", merchantPresentedMode.merchantName)
        assertEquals("Banteay Meanchey", merchantPresentedMode.merchantCity)
        assertEquals("010812345678", merchantPresentedMode.additionalDataFieldTemplate)
        assertEquals("1FF2", merchantPresentedMode.crc)
    }
}
