import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String diceRoll;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Give a string with 5 values in the range [1..6], like '12345', or 'exit' to stop: ");
            diceRoll = scanner.nextLine();
            if (diceRoll.equals("exit")) {
                System.out.println("Bye!");
                return;
            }
            // check if the input is valid: 5 characters in the range [1..6]
            if (!diceRoll.matches("[1-6]{5}")) {
                System.out.println("Invalid input!");
                continue;
            }
            evaluateDiceRoll(diceRoll);
        } while (true);
    }

    private static void evaluateDiceRoll(String diceRoll) {
        boolean isYahtzee, isFourOfAKind, isThreeOfAKind, isFullHouse, isLargeStreet, isSmallStreet;
        int maxCount = 0;               // highest occurrence of the same number
        int maxLength = 0;              // the longest street
        int length = 0;
        int cardinality = 0;            // counts the number of different occurrences

        // histogram[1]..[6] holds the number of occurrences of 1..6
        // this means that histogram[0] is not used!
        int[] histogram = new int[7];

        for (int i = 0; i < diceRoll.length(); i++) {
            int value = Character.getNumericValue(diceRoll.charAt(i));
            histogram[value]++;
        }

        for (int i = 1; i <= 6; i++) {
            if (histogram[i] > 0) {
                cardinality++;
                length++;
                maxLength = Math.max(length, maxLength);
                maxCount = Math.max(histogram[i], maxCount);
            } else {
                length = 0;
            }
        }

        isYahtzee = maxCount == 5;
        isFourOfAKind = maxCount >= 4;
        isThreeOfAKind = maxCount >= 3;
        isFullHouse = maxCount == 3 && cardinality == 2;
        isLargeStreet = maxLength == 5;
        isSmallStreet = maxLength >= 4;

        System.out.println("Yahtzee:       " + isYahtzee);
        System.out.println("4 of a kind:   " + isFourOfAKind);
        System.out.println("3 of a kind:   " + isThreeOfAKind);
        System.out.println("Full house:    " + isFullHouse);
        System.out.println("Large street:  " + isLargeStreet);
        System.out.println("Small street:  " + isSmallStreet);
        System.out.println();
    }
}