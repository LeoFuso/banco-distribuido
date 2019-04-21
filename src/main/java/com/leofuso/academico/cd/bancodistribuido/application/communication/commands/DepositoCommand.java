package com.leofuso.academico.cd.bancodistribuido.application.communication.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@CommandApplicationEvent
public class DepositoCommand implements OperacaoBancariaCommand {

    @NotNull
    private final Integer contaId;

    @NotNull
    private final Double valor;

    private DepositoCommand(@NotNull Integer contaId,
                            @NotNull Double valor) {
        this.contaId = contaId;
        this.valor = valor;
    }

    public static DepositoCommand create(@JsonProperty("conta_id") Integer contaId,
                                         @JsonProperty("valor") Double valor) {
        return new DepositoCommand(contaId, valor);
    }
}
