 import java.util.*;import java.io.File;import java.util.function.*;
 
public class badExampleClass { // Bad class name convention
    public static final int some_CONSTANT = 10; // Bad constant naming convention
 
    private List<String> l = new ArrayList<String>(); // Non-descriptive variable name
 
    public static void main(String args[]) { // String array should be final and named 'args'
        badExampleClass bec = new badExampleClass(); // Class name not camel case
        bec.add("exampleString");bec.add(null); bec.add(""); // No space after semicolon, nulls and empty strings should be handled
        bec.doSomething(100); // Magic number, use constant instead
        bec.doSomething(some_CONSTANT);
    }
 
    public void add(String s) {
        l.add(s); // Should check for null or empty string
    }
 
    public void doSomething(int n) {
        if (n == 100) { // Magic number, use a named constant
            System.out.println("Doing something"); // Avoid using System.out.println for logging
        } else if (n == some_CONSTANT) {
            System.out.println("Doing something else"); // Avoid using System.out.println for logging
        } else {
            System.out.println("Doing nothing"); // Avoid using System.out.println for logging
        }
    }
 
    public void veryLongMethodNameThatShouldBeShorter() { // Method name is too long
        // Code that is too long and complex
        try {
            File file = new File("path/to/file"); // Hardcoded file path
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) { // Catching generic exception, should be specific
            e.printStackTrace(); // Using printStackTrace, should use logger
        }
    }
}

has context menu
