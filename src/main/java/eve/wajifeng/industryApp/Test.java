package eve.wajifeng.industryApp;

import eve.wajifeng.industryApp.copyImages.CopyImages;
import eve.wajifeng.industryApp.formatYaml.FormatBlueprints;
import eve.wajifeng.industryApp.formatYaml.FormatTypeIDs;
import eve.wajifeng.industryApp.mvc.Model;
import org.apache.commons.lang.time.StopWatch;

import java.io.File;


public class Test {

    public static void main(String[] args) throws Exception {

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

//        FormatBlueprints fBp = new FormatBlueprints();
//        FormatTypeIDs fTi = new FormatTypeIDs();
//
//        String fBpFilePath = "E:" + File.separator + "Dev" + File.separator + "Eve" + File.separator + "Ressources" +
//                                File.separator + "sde" + File.separator + "fsd" + File.separator + "blueprints.yaml";
//        String FtiFilePath = "E:" + File.separator + "Dev" + File.separator + "Eve" + File.separator + "Ressources" +
//                File.separator + "sde" + File.separator + "fsd" + File.separator + "typeIDs.yaml";
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
