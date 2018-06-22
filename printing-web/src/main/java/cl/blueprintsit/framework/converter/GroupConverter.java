package cl.blueprintsit.framework.converter;


import cl.blueprintsit.framework.domain.Group;
import cl.metlife.saam.web.controller.users.UserEditBean;

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
@FacesConverter("GroupConverter")
public class GroupConverter implements Converter {



    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s != null && s.trim().length() > 0) {
            try {

                ELContext elContext = facesContext.getELContext();
                UserEditBean bean = (UserEditBean) FacesContext.getCurrentInstance().getApplication() .getELResolver().getValue(elContext, null, "userEditBean");
                return bean.getGroupById(Long.parseLong(s));

            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Grupo no valida"));
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o != null) {
            return String.valueOf(((Group) o).getId());
        }
        else {
            return null;
        }
    }
}