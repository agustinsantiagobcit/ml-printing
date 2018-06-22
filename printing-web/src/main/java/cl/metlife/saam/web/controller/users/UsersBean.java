package cl.metlife.saam.web.controller.users;

import cl.blueprintsit.framework.auth.AuthorizationManager;
import cl.blueprintsit.framework.auth.UserManager;
import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.blueprintsit.framework.domain.User;
import cl.metlife.saam.web.controller.BaseBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Blueprints on 1/27/2016.
 */
@ManagedBean(name="usersBean")
@ViewScoped
public class UsersBean extends BaseBean {


    private User selectedItem;

    @EJB
    private ConfigurationManager manager;

    @EJB
    private UserManager userManager;

    @EJB
    private AuthorizationManager authorizationManager;


    public List<User> getAllUsers(){
        return userManager.findAll();
    }

    public User getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(User selectedItem) {
        this.selectedItem = selectedItem;
    }



}
