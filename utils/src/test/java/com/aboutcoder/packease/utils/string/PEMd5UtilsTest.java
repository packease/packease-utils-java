package com.aboutcoder.packease.utils.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/7/16 12:15 AM<br />
 * @description <br />
 */
public class PEMd5UtilsTest {
    @Test
    public void testGenerateMd5() {
        String srcTarget = "e807f1fcf82d132f9bb018ca6738a19f";
        String srcAfterMd5 = PEMd5Utils.generateMd5("1234567890");
        Assert.assertTrue(srcTarget.equals(srcAfterMd5));
    }
}
