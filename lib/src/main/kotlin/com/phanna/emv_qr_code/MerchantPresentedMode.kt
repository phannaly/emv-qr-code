package com.phanna.emv_qr_code

import java.util.*

class MerchantPresentedMode(private val map: MutableMap<String, String>) {
    var payloadFormatIndicator: String by map
    var pointOfInitiationMethod: String by map
    var merchantAccountInformation: String by map
    var merchantCategoryCode: String by map
    var transactionCurrency: String by map
    var transactionAmount: String by map
    var countryCode: String by map
    var merchantName: String by map
    var merchantCity: String by map
    var additionalDataFieldTemplate: String by map
    var crc: String by map

    val isStatic = pointOfInitiationMethod == "11"
    val isDynamic = !isStatic
    val currency = Currency.getAvailableCurrencies().first { it.numericCode == transactionCurrency.toInt() }.toString()
}
