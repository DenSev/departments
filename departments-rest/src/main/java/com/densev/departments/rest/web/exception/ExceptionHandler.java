package com.densev.departments.rest.web.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Component
public class ExceptionHandler extends Throwable implements ExceptionMapper<Throwable> {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);
    private static final long serialVersionUID = 1L;

    private final boolean returnStackTrace;

    ExceptionHandler() {
        this.returnStackTrace = true;
    }

    @Override
    public Response toResponse(Throwable exception) {
        LOG.error(exception.getMessage(), exception);
        return Response
            .status(ExceptionMappings.getStatus(exception))
            .entity(
                ExceptionWrapper.builder()
                    .statusCode(ExceptionMappings.getStatus(exception))
                    .errorMessage(returnStackTrace ? ExceptionUtils.getStackTrace(exception) : ExceptionUtils.getRootCauseMessage(exception))
                    .build()
            )
            .type(MediaType.APPLICATION_JSON)
            .build();
    }

}
