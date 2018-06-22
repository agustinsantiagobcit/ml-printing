package cl.metlife.printing.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "PRINTING_TIPOS_IMPRESION")
public class PrintType extends BaseEntity implements Serializable {
    private Long id;
    private String name;

    static public final Long PRINT_TYPE_ORIGINAL = 1L;
    static public final Long PRINT_TYPE_REIMPRESION_MANUAL = 2L;

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
