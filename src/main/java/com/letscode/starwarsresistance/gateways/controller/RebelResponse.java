package com.letscode.starwarsresistance.gateways.controller;

import com.letscode.starwarsresistance.domains.entities.Inventory;
import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.domains.enums.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@NoArgsConstructor
public class RebelResponse {
    private Long id;
    private String name;
    private int age;
    private Gender gender;
    private Location location;
    private Inventory inventory;
    private boolean isTraitor;
    private int numberOfReportsAsTraitor;

    public RebelResponse(Rebel rebel) {
        this.id = rebel.getId();
        this.name = rebel.getName();
        this.age = rebel.getAge();
        this.gender = rebel.getGender();
        this.location = rebel.getLocation();
        this.inventory = rebel.getInventory();
        this.isTraitor = rebel.isTraitor();
        this.numberOfReportsAsTraitor = rebel.getNumberOfReportsAsTraitor();
    }
}
