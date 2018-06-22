package cl.metlife.documentsender.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The persistent class for the DOCUMENT database table.
 */
@Entity
@Table(name = "DOCUMENTOS")
@NamedQuery(name = "DocumentEntity.findAll", query = "SELECT d FROM DocumentEntity d")
public class DocumentEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Column(name = "NUMERO_DOCUMENTO")
    private String documentNumber;

    @Column(name = "MESSAGE_ID")
    private String messageId;

    @Column(name = "TIPO_DOCUMENTO")
    private String documentType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_INICIO_COBERTURA")
    private Calendar initDate;

    @Column(name = "PLAN")
    private String plan;

    @Column(name = "NOMBRE_CONTRATANTE")
    private String customerName;

    @Column(name = "RUT_CONTRATANTE")
    private String customerRUT;

    @Column(name = "MAIL_CONTRATANTE")
    private String customerMail;

    @Column(name = "ENCRYPTED")
    private String encrypted;

    @Lob
    @Column(name = "CONTENIDO_DOCUMENTO")
    @Basic(fetch=FetchType.LAZY)
    private byte[] documentContent;

    @Id
    @SequenceGenerator(name="DOCUMENT_ID_GENERATOR", sequenceName="ID_DOCUMENTO")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCUMENT_ID_GENERATOR")
    @Column(name = "ID")
    private long documentId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_ENVIO")
    private Calendar sentTime;

    @Column(name = "USUARIO")
    private String user;

    @Column(name = "LOTE")
    private String lot;

    @Column(name = "ESTADO")
    private String status;

    @Column(name = "NOMBRE_ARCHIVO")
    private String fileName;

    @Column(name = "NOMBRE_EN_REPOSITORIO")
    private String fileRepositoryPath;

    @Column(name = "PLANTILLA")
    private String templateName;

    @Column(name = "LINEA_NEGOCIO")
    private String businessLine;


    @Column(name = "LLAVE_CONTENIDO")
    private String llaveContenido;



    @Column(name = "DATOS_EXTRA")
    private String datosExtra;

    @Column(name = "FECHA_RECEPCION")
    private Date fechaRecepcion;

    @Column(name = "SENT_FROM")
    private String sentFrom;

    @Column(name = "CORREOS_ASESORES")
    private String correosAsesores;

    @Column(name = "SENDER_MAIL")
    private String senderMail;

    @Column(name = "SENDER_NAME")
    private String senderName;

    @Column(name = "FECHA_CARGA")
    private Date loadDate;

    @Transient
    private String date;

    @Transient
    private String hour;

    @Transient
    private String dateAndHour;

    @Transient
    private String fechaVigencia;

    @Transient
    private String lotShortName;

    @Transient
    private String formattedFechaRecepcion;


    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentoType) {
        this.documentType = documentoType;
    }

    public String getBusinessLine() {
        return businessLine;
    }

    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }

    public Calendar getInitDate() {
        return initDate;
    }

    public void setInitDate(Calendar initDate) {
        this.initDate = initDate;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerRUT() {
        return customerRUT;
    }

    public void setCustomerRUT(String customerRUT) {
        this.customerRUT = customerRUT;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public byte[] getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(byte[] documentContent) {
        this.documentContent = documentContent;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public Calendar getSentTime() {
        return sentTime;
    }

    public void setSentTime(Calendar sentTime) {
        this.sentTime = sentTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileRepositoryPath() {
        return fileRepositoryPath;
    }

    public void setFileRepositoryPath(String fileRepositoryPath) {
        this.fileRepositoryPath = fileRepositoryPath;
    }

    public String getFormattedDate() {
        if(sentTime == null) {
            return "";
        }
        Date date = sentTime.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public String getFormattedInitDate() {
        if(initDate == null) {
            return "";
        }
        Date date = initDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    public String getFormattedHour() {
        if(sentTime == null) {
            return "";
        }
        Date date = sentTime.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    public String getFormattedHourAndDate() {
        if(sentTime == null) {
            return "";
        }
        Date date = sentTime.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDateAndHour() {
        return dateAndHour;
    }

    public void setDateAndHour(String dateAndHour) {
        this.dateAndHour = dateAndHour;
    }

    public void setLotShortName(String lotShortName) {
        this.lotShortName = lotShortName;
    }

    public String getLotShortName() {
        return lotShortName;
    }

    public void setFormattedLotName(){
        lotShortName = "";
        String[] array_lotName = lot.split("_");
        if(array_lotName.length >= 2){
            for(int i = 2; i < array_lotName.length; i++ ){
                this.lotShortName = this.lotShortName + array_lotName[i];
            }
        }else{
            this.lotShortName = "";
        }
    }

    public String getFormattedLotName(){
        lotShortName = "";
        String[] array_lotName = lot.split("_");
        if(array_lotName.length >= 2){
            for(int i = 2; i < array_lotName.length; i++ ){
                lotShortName = lotShortName + array_lotName[i];
            }
        }else{
            lotShortName = "";
        }
        return lotShortName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


    public String getDatosExtra() {
        return datosExtra;
    }

    public void setDatosExtra(String datosExtra) {
        this.datosExtra = datosExtra;
    }

    public String getLlaveContenido() {
        return llaveContenido;
    }

    public void setLlaveContenido(String llaveContenido) {
        this.llaveContenido = llaveContenido;
    }

    public String getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(String fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getFormattedFechaRecepcion() {
        return formattedFechaRecepcion;
    }

    public void setFormattedFechaRecepcion(String formattedFechaRecepcion) {
        this.formattedFechaRecepcion = formattedFechaRecepcion;
    }

    public String getSentFrom() {
        return sentFrom;
    }

    public void setSentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    public String getCorreosAsesores() {
        return correosAsesores;
    }

    public void setCorreosAsesores(String correosAsesores) {
        this.correosAsesores = correosAsesores;
    }

    public String getSenderMail() { return senderMail; }

    public void setSenderMail(String senderMail) { this.senderMail = senderMail; }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderName() {
        return senderName;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }
}