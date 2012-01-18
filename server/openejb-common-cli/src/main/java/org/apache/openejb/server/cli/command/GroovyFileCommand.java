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
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.openejb.server.cli.command;

import org.apache.openejb.server.groovy.OpenEJBGroovyShell;

import java.io.File;

public class GroovyFileCommand extends AbstractCommand {
    private OpenEJBGroovyShell shell;

    @Override
    public String name() {
        return "groovy file";
    }

    @Override
    public String usage() {
        return name() + " <groovy file path>";
    }

    @Override
    public String description() {
        return "execute groovy code contained in a file. ejb can be accessed through their ejb name in the script.";
    }

    @Override
    public void execute(final String cmd) {
        try {
            streamManager.writeOut(streamManager.asString(shell.evaluate(new File(cmd.substring(name().length() + 1).trim()))));
        } catch (Exception e) {
            streamManager.writeErr(e);
        }
    }

    public void setShell(OpenEJBGroovyShell shell) {
        this.shell = shell;
    }
}
