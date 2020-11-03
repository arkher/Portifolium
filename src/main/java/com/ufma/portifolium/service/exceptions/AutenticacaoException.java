package com.ufma.portifolium.service.exceptions;

@SuppressWarnings("serial")
public class AutenticacaoException extends RuntimeException {
    
    public AutenticacaoException(String msg){
        super(msg);
    }
}