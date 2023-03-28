package eve.wajifeng.industryApp.mvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eve.wajifeng.industryApp.json.EMPrices;
import eve.wajifeng.industryApp.yaml.blueprints.Blueprints;
import eve.wajifeng.industryApp.yaml.blueprints.Materials;
import eve.wajifeng.industryApp.yaml.blueprints.Products;
import eve.wajifeng.industryApp.yaml.typeIDs.TypeIDs;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {

    private static Model model = null;
    private ProductedBlueprint productedBlueprint;
    private List<ComponentBlueprint> componentBlueprintList;
    private Total total;
    private boolean sub = false;
    private int te = 0;
    private int me = 0;
    private int subTE = 0;
    private int subME = 0;
    private double finalTE;
    private double finalME;
    private double finalSubTE;
    private double finalSubME;
    private int station = 30000142;
    private String count = "";

    private final List<String> comboboxList;
    private final List<TypeIDs> typeIDsList;
    private final List<Blueprints> blueprintsList;
    private final HashMap<String, Integer> stationHashMap;

    private Model(){
        typeIDsList = getTypeIDs();
        comboboxList = typeIDsGetList();
        blueprintsList = getBlueprints();
        stationHashMap = getStationsHashMap();
    }

    public static Model getInstance(){
        if(model == null){
            model = new Model();
        }

        return model;
    }


    //////////////////////////////// GET BLUEPRINTS LIST //////////////////////////////
    private List<Blueprints> getBlueprints(){

        List<Blueprints> list = new ArrayList<>();

        LoaderOptions loaderOptions = new LoaderOptions();
        Yaml yaml = new Yaml(new Constructor(Blueprints.class,loaderOptions));
        InputStream inputStream1 = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yaml/blueprints/blueprints1.yaml");
        InputStream inputStream2 = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yaml/blueprints/blueprints2.yaml");
        InputStream inputStream3 = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yaml/blueprints/blueprints3.yaml");


        for (Object o : yaml.loadAll(inputStream1)) {
            Blueprints t = (Blueprints) o;
            list.add(t);
        }

        for (Object o : yaml.loadAll(inputStream2)) {
            Blueprints t = (Blueprints) o;
            list.add(t);
        }

        for (Object o : yaml.loadAll(inputStream3)) {
            Blueprints t = (Blueprints) o;
            list.add(t);
        }

        return list;
    }


    //////////////////////////////// GET TYPEIDS LIST //////////////////////////////
    private List<TypeIDs> getTypeIDs(){

        List<TypeIDs> list = new ArrayList<>();

        LoaderOptions loaderOptions = new LoaderOptions();
        Yaml yaml = new Yaml(new Constructor(TypeIDs.class,loaderOptions));
        InputStream inputStream1 = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yaml/typeIDs/typeIDs1.yaml");
        InputStream inputStream2 = this.getClass()
                .getClassLoader()
                .getResourceAsStream("yaml/typeIDs/typeIDs2.yaml");


        for (Object o : yaml.loadAll(inputStream1)) {
            TypeIDs t = (TypeIDs) o;
            list.add(t);
        }

        for (Object o : yaml.loadAll(inputStream2)) {
            TypeIDs t = (TypeIDs) o;
            list.add(t);
        }

        return list;
    }

    /////////////////////// LIST OF ALL USED ID //////////
    private List<Integer> usefullID(){

        getInstance();
        List<Integer> list = new ArrayList<>();

        for(Blueprints bp : blueprintsList){
            if(!list.contains(bp.getBlueprintTypeID()))
                list.add(bp.getBlueprintTypeID());

            if(bp.getActivities().getManufacturing() != null && bp.getActivities().getManufacturing().getProducts() != null) {
                for (Products p : bp.getActivities().getManufacturing().getProducts()) {
                    if(!list.contains(p.getTypeID()))
                        list.add(p.getTypeID());
                }
            }

            if(bp.getActivities().getManufacturing() != null && bp.getActivities().getManufacturing().getMaterials() != null) {
                for (Materials m : bp.getActivities().getManufacturing().getMaterials()) {
                    if(!list.contains(m.getTypeID()))
                        list.add(m.getTypeID());
                }
            }
        }

        return list;
    }


    ///////////////////////  CREATING HASHMAP FOR STATIONS NAME + ID ////////////////////////////////
    private HashMap<String, Integer> getStationsHashMap(){

        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("Jita", 30000142);
        hashmap.put("Amarr", 30002187);
        hashmap.put("Rens", 30002510);
        hashmap.put("Hek", 30002053);
        hashmap.put("Dodixie", 30002659);

        return hashmap;
    }


    /////////////////////// FILTER BLUEPRINTS BY SEARCH //////////
    protected List<String> typeIDsGetFilteredList(String s){

        List<String> filteredList= new ArrayList<>();

        for(String t : comboboxList){
            String compare = t.toLowerCase();
            s = s.toLowerCase();
            if(compare.contains(s)){
                filteredList.add(t);
            }
        }

        return filteredList;
    }


    //////////////////////////////// GET TYPEIDS NAME LIST //////////////////////////////
    private List<String> typeIDsGetList(){

        List<String> list = new ArrayList<>();

        for(TypeIDs t : typeIDsList){
            if(t.getName().contains("Blueprint")) {
                list.add(t.getName());
            }
        }

        return list;
    }


    //////////////////////////////// GET TYPEIDS ID BY NAME //////////////////////////////
    private int typeIDsGetIDByName(String name){

        int res = 0;

        for(TypeIDs t : typeIDsList){
            if (t.getName().equals(name)) {
                res = t.getId();
                return res;
            }
        }

        return res;
    }


    //////////////////////////////// GET TYPEIDS NAME BY ID //////////////////////////////
    private String typeIDsGetNameByID(int id){

        String res = "";

        for(TypeIDs t : typeIDsList){
            if (t.getId() == id) {
                res = t.getName();
                return res;
            }
        }

        return res;
    }


    //////////////////////////////// GET TYPEIDS VOLUME BY NAME //////////////////////////////
    private double typeIDsGetVolumeByName(String name){

        double res = 0.0;
        for (TypeIDs t : typeIDsList) {
            if (t.getName().equals(name)) {
                res = t.getVolume();
                return res;
            }
        }

        return res;
    }


    protected void bpFinalFormated(String bpName) throws IOException {
        //// RETURN BLUEPRINTFINAL ////
        productedBlueprint = bpProducted(bpName);

        componentBlueprintList = bpComponents(bpName);

        try{
            emUpdatePrices();
        }catch (Exception e){
            jsonPriceUpdate(componentBlueprintList);
        }

        formatFloat(componentBlueprintList);

        formatPricesVolume();

        count = "";

        setChanged();
        notifyObservers(productedBlueprint);
    }


    protected void bpSubFinalFormated(String bpName) throws IOException {
        //// RETURN BLUEPRINTFINAL ////
        productedBlueprint = bpProducted(bpName);

        componentBlueprintList = bpComponents(bpName);

        bpSubComponents();

        try{
            emUpdatePricesSubComp();
        }catch (Exception e){
            jsonPriceUpdateSubComp(componentBlueprintList);
        }

        formatFloat(componentBlueprintList);

        formatPricesVolume();

        count = "";

        setChanged();
        notifyObservers(productedBlueprint);
    }


    /////////////////////// RETURN BLUEPRINT PRODUCTED ////////////////
    private ProductedBlueprint bpProducted(String bpName){

        ProductedBlueprint resultProducted = new ProductedBlueprint();

        int componentID = typeIDsGetIDByName(bpName);


        for (Blueprints b : blueprintsList) {

            if (b.getBlueprintTypeID() == componentID) {
                resultProducted.setBlueprintTypeID(componentID);
                resultProducted.setProductedName(bpName.substring(0, bpName.length() - 10));
                resultProducted.setMaxProductionLimit(b.getMaxProductionLimit());
                resultProducted.setTime(b.getActivities().getManufacturing().getTime());
                resultProducted.setProductedVolume(typeIDsGetVolumeByName(bpName.substring(0, bpName.length() - 10)));

                for (Products p : b.getActivities().getManufacturing().getProducts()) {

                    resultProducted.setProductedQuantity(p.getQuantity());
                    resultProducted.setProductedTypeID(p.getTypeID());
                    resultProducted.setProductedImage("images\\" + p.getTypeID() + "_32.png");

                }
            }
        }
        return resultProducted;
    }


    /////////////////////// RETURN BLUEPRINTFINAL ////////////////
    private List<ComponentBlueprint> bpComponents(String bpName){

        ComponentBlueprint componentBlueprint = new ComponentBlueprint();
        List<ComponentBlueprint> resultList = new ArrayList<>();

        finalME = 1 - ((double)me / 100);
        total = new Total();

        int componentID = typeIDsGetIDByName(bpName);


        for (Blueprints b : blueprintsList) {

            if (b.getBlueprintTypeID() == componentID) {

                for (Materials m : b.getActivities().getManufacturing().getMaterials()) {
                    componentBlueprint.setMaterialQuantity(m.getQuantity());
                    componentBlueprint.setMaterialTypeID(m.getTypeID());
                    componentBlueprint.setMaterialImage("images\\" + m.getTypeID() + "_32.png");
                    String componentName = typeIDsGetNameByID(m.getTypeID());
                    componentBlueprint.setMaterialName(componentName);

                    // UPDATING QUANTITY ACCORDING TO ME
                    componentBlueprint.setMaterialQuantity((int) Math.ceil(componentBlueprint.getMaterialQuantity() * finalME));

                    // UPDATING VOLUME ACCORDING TO QUANTITY
                    double componentVolume = typeIDsGetVolumeByName(componentName);
                    componentBlueprint.setMaterialVolume(componentVolume * componentBlueprint.getMaterialQuantity());

                    resultList.add(componentBlueprint);
                    componentBlueprint = new ComponentBlueprint();
                }
            }
        }
        return resultList;
    }


    /////////////////////// RETURN BLUEPRINTFINAL ////////////////
    private List<ComponentBlueprint> bpComponentsForSub(String bpName){

        ComponentBlueprint componentBlueprint = new ComponentBlueprint();
        List<ComponentBlueprint> resultList = new ArrayList<>();

        total = new Total();

        int componentID = typeIDsGetIDByName(bpName);


        for (Blueprints b : blueprintsList) {

            if (b.getBlueprintTypeID() == componentID) {

                for (Materials m : b.getActivities().getManufacturing().getMaterials()) {
                    componentBlueprint.setMaterialQuantity(m.getQuantity());
                    componentBlueprint.setMaterialTypeID(m.getTypeID());
                    componentBlueprint.setMaterialImage("images\\" + m.getTypeID() + "_32.png");
                    String componentName = typeIDsGetNameByID(m.getTypeID());
                    componentBlueprint.setMaterialName(componentName);

                    // UPDATING VOLUME ACCORDING TO QUANTITY
                    double componentVolume = typeIDsGetVolumeByName(componentName);
                    componentBlueprint.setMaterialVolume(componentVolume * componentBlueprint.getMaterialQuantity());

                    resultList.add(componentBlueprint);
                    componentBlueprint = new ComponentBlueprint();
                }
            }
        }
        return resultList;
    }


    ////////////////////////////////   Adding Sub-Comp   ///////////////////////////////
    private void bpSubComponents(){
        List<ComponentBlueprint> subComponents;
        List<ComponentBlueprint> tempList = new ArrayList<>();

        finalSubME = 1 - ((double)subME / 100);
        int quant = 0;
        String s;

        tempList.addAll(componentBlueprintList);

        componentBlueprintList.clear();

        for (ComponentBlueprint cbp : tempList) {

            componentBlueprintList.add(cbp);
            count += "a";

            s = cbp.getMaterialName() + " Blueprint";

            subComponents = bpComponentsForSub(s);


            if(!subComponents.isEmpty()){
                count = count.substring(0, count.length() - 1);
                count += "e";

                for(ComponentBlueprint sub : subComponents){

                    int componentID = typeIDsGetIDByName(s);

                    for (Blueprints b : blueprintsList) {
                        if (b.getBlueprintTypeID() == componentID) {
                            for(Products p : b.getActivities().getManufacturing().getProducts()){
                                quant = p.getQuantity();
                            }
                        }
                    }

                    sub.setMaterialQuantity((int) Math.ceil((float)cbp.getMaterialQuantity() / quant) * sub.getMaterialQuantity());

                    // UPDATING QUANTITY ACCORDING TO ME
                    if(!(cbp.getMaterialQuantity() == sub.getMaterialQuantity()))
                        sub.setMaterialQuantity((int) Math.ceil(sub.getMaterialQuantity() * finalSubME));

                    // UPDATING VOLUME ACCORDING TO QUANTITY
                    String componentName = typeIDsGetNameByID(sub.getMaterialTypeID());
                    double componentVolume = typeIDsGetVolumeByName(componentName);
                    sub.setMaterialVolume(componentVolume * sub.getMaterialQuantity());

                    sub.setMaterialName("|____"+ sub.getMaterialName());
                    sub.setSub(true);
                    componentBlueprintList.add(sub);
                    count += "b";
                }
            }
        }
    }


    //////////////////////////////  JSON LIST FOR MARKET PRICE + UPDATING PRICES   //////////////////
    private void jsonPriceUpdate(List<ComponentBlueprint> itInfo){
        //        JSON LIST FOR MARKET PRICE
        String userprofile = System.getenv("USERPROFILE");
        File prices = new File(userprofile + "\\IndustryApp\\prices.json");

        //        UPDATING BASEPRICE, MULTPRICE AND TOTALPRICE VALUE WITH JSON
        try {
            List<EMPrices> list = new ObjectMapper().readValue(prices, new TypeReference<>() {
            });

            for (ComponentBlueprint bp : itInfo) {
                for (EMPrices json : list) {
                    if (bp.getMaterialTypeID() == json.getType_id()) {
                        bp.setBasePrice(json.getMin_price());
                    }
                    if(productedBlueprint.getProductedTypeID() == json.getType_id()){
                        productedBlueprint.setProductedPrice(json.getMin_price());
                    }
                }
            }

            for (ComponentBlueprint cbp : componentBlueprintList){
                cbp.setMultPrice(cbp.getMaterialQuantity()*cbp.getBasePrice());

                //////////////// UPDATING TOTAL PRICE //////////////////////
                total.setTotalPrice(total.getTotalPrice() + cbp.getMultPrice());
                ////////////////  UPDATING TOTAL QUANTITY //////////////////////
                total.setTotalVolume(total.getTotalVolume() + cbp.getMaterialVolume());
            }

        }catch (Exception e){
            System.out.println("Cannot find prices.json file or no Blueprint selected");
        }
    }


    //////////////////////////////  JSON LIST FOR MARKET PRICE + UPDATING PRICES FOR SUBCOMP  //////////////////
    private void jsonPriceUpdateSubComp(List<ComponentBlueprint> itInfo){
        //        JSON LIST FOR MARKET PRICE
        String userprofile = System.getenv("USERPROFILE");
        File prices = new File(userprofile + "\\IndustryApp\\prices.json");

        //        UPDATING BASEPRICE, MULTPRICE AND TOTALPRICE VALUE WITH JSON
        try {
            List<EMPrices> list = new ObjectMapper().readValue(prices, new TypeReference<>() {
            });

            for (ComponentBlueprint bp : itInfo) {
                for (EMPrices json : list) {
                    if (bp.getMaterialTypeID() == json.getType_id()) {
                        bp.setBasePrice(json.getMin_price());
                    }
                    if(productedBlueprint.getProductedTypeID() == json.getType_id()){
                        productedBlueprint.setProductedPrice(json.getMin_price());
                    }
                }
            }

            StringReader stringReader = new StringReader(count);

            for (ComponentBlueprint cbp : componentBlueprintList){
                cbp.setMultPrice(cbp.getMaterialQuantity()*cbp.getBasePrice());

                //////////////// UPDATING TOTAL PRICE //////////////////////

                String chr = Character.toString(stringReader.read());

                if(chr.equals("e")){
                    cbp.setMultPrice(0);
                    cbp.setMaterialVolume(0);
                }

                total.setTotalPrice(total.getTotalPrice() + cbp.getMultPrice());
                total.setTotalVolume(total.getTotalVolume() + cbp.getMaterialVolume());
            }

            stringReader.close();

        }catch (Exception e){
            System.out.println("Cannot find prices.json file");
        }
    }


    //////////////////////////  UPDATE PRICES FROM EVE MARKETER + MULT PRICES + TOTAL  ///////////////////
    private void emUpdatePrices() throws IOException, SAXException, ParserConfigurationException {

        int count = 1;
        String id = String.valueOf(productedBlueprint.getProductedTypeID());

        for(ComponentBlueprint cp : componentBlueprintList) {
            id += "," + cp.getMaterialTypeID();
            count ++;
        }

        String url = "https://api.evemarketer.com/ec/marketstat?typeid=" + id + "&usesystem=" + station;
        URL file = new URL(url);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file.openStream());
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("sell");

        for(int i = 0 ; i < count ; i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            String res = elem.getElementsByTagName("min").item(0).getTextContent();
            float res2 = Float.parseFloat(res);

            if(i == 0)
                productedBlueprint.setProductedPrice(res2);
            else
                componentBlueprintList.get(i-1).setBasePrice(res2);
        }

        total.setTotalPrice(0);
        for (ComponentBlueprint cbp : componentBlueprintList){
            cbp.setMultPrice(cbp.getMaterialQuantity()*cbp.getBasePrice());

            //////////////// UPDATING TOTAL PRICE //////////////////////
            total.setTotalPrice(total.getTotalPrice() + cbp.getMultPrice());
            ////////////////  UPDATING TOTAL QUANTITY //////////////////////
            total.setTotalVolume(total.getTotalVolume() + cbp.getMaterialVolume());
        }
    }


    //////////////////////////  UPDATE PRICES FROM EVE MARKETER + MULT PRICES + TOTAL  ///////////////////
    private void emUpdatePricesSubComp() throws IOException, SAXException, ParserConfigurationException {

        int size = 1;
        String id = String.valueOf(productedBlueprint.getProductedTypeID());

        for(ComponentBlueprint cp : componentBlueprintList) {
            id += "," + cp.getMaterialTypeID();
            size ++;
        }

        String url = "https://api.evemarketer.com/ec/marketstat?typeid=" + id + "&usesystem=" + station;
        URL file = new URL(url);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file.openStream());
        NodeList nodeList = document.getDocumentElement().getElementsByTagName("sell");

        for(int i = 0 ; i < size ; i++) {
            Node node = nodeList.item(i);
            Element elem = (Element) node;
            String res = elem.getElementsByTagName("min").item(0).getTextContent();
            float res2 = Float.parseFloat(res);

            if(i == 0)
                productedBlueprint.setProductedPrice(res2);
            else
                componentBlueprintList.get(i-1).setBasePrice(res2);
        }

        StringReader stringReader = new StringReader(count);

        total.setTotalPrice(0);
        for (ComponentBlueprint cbp : componentBlueprintList){
            cbp.setMultPrice(cbp.getMaterialQuantity()*cbp.getBasePrice());

            //////////////// UPDATING TOTAL PRICE //////////////////////

            String chr = Character.toString(stringReader.read());

            if(chr.equals("e")){
                cbp.setMultPrice(0);
                cbp.setMaterialVolume(0);
            }

            total.setTotalPrice(total.getTotalPrice() + cbp.getMultPrice());
            total.setTotalVolume(total.getTotalVolume() + cbp.getMaterialVolume());
        }

        stringReader.close();
    }


    //////////////////////////// UPDATE JSON PRICES WITH EM ON START ///////////////////////////////
    protected void updateJsonEM() throws IOException {
        updateJsonEM1();
        updateJsonEM2();
    }


    private void updateJsonEM1() throws IOException {

        int count = 1;
        int subcount = 1;
        int maxcount = 200;
        int modres;

        String userprofile = System.getenv("USERPROFILE");
        Files.createDirectories(Paths.get(userprofile + "\\IndustryApp"));

        String id ="";

        String url;
        StringBuilder response = new StringBuilder();
        response.append("[");
        URL file;

        List<Integer> list = usefullID();

        for(Integer tID : list) {

            modres = count%maxcount;

            if(modres == 1) {
                id += tID;
                subcount++;
            }else if(modres == 0){
                id += "," + tID;
                try {
                    url = "https://api.evemarketer.com/ec/marketstat?typeid=" + id + "&usesystem=" + station;
                    file = new URL(url);
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                    Document document = documentBuilder.parse(file.openStream());
                    NodeList nodeList = document.getDocumentElement().getElementsByTagName("sell");

                    for(int i = 0 ; i < subcount ; i++) {
                        Node node = nodeList.item(i);
                        Element elem = (Element) node;
                        String res = elem.getElementsByTagName("min").item(0).getTextContent();
                        response.append("{\"min_price\":" + res + ",\"type_id\":}," + System.lineSeparator());
                    }

                    id ="";
                    subcount = 1;

                }catch (Exception e){
                    System.out.println("Cannot update prices in updateJsonEM()");
                }
            }else {
                id += "," + tID;
                subcount++;
            }

            count ++;
        }

        try {
            url = "https://api.evemarketer.com/ec/marketstat?typeid=" + id + "&usesystem=" + station;
            file = new URL(url);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file.openStream());
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("sell");

            subcount = list.size()%200;

            for(int i = 0 ; i < subcount ; i++) {
                Node node = nodeList.item(i);
                Element elem = (Element) node;
                String res = elem.getElementsByTagName("min").item(0).getTextContent();

                response.append("{\"min_price\":" + res + ",\"type_id\":}," + System.lineSeparator());
            }

        }catch (Exception e){
            System.out.println("Cannot update prices in updateJsonEM()");
        }

        response.append("]");

        File prices = new File(userprofile + "\\IndustryApp\\prices.json");
        String res = response.substring(0, response.length() - 4) + "]";

        try (FileWriter writer = new FileWriter(prices)) {
            writer.write(res);
        }
    }


    private void updateJsonEM2(){

        String userprofile = System.getenv("USERPROFILE");
        File prices = new File(userprofile + "\\IndustryApp\\prices.json");

        List<Integer> list = usefullID();
        int id = 0;

        StringBuilder originalContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(prices))) {
            String line = reader.readLine();
            while (line != null) {

                line = line.substring(0, line.length() - 2) + list.get(id) + "},";

                originalContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
                id++;
            }
            String newContent = originalContent.substring(0,originalContent.length() - 3) + "]";
            try (FileWriter writer = new FileWriter(prices)) {
                writer.write(newContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /////////  UPDATE FLOAT TO 0.2F AND REMOVE EXPONENT FOR BASEPRICE, MULTPRICE AND TOTALPRICE  //////////////
    private void formatFloat(List<ComponentBlueprint> itInfo){
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(20);
        String str;

        for(ComponentBlueprint bp : itInfo){


            try {
                str = String.format("%.2f", bp.getBasePrice()).replaceFirst(",", ".");
                bp.setBasePriceString(str);
            }catch (Exception e){
                System.out.println("format error11");
            }

            try {
                str = String.format("%.2f", bp.getMultPrice()).replaceFirst(",", ".");
                bp.setMultPriceString(str);
            }catch (Exception e){
                System.out.println("format error22");
            }

            try {
                str = String.format("%.2f", bp.getMaterialVolume()).replaceFirst(",", ".");
                bp.setMaterialVolumeString(str);
            }catch (Exception e){
                System.out.println("format error33");
            }
        }

        ////////////////// UPDATING FOR PRODUCED ITEM /////////////////

        try {
            str = String.format("%.2f", productedBlueprint.getProductedPrice()).replaceFirst(",", ".");
            productedBlueprint.setProductedPriceString(str);
        }catch (Exception e){
            System.out.println("format error1");
        }

        try {
            str = String.format("%.2f", productedBlueprint.getProductedVolume()).replaceFirst(",", ".");
            productedBlueprint.setProductedVolumeString(str);
        }catch (Exception e){
            System.out.println("format error2");
        }

        ////////////////// UPDATING FOR TOTAL /////////////////

        try {
            str = String.format("%.2f", total.getTotalPrice()).replaceFirst(",", ".");
            total.setTotalPriceString(str);
        }catch (Exception e){
            System.out.println("format error3");
        }

        try {
            str = String.format("%.2f", total.getTotalVolume()).replaceFirst(",", ".");
            total.setTotalVolumeString(str);
        }catch (Exception e){
            System.out.println("format error4");
        }
    }


    /////////////////////  PUT SPACES IN PRICES, VOLUME ////////////////////////
    private String priceSpaces(String s) throws IOException {
        String result ="";
        int size = s.length()-3;
        int rest = size%3;

        StringReader stringReader = new StringReader(s);

        for(int i=0; i<size; i++){
            result += Character.toString(stringReader.read());

            if(i<size-1) {
                if (i == rest - 1) {
                    result += " ";
                }
                if (i == rest + 2) {
                    result += " ";
                }
                if (i == rest + 5) {
                    result += " ";
                }
                if (i == rest + 8) {
                    result += " ";
                }
            }
        }

        stringReader.close();

        result += s.substring(size);

        return result;
    }


    /////////////////////  PUT SPACES QUANTITY ////////////////////////
    private String quantitySpaces(String s) throws IOException {
        String result ="";
        int size = s.length();
        int rest = size%3;

        StringReader stringReader = new StringReader(s);

        for(int i=0; i<size; i++){
            result += Character.toString(stringReader.read());

            if(i<size-1) {
                if (i == rest - 1) {
                    result += " ";
                }
                if (i == rest + 2) {
                    result += " ";
                }
                if (i == rest + 5) {
                    result += " ";
                }
                if (i == rest + 8) {
                    result += " ";
                }
            }
        }

        stringReader.close();

        result += s.substring(size);

        return result;
    }


    //////////////////////////// FORMAT PRICES, VOLUME AND QUANTITY ///////////////////////////////
    //////////////////////////// + ADD MARGIN ///////////////////////////////
    private void formatPricesVolume() throws IOException {
        //      ADD SPACES FOR PRICES, VOLUME AND QUANTITY + ISK OR M^3
        for(ComponentBlueprint bp : componentBlueprintList){

            bp.setBasePriceString(priceSpaces(bp.getBasePriceString()));
            bp.setBasePriceString(bp.getBasePriceString() + " ISK  ");

            bp.setMultPriceString(priceSpaces(bp.getMultPriceString()));
            bp.setMultPriceString(bp.getMultPriceString() + " ISK  ");

            bp.setMaterialVolumeString(priceSpaces(bp.getMaterialVolumeString()));
            bp.setMaterialVolumeString(bp.getMaterialVolumeString() + " m3  ");


            bp.setMaterialQuantityString(String.valueOf(bp.getMaterialQuantity()));
            bp.setMaterialQuantityString(quantitySpaces(bp.getMaterialQuantityString()));
            bp.setMaterialQuantityString(bp.getMaterialQuantityString() + "  ");

            bp.setMaterialName(" " + bp.getMaterialName());
        }
        //      ADD SPACES FOR PRICES + ISK OR M^3 FOR OUTPUT ITEM
        productedBlueprint.setProductedPriceString(priceSpaces(productedBlueprint.getProductedPriceString()));
        productedBlueprint.setProductedPriceString(productedBlueprint.getProductedPriceString() + " ISK  ");

        productedBlueprint.setProductedVolumeString(priceSpaces(productedBlueprint.getProductedVolumeString()));
        productedBlueprint.setProductedVolumeString(productedBlueprint.getProductedVolumeString() + " m3  ");

        productedBlueprint.setProductedQuantityString(productedBlueprint.getProductedQuantity() + "  ");

        productedBlueprint.setProductedName(" " + productedBlueprint.getProductedName());

        //      ADD SPACES FOR PRICES + ISK OR M^3 FOR TOTAL
        total.setTotalPriceString(priceSpaces(total.getTotalPriceString()));
        total.setTotalPriceString(total.getTotalPriceString() + " ISK  ");

        total.setTotalVolumeString(priceSpaces(total.getTotalVolumeString()));
        total.setTotalVolumeString(total.getTotalVolumeString() + " m3  ");

    }








    ////////////////////// GETTERS AND SETTERS //////////////////////


    public List<Blueprints> getBlueprintsList() {
        return blueprintsList;
    }

    public ProductedBlueprint getProductedBlueprint() {
        return productedBlueprint;
    }

    public List<ComponentBlueprint> getComponentBlueprintList() {
        return componentBlueprintList;
    }

    public List<String> getComboboxList() {
        return comboboxList;
    }

    public HashMap<String, Integer> getStationHashMap() {
        return stationHashMap;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public boolean isSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

    public void setTe(int te) {
        this.te = te;
    }

    public void setMe(int me) {
        this.me = me;
    }

    public void setSubTE(int subTE) {
        this.subTE = subTE;
    }

    public void setSubME(int subME) {
        this.subME = subME;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
}
