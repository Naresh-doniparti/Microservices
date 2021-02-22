package com.example;

public class NoRecordFoundException  extends  RuntimeException{

    public NoRecordFoundException(long id){
        super("No record found : "+ id);
    }

}
