package com.leofuso.academico.cd.bancod.api.application.services;

import com.leofuso.academico.cd.bancod.api.application.exceptions.ComponentNotFoundException;
import com.leofuso.academico.cd.bancod.api.domain.Conta;
import com.leofuso.academico.cd.bancod.api.domain.events.NovaTransferencia;
import com.leofuso.academico.cd.bancod.api.domain.events.NovoDeposito;
import com.leofuso.academico.cd.bancod.api.domain.events.NovoSaque;
import com.leofuso.academico.cd.bancod.api.domain.interfaces.ContaRepository;
import com.leofuso.academico.cd.bancod.api.domain.interfaces.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository repository;

    @Autowired
    public ContaServiceImpl(ContaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void deposito(NovoDeposito event) {
        final Conta conta = event.getConta();
        final Double valor = event.getValor();
        conta.deposito(valor);
        repository.saveAndFlush(conta);
    }

    @Override
    @Transactional
    public void saque(NovoSaque event) {
        final Conta conta = event.getConta();
        final Double valor = event.getValor();
        conta.saque(valor);
        repository.saveAndFlush(conta);
    }

    @Override
    @Transactional
    public void transferencia(NovaTransferencia event) {
        final Conta conta = event.getConta();
        final Conta contaDestino = event.getContaDestino();
        final Double valor = event.getValor();
        conta.transferencia(contaDestino, valor);
        repository.saveAndFlush(conta);
        repository.saveAndFlush(contaDestino);
    }

    @Override
    @Transactional(readOnly = true)
    public Conta findOneById(Integer contaId) {
        return repository.findById(contaId)
                .orElseThrow(() -> new ComponentNotFoundException(contaId, Conta.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conta> findAll() {
        return repository.findAll();
    }

}
