package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(StepType.STEP_TYPE_SHARED_FOLDER_OUT)
@PrimaryKeyJoinColumn(name = "PASO_ID", referencedColumnName = "ID_PASO")
@Table(name = "SAAM2_PASO_FTP_OUT")
public class SharedFolderStep extends Step implements Serializable{
    private String user;
    private String pass;
    private String route;
    private String host;
    private String port;

    @Basic
    @JoinColumn(name = "USUARIO")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @JoinColumn(name = "CONTRASENA")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @JoinColumn(name = "RUTA")
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Basic
    @JoinColumn(name = "HOST")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Basic
    @JoinColumn(name = "PUERTO")
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
