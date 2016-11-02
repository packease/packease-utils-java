package com.aboutcoder.packease.utils.json;

import com.jayway.jsonpath.TypeRef;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 24/09/2016 2:01 PM<br />
 * @description <br />
 */
@RunWith(Parameterized.class)
public class PEJsonPathHandlerTest {

    @Parameterized.Parameter(value = 0)
    public String jsonString;

    @Parameterized.Parameter(value = 1)
    public String pathExpression;

    @Test
    public void testReadPropertiesWithDefault() {
        List<String> stringList = PEJsonPathHandler.readProperties(jsonString, pathExpression);
        Assert.assertTrue(stringList.size() == 4);
    }

    @Test
    public void testReadPropertiesWithConfig() {
        PEJsonPathConfig peJsonPathConfig = new PEJsonPathConfig();
        List<String> stringList = PEJsonPathHandler.readProperties(jsonString, pathExpression, peJsonPathConfig);
        Assert.assertTrue(stringList.size() == 4);

        peJsonPathConfig.setAsPathList(true);
        List<String> pathList = PEJsonPathHandler.readProperties(jsonString, pathExpression, peJsonPathConfig);
        Assert.assertTrue(pathList.size() == 4);

        peJsonPathConfig.setAsPathList(false);
        peJsonPathConfig.setAlwaysReturnList(true);
        // This will only output one value as String type.
        List<String> stringListAbsoluteList = PEJsonPathHandler.readProperties(jsonString, "$.store.book[999].author", peJsonPathConfig);
        Assert.assertTrue(null != stringListAbsoluteList && stringListAbsoluteList.size() == 0);
    }

    @Test
    public void testReadPropertiesWithClassType() {
        List<Map<Integer, String>> stringList = PEJsonPathHandler.readProperties(jsonString, pathExpression, List.class);
        Assert.assertTrue(stringList.size() == 4);

        PEBookNode bookNode = PEJsonPathHandler.readProperties(jsonString, "$.store.book[0]", PEBookNode.class);
        Assert.assertNotNull(bookNode);

        PEBookNode bookNodeType = PEJsonPathHandler.readProperties(jsonString, "$.store.book[0]", new TypeRef<PEBookNode>() {
        });
        Assert.assertNotNull(bookNodeType);

        String json = "{\"date_as_long\" : 1411455611975}";
        Date date = PEJsonPathHandler.readProperties(json, "$['date_as_long']", Date.class);
        Assert.assertNotNull(date);
    }

    @Test
    public void testReadPropertiesWithTypeRef() {
        PEStoreNode<List<PEBookNode>> bookNode = PEJsonPathHandler.readProperties(jsonString, "$.store", new TypeRef<PEStoreNode<List<PEBookNode>>>() {
        });
        Assert.assertNotNull(bookNode);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}", "$.store.book[*].author"},
                {"{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}", "$.store.book[0,1,2,3].author"}
        });
    }
}
