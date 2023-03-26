package eve.wajifeng.industryApp;

import org.apache.commons.lang.time.StopWatch;


public class Test {

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
