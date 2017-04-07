package com.tle.web.api.users.interfaces;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.tle.web.api.interfaces.beans.SearchBean;
import com.tle.web.api.users.interfaces.beans.RoleBean;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

// Note: EQUELLA overrides the root Path
@Produces(MediaType.APPLICATION_JSON)
@Path("usermanagement/local/role/")
@Api(value = "/usermanagement/local/role", description = "usermanagement-local-role")
@SuppressWarnings("nls")
public interface RoleResource
{
	static final UriBuilder GETROLE = UriBuilder.fromResource(RoleResource.class).path(RoleResource.class, "getRole");
	static final String ACTIVITY_OBJECT_ROLE = "role";

	@GET
	@Path("/{uuid}")
	@ApiOperation("Retrieve a role")
	public RoleBean getRole(@Context UriInfo uriInfo, @PathParam("uuid") String uuid);

	@PUT
	@Path("/{uuid}")
	@ApiOperation("Edit a role")
	public Response editRole(@PathParam("uuid") String uuid, @ApiParam RoleBean role);

	@DELETE
	@Path("/{uuid}")
	@ApiOperation("Delete a role")
	public Response deleteRole(@PathParam("uuid") String uuid);

	@POST
	@Path("/")
	@ApiOperation("Add a role")
	public Response addRole(@ApiParam RoleBean role);

	@GET
	@Path("/name/{name}")
	@ApiOperation("Retrieve a role by name")
	public RoleBean getRoleByName(@Context UriInfo uriInfo, @PathParam("name") String name);

	@GET
	@Path("/")
	@ApiOperation("List internal roles")
	public SearchBean<RoleBean> list(@Context UriInfo uriInfo, @ApiParam(required = false) @QueryParam("q") String query);
}
