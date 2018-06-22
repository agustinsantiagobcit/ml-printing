package cl.metlife.saam.domain.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Objeto que almacena toda la data de un proceso en tiempo de ejecucion
 */
public class ProcessExecutionRuntime {

    private Map<String,Object> properties;

    private WorkingData workingData;

    private Map<String, List<String>> generatedFiles;

    public ProcessExecutionRuntime() {
        this.properties = new HashMap<String, Object>();
    }


    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }


    public WorkingData getWorkingData() {
        return workingData;
    }

    public void setWorkingData(WorkingData workingData) {
        this.workingData = workingData;
    }


    public Map<String, List<String>> getGeneratedFiles() {
        if(generatedFiles==null)
            generatedFiles = new HashMap<String, List<String>>();

        return generatedFiles;
    }

    public void setGeneratedFiles(Map<String, List<String>> generatedFiles) {
        this.generatedFiles = generatedFiles;
    }


}
