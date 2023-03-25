package eve.wajifeng.industryApp;

import eve.wajifeng.industryApp.copyImages.CopyImages;
import eve.wajifeng.industryApp.formatYaml.FormatBlueprints;
import eve.wajifeng.industryApp.formatYaml.FormatTypeIDs;
import eve.wajifeng.industryApp.mvc.ComponentBlueprint;
import eve.wajifeng.industryApp.mvc.Model;
import eve.wajifeng.industryApp.mvc.ProductedBlueprint;
import org.apache.commons.lang.time.StopWatch;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class FormatYamlFiles {

    public static void main(String[] args) throws Exception {

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

//        FormatBlueprints fBp = new FormatBlueprints();
//        FormatTypeIDs fTi = new FormatTypeIDs();
//
//        String fBpFilePath = "E:\\Dev\\Eve\\Ressources\\sde\\fsd\\blueprints.yaml";
//        String FtiFilePath = "E:\\Dev\\Eve\\Ressources\\sde\\fsd\\typeIDs.yaml";
//
//        fBp.modifyFile(fBpFilePath);
//        fTi.modifyFile(FtiFilePath);

//        CopyImages cp = new CopyImages();
//        cp.copy();


        stopwatch.stop();
        long timeTaken = stopwatch.getTime();
        System.out.println(timeTaken);


    }
}
