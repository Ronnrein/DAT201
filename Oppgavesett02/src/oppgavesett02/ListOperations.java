package oppgavesett02;

import java.util.*;

/**
 * Contains a list and various methods to be performed on said list
 *
 * @author Ronnrein
 */
public class ListOperations {

    private int length;
    private int max;
    private List<Integer> nums;

    /**
     * Constructor
     * @param length How many numbers the list will carry
     * @param max The maximum value of the random values in list (1 - max)
     */
    public ListOperations(int length, int max) {
        this.length = length;
        this.max = max;
        nums = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            nums.add(Utils.randBetween(1, max));
        }
    }

    /**
     * Shuffles existing list of numbers
     */
    public void shuffle() {
        Collections.shuffle(nums);
    }

    /**
     * Generates a brand new list of random numbers
     */
    public void reset() {
        nums = new ArrayList<>(length);
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            nums.add(Utils.randBetween(1, max));
        }
    }

    /**
     * Generates a brand new list of random numbers with new supplied length and
     * max value
     *
     * @param length How many numbers the list will carry
     * @param max The maximum value of the random values in list (1 - max)
     */
    public void reset(int length, int max) {
        this.length = length;
        this.max = max;
        nums = new ArrayList<>(length);
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            nums.add(Utils.randBetween(1, max));
        }
    }

    /**
     * Sorts the numbers using a linear algorithm
     */
    public void linearSort() {
        int sorted = 0;
        while (sorted < nums.size()) {
            int lowestI = sorted;
            for (int i = sorted + 1; i < nums.size(); i++) {
                if (nums.get(i) < nums.get(lowestI)) {
                    lowestI = i;
                }
            }
            int replacedNum = nums.get(sorted);
            nums.set(sorted, nums.get(lowestI));
            nums.set(lowestI, replacedNum);
            sorted++;
        }
    }

    /**
     * Sorts the numbers using a bubble algorithm
     */
    public void bubbleSort() {
        boolean swapped = true;
        int j = 1;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < length - j; i++) {
                int num1 = nums.get(i);
                int num2 = nums.get(i + 1);
                if (num1 > num2) {
                    nums.set(i, num2);
                    nums.set(i + 1, num1);
                    swapped = true;
                }
            }
            j++;
        }
    }

    /**
     * Compare the performance of linear and bubble sorting and prints out
     * results afterwards
     *
     * @param times Amount of times to do the test
     */
    public void compareSorts(int times) {
        float sumLinear = 0;
        float sumBubble = 0;
        for (int i = 0; i < times; i++) {
            reset();
            long preTime = System.currentTimeMillis();
            linearSort();
            sumLinear += System.currentTimeMillis() - preTime;
            reset();
            preTime = System.currentTimeMillis();
            bubbleSort();
            sumBubble = System.currentTimeMillis() - preTime;
        }
        System.out.println("Results: Sorting " + times + " times:\n   Linear sorting: " + (sumLinear / times) + "ms\n   Bubble sorting: " + (sumBubble / times) + "ms\n");
    }

    /**
     * Searches for a value through linear search
     *
     * @param value The value to be searched for
     * @return Whether or not value exists in the list
     */
    public boolean linearSearch(int value) {
        bubbleSort();
        for (Integer num : nums) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for a value through recursive binary search
     *
     * @param value The value to be searched for
     * @return Whether or not the value exists in the list
     */
    public boolean binarySearch(int value) {
        bubbleSort();
        return binaryRecursiveSearch(value, 0, nums.size() - 1);
    }

    /**
     * The private recursive method for searching for a value through binary
     * search
     *
     * @param value The value to be searched for
     * @param min Current minimum index
     * @param max Current maximum index
     * @return Whether or not the value was found
     */
    private boolean binaryRecursiveSearch(int value, int min, int max) {
        if (min > max) {
            return false;
        }
        int middle = (min + max) / 2;
        if (nums.get(middle) == value) {
            return true;
        } else if (nums.get(middle) > value) {
            return binaryRecursiveSearch(value, min, middle - 1);
        } else {
            return binaryRecursiveSearch(value, middle + 1, max);
        }
    }

    /**
     * Searches for a value through iterative binary search
     *
     * @param value The value to be searched for
     * @return Whether or not the value exists in the list
     */
    public boolean binarySearchIterative(int value) {
        bubbleSort();
        int min = 0;
        int max = nums.size() - 1;
        while (max >= min) {
            int middle = (min + max) / 2;
            if (nums.get(middle) == value) {
                return true;
            } else if (nums.get(middle) > value) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return false;
    }

    /**
     * Compares the performance of the two different search methods and outputs
     * the result
     *
     * @param times Amount of times to do the test
     * @param value The value to be searched for
     */
    public void compareSearches(int times, int value) {
        float sumLinear = 0;
        float sumBinary = 0;
        for (int i = 0; i < times; i++) {
            reset();
            bubbleSort();
            long preTime = System.currentTimeMillis();
            linearSearch(value);
            sumLinear += System.currentTimeMillis() - preTime;
            preTime = System.currentTimeMillis();
            binarySearch(value);
            sumBinary += System.currentTimeMillis() - preTime;
        }
        System.out.println("Results: Searching " + times + " times with max value of " + max + ":\n   Linear searching: " + (sumLinear / times) + "ms\n   Binary searching: " + (sumBinary / times) + "ms\n");
    }

    /**
     * Calls the toString method of the list, and returns the entire list as a
     * string
     *
     * @return The string of all the list elements
     */
    @Override
    public String toString() {
        return nums.toString();
    }

    /**
     * Returns a custom string containing the first and last number of elements
     * specified, easier to read with many elements
     *
     * @param items Number of first and last elements to be returned
     * @return String containing specified elements
     */
    public String toString(int items) {
        String result = "[";
        for (int i = 0; i < items; i++) {
            result += nums.get(i) + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]...[";
        for (int i = length - 1; i > length - items - 1; i--) {
            result += nums.get(i) + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result += "]";
        return result;
    }

}
