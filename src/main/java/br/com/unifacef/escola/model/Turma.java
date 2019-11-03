package br.com.unifacef.escola.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataPeriodoInicio;
    private LocalDate dataPeriodoFim;
    private String observacao;
    private Character situacao;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonBackReference(value = "turmaReference")
    private Curso curso;
    @ManyToMany
    @JoinTable(
            name = "aluno_turma",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos;
    @CreationTimestamp
    private LocalDateTime dataCriacao;
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;



    public void update(Turma turmaUpdate) {
        setDataPeriodoInicio(turmaUpdate.getDataPeriodoInicio());
        setDataPeriodoFim(turmaUpdate.getDataPeriodoFim());
        setObservacao(turmaUpdate.getObservacao());
        setSituacao(turmaUpdate.getSituacao());
    }

}
