package oppgavesett03;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ronnrein
 */
public class Customer implements Comparable<Customer>, Serializable{
    
    public String clerk;
    public CustomerStatus status;
    public int ticket;
    public Date arrival;
    
    public Customer(int ticket){
        this.ticket = ticket;
        status = CustomerStatus.WAITING;
        arrival = new Date();
    }

    @Override
    public int compareTo(Customer e) {
        if(e.ticket < ticket){
            return 1;
        } else if(e.ticket > ticket){
            return -1;
        }
        return 0;
    }
    
}
