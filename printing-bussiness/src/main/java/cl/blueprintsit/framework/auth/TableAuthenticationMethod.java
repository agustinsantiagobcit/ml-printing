package cl.blueprintsit.framework.auth;

import cl.metlife.printing.persistence.dao.UserDAO;
import cl.blueprintsit.utils.encryption.Encryption;
import cl.blueprintsit.utils.encryption.EncryptionException;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Created by BluePrints Developer on 18-05-2016.
 */
@Stateless
public class TableAuthenticationMethod extends AuthenticationMethod {

    public static final String NOMBRE_DE_USUARIO_Y_O_CONTRASENA_INCORRECTA = "Nombre de usuario y/o Contraseña incorrecta";

    @EJB
    UserDAO userDAO;

    public void authenticate(String username, String password) throws AuthenticationException {

        cl.blueprintsit.framework.domain.User bdUser = userDAO.getByUsername(username);

        //si usuario no existe en BD
        if(bdUser==null)
            throw new AuthenticationException(NOMBRE_DE_USUARIO_Y_O_CONTRASENA_INCORRECTA);

        byte[] encripted = bdUser.getPassSecretEncrypted();
        String message = bdUser.getPassSecret();

        //El extraño caso de que el usuario no tiene credenciales en la BD
        if(encripted==null || message==null)
            throw new AuthenticationException("Credenciales Invalidas");

        //validacion de clave
        if(!message.equals(decrypt(encripted, password)))
            throw new AuthenticationException(NOMBRE_DE_USUARIO_Y_O_CONTRASENA_INCORRECTA);

        //Usuario bloqueado
        if(bdUser.getLocked())
            throw new AuthenticationException("Usuario bloqueado, contacte al administrador");


    }

    private String decrypt(byte[] strEncrypted,String strKey) throws AuthenticationException {

        try {
            return Encryption.decrypt(strEncrypted,strKey);

        }catch (EncryptionException e){
            throw new AuthenticationException(NOMBRE_DE_USUARIO_Y_O_CONTRASENA_INCORRECTA);
        }

    }

}
