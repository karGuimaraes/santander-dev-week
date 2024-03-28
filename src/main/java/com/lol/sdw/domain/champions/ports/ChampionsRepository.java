package com.lol.sdw.domain.champions.ports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lol.sdw.domain.champions.model.Champions;


public interface ChampionsRepository extends JpaRepository<Champions, Long>{
    List<Champions> findByName(String name);
    List<Champions> findByRole(String role);
    List<Champions> findByNameAndRole(String name, String role);
}
