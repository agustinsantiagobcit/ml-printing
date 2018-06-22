package cl.metlife.saam.domain.runtime;

import java.util.ArrayList;
import java.util.List;

public class WorkingData {

    private List<ExtractionRow> rows;

    private List<String> generatedFiles;

    public WorkingData() {
        this.rows = new ArrayList<ExtractionRow>();
        this.generatedFiles = new ArrayList<String>();
    }

    public List<ExtractionRow> getRows() {
        return rows;
    }

    public void setRows(List<ExtractionRow> rows) {
        this.rows = rows;
    }

    public List<String> getGeneratedFiles() {
        return generatedFiles;
    }

    public void setGeneratedFiles(List<String> generatedFiles) {
        this.generatedFiles = generatedFiles;
    }
}
