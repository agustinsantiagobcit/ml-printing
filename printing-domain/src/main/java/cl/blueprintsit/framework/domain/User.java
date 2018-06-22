package cl.blueprintsit.framework.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by BluePrints Developer on 22-05-2017.
 */
@Entity
@Table(name = "SAAM2_USUARIO")
public class User  implements Serializable {


    private String username;
    private String name;
    private String email;
    private String rut;
    private Date creationDate;
    private Date lastLogin;
    private Date lastPasswordChange;
    private Boolean locked;

    private String passSecret;
    private byte[] passSecretEncrypted;

    private Integer failedLoginAttempts;

    private List<Group> groups;


    @Id
    @Column(name = "USERNAME", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "ULTIMO_INGRESO")
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Column(name = "RUT")
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Column(name = "CREADO")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "BLOQUEADO")
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean blocked) {
        this.locked = blocked;
    }

    @Column(name = "PASS_MESSAGE")
    public String getPassSecret() {
        return passSecret;
    }

    public void setPassSecret(String passSecret) {
        this.passSecret = passSecret;
    }

    @Column(name = "PASS_MESSAGE_ENC")
    public byte[] getPassSecretEncrypted() {
        return passSecretEncrypted;
    }

    public void setPassSecretEncrypted(byte[] passSecretEncrypted) {
        this.passSecretEncrypted = passSecretEncrypted;
    }

    @Column(name = "INGRESOS_FALLIDOS")
    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttemts) {
        this.failedLoginAttempts = failedLoginAttemts;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "SAAM2_USUARIOS_GRUPOS",
    joinColumns = @JoinColumn(name = "USERNAME",referencedColumnName = "USERNAME"),
    inverseJoinColumns = @JoinColumn(name = "GRUPO_ID",referencedColumnName = "ID"))
    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Column(name = "ULTIMO_CAMBIO_PASS")
    public Date getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Date lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }
}