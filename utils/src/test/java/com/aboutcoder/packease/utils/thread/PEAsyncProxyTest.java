package com.aboutcoder.packease.utils.thread;

import org.junit.Test;

/**
 * <Description>
 * Copyright © 2013-2016 AboutCoder.COM. All rights reserved.<br />
 *
 * @author jeromechan<br />
 * @CreateDate 9/6/16 10:37 PM<br />
 * @description <br />
 */
public class PEAsyncProxyTest {

    @Test
    public void testPEAsyncProxy() {
        // To test onSuccess()
        PEAsyncProxyParam peAsyncProxyParam1 = new PEAsyncProxyParam() {
            /**
             * Processing async function
             */
            @Override
            public void execute() {
                super.execute();
                System.out.println("Executing in a new thread.........");
            }
        };

        new PEAsyncProxy<PEAsyncProxyParam>(peAsyncProxyParam1, new PEIAsyncProxyCallback() {
            public void onSuccess() {
                System.out.println("Invoking successfully.");
            }

            public void onError() {
                System.out.println("Invoking fail.");
            }
        });

        // To test onError()
        PEAsyncProxyParam peAsyncProxyParam2 = new PEAsyncProxyParam() {
            /**
             * Processing async function
             */
            @Override
            public void execute() {
                super.execute();
                System.out.println("Executing in a new thread.........");
                String str = null;
                int digit = Integer.parseInt(str);
            }
        };
        new PEAsyncProxy<PEAsyncProxyParam>(peAsyncProxyParam2, new PEIAsyncProxyCallback() {
            public void onSuccess() {
                System.out.println("Invoking successfully.");
            }

            public void onError() {
                System.out.println("Invoking fail.");
            }
        });
    }
}
