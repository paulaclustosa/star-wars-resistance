package com.letscode.starwarsresistance.gateways.controller;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationRequest {
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
}
