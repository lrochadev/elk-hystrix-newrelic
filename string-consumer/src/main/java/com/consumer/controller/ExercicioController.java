package com.consumer.controller;

import com.consumer.model.Exercicios;
import com.consumer.service.ExercicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
@Tag(name = "Endpoints de consulta de exercícios.")
public class ExercicioController {

    @NonNull
    private final ExercicioService exercicioService;

    @Operation(summary = "Busca todos exercícios cadastrados.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"), @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping
    public ResponseEntity<List<Exercicios>> getExercicios() {
        return ResponseEntity.ok(exercicioService.findExercicios());
    }

    @Operation(summary = "Busca exercício por código.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok"), @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/{codigo}")
    public ResponseEntity<?> getExercicioByCodigo(@PathVariable("codigo") String codigo) {
        return ResponseEntity.ok(exercicioService.findExercicioByCodigo(codigo));
    }

    @Operation(summary = "Persiste o exercício.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created"), @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping
    public ResponseEntity<Exercicios> persistExercicio(@RequestBody Exercicios exercicio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exercicioService.saveExercicio(exercicio));
    }

    @Operation(summary = "Remove o exercício.")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No Content"), @ApiResponse(responseCode = "500", description = "Internal server error")})
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteExercicio(@PathVariable("codigo") String codigo) {
        exercicioService.deleteExercicio(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}