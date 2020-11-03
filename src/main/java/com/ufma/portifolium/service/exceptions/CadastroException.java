package com.ufma.portifolium.service.exceptions;

@SuppressWarnings("serial")
public class CadastroException extends RuntimeException{
    
    public CadastroException(String msg){
        super(msg);
    }
}
