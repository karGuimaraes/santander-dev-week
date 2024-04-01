package com.lol.sdw.application.champions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lol.sdw.domain.champions.dto.ChampionsResponseDTO;
import com.lol.sdw.domain.champions.model.Champions;
import com.lol.sdw.domain.champions.ports.ChampionsRepository;

@Service
public class ChampionsService {
    @Autowired
    private ChampionsRepository championsRepository;

    public List<ChampionsResponseDTO> getAll(String name, String role) {
        List<ChampionsResponseDTO> champions;
        if (name != null && role != null) {
            champions = championsRepository.findByNameAndRole(name, role).stream().map(ChampionsResponseDTO::new).toList();
        } else if (name != null) {
            champions = championsRepository.findByName(name).stream().map(ChampionsResponseDTO::new).toList();
        } else if (role != null) {
            champions = championsRepository.findByRole(role).stream().map(ChampionsResponseDTO::new).toList();
        } else {
            champions = championsRepository.findAll().stream().map(ChampionsResponseDTO::new).toList();
        }
        return champions;
    }

    public ChampionsResponseDTO getOne(Long id) {
        Champions champion = championsRepository.findById(id)
                                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Champion not found"));
        return new ChampionsResponseDTO(champion);
    }

    public String askChampion(Long id, String question) {
        Champions champion = championsRepository.findById(id)
                                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Champion not found"));
        String championContext = champion.generateContextByQuestion(question);

        return championContext;
    }
}
