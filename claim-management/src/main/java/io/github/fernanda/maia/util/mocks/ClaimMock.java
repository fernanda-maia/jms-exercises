package io.github.fernanda.maia.util.mocks;

import io.github.fernanda.maia.model.Claim;
import io.github.fernanda.maia.model.Doctor;
import io.github.fernanda.maia.model.InsuranceProvider;
import io.github.fernanda.maia.util.enums.DoctorType;

public class ClaimMock {
    public static Doctor createDoctor() {
        return Doctor
                .builder()
                .id(1L)
                .firstName("Herriot")
                .lastName("Doe")
                .email("email@email.com")
                .phoneNumber("999999999")
                .type(DoctorType.CARDIOLOGIST)
                .build();
    }

    public static InsuranceProvider createProvider() {
        return InsuranceProvider
                .builder()
                .id(1L)
                .providerName("Blue Cross")
                .type("Premium")
                .build();
    }

    public static Claim createClaim() {

        return Claim
                .builder()
                .hospitalId(1L)
                .doctor(createDoctor())
                .provider(createProvider())
                .claimAmount(1000.00)
                .build();
    }
}
