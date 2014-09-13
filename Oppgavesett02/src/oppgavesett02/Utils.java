package oppgavesett02;

import java.util.Arrays;
import java.util.Random;

public class Utils {

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

}
