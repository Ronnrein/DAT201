package oppgavesett03;

/**
 * The main part of the program
 * @author Ronny Reinhardtsen
 */
public class Oppgave {

    public Oppgave(){
        
        // Run tests for assignments 1-9
        System.out.println(Tests.runIntTest());
        System.out.println(Tests.runStringTest());
        
        // Run GUI program
        GUI gui = new GUI();
        
    }

    public static void main(String[] args) {
        Oppgave o = new Oppgave();
    }
    
}
