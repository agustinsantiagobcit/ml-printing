package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO_PASO_ID",discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "SAAM2_PASO")
public abstract class Step extends BaseEntity implements Serializable{

    private Long id;
    private Long processId;
    private Long stepTypeId;
    private Integer orden;
    private StepType type;
    private Boolean enabled;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PASO", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROCESO_ID", nullable = true, precision = 0)
    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long procesoId) {
        this.processId = procesoId;
    }

    @Basic
    @Column(name = "TIPO_PASO_ID", nullable = true, precision = 0)
    public Long getStepTypeId() {
        return stepTypeId;
    }

    public void setStepTypeId(Long tipoPasoId) {
        this.stepTypeId = tipoPasoId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TIPO_PASO_ID")
    public StepType getType() {
        return type;
    }

    public void setType(StepType stepType) {
        this.type = stepType;
    }

    @Basic
    @Column(name = "ORDEN", nullable = true, precision = 0)
    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Basic
    @Column(name = "ACTIVO")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    @Transient
    public boolean isExtractionType(){
        return StepType.STEP_TYPE_EXTRACTION.equals(this.getStepTypeId().toString());
    }


}
