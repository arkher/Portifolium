package com.ufma.portifolium.exceptionHandler;

import com.ufma.portifolium.model.exceptions.AlunoInvalidoException;
import com.ufma.portifolium.model.exceptions.AutenticacaoException;
import com.ufma.portifolium.model.exceptions.ProfessorInvalidoException;
import com.ufma.portifolium.model.exceptions.UsuarioInvalidoException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AlunoInvalidoException.class)
  protected ResponseEntity<Object> handleAlunoInvalido(AlunoInvalidoException ex) {
    ApiError apiError = buildApiError(HttpStatus.BAD_REQUEST, ex);
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(ProfessorInvalidoException.class)
  protected ResponseEntity<Object> handleProfessorInvalido(ProfessorInvalidoException ex) {
    ApiError apiError = buildApiError(HttpStatus.BAD_REQUEST, ex);
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(AutenticacaoException.class)
  protected ResponseEntity<Object> handleErroAutenticacao(AutenticacaoException ex) {
    ApiError apiError = buildApiError(HttpStatus.BAD_REQUEST, ex);
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(UsuarioInvalidoException.class)
  protected ResponseEntity<Object> handleUsuarioInvalido(UsuarioInvalidoException ex) {
    ApiError apiError = buildApiError(HttpStatus.BAD_REQUEST, ex);
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  private ApiError buildApiError(HttpStatus status, Exception ex){
    ApiError apiError = new ApiError(status);
    apiError.setMessage(ex.getMessage());
    apiError.setDebugMessage(ex.getLocalizedMessage());
    return apiError;
  }

}