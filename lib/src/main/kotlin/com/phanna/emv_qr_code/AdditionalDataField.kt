package com.phanna.emv_qr_code

class AdditionalDataField {
    private val dataFieldTemplate = mutableMapOf<String, String>()

    var billNumber: String = ""
    var mobileNumber: String = ""
    var storeLabel: String = ""
    var loyaltyNumber: String = ""
    var referenceLabel: String = ""
    var customerLabel: String = ""
    var terminalLabel: String = ""
    var purposeOfTransaction: String = ""
    var additionalConsumerDataRequest: String = ""

    override fun toString(): String {
        if (billNumber.isNotEmpty()) {
            dataFieldTemplate["01"] = billNumber
        }
        if (mobileNumber.isNotEmpty()) {
            dataFieldTemplate["02"] = mobileNumber
        }
        if (storeLabel.isNotEmpty()) {
            dataFieldTemplate["03"] = storeLabel
        }
        if (loyaltyNumber.isNotEmpty()) {
            dataFieldTemplate["04"] = loyaltyNumber
        }
        if (referenceLabel.isNotEmpty()) {
            dataFieldTemplate["05"] = referenceLabel
        }
        if (customerLabel.isNotEmpty()) {
            dataFieldTemplate["06"] = customerLabel
        }
        if (terminalLabel.isNotEmpty()) {
            dataFieldTemplate["07"] = terminalLabel
        }
        if (purposeOfTransaction.isNotEmpty()) {
            dataFieldTemplate["08"] = purposeOfTransaction
        }
        if (additionalConsumerDataRequest.isNotEmpty()) {
            dataFieldTemplate["09"] = additionalConsumerDataRequest
        }

        var payload = ""

        dataFieldTemplate.forEach {
            payload += it.key.padStart(2, '0')
            payload += it.value.length.toString().padStart(2, '0')
            payload += it.value.padStart(2, '0')
        }

        return payload
    }
}
