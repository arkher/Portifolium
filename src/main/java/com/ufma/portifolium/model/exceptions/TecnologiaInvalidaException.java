package com.ufma.portifolium.model.exceptions;

@SuppressWarnings("serial")
public class TecnologiaInvalidaException extends RuntimeException {
  public TecnologiaInvalidaException(String msg) { super(msg); }
}