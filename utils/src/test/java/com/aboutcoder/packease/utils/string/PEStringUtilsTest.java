package com.aboutcoder.packease.utils.string;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/7/16 12:15 AM<br />
 * @description <br />
 */
public class PEStringUtilsTest {
    @Rule
    public Timeout globalTimeout = new Timeout(3000);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(timeout = 2000)
    public void testLocalTimeoutSettings() throws Exception{
        System.out.println("test timeout settings of method only.");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void testGlobalTimeoutSettings() throws Exception{
        System.out.println("test timeout settings of test class.");
        TimeUnit.MILLISECONDS.sleep(2000);
    }

    @Test
    public void testIsEmptyOrIsNotEmpty() throws Exception{
        String strEmpty = "";
        String strNotEmpty = "abc";
        Assert.assertTrue(PEStringUtils.isEmpty(strEmpty));
        Assert.assertTrue(PEStringUtils.isNotEmpty(strNotEmpty));
    }

    @Test
    public void testStringToArray() {
        String str = "a,b,c";
        String[] strArr = PEStringUtils.stringToArray(str, ",");
        Assert.assertEquals(3, strArr.length);
    }

    @Test
    public void testArrayToString() {
        String[] strArr = {"a","b","c"};
        String targetStr = "a,b,c";
        Assert.assertEquals(targetStr, PEStringUtils.arrayToString(strArr, ","));
    }

    @Test
    public void testListToString() {
        List<String> stringList = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
        }};
        String targetStringList = "a,b,c";
        Assert.assertEquals(PEStringUtils.listToString(stringList, ","), targetStringList);

        // when we use junit-4.11,
        // we recommend using CoreMatchers.hasItems() rather than using "import static CoreMatchers.hasItems" sentence.
        Assert.assertThat("There aren't any string 'b' in stringList object.", stringList, CoreMatchers.hasItems("b"));

        List<Integer> integerList = Arrays.asList(1, 2, 3);
        String targetIntegerList = "1,2,3";
        Assert.assertEquals(PEStringUtils.listToString(integerList, ","), targetIntegerList);

        List<BigDecimal> decimalListList = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        String targetDecimalList = "1,2,3";
        Assert.assertEquals(PEStringUtils.listToString(decimalListList, ","), targetDecimalList);

        List<Object> objectList = Arrays.asList(new Object(), new Object(), new Object());
        expectedException.expect(IllegalArgumentException.class);
        PEStringUtils.listToString(objectList, ",");
    }
}
