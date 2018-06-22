package cl.metlife.printing.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@javax.persistence.Table(name = "PRINTING_DATOS_DOC")
public class DocumentData extends BaseEntity implements Serializable {
    private Long id;
    private String name;
    private String value;
    private Long documentDataId;
    private DocumentData documentData;
    private Long documentId;
    private Document document;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "VALOR")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "PADRE_ID", nullable = true, precision = 0)
    public Long getDocumentDataId() {
        return documentDataId;
    }

    public void setDocumentDataId(Long documentDataId) {
        this.documentDataId = documentDataId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PADRE_ID")
    public DocumentData getDocumentData() {
        return documentData;
    }

    public void setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
    }

    @Basic
    @Column(name = "PRINTING_DOCUMENTOS_ID", nullable = true, precision = 0)
    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRINTING_DOCUMENTOS_ID")
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
