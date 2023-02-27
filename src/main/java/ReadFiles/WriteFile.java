package ReadFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteFile {
    
    private String fileName;
    private String content;

    public WriteFile(String name, String content) {
        fileName = name;
        this.content = content;
        writeFile();
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public void writeFile(){
        try {
            FileOutputStream fos = new FileOutputStream(this.fileName);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
            bufferedWriter.write(this.content);
            bufferedWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}
