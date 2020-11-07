package com.ufma.portifolium.model.exceptions;

@SuppressWarnings("serial")
public class AlunoInvalidoException extends RuntimeException {
  public AlunoInvalidoException(String msg){ super(msg); }
}