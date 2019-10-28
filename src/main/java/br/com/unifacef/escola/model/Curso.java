package br.com.unifacef.escola.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String titulo;
  private String descricao;
  private Character situacao;
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