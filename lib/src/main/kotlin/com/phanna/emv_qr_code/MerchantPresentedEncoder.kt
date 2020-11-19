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

        if (payloadFormatIndicator.length == 2) {
            dataField[data["payloadFormatIndicator"].toString()] = payloadFormatIndicator
        } else {
            throw Exception("payloadFormatIndicator is required")
        }
        if (pointOfInitiationMethod.length == 2) {
            dataField[data["pointOfInitiationMethod"].toString()] = pointOfInitiationMethod
        }
        if (merchantCategoryCode.length == 4) {
            dataField[data["merchantCategoryCode"].toString()] = merchantCategoryCode
        } else {
            throw Exception("merchantCategoryCode is required")
        }
        if (this::merchantAccountInformation.isInitialized && merchantAccountInformation.value.length <= 99) {
            dataField[merchantAccountInformation.tag] = merchantAccountInformation.value
        } else {
            throw ExceptionInInitializerError("merchantAccountInformation must be initialized")
        }
        println(merchantCity.length)
        if (merchantCity.isNotBlank() && merchantCity.length <= 15) {
            dataField[data["merchantCity"].toString()] = merchantCity
        } else {
            throw Exception("merchantCity is required")
        }
        if (merchantName.isNotBlank() && merchantName.length <= 25) {
            dataField[data["merchantName"].toString()] = merchantName
        } else {
            throw Exception("merchantName is required")
        }
        if (countryCode.length == 2) {
            dataField[data["countryCode"].toString()] = countryCode
        } else {
            throw Exception("countryCode is required")
        }
        if (transactionAmount.isNotBlank() && transactionAmount.length <= 13) {
            dataField[data["transactionAmount"].toString()] = transactionAmount
        }
        if (transactionCurrency.length == 3) {
            dataField[data["transactionCurrency"].toString()] = transactionCurrency
        } else {
            throw Exception("transactionCurrency is required")
        }
        if (this::additionalDataFieldTemplate.isInitialized && additionalDataFieldTemplate.toString().length <= 99) {
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
