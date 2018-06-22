package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_PLANTILLA_ALTIUZ")
public class AltiuzTemplate extends BaseEntity implements Serializable {
    private Long id;
    private String name;
    private List<AltiuzField> fields;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLANTILLA_ALTIUZ", nullable = false, precision = 0)
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLANTILLA_ALTIUZ_ID")
    public List<AltiuzField> getFields() {
        return fields;
    }

    public void setFields(List<AltiuzField> fields) {
        this.fields = fields;
    }
}
