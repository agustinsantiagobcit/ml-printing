package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue(StepType.STEP_TYPE_CSV_OUT)
@PrimaryKeyJoinColumn(name = "PASO_ID", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_CSV_OUT")
public class CSVOutStep extends Step implements Serializable {

    private List<CSVOutField> fields;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="CSV_OUT_ID" )
    public List<CSVOutField> getFields() {
        return fields;
    }

    public void setFields(List<CSVOutField> csvOutFieldList) {
        this.fields = csvOutFieldList;
    }
}
