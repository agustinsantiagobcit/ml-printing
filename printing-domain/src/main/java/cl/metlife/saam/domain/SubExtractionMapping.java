package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_MAPEO_SUB_EXTRACCION")
public class SubExtractionMapping extends BaseEntity implements Serializable {

    private Long id;
    private Long subExtractionId;
    private Long parameterId;
    private Long fieldId;
    private SubExtraction subExtraction;
    private ExtractionField field;
    private QueryParameter parameter;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAPEO_SUB_EXTRACCION", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SUB_EXTRACCION_ID", nullable = true, precision = 0)
    public Long getSubExtractionId() {
        return subExtractionId;
    }

    public void setSubExtractionId(Long subExtraccionId) {
        this.subExtractionId = subExtraccionId;
    }

    @Basic
    @Column(name = "PARAMETRO_CONSULTA_ID", nullable = true, precision = 0)
    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parametroConsultaId) {
        this.parameterId = parametroConsultaId;
    }

    @Basic
    @Column(name = "CAMPO_EXTRACCION_ID", nullable = true, precision = 0)
    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long campoExtraccionId) {
        this.fieldId = campoExtraccionId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "SUB_EXTRACCION_ID", referencedColumnName = "ID_SUB_EXTRACCION")
    public SubExtraction getSubExtraction() {
        return subExtraction;
    }

    public void setSubExtraction(SubExtraction subExtraction) {
        this.subExtraction = subExtraction;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION_ID")
    public ExtractionField getField() {
        return field;
    }

    public void setField(ExtractionField field) {
        this.field = field;
    }

    @ManyToOne
    @JoinColumn(name = "PARAMETRO_CONSULTA_ID")
    public QueryParameter getParameter() {
        return parameter;
    }

    public void setParameter(QueryParameter parameter) {
        this.parameter = parameter;
    }
}
