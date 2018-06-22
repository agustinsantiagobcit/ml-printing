package cl.metlife.documentsender.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "LOTES")
@NamedQuery(name = "LotEntity.findAll", query = "SELECT l FROM LotEntity l")
public class LotEntity {

    public static final String LOT_SOURCE_WEB_APPLICATION = "Web Application";
    public static final String LOT_SOURCE_FILESYSTEM_DEMON = "Filesystem Demon";
    public static final String LOT_SOURCE_WEB_SERVICE = "Web Service";



    @Column(name = "RUT_USUARIO")
    private String userRUT;

    @Column(name = "MAIL_USUARIO")
    private String userMail;

    @Id
    @Column(name = "LOTE")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CARGA")
    private Calendar loadTime;

    @Column(name = "ESTADO") // Por enviar, Enviando, Completado, No completado
    private String status;

    @Column(name = "TIEMPO_PROCESO")
    private long processTime;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "RUTA_ARCHIVO_PROCESANDO")
    private String processingFilePath;



    @Transient
    private int docsNumber;

    @Transient
    private String date;

    @Transient
    private String lotShortName;

    @Transient
    private String[] array_lotName;

    public String getUserRUT() {
        return userRUT;
    }

    public void setUserRUT(String userRUT) {
        this.userRUT = userRUT;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Calendar loadTime) {
        this.loadTime = loadTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getProcessTime() {
        return processTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }

    public int getDocsNumber() {
        return docsNumber;
    }

    public void setDocsNumber(int docsNumber) {
        this.docsNumber = docsNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLotShortName() {
        if(lotShortName == null || lotShortName.isEmpty()) {
            calculateFormattedLotName();
        }
        return lotShortName;
    }

    public void setLotShortName(String lotShortName) {
        this.lotShortName = lotShortName;
    }

    public String getFormattedLoadTime() {
        if(loadTime == null) {
            return "";
        }
        Date date = loadTime.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date);
    }

    public void setFormattedLotName(String lotShortName){
        this.lotShortName = lotShortName;
    }

    public String getFormattedLotName() {
        return this.lotShortName;
    }

    public void calculateFormattedLotName() {
        String lotShortName = "";
        array_lotName = this.name.split("_");

        for(int i = 2; i < array_lotName.length; i++ ){
            lotShortName = lotShortName + array_lotName[i];
        }
        this.lotShortName = lotShortName;
    }


    @Override
    public String toString(){
        return String.format("lot:{name:'%s',status:'%s',load_date:'%s',rut:'%s',mail:'%s', source: '%s'}", name, status, getFormattedLoadTime(), userRUT, userMail,source);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProcessingFilePath() {
        return processingFilePath;
    }

    public void setProcessingFilePath(String processingFilePath) {
        this.processingFilePath = processingFilePath;
    }
}