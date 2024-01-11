package com.felipebdn.gestao_vagas.exceptions;

public class EmailNotFoundException extends RuntimeException {

  public EmailNotFoundException(String err){
    super(err);
  }
}
