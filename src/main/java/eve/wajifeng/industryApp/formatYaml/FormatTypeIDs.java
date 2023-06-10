package eve.wajifeng.industryApp.formatYaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FormatTypeIDs {

    public void modifyFile(String s) throws Exception {
        modifyFileV1(s);
        modifyFileV2();
        modifyFileV3();
        modifyFileV4();
        modifyFileV5();
    }

    private void modifyFileV1(String s) throws Exception {
        File file1 = new File(s);
        File file2 = new File("." + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "yaml" + File.separator + "typeIDs" + File.separator + "typeIDsNewVersion.yaml");

        StringBuilder originalContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line = reader.readLine();
            while (line != null) {
                if(!line.equals("")) {
                    char c = line.charAt(0);
                    if (Character.isDigit(c)) {
                        line = line.substring(0, line.length() - 1);
                        line = "---\n    id: " + line;
                    }
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

    private void modifyFileV2() throws Exception {
        File file = new File("." + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "yaml" + File.separator + "typeIDs" + File.separator + "typeIDsNewVersion.yaml");

        StringBuilder originalContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith("    id") ||
                        line.startsWith("    name") ||
                        line.startsWith("        en") ||
                        line.startsWith("    volume") ||
                        line.startsWith("---") ||
                        line.startsWith("    iconID") ||
                        line.startsWith("    description")) {
                    originalContent.append(line).append(System.lineSeparator());
                }
                line = reader.readLine();
            }
            String newContent = originalContent.toString();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(newContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyFileV3() throws Exception {
        File file = new File("." + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "yaml" + File.separator + "typeIDs" + File.separator + "typeIDsNewVersion.yaml");

        StringBuilder originalContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith("    description")) {
                    line = reader.readLine();
                    line = reader.readLine();
                }
                if(line.startsWith("    name")) {
                    line = reader.readLine();
                }
                originalContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            String newContent = originalContent.toString();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(newContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyFileV4() throws Exception {
        File file = new File("." + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "yaml" + File.separator + "typeIDs" + File.separator + "typeIDsNewVersion.yaml");

        StringBuilder originalContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                if(line.startsWith("        en")) {
                    line = line.substring(12);
                    line = "    name: " + line;
                }
                originalContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            String newContent = originalContent.toString();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(newContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modifyFileV5() throws Exception {
        File file1 = new File("." + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "yaml" + File.separator + "typeIDs" + File.separator + "typeIDsNewVersion.yaml");
        File file2 = new File("." + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "yaml" + File.separator + "typeIDs" + File.separator + "typeIDs1.yaml");
        File file3 = new File("." + File.separator + "src" + File.separator + "main" + File.separator + "resources" +
                File.separator + "yaml" + File.separator + "typeIDs" + File.separator + "typeIDs2.yaml");


        StringBuilder originalContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line = reader.readLine();
            int lineCount = 1;
            while (line != null) {
                originalContent.append(line).append(System.lineSeparator());
                line = reader.readLine();

                if(lineCount == 123240){
                    String newContent = originalContent.toString();
                    try (FileWriter writer = new FileWriter(file2)) {
                        writer.write(newContent);
                        originalContent = new StringBuilder();
                    }
                }

                lineCount += 1;
            }
            String newContent = originalContent.toString();
            try (FileWriter writer = new FileWriter(file3)) {
                writer.write(newContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        file1.delete();
    }

}
