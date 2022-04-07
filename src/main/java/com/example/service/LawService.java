package com.example.service;

import com.example.controllers.dto.CreateLawOfObligationsDto;
import com.example.model.LawOfObligations;
import com.example.model.Side;
import com.example.utils.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class LawService {

    private final List<LawOfObligations> laws = new ArrayList<>();

    public LawService() {
        init();
    }

    private void init() {
        Thread thread = new Thread(() -> {
            try {
                laws.addAll(JsonUtil.parseCollectionJson("/data/laws.json", LawOfObligations.class));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        thread.start();
    }

    public Collection<LawOfObligations> list() {
        return laws;
    }

    public void add(CreateLawOfObligationsDto dto) {
        var law = LawOfObligations.builder()
                         .id(UUID.randomUUID())
                         .dateOfFinish(dto.getDateOfFinish())
                         .dateOfStart(dto.getDateOfStart())
                         .derivative(dto.getDerivative())
                         .law(dto.getLaw())
                         .risk(dto.getRisk())
                         .side(Side.builder()
                                 .id(UUID.randomUUID())
                                 .name(dto.getSideName())
                                 .build())
                         .build();
        laws.add(law);
    }

    public void save() {
        Thread thread = new Thread(() -> {
            try {
                JsonUtil.writeJson("/data/laws.json", laws);
                log.info(String.format("%d tracks saved", laws.size()));
            } catch (IOException e) {
                log.error("Couldn't save json!", e);
            }
        });
        thread.start();
    }

}
