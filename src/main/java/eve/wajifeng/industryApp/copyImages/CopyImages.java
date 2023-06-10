package eve.wajifeng.industryApp.copyImages;

import eve.wajifeng.industryApp.mvc.Model;
import eve.wajifeng.industryApp.yaml.blueprints.Blueprints;
import eve.wajifeng.industryApp.yaml.blueprints.Materials;
import eve.wajifeng.industryApp.yaml.blueprints.Products;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopyImages {

    public void copy(){

        String sourceString = "E:" + File.separator + "Dev" + File.separator + "Eve" + File.separator + "Ressources"
                + File.separator + "Invasion_1.0_Types" + File.separator + "Types";
        String destString = "." + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "images";

        File source;
        File dest;

        Model model = Model.getInstance();
        String bpID;

        for(Blueprints bp : model.getBlueprintsList()){

            try{
                for(Products p : bp.getActivities().getManufacturing().getProducts()){
                    bpID = String.valueOf(p.getTypeID());
                    source = new File(sourceString + File.separator + bpID + "_32.png");
                    dest = new File(destString + File.separator + bpID + "_32.png");

                    try {
                        if(!dest.exists())
                            FileUtils.copyFile(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            try{
                for(Materials m : bp.getActivities().getManufacturing().getMaterials()){
                    bpID = String.valueOf(m.getTypeID());
                    source = new File(sourceString + File.separator + bpID + "_32.png");
                    dest = new File(destString + File.separator + bpID + "_32.png");

                    try {
                        if(!dest.exists())
                            FileUtils.copyFile(source, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception em) {
                em.printStackTrace();
            }
        }
    }

}
