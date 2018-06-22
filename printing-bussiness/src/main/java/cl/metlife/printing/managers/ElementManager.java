package cl.metlife.printing.managers;


import cl.metlife.saam.domain.templates.ElementType;
import cl.metlife.saam.domain.templates.ListItem;
import cl.metlife.saam.domain.templates.ListType;
import cl.metlife.saam.domain.templates.TemplateElement;
import cl.metlife.printing.persistence.dao.ElementListDAO;
import cl.metlife.printing.persistence.dao.ElementTypeDAO;
import cl.metlife.printing.persistence.dao.ListTypeDAO;
import cl.metlife.printing.persistence.dao.TemplateElementDAO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ElementManager {

    @EJB
    TemplateElementDAO templateElementDAO;

    @EJB
    ElementTypeDAO elementTypeDAO;

    @EJB
    ElementListDAO elementListDAO;

    @EJB
    ListTypeDAO listTypeDAO;


    public List<ElementType> getAllElementTypes(){
        return elementTypeDAO.getAllElementTypes();
    }

    public TemplateElement create(TemplateElement elementoPlantilla){
        return templateElementDAO.create(elementoPlantilla);
    }

    public TemplateElement update(TemplateElement elementoPlantilla){
        return templateElementDAO.update(elementoPlantilla);
    }

    public boolean delete(TemplateElement elementoPlantilla){
        return templateElementDAO.delete(elementoPlantilla);
    }

    public void createElementsList(ListItem elementosLista){
        elementListDAO.createElementsList(elementosLista);
    }

    public List<ListType> getAllListTypes(){
        return listTypeDAO.getAllListTypes();
    }
}
