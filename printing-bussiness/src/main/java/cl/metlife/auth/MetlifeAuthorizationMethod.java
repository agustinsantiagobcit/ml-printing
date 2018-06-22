package cl.metlife.auth;

import cl.blueprintsit.framework.auth.*;
import cl.metlife.ws.clients.auth.MetlifeAuthenticationServiceManager;
import cl.metlife.ws.clients.auth.ROLES_PERSONA_RUT_APLICResponseROLES_PERSONA_RUT_APLICResult;
import cl.metlife.ws.clients.auth.Sp_info_personaResponseSp_info_personaResult;
import org.apache.axis.message.MessageElement;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BluePrints Developer on 22-03-2017.
 */
@Stateless
public class MetlifeAuthorizationMethod extends AuthorizationMethod {

    @EJB
    MetlifeAuthenticationServiceManager service;


    @Override
    public User getUser(String username) throws UserNotFoundException {

        Sp_info_personaResponseSp_info_personaResult result = null;
        try {
            result = service.getServiceInstance().sp_info_persona(username);
        } catch (RemoteException e) {
            throw new MetlifeAuthotizationException("Error de conexion con servicio de autenticacion de Metlife",e);
        }

        User user = makeUserFromResponse(username, result);

        List<Group> usersGroups = getUsersPermissions(username);
        user.setGroups(usersGroups);

        return user;
    }


    private List<Group> getUsersPermissions(String username){

        String idApp = "INT";
        String rutStr = username.replace("-","").replace(".","");
        ROLES_PERSONA_RUT_APLICResponseROLES_PERSONA_RUT_APLICResult result;

        List<Group> groups = new ArrayList<Group>();
        try {
            result = service.getServiceInstance().ROLES_PERSONA_RUT_APLIC(idApp, rutStr);

            for (String s : getGroupsFromResponse(result)) {
                Group group = new Group();
                group.setDescription(s);
                group.setName(s);
                groups.add(group);
            }
        }catch (Exception e){
            throw new MetlifeAuthotizationException("Error leyendo grupos de servicio de autenticacion de Metlife",e);
        }

        return groups;
    }

    private List<String> getGroupsFromResponse(ROLES_PERSONA_RUT_APLICResponseROLES_PERSONA_RUT_APLICResult result) throws Exception {
        MessageElement[] msgContent = result.get_any();
        String xmlStr = null;

        xmlStr = msgContent[0].getAsString();

        return xpathListExtractor(xmlStr, "/NewDataSet/Table/ROL_CODIGO/text()");
    }

    private User makeUserFromResponse(String username, Sp_info_personaResponseSp_info_personaResult result){

        MessageElement[] elements = result.get_any();
        String xmlStr = null;
        try {
            xmlStr = elements[1].getAsString();
        } catch (Exception e) {
            throw new MetlifeAuthotizationException("Error de leyendo respuesta de servicio de autenticacion de Metlife",e);
        }

        try {
            String nombre = xpathExtractor(xmlStr, "//NOMBRES/text()");
            String apaterno = xpathExtractor(xmlStr, "//APPAT/text()");
            String amaterno = xpathExtractor(xmlStr, "//APMAT/text()");
            String email = xpathExtractor(xmlStr, "//CORREOELECTRONICO/text()");

        User user = new User();
        user.setName(nombre + " " + apaterno + " " +  amaterno);
        user.setEmail(email);
        user.setUsername(username);
        return user;
        }catch (Exception e){
            throw new MetlifeAuthotizationException("Error de leyendo respuesta de servicio de autenticacion de Metlife",e);
        }
    }


    @Override
    public void lockUser(String username) throws UserNotFoundException {
        //no soportado para ml
    }

    @Override
    public void unlockUser(String username) throws UserNotFoundException {
        //no soportado para ml
    }

    @Override
    public void markLogin(String username) throws UserNotFoundException {
        //no soportado para ml

    }


    @Override
    public void setPassword(String username, String password) throws UserNotFoundException, PasswordChangeException {
        throw new PasswordChangeException("No se puede cambiar password");
    }


    private String xpathExtractor(String xml, String xpathStr) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();
            InputStream xmlIs = new ByteArrayInputStream(xml.getBytes());


            Document doc = builder.parse(xmlIs);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile(xpathStr);
            return (String) expr.evaluate(doc, XPathConstants.STRING);

    }

    private List<String> xpathListExtractor(String xml, String xpathStr) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;

            builder = factory.newDocumentBuilder();
            InputStream xmlIs = new ByteArrayInputStream(xml.getBytes());

            Document doc = builder.parse(xmlIs);
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile(xpathStr);

            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < nodes.getLength(); i++) {
                list.add(nodes.item(i).getNodeValue());
            }

            return list;

        }

    public class MetlifeAuthotizationException extends RuntimeException{
        public MetlifeAuthotizationException() {
        }

        public MetlifeAuthotizationException(String message) {
            super(message);
        }

        public MetlifeAuthotizationException(String message, Throwable cause) {
            super(message, cause);
        }

        public MetlifeAuthotizationException(Throwable cause) {
            super(cause);
        }
    }
}
