package oppgavesett01;

import java.util.Arrays;
import java.util.Random;

public class OppgaveA {

    private int amount;
    private int[] numbers;
    private int sortingIterations = 100;
    private long[] sortTimes;

    public OppgaveA(int amount) {
        this.amount = amount;
        numbers = new int[amount];
        sortTimes = new long[sortingIterations];
    }

    public void generateRandomNumbers() {
        Random rand = new Random();

        for (int i = 0; i < numbers.length; i++) {
            int randInt = Utils.randBetween(0, amount);
            numbers[i] = randInt;
        }

        System.out.println("§ Random numbers generated.");
    }

    public void generateRisingNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        System.out.println("§ 0-" + (numbers.length - 1) + " numbers generated.");
    }

    public void generateFallingNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers.length - i - 1;
        }

        System.out.println("§ " + (numbers.length - 1) + "-0 numbers generated.");
    }

    public void sort() {
        for (int i = 0; i < sortingIterations; i++) {
            long splitTime = System.nanoTime();
            Arrays.sort(numbers);
            sortTimes[i] = System.nanoTime() - splitTime;
        }

        Arrays.sort(sortTimes);
        long sum = 0;

        for (long time : sortTimes) {
            sum += time;
        }

        long avarage = sum / sortTimes.length;
        long diff = sortTimes[sortingIterations - 1] - sortTimes[0];
        System.out.println("Sorting done, results:\n  Min: " + sortTimes[0] + " nanosec\n  Max: " + sortTimes[sortingIterations - 1] + " nanosec\n  Avarage: " + avarage + " nanosec\n  Largest diff: " + diff + " nanosec\n");
    }

    public void setAmount(int newAmount) {
        amount = newAmount;
        numbers = new int[amount];
    }

    public void setSortingIterations(int newIterations) {
        sortingIterations = newIterations;
        sortTimes = new long[sortingIterations];
    }

    public int length() {
        return numbers.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }

}
