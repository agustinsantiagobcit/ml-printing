package cl.metlife.printing.beans;

import cl.metlife.saam.domain.templates.TemplateElement;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="clipboardBean")
@SessionScoped
public class ClipboardBean {

    private TemplateElement elementClipboard;

    private List<TemplateElement> elementList;

    @PostConstruct
    public void init(){
        elementList = new ArrayList<TemplateElement>();
    }

    public void copyElement(TemplateElement elementoPlantilla){
        elementClipboard = elementoPlantilla.copy();
        addElementToList(elementoPlantilla.copy());
    }

    public void addElementToList(TemplateElement elementoPlantilla){
        if(elementList.size()==5){
            elementList.remove(0);
        }
        elementList.add(elementoPlantilla);
    }


    public TemplateElement getElementClipboard() {
        return elementClipboard;
    }

    public void setElementClipboard(TemplateElement elementClipboard) {
        this.elementClipboard = elementClipboard;
    }

    public List<TemplateElement> getElementList() {
        return elementList;
    }

    public void setElementList(List<TemplateElement> elementList) {
        this.elementList = elementList;
    }
}
