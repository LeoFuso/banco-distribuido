package com.leofuso.academico.cd.bancodistribuido.application.facade;

import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.DepositoCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.SaqueCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.commands.TransferenciaCommand;
import com.leofuso.academico.cd.bancodistribuido.application.communication.resources.ContaResource;

import java.util.List;

public interface ContaFacade {

    void deposito(Integer id, DepositoCommand command);

    void saque(Integer id, SaqueCommand command);

    void transferencia(Integer id, TransferenciaCommand command);

    List<ContaResource> findAll();

    ContaResource findOneById(Integer id);

}
