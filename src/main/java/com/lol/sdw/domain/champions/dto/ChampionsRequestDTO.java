package com.lol.sdw.domain.champions.dto;

public record ChampionsRequestDTO(
    String name,
    String role,
    String lore,
    String imageUrl
) {
} 
