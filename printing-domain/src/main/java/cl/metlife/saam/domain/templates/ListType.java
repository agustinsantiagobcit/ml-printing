package cl.metlife.saam.domain.templates;

import cl.metlife.saam.domain.BaseEntity;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "SAAM2_TIPO_LISTA")
public class ListType extends BaseEntity {

    public static final Long LIST_TYPE_NUMERIC = 1L;
    public static final Long LIST_TYPE_ALPHABET = 2L;
    public static final Long LIST_TYPE_ROMAN = 3L;
    public static final Long LIST_TYPE_CIRCLE = 4L;



    private Long id;
    private String name;

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
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    @Transient
    public String getPFListType(){

        switch (getId().intValue()){
            case 1:
            case 2:
            case 3:
                return "ordered";
            case 4:
                return "unordered";

        }

        return "unordered";
    }


    @Transient
    public String getPFItemType() {

        switch (getId().intValue()) {
            case 1:
                return "1";
            case 2:
                return "A";
            case 3:
                return "I";
            case 4:
                return "disc";
        }

        return "a";
    }
}
