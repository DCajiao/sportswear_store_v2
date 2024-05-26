package com.apiweb.backend.Exception;

public class TallaNoEncontradaException extends RuntimeException{
    public TallaNoEncontradaException(String mensaje){
        super(mensaje);
    }
}
