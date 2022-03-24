package com.letscode.starwarsresistance.gateways.controller;

import com.letscode.starwarsresistance.domains.entities.Location;
import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.gateways.persistence.RebelPersistenceGateway;
import com.letscode.starwarsresistance.usecases.CreateRebel;
import com.letscode.starwarsresistance.usecases.UpdateRebelLocation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping(value = "/rebels")
@RequiredArgsConstructor
public class RebelController {

  private final Logger log = LoggerFactory.getLogger(RebelController.class.getName());
  private final RebelMapper rebelMapper;
  private final CreateRebel createRebel;
  private final LocationMapper locationMapper;
  private final UpdateRebelLocation updateRebelLocation;
  private final RebelPersistenceGateway rebelPersistenceGateway;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public RebelResponse create(@RequestBody RebelRequest rebelRequest) {
    log.info("Creating rebel (rebelRequest): {}", rebelRequest);
    Rebel rebel = rebelMapper.toRebel(rebelRequest);
    log.info("Creating rebel (rebel Domain): {}", rebel);
    Rebel rebelSaved = createRebel.execute(rebel);
    log.info("Creating rebel (rebelResponse): {}", rebelSaved);
    return new RebelResponse(rebelSaved);
  }

  @PatchMapping(path = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public RebelResponse updateRebelLocation(
      @PathVariable("id") Long id,
      @RequestBody LocationRequest locationRequest) {
    log.info("Updating location: {}", locationRequest);
    Location location = locationMapper.toLocation(locationRequest);
    log.info("Updating rebel: {}", id);
    Rebel rebel = rebelPersistenceGateway.findById(id).get();
    log.info("Updating rebel: {}", rebel);
    Rebel rebelSaved = updateRebelLocation.execute(rebel, location);
    return new RebelResponse(rebelSaved);
  }
}
