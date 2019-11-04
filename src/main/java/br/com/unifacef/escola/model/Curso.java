package br.com.unifacef.escola.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Curso implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String titulo;
  private String descricao;
  @ManyToMany
  @JoinTable(
          name = "materia_curso",
          joinColumns = @JoinColumn(name = "curso_id"),
          inverseJoinColumns = @JoinColumn(name = "materia_id"))
  private List<Materia> materias;
  private Character situacao;
  @OneToMany(mappedBy = "curso", targetEntity = Turma.class,  fetch = FetchType.LAZY)
  @JsonManagedReference(value = "turmaReference")
  private List<Turma> turmas = new ArrayList<>();
  @CreationTimestamp
  private LocalDateTime dataCriacao;
  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;

  public void update(Curso cursoUpdate) {
    setTitulo(cursoUpdate.getTitulo());
    setDescricao(cursoUpdate.getDescricao());
    setSituacao(cursoUpdate.getSituacao());
  }
}