package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_TIPO_PASO_RECUP_ARCHIVO")
public class FileFetchType extends BaseEntity implements Serializable {

    public static final Long STEP_WITH_MAPPING = 1L;
    public static final Long STEP_WITHOUT_MAPPING = 2L;

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PASO_RECUP_ARCHIVO", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
