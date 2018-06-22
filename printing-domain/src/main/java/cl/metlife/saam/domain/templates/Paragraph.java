package cl.metlife.saam.domain.templates;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.persistence.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_TYPE_PARAGRAPH)
@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@javax.persistence.Table(name = "SAAM2_ELEMENTO_PARRAFO")
public class Paragraph extends TemplateElement {

    private String texto;

    @Basic
    @Column(name = "TEXTO", nullable = true, length = 1000)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }



    @Transient
    @Override
    public TemplateElement copy() {
        Paragraph clone = new Paragraph();
        baseCopyTo(clone);
        clone.setTexto(this.getTexto());
        return clone;
    }



    @Transient
    @Override
    public String getXML(Map<String, Object> parameterMap) {

        StringBuilder stringBuilder = new StringBuilder();

        String text = "<?xml version=\"1.0\"?> \n" +
                "<!DOCTYPE html [ \n" +
                "<!ENTITY nbsp \"&#160;\"> \n" +
                "]> \n" +
                "<base>"+fillParameterValues(this.getTexto(),parameterMap)+"</base>";

        text = text.replaceAll("<br>","<br  />");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.parse(new ByteArrayInputStream(text.getBytes("UTF-8")));


            NodeList pList = doc.getElementsByTagName("p");

            for (int i = 0; i < pList.getLength(); i++){
                Node item = pList.item(i);
                Node classe = pList.item(i).getAttributes().getNamedItem("class");
                String align = "left";
                if(classe != null)
                    align = classe.getNodeValue();

                if(align.contains("ql-align-justify"))
                    align="justify";
                else if(align.contains("ql-align-left"))
                    align="left";
                else if(align.contains("ql-align-right"))
                    align="right";
                else if(align.contains("ql-align-center"))
                    align="center";

                stringBuilder.append(createTextElement("Alignment",align));

                stringBuilder.append(createTextElement("Paragraph",nodeToString(item)));

                if(i< pList.getLength()-1) {
                    stringBuilder.append("</Element>\n");
                    stringBuilder.append("<Element>");
                }

            }

            stringBuilder.append("<Spacer>Space</Spacer>");
            return  stringBuilder.toString();

        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Error al parsear parrafos",e);
        } catch (SAXException e) {
            throw new RuntimeException("Error al parsear parrafos",e);
        } catch (IOException e) {
            throw new RuntimeException("Error al parsear parrafos",e);
        } catch (TransformerException e) {
            throw new RuntimeException("Error al parsear parrafos",e);
        }

    }

    @Transient
    protected String nodeToString(Node node) throws TransformerException {
        StringWriter sw = new StringWriter();

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(node), new StreamResult(sw));

        return sw.toString();
    }


}
