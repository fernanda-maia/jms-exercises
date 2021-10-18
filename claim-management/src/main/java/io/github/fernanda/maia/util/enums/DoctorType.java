package io.github.fernanda.maia.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DoctorType {

    CARDIOLOGIST("Cardiologist"),
    NEUROLOGIST("Neurologist"),
    PSYCHOLOGIST("Psychologist");

    private final String description;
}
