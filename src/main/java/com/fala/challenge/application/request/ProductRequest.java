package com.fala.challenge.application.request;

import com.fala.challenge.domain.model.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest extends Product {

    @JsonProperty("principal_image")
    private String principalImage;

}
