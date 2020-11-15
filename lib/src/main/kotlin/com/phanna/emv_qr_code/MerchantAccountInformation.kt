package com.phanna.emv_qr_code

class MerchantAccountInformation {
    private val paymentNetworks = mapOf(
        "Visa" to 2..3,
        "Mastercard" to 4..5,
        "EMVco" to 6..8,
        "Discover" to 9..10,
        "Amex" to 11..12,
        "JCB" to 13..14,
        "UnionPay" to 15..16,
        "EMVco" to 17..25,
        "Reserved" to 26..53
    )

    var value: String = ""
        set(value) {
            if (value.length <= 99) {
                field = value
            } else {
                throw Exception("Length of value cannot be greater than 99")
            }
        }

    var tag: String = ""
        set(value) {
            if (value.toInt() in DataObjectTypes.merchantAccountInformation()) {
                field = value
            } else {
                throw Exception("Tag is invalid")
            }
        }

    fun paymentNetwork(): String {
        if (this.value.isEmpty() || this.tag.isEmpty()) {
            throw Exception("Value and Tag must be set first")
        }
        var cardScheme = ""

        paymentNetworks.forEach {
            if (tag.toInt() in it.value) {
                cardScheme = it.key
            }
        }

        return cardScheme
    }
}
