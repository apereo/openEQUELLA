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

package com.tle.upgrade.upgraders;

import java.io.File;

import com.dytech.common.io.FileUtils;
import com.tle.upgrade.UpgradeResult;

@SuppressWarnings("nls")
public class ConvertBoneCPtoHikariCP extends AbstractUpgrader {

  @Override
  public String getId() {
    return "ConvertBoneCPtoHikariCP";
  }

  @Override
  public boolean isBackwardsCompatible() {
    return true;
  }

  @Override
  public void upgrade(UpgradeResult result, File tleInstallDir) throws Exception {
    File configFolder = new File(tleInstallDir, CONFIG_FOLDER);
    copyResource("data/hikari.properties", configFolder);
    File existingBoneCP = new File(configFolder, "bonecp.properties");
    if (existingBoneCP.exists()) {
      FileUtils.delete(existingBoneCP);
    }
  }
}
