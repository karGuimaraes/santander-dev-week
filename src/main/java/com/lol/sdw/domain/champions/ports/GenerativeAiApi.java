package com.lol.sdw.domain.champions.ports;

public interface GenerativeAiApi {
    String generateContent(String objective, String context);
}
