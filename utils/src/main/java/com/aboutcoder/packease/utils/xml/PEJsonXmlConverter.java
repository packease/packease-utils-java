package com.aboutcoder.packease.utils.xml;

import com.aboutcoder.packease.utils.io.PEInputStreamUtils;
import com.aboutcoder.packease.utils.io.PEOutputStreamUtils;
import de.odysseus.staxon.json.JsonXMLInputFactory;
import de.odysseus.staxon.json.JsonXMLOutputFactory;
import de.odysseus.staxon.xml.util.PrettyXMLEventWriter;
import de.odysseus.staxon.xml.util.PrettyXMLStreamWriter;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/8/16 3:07 PM<br />
 * @description <br />
 */
public class PEJsonXmlConverter {

    /**
     * Copying JSON to XML via XSL Transformation
     * {@link Transformer#transform(Source, Result)}.
     *
     * If the <code>multiplePI</code> property is
     * set to <code>true</code>, the StAXON reader will generate
     * <code>&lt;xml-multiple&gt;</code> processing instructions
     * which would be copied to the XML output.
     * These can be used by StAXON when converting back to JSON
     * to trigger array starts.
     * Set to <code>false</code> if you don't need to go back to JSON.
     *
     * @param json
     * @param jsonXMLConfig
     * @return
     */
    public static OutputStream convertJsonToXmlViaXSL(String json, PEJsonXMLConfig jsonXMLConfig) {
        try {
            InputStream input = PEInputStreamUtils.convertStringToInputStream(json);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            /**
             * Create source (JSON).
             */
            XMLStreamReader reader = new JsonXMLInputFactory(jsonXMLConfig).createXMLStreamReader(input);
            Source source = new StAXSource(reader);

            /**
             * Create result (XML).
             */
            XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
            Result result = new StAXResult(new PrettyXMLStreamWriter(writer));

            /**
             * Copy source to result via "identity transform".
             */
            TransformerFactory.newInstance().newTransformer().transform(source, result);

            /**
             * As per StAX specification, XMLStreamReader/Writer.close() doesn't close
             * the underlying stream.
             */
            output.close();
            input.close();

            return output;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Copying JSON to XML via StAX Event API
     * {@link XMLEventWriter#add(XMLEventReader)}.
     *
     * If the <code>multiplePI</code> property is
     * set to <code>true</code>, the StAXON reader will generate
     * <code>&lt;xml-multiple&gt;</code> processing instructions
     * which would be copied to the XML output.
     * These can be used by StAXON when converting back to JSON
     * to trigger array starts.
     * Set to <code>false</code> if you don't need to go back to JSON.
     *
     * @param json
     * @param jsonXMLConfig
     * @return
     */
    public static OutputStream convertJsonToXmlViaStAX(String json, PEJsonXMLConfig jsonXMLConfig) {
        try {
            InputStream input = PEInputStreamUtils.convertStringToInputStream(json);
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            /**
             * Create reader (JSON).
             */
            XMLEventReader reader = new JsonXMLInputFactory(jsonXMLConfig).createXMLEventReader(input);

            /**
             * Create writer (XML).
             */
            XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(output);
            writer = new PrettyXMLEventWriter(writer);

            /**
             * Copy events from reader to writer.
             */
            writer.add(reader);

            /**
             * Close reader/writer.
             */
            writer.close();
            reader.close();

            /**
             * As per StAX specification, XMLStreamReader/Writer.close() doesn't close
             * the underlying stream.
             */
            output.close();
            input.close();

            return output;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Copying XML to JSON via XSL Transformation
     * {@link Transformer#transform(Source, Result)}.
     *
     * If we want to insert JSON array boundaries for multiple elements,
     * we need to set the <code>autoArray</code> property.
     * If our XML source was decorated with <code>&lt;?xml-multiple?&gt;</code>
     * processing instructions, we'd set the <code>multiplePI</code>
     * property instead.
     * With the <code>autoPrimitive</code> property set, element text gets
     * automatically converted to JSON primitives (number, boolean, null).
     *
     * @param input
     * @param jsonXMLConfig
     * @return
     */
    public static String convertXmlToJsonViaXSL(InputStream input, PEJsonXMLConfig jsonXMLConfig) {
        try {
            OutputStream output = new ByteArrayOutputStream();

            /**
             * Create source (XML).
             */
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(input);
            Source source = new StAXSource(reader);

            /**
             * Create result (JSON).
             */
            XMLStreamWriter writer = new JsonXMLOutputFactory(jsonXMLConfig).createXMLStreamWriter(output);
            Result result = new StAXResult(writer);

            /**
             * Copy source to result via "identity transform".
             */
            TransformerFactory.newInstance().newTransformer().transform(source, result);

            /**
             * As per StAX specification, XMLStreamReader/Writer.close() doesn't close
             * the underlying stream.
             */
            output.close();
            input.close();

            return PEOutputStreamUtils.convertOutputStreamToString(output);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Copying XML to JSON via StAX Event API
     * {@link XMLEventWriter#add(XMLEventReader)}.
     *
     * If we want to insert JSON array boundaries for multiple elements,
     * we need to set the <code>autoArray</code> property.
     * If our XML source was decorated with <code>&lt;?xml-multiple?&gt;</code>
     * processing instructions, we'd set the <code>multiplePI</code>
     * property instead.
     * With the <code>autoPrimitive</code> property set, element text gets
     * automatically converted to JSON primitives (number, boolean, null).
     *
     * @param input
     * @param jsonXMLConfig
     * @return
     */
    public static String convertXmlToJsonViaStAX(InputStream input, PEJsonXMLConfig jsonXMLConfig) {
        try {
            OutputStream output = new ByteArrayOutputStream();

            /**
             * Create reader (XML).
             */
            XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(input);

            /**
             * Create writer (JSON).
             */
            XMLEventWriter writer = new JsonXMLOutputFactory(jsonXMLConfig).createXMLEventWriter(output);

            /**
             * Copy events from reader to writer.
             */
            writer.add(reader);

            /**
             * Close reader/writer.
             */
            writer.close();
            reader.close();

            /**
             * As per StAX specification, XMLEventReader/Writer.close() doesn't close
             * the underlying stream.
             */
            output.close();
            input.close();

            return PEOutputStreamUtils.convertOutputStreamToString(output);
        } catch (Exception e) {
            return null;
        }
    }
}
