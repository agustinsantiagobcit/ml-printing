package cl.metlife.printing.generation;

import cl.blueprintsit.framework.config.ConfigurationManager;
import cl.metlife.printing.domain.Document;
import cl.metlife.printing.managers.TemplateManager;
import cl.metlife.printing.persistence.dao.DocumentDAO;
import cl.metlife.saam.business.altiuz.AltiuzServiceManager;
import cl.metlife.saam.business.odload.ODLoadServiceManager;
import cl.metlife.saam.domain.templates.Template;
import cl.metlife.ws.clients.altiuz.Parameter;
import cl.metlife.ws.clients.altiuz.Parameters;
import cl.metlife.ws.clients.altiuz.QueryRequest;
import cl.metlife.ws.clients.altiuz.QueryResponse;
import cl.metlife.ws.clients.od.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
public class GenerationServiceManager {

    public static final String OD_LOAD_SERVICE_ADD_EXTRA_CHAR_AT_BEGINNING = "od.load.service.add.extra.char.at.beginning";
    public static final String OD_LOAD_SERVICE_CLEANUP_AT_FINISH = "od.load.service.cleanup.at.finish";
    public static final String OD_LOAD_SERVICE_HOST = "od.load.service.host";
    public static final String OD_LOAD_SERVICE_BIN_DIR = "od.load.service.bin.dir";
    public static final String OD_LOAD_SERVICE_CREDENTIAL = "od.load.service.password";
    public static final String OD_LOAD_SERVICE_USERNAME = "od.load.service.username";

    public static final String OD_SAAM_EXECUTION_WRITEFOLDER = "od.load.service.writefolder";
    public static final String OD_SAAM_EXECUTION_READFOLDER = "od.load.service.readfolder";

