package calculator;

public class CommandProcessor {

    public boolean isCommand(String input) {
        return input.startsWith("/");
    }

    public boolean processCommand(String input) {
        return switch (input) {
            case "/exit" -> {
                System.out.println("Bye!");
                yield true;
            }
            case "/help" -> {
                System.out.println("The program calculates the sum or subtraction of numbers");
                yield false;
            }
            default -> {
                System.out.println("Unknown command");
                yield false;
            }
        };
    }

}
