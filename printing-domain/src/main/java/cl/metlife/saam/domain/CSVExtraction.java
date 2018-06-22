package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@DiscriminatorValue(ExtractionType.CSV)
@Table(name = "SAAM2_EXTRACCION_CSV")
@PrimaryKeyJoinColumn(name = "EXTRACCION_ID",referencedColumnName = "ID_EXTRACCION")
public class CSVExtraction extends Extraction implements Serializable {

    private String ruta;

    @Basic
    @Column(name = "RUTA", nullable = true)
    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }


}
