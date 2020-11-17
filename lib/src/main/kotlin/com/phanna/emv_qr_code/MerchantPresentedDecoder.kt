package com.phanna.emv_qr_code

class MerchantPresentedDecoder {
    private val dataObjectTypes = DataObjectTypes.data()
    private val merchantAccountInformation = DataObjectTypes.merchantAccountInformation()

    companion object {
        @JvmStatic
        fun decode(payload: String, verifyCRC: Boolean = true): MerchantPresentedMode {
            if (verifyCRC) {
                CRC.verify(payload)
            }

            val data = MerchantPresentedDecoder().decode(payload)
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
        val valueLength = temp.substring(0, 2)

        valueLength.toIntOrNull()?.let { length ->
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
