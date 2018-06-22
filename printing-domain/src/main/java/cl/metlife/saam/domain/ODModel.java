package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_MODELO_OD")
public class ODModel extends BaseEntity implements Serializable {
    private Long id;
    private String name;
    private String applicationGroup;
    private List<ODField> fields;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MODELO_OD", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "APPLICATION_GROUP", nullable = true, length = 255)
    public String getApplicationGroup() {
        return applicationGroup;
    }

    public void setApplicationGroup(String applicationGroup) {
        this.applicationGroup = applicationGroup;
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
    @JoinColumn(name = "MODELO_OD_ID")
    public List<ODField> getFields() {
        return fields;
    }

    public void setFields(List<ODField> fields) {
        this.fields = fields;
    }

}
