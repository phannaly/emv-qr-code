package com.phanna.emv_qr_code

class MerchantPresentedEncoder {
    private val data = DataObjectTypes.data()
    private val map = mutableMapOf<String, String>()
    private lateinit var payload: String
    lateinit var merchantAccountInformation: MerchantAccountInformation
    var payloadFormatIndicator: String = ""
    var pointOfInitiationMethod: String = ""
    var merchantCategoryCode: String = ""
    var transactionCurrency: String = ""
    var transactionAmount: String = ""
    var countryCode: String = ""
    var merchantName: String = ""
    var additionalDataFieldTemplate: String = ""
    var merchantCity: String = ""

    fun encode(): String {
        payload = ""

        if (payloadFormatIndicator.isNotBlank()) {
            map[data["payloadFormatIndicator"].toString()] = payloadFormatIndicator
        }
        if (pointOfInitiationMethod.isNotBlank()) {
            map[data["pointOfInitiationMethod"].toString()] = pointOfInitiationMethod
        }
        if (merchantCategoryCode.isNotBlank()) {
            map[data["merchantCategoryCode"].toString()] = merchantCategoryCode
        }
        if (this::merchantAccountInformation.isInitialized) {
            map[merchantAccountInformation.tag] = merchantAccountInformation.value
        } else {
            throw ExceptionInInitializerError("merchantAccountInformation must be initialized")
        }
        if (merchantCity.isNotBlank()) {
            map[data["merchantCity"].toString()] = merchantCity
        }
        if (merchantName.isNotBlank()) {
            map[data["merchantName"].toString()] = merchantName
        }
        if (countryCode.isNotBlank()) {
            map[data["countryCode"].toString()] = countryCode
        }
        if (transactionAmount.isNotBlank()) {
            map[data["transactionAmount"].toString()] = transactionAmount
        }
        if (transactionCurrency.isNotBlank()) {
            map[data["transactionCurrency"].toString()] = transactionCurrency
        }
        if (additionalDataFieldTemplate.isNotBlank()) {
            map[data["additionalDataFieldTemplate"].toString()] = additionalDataFieldTemplate
        }

        map.forEach {
            payload += it.key.padStart(2, '0')
            payload += it.value.length.toString().padStart(2, '0')
            payload += it.value.padStart(2, '0')
        }
        payload += CRC.ID + CRC.VALUE

        val crc = CRC.calculate(payload)

        return payload.plus(crc)
    }
}
