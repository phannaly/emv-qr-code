package com.phanna.emv_qr_code

class DataObjectTypes {
    companion object {
        fun data(): Map<String, String> {
            val dataObjects = mutableMapOf<String, String>()

            dataObjects["payloadFormatIndicator"] = "00"
            dataObjects["pointOfInitiationMethod"] = "01"
            dataObjects["merchantCategoryCode"] = "52"
            dataObjects["transactionCurrency"] = "53"
            dataObjects["transactionAmount"] = "54"
            dataObjects["countryCode"] = "58"
            dataObjects["merchantName"] = "59"
            dataObjects["merchantCity"] = "60"
            dataObjects["additionalDataFieldTemplate"] = "62"
            dataObjects["crc"] = "63"

            return dataObjects
        }

        fun merchantAccountInformation(): IntRange { return 2..51 }
    }
}
