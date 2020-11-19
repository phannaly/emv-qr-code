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
    var transactionPurpose: String = ""

    override fun toString(): String {
        if (validDataField(billNumber)) {
            dataFieldTemplate["01"] = billNumber
        }
        if (validDataField(mobileNumber)) {
            dataFieldTemplate["02"] = mobileNumber
        }
        if (validDataField(storeLabel)) {
            dataFieldTemplate["03"] = storeLabel
        }
        if (validDataField(loyaltyNumber)) {
            dataFieldTemplate["04"] = loyaltyNumber
        }
        if (validDataField(referenceLabel)) {
            dataFieldTemplate["05"] = referenceLabel
        }
        if (validDataField(customerLabel)) {
            dataFieldTemplate["06"] = customerLabel
        }
        if (validDataField(terminalLabel)) {
            dataFieldTemplate["07"] = terminalLabel
        }
        if (validDataField(transactionPurpose)) {
            dataFieldTemplate["08"] = transactionPurpose
        }

        var payload = ""

        dataFieldTemplate.forEach {
            payload += it.key.padStart(2, '0')
            payload += it.value.length.toString().padStart(2, '0')
            payload += it.value.padStart(2, '0')
        }

        return payload
    }

    private fun validDataField(field: String): Boolean {
        return field.isNotEmpty() && field.length <= 25
    }
}
