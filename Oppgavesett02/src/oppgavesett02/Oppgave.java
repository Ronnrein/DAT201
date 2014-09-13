package oppgavesett02;

public class Oppgave {

    public Oppgave() {

        // Oppgave 1
        ListOperations l = new ListOperations(1000, 1000000);
        System.out.println("Randomly generated: " + l.toString(5));

        // Oppgave 2
        l.linearSort();
        System.out.println("Linear sorted: " + l.toString(5));

        l.shuffle();
        System.out.println("Randomly shuffled: " + l.toString(5));

        // Oppgave 4
        l.bubbleSort();
        System.out.println("Bubble sorted: " + l.toString(5) + "\n");

        // Oppgave 5
        l.compareSorts(1000);

        // Oppgave 7, 8 og 9
        l.reset(1000, 1000);
        l.linearSearch(300);
        l.binarySearch(300);
        l.compareSearches(1000, 300);
        l.reset(1000, 1000000);
        l.compareSearches(1000, 300);

        // Oppgave 10
        TowerOfHanoi.move(7, 1, 3);

        // Oppgave 11
        /*
         Når du flytter den første ringen, om antall ringer er partall, skal den flyttes ett hakk til høyre, om antall ringer er oddetall, skal den flyttes ett hakk til venstre, om det ikke er stenger til venstre, flyttes ringen helt til høyre.
         */
        
        // Oppgave 12
        l.binarySearchIterative(300);
    }

    public static void main(String[] args) {
        Oppgave o = new Oppgave();
    }

}
