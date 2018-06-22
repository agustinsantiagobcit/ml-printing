package cl.metlife.printing.domain;

import javax.persistence.*;

@Entity
@Table(name = "PRINTING_TYPE_AUDIT_LOG")
public class AuditLogType {

    public static final Long INICIA_PROCESO_PRINTING = 1L;
    public static final Long ELIMINAR_PLANTILLA = 2L;
    public static final Long CLONAR_PLANTILLA = 3L;
    public static final Long ACTUALIZACION_PLANTILLA = 4L;
    public static final Long CREACION_PLANTILLA = 5L;
    public static final Long INICIA_CARGA_NN = 6L;
    public static final Long LOGIN = 7L;
    public static final Long PROGRAMAR_PROCESO= 8L;
    public static final Long CREA_PROCESO= 9L;
    public static final Long ACTUALIZA_PROCESO= 10L;

    private Long id;
    private String name;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
