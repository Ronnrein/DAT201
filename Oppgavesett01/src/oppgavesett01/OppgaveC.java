package oppgavesett01;

import java.util.HashMap;
import java.util.Map;

public class OppgaveC {

    private Map<String, Person> persons = new HashMap<>();

    public OppgaveC(int amount) {

        for (int i = 0; i < amount; i++) {
            String personalNumber = Utils.generatePersonalNumber();

            while (persons.containsKey(personalNumber)) {
                personalNumber = Utils.generatePersonalNumber();
            }

            persons.put(personalNumber, new Person(personalNumber, Utils.generateName()));
        }
        
        System.out.println("$ "+persons.size()+" people with unique personal numbers generated.");

    }

    @Override
    public String toString() {
        String result = "{";

        for (Map.Entry<String, Person> person : persons.entrySet()) {
            result += person.getKey() + "=" + person.getValue().name + ", ";
        }

        result = result.substring(0, result.length() - 2);
        result += "}";
        return result;
    }

}

class Person {

    public String personalNumber;
    public String name;

    public Person(String personalNumber, String name) {
        this.personalNumber = personalNumber;
        this.name = name;
    }
}
