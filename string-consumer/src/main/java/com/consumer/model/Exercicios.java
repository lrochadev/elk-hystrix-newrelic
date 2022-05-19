package com.consumer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "EXERCICIOS")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Exercicios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "EXER_ID")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EXER_COD", length = 4)
    private String codigo;

    @Column(name = "EXER_ELAB_PPA")
    private Boolean elaboracaoPpa = false;

    @Column(name = "EXER_ELAB_LDO")
    private Boolean elaboracaoLdo = false;

    @Column(name = "EXER_ELAB_LOA")
    private Boolean elaboracaoLoa = false;

    @Column(name = "EXER_EXEC_PPA")
    private Boolean execucaoPpa = false;

    @Column(name = "EXER_EXEC_MOV")
    private Boolean execucaoMov = false;

    @Column(name = "EXER_EXEC_ADEQUACAO_METAS")
    private Boolean adequacaoMetas = false;

    @Column(name = "EXER_ADM")
    private Boolean administracao = false;

    @Column(name = "EXER_REL")
    private Boolean relatorio = false;

    @Column(name = "EXER_EXEC_ADEQ_METAS_INICIO")
    private LocalDate dataInicio;

    @Column(name = "EXER_EXEC_ADEQ_METAS_FIM")
    private LocalDate dataFim;

    @Column(name = "EXER_SANC_PPA")
    private Boolean sancionado = false;

    @Column(name = "EXER_ALT_TABELA_BASE")
    private Boolean alterarTabelaBase = false;
}