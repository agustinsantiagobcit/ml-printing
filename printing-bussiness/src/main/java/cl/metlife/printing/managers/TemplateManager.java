package cl.metlife.printing.managers;

import cl.metlife.printing.domain.AuditLogType;
import cl.metlife.saam.domain.templates.*;
import cl.metlife.printing.persistence.dao.TemplateDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class TemplateManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateManager.class);

    @EJB
    TemplateDAO templateDAO;

    @EJB
    AuditLogManager auditLogManager;

    public Template getById(Long id){
        return templateDAO.getById(id);
    }

    public Template create(Template plantilla, String user){
        Template template = templateDAO.create(plantilla);
        auditLogManager.log(AuditLogType.CREACION_PLANTILLA, template.getName()+", Id:"+template.getId(), user);
        return template;
    }

    public boolean delete(Template plantilla, String user){
        return templateDAO.delete(plantilla);
    }

    public Template update(Template plantilla, String user){
        Template template = templateDAO.update(plantilla);
        auditLogManager.log(AuditLogType.ACTUALIZACION_PLANTILLA, template.getName()+", Id:"+template.getId(), user);
        return template;
    }


    public List<Template> getAllTemplates(){
        List<Template> allTemplates = templateDAO.getAllTemplates();


        return new ArrayList<Template>(allTemplates);
    }

    public List<TemplateElement> defaultTemplateElement(){
        List<TemplateElement> elementoPlantillaList = new ArrayList<TemplateElement>();

        Title elementoTitulo = new Title();
        elementoTitulo.setTexto("Nuevo Documento");

        Paragraph elementoParrafo = new Paragraph();
        elementoParrafo.setTexto("Lorem Ipsum...");

        elementoPlantillaList.add(elementoTitulo);
        elementoPlantillaList.add(elementoParrafo);



        return elementoPlantillaList;
    }


    public byte[] getPreviewPDF(Template plantilla)  {
        return generateDocument(plantilla,null);
    }

    /**
     * Metodo que genera PDF a partir de un XML
     * @param xmlSource
     * @return
     */
    public byte[] getGeneratePDFBytes(String xmlSource) {
        String jasperReportName = "BPFWK_Printing_Generic_Template.jasper";

        InputStream jasperReportIS = this.getClass().getClassLoader().getResourceAsStream("cl/metlife/saam/printing/" + jasperReportName);

        String basepath = this.getClass().getClassLoader().getResource("cl/metlife/saam/printing/" + jasperReportName).toString().replace(jasperReportName,"");


        Map<String, Object> parametros = new HashMap<String, Object>();

        parametros.put("BASE_PATH",basepath);

        Document d = null;
        try {
            d = stringToDom(xmlSource);
            byte[] bytes = JasperRunManager.runReportToPdf(jasperReportIS, parametros, new JRXmlDataSource(d));
            return bytes;

        } catch (SAXException e) {
            throw new TemplateGenerationException("Error al generar el XML",e);
        } catch (ParserConfigurationException e) {
            throw new TemplateGenerationException("Error con configuracion del parser XML",e);
        } catch (IOException e) {
            throw new TemplateGenerationException("Error exotico con streams",e);
        } catch (JRException e) {
            throw new TemplateGenerationException("Error al generar jasper",e);
        }catch (RuntimeException e){
            throw new TemplateGenerationException("Really unexpected error 100% real no fake",e);
        }
    }

    private void addTextElement( StringBuilder xmlbuilder, String tagname,String text) {
        xmlbuilder.append("<" + tagname + ">");
        xmlbuilder.append("<![CDATA["+ text+"]]>");
        xmlbuilder.append("</" + tagname + ">");
    }

    private void makeFooter(Template template, Map<String, Object> parameterMap, StringBuilder xmlbuilder) {

        makeHeaderOrFooter( xmlbuilder, "<Footer>", "B", template.getLogoBR(), template.getLogoBL(), template.getTextoBR(), template.getTextoBL(), "</Footer>",parameterMap);
    }

    private void makeHeader(Template template, Map<String, Object> parameterMap, StringBuilder xmlbuilder) {
        makeHeaderOrFooter( xmlbuilder, "<Header>", "T", template.getLogoTR(), template.getLogoTL(), template.getTextoTR(), template.getTextoTL(),"</Header>",parameterMap);
    }

    private void makeHeaderOrFooter( StringBuilder xmlbuilder, String openElem, String tOrB, String logoR, String logoL, String textoR, String textoL, String closeElem, Map<String, Object> parameterMap) {
        xmlbuilder.append(openElem);

        if (logoR != null) {
            addTextElement(xmlbuilder, "Logo"+tOrB+"R", logoR);
        }

        if (logoL != null) {
            addTextElement(xmlbuilder, "Logo"+tOrB+"L", logoL);
        }

        if (textoR != null) {
            addTextElement(xmlbuilder, "Text"+tOrB+"R", textoR);
        }

        if (textoL != null) {
            addTextElement(xmlbuilder, "Text"+tOrB+"L", textoL);
        }
        xmlbuilder.append(closeElem);
    }

    //this is the function that is converting the xmlsource to Document
    private Document stringToDom(String xmlSource)  throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xmlSource)));
    }


    private static final String XMLBASE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<BlueprintsPrinting xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" ;


    private static final String POSTXML = "</BlueprintsPrinting>\n" ;

    public byte[] generateDocument(Template template, Map<String,Object> parameterMap) {

        String xml = getXML(template,parameterMap);
        return getGeneratePDFBytes(xml);

    }

    public String getXML(Template template, Map<String,Object> parameterMap) {
        StringBuilder xmlbuilder = new StringBuilder(XMLBASE);

        makeHeader(template, parameterMap, xmlbuilder);
        makeFooter(template, parameterMap, xmlbuilder);

        makeElements(template, parameterMap, xmlbuilder);

        addTextElement(xmlbuilder,"PageNumberPosition",template.getPageNumberPosition());


        xmlbuilder.append(POSTXML);

        return xmlbuilder.toString();
    }

    private void makeElements(Template template, Map<String, Object> parameterMap, StringBuilder xmlbuilder) {
        xmlbuilder.append("\t<Elements>\n");
        for (TemplateElement templateElement : template.getElements()) {
            String xml = templateElement.getXML(parameterMap);
            if(xml != null) {
                xmlbuilder.append("<Element>");
                xmlbuilder.append(xml);
                xmlbuilder.append("</Element>\n");
            }
        }
        xmlbuilder.append("\t</Elements>\n");
    }


    public void parseParameters(Template template) {

        List<Parameter> oldParameters = template.getParameters();
        List<Parameter> newParameters = new ArrayList<Parameter>();

        for (TemplateElement templateElement : template.getElements()) {
            List<Parameter> elementParameters = templateElement.parseParameters();
            newParameters.addAll(elementParameters);
        }

        template.setParameters(mergeParameters(oldParameters, newParameters));

    }

    private  List<Parameter> mergeParameters(List<Parameter> oldParameters, List<Parameter> newParameters) {
        List<Parameter> mergedParameters =new ArrayList<Parameter>();

        for (Parameter newParameter : newParameters) {
            for (Parameter oldParameter : oldParameters) {
                if(newParameter.getNombre().equals(oldParameter.getNombre())&&!mergedParameters.contains(oldParameter)) {
                    mergedParameters.add(oldParameter);
                    break;
                }
            }
        }

        for (Parameter newParameter : newParameters) {
            boolean esta=false;
            for (Parameter mergedParameter : mergedParameters) {
                if(newParameter.getNombre().equals(mergedParameter.getNombre())) {
                    esta=true;
                    break;
                }
            }
            if(!esta)
                mergedParameters.add(newParameter);
        }

        return mergedParameters;

    }

    public Template clone(Long templateId, String user) {


        Template original = getById(templateId);
        Template clone = new Template();

        clone.setName("Copia de "+ original.getName());
        clone.setDescription(original.getDescription());
        clone.setComentario(original.getComentario());
        clone.setLogoTR(original.getLogoTR());
        clone.setLogoBR(original.getLogoBR());
        clone.setLogoTL(original.getLogoTL());
        clone.setLogoBL(original.getLogoBL());
        clone.setTextoTL(original.getTextoTL());
        clone.setTextoBL(original.getTextoBL());
        clone.setTextoTR(original.getTextoTR());
        clone.setTextoBR(original.getTextoBR());
        clone.setPageNumberPosition(original.getPageNumberPosition());

        clone.setParameters(new ArrayList<Parameter>());
        clone.setElements(new ArrayList<TemplateElement>());

        for (Parameter parameter : original.getParameters()) {
            Parameter cloneParameter = new Parameter();
            cloneParameter.setNombre(parameter.getNombre());
            cloneParameter.setDescripcion(parameter.getDescripcion());
            cloneParameter.setMultiple(parameter.isMultiple());
            clone.getParameters().add(cloneParameter);
        }

        for (TemplateElement originalElement : original.getElements()) {
            TemplateElement copy = originalElement.copy();
            clone.getElements().add(copy);
        }

        Template ret = templateDAO.update(clone);

        auditLogManager.log(AuditLogType.CLONAR_PLANTILLA, clone.getName()+", Id original:"+templateId+ " Nuevo Id: "+ ret.getId(), user);
        return ret;

    }


    private class TemplateGenerationException extends RuntimeException      {

        public TemplateGenerationException(String message) {
            super(message);
        }

        public TemplateGenerationException(String message, Throwable cause) {
            super(message, cause);
        }

    }

}
