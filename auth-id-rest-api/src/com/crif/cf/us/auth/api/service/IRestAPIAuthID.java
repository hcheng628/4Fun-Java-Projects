package com.crif.cf.us.auth.api.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/REST/AUTHID")
public interface IRestAPIAuthID {
	@Path("/AUTHDOC")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String authDocument(String authDocumentRequest);
	
	@Path("/MATCHSELFIE")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String matchSelfie(String matchSelfieRequest);
	
	@Path("/GETTRANSINFO")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getTransInformation(@QueryParam("transactionID") String transactionID);
	
	@Path("/TEST")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String test();
	
	// Non Web Services
	public String filterAuthDocResponse(String authDocResponse) throws Exception;
	public String filterMatchSelfieResponse(String matchSelfieResponse) throws Exception;
	public String filterGetTransInfoResponse(String getTransInfoResponse) throws Exception;
}