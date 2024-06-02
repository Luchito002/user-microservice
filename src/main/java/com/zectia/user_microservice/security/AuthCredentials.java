package com.zectia.user_microservice.security;

import lombok.Data;

@Data
public class AuthCredentials {
  private String correo;
  private String clave;

  public String getCorreo() {
    return correo;
  }

  public String getClave() {
    return clave;
  }

}
