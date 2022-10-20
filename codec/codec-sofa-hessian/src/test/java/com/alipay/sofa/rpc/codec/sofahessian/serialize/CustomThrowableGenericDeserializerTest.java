/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.rpc.codec.sofahessian.serialize;

import com.alipay.hessian.generic.model.GenericObject;
import org.junit.Assert;
import org.junit.Test;

import static com.alipay.sofa.rpc.codec.sofahessian.serialize.CustomThrowableGenericDeserializer.judgeCustomThrowable;
import static com.alipay.sofa.rpc.codec.sofahessian.serialize.CustomThrowableGenericDeserializer.setGenericThrowException;

/**
 *
 * @author xingqi
 * @version : CustomThrowableGenericDeserializerTest.java, v 0.1 2022年10月20日 4:23 PM xingqi Exp $
 */
public class CustomThrowableGenericDeserializerTest {

    @Test
    public void testJudgeCustomThrowable() throws Exception {
        setGenericThrowException(true);
        try {
            Assert.assertNull(judgeCustomThrowable(null));
            Object o = new Object();
            Assert.assertEquals(o, judgeCustomThrowable(o));
            GenericObject genericObject = new GenericObject("");
            Assert.assertEquals(genericObject, judgeCustomThrowable(genericObject));
            genericObject.putField("xxx", "yyy");
            Assert.assertEquals(genericObject, judgeCustomThrowable(genericObject));

            genericObject.putField("cause", "yyy");
            genericObject.putField("detailMessage", "yyy");
            genericObject.putField("stackTrace", "yyy");
            genericObject.putField("suppressedExceptions", "yyy");
            Assert.assertTrue(judgeCustomThrowable(genericObject) instanceof RuntimeException);
        } finally {
            setGenericThrowException(false);
        }
    }
}