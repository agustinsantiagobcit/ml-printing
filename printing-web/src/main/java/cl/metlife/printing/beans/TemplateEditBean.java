package cl.metlife.printing.beans;


import cl.blueprintsit.framework.auth.AuthenticationBean;
import cl.metlife.printing.managers.ElementManager;
import cl.metlife.printing.managers.TemplateManager;
import cl.metlife.saam.domain.templates.*;
import cl.metlife.saam.web.controller.BaseBean;
import org.primefaces.event.ReorderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "templateEditBean")
@ViewScoped
public class TemplateEditBean extends BaseBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateEditBean.class);

    private Template plantilla;
    private Long idPlantilla;

    private List<ElementType> tipoElementoList;

    private List<ListType> listTypes;

    private List<TemplateElement> elementsToRemove;

    private boolean bl;
    private boolean br;


    protected static Map<Long,Template> openTemplates = new HashMap<Long, Template>();

    private boolean tl;
    private boolean tr;

    @EJB
    private TemplateManager templateManager;

    @EJB
    private ElementManager elementManager;

    @ManagedProperty(value = "#{clipboardBean}")
    private ClipboardBean clipboardBean;

    @ManagedProperty(value = "#{authenticationBean}")
    private AuthenticationBean authenticationBean;


    @PostConstruct
    public void loadElement(){
        listTypes = elementManager.getAllListTypes();

        tipoElementoList= elementManager.getAllElementTypes();

        elementsToRemove = new ArrayList<TemplateElement>();
    }

    public void init(){
        if(plantilla== null){
            if(idPlantilla == null || idPlantilla == -1L){
                plantilla = new Template();
                plantilla.setElements(new ArrayList<TemplateElement>());
                plantilla.setParameters(new ArrayList<Parameter>());
                templateManager.create(plantilla,authenticationBean.getLoggedUser().getUsername());
                idPlantilla = plantilla.getId();
            }else{
                plantilla=templateManager.getById(idPlantilla);
                plantilla.setElements((plantilla.getElements()!=null)?plantilla.getElements():new ArrayList<TemplateElement>());
                plantilla.setParameters((plantilla.getParameters()!=null)?plantilla.getParameters():new ArrayList<Parameter>());
            }
        }

        openTemplates.put(plantilla.getId(),plantilla);
    }
    public void save(){

        templateManager.parseParameters(plantilla);

        if(plantilla.getId()!=null){
            templateManager.update(plantilla,authenticationBean.getLoggedUser().getUsername());
            showInfo("Aviso", "Cambios guardados satisfactoriamente.");
        } else {
            templateManager.create(plantilla,authenticationBean.getLoggedUser().getUsername());
            showInfo("Éxito", "Se creó correctamente la plantilla.");
        }
    }

    public void elementsToRemoveMultiple(TemplateElement templateElement){
        if(!elementsToRemove.contains(templateElement)){
            elementsToRemove.add(templateElement);
        }else{
            elementsToRemove.remove(templateElement);
        }
    }

    public void removeAllElementListMultiple(){
        plantilla.getElements().removeAll(elementsToRemove);
        elementsToRemove= new ArrayList<TemplateElement>();
    }


    public void deleteElement(TemplateElement elementoPlantilla){
        plantilla.getElements().remove(elementoPlantilla);
    }
    public void copyElementAt(TemplateElement elementoPlantilla, int posicion){
        plantilla.getElements().add(posicion,elementoPlantilla.copy());
    }

    public void deleteElementList(ListItem item, ListElement lista){
        lista.getItems().remove(item);
        fixListOrder(lista);
    }

    public void addElementToList(TemplateElement lista){

            int items = 0;
            ListElement listElement = (ListElement) lista;
            if(listElement.getItems() != null)
                items= listElement.getItems().size();

            items ++;

            ListItem nuevoElementoLista= new ListItem();
            nuevoElementoLista.setText("Elemento "+ items);
            listElement.getItems().add(nuevoElementoLista);

            fixListOrder(listElement);

    }

    public void addColumn(Table tabla){
        TableColumn tableColumn = new TableColumn();
        tableColumn.setName("Columna " + (tabla.getColumns().size() + 1) );
        tabla.getColumns().add(tableColumn);

        for (TableRow tableRow : tabla.getRows()) {
            TableCell newCell = new TableCell();
            newCell.setTexto("");
            tableRow.getCells().add(newCell);
        }
    }

    public void addRow(Table tabla){

        TableRow tableRow = new TableRow();
        tableRow.setCells(new ArrayList<TableCell>());
        tabla.getRows().add(tableRow);

        for (TableColumn tableColumn : tabla.getColumns()) {
            TableCell newCell = new TableCell();
            newCell.setTexto("");
            tableRow.getCells().add(newCell);
        }

    }



    public void addDynamicColumn(DynamicTable tabla){
        TableColumn tableColumn = new TableColumn();
        tableColumn.setName("Columna " + (tabla.getColumns().size() + 1) );
        tabla.getColumns().add(tableColumn);

        for (TableRow tableRow : tabla.getRows()) {
            TableCell newCell = new TableCell();
            newCell.setTexto("");
            tableRow.getCells().add(newCell);
        }
    }

    public void addDynamicRow(DynamicTable tabla){

        TableRow tableRow = new TableRow();
        tableRow.setCells(new ArrayList<TableCell>());
        tabla.getRows().add(tableRow);

        for (TableColumn tableColumn : tabla.getColumns()) {
            TableCell newCell = new TableCell();
            newCell.setTexto("");
            tableRow.getCells().add(newCell);
        }

    }


    public void deleteRow(Table elementoTabla, TableRow elementoFila){
        elementoTabla.getRows().remove(elementoFila);
    }

    public void deleteColumn(Table table, TableColumn column){
        int colIndex = table.getColumns().indexOf(column);

        for (TableRow tableRow : table.getRows()) {
            tableRow.getCells().remove(colIndex);
        }

        table.getColumns().remove(column);
    }

    public void deleteDynamicColumn(DynamicTable table, TableColumn column){
        int colIndex = table.getColumns().indexOf(column);

        for (TableRow tableRow : table.getRows()) {
            tableRow.getCells().remove(colIndex);
        }

        table.getColumns().remove(column);
    }

    public void onElementReorder(ReorderEvent event) {

        event.getFromIndex();
        fixOrder();
    }

    public void reorderElements() {


        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer fromIndex = Integer.parseInt(params.get("fromIndex"));
        Integer toIndex = Integer.parseInt(params.get("toIndex"));

        TemplateElement movedElement = plantilla.getElements().remove(fromIndex.intValue());
        plantilla.getElements().add(toIndex,movedElement);

        fixOrder();
    }

    public void addElementsFromClipboard() {

        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String position = params.get("elementType");
        Integer index = Integer.parseInt(params.get("index"));

        TemplateElement templateElement = clipboardBean.getElementList().get(Integer.parseInt(position)).copy();

        plantilla.getElements().add((index==1 && plantilla.getElements().isEmpty())?0:index,templateElement);
        fixOrder();

    }

    public void addElementsFromClipboard(TemplateElement templateElement) {
        plantilla.getElements().add(templateElement.copy());
    }


    private TemplateElement createElementTemplateByType(ElementType type) {

        TemplateElement templateElement = null;

        if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_TITLE)) {
            Title elementoTitulo = new Title();
            elementoTitulo.setTexto("Titulo");
            templateElement = elementoTitulo;
        }
        if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_SUBTITLE)) {
            Subtitle subtitle = new Subtitle();
            subtitle.setText("Subtitulo");
            templateElement = subtitle;
        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_PARAGRAPH)) {
            Paragraph elementoParrafo = new Paragraph();
            elementoParrafo.setTexto("<p>Nuevo párrafo... <br /></p>");
            templateElement =  elementoParrafo;
        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_IMAGE)) {
            Image image = new Image();
            image.setImage("");
            image.setTamanioY(300L);
            templateElement =  image;
        }
        else if( type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_LIST)) {

            ListElement listElement = new ListElement();
            listElement.setListType(listTypes.get(0));

            listElement.setItems(new ArrayList<ListItem>());
            ListItem item = new ListItem();
            item.setText("Elemento 1");
            item.setOrder(1L);
            listElement.getItems().add(item);
            templateElement = listElement;

        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_TABLE)) {
            Table table = defaultTable();
            templateElement = table;

        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_DYNAMIC_TABLE)) {
            DynamicTable table = defaultDynamicTable();
            templateElement = table;

        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_SIGNING)){
            Signature signing = new Signature();
            signing.setName("Nombre");
            signing.setPosition("Cargo");
            signing.setInstitution("Institucion");

            templateElement = signing;
        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_DOUBLE_SIGNING)){
            SignatureDouble signing = new SignatureDouble();
            signing.setName1("Nombre 1");
            signing.setPosition1("Cargo 1");
            signing.setInstitution1("Institucion 1");

            signing.setName2("Nombre 2");
            signing.setPosition2("Cargo 2");
            signing.setInstitution2("Institucion 2");

            templateElement = signing;
        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_CUADRO)){
            Cuadro cuadro = new Cuadro();
            cuadro.setTexto("Texto");
            templateElement = cuadro;
        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_CARATULA_SALUD)){
            CaratulaSalud caratula = getDefaultCaratula();
            templateElement = caratula;
        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_TYPE_PAGE_BREAK)){
            PageBreak pageBreak = new PageBreak();
            templateElement = pageBreak;
        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_CARATULA_VIDA)){
            CaratulaVida caratula = getDefaultCaratulaVida();
            templateElement = caratula;
        }
        else if(type.getId() == Integer.parseInt(ElementType.ELEMENT_CARATULA_VIDA_CON_TABLA)){
            CaratulaVidaConTabla caratula = getDefaultCaratulaVidaConTabla();
            templateElement = caratula;
        }


        if(templateElement == null)
            return null;

        setElementParents(templateElement,type);

        return templateElement;
    }

    private CaratulaSalud getDefaultCaratula() {
        CaratulaSalud caratula = new CaratulaSalud();
        caratula.setCodigoSVSPoliza("");
        caratula.setNumPoliza("");
        caratula.setContratante("");
        caratula.setRutContratante("");
        caratula.setAsegurado("");
        caratula.setRutAsegurado("");
        caratula.setBeneficiario("");
        caratula.setRutBeneficiario("");
        caratula.setTipoRiesgoAsegurado("");
        caratula.setPoliza("");
        caratula.setVigenciaInicio("");
        caratula.setVigenciaTermino("");
        caratula.setRenovacionAutomatica("");
        caratula.setPrima("");
        caratula.setMoneda("");
        caratula.setPeriodoPago("");
        caratula.setCondiciones("");
        caratula.setComisionTotalCorrectoMonto("");
        caratula.setCorredorNoHayComision("");
        caratula.setArtCG1("");
        caratula.setArtCG2("");
        caratula.setArtCG3("");
        caratula.setArtCG4("");
        caratula.setArtCG5("");
        caratula.setArtCG6("");
        caratula.setArtCG7("");
        caratula.setArtCG8("");
        caratula.setArtCP1("");
        caratula.setArtCP2("");
        caratula.setArtCP3("");
        caratula.setArtCP4("");
        caratula.setArtCP5("");
        caratula.setArtCP6("");
        caratula.setArtCP7("");
        caratula.setArtCP8("");
        caratula.setReglasPreexistencia("");
        caratula.setCondEspAsegurabilidad("");
        caratula.setExclusionesArtCG("");
        caratula.setPeriodoCarencia("");
        caratula.setNotificacion("");
        caratula.setNotificacion1("");
        caratula.setNotificacion2("");
        caratula.setNotificacion3("");
        return caratula;
    }

    private CaratulaVida getDefaultCaratulaVida() {
        CaratulaVida caratula = new CaratulaVida();
        caratula.setCodigoSVSPoliza("");
        caratula.setNumPoliza("");
        caratula.setContratante("");
        caratula.setRutContratante("");
        caratula.setAsegurado("");
        caratula.setRutAsegurado("");
        caratula.setPoliza("");
        caratula.setVigenciaInicio("");
        caratula.setVigenciaTermino("");
        caratula.setRenovacionAutomatica("");
        caratula.setPrima("");
        caratula.setMoneda("");
        caratula.setPeriodoPago("");
        caratula.setCondiciones("");
        caratula.setComisionTotalCorrectoMonto("");
        caratula.setCorredorNoHayComision("");
        caratula.setArtCG1("");
        caratula.setArtCG2("");
        caratula.setArtCG3("");
        caratula.setArtCG4("");
        caratula.setArtCG5("");
        caratula.setArtCG6("");
        caratula.setArtCG7("");

        caratula.setArtCP1("");
        caratula.setArtCP2("");
        caratula.setArtCP3("");
        caratula.setArtCP4("");
        caratula.setArtCP5("");
        caratula.setArtCP6("");
        caratula.setArtCP7("");

        caratula.setCondEspAsegurabilidad("");
        caratula.setExclusionesArtCG("");
        caratula.setPeriodoCarencia("");
        caratula.setNotificacion("");
        caratula.setNotificacion1("");
        caratula.setNotificacion2("");
        caratula.setNotificacion3("");


        caratula.setCoberMuerte("");
        caratula.setCoberMuerteMonto("");
        caratula.setCoberMuerteMoneda("");
        caratula.setCoberMuerteArtCg("");
        caratula.setCoberMuerteArtCp("");
        caratula.setCoberInvali("");
        caratula.setCoberInvaliMonto("");
        caratula.setCoberInvaliMoneda("");
        caratula.setCoberInvaliArtCg("");
        caratula.setCoberInvaliArtCp("");
        caratula.setCoberSobrevivencia("");
        caratula.setCoberSobrevivenciaMonto("");
        caratula.setCoberSobrevivenciaMoneda("");
        caratula.setCoberSobrevivenciaArtCg("");
        caratula.setCoberSobrevivenciaArtCp("");
        caratula.setCoberMuerteAccidental("");
        caratula.setCoberMuerteAccidentalMonto("");
        caratula.setCoberMuerteAccidentalMoneda("");
        caratula.setCoberMuerteAccidentalArtCg("");
        caratula.setCoberMuerteAccidentalArtCp("");
        caratula.setCoberOtras("");

        return caratula;
    }
    private CaratulaVidaConTabla getDefaultCaratulaVidaConTabla() {
        CaratulaVidaConTabla caratula = new CaratulaVidaConTabla();
        caratula.setCodigoSVSPoliza("");
        caratula.setNumPoliza("");
        caratula.setContratante("");
        caratula.setRutContratante("");
        caratula.setAsegurado("");
        caratula.setRutAsegurado("");
        caratula.setPoliza("");
        caratula.setVigenciaInicio("");
        caratula.setVigenciaTermino("");
        caratula.setRenovacionAutomatica("");
        caratula.setPrima("");
        caratula.setMoneda("");
        caratula.setPeriodoPago("");
        caratula.setCondiciones("");
        caratula.setComisionTotalCorrectoMonto("");
        caratula.setCorredorNoHayComision("");
        caratula.setArtCG1("");
        caratula.setArtCG2("");
        caratula.setArtCG3("");
        caratula.setArtCG4("");
        caratula.setArtCG5("");
        caratula.setArtCG6("");
        caratula.setArtCG7("");

        caratula.setArtCP1("");
        caratula.setArtCP2("");
        caratula.setArtCP3("");
        caratula.setArtCP4("");
        caratula.setArtCP5("");
        caratula.setArtCP6("");
        caratula.setArtCP7("");

        caratula.setCondEspAsegurabilidad("");
        caratula.setExclusionesArtCG("");
        caratula.setPeriodoCarencia("");
        caratula.setNotificacion("");
        caratula.setNotificacion1("");
        caratula.setNotificacion2("");
        caratula.setNotificacion3("");

        caratula.setCoberMuerte("");
        caratula.setCoberMuerteMonto("");
        caratula.setCoberMuerteMoneda("");
        caratula.setCoberMuerteArtCg("");
        caratula.setCoberMuerteArtCp("");
        caratula.setCoberInvali("");
        caratula.setCoberInvaliMonto("");
        caratula.setCoberInvaliMoneda("");
        caratula.setCoberInvaliArtCg("");
        caratula.setCoberInvaliArtCp("");
        caratula.setCoberSobrevivencia("");
        caratula.setCoberSobrevivenciaMonto("");
        caratula.setCoberSobrevivenciaMoneda("");
        caratula.setCoberSobrevivenciaArtCg("");
        caratula.setCoberSobrevivenciaArtCp("");
        caratula.setCoberMuerteAccidental("");
        caratula.setCoberMuerteAccidentalMonto("");
        caratula.setCoberMuerteAccidentalMoneda("");
        caratula.setCoberMuerteAccidentalArtCg("");
        caratula.setCoberMuerteAccidentalArtCp("");
        caratula.setCoberOtras("");

        caratula.setPrima("");
        caratula.setPrima1("");
        caratula.setPrima2("");
        caratula.setPrima3("");
        caratula.setPlan("");

        return caratula;
    }
    private Table defaultTable(){
        Table table = new Table();
        table.setRows(new ArrayList<TableRow>());
        table.setColumns(new ArrayList<TableColumn>());

        addColumn(table);
        addColumn(table);
        addRow(table);
        addRow(table);

        return table;
    }

    private DynamicTable defaultDynamicTable(){
        DynamicTable table = new DynamicTable();
        table.setRows(new ArrayList<TableRow>());
        table.setColumns(new ArrayList<TableColumn>());

        addDynamicColumn(table);
        addDynamicColumn(table);
        addDynamicRow(table);

        return table;
    }

    private void setElementParents(TemplateElement elementoPlantilla, ElementType elementType){
        elementoPlantilla.setTypeId(elementType.getId());
        elementoPlantilla.setType(elementType);
        elementoPlantilla.setOrden(plantilla.getElements().size()+1L);
    }

    public Template getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Template plantilla) {
        this.plantilla = plantilla;
    }

    public Long getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(Long idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public List<ElementType> getTipoElementoList() {
        return tipoElementoList;
    }

    public ListElement el(){
        return null;
    }

    public void addElement(ElementType elementType) {
        TemplateElement templateElement = createElementTemplateByType(elementType);
        plantilla.getElements().add(templateElement);
        fixOrder();

    }

    private void fixOrder(){
        Long order = 0L;
        for (TemplateElement templateElement : plantilla.getElements()) {
            templateElement.setOrden(order);
            order++;
        }
    }


    private void fixListOrder(ListElement listElement){
        Long order = 0L;
        for (ListItem listItem : listElement.getItems()) {
            listItem.setOrder(order);
            order++;
        }
    }


    public List<ListType> getListTypes() {
        return listTypes;
    }

    public void setListTypes(List<ListType> listTypes) {
        this.listTypes = listTypes;
    }

    public String removeP(String stringo){
        if(stringo==null)
            return null;

        return stringo.replace("<p>","").replace("</p>","");
    }


    public boolean isBl() {
        return bl;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    public boolean isBr() {
        return br;
    }

    public void setBr(boolean br) {
        this.br = br;
    }

    public boolean isTl() {
        return tl;
    }

    public void setTl(boolean tl) {
        this.tl = tl;
    }

    public boolean isTr() {
        return tr;
    }

    public void setTr(boolean tr) {
        this.tr = tr;
    }

    public boolean isNullBL(){
        boolean isNull= true;
        if((plantilla.getLogoBL()!=null && plantilla.getLogoBL().length()>0)
        || (plantilla.getTextoBL()!=null && plantilla.getTextoBL().length()>0)){
                isNull= false;
        }
        return isNull;
    }

    public boolean isNullImageBL() {
        boolean isNull = true;
        if (plantilla.getLogoBL() != null) {
            if (plantilla.getLogoBL().length() > 0) {
                isNull = false;
            }
        }
        return isNull;
    }

    public boolean isNullImageBR() {
        boolean isNull = true;
        if (plantilla.getLogoBR() != null) {
            if (plantilla.getLogoBR().length() > 0) {
                isNull = false;
            }
        }
        return isNull;
    }

    public boolean isNullBR(){
        boolean isNull= isNullImageBR();
        if(plantilla.getTextoBR()!=null){
            if(plantilla.getTextoBR().length()>0){
                isNull= false;
            }
        }
        return isNull;
    }

    public boolean isNullImageTL() {
        boolean isNull = true;
        if (plantilla.getLogoTL() != null) {
            if (plantilla.getLogoTL().length() > 0) {
                isNull = false;
            }
        }
        return isNull;
    }
    public boolean isNullTL(){
        boolean isNull= isNullImageTL();
        if(plantilla.getTextoTL()!=null){
            if(plantilla.getTextoTL().length()>0){
                isNull = false;
            }
        }
        return isNull;
    }

    public boolean isNullImageTR() {
        boolean isNull = true;
        if (plantilla.getLogoTR() != null) {
            if (plantilla.getLogoTR().length() > 0) {
                isNull = false;
            }
        }
        return isNull;
    }
    public boolean isNullTR(){
        boolean isNull = isNullImageTR();
        if(plantilla.getTextoTR()!=null){
            if(plantilla.getTextoTR().length()>0){
                isNull = false;
            }
        }
        return isNull;
    }


    public void paletteToDocument(){
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String elementTypeString = params.get("elementType");
        Integer index = Integer.parseInt(params.get("index"));


        for (ElementType elementType : tipoElementoList) {
            if(elementType.getNombre().equals(elementTypeString)) {
                TemplateElement elementTemplateByType = createElementTemplateByType(elementType);
                plantilla.getElements().add((index==1 && plantilla.getElements().isEmpty())?0:index,elementTemplateByType);
                fixOrder();
            }
        }

    }

    public ClipboardBean getClipboardBean() {
        return clipboardBean;
    }

    public void setClipboardBean(ClipboardBean clipboardBean) {
        this.clipboardBean = clipboardBean;
    }

    public List<TemplateElement> getElementsToRemove() {
        return elementsToRemove;
    }

    public void setElementsToRemove(List<TemplateElement> elementsToRemove) {
        this.elementsToRemove = elementsToRemove;
    }


    public String getXML(){
        return templateManager.getXML(this.plantilla, null);
    }

    public AuthenticationBean getAuthenticationBean() {
        return authenticationBean;
    }

    public void setAuthenticationBean(AuthenticationBean authenticationBean) {
        this.authenticationBean = authenticationBean;
    }
}
