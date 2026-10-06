import java.io.File;
import java.util.Scanner;

public class Main {
    public static String getCommandPath(String command) {
        String[] path = System.getenv("PATH").split(":");
        for (String i : path) {
            File file = new File(i, command);
            if (file.exists() && file.canExecute()) {
                return file.getAbsolutePath();
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.print("$ ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }
            else if (command.startsWith("echo ")) {
                System.out.print(command.substring(5, command.length()) + "\n");
            }
            else if (command.startsWith("type ")) {
                String typeCommand = command.substring(5, command.length());
                if (typeCommand.contains("echo") || typeCommand.contains("exit") || typeCommand.contains("type")) {
                    System.out.print(typeCommand + " is a shell builtin \n");
                }
                else if (getCommandPath(typeCommand) != null) {
                    System.out.print(typeCommand + " is " + getCommandPath(typeCommand) + "\n");
                }
                else {
                    System.out.print(typeCommand + ": not found\n");
                }
            }
            else {
                System.out.print(command + ": command not found\n");
            }
        }
    }
}
