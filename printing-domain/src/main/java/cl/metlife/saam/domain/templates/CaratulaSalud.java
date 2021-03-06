package cl.metlife.saam.domain.templates;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@DiscriminatorValue(ElementType.ELEMENT_CARATULA_SALUD)
@javax.persistence.Table(name = "SAAM2_ELEMENTO_CARATULA")
@PrimaryKeyJoinColumn(name = "ID_CARATULA", referencedColumnName = "ID")
public class CaratulaSalud extends TemplateElement implements Serializable {

    private String codigoSVSPoliza;
    private String numPoliza;
    private String contratante;
    private String rutContratante;
    private String asegurado;
    private String rutAsegurado;
    private String beneficiario;
    private String rutBeneficiario;
    private String tipoRiesgoAsegurado;
    private String poliza;
    private String vigenciaInicio;
    private String vigenciaTermino;
    private String renovacionAutomatica;
    private String prima;
    private String moneda;
    private String periodoPago;
    private String condiciones;
    private String comisionTotalCorrectoMonto;
    private String corredorNoHayComision;

    private String artCG1;
    private String artCG2;
    private String artCG3;
    private String artCG4;
    private String artCG5;
    private String artCG6;
    private String artCG7;
    private String artCG8;
    private String artCP1;
    private String artCP2;
    private String artCP3;
    private String artCP4;
    private String artCP5;
    private String artCP6;
    private String artCP7;
    private String artCP8;
    private String reglasPreexistencia;
    private String condEspAsegurabilidad;
    private String exclusionesArtCG;
    private String periodoCarencia;
    private String notificacion;
    private String notificacion1;
    private String notificacion2;
    private String notificacion3;


    @Basic
    @Column(name = "CODIGO_SVS_POLIZA")
    public String getCodigoSVSPoliza() {
        return codigoSVSPoliza;
    }

    public void setCodigoSVSPoliza(String codigoSVSPoliza) {
        this.codigoSVSPoliza = codigoSVSPoliza;
    }

    @Basic
    @Column(name = "NUM_POLIZA")
    public String getNumPoliza() {
        return numPoliza;
    }

    public void setNumPoliza(String numPoliza) {
        this.numPoliza = numPoliza;
    }

    @Basic
    @Column(name = "contratante")
    public String getContratante() {
        return contratante;
    }

    public void setContratante(String contratante) {
        this.contratante = contratante;
    }

    @Basic
    @Column(name = "rut_contratante")
    public String getRutContratante() {
        return rutContratante;
    }

    public void setRutContratante(String rutContratante) {
        this.rutContratante = rutContratante;
    }

    @Basic
    @Column(name = "asegurado")
    public String getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(String asegurado) {
        this.asegurado = asegurado;
    }

    @Basic
    @Column(name = "rut_asegurado")
    public String getRutAsegurado() {
        return rutAsegurado;
    }

    public void setRutAsegurado(String rutAsegurado) {
        this.rutAsegurado = rutAsegurado;
    }

    @Basic
    @Column(name = "TIPO_DE_RIESGO_ASEGURADO")
    public String getTipoRiesgoAsegurado() {
        return tipoRiesgoAsegurado;
    }

    public void setTipoRiesgoAsegurado(String tipoPoliza) {
        this.tipoRiesgoAsegurado = tipoPoliza;
    }

    @Basic
    @Column(name = "poliza")
    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    @Basic
    @Column(name = "vigencia_inicio")
    public String getVigenciaInicio() {
        return vigenciaInicio;
    }

    public void setVigenciaInicio(String vigenciaInicio) {
        this.vigenciaInicio = vigenciaInicio;
    }

    @Basic
    @Column(name = "vigencia_termino")
    public String getVigenciaTermino() {
        return vigenciaTermino;
    }

    public void setVigenciaTermino(String vigenciaTermino) {
        this.vigenciaTermino = vigenciaTermino;
    }

    @Basic
    @Column(name = "renovacion_automatica")
    public String getRenovacionAutomatica() {
        return renovacionAutomatica;
    }

    public void setRenovacionAutomatica(String renovacionAutomatica) {
        this.renovacionAutomatica = renovacionAutomatica;
    }

    @Basic
    @Column(name = "prima")
    public String getPrima() {
        return prima;
    }

    public void setPrima(String prima) {
        this.prima = prima;
    }

    @Basic
    @Column(name = "moneda")
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Basic
    @Column(name = "periodo_pago")
    public String getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(String periodoPago) {
        this.periodoPago = periodoPago;
    }

    @Basic
    @Column(name = "condiciones")
    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    @Basic
    @Column(name = "comision_total_corredor_monto")
    public String getComisionTotalCorrectoMonto() {
        return comisionTotalCorrectoMonto;
    }

    public void setComisionTotalCorrectoMonto(String comisionTotalCorrectoMonto) {
        this.comisionTotalCorrectoMonto = comisionTotalCorrectoMonto;
    }

    @Basic
    @Column(name = "corredor_no_hay_comision")
    public String getCorredorNoHayComision() {
        return corredorNoHayComision;
    }

    public void setCorredorNoHayComision(String corredorNoHayComision) {
        this.corredorNoHayComision = corredorNoHayComision;
    }

    @Basic
    @Column(name = "art_cg_1")
    public String getArtCG1() {
        return artCG1;
    }

    public void setArtCG1(String artCG1) {
        this.artCG1 = artCG1;
    }

    @Basic
    @Column(name = "art_cg_2")
    public String getArtCG2() {
        return artCG2;
    }

    public void setArtCG2(String artCG2) {
        this.artCG2 = artCG2;
    }

    @Basic
    @Column(name = "art_cg_3")
    public String getArtCG3() {
        return artCG3;
    }

    public void setArtCG3(String artCG3) {
        this.artCG3 = artCG3;
    }

    @Basic
    @Column(name = "art_cg_4")
    public String getArtCG4() {
        return artCG4;
    }

    public void setArtCG4(String artCG4) {
        this.artCG4 = artCG4;
    }

    @Basic
    @Column(name = "art_cg_5")
    public String getArtCG5() {
        return artCG5;
    }

    public void setArtCG5(String artCG5) {
        this.artCG5 = artCG5;
    }

    @Basic
    @Column(name = "art_cg_6")
    public String getArtCG6() {
        return artCG6;
    }

    public void setArtCG6(String artCG6) {
        this.artCG6 = artCG6;
    }

    @Basic
    @Column(name = "art_cg_7")
    public String getArtCG7() {
        return artCG7;
    }

    public void setArtCG7(String artCG7) {
        this.artCG7 = artCG7;
    }

    @Basic
    @Column(name = "art_cg_8")
    public String getArtCG8() {
        return artCG8;
    }

    public void setArtCG8(String artCG8) {
        this.artCG8 = artCG8;
    }


    @Basic
    @Column(name = "art_cp_1")
    public String getArtCP1() {
        return artCP1;
    }

    public void setArtCP1(String artCP1) {
        this.artCP1 = artCP1;
    }

    @Basic
    @Column(name = "art_cp_2")
    public String getArtCP2() {
        return artCP2;
    }

    public void setArtCP2(String artCP2) {
        this.artCP2 = artCP2;
    }

    @Basic
    @Column(name = "art_cp_3")
    public String getArtCP3() {
        return artCP3;
    }

    public void setArtCP3(String artCP3) {
        this.artCP3 = artCP3;
    }

    @Basic
    @Column(name = "art_cp_4")
    public String getArtCP4() {
        return artCP4;
    }

    public void setArtCP4(String artCP4) {
        this.artCP4 = artCP4;
    }

    @Basic
    @Column(name = "art_cp_5")
    public String getArtCP5() {
        return artCP5;
    }

    public void setArtCP5(String artCP5) {
        this.artCP5 = artCP5;
    }

    @Basic
    @Column(name = "art_cp_6")
    public String getArtCP6() {
        return artCP6;
    }

    public void setArtCP6(String artCP6) {
        this.artCP6 = artCP6;
    }

    @Basic
    @Column(name = "art_cp_7")
    public String getArtCP7() {
        return artCP7;
    }

    public void setArtCP7(String artCP7) {
        this.artCP7 = artCP7;
    }

    @Basic
    @Column(name = "art_cp_8")
    public String getArtCP8() {
        return artCP8;
    }

