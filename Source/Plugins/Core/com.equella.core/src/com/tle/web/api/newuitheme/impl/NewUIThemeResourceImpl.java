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

package com.tle.web.api.newuitheme.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.*;
import java.net.URI;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.tle.core.guice.Bind;
import com.tle.core.jackson.ObjectMapperService;
import com.tle.core.settings.service.ThemeSettingsService;
import com.tle.web.api.newuitheme.NewUIThemeResource;
import com.tle.web.resources.PluginResourceHelper;
import com.tle.web.resources.ResourcesService;

/**
 * @author Samantha Fisher
 */

@Bind(NewUIThemeResource.class)
@Singleton
public class NewUIThemeResourceImpl implements NewUIThemeResource {
	private static final String LOGO_FILENAME = "newLogo.png";
	@Inject
	ThemeSettingsService themeSettingsService;
	@Inject
	private static PluginResourceHelper helper = ResourcesService.getResourceHelper(NewUIThemeResourceImpl.class);
	@Inject
	ObjectMapperService objectMapperService;

	@GET
	@Path("settings")
	@Produces("application/javascript")
	public Response retrieveThemeInfo() throws IOException {
		String themeString = objectMapperService.createObjectMapper().writeValueAsString(themeSettingsService.getTheme());
		return Response.ok("var themeSettings = " + themeString).build();
	}

	@PUT
	@Path("settings")
	public Response updateThemeInfo(NewUITheme theme) throws JsonProcessingException {
		themeSettingsService.setTheme(theme);
		return Response.accepted().build();
	}

	@PUT
	@Path("logo")
	public Response updateLogo(File logoFile) throws IOException {
		themeSettingsService.setLogo(logoFile);
		return Response.accepted().build();
	}

	@DELETE
	@Path("logo")
	public Response resetLogo() {
		themeSettingsService.deleteLogo();
		return Response.accepted().build();
	}

	@GET
	@Path("newLogo.png")
	@Produces("image/png")
	public Response retrieveLogo() throws IOException {
		if(themeSettingsService.isCustomLogo()){
			return Response.ok(themeSettingsService.getCustomLogo(), "image/png").build();
		}else{
			return Response.seeOther(URI.create(helper.instUrl(helper.url("images/new-equella-logo.png")))).build();
		}
	}

	@GET
	@Path("customlogo.js")
	@Produces("application/javascript")
	public Response retrieveCustomLogoURL(@Context UriInfo info) {
		String logoURL = info.getBaseUriBuilder().path(NewUIThemeResource.class).path(NewUIThemeResource.class, "retrieveLogo").build().toASCIIString();
		return Response.ok().entity("var logoURL = \"" + logoURL + "\";").build();
	}
}
