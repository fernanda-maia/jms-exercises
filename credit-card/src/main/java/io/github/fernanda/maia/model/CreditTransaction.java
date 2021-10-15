package io.github.fernanda.maia.model;

import io.github.fernanda.maia.util.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditTransaction implements Serializable {

    private Long id;
    private Long client_id;
    private TransactionType type;
    private Double amount;

    @Override
    public String toString() {
        return "Type:" + type.getDescription() +
                " Amount: U$: " + amount;
    }
}
