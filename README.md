# EMV QR Code
[![Actions Status](https://github.com/phannaly/emv-qr-code/workflows/Build%20and%20test/badge.svg)](https://github.com/phannaly/emv-qr-code/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


EMV QR Code library build with Kotlin 

## Usage

You can install this package from the central Maven repositories:

Maven
```xml
<dependency>
    <groupId>com.github.phannaly</groupId>
    <artifactId>emv-qr-code</artifactId>
    <version>0.0.1</version>
</dependency>
```

Gradle
```kotlin
implementation 'com.github.phannaly:emv-qr-code:0.0.1'
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

### Merchant Presented Mode encode

```kotlin
val merchantPresentedMode = MerchantPresentedEncoder()

merchantPresentedMode.pointOfInitiationMethod = "12"
merchantPresentedMode.payloadFormatIndicator = "01"
merchantPresentedMode.merchantAccountInformation = "37210116372101160123456789ABCDE"
merchantPresentedMode.merchantCategoryCode = "4131"
merchantPresentedMode.transactionCurrency = "840"
merchantPresentedMode.transactionAmount = "10.00"
merchantPresentedMode.countryCode = "KH"
merchantPresentedMode.merchantName = "Kuntheas Shop"
merchantPresentedMode.merchantCity = "Banteay Meanchey"
merchantPresentedMode.additionalDataFieldTemplate = "010812345678"

merchantPresentedMode.encode()
//"00020101021252044131153137210116372101160123456789ABCDE6016Banteay Meanchey5913Kuntheas Shop5802KH540510.005303840621201081234567863041FF2"

```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details