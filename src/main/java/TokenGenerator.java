import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Random;
import java.util.Scanner;

public class TokenGenerator {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        welcomeUser();
        int lengthVariant = chooseOption();
        int[] token = generateToken(lengthVariant);
        deliverToken(token);
    }

    private static void welcomeUser() {

        System.out.println("\n");
        System.out.println("***************************");
        System.out.println(ANSI_YELLOW + "WELCOME TO TOKEN GENERATOR" + ANSI_RESET);
        System.out.println("***************************");
        System.out.println("This application can generate 5, 10 or 15 character long tokens.");
        System.out.println("\n");
        System.out.println("Please choose (and confirm with ENTER):");
        System.out.println("1 for 5 characters long token");
        System.out.println("2 for 10 characters long token");
        System.out.println("3 for 15 characters long token");
    }

    private static int chooseOption() {

        Scanner scanner = new Scanner(System.in);
        boolean incorrectInput = true;

        int lengthVariant = 0;

        while (incorrectInput) {
            if (scanner.hasNextInt()) {
                lengthVariant = scanner.nextInt();
                if (lengthVariant > 0 && lengthVariant < 4)
                    incorrectInput = false;
                else {
                    System.out.println("That's not a valid number! Please stick to presented options: 1, 2 or 3");
                }
            } else {
                System.out.println("That's not a number! Just choose: 1, 2 or 3");
                scanner.next();
            }
        }
        return lengthVariant;
    }

    private static int[] generateToken(int tokenoption) {

        Random randomGenerator = new Random();
        int tokenLength = 0;
        int[] LastRangeScope = new int[]{33, 35, 36, 37, 38, 40, 41, 42, 64, 94};
                 //values for this scope are in array (! 33, # 35, $ 36, % 37, & 38, ( 40, ) 41, * 42, @ 64, ^ 94) as they were not one after another in the ASCII chart

        switch (tokenoption) {
            case 1:
                tokenLength = 5;
                break;
            case 2:
                tokenLength = 10;
                break;
            case 3:
                tokenLength = 15;
                break;
            default: {
                System.out.println("Something went wrong.");
                chooseOption();
            }
        }

        int[] tokenArray = new int[tokenLength];

        for (int i = 0; i < tokenLength; i++) {
            int drawRandomRange = 1 + randomGenerator.nextInt(4);

            switch (drawRandomRange) {
                case 1:
                    tokenArray[i] = 97 + randomGenerator.nextInt(122 - 97 + 1);  // range #1 from "a" 97 to "z" 122
                    break;

                case 2:
                    tokenArray[i] = 65 + randomGenerator.nextInt(90 - 65 + 1);  // range #2 from "A" 65 to "Z" 90
                    break;

                case 3:
                    tokenArray[i] = 48 + randomGenerator.nextInt(57 - 48 + 1);  // range #3 from "0" 48 to "9" 57
                    break;
                case 4:
                    int drawRandomCell = randomGenerator.nextInt(10); //range #4 - defined in LastRangeScope array in this method
                    tokenArray[i] = LastRangeScope[drawRandomCell];
                    break;
                default: {
                    System.out.println("Something went wrong.");
                }
            }
        }
        return tokenArray;
    }

    private static void deliverToken(int[] tokenValues) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int x : tokenValues) {
            stringBuilder.append((char) x);
        }

        String token = String.valueOf(stringBuilder);

        System.out.println("\n");
        System.out.println("Here is your token:");
        System.out.println(ANSI_YELLOW + token + ANSI_RESET);
        System.out.println("It was also copied to your clipboard! (try with CTRL+V)");

        StringSelection stringSelection = new StringSelection(token);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}