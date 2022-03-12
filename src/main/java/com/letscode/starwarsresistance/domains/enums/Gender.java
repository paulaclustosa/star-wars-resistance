package com.letscode.starwarsresistance.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
  FEMALE("F"),
  MALE("M");

  private String id;

  Gender fromId(String id) {
    for (Gender at : Gender.values()) {
      if (at.getId().equals(id)) return at;
    }
    return null;
  }
}
