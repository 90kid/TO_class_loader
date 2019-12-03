import org.apache.commons.io.FilenameUtils;


import java.io.File;

public class Calculator {
    private String pluginsPath;
    public Calculator(String pluginsPath){
        this.pluginsPath = pluginsPath;
    }
    private void findClassFiles(){
        File dir = new File(pluginsPath);
        try {
            for(File fileEntry : dir.listFiles()){
                if(FilenameUtils.getExtension(fileEntry.getName()).equals("class"))
                {
                    System.out.println("mamy class");
                }else {
                    System.out.println("nie mamy class");
                }
                //System.out.println(fileEntry.getName());
        }
        } catch (NullPointerException e) {
            System.out.println("Folder jest pusty");
        }
    }
}
