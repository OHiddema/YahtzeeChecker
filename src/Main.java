import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            } else {
                evaluateDiceRoll(diceRoll);
            }
        } while (true);
    }

    private static void evaluateDiceRoll(String diceRoll) {
        boolean isYahtzee, isFourOfAKind, isThreeOfAKind,
                isFullHouse, isLargeStreet, isSmallStreet;

        // check if the input is valid: 5 characters in the range [1..6]
        if (!diceRoll.matches("[1-6]{5}")) {
            System.out.println("Invalid input!");
            return;
        }

        // create a list of integers from the input string
        List<Integer> diceList = diceRoll.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(Integer::parseInt)
                .toList();

        // create a sorted string of integers from the list
        String diceRollAsString = diceList.stream()
                .sorted(Integer::compareTo)
                .map(Object::toString)
                .collect(Collectors.joining(""));

        // create a sorted string of integers from the list, remove duplicates
        // makes checking isSmallStreet much easier
        String diceRollDistinctAsString = diceList.stream()
                .distinct()
                .sorted(Integer::compareTo)
                .map(Object::toString)
                .collect(Collectors.joining(""));

        isYahtzee = diceRollAsString.matches("(.)\\1{4}");
        isFourOfAKind = diceRollAsString.matches(".*(.)\\1{3}.*");
        isThreeOfAKind = diceRollAsString.matches(".*(.)\\1{2}.*");

        // first part of regex matches 2 the same followed by 3 the same
        // second part of regex matches 3 the same followed by 2 the same
        isFullHouse = diceRollAsString.matches("(.)\\1((?!\\1).)\\2{2}|(.)\\3{2}((?!\\3).)\\4");

        isLargeStreet = diceRollAsString.matches("12345|23456");
        isSmallStreet = diceRollDistinctAsString.matches("1234.?|.?2345.?|.?3456");

        System.out.println("Yahtzee:       " + isYahtzee);
        System.out.println("4 of a kind:   " + isFourOfAKind);
        System.out.println("3 of a kind:   " + isThreeOfAKind);
        System.out.println("Full house:    " + isFullHouse);
        System.out.println("Large street:  " + isLargeStreet);
        System.out.println("Small street:  " + isSmallStreet);
        System.out.println();
    }
}