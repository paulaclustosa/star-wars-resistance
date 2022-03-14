package com.letscode.starwarsresistance.gateways.persistence.repository.entities;

import com.letscode.starwarsresistance.domains.entities.Inventory;
import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.domains.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class RebelEntity {

  @Id @GeneratedValue
  private Long id;

  @Column( name = "NAME" )
  private String name;

  @Column( name = "AGE" )
  private int age;

  @Column ( name = "GENDER" )
  private Gender gender;

  @Embedded
  private LocationEntity location;

  @Embedded
  private InventoryEntity inventory;

  @Column( name = "IS_TRAITOR" )
  private boolean isTraitor = false;

  @Column( name = "N_REP_AS_TRAITOR" )
  private int numberOfReportsAsTraitor = 0;

  public RebelEntity(Rebel rebel) {
    BeanUtils.copyProperties(rebel, this);
  }

  public Rebel toRebel() {
    Rebel rebel = new Rebel();
    BeanUtils.copyProperties(this, rebel);
    return rebel;
  }

}
