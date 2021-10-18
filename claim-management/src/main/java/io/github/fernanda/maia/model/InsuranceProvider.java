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
public class InsuranceProvider implements Serializable {

    private Long id;
    private String providerName;
    private String type;
}
