package com.letscode.starwarsresistance.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Location {
  private double latitude;
  private double longitude;
  private String galaxyName;
}
