package oppgavesett01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class OppgaveB {

    ArrayList<String> numbers = new ArrayList<>();

    public OppgaveB(int amount) {
        Random rand = new Random();

        for (int i = 0; i < amount; i++) {
            numbers.add(Utils.generatePersonalNumber());
        }

        System.out.println("$ " + numbers.size() + " personal numbers generated.");
    }

    public void removeContaining(String str) {
        int removed = 0;

        Iterator<String> i = numbers.iterator();
        while (i.hasNext()) {

            String s = i.next();
            if (s.contains(str)) {
                removed++;
                i.remove();
            }

        }

        System.out.println("Removing done, removed " + removed + " items containing \"" + str + "\".");
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
