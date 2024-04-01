package com.lol.sdw.adapters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lol.sdw.application.champions.ChampionsService;
import com.lol.sdw.domain.champions.dto.AskChampionsRequestDTO;
import com.lol.sdw.domain.champions.dto.AskChampionsResponseDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<ChampionsResponseDTO> getChampionById(@PathVariable Long id) {
        return ResponseEntity.ok(championsService.getOne(id));
    }

    @PostMapping("/{id}/ask")
    public ResponseEntity<AskChampionsResponseDTO> askChampion(@PathVariable Long id, @RequestBody AskChampionsRequestDTO request) {
        String question = request.question();
        String answer = championsService.askChampion(id, question);
        return ResponseEntity.ok(new AskChampionsResponseDTO(answer));
    }
}