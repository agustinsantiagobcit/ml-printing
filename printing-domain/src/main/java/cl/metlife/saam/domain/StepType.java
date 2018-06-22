package cl.metlife.saam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BluePrints Developer on 24-01-2017.
 */
@Entity
@Table(name = "SAAM2_TIPO_PASO")
public class StepType extends BaseEntity implements Serializable{

    public static final String STEP_TYPE_EXTRACTION = "1";
    public static final String STEP_TYPE_ODLOAD = "2";
    public static final String STEP_TYPE_ALTIUZ_GENERATIION = "3";
    public static final String STEP_TYPE_FILE_FETCH = "4";
    public static final String STEP_TYPE_MAIL_SEND = "5";
    public static final String STEP_TYPE_PROCESS_CALL = "6";
    public static final String STEP_TYPE_SMS_SEND = "7";
    public static final String STEP_TYPE_FILENET_FETCH = "8";
    public static final String STEP_TYPE_ENCRYPTION = "9";

    public static final String STEP_TYPE_FTP_OUT ="11";
    public static final String STEP_TYPE_CSV_OUT="12";
    public static final String STEP_TYPE_TEMPLATE_GENERATION="13";
    public static final String STEP_TYPE_SHARED_FOLDER_OUT ="14";
    public static final String STEP_TYPE_EXTRACTION_QUERY ="15";
    public static final String STEP_TYPE_PRINTING ="16";

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PASO", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
