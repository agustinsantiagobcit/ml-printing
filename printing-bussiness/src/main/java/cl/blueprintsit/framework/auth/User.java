package cl.blueprintsit.framework.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by BluePrints Developer on 19-05-2016.
 */
public class User {

    private String username;
    private String name;
    private String email;
    private Date lastLogin;

    private List<Group> groups;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Group> getGroups() {
        if(groups==null)
            groups= new ArrayList<Group>();
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
