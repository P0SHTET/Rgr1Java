package com.example.controllers.dto;

import com.example.model.Side;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@Builder

public class CreateLawOfObligationsDto {
    String law;
    String derivative;
    LocalDate dateOfStart;
    LocalDate dateOfFinish;
    String sideName;
    Double risk;
}
