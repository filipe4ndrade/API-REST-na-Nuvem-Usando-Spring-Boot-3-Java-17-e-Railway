package com.github.filipe.desafioapi.controllers.dto;


import com.github.filipe.desafioapi.entities.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record UserDto(
        Long id,

        @NotBlank(message = "Name cannot be blank")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotNull(message = "Account cannot be null")
        @Valid
        AccountDto account,

        @NotNull(message = "Card cannot be null")
        @Valid
        CardDto card,

        @Valid
        List<FeatureDto> features,

        @Valid
        List<NewsDto> news) {

    public UserDto(User model) {
        this(
                model.getId(),
                model.getName(),
                new AccountDto(model.getAccount()),
                new CardDto(model.getCard()),
                model.getFeatures().stream().map(FeatureDto::new).toList(),
                model.getNews().stream().map(NewsDto::new).toList()
        );
    }

    public User toModel() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setAccount(this.account.toModel());
        user.setCard(this.card.toModel());
        user.setFeatures(this.features.stream().map(FeatureDto::toModel).toList());
        user.setNews(this.news.stream().map(NewsDto::toModel).toList());
        return user;
    }
}

