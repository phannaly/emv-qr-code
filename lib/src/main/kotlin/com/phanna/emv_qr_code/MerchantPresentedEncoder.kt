package com.phanna.emv_qr_code

import java.lang.StringBuilder

class MerchantPresentedEncoder {
    var data = DataObjectTypes.data()
    private val map = mutableMapOf<String, String>()

    var payloadFormatIndicator: String = ""
        set(value) {
            map[data["payloadFormatIndicator"].toString()] = value
            field = value
        }

    var pointOfInitiationMethod: String = ""
        set(value) {
            map[data["pointOfInitiationMethod"].toString()] = value
            field = value
        }

    var merchantAccountInformation: String = ""
        set(value) {
            map[data["merchantAccountInformation"].toString()] = value
            field = value
        }

    var merchantCategoryCode: String = ""
        set(value) {
            map[data["merchantCategoryCode"].toString()] = value
            field = value
        }

    var transactionCurrency: String = ""
        set(value) {
            map[data["transactionCurrency"].toString()] = value
            field = value
        }

    var transactionAmount: String = ""
        set(value) {
            map[data["transactionAmount"].toString()] = value
            field = value
        }

    var countryCode: String = ""
        set(value) {
            map[data["countryCode"].toString()] = value
            field = value
        }

    var merchantName: String = ""
        set(value) {
            map[data["merchantName"].toString()] = value
            field = value
        }

    var additionalDataFieldTemplate: String = ""
        set(value) {
            map[data["additionalDataFieldTemplate"].toString()] = value
            field = value
        }

    var merchantCity: String = ""
        set(value) {
            map[data["merchantCity"].toString()] = value
            field = value
        }

    fun encode(): String {
        val builder = StringBuilder()

        map.forEach {
            builder.append(it.key.padStart(2, '0'))
                .append(it.value.length.toString().padStart(2, '0'))
                .append(it.value.padStart(2, '0'))
        }

        return builder.toString()
    }
}
