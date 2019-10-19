package br.com.unifacef.escola.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor implements Serializable {

    private static final long serialVersionUID = 4694847122323713499L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private String cpf;
    private Date dataAdmissao;
    private Date dataDemissao;
    @OneToMany(mappedBy = "professor", targetEntity = Materia.class,  fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Materia> materias = new ArrayList<>();
    @CreationTimestamp
    private Date dataCriacao;
    @UpdateTimestamp
    private Date dataAtualizacao;

    public void update(Professor professorUpdate) {
        setNome(professorUpdate.getNome());
        setEmail(professorUpdate.getEmail());
        setDataNascimento(professorUpdate.getDataNascimento());
        setCpf(professorUpdate.getCpf());
        setDataAdmissao(professorUpdate.getDataAdmissao());
        setDataDemissao(professorUpdate.getDataDemissao());
    }
}
