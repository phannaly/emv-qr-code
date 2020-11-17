package com.phanna.emv_qr_code

import java.lang.Exception

class MerchantPresentedEncoder {
    private val data = DataObjectTypes.data()
    private val dataField = mutableMapOf<String, String>()
    private lateinit var payload: String
    lateinit var merchantAccountInformation: MerchantAccountInformation
    lateinit var additionalDataFieldTemplate: AdditionalDataField
    var payloadFormatIndicator: String = ""
    var pointOfInitiationMethod: String = ""
    var merchantCategoryCode: String = ""
    var transactionCurrency: String = ""
    var transactionAmount: String = ""
    var countryCode: String = ""
    var merchantName: String = ""
    var merchantCity: String = ""

    fun encode(): String {
        payload = ""

        if (payloadFormatIndicator.isNotBlank()) {
            dataField[data["payloadFormatIndicator"].toString()] = payloadFormatIndicator
        } else {
            throw Exception("payloadFormatIndicator is required")
        }
        if (pointOfInitiationMethod.isNotBlank()) {
            dataField[data["pointOfInitiationMethod"].toString()] = pointOfInitiationMethod
        }
        if (merchantCategoryCode.isNotBlank()) {
            dataField[data["merchantCategoryCode"].toString()] = merchantCategoryCode
        } else {
            throw Exception("merchantCategoryCode is required")
        }
        if (this::merchantAccountInformation.isInitialized) {
            dataField[merchantAccountInformation.tag] = merchantAccountInformation.value
        } else {
            throw ExceptionInInitializerError("merchantAccountInformation must be initialized")
        }
        if (merchantCity.isNotBlank()) {
            dataField[data["merchantCity"].toString()] = merchantCity
        } else {
            throw Exception("merchantCity is required")
        }
        if (merchantName.isNotBlank()) {
            dataField[data["merchantName"].toString()] = merchantName
        } else {
            throw Exception("merchantName is required")
        }
        if (countryCode.isNotBlank()) {
            dataField[data["countryCode"].toString()] = countryCode
        } else {
            throw Exception("countryCode is required")
        }
        if (transactionAmount.isNotBlank()) {
            dataField[data["transactionAmount"].toString()] = transactionAmount
        }
        if (transactionCurrency.isNotBlank()) {
            dataField[data["transactionCurrency"].toString()] = transactionCurrency
        } else {
            throw Exception("transactionCurrency is required")
        }
        if (this::additionalDataFieldTemplate.isInitialized) {
            dataField[data["additionalDataFieldTemplate"].toString()] = additionalDataFieldTemplate.toString()
        }

        dataField.forEach {
            payload += it.key.padStart(2, '0')
            payload += it.value.length.toString().padStart(2, '0')
            payload += it.value.padStart(2, '0')
        }
        payload += CRC.ID + CRC.VALUE_LENGTH

        val crc = CRC.calculate(payload)

        return payload.plus(crc)
    }
}
