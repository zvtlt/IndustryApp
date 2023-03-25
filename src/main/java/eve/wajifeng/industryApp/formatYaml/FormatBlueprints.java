package eve.wajifeng.industryApp.formatYaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FormatBlueprints {

    public void modifyFile(String s) throws Exception {
        modifyFile1(s);
        modifyFile2();
    }

    private void modifyFile1(String s) throws Exception {
        File file1 = new File(s);
        File file2 = new File("src/main/resources/yaml/blueprints/blueprintsNewVersion.yaml");

        StringBuilder originalContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line = reader.readLine();
            while (line != null) {
                char c = line.charAt(0);
                if(Character.isDigit(c)){
                    line = "---";
                }
                while(line.contains(" []")){
                    line = reader.readLine();
                }
                originalContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            String newContent = originalContent.toString();
            try (FileWriter writer = new FileWriter(file2)) {
                writer.write(newContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyFile2() throws Exception {
        File file1 = new File("src/main/resources/yaml/blueprints/blueprintsNewVersion.yaml");
        File file2 = new File("src/main/resources/yaml/blueprints/blueprints1.yaml");
        File file3 = new File("src/main/resources/yaml/blueprints/blueprints2.yaml");
        File file4 = new File("src/main/resources/yaml/blueprints/blueprints3.yaml");

        StringBuilder originalContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line = reader.readLine();
            int lineCount = 1;
            while (line != null) {
                originalContent.append(line).append(System.lineSeparator());
                line = reader.readLine();

                if(lineCount == 76632){
                    String newContent = originalContent.toString();
                    try (FileWriter writer = new FileWriter(file2)) {
                        writer.write(newContent);
                        originalContent = new StringBuilder();
                    }
                }

                if(lineCount == 137799){
                    String newContent = originalContent.toString();
                    try (FileWriter writer = new FileWriter(file3)) {
                        writer.write(newContent);
                        originalContent = new StringBuilder();
                    }
                }

                lineCount += 1;
            }

            String newContent = originalContent.toString();
            try (FileWriter writer = new FileWriter(file4)) {
                writer.write(newContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        file1.delete();
    }

}
