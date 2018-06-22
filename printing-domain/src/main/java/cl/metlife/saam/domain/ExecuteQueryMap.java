package cl.metlife.saam.domain;

import javax.persistence.*;

@Entity
@Table(name = "SAAM2_MAPEO_EJECUCION_QUERY")
public class ExecuteQueryMap {

    Long id;
    Long executeQueryId;
    Long extractionFieldId;
    Long queryParametersId;

    ExtractionField extractionField;
    QueryParameter queryParameter;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EJECUCION_QUERY_ID", nullable = true, precision = 0)
    public Long getExecuteQueryId() {
        return executeQueryId;
    }

    public void setExecuteQueryId(Long executeQueryId) {
        this.executeQueryId = executeQueryId;
    }

    @Basic
    @Column(name = "CAMPO_EXTRACCION", nullable = true, precision = 0)
    public Long getExtractionFieldId() {
        return extractionFieldId;
    }

    public void setExtractionFieldId(Long extractionFieldId) {
        this.extractionFieldId = extractionFieldId;
    }

    @Basic
    @Column(name = "QUERY_PARAMETRO", nullable = true, precision = 0)
    public Long getQueryParametersId() {
        return queryParametersId;
    }

    public void setQueryParametersId(Long queryParametersId) {
        this.queryParametersId = queryParametersId;
    }

    @ManyToOne
    @JoinColumn(name = "CAMPO_EXTRACCION")
    public ExtractionField getExtractionField() {
        return extractionField;
    }

    public void setExtractionField(ExtractionField extractionField) {
        this.extractionField = extractionField;
    }

    @ManyToOne
    @JoinColumn(name = "QUERY_PARAMETRO")
    public QueryParameter getQueryParameter() {
        return queryParameter;
    }

    public void setQueryParameter(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
    }
}
