package nro.art;

import java.io.*;

/**
 *
 * @author Administrator
 */
public class Text {

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\Administrator\\Desktop\\part";
        String outputPath = "output.txt";

        try {
            File folder = new File(folderPath);
            File outputFile = new File(outputPath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            if (folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".json")) {
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                writer.write("name: " + file.getName() + line);
                                writer.newLine();
                            }
                            reader.close();
                        }
                    }
                }
            }

            writer.close();
            System.out.println("cccccccccccccccccccccccccccccccccc: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
