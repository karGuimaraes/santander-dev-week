package com.lol.sdw.domain.champions.model;

import com.lol.sdw.domain.champions.dto.ChampionsRequestDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "champions")
@Table(name = "champions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Champions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private String lore;
    private String imageUrl;

    public Champions(ChampionsRequestDTO data) {
        this.name = data.name();
        this.role = data.role();
        this.lore = data.lore();
        this.imageUrl = data.imageUrl();
    }

    public String generateContextByQuestion(String question) {
        return String.format("""
            Question: %s
            Name of the champion: %s
            Role of the champion: %s
            Lore of the champion: %s
            """, question, this.getName(), this.getRole(), this.getLore());
    }
}
