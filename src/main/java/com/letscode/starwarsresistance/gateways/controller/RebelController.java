package com.letscode.starwarsresistance.gateways.controller;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.usecases.CreateRebel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rebels")
@RequiredArgsConstructor
public class RebelController {

  private final Logger log = LoggerFactory.getLogger(RebelController.class.getName());
  private final RebelMapper rebelMapper;
  private final CreateRebel createRebel;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public Rebel create(@RequestBody RebelRequest rebelRequest) {
    log.info("Creating rebel: {}", rebelRequest);
    Rebel rebel = rebelMapper.toRebel(rebelRequest);
    log.info("Creating rebel: {}", rebel);
    Rebel rebelSaved = createRebel.execute(rebel);
    log.info("Creating rebel: {}", rebelSaved);
    return rebelSaved;
  }

}
