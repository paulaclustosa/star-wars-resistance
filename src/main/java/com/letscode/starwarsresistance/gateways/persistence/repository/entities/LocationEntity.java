package com.letscode.starwarsresistance.gateways.persistence.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LocationEntity {

  @Column ( name = "LATITUDE" )
  private double latitude;
  @Column ( name = "LONGITUDE" )
  private double longitude;
  @Column ( name = "GALAXY_NAME" )
  private String galaxyName;

}
