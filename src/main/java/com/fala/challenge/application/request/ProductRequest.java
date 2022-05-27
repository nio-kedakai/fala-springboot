package com.fala.challenge.application.request;

import com.fala.challenge.domain.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest extends Product {

    private static final long serialVersionUID = 1L;

    @JsonProperty("principal_image")
    private String principalImage;

}
