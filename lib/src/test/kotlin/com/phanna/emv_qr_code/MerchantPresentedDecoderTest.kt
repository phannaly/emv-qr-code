package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class MerchantPresentedDecoderTest {
    @Test fun `decode qrcode data`() {
        val merchantPresentedMode = MerchantPresentedDecoder.decode(
            "00020101021252044131153137210116372101160123456789ABCDE6016Banteay Meanchey5913Kuntheas Shop5802KH540510.005303840621201081234567863041FF2"
        )

        assertEquals(merchantPresentedMode.payloadFormatIndicator, "01")
        assertEquals(merchantPresentedMode.pointOfInitiationMethod, "12")
        assertEquals(merchantPresentedMode.merchantAccountInformation, "37210116372101160123456789ABCDE")
        assertEquals(merchantPresentedMode.merchantCategoryCode, "4131")
        assertEquals(merchantPresentedMode.transactionCurrency, "840")
        assertEquals(merchantPresentedMode.transactionAmount, "10.00")
        assertEquals(merchantPresentedMode.countryCode, "KH")
        assertEquals(merchantPresentedMode.merchantName, "Kuntheas Shop")
        assertEquals(merchantPresentedMode.merchantCity, "Banteay Meanchey")
        assertEquals(merchantPresentedMode.additionalDataFieldTemplate, "010812345678")
        assertEquals(merchantPresentedMode.crc, "1FF2")
    }
}
