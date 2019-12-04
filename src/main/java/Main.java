public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator("src/main/plugins/");
        calculator.findClassFiles();
        System.out.println(calculator.getListOfReadClass().get(0).function(1.f, 2.f));
        System.out.println(calculator.getListOfReadClass().get(1).function(1.f, 2.f));
        System.out.println(calculator.getListOfReadClass().get(2).function(1.f, 2.f));
        System.out.println(calculator.getListOfReadClass().get(3).function(1.f, 2.f));
    }
}
