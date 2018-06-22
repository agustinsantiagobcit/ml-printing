package cl.metlife.saam.domain.templates;

import cl.metlife.saam.domain.BaseEntity;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "SAAM2_TIPO_ELEMENTO")
public class ElementType extends BaseEntity {

    public static final String ELEMENT_TYPE_TITLE = "1";
    public static final String ELEMENT_TYPE_TABLE = "2";
    public static final String ELEMENT_TYPE_IMAGE = "3";
    public static final String ELEMENT_TYPE_PARAGRAPH = "4";
    public static final String ELEMENT_TYPE_DYNAMIC_TABLE = "5";
    public static final String ELEMENT_TYPE_SUBTITLE = "6";
    public static final String ELEMENT_TYPE_LIST = "7";
    public static final String ELEMENT_TYPE_SIGNING = "8";
    public static final String ELEMENT_TYPE_DOUBLE_SIGNING = "9";
    public static final String ELEMENT_TYPE_CUADRO  = "10";
    public static final String ELEMENT_CARATULA_SALUD = "11";
    public static final String ELEMENT_TYPE_PAGE_BREAK= "12";
    public static final String ELEMENT_CARATULA_VIDA = "13";
    public static final String ELEMENT_CARATULA_VIDA_CON_TABLA = "14";

    private Long id;
    private String nombre;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 200)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Transient
    public String getIconImage(){

        switch (getId().intValue()){
            case 1:
                return "el1.jpg";
            case 2:
                return "el6.jpg";
            case 3:
                return "el8.png";
            case 4:
                return "el4.jpg";
            case 5:
                return "el6.jpg";
            case 6:
                return "el2.jpg";
            case 7:
                return "el3.jpg";
            case 8:
                return "el1.jpg";
            case 9:
                return "el1.jpg";
            default:
                return "el8.png";
        }
    }

}
