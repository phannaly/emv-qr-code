package com.phanna.emv_qr_code

import org.junit.Assert.assertEquals
import kotlin.test.Test

class MerchantPresentedEncoderTest {
    @Test
    fun encodeQRCodePayload() {
        val merchantPresentedMode = MerchantPresentedEncoder()
        val merchantAccountInformation = MerchantAccountInformation()
        merchantAccountInformation.value = "37210116372101160123456789ABCDE"
        merchantAccountInformation.tag = "15"

        val additionalDataField = AdditionalDataField()
        additionalDataField.billNumber = "12345678"
        additionalDataField.mobileNumber = "012444444"

        merchantPresentedMode.merchantAccountInformation = merchantAccountInformation
        merchantPresentedMode.pointOfInitiationMethod = "12"
        merchantPresentedMode.payloadFormatIndicator = "01"
        merchantPresentedMode.merchantCategoryCode = "4131"
        merchantPresentedMode.transactionCurrency = "840"
        merchantPresentedMode.transactionAmount = "10.00"
        merchantPresentedMode.countryCode = "KH"
        merchantPresentedMode.merchantName = "Merchant Shop"
        merchantPresentedMode.merchantCity = "Phnom Penh"
        merchantPresentedMode.additionalDataFieldTemplate = additionalDataField

        assertEquals("UnionPay", merchantAccountInformation.paymentNetwork())
        assertEquals(
            "00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304A0BA",
            merchantPresentedMode.encode()
        )
    }
}
