package cl.blueprintsit.framework.auth;

import java.io.Serializable;

/**
 * Created by BluePrints Developer on 14-09-2016.
 */
public class Group implements Serializable{

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
