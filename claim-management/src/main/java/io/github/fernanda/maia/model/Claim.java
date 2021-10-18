package io.github.fernanda.maia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Claim implements Serializable {

    private Long hospitalId;
    private Doctor doctor;
    private InsuranceProvider provider;
    private Double claimAmount;

    @Override
    public String toString() {
        return "Claim{" +
                "hospitalId=" + hospitalId +
                ", doctor=" + doctor.getFullName() +
                ", provider=" + provider.getProviderName() +
                ", claimAmount=" + claimAmount +
                '}';
    }
}
