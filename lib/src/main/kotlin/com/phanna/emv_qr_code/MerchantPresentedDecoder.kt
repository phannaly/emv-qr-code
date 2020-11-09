package com.phanna.emv_qr_code

class MerchantPresentedDecoder {
    private val dataObjectTypes = DataObjectTypes.data()
    private val merchantAccountInformation = DataObjectTypes.merchantAccountInformation()

    companion object {
        @JvmStatic
        fun decode(encoded: String): MerchantPresentedMode {
            val data = MerchantPresentedDecoder().decode(encoded)
            return MerchantPresentedMode(data)
        }
    }

    fun decode(qrcode: String): MutableMap<String, String> {
        var qrcodeString = qrcode
        val qrcodeMappings = mutableMapOf<String, String>()
        val result = mutableMapOf<String, String>()

        while (qrcodeString.isNotEmpty()) {
            readString(qrcodeString)?.let {
                qrcodeString = it.remaining
                if (it.id.isNotEmpty() && it.value.isNotEmpty()) {
                    qrcodeMappings[it.id] = it.value
                }
            }
        }

        qrcodeMappings.forEach { qrcodeParsed ->
            if (qrcodeParsed.key.toInt() in merchantAccountInformation) {
                result["merchantAccountInformation"] = qrcodeParsed.value
            } else {
                dataObjectTypes.forEach { data ->
                    result[data.key] = data.value
                    qrcodeMappings[data.value]?.let { value ->
                        dataObjectTypes[data.key]?.let {
                            result[data.key] = value
                        }
                    }
                }
            }
        }

        return result
    }

    private fun readString(data: String): StringParser? {
        val temp = data.substring(2)
        val lengthValue = temp.substring(0, 2)

        lengthValue.toIntOrNull()?.let { length ->
            return StringParser(
                id = data.substring(0, 2),
                value = temp.substring(2, 2 + length),
                remaining = temp.substring(2 + length)
            )
        }

        return null
    }

    data class StringParser(val id: String, val value: String, val remaining: String)
}
