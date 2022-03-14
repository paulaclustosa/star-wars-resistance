package com.letscode.starwarsresistance.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {

  FEMALE("F"),
  MALE("M");

  private final String genderRef;

  public static Gender fromGenderRef(String genderRef) {
    for (Gender gender : Gender.values()) {
      if (gender.genderRef.equalsIgnoreCase(genderRef)) return gender;
    }
    return null;
  }

}