    public void setArtCP8(String artCP8) {
        this.artCP8 = artCP8;
    }

    @Basic
    @Column(name = "REGLAS_PREEXISTENCIA")
    public String getReglasPreexistencia() {
        return reglasPreexistencia;
    }

    public void setReglasPreexistencia(String beneficioFallecimiento) {
        this.reglasPreexistencia = beneficioFallecimiento;
    }

    @Basic
    @Column(name = "cond_esp_asegurabilidad")
    public String getCondEspAsegurabilidad() {
        return condEspAsegurabilidad;
    }

    public void setCondEspAsegurabilidad(String condEspAsegurabilidad) {
        this.condEspAsegurabilidad = condEspAsegurabilidad;
    }

    @Basic
    @Column(name = "exclusiones_art_cg")
    public String getExclusionesArtCG() {
        return exclusionesArtCG;
    }

    public void setExclusionesArtCG(String exclusionesArtCG) {
        this.exclusionesArtCG = exclusionesArtCG;
    }

    @Basic
    @Column(name = "periodo_carencia")
    public String getPeriodoCarencia() {
        return periodoCarencia;
    }

    public void setPeriodoCarencia(String periodoCarencia) {
        this.periodoCarencia = periodoCarencia;
    }

    @Basic
    @Column(name = "sistema_notificacion")
    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    @Basic
    @Column(name = "sistema_notificacion_1")
    public String getNotificacion1() {
        return notificacion1;
    }

    public void setNotificacion1(String notificacion1) {
        this.notificacion1 = notificacion1;
    }

    @Basic
    @Column(name = "sistema_notificacion_2")
    public String getNotificacion2() {
        return notificacion2;
    }

    public void setNotificacion2(String notificacion2) {
        this.notificacion2 = notificacion2;
    }

    @Basic
    @Column(name = "sistema_notificacion_3")
    public String getNotificacion3() {
        return notificacion3;
    }

    public void setNotificacion3(String notificacion3) {
        this.notificacion3 = notificacion3;
    }

    @Basic
    @Column(name = "beneficiario")
    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    @Basic
    @Column(name = "rut_beneficiario")
    public String getRutBeneficiario() {
        return rutBeneficiario;
    }

    public void setRutBeneficiario(String rutBeneficiario) {
        this.rutBeneficiario = rutBeneficiario;
    }

    @Transient
    @Override
    public String getXML(Map<String, Object> parameterMap) {


        String baseXML = "<CaratulaVida>" +
                createTextElement("TipoCaratula", "Salud") +
                createTextElement("CodigoSVSPoliza", getCodigoSVSPoliza()) +
                createTextElement("NumPoliza", getNumPoliza() == null ? "" : getNumPoliza().toString()) +
                createTextElement("Contratante", getContratante()) +
                createTextElement("RutContratante", getRutContratante()) +
                createTextElement("Asegurado", getAsegurado()) +
                createTextElement("RutAsegurado", getRutAsegurado()) +
                createTextElement("Beneficiario", getBeneficiario()) +
                createTextElement("RutBeneficiario", getRutBeneficiario()) +
                createTextElement("TipoRiesgoAsegurado", getTipoRiesgoAsegurado()) +
                createTextElement("Poliza", getPoliza()) +
                createTextElement("VigenciaInicio", getVigenciaInicio()) +
                createTextElement("VigenciaTermino", getVigenciaTermino()) +
                createTextElement("RenovacionAutomatica", getRenovacionAutomatica()) +
                createTextElement("Prima", getPrima()) +
                createTextElement("Moneda", getMoneda()) +
                createTextElement("PeriodoPago", getPeriodoPago()) +
                createTextElement("Condiciones", getCondiciones()) +
                createTextElement("ComisionTotalCorrectoMonto", getComisionTotalCorrectoMonto()) +
                createTextElement("CorredorNoHayComision", getCorredorNoHayComision()) +
                createTextElement("ArtCG1", getArtCG1()) +
                createTextElement("ArtCG2", getArtCG2()) +
                createTextElement("ArtCG3", getArtCG3()) +
                createTextElement("ArtCG4", getArtCG4()) +
                createTextElement("ArtCG5", getArtCG5()) +
                createTextElement("ArtCG6", getArtCG6()) +
                createTextElement("ArtCG7", getArtCG7()) +
                createTextElement("ArtCG8", getArtCG8()) +
                createTextElement("ArtCP1", getArtCP1()) +
                createTextElement("ArtCP2", getArtCP2()) +
                createTextElement("ArtCP3", getArtCP3()) +
                createTextElement("ArtCP4", getArtCP4()) +
                createTextElement("ArtCP5", getArtCP5()) +
                createTextElement("ArtCP6", getArtCP6()) +
                createTextElement("ArtCP7", getArtCP7()) +
                createTextElement("ArtCP8", getArtCP8()) +
                createTextElement("ReglasPreexistencia", getReglasPreexistencia()) +
                createTextElement("CondEspAsegurabilidad", getCondEspAsegurabilidad()) +
                createTextElement("ExclusionesArtCG", getExclusionesArtCG()) +
                createTextElement("PeriodoCarencia", getPeriodoCarencia()) +
                createTextElement("Notificacion", getNotificacion()) +
                createTextElement("Notificacion1", getNotificacion1()) +
                createTextElement("Notificacion2", getNotificacion2()) +
                createTextElement("Notificacion3", getNotificacion3()) +
                "</CaratulaVida>";
        return fillParameterValues(baseXML,parameterMap);


    }

    @Override
    public TemplateElement copy() {
        CaratulaSalud copy = new CaratulaSalud();

        baseCopyTo(copy);

        copy.setCodigoSVSPoliza(this.getCodigoSVSPoliza());
        copy.setNumPoliza(this.getNumPoliza());
        copy.setContratante(this.getContratante());
        copy.setRutContratante(this.getRutContratante());
        copy.setAsegurado(this.getAsegurado());
        copy.setRutAsegurado(this.getRutAsegurado());
        copy.setBeneficiario(this.getBeneficiario());
        copy.setRutBeneficiario(this.getRutBeneficiario());
        copy.setTipoRiesgoAsegurado(this.getTipoRiesgoAsegurado());
        copy.setPoliza(this.getPoliza());
        copy.setVigenciaInicio(this.getVigenciaInicio());
        copy.setVigenciaTermino(this.getVigenciaTermino());
        copy.setRenovacionAutomatica(this.getRenovacionAutomatica());
        copy.setPrima(this.getPrima());
        copy.setMoneda(this.getMoneda());
        copy.setPeriodoPago(this.getPeriodoPago());
        copy.setCondiciones(this.getCondiciones());
        copy.setComisionTotalCorrectoMonto(this.getComisionTotalCorrectoMonto());
        copy.setCorredorNoHayComision(this.getCorredorNoHayComision());
        copy.setArtCG1(this.getArtCG1());
        copy.setArtCG2(this.getArtCG2());
        copy.setArtCG3(this.getArtCG3());
        copy.setArtCG4(this.getArtCG4());
        copy.setArtCG5(this.getArtCG5());
        copy.setArtCG6(this.getArtCG6());
        copy.setArtCG7(this.getArtCG7());
        copy.setArtCG8(this.getArtCG8());
        copy.setArtCP1(this.getArtCP1());
        copy.setArtCP2(this.getArtCP2());
        copy.setArtCP3(this.getArtCP3());
        copy.setArtCP4(this.getArtCP4());
        copy.setArtCP5(this.getArtCP5());
        copy.setArtCP6(this.getArtCP6());
        copy.setArtCP7(this.getArtCP7());
        copy.setArtCP8(this.getArtCP8());
        copy.setReglasPreexistencia(this.getReglasPreexistencia());
        copy.setCondEspAsegurabilidad(this.getCondEspAsegurabilidad());
        copy.setExclusionesArtCG(this.getExclusionesArtCG());
        copy.setPeriodoCarencia(this.getPeriodoCarencia());
        copy.setNotificacion(this.getNotificacion());
        copy.setNotificacion1(this.getNotificacion1());
        copy.setNotificacion2(this.getNotificacion2());
        copy.setNotificacion3(this.getNotificacion3());

        return copy;
    }

}


