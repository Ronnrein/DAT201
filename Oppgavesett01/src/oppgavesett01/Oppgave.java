package oppgavesett01;

public class Oppgave {

    public Oppgave() {
        
        System.out.println("--- OPPGAVE A ---");
        OppgaveA a = new OppgaveA(1000);
        a.generateRandomNumbers();
        a.sort();
        a.generateRisingNumbers();
        a.sort();
        a.generateFallingNumbers();
        a.sort();
        
        System.out.println("\n--- OPPGAVE B ---");
        OppgaveB b = new OppgaveB(1000);
        b.removeContaining("91");

        System.out.println("\n--- OPPGAVE C ---");
        OppgaveC c = new OppgaveC(100);

    }

    public static void main(String[] args) {
        Oppgave o = new Oppgave();
    }

}
