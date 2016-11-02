package com.aboutcoder.packease.utils;

import com.aboutcoder.packease.utils.date.PEDateUtilsTest;
import com.aboutcoder.packease.utils.excel.PESheetReaderTest;
import com.aboutcoder.packease.utils.excel.PESheetWriterTest;
import com.aboutcoder.packease.utils.io.PEInputStreamUtilsTest;
import com.aboutcoder.packease.utils.io.PEOutputStreamUtilsTest;
import com.aboutcoder.packease.utils.json.PEFastJsonHandlerTest;
import com.aboutcoder.packease.utils.json.PEJacksonHandlerTest;
import com.aboutcoder.packease.utils.json.PEJsonPathHandlerTest;
import com.aboutcoder.packease.utils.string.PEBase64UtilsTest;
import com.aboutcoder.packease.utils.string.PEMd5UtilsTest;
import com.aboutcoder.packease.utils.string.PEStringUtilsTest;
import com.aboutcoder.packease.utils.thread.PEAsyncProxyTest;
import com.aboutcoder.packease.utils.thread.PEThreadPoolFixedTest;
import com.aboutcoder.packease.utils.xml.PEJsonXmlConverterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 2:57 PM<br />
 * @description <br />
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        PEDateUtilsTest.class,
        PESheetReaderTest.class,
        PESheetWriterTest.class,
        PEInputStreamUtilsTest.class,
        PEOutputStreamUtilsTest.class,
        PEFastJsonHandlerTest.class,
        PEJacksonHandlerTest.class,
        PEJsonPathHandlerTest.class,
        PEBase64UtilsTest.class,
        PEMd5UtilsTest.class,
        PEStringUtilsTest.class,
        PEAsyncProxyTest.class,
        PEThreadPoolFixedTest.class,
        PEJsonXmlConverterTest.class,
})

public class PEUtilsSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}
