package com.lol.sdw.domain.champions.dto;

import com.lol.sdw.domain.champions.model.Champions;

public record ChampionsResponseDTO(
    Long id,
    String name,
    String role,
    String lore,
    String imageUrl
) {
    public ChampionsResponseDTO(Champions champion) {
        this(champion.getId(), champion.getName(), champion.getRole(), champion.getLore(), champion.getImageUrl());
    }
}
