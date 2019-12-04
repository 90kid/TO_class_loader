import org.apache.commons.io.FilenameUtils;
import sun.font.TrueTypeFont;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Calculator {
    private String pluginsPath;
    private List<ArithmeticOperation> listOfReadClass = new ArrayList <ArithmeticOperation>();
    Calculator(String pluginsPath){
        this.pluginsPath = pluginsPath;
        findClassFiles();
    }
    List<ArithmeticOperation> getListOfReadClass(){
        return listOfReadClass;
    }
    private void findClassFiles(){
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
            System.out.println("Wczytywanie zakończone powodzeniem");
        } catch (NullPointerException e) {
            System.out.println("Folder jest pusty");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    private void loadClass(String nameClass) throws IllegalAccessException, InstantiationException {
        File file = new File(pluginsPath);
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};

            ClassLoader cl = new URLClassLoader(urls);

            Class cls = cl.loadClass(nameClass);
            listOfReadClass.add((ArithmeticOperation) cls.newInstance());
        } catch (MalformedURLException e) {
        } catch (ClassNotFoundException e) {
        }
    }
    void start(){
        while(true) {
            int i = 0;
            for (; i < listOfReadClass.size(); i++) {
                System.out.printf("%d. %s\n", i, listOfReadClass.get(i).getClass().getName());
            }
            System.out.printf("%d. %s\n", i + 1, "Exit");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj a:");
            float a = scanner.nextFloat();
            System.out.println("Podaj b:");
            float b = scanner.nextFloat();
            System.out.println("Podaj operacje");
            int operacja = scanner.nextInt();
            if (operacja < listOfReadClass.size() && operacja >= 0) {
                System.out.println(listOfReadClass.get(operacja).function(a, b));
            }
            else if(operacja == i + 1){
                break;
            }
        }
    }
}
