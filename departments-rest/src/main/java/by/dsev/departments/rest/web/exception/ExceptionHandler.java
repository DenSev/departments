package by.dsev.departments.rest.web.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Component
public class ExceptionHandler extends Throwable implements ExceptionMapper<Throwable> {

    private static final long serialVersionUID = 1L;

    private final boolean returnStackTrace;

    ExceptionHandler() {
        this.returnStackTrace = false;
    }

    @Override
    public Response toResponse(Throwable exception) {
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
