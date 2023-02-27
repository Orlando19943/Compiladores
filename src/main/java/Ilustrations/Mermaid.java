package Ilustrations;

import ReadFiles.ReadFile;
import ReadFiles.WriteFile;

public class Mermaid {
    
    // main
    private String html;
    private String fileName;

    public Mermaid(String automata, String fileName, String expresion){
        this.html = generateHtml(automata, expresion);
        this.fileName = fileName;
        writeFile();
    }

    private String generateHtml (String automata, String expresion){
        return "<!DOCTYPE html>\n" + 
                        "<html lang='en'>\n"+
                        "<head>\n"+
                        "  <meta charset='utf-8'>\n"+
                        "</head>\n"+
                        "<body>\n"+
                        "  <div class='mermaid'>\n"+
                        "graph LR\n" + 
                        automata + 
                        "  </div>\n"+
                        "  <div>\n"+
                        expresion +
                        "  </div>\n"+
                        "   <script src='https://unpkg.com/mermaid@8.0.0-rc.8/dist/mermaid.min.js'></script>\n"+
                        "   <script> mermaid.initialize({theme: 'neutral'}); </script>\n"+
                        "   </body>\n"+
                        "</html>";
    }

    private void writeFile(){
        WriteFile file = new WriteFile(fileName, this.html);
        
    }
}
