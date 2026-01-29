package top.blogapi.exception.global_exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import top.blogapi.exception.BaseException;
import top.blogapi.exception.response_exception.ErrorResponse;
import top.blogapi.exception.system_exception.SystemException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(BaseException ex, ServletWebRequest rq) {
        HttpServletRequest request = rq.getRequest();

        if (ex instanceof SystemException)
            log.error("🚨 SYSTEM ERROR [{}] {} - Path: {}",
                    ex.getErrorCode(), ex.getMessage(), request.getRequestURI(), ex);
        else
            log.warn("⚠️ CLIENT ERROR [{}] {} - Path: {}",
                    ex.getErrorCode(), ex.getMessage(), request.getRequestURI());


        if (ex.getContext() != null && !ex.getContext().isEmpty()) {
            log.debug("Exception Context: {}", ex.getContext());
        }
        ErrorResponse error = ErrorResponse.builder()
                .code(ex.getHttpStatus().value())
                .errorCode(ex.getErrorCode())
                .path(request.getRequestURI())
                .httpMethod(request.getMethod())
                .timestamp(LocalDateTime.now())
                .msg(ex.getMessage())

                .build();
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }
}
