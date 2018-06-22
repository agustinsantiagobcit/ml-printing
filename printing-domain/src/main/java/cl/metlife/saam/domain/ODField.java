package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_CAMPO_OD")
public class ODField extends BaseEntity implements Serializable {
    private Long id;
    private Long modelId;
    private ODFieldType type;
    private Long typeId;
    private String name;
    private Integer size;
    private Integer precision;
    private Boolean index;
    private Boolean subreporte;
    private ODModel model;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPO_OD", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MODELO_OD_ID", nullable = true, precision = 0)
    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }


    @Basic
    @Column(name = "TIPO_CAMPO_OD_ID", nullable = true, precision = 0)
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TIPO_CAMPO_OD_ID")
    public ODFieldType getType() {
        return type;
    }

    public void setType(ODFieldType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TAMANO")
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "PRECISION", nullable = true, precision = 0)
    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    @Basic
    @Column(name = "INDICE", nullable = true, precision = 0)
    public Boolean getIndex() {
        return index;
    }

    public void setIndex(Boolean indice) {
        this.index = indice;
    }

    @Basic
    @Column(name = "SUBREPORTE", nullable = true, length = 255)
    public Boolean getSubreporte() {
        return subreporte;
    }

    public void setSubreporte(Boolean subreporte) {
        this.subreporte = subreporte;
    }

    @ManyToOne
    @JoinColumn(name = "MODELO_OD_ID")
    public ODModel getModel() {
        return model;
    }

    public void setModel(ODModel model) {
        this.model = model;
    }
}
