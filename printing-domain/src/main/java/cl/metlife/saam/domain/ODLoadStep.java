package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(StepType.STEP_TYPE_ODLOAD)
@PrimaryKeyJoinColumn(name = "ID_PASO", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_CARGA")
public class ODLoadStep extends Step implements Serializable{

    private Long ODModelId;
    private ODModel ODModel;
    private String xmlJasperTemplate;
    private List<ODMapping> mappings;

    @Basic
    @Column(name = "MODELO_OD_ID")
    public Long getODModelId() {
        return ODModelId;
    }

    public void setODModelId(Long modeloOdId) {
        this.ODModelId = modeloOdId;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MODELO_OD_ID")
    public ODModel getODModel() {
        return ODModel;
    }

    public void setODModel(ODModel ODModel) {
        this.ODModel = ODModel;
    }

    @Basic
    @Column(name = "PLANTILLA_XML_JASPER")
    public String getXmlJasperTemplate() {
        return xmlJasperTemplate;
    }

    public void setXmlJasperTemplate(String xmlJasperTemplate) {
        this.xmlJasperTemplate = xmlJasperTemplate;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PASO_ID")
    public List<ODMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<ODMapping> mappings) {
        this.mappings = mappings;
    }


}
