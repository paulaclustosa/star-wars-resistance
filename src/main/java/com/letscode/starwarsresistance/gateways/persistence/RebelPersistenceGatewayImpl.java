package com.letscode.starwarsresistance.gateways.persistence;

import com.letscode.starwarsresistance.domains.entities.Rebel;

import java.util.*;

public class RebelPersistenceGatewayImpl implements RebelPersistenceGateway {

  private static final Map<Long, Rebel> rebels = new HashMap<>();
  private static Long rebelId = 1L;

  @Override
  public Rebel save(Rebel rebel) {
    if (rebel.getId() == null) rebel.setId(rebelId++);
    rebels.put(rebel.getId(), rebel);
    return rebel;
  }

  @Override
  public void delete(Rebel rebel) {
    rebels.remove(rebel.getId());
  }

  @Override
  public boolean existsById(Long id) {
    return rebels.containsKey(id);
  }

  @Override
  public Optional<Rebel> findById(Long id) {
    return Optional.ofNullable(rebels.get(id));
  }

  @Override
  public List<Rebel> findAll() {
    return new ArrayList<>(rebels.values());
  }

}
