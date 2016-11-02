PE.utils.io
=============

> 处理InputStream/OutputStream与String之间的转换函数

* PEInputStreamUtils: 放置InputStream与String转换工具函数

```java
// String to InputStream
String str = "hello world";
InputStream inputStreamWithoutCharset = PEInputStreamUtils.convertStringToInputStream(str);
InputStream inputStreamWithCharset = PEInputStreamUtils.convertStringToInputStream(str, "GB2312");

// InputStream to String
String str = "hello 世界";
InputStream inputStream = new ByteArrayInputStream(str.getBytes());
String resultStrWithoutCharset = PEInputStreamUtils.convertInputStreamToString(inputStream);
inputStream = new ByteArrayInputStream(str.getBytes());
String resultStrWithCharset = PEInputStreamUtils.convertInputStreamToString(inputStream, "GB2312");

// To get resource from net
String url = "http://www.baidu.com";
int connectTimeOut = 5000;
int readTimeOut = 2000;
InputStream httpResourceInputStream1 = PEInputStreamUtils.getHttpResourceAsInputStream(url, 2000, 5000);

```

* PEOutputStreamUtils: 放置OutputStream与String转换工具函数

```java
// String to OutputStream
String str = "hello world";
OutputStream inputStreamWithoutCharset = PEOutputStreamUtils.convertStringToOutputStream(str);
OutputStream inputStreamWithCharset = PEOutputStreamUtils.convertStringToOutputStream(str, "GB2312");

// OutputStream to String
String str = "hello 世界";
OutputStream outputStream = new ByteArrayOutputStream();
outputStream.write(str.getBytes());
String resultStrWithoutCharset = PEOutputStreamUtils.convertOutputStreamToString(outputStream);
outputStream = new ByteArrayOutputStream();
outputStream.write(str.getBytes());
String resultStrWithCharset = PEOutputStreamUtils.convertOutputStreamToString(outputStream, "GB2312");
```
