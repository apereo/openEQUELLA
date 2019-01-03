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

package com.tle.web.api.oauth.interfaces;

import com.tle.web.api.interfaces.BaseEntityResource;
import com.tle.web.api.interfaces.beans.PagingBean;
import com.tle.web.api.interfaces.beans.security.BaseEntitySecurityBean;
import com.tle.web.api.oauth.interfaces.beans.OAuthClientBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Produces({"application/json"})
@Path("oauth/")
@Api(value = "OAuth clients", description = "oauth")
public interface OAuthResource extends BaseEntityResource<OAuthClientBean, BaseEntitySecurityBean>
{
	@GET
	@Path("/acl")
	@ApiOperation(value = "List global oauth client acls")
	public BaseEntitySecurityBean getAcls(@Context UriInfo uriInfo);

	@PUT
	@Path("/acl")
	@ApiOperation(value = "Edit global oauth client acls")
	public Response editAcls(@Context UriInfo uriInfo, BaseEntitySecurityBean security);

	@GET
	@ApiOperation(value = "List all oauth clients")
	public PagingBean<OAuthClientBean> list(
			@Context UriInfo uriInfo,
			@ApiParam("Search name and description") @QueryParam("q") String q,
			@ApiParam("Privilege(s) to filter by") @QueryParam("privilege") List<String> privilege,
			@QueryParam("resumption") @ApiParam("Resumption token for paging") String resumptionToken,
			@QueryParam("length") @ApiParam("Number of results") @DefaultValue("10") int length,
			@QueryParam("full") @ApiParam("Return full entity (needs VIEW or EDIT privilege)") boolean full);


	@GET
	@Path("/{uuid}")
	@ApiOperation(value = "Get an oauth client")
	public OAuthClientBean get(@Context UriInfo uriInfo, @PathParam("uuid") String uuid);

	@DELETE
	@Path("/{uuid}")
	@ApiOperation("Delete an oauth client")
	public Response delete(@Context UriInfo uriInfo, @PathParam("uuid") String uuid);

	@POST
	@ApiOperation("Create a new oauth client")
	public Response create(@Context UriInfo uriInfo, @ApiParam OAuthClientBean bean,
		@ApiParam(required = false) @QueryParam("file") String stagingUuid);

	@PUT
	@Path("/{uuid}")
	@ApiOperation(value = "Edit an oauth client")
	public Response edit(@Context UriInfo uriInfo, @PathParam("uuid") String uuid, @ApiParam OAuthClientBean bean,
		@ApiParam(required = false, value = "Staging area UUID") @QueryParam("file") String stagingUuid,
		@ApiParam(required = false) @QueryParam("lock") String lockId,
		@ApiParam(required = false) @QueryParam("keeplocked") boolean keepLocked);

	@GET
	@Path("/{uuid}/lock")
	@ApiOperation("Read the lock for an oauth client")
	public Response getLock(@Context UriInfo uriInfo, @PathParam("uuid") String uuid);

	@POST
	@Path("/{uuid}/lock")
	@ApiOperation("Lock an oauth client")
	public Response lock(@Context UriInfo uriInfo, @PathParam("uuid") String uuid);

	@DELETE
	@Path("/{uuid}/lock")
	@ApiOperation("Unlock an oauth client")
	public Response unlock(@Context UriInfo uriInfo, @PathParam("uuid") String uuid);
}
