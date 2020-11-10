# EMV QR Code

EMV QR Code library build with Kotlin 

## Features

- [x] Merchant Presented Mode decode
- [x] Validate CRC
- [ ] Merchant Presented Mode encode
- [ ] Consumer Presented Mode encode
- [ ] Consumer Presented Mode decode

### Merchant Presented Mode Deocde

```kotlin
 val merchantPresentedMode = MerchantPresentedDecoder.decode(
     "00020101021252044131153137210116372101160123456789ABCDE6016Banteay Meanchey5913Kuntheas Shop5802KH540510.005303840621201081234567863041FF2"
 )

merchantPresentedMode.isDynamic() = true
merchantPresentedMode.payloadFormatIndicator = "01"
merchantPresentedMode.pointOfInitiationMethod = "12"
merchantPresentedMode.merchantAccountInformation = "37210116372101160123456789ABCDE"
merchantPresentedMode.merchantCategoryCode = "4131"
merchantPresentedMode.transactionCurrency = "840"
merchantPresentedMode.transactionAmount = "10.00"
merchantPresentedMode.countryCode = "KH"
merchantPresentedMode.merchantName = "Kuntheas Shop"
merchantPresentedMode.merchantCity = "Banteay Meanchey"
merchantPresentedMode.additionalDataFieldTemplate = "010812345678"
merchantPresentedMode.crc = "1FF2"
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details