package com.lol.sdw.adapters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lol.sdw.application.champions.ChampionsService;
import com.lol.sdw.domain.champions.dto.ChampionsResponseDTO;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Champions", description = "Champions League of Legends")
@RestController
@RequestMapping("/champions")
public class ChampionsController {
    @Autowired
    private ChampionsService championsService;

    @GetMapping
    public ResponseEntity<List<ChampionsResponseDTO>> getChampions(
        @RequestParam(name = "name", required = false) String name,
        @RequestParam(name = "role", required = false) String role
    ) {
        return ResponseEntity.ok(championsService.getAll(name, role));
    }
}
