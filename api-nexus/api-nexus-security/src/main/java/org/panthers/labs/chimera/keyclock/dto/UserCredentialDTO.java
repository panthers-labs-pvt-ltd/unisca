package org.panthers.labs.chimera.keyclock.dto;

import lombok.Data;

@Data
public class UserCredentialDTO {
  private String type;
  private String value;
}
