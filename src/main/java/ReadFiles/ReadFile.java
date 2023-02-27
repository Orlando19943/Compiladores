package ReadFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadFile {
    private String fileName;
    private ArrayList<String> lines = new ArrayList<String>();

    public ReadFile(String name) {
        fileName = name;
        readFile();
    }

    public String getFileName() {
        return fileName;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void readFile(){
        StringBuffer buffer = new StringBuffer();
        try {
            Path path = Paths.get(this.fileName);
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            //Reading the UTF-8 data from the file
            buffer = new StringBuffer();
            int ch = 0;
            while((ch = reader.read())!=-1) {
                buffer.append((char)ch);
            }

            lines.add(buffer.toString());
        
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                this.fileName + "'");                   
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file '" 
                    + this.fileName + "'");                    
                }
    }
}
