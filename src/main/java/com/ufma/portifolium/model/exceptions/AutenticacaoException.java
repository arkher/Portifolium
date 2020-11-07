package com.ufma.portifolium.model.exceptions;

@SuppressWarnings("serial")
public class AutenticacaoException extends RuntimeException {
    public AutenticacaoException(String msg){
        super(msg);
    }
}