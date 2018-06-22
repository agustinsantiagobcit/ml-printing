package cl.metlife.documentsender.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "CONTENIDOS")
@NamedQuery(name = "ContentEntity.findAll", query = "SELECT c FROM ContentEntity c")
public class ContentEntity {

    @Id
    @SequenceGenerator(name="CONTENT_ID_GENERATOR", sequenceName="CONTENIDOS_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONTENT_ID_GENERATOR")
    @Column(name = "ID_CONTENIDO")
    private String id;

    @Column(name = "LLAVE")
    private String contentKey;

    @Column(name = "CONTENIDO")
    private String content;

    @Column(name = "HORAENVIO")
    private Calendar sentTime;

    @Column(name = "MAILDESTINATARIOS")
    private String recipientMail;

    @Column(name = "FIRST_VIEWED")
    private Date firstViewed;

    @Column(name = "LAST_VIEWED")
    private Date lastViewed;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getSentTime() {
        return sentTime;
    }

    public void setSentTime(Calendar sentTime) {
        this.sentTime = sentTime;
    }

    public String getRecipientMail() {
        return recipientMail;
    }

    public void setRecipientMail(String recipientMail) {
        this.recipientMail = recipientMail;
    }

    public Date getFirstViewed() {
        return firstViewed;
    }

    public void setFirstViewed(Date firstViewed) {
        this.firstViewed = firstViewed;
    }

    public Date getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(Date lastViewed) {
        this.lastViewed = lastViewed;
    }

}