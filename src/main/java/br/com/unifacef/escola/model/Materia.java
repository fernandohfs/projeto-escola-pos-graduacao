package br.com.unifacef.escola.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.util.Date;

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
    private char situacao;
    @CreationTimestamp
    private Date dataCriacao;
    @UpdateTimestamp
    private Date dataAtualizacao;

    public void update(Materia materiaUpdate) {
        setTitulo(materiaUpdate.getTitulo());
        setDescricao(materiaUpdate.getDescricao());
        setSituacao(materiaUpdate.getSituacao());
    }

}
