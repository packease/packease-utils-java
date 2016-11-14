PE.utils.string
=============

* PEMd5Utils: 定义md5加密方法

* PEStringUtils: 提供常见字符串操作静态函数

* PESerializationUtils: 提供对象或字符串的序列化/反序列化工具函数

* PEBase64Utils: 提供Base64编解码的静态操作函数

```java
// Base64 encode with configurations.
String beforeBase64Encode1 = "hello world";
PEBase64EncodeConfig base64EncodeConfig = new PEBase64EncodeConfig();
String afterBase64Encode1 = PEBase64Utils.encode(beforeBase64Encode1, base64EncodeConfig);

// Base64 encode without configurations.
String beforeBase64Encode2 = "hello world";
String afterBase64Encode2 = PEBase64Utils.encode(beforeBase64Encode2);

// Base64 decode with configurations.
String afterBase64Encode = "aGVsbG8gd29ybGQ=";
String beforeBase64Encode1 = PEBase64Utils.decode(afterBase64Encode, "UTF-8");

// Base64 decode without configurations.
String afterBase64Encode = "aGVsbG8gd29ybGQ=";
String beforeBase64Encode1 = PEBase64Utils.decode(afterBase64Encode);

```