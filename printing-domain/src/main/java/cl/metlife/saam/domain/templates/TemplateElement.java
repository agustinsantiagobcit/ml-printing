package cl.metlife.saam.domain.templates;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TIPO_ELEMENTO_ID",discriminatorType = DiscriminatorType.INTEGER)
@javax.persistence.Table(name = "SAAM2_ELEMENTO_PLANTILLA")
public abstract class TemplateElement {
    private Long id;
    private Long orden;
    private Long typeId;
    private Long tamanioX;
    private Long tamanioY;

    private ElementType type;
    protected static final String PARAMETER_REGEX = "\\$\\{(.*?)\\}";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORDEN")
    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    @Basic
    @Column(name = "TIPO_ELEMENTO_ID")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long tipoElemento) {
        this.typeId = tipoElemento;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TIPO_ELEMENTO_ID")
    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "TAMANO_X")
    public Long getTamanioX() {
        return tamanioX;
    }

    public void setTamanioX(Long tamanioX) {
        this.tamanioX = tamanioX;
    }

    @Basic
    @Column(name = "TAMANO_Y")
    public Long getTamanioY() {
        return tamanioY;
    }

    public void setTamanioY(Long tamanioY) {
        this.tamanioY = tamanioY;
    }

    @Transient
    public abstract String getXML(Map<String, Object> parameterMap);


    @Transient
    protected String removeP(String text) {
        if(text==null)
            return null;


        return text.replace("<p>","").replace("</p>","");
    }


    @Transient
    public List<Parameter> parseParameters() {

        String xml = this.getXML(null);

        if(xml == null)
            return new ArrayList<Parameter>();

        List<Parameter> resultingParameters = parseTextForParameters(xml);

        return resultingParameters;
    }

    @Transient
    protected List<Parameter> parseTextForParameters(String xml) {

        List<Parameter> resultingParameters = new ArrayList<Parameter>();


        Pattern p = Pattern.compile(PARAMETER_REGEX);

        Matcher m = p.matcher(xml);
        String foundParameter;
        String parameterName;

        while ( m.find() ) {
            foundParameter = m.group();

            if ( foundParameter.length() < 3 )
                throw new ParameterParserException("Malformed parameter, expected: ${parameter_name}, found: " + foundParameter);

            parameterName = foundParameter.substring(2, foundParameter.length() - 1);

            if ( parameterName == null || "".equals(parameterName) )
                throw new ParameterParserException("Parameter name can't be empty: " + foundParameter + ", at position: " + m.start());

            if (parameterName.contains("$") || parameterName.contains("{") || parameterName.contains("}"))
                throw new ParameterParserException("Found a parameter with a special character ($,{ or }) in it's name: " + foundParameter + ", at position: " + m.start());

            if(!resultingParameters.contains(parameterName)) {
                Parameter parameter = new Parameter();
                parameter.setNombre(parameterName);
                resultingParameters.add(parameter);
            }
        }
        return resultingParameters;
    }

    @Transient
    protected String createTextElement(String tagname, String text) {
        return "<" + tagname + ">"+
        "<![CDATA["+ (text==null?"":text)+"]]>"+
        "</" + tagname + ">";
    }


    /**
     *
     * @param xml <Parrafo>${texto}</Parrafo>
     * @param paramsValue Ej: {"texto": "asasdasd"}
     * @return <Parrafo>asasdasd</Parrafo>
     * @throws ParameterParserException
     */
    @Transient
    public String fillParameterValues(String xml, Map<String, Object> paramsValue){

        if(paramsValue == null)
            return xml;

        String res = xml;

        for ( Map.Entry<String, Object> parameter : paramsValue.entrySet() ) {

            if(parameter.getValue() instanceof String) {

                String paramRegex = "\\$\\{" + parameter.getKey() + "\\}";
                res = res.replaceAll(paramRegex, (String)parameter.getValue());
            }
        }

        return res;
    }

    @Transient
    public abstract TemplateElement copy();

    @Transient
    protected void baseCopyTo(TemplateElement copy) {

        copy.setOrden(this.getOrden());
        copy.setTypeId(this.getTypeId());
        copy.setType(this.getType());
        copy.setTamanioX(this.getTamanioX());
        copy.setTamanioY(this.getTamanioY());

    }


}
