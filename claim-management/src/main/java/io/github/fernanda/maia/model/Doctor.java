package io.github.fernanda.maia.model;

import io.github.fernanda.maia.util.enums.DoctorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private DoctorType type;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
