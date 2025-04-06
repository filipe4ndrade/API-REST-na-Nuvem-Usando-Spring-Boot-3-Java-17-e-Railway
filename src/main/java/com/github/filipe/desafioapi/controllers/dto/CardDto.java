package com.github.filipe.desafioapi.controllers.dto;

import com.github.filipe.desafioapi.entities.Card;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CardDto(
        Long id,
        @NotBlank(message = "Number cannot be blank")
        String number,

        @NotNull(message = "limit cannot be null")
        BigDecimal limit) {

    public CardDto(Card model) {
        this(model.getId(), model.getNumber(), model.getLimit());
    }

    public Card toModel() {
        Card model = new Card();
        model.setId(this.id);
        model.setNumber(this.number);
        model.setLimit(this.limit);
        return model;
    }
}
