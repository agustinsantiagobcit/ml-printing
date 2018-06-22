package cl.metlife.saam.domain.runtime;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;


@XmlRootElement(name = "Data")
public class ExtractionRow {



    private Map<String,Object> data;

    private Map<Long,WorkingData> subextractions;


    public ExtractionRow() {
        this.data = new HashMap<String,Object>();
        this.subextractions = new HashMap<Long, WorkingData>();
    }

    public ExtractionRow(Map<String, Object> data) {
        this.data = data;
        this.subextractions = new HashMap<Long, WorkingData>();
    }



    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }


    public Map<Long, WorkingData> getSubextractions() {
        return subextractions;
    }

    public void setSubextractions(Map<Long, WorkingData> subextractions) {
        this.subextractions = subextractions;
    }

    @Override
    public String toString() {
        return "data: " + data.toString();
    }
}
