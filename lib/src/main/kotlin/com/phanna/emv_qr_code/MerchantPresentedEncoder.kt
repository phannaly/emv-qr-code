package com.phanna.emv_qr_code

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
        }
        if (pointOfInitiationMethod.isNotBlank()) {
            dataField[data["pointOfInitiationMethod"].toString()] = pointOfInitiationMethod
        }
        if (merchantCategoryCode.isNotBlank()) {
            dataField[data["merchantCategoryCode"].toString()] = merchantCategoryCode
        }
        if (this::merchantAccountInformation.isInitialized) {
            dataField[merchantAccountInformation.tag] = merchantAccountInformation.value
        } else {
            throw ExceptionInInitializerError("merchantAccountInformation must be initialized")
        }
        if (merchantCity.isNotBlank()) {
            dataField[data["merchantCity"].toString()] = merchantCity
        }
        if (merchantName.isNotBlank()) {
            dataField[data["merchantName"].toString()] = merchantName
        }
        if (countryCode.isNotBlank()) {
            dataField[data["countryCode"].toString()] = countryCode
        }
        if (transactionAmount.isNotBlank()) {
            dataField[data["transactionAmount"].toString()] = transactionAmount
        }
        if (transactionCurrency.isNotBlank()) {
            dataField[data["transactionCurrency"].toString()] = transactionCurrency
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
