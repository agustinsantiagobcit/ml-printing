package cl.blueprintsit.framework.fileexplorer;

import cl.metlife.saam.web.controller.BaseBean;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BluePrints Developer on 02-12-2016.
 */

@ManagedBean(name = "fileExplorerBean")
@ViewScoped
public class FileExplorerBean extends BaseBean{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileExplorerBean.class);

    private String path="/";
    private String tailPath="/";


    public List<File> getPathFiles(){

        File f = new File(path);

        ArrayList<File> files = new ArrayList<File>();

        if(!f.exists()){
            showError("Error","Ruta no existe");
            return files;
        }

        if(!f.isDirectory()) {
            showError("Error", "Ruta no es un directorio");
            return files;
        }

        for (File file : f.listFiles()) {
            files.add(file);
        }
        return files;

    }

    public StreamedContent downloadFile(File file){
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        FileInputStream fis =null;
        try {
             fis = new FileInputStream(file);

            String contentType = URLConnection.guessContentTypeFromName(file.getName());
            return new DefaultStreamedContent(fis, contentType, file.getName());
        } catch (FileNotFoundException e) {
            showError("Error","Archivo no enconrtado");

        }

        return new DefaultStreamedContent();
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
