/*
 * Copyright 2017 Apereo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dytech.edge.installer.application;

import com.dytech.devlib.PropBagEx;
import com.dytech.installer.Installer;
import com.dytech.installer.InstallerException;
import java.io.InputStream;

public class Migrate {
  public Migrate() {}

  public void start() throws InstallerException {
    InputStream script = getClass().getResourceAsStream("/script/migrate-script.xml");
    InputStream commands = getClass().getResourceAsStream("/script/migrate-commands.xml");

    new Installer(new PropBagEx(script), new PropBagEx(commands));
  }

  public static void main(String[] args) throws Exception {
    Migrate migrate = new Migrate();
    migrate.start();
  }
}