    public static final String OD_PRINTING_GROUP_NAME = "od.printing.group.name";
    public static final String ALTIUZ_PRINTING_TEMPLATE = "altiuz.printing.template";

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerationServiceManager.class);

    @EJB
    DocumentDAO documentDAO;

    @EJB
    AltiuzServiceManager altiuzServiceManager;

    @EJB
    TemplateManager templateManager;

    @EJB
    ConfigurationManager configurationManager;


    @EJB
    ODLoadServiceManager odLoadServiceManager;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public byte[] createNewPrint(Long documentId){
        Document document = documentDAO.getById(documentId);

        //mapa de parametros se obtiene desde OD a travez de altiuz
        HashMap parameterMap = getParameterMapFromOD(document);

        Template template = templateManager.getById(document.getProcess().getTemplateId());

        String xml = templateManager.getXML(template, parameterMap);

        Integer newPrintNumber = document.getPrints().size()+1;

        registerDataInOD(document, newPrintNumber, xml);

        return templateManager.getGeneratePDFBytes(xml);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public byte[] getPrint(Long documentId, Long printNumber){
        Document document = documentDAO.getById(documentId);

        byte[] dataFromOD = getBytesFromOD(document, printNumber);
        String xml = new String(dataFromOD);

        return templateManager.getGeneratePDFBytes(xml);
    }

    /**
     * registra nueva version de impresion en OD
     * @param document
     * @param newPrintNumber
     * @param xml
     */
    private void registerDataInOD(Document document, Integer newPrintNumber, String xml) {
        OdConfig odConfig = makeOdConfig();

        List<DataDescriptor> dataDescriptors = makeDescriptors(xml,document, newPrintNumber);

        try {
            odLoadServiceManager.getService().loadData(odConfig,dataDescriptors);
        } catch (Exception_Exception e) {
            throw new PrintingGenerationException("Error al llamar a OD",e);
        }
    }


    /**
     * genera objeto OdConfig para invocar a servicio
     * OdConfig representa la configuracion de On demand
     * se usan valores de la configuracion general de la aplicacion y de la configuracion particular del paso
     * @return
     */
    private OdConfig makeOdConfig() {
        ObjectFactory objectFactory = new ObjectFactory();
        OdConfig odConfig = objectFactory.createOdConfig();
        odConfig.setGroupName(configurationManager.getByKey(OD_PRINTING_GROUP_NAME).getValor());
        odConfig.setAddExtraCharAtBeginning(configurationManager.getByKey(OD_LOAD_SERVICE_ADD_EXTRA_CHAR_AT_BEGINNING).getValor().equals("true"));
        odConfig.setCleanupAtFinish(configurationManager.getByKey(OD_LOAD_SERVICE_CLEANUP_AT_FINISH).getValor().equals("true"));
        odConfig.setHost(configurationManager.getByKey(OD_LOAD_SERVICE_HOST).getValor());
        odConfig.setOnDemandBinDir(configurationManager.getByKey(OD_LOAD_SERVICE_BIN_DIR).getValor());
        odConfig.setPassword(configurationManager.getByKey(OD_LOAD_SERVICE_CREDENTIAL).getValor());
        odConfig.setUsername(configurationManager.getByKey(OD_LOAD_SERVICE_USERNAME).getValor());
        return odConfig;
    }



    private DataDescriptor makeDataDescriptor(String xml, Document document, Integer printNumber ) {
        ObjectFactory objectFactory = new ObjectFactory();

        DataDescriptor dataDescriptor = objectFactory.createDataDescriptor();

        String data = xml;

        IndexList indexList = makeIndexList( document, printNumber );

        dataDescriptor.setIndexList(indexList);
        dataDescriptor.setData(data);

        return dataDescriptor;
    }

    private IndexList makeIndexList(Document document, Integer printNumber ) {

        ObjectFactory objectFactory = new ObjectFactory();

        IndexList indexList = objectFactory.createIndexList();

        indexList.getIndex().add( makeIndex(objectFactory, "ID_PROCESO", document.getProcessId().toString()));
        indexList.getIndex().add( makeIndex(objectFactory, "RUT", document.getClientRut().toString()));
        indexList.getIndex().add( makeIndex(objectFactory, "ID_PLANTILLA", document.getProcess().getTemplateId().toString()));
        indexList.getIndex().add( makeIndex(objectFactory, "ID_DOCUMENTO", document.getId().toString()));
        indexList.getIndex().add( makeIndex(objectFactory, "ID_VERSION", printNumber.toString()));
        indexList.getIndex().add( makeIndex(objectFactory, "NRO_CERTIFICADO", printNumber.toString()));
        indexList.getIndex().add( makeIndex(objectFactory, "POLIZA", document.getProcess().getPolicyCode()));


        return indexList;
    }

    private Index makeIndex(ObjectFactory objectFactory, String name, String value) {
        Index index = objectFactory.createIndex();
        index.setName(name);
        index.setValue(value);
        return index;
    }


    private List<DataDescriptor> makeDescriptors(String xml, Document document, Integer printNumber ) {
        List<DataDescriptor> dataDescriptors = new ArrayList<DataDescriptor>();

            DataDescriptor dataDescriptor = makeDataDescriptor( xml,  document, printNumber );

            dataDescriptors.add(dataDescriptor);

        return dataDescriptors;
    }



    private HashMap getParameterMapFromOD(Document document) {
        try {
            Long numeroImpresion = 0L;
            LOGGER.debug("recuperando hashmap de datos de OD, doc id:{}",document.getId());
            byte[] dataFromOD = getBytesFromOD(document, numeroImpresion);
            String stringdeOD = new String(dataFromOD);

            LOGGER.debug("hashmap de od {}",stringdeOD);

            byte[] bytes = Base64.decodeBase64(stringdeOD);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objIn = null;

            objIn = new ObjectInputStream(byteArrayInputStream);

            return (HashMap) objIn.readObject();
        } catch (IOException e) {
            throw  new PrintingGenerationException("Error al crear Mapa de parametros",e);
        } catch (ClassNotFoundException e) {
            throw  new PrintingGenerationException("Error al crear Mapa de parametros",e);
        }

    }


    private byte[] getBytesFromOD(Document document, Long numeroImpresion) {
        LOGGER.debug("buscando impresion {} del documento {}",numeroImpresion,document.getId());

        QueryRequest queryRequest = makeQueryRequest(document,numeroImpresion);

        QueryResponse queryResponse = altiuzServiceManager.getService().query(queryRequest);
        return queryResponse.getReturn();
    }

    private QueryRequest makeQueryRequest( Document document,Long numeroCertificado) {
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setOperation("TXT"); // PDF POR DEFECTO
        queryRequest.setContext(configurationManager.getByKey(ALTIUZ_PRINTING_TEMPLATE).getValor());

        Parameters parameters = new Parameters();
        List<Parameter> parametersList = new ArrayList<Parameter>();

        parametersList.add(makeParameter("ID_PROCESO",document.getProcessId()+""));
        parametersList.add(makeParameter("RUT",document.getClientRut()));
        parametersList.add(makeParameter("POLIZA",document.getProcess().getPolicyCode()));
        parametersList.add(makeParameter("ID_PLANTILLA",document.getProcess().getTemplateId()+""));
        parametersList.add(makeParameter("ID_DOCUMENTO",document.getId()+""));
        parametersList.add(makeParameter("NRO_CERTIFICADO",numeroCertificado.toString()));
        parametersList.add(makeParameter("ID_VERSION", numeroCertificado.toString()));

        parameters.setParameter(parametersList);
        queryRequest.setParameters(parameters);

        return queryRequest;
    }

    private Parameter makeParameter(String name, String value) {
        Parameter parameter = new Parameter();

        parameter.setField(name);
        parameter.setValue(value);
        return parameter;
    }

}
