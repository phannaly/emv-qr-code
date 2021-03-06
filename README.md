# EMV QR Code
[![Actions Status](https://github.com/phannaly/emv-qr-code/workflows/Build%20and%20test/badge.svg)](https://github.com/phannaly/emv-qr-code/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.phannaly/emv-qr-code/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.phannaly/emv-qr-code)


EMV QR Code library for encode and decode both Merchant Presented Mode and Consumer Presented Mode

## Usage

You can install this package from the central Maven repositories:

Maven
```xml
<dependency>
    <groupId>com.github.phannaly</groupId>
    <artifactId>emv-qr-code</artifactId>
    <version>0.0.5</version>
</dependency>
```

Gradle
```kotlin
implementation 'com.github.phannaly:emv-qr-code:0.0.5'
```

## Features

- [x] Merchant Presented Mode decode
- [x] Validate CRC
- [x] Merchant Presented Mode encode
- [ ] Consumer Presented Mode encode
- [ ] Consumer Presented Mode decode

### Merchant Presented Mode decode
```kotlin
 val merchantPresentedMode = MerchantPresentedDecoder.decode(
    "00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304A0BA"
 )

merchantPresentedMode.isDynamic // true
merchantPresentedMode.payloadFormatIndicator // "01"
merchantPresentedMode.pointOfInitiationMethod // "12"
merchantPresentedMode.merchantAccountInformation // "37210116372101160123456789ABCDE"
merchantPresentedMode.merchantCategoryCode // "4131"
merchantPresentedMode.transactionCurrency // "840"
merchantPresentedMode.currency // "USD"
merchantPresentedMode.transactionAmount // "10.00"
merchantPresentedMode.countryCode // "KH"
merchantPresentedMode.merchantName // "Merchant Shop"
merchantPresentedMode.merchantCity // "Phnom Penh"
merchantPresentedMode.additionalDataFieldTemplate // "0108123456780209012444444"
merchantPresentedMode.crc // "A0BA"
```

### Merchant Presented Mode encode

```kotlin
val merchantPresentedMode = MerchantPresentedEncoder()
val merchantAccountInformation = MerchantAccountInformation()
merchantAccountInformation.value = "37210116372101160123456789ABCDE"
merchantAccountInformation.tag = "15"

// add your custom additional data field template here
val additionalDataField = AdditionalDataField()
additionalDataField.billNumber = "12345678"
additionalDataField.mobileNumber = "012444444"

merchantPresentedMode.merchantAccountInformation = merchantAccountInformation
merchantPresentedMode.additionalDataField = additionalDataField
merchantPresentedMode.pointOfInitiationMethod = "12"
merchantPresentedMode.payloadFormatIndicator = "01"
merchantPresentedMode.merchantCategoryCode = "4131"
merchantPresentedMode.transactionCurrency = "840"
merchantPresentedMode.transactionAmount = "10.00"
merchantPresentedMode.countryCode = "KH"
merchantPresentedMode.merchantName = "Merchant Shop"
merchantPresentedMode.merchantCity = "Phnom Penh"

merchantPresentedMode.encode()
// 00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304A0BA

merchantAccountInformation.paymentNetwork()
// UnionPay
```

### CRC
CRC has been validated by default, but you can skip it by just set second params to false

```kotlin
val merchantPresentedMode = MerchantPresentedDecoder.decode(
  "00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304A0BA",
  false
)
```
Compute CRC
```kotlin
CRC.calculate("00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304")
// A0BA
```
Validate CRC
```kotlin
CRC.validate("00020101021252044131153137210116372101160123456789ABCDE6010Phnom Penh5913Merchant Shop5802KH540510.005303840622501081234567802090124444446304A0BA")
// true
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details