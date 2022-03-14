package com.letscode.starwarsresistance.gateways.persistence.repository;

import com.letscode.starwarsresistance.gateways.persistence.repository.entities.RebelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RebelH2Repository extends JpaRepository<RebelEntity, Long> {
}
