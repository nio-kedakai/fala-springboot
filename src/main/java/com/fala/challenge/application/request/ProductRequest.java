package com.fala.challenge.application.request;

import java.util.List;

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

    @JsonProperty("other_images")
    private List<String> otherImages;


}
