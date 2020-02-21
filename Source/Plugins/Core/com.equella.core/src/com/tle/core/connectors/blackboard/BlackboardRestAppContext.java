/*
 * Licensed to The Apereo Foundation under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * The Apereo Foundation licenses this file to you under the Apache License,
 * Version 2.0, (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.core.connectors.blackboard;

import com.tle.annotation.Nullable;
import com.tle.core.connectors.brightspace.D2LSigner;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;

// TODO Taken from the D2L logic.  May not need everything
public class BlackboardRestAppContext {
  private static final String APP_ID_PARAMETER = "x_a";
  private static final String APP_KEY_PARAMETER = "x_b";
  private static final String CALLBACK_URL_PARAMETER = "x_target";
  private static final String STATE_PARAMETER = "state";
  private static final String USER_ID_CALLBACK_PARAMETER = "x_a";
  private static final String USER_KEY_CALLBACK_PARAMETER = "x_b";

  public static final String FIELD_CODE = "code";
  public static final String VALUE_CODE = "code";
  public static final String FIELD_REDIRECT_URI = "redirect_uri";
  public static final String FIELD_RESPONSE_TYPE = "response_type";
  public static final String FIELD_CLIENT_ID = "client_id";
  public static final String FIELD_SCOPE = "scope";
  public static final String VALUE_WRITE = "write";

  private final String _appId;
  private final String _appKey;
  private String _url;

  /**
   * Constructs a D2LAppContext with the provided application values
   *
   * @param appId The application ID provided by the key tool
   * @param appKey The application key provided by the key tool
   * @param url The url of the D2L instance
   */
  public BlackboardRestAppContext(String appId, String appKey, String url) {
    _appId = appId;
    _appKey = appKey;
    if (url != null && url.endsWith("/")) {
      _url = url.substring(0, url.lastIndexOf("/"));
    } else {
      _url = url;
    }
  }

  public URI createWebUrlForAuthenticationWithCode(
      URI redirectUrl, @Nullable String state, String code) {
    try {
      URI uri =
          new URI(
              _url
                  + BlackboardRESTConnectorConstants.AUTHENTICATION_SERVICE_URI_PATH
                  + "?"
                  + buildAuthenticationUriQueryStringWithCode(redirectUrl, state, code));
      return uri;
    } catch (URISyntaxException e) {
      return null;
    }
  }

  public URI createWebUrlForAuthenticationToken(URI redirectUrl, @Nullable String state) {
    try {
      URI uri =
          new URI(
              _url
                  + BlackboardRESTConnectorConstants.AUTHENTICATIONCODE_SERVICE_URI_PATH
                  + "?"
                  + buildAuthenticationCodeUriQueryString(redirectUrl, state));
      return uri;
    } catch (URISyntaxException e) {
      return null;
    }
  }

  public URI createWebUrlForAuthentication(URI redirectUrl, @Nullable String state) {
    try {
      URI uri =
          new URI(
              _url
                  + BlackboardRESTConnectorConstants.AUTHENTICATIONCODE_SERVICE_URI_PATH
                  + "?"
                  + buildAuthenticationCodeUriQueryString(redirectUrl, state));
      return uri;
    } catch (URISyntaxException e) {
      return null;
    }
  }

  private HashMap<String, String> getParameters(String queryString) {
    HashMap<String, String> result = new HashMap<String, String>();
    String[] strings = queryString.split(("&"));
    for (String s : strings) {
      String[] split = s.split("=");
      if (split.length == 2) {
        result.put(split[0], split[1]);
      }
    }
    return result;
  }

  public BlackboardRestUserContext createUserContext(URI uri) {
    if (uri.getQuery() != null) {
      HashMap<String, String> r = getParameters(uri.getQuery());
      // use a mock servlet request so we can retrieve parameters from the uri conveniently
      String userId = r.get(USER_ID_CALLBACK_PARAMETER);
      String userKey = r.get(USER_KEY_CALLBACK_PARAMETER);
      if (userId == null || userKey == null) {
        return null;
      }
      return new BlackboardRestUserContext(_url, _appId, _appKey, userId, userKey);
    } else {
      return null;
    }
  }

  public BlackboardRestUserContext createUserContext(String userId, String userKey) {
    return new BlackboardRestUserContext(_url, _appId, _appKey, userId, userKey);
  }

  public BlackboardRestUserContext createAnonymousUserContext() {
    return new BlackboardRestUserContext(_url, _appId, _appKey, null, null);
  }

  /**
   * Constructs a URI to call for authentication given the target URI provided
   *
   * @param callbackUri The target which the D2L server should return to after authenticating
   * @return The URI for the user to authenticate against
   */
  private String buildAuthenticationUriQueryString(URI callbackUri, @Nullable String state) {
    String callbackUriString = callbackUri.toString();
    String uriHash = D2LSigner.getBase64HashString(_appKey, callbackUriString);
    String result = APP_ID_PARAMETER + "=" + _appId;
    result += "&" + APP_KEY_PARAMETER + "=" + uriHash;
    try {
      result += "&" + CALLBACK_URL_PARAMETER + "=" + URLEncoder.encode(callbackUriString, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      result += "&" + CALLBACK_URL_PARAMETER + "=" + URLEncoder.encode(callbackUriString);
    }
    if (state != null) {
      result += "&" + STATE_PARAMETER + "=" + URLEncoder.encode(state);
    }
    return result;
  }

  private String buildAuthenticationUriQueryStringWithCode(
      URI callbackUri, @Nullable String state, String code) {
    String callbackUriString = callbackUri.toString();
    String result = FIELD_CODE + "=" + code;
    result += "&" + FIELD_REDIRECT_URI + "=" + callbackUriString;
    return result;
  }

  private String buildAuthenticationCodeUriQueryString(URI callbackUri, @Nullable String state) {
    String callbackUriString = callbackUri.toString();
    String result = FIELD_RESPONSE_TYPE + "=" + VALUE_CODE;
    result += "&" + FIELD_REDIRECT_URI + "=" + callbackUriString;
    result += "&" + FIELD_CLIENT_ID + "=" + _appId;
    result += "&" + FIELD_SCOPE + "=" + VALUE_WRITE;
    if (state != null) {
      result += "&" + STATE_PARAMETER + "=" + URLEncoder.encode(state);
    }
    return result;
  }

  public String getAppId() {
    return _appId;
  }

  public String getAppKey() {
    return _appKey;
  }

  public String getUrl() {
    return _url;
  }
}
