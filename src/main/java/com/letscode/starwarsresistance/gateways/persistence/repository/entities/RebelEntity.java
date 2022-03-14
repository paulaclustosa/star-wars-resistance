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
  private String gender;

  @Embedded
  private LocationEntity location;

  @Embedded
  private InventoryEntity inventory;

  @Column( name = "IS_TRAITOR" )
  private boolean isTraitor = false;

  @Column( name = "N_REP_AS_TRAITOR" )
  private int numberOfReportsAsTraitor = 0;

  public RebelEntity(Rebel rebel) {
    this.name = rebel.getName();
    this.age = rebel.getAge();
    this.gender = rebel.getGender().getGenderRef();
    this.location = new LocationEntity(rebel.getLocation().getLatitude(),
        rebel.getLocation().getLongitude(),
        rebel.getLocation().getGalaxyName());
    this.inventory = new InventoryEntity(rebel.getInventory().getWeaponAmount(),
        rebel.getInventory().getAmmunitionAmount(),
        rebel.getInventory().getWaterAmount(),
        rebel.getInventory().getFoodAmount());
  }

  public Rebel toRebel() {
    Rebel rebel = Rebel.builder()
        .name(name)
        .age(age)
        .gender(Gender.fromGenderRef(gender))
        .location(new Location(location.getLatitude(),
            location.getLongitude(),
            location.getGalaxyName()))
        .inventory(new Inventory(inventory.getWeaponAmount(),
            inventory.getAmmunitionAmount(),
            inventory.getWaterAmount(),
            inventory.getFoodAmount()))
        .build();

    rebel.setId(id);
    rebel.setTraitor(isTraitor);
    rebel.setNumberOfReportsAsTraitor(numberOfReportsAsTraitor);

    return rebel;
  }

}
