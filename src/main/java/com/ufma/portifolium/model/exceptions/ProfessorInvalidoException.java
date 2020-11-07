package com.ufma.portifolium.model.exceptions;

@SuppressWarnings("serial")
public class ProfessorInvalidoException extends RuntimeException {
  public ProfessorInvalidoException(String msg){ super(msg); }
}