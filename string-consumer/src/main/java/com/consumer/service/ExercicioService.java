package com.consumer.service;

import com.consumer.model.Exercicios;
import com.consumer.repository.ExercicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public List<Exercicios> findExercicios() {
        return exercicioRepository.findAll();
    }

    public Exercicios findExercicioByCodigo(final String codigo) {
        return exercicioRepository.findByCodigo(codigo);
    }

    public Exercicios saveExercicio(final Exercicios exercicio) {
        return exercicioRepository.save(exercicio);
    }
}
