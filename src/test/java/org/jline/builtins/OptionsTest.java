/*
 * Copyright (c) 2002-2015, the original author or authors.
 *
 * This software is distributable under the BSD license. See the terms of the
 * BSD license in the documentation provided with this software.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jline.builtins;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OptionsTest {

    @Test
    public void testOptions() {
        final String[] usage = {
                "test - test Options usage",
                "  text before Usage: is displayed when usage() is called and no error has occurred.",
                "  so can be used as a simple help message.",
                "",
                "Usage: testOptions [OPTION]... PATTERN [FILES]...",
                "  Output control: arbitary non-option text can be included.",
                "  -? --help                show help",
                "  -c --count=COUNT           show COUNT lines",
                "  -h --no-filename         suppress the prefixing filename on output",
                "  -q --quiet, --silent     suppress all normal output",
                "     --binary-files=TYPE   assume that binary files are TYPE",
                "                           TYPE is 'binary', 'text', or 'without-match'",
                "  -I                       equivalent to --binary-files=without-match",
                "  -d --directories=ACTION  how to handle directories (default=skip)",
                "                           ACTION is 'read', 'recurse', or 'skip'",
                "  -D --devices=ACTION      how to handle devices, FIFOs and sockets",
                "                           ACTION is 'read' or 'skip'",
                "  -R, -r --recursive       equivalent to --directories=recurse" };

        Options opt = Options.compile(usage).parse("test -c 2 --binary-files=foo --binary-files bar pattern".split("\\s"));
        assertTrue(opt.isSet("count"));
        assertEquals(2, opt.getNumber("count"));
        assertFalse(opt.isSet("no-filename"));
        assertTrue(opt.isSet("binary-files"));
        assertEquals(Arrays.asList("foo", "bar"), opt.getList("binary-files"));
        assertEquals(Arrays.asList("test", "pattern"), opt.args());
    }
}
