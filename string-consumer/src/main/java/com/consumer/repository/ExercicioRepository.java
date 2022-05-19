package com.consumer.repository;

import com.consumer.model.Exercicios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicios, Long> {

    Exercicios findByCodigo(String codigo);
}
