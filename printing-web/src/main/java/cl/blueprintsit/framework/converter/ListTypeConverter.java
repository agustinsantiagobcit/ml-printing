package cl.blueprintsit.framework.converter;

import cl.metlife.printing.beans.TemplateEditBean;
import cl.metlife.saam.domain.templates.ListType;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by BluePrints Developer on 24-05-2017.
 */
@FacesConverter("ListTypeConverter")
public class ListTypeConverter implements Converter {



    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s != null && s.trim().length() > 0) {
            try {

                ELContext elContext = facesContext.getELContext();
                TemplateEditBean bean = (TemplateEditBean) FacesContext.getCurrentInstance().getApplication() .getELResolver().getValue(elContext, null, "templateEditBean");
                Long listTypeId = Long.parseLong(s);
                for (ListType listType : bean.getListTypes()) {
                    if(listType.getId().equals(listTypeId))
                        return listType;
                }

            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Tipo Lista no valida"));
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o != null) {
            return String.valueOf(((ListType) o).getId());
        }
        else {
            return null;
        }
    }
}
