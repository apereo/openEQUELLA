/*
 * Licensed to the Apereo Foundation under one
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

package com.tle.web.settings

import com.tle.common.connectors.ConnectorConstants.{PRIV_CREATE_CONNECTOR, PRIV_EDIT_CONNECTOR}
import com.tle.common.externaltools.constants.ExternalToolConstants
import com.tle.common.lti.consumers.LtiConsumerConstants
import com.tle.common.security.SecurityConstants
import com.tle.common.userscripts.UserScriptsConstants
import com.tle.core.activation.service.CourseInfoService
import com.tle.core.db.{DB, RunWithDB}
import com.tle.core.echo.EchoConstants
import com.tle.core.i18n.CoreStrings
import com.tle.core.oauth.OAuthConstants
import com.tle.core.security.AclChecks
import com.tle.legacy.LegacyGuice._
import com.tle.web.mimetypes.MimeEditorUtils
import com.tle.web.sections.render.TextLabel
import com.tle.web.sections.standard.model.{HtmlLinkState, SimpleBookmark}

import scala.collection.mutable

object CoreSettingsPage
{
  def apply(id: String, group: String, nameKey: String, descKey: String, page: String, editable: () => Boolean) =
    SettingsPage(CoreStrings.lookup, id, group, nameKey, descKey, page, "web", editable)
}

object CoreSettingsRest
{
  def apply(id: String, group: String, nameKey: String, descKey: String, endpoint: String, editable: DB[Set[String]]) =
    SettingsPage(CoreStrings.lookup, id, group, nameKey, descKey, endpoint, "rest", () =>
      RunWithDB.execute(editable).nonEmpty)
}

object SettingsList {
  val General = "general"
  val Integration = "integration"

  def +=(setting: EditableSettings): Unit = synchronized {
    allSettings += setting
  }

  def asLinkOrNull(settings: EditableSettings) : HtmlLinkState = {
    new HtmlLinkState(new TextLabel(settings.name), new SimpleBookmark(settings.uri))
  }

  val uiSettings = CoreSettingsRest("ui", "ui", "uisettings.name", "uisettings.desc", "api/settings/ui",
    AclChecks.filterNonGrantedPrivileges(Iterable("EDIT_SYSTEM_SETTINGS"), false))

  val echoSettings = CoreSettingsPage("echo", Integration, "echo.settings.title", "echo.settings.description",
    "access/echoservers.do", () => !aclManager.filterNonGrantedPrivileges(EchoConstants.PRIV_CREATE_ECHO, EchoConstants.PRIV_EDIT_ECHO).isEmpty)

  val connectorSettings = CoreSettingsPage("connectors", Integration,"connector.setting.title", "connector.setting.description",
    "access/connectors.do", () => !aclManager.filterNonGrantedPrivileges(PRIV_CREATE_CONNECTOR, PRIV_EDIT_CONNECTOR).isEmpty)

  val ltiConsumersSettings = CoreSettingsPage("lti", Integration, "lti.settings.title", "lti.settings.description",
    "access/lticonsumers.do", () => !aclManager.filterNonGrantedPrivileges(LtiConsumerConstants.PRIV_CREATE_CONUSMER,
      LtiConsumerConstants.PRIV_EDIT_CONSUMER).isEmpty)

  val userScriptSettings = CoreSettingsPage("scripts", General, "scripts.settings.title", "scripts.settings.description",
    "access/userscripts.do", () => !aclManager.filterNonGrantedPrivileges(UserScriptsConstants.PRIV_CREATE_SCRIPT,
      UserScriptsConstants.PRIV_EDIT_SCRIPT).isEmpty)

  val oauthSettings = CoreSettingsPage("oauth", Integration, "oauth.page.title", "oauth.setting.description",
    "access/oauthadmin.do", () => !aclManager.filterNonGrantedPrivileges(OAuthConstants.PRIV_CREATE_OAUTH_CLIENT,
      OAuthConstants.PRIV_EDIT_OAUTH_CLIENT, OAuthConstants.PRIV_ADMINISTER_OAUTH_TOKENS).isEmpty)

  val htmlEditorSettings = CoreSettingsPage("htmleditor", General, "htmledit.settings.title", "htmledit.settings.description",
    "access/editoradmin.do", htmlEditorPrivProvider.isAuthorised)

  val externalToolsSettings = CoreSettingsPage("externaltools", Integration, "tools.settings.title", "tools.settings.description",
    "access/externaltools.do", () => !aclManager.filterNonGrantedPrivileges(ExternalToolConstants.PRIV_CREATE_TOOL, ExternalToolConstants.PRIV_EDIT_TOOL).isEmpty)

  val allSettings : mutable.Buffer[EditableSettings] = mutable.Buffer(
    connectorSettings, echoSettings, ltiConsumersSettings, userScriptSettings,
    oauthSettings, htmlEditorSettings, externalToolsSettings, uiSettings,

    CoreSettingsPage("shortcuts", General, "shortcuts.settings.title", "shortcuts.settings.description",
      "access/shortcuturlssettings.do", shortcutPrivProvider.isAuthorised),

    CoreSettingsPage("language", General, "language.title", "language.description",
      "access/language.do", langPrivProvider.isAuthorised),

    CoreSettingsPage("googleapi", General, "google.settings.title", "google.settings.description",
      "access/googleapisettings.do", googlePrivProvider.isAuthorised),

    CoreSettingsPage("search", General, "settings.link.title", "settings.link.description",
      "access/searchsettings.do", searchPrivProvider.isAuthorised),

    CoreSettingsPage("login", General, "login.title", "login.description",
      "access/loginsettings.do", loginPrivProvider.isAuthorised),

    CoreSettingsPage("quickcontrib", General, "quickcontributeandversionsettings.title", "quickcontributeandversionsettings.description",
      "access/quickcontributeandversionsettings.do", quickContribPrivProvider.isAuthorised),

    CoreSettingsPage("datafixes", "diagnostics", "fix.settings.title", "fix.settings.description",
      "access/manualdatafixes.do", manualFixPrivProvider.isAuthorised),

    CoreSettingsPage("oai", Integration, "oai.title", "oaiidentifier.description",
      "access/oaiidentifiersettings.do", oaiPrivProvider.isAuthorised),

    CoreSettingsPage("harvester", General, "harvesterskipdrmsettings.title", "harvesterskipdrmsettings.description",
      "access/harvesterskipdrmsettings.do", harvesterPrivProvider.isAuthorised),

    CoreSettingsPage("scheduler", General, "scheduler.settings.title", "scheduler.settings.description",
      "access/settings/scheduledtasks.do", scheduledPrivProvider.isAuthorised),

    CoreSettingsPage("mimetypes", General, "mimetypes.settings.title", "mimetypes.settings.description",
      MimeEditorUtils.MIME_BOOKMARK, mimePrivProvider.isAuthorised),

    CoreSettingsPage("dates", General, "dates.settings.title", "dates.settings.description",
      "access/dateformatsettings.do", datePrivProvider.isAuthorised),

    CoreSettingsPage("theme", General, "customisation.settings.title", "customisation.settings.description",
      "access/themesettings.do", themePrivProvider.isAuthorised),

    CoreSettingsPage("googleanalytics", General, "analytics.settings.title", "analytics.settings.description",
      "access/googleAnalyticsPage.do", analyticsPrivProvider.isAuthorised),

    CoreSettingsPage("diagnostics", "diagnostics", "diagnostics.settings.title", "diagnostics.settings.description",
      "access/diagnostics.do", diagnosticPrivProvider.isAuthorised),

    CoreSettingsPage("mail", General, "settings.title", "settings.description",
      "access/mailsettings.do", mailPrivProvider.isAuthorised),

    CoreSettingsPage("customlinks", General, "menu.title", "menu.description",
      "access/customlinks.do", () => !aclManager.filterNonGrantedPrivileges("EDIT_CUSTOM_LINK").isEmpty),

    CoreSettingsPage("remotecaching", General, "remotecaching.title", "remotecaching.description",
      "access/remotecaching.do", remoteCachePrivProvider.isAuthorised),

    CoreSettingsPage("loggedin", "diagnostics", "liu.settings.title", "liu.settings.description",
      "access/liu.do", liuPrivProvider.isAuthorised),

    CoreSettingsPage("copyright", Integration, "coursedefaults.title", "coursedefaults.description",
      "access/coursedefaultssettings.do", courseDefPrivProvider.isAuthorised),

    CoreSettingsPage("contentrestrictions", General, "contentrestrictions.title", "contentrestrictions.description",
      "access/contentrestrictions.do", contentRestricPrivProvider.isAuthorised),

    CoreSettingsPage("portals", General, "setting.title", "setting.description",
      "access/portaladmin.do", portletWebService.canAdminister),

    CoreSettingsPage("courses", Integration, "courses.title", "courses.description",
      "page/course", () => !aclManager.filterNonGrantedPrivileges(CourseInfoService.PRIV_CREATE_COURSE,
        CourseInfoService.PRIV_EDIT_COURSE).isEmpty)

//    CoreSettingsPage("schemas", "entities", "schemas.title", "schemas.description",
//      "page/schema", () => true),
  )

  def anyEditable = allSettings.exists(_.isEditable)
}
