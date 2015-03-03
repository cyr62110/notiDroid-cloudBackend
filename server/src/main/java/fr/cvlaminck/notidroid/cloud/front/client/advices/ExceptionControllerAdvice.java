package fr.cvlaminck.notidroid.cloud.front.client.advices;

import fr.cvlaminck.notidroid.cloud.client.api.NotidroidClientAPI;
import fr.cvlaminck.notidroid.cloud.client.api.errors.ServerErrorResource;
import fr.cvlaminck.notidroid.cloud.config.runtime.DebugRuntimeConfiguration;
import fr.cvlaminck.notidroid.cloud.core.exceptions.NotidroidException;
import fr.cvlaminck.notidroid.cloud.core.exceptions.rest.InvalidResourceFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * ControllerAdvice providing exception handlers for all REST controllers.
 * Those exception handlers will help to add more information in the body
 * of exceptions.
 */
@ControllerAdvice(basePackages = "fr.cvlaminck.notidroid.cloud.front.api.controllers")
public class ExceptionControllerAdvice {

    @Autowired
    private DebugRuntimeConfiguration debugRuntimeConfiguration;

    /**
     * Build a standard server error resource that should be sent to the client.
     * A standard resource contains :
     * - information about the request
     * - information about the server
     * - a message coming from the exception that has been thrown
     *
     * @param request
     * @return
     */
    private ServerErrorResource buildServerErrorResource(NotidroidException ex, HttpServletRequest request) {
        final ServerErrorResource serverErrorResource = new ServerErrorResource();
        //Message coming from the exception and stack trace if configured in the runtime configuration
        serverErrorResource.setMessage(ex.getMessage());
        if (debugRuntimeConfiguration.outputFullStackTraceOnError()) {
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            ex.printStackTrace(new PrintStream(os));
            serverErrorResource.setStackTrace(os.toString());
        }
        //Information about the request
        serverErrorResource.setHttpMethod(request.getMethod());
        serverErrorResource.setEndpoint(request.getRequestURI());
        //Information about the server
        serverErrorResource.setApiVersion(NotidroidClientAPI.getAPIVersion());
        return serverErrorResource;
    }

    @ResponseBody
    @ExceptionHandler(value = {NotidroidException.class})
    public ServerErrorResource notidroidExceptionHandler(NotidroidException ex, HttpServletRequest request, HttpServletResponse response) {
        //We set the response status according to the one in the exception
        response.setStatus(ex.getHttpStatus().value());
        //Then we write the message
        final ServerErrorResource serverErrorResource = buildServerErrorResource(ex, request);
        return serverErrorResource;
    }

    @ResponseBody
    @ExceptionHandler(value = {InvalidResourceFormatException.class})
    public ServerErrorResource invalidResourceFormatException(InvalidResourceFormatException ex, HttpServletRequest request, HttpServletResponse response) {
        //We set the response status according to the one in the exception
        response.setStatus(ex.getHttpStatus().value());
        //Then we write the message
        final ServerErrorResource serverErrorResource = buildServerErrorResource(ex, request);
        //Since it is a quite common error we had additional information to help the dev to solve the issue in their implementation
        serverErrorResource.setDetailedCauses(ex.getConstraintViolations());
        return serverErrorResource;
    }

    @ExceptionHandler(value = {Exception.class})
    public void internalServerErrorExceptionHandler(Exception ex, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        //TODO : Log error that are coming here since they are unhandled.
        ex.printStackTrace();
    }


}
