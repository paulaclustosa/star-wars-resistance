package com.letscode.starwarsresistance.gateways.controller;

import com.letscode.starwarsresistance.domains.entities.Rebel;
import com.letscode.starwarsresistance.usecases.CreateRebel;
import com.letscode.starwarsresistance.usecases.ReportRebelAsTraitor;
import com.letscode.starwarsresistance.usecases.TradeItem;
import com.letscode.starwarsresistance.usecases.UpdateRebelLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rebels")
@RequiredArgsConstructor
public class RebelController {

  private final RebelMapper rebelMapper;
  private final CreateRebel createRebel;
  private final ReportRebelAsTraitor reportRebelAsTraitor;
  private final TradeItem tradeItem;
  private final UpdateRebelLocation updateRebelLocation;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public Rebel create(@RequestBody RebelRequest rebelRequest) {
    Rebel rebel = rebelMapper.toRebel(rebelRequest);
    Rebel rebelSaved = createRebel.execute(rebel);
    return rebelSaved;
  }

}
