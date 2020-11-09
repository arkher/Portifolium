package com.ufma.portifolium.model.exceptions;

@SuppressWarnings("serial")
public class ProjetoInvalidoException extends RuntimeException {
  public ProjetoInvalidoException(String msg){ super(msg); }  
}