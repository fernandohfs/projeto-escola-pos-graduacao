package br.com.unifacef.escola.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Materia implements Serializable {

    private static final long serialVersionUID = 891089797351818733L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonBackReference(value = "materia-professor")
    private Professor professor;
    @ManyToMany
    @JoinTable(
            name = "materia_curso",
            joinColumns = @JoinColumn(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    @JsonBackReference(value = "curso-materias")
    private List<Curso> cursos;
    private char situacao;
    @CreationTimestamp
    private Date dataCriacao;
    @UpdateTimestamp
    private Date dataAtualizacao;

    public void update(Materia materiaUpdate) {
        setTitulo(materiaUpdate.getTitulo());
        setDescricao(materiaUpdate.getDescricao());
        setProfessor(materiaUpdate.getProfessor());
        setSituacao(materiaUpdate.getSituacao());
    }

}
