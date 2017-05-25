package com.aboutcoder.packease.utils.xml;

import com.aboutcoder.packease.utils.io.PEInputStreamUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/8/16 11:07 PM<br />
 * @description <br />
 */
@RunWith(Parameterized.class)
public class PEJsonXmlConverterTest {

    @Parameterized.Parameter
    public String jsonString;

    @Parameterized.Parameter(value = 1)
    public String xmlString;

    @Test
    public void testConvertJsonToXmlViaXSL() {
        PEJsonXMLConfig peJsonXMLConfig = new PEJsonXmlConfigFactory().newPEJsonXmlConfigInstance();
        peJsonXMLConfig.setMultiplePI(false);

        OutputStream resultXmlOutputStream = PEJsonXmlConverter.convertJsonToXmlViaXSL(jsonString, peJsonXMLConfig);
        Assert.assertNotNull(resultXmlOutputStream);

        System.out.println(resultXmlOutputStream.toString());
    }

    @Test
    public void testConvertJsonToXmlViaStAX() {
        PEJsonXMLConfig peJsonXMLConfig = new PEJsonXmlConfigFactory().newPEJsonXmlConfigInstance();
        peJsonXMLConfig.setMultiplePI(false);

        OutputStream resultXmlOutputStream = PEJsonXmlConverter.convertJsonToXmlViaStAX(jsonString, peJsonXMLConfig);
        Assert.assertNotNull(resultXmlOutputStream);

        System.out.println(resultXmlOutputStream.toString());
    }

    @Test
    public void testConvertXmlToJsonViaXSL() {
        PEJsonXMLConfig peJsonXMLConfig = new PEJsonXmlConfigFactory().newPEJsonXmlConfigInstance();
        peJsonXMLConfig.setAutoArray(true);
        peJsonXMLConfig.setAutoPrimitive(true);
        peJsonXMLConfig.setPrettyPrint(true);

        InputStream xmlInputStream = PEInputStreamUtils.convertStringToInputStream(xmlString);
        String resultJson = PEJsonXmlConverter.convertXmlToJsonViaXSL(xmlInputStream, peJsonXMLConfig);
        Assert.assertNotNull(resultJson);

        System.out.println(resultJson);
    }

    @Test
    public void testConvertXmlToJsonViaStAX() {
        PEJsonXMLConfig peJsonXMLConfig = new PEJsonXmlConfigFactory().newPEJsonXmlConfigInstance();
        peJsonXMLConfig.setAutoArray(true);
        peJsonXMLConfig.setAutoPrimitive(true);
        peJsonXMLConfig.setPrettyPrint(true);

        InputStream xmlInputStream = PEInputStreamUtils.convertStringToInputStream(xmlString);
        String resultJson = PEJsonXmlConverter.convertXmlToJsonViaStAX(xmlInputStream, peJsonXMLConfig);
        Assert.assertNotNull(resultJson);

        System.out.println(resultJson);
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        jsonString = null;
        xmlString = null;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                    "{\"customer\":{\"first-name\":\"Jane\",\"last-name\":\"Doe\",\"address\":{\"street\":\"123 A Street\"},\"phone-number\":[{\"@type\":\"work\",\"$\":\"555-1111\"},{\"@type\":\"cell\",\"$\":\"555-2222\"}]}}",
                    "<?xml version=\"1.0\"?>\n" +
                            "<customer>\n" +
                            "    <first-name>Jane</first-name>\n" +
                            "    <last-name>Doe</last-name>\n" +
                            "    <address>\n" +
                            "        <street>123 A Street</street>\n" +
                            "    </address>\n" +
                            "    <phone-number type=\"work\">555-1111</phone-number>\n" +
                            "    <phone-number type=\"cell\">555-2222</phone-number>\n" +
                            "</customer>"
                }
        });
    }
}
