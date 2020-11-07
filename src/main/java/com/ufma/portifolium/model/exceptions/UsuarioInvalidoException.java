package com.ufma.portifolium.model.exceptions;

@SuppressWarnings("serial")
public class UsuarioInvalidoException extends RuntimeException {
  public UsuarioInvalidoException(String msg){ super(msg); }
}