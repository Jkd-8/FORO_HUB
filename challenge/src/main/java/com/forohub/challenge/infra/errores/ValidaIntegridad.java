package com.forohub.challenge.infra.errores;

public class ValidaIntegridad  extends RuntimeException{
    public ValidaIntegridad(String s){
        super(s);
    }
}
