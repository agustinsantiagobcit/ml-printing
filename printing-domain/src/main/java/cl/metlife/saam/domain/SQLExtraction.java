package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(ExtractionType.SQL)
@Table(name = "SAAM2_EXTRACCION_SQL")
@PrimaryKeyJoinColumn(name = "EXTRACCION_ID",referencedColumnName = "ID_EXTRACCION")
public class SQLExtraction extends Extraction implements Serializable {

    private String datasource;
    private String query;

    @Basic
    @Column(name = "FUENTE_DATOS", nullable = true)
    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }


    @Basic
    @Column(name = "CONSULTA", nullable = true, length = 1000)
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
