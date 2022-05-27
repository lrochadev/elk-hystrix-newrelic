package com.consumer.service;

import com.consumer.exceptions.ExercicioNotFoundException;
import com.consumer.model.Exercicios;
import com.consumer.repository.ExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public List<Exercicios> findAll() {
        return exercicioRepository.findAll();
    }

    public Exercicios findByCodigo(final String codigo) {
        return Optional.ofNullable(exercicioRepository.findByCodigo(codigo)).orElseThrow(ExercicioNotFoundException::new);
    }

    @Transactional
    public Exercicios save(final Exercicios exercicio) {
        return exercicioRepository.save(exercicio);
    }

    @Transactional
    public void delete(final String codigo) {
        this.findByCodigo(codigo);
        exercicioRepository.deleteByCodigo(codigo);
    }
}
