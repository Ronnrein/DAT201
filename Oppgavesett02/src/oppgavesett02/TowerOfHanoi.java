package oppgavesett02;

/**
 * Class containing static method to solve tower of hanoi
 *
 * @author Ronnrein
 */
public class TowerOfHanoi {

    /**
     * Algorithm to solve the tower of hanoi problem.
     *
     * @param rings Number of rings to be moved
     * @param from From which peg they should start
     * @param to To which peg they should move
     */
    public static void move(int rings, int from, int to) {
        if (rings == 1) {
            System.out.println("Moved from " + from + " to " + to);
        } else {
            int temp = 6 - from - to;
            move(rings - 1, from, temp);
            System.out.println("Moved from " + from + " to " + to);
            move(rings - 1, temp, to);
        }
    }
}
