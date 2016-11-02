PE.utils.xml
=============

> 构造JsonXml解析器, 实现json与xml互相转换。

* PEJsonXMLConfig: 配置bean,按需配置

* PEJsonXmlConfigFactory: PEJsonXMLConfig的工厂类

* PEJsonXmlConverter: JsonXml转换器

(1) json转换为xml

OutputStream convertJsonToXmlViaXSL(String json, PEJsonXMLConfig jsonXMLConfig)

OutputStream convertJsonToXmlViaStAX(String json, PEJsonXMLConfig jsonXMLConfig)

(2) xml转换为json

String convertXmlToJsonViaXSL(InputStream input, PEJsonXMLConfig jsonXMLConfig)

String convertXmlToJsonViaStAX(InputStream input, PEJsonXMLConfig jsonXMLConfig)

```js
// JSON格式
{
  "customer": {
    "first-name": "Jane",
    "last-name": "Doe",
    "address": {
      "street": "123 A Street"
    },
    "phone-number": [
      {
        "@type": "work",
        "$": "555-1111"
      },
      {
        "@type": "cell",
        "$": "555-2222"
      }
    ]
  }
}

```

```xml
// XML格式
<?xml version="1.0" ?>
<customer>
	<first-name>Jane</first-name>
	<last-name>Doe</last-name>
	<address>
		<street>123 A Street</street>
	</address>
	<phone-number type="work">555-1111</phone-number>
	<phone-number type="cell">555-2222</phone-number>
</customer>

```