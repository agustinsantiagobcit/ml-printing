package cl.metlife.saam.web.controller.users;

import cl.blueprintsit.framework.auth.*;
import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.blueprintsit.framework.domain.Group;
import cl.blueprintsit.framework.domain.User;
import cl.blueprintsit.utils.Rut;
import cl.metlife.saam.web.controller.BaseBean;
import org.primefaces.model.DualListModel;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blueprints on 1/27/2016.
 */
@ManagedBean(name="userEditBean")
@ViewScoped
public class UserEditBean extends BaseBean {

    public static final String PLACEHOLDER_PASS_NOT_CHANGED = "@@PLACEHOLDER_PASS_NOT_CHANGED@@";
    public static final String USUARIO_NO_ENCONTRADO = "Usuario no encontrado";
    private User editItem;
    private String username;
    private boolean nuevo;
    private DualListModel<Group> groups;
    private boolean inited = false;

    private boolean returnToProfile = false;

    private String password = PLACEHOLDER_PASS_NOT_CHANGED;

    @EJB
    private ConfigurationManager manager;

    @EJB
    private UserManager userManager;

    @EJB
    private AuthorizationManager authorizationManager;

    public void initForLogedUser(cl.blueprintsit.framework.auth.User user){
        this.username = user.getUsername();
        returnToProfile=true;

    }
    public void init(){
        if(inited)
            return;

        inited = true;
        if(username==null) {
            newUser();
        }else {
            editItem=userManager.getByUsername(username);
        }

        List<Group> allGroups = userManager.getAllGroups();

        for (Group group : editItem.getGroups()) {
            for (Group allGroup : allGroups) {
                if(allGroup.getId()==group.getId()) {
                    allGroups.remove(allGroup);
                    break;
                }
            }
        }



        groups = new DualListModel<Group>(allGroups,editItem.getGroups());

    }

    private void newUser() {
        String initialPassValue = "";
        password=initialPassValue;

        nuevo = true;
        editItem = new User();
        editItem.setGroups(new ArrayList<Group>());
    }


    public void formatRut() {

        if(!editItem.getRut().trim().isEmpty()) {
            editItem.setRut(Rut.formatRut(editItem.getRut()));
        }

    }


    public User getEditItem() {
        return editItem;
    }

    public void setEditItem(User editItem) {
        this.editItem = editItem;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Group getGroupById(Long id) {
        return userManager.getGroupById(id);
    }



    public DualListModel<Group> getGroups() {
        return groups;
    }

    public void setGroups(DualListModel<Group> groups) {
        this.groups = groups;
    }

    public void unlockUser(){
        try {
            authorizationManager.unlockUser(this.editItem.getUsername());
        } catch (UserNotFoundException e) {
            showError("Error al desbloquear",USUARIO_NO_ENCONTRADO);
        }
    }
    public void lockUser(){
        try {
            authorizationManager.lockUser(this.editItem.getUsername());
        } catch (UserNotFoundException e) {
            showError("Error al bloquear",USUARIO_NO_ENCONTRADO);
        }
    }


    public String delete(){
        if(!userManager.delete(this.editItem)){
            showError("Error al borrar", USUARIO_NO_ENCONTRADO);
            return null;
        }
        return "users";
    }

    public String save(){

        editItem.setGroups(getGroups().getTarget());

        if(nuevo){
            try {
                userManager.create(editItem);
            } catch (UsernameExistsException e) {
                showError("Error",e.getMessage());
                return null;
            }
        }
        else {
            userManager.update(editItem);

        }

        if(!password.equals(PLACEHOLDER_PASS_NOT_CHANGED)) {
            try {
                authorizationManager.setPassword(this.editItem.getUsername(), password);
            } catch (UserNotFoundException e) {
                showWarn("Password no guardado",e.getMessage());
            } catch (PasswordChangeException e) {
                showWarn("Password no guardado",e.getMessage());
            }
        }

        showInfo("Exito","Cambios guardados");

        if(returnToProfile)
            return "userProfile";
        return "users";
    }

}
