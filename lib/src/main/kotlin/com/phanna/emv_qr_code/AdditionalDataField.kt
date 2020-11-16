package com.phanna.emv_qr_code

class AdditionalDataField {
    private val map = mutableMapOf<String, String>()

    private val billNumber: String = ""
    private val mobileNumber: String = ""
    private val storeLabel: String = ""
    private val loyaltyNumber: String = ""
    private val referenceLabel: String = ""
    private val customerLabel: String = ""
    private val terminalLabel: String = ""
    private val purposeOfTransaction: String = ""
    private val additionalConsumerDataRequest: String = ""

    override fun toString(): String {
        if (billNumber.isNotEmpty()) {
            map["01"] = billNumber
        }
        if (mobileNumber.isNotEmpty()) {
            map["02"] = mobileNumber
        }
        if (storeLabel.isNotEmpty()) {
            map["03"] = storeLabel
        }
        if (loyaltyNumber.isNotEmpty()) {
            map["04"] = loyaltyNumber
        }
        if (referenceLabel.isNotEmpty()) {
            map["05"] = referenceLabel
        }
        if (customerLabel.isNotEmpty()) {
            map["06"] = customerLabel
        }
        if (terminalLabel.isNotEmpty()) {
            map["07"] = terminalLabel
        }
        if (purposeOfTransaction.isNotEmpty()) {
            map["08"] = purposeOfTransaction
        }
        if (additionalConsumerDataRequest.isNotEmpty()) {
            map["09"] = additionalConsumerDataRequest
        }

        return map.toString()
    }
}
