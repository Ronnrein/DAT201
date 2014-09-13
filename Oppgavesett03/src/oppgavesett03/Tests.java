package oppgavesett03;

/**
 *
 * @author Ronnrein
 */
public class Tests {
    
    public static String runIntTest(){
        String result = "";
        
        // 1 and 2
        DoubleList<Integer> l = new DoubleList<>();
        result += "Created DoubleList:\n"+l.toString()+"\n";
        
        // 3
        l.addFirst(20);
        l.addFirst(12);
        l.addFirst(10);
        result += "Add first:\n"+l.toString()+"\n";
        
        // 4
        l.addLast(50);
        l.addLast(75);
        result += "Add last:\n"+l.toString()+"\n";
        
        // 7
        l.clear();
        l.add(50);
        l.add(20);
        l.add(30);
        l.add(2);
        result += "Add in order:\n"+l.toString()+"\n";
        
        // 9
        l.remove(2);
        l.remove(20);
        result += "Remove:\n"+l.toString()+"\n";
        
        return result;
    }
    
    public static String runStringTest(){
        String result = "";
        
        // 1 and 2
        DoubleList<String> l = new DoubleList<>();
        result += "Created DoubleList:\n"+l.toString()+"\n";
        
        // 3
        l.addFirst("Heisann");
        l.addFirst("Se det");
        l.addFirst("Per");
        result += "Add first:\n"+l.toString()+"\n";
        
        // 4
        l.addLast("Ja");
        l.addLast("Nei");
        result += "Add last:\n"+l.toString()+"\n";
        
        // 7
        l.clear();
        l.add("Pål");
        l.add("Per");
        l.add("Anders");
        l.add("Stian");
        result += "Add in order:\n"+l.toString()+"\n";
        
        // 9
        l.remove("Anders");
        l.remove("Per");
        result += "Remove:\n"+l.toString()+"\n";
        
        return result;
    }
    
}
