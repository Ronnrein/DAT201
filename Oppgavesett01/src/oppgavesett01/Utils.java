package oppgavesett01;

import java.util.Arrays;
import java.util.Random;

public class Utils {

    private static String[] firstNames = {"Bob", "Jill", "Tom", "Brandon", "Peter", "Stephen", "Ricky", "Karl", "Claire", "David", "Jonathan"};
    private static String[] lastNames = {"Gervais", "Merchant", "Pill", "Tolkien", "Marshall", "Gonzales", "Pilkington", "Brent", "Keenan", "Ross"};

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static String randBetweenLeadingZeros(int start, int end) {
        String numStr = Integer.toString(start + (int) Math.round(Math.random() * (end - start)));
        String endStr = Integer.toString(end);

        int diff = endStr.length() - numStr.length();

        if (diff > 0) {

            char[] zeros = new char[diff];
            Arrays.fill(zeros, '0');
            numStr = String.valueOf(zeros) + numStr;

        }

        return numStr;
    }

    // Legger denne her, ettersom den brukes i flere oppgaver
    public static String generatePersonalNumber() {
        String result = "";
        result += randBetweenLeadingZeros(1, 29);
        result += randBetweenLeadingZeros(1, 12);
        result += randBetweenLeadingZeros(90, 99);
        result += randBetweenLeadingZeros(1, 99999);
        return result;
    }

    public static String generateName() {
        Random rand = new Random();

        return firstNames[rand.nextInt(firstNames.length)] + " " + lastNames[rand.nextInt(lastNames.length)];
    }

}
