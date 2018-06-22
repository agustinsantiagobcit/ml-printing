package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_PROCESO")
public class Process extends BaseEntity implements Serializable {
    private Long id;
    private String name;
    private String description;
    private List<Step> steps;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROCESO", nullable = false, precision = 0)
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

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("orden ASC")
    @JoinColumn(name = "PROCESO_ID")
    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

}
