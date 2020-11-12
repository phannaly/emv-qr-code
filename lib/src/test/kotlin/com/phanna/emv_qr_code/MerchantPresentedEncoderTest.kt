package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class MerchantPresentedEncoderTest {
    @Test fun `encode qrcode data`() {
        val merchantPresentedMode = MerchantPresentedEncoder()

        merchantPresentedMode.pointOfInitiationMethod = "12"
        merchantPresentedMode.payloadFormatIndicator = "01"
        merchantPresentedMode.merchantAccountInformation = "37210116372101160123456789ABCDE"
        merchantPresentedMode.merchantCategoryCode = "4131"
        merchantPresentedMode.transactionCurrency = "840"
        merchantPresentedMode.transactionAmount = "10.00"
        merchantPresentedMode.countryCode = "KH"
        merchantPresentedMode.merchantName = "Kuntheas Shop"
        merchantPresentedMode.merchantCity = "Banteay Meanchey"
        merchantPresentedMode.additionalDataFieldTemplate = "010812345678"

        assertEquals(
            "00020101021252044131153137210116372101160123456789ABCDE6016Banteay Meanchey5913Kuntheas Shop5802KH540510.005303840621201081234567863041FF2",
            merchantPresentedMode.encode()
        )
    }
}
