import org.apache.commons.io.FilenameUtils;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

class Calculator {
    private String pluginsPath;
    private List<ArithmeticOperation> listOfReadClass = new ArrayList <ArithmeticOperation>();
    Calculator(String pluginsPath){
        this.pluginsPath = pluginsPath;
    }
    public List<ArithmeticOperation> getListOfReadClass(){
        return listOfReadClass;
    }
    void findClassFiles(){
        File dir = new File(pluginsPath);
        try {
            for(File fileEntry : dir.listFiles()){
                if(FilenameUtils.getExtension(fileEntry.getName()).equals("class"))
                {
                    System.out.print("mamy class ");
                    loadClass(FilenameUtils.removeExtension(fileEntry.getName()));
                }else {
                    System.out.print("nie mamy class ");
                }
                System.out.println(fileEntry.getName());
        }
        } catch (NullPointerException e) {
            System.out.println("Folder jest pusty");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    private void loadClass(String nameClass) throws IllegalAccessException, InstantiationException {
        // Create a File object on the root of the directory containing the class file
        File file = new File(pluginsPath);
        try {
            // Convert File to a URL
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            // Create a new class loader with the directory
            ClassLoader cl = new URLClassLoader(urls);

            // Load in the class; MyClass.class should be located in
            // the directory file:/c:/myclasses/com/mycompany
            Class cls = cl.loadClass(nameClass);
            listOfReadClass.add((ArithmeticOperation) cls.newInstance());
        } catch (MalformedURLException e) {
        } catch (ClassNotFoundException e) {
        }
    }
}
