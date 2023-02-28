package heroku.huelladecarbono.service.CalculoDeHuellaService;


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;


public class FactoresDeEmision {
    HashMap<String, Double> fe = new HashMap<String, Double>();
    // HashMap<Clave,Fe>, Clave = Tipos de Consumo o medios de transporte
    private static FactoresDeEmision instance;

    public static FactoresDeEmision getInstance(){
        if(instance == null) instance = new FactoresDeEmision();
        return instance;
    }

    //Se pueden cargar FE con tipos de consumo o con medios de transporte


    public void setFE(String claveDeFE, Double factorDeEmision){
        fe.put(claveDeFE, factorDeEmision);
    }

    public Double getFE(String claveDeFE){
        return fe.get(claveDeFE);
    }

    public HashMap<String, Double> getFe() throws IOException {
        return this.cargaDeFactores();
    }

    public void setFe(HashMap<String, Double> fe) {
        this.fe = fe;
    }

    public void lecturaFactorEmision() throws IOException {
        String CONFIGFACTORES = "src\\main\\resources\\factoremision.properties";

        Properties property = new Properties();
        property.load(new FileReader(CONFIGFACTORES));

        property.entrySet().forEach(System.out::println);

    }
    public void lecturaFactorEmisionClave() throws IOException {
        String CONFIGFACTORES = "src\\main\\resources\\factoremision.properties";

        Properties property = new Properties();
        property.load(new FileReader(CONFIGFACTORES));

        property.keySet().forEach(System.out::println);

    }
    public void lecturaFactorEmisionValor() throws IOException {
        String CONFIGFACTORES = "src\\main\\resources\\factoremision.properties";

        Properties property = new Properties();
        property.load(new FileReader(CONFIGFACTORES));

        property.values().forEach(System.out::println);

    }

    //Carga los factores de emision desde factoremision.properties
    public HashMap<String, Double> cargaDeFactores() throws IOException {

        String CONFIGFACTORES = "src\\main\\resources\\factoremision.properties";

        Properties property = new Properties();
        property.load(new FileReader(CONFIGFACTORES));

        for (String key : property.stringPropertyNames()) {
            fe.put(key, Double.valueOf(property.get(key).toString()));
        }

        return fe;
    }


}
