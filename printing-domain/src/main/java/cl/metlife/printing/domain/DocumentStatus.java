package cl.metlife.printing.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "PRINTING_ESTADOS_DOC")
public class DocumentStatus extends BaseEntity implements Serializable {

    static public final Long CREADO              = 1L;
    static public final Long REGISTRADO_EN_OD    = 2L;
    static public final Long ENVIADO_A_IMPRESION = 3L;
    static public final Long FORMATO_INVALIDO = 4L;


    private Long id;
    private String name;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
