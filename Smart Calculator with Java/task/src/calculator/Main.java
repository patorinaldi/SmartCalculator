package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        VariableStore variableStore = new VariableStore();
        CommandProcessor commandProcessor = new CommandProcessor();

        Scanner scanner = new Scanner(System.in);


        while (true) {
            String input = scanner.nextLine();

            if (commandProcessor.isCommand(input)) {
                if (commandProcessor.processCommand(input)) {
                    break;
                }
                continue;
            }

            if (input.isEmpty()) {
                continue;
            }

            String[] tokens = calculator.curateInput(input);

            if (!calculator.isValidExpression(tokens)) {
                System.out.println("Invalid expression");
                continue;
            }

            if ((tokens.length == 3) && tokens[1].equals("=")) {
                calculator.handleAssignments(tokens, variableStore);
            } else if (tokens.length == 4 && tokens[2].equals("-")) {
                calculator.handleAssignments(tokens, variableStore);
            } else if (tokens.length == 1 && !calculator.isNumeric(tokens[0])) {
                variableStore.printVariableValue(tokens[0]);
            } else {
                calculator.calculate(tokens, variableStore);
            }

        }

    }
    
}

