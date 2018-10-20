package edu.palevobot.rest.response;

import javax.ws.rs.core.Response;

public class ResponseCreator {

    public static Response error(int status, int errorCode, String version){
        Response.ResponseBuilder response = Response.status(status);
        response.header("version", version);
        response.header("errorCode", errorCode);
        response.entity("none");
        return response.build();
    }

    public static Response success(String version, Object obj){
        Response.ResponseBuilder response = Response.ok();
        response.header("version", version);
        if(obj != null){
            response.entity(obj);
        }
        else
            response.entity("none");
        return response.build();
    }
}
