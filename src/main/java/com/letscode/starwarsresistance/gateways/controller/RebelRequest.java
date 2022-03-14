package com.letscode.starwarsresistance.gateways.controller;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
public class RebelRequest {

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Rebel's name.",
      dataType = "String",
      example = "Maria"
  )
  private String name;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Rebel's age.",
      dataType = "int",
      example = "18")
  private int age;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Rebel's gender.",
      dataType = "String",
      example = "F" )
  private String gender;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Rebel's location (latitude).",
      dataType = "double",
      example = "10.0")
  private double latitude;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Rebel's location (longitude).",
      dataType = "double",
      example = "15.0")
  private double longitude;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Rebel's location (galaxy's name).",
      dataType = "String",
      example = "Via Láctea")
  private String galaxyName;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Amount of weapon in its inventory.",
      dataType = "int",
      example = "0")
  private int weaponAmount;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Amount of ammunition in its inventory.",
      dataType = "int",
      example = "4")
  private  int ammunitionAmount;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Amount of water in its inventory.",
      dataType = "int",
      example = "5")
  int waterAmount;

  @NotBlank
  @ApiModelProperty(
      required = true,
      value = "Amount of food in its inventory.",
      dataType = "int",
      example = "0")
  int foodAmount;

}
