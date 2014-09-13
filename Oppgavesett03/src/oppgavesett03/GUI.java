package oppgavesett03;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronnrein
 */
public class GUI implements MouseListener{
    private static final String FILENAME = "customers.ser";
    private int currentTicket = 0;
    private int served = 0;
    private long totalTime = 0;
    
    Object[] columns1 = {"Billett nr", "Kundebehandler"};
    Object[] columns2 = {"Billett nr", "Tid ventet"};
    
    JFrame f = new JFrame("Køsystem");
    JLabel ticket = new JLabel("0");
    JTextField name = new JTextField("Sett navn her");
    JLabel info = new JLabel();
    DefaultTableModel currentTableModel = new DefaultTableModel(columns1, 0);
    DefaultTableModel waitingTableModel = new DefaultTableModel(columns2, 0);
    
    DoubleList<Customer> customers = new DoubleList<>();
    
    public GUI(){
        
        File file = new File(FILENAME);
        if(!file.exists() || file.isDirectory()){
            saveCustomers();
        } else{
            loadCustomers();
        }
        
        JTable currentTable = new JTable(currentTableModel);
        JTable waitingTable = new JTable(waitingTableModel);
        JPanel l = new JPanel();
        JPanel top = new JPanel(new BorderLayout());
        JPanel topCtrl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonCtrl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel bottom = new JPanel();
        JPanel bottom1 = new JPanel();
        JPanel bottom2 = new JPanel();
        JButton buttonNext = new JButton("Serve next");
        JButton buttonNew = new JButton("New customer");
        
        buttonNew.addMouseListener(this);
        buttonNext.addMouseListener(this);
        
        buttonNext.setActionCommand("next");
        buttonNew.setActionCommand("new");
        bottom.setLayout(new GridLayout(2, 1));
        bottom1.setLayout(new BorderLayout());
        bottom2.setLayout(new BorderLayout());
        topCtrl.setLayout(new BorderLayout());
        
        f.setLayout(new BorderLayout());
        f.add(top, BorderLayout.NORTH);
        f.add(bottom);
        top.add(name, BorderLayout.NORTH);
        top.add(ticket, BorderLayout.EAST);
        top.add(topCtrl);
        topCtrl.add(buttonCtrl, BorderLayout.SOUTH);
        topCtrl.add(info);
        buttonCtrl.add(buttonNext);
        buttonCtrl.add(buttonNew);
        name.setHorizontalAlignment(JLabel.CENTER);
        bottom.add(bottom1);
        bottom.add(bottom2);
        bottom1.add(new JLabel("Behandles nå:"), BorderLayout.NORTH);
        bottom1.add(new JScrollPane(currentTable));
        bottom2.add(new JLabel("Venter på behandling:"), BorderLayout.NORTH);
        bottom2.add(new JScrollPane(waitingTable));
        
        ticket.setSize(200, 200);
        ticket.setOpaque(true);
        ticket.setBackground(Color.black);
        ticket.setForeground(Color.red);
        ticket.setFont(new Font("Monospaced", Font.BOLD, 100));
        
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        l.setBorder(border);
        top.setBorder(border);
        name.setBorder(border);
        bottom1.setBorder(border);
        bottom2.setBorder(border);
        
        new java.util.Timer().schedule(new TimerTask(){
            @Override
            public void run(){
                tick();
            }
        }, 0, 1000);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    private Customer getNextToServe(){
        Customer current = null;
        for(DoubleNode<Customer> i : customers){
            if(current == null || (i.getValue().status == CustomerStatus.WAITING && i.getValue().ticket < current.ticket)){
                current = i.getValue();
            }
        }
        return current;
    }
    
    private void updateTables(){
        if(customers.size() > 0){
            currentTableModel.setRowCount(0);
            waitingTableModel.setRowCount(0);
            for(DoubleNode<Customer> i : customers){
                switch(i.getValue().status){
                    case WAITING:
                        Date now = new Date();
                        long diff = now.getTime() - i.getValue().arrival.getTime();
                        Object[] row1 = {i.getValue().ticket, TimeUnit.MILLISECONDS.toMinutes(diff)+" minutter, "+TimeUnit.MILLISECONDS.toSeconds(diff)+" sekunder"};
                        waitingTableModel.addRow(row1);
                        break;
                    case CURRENT:
                        Object[] row2 = {i.getValue().ticket, i.getValue().clerk};
                        currentTableModel.addRow(row2);
                        break;
                }
            }
        }   
    }
    
    private void loadCustomers(){
        try{
            FileInputStream fileIn = new FileInputStream(FILENAME);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            customers = (DoubleList<Customer>) in.readObject();
            if(customers.size() > 0){
                currentTicket = customers.highestValue().getValue().ticket;
            }
            in.close();
            fileIn.close();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    private void saveCustomers(){
        try{
            FileOutputStream fileOut = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(customers);
            out.close();
            fileOut.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void tick(){
        loadCustomers();
        updateTables();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(((JButton)e.getComponent()).getActionCommand()){
            case "next":
                Customer currentCustomer = getNextToServe();
                if(currentCustomer == null){
                    break;
                }
                for(DoubleNode<Customer> i : customers){
                    if(i.getValue().status == CustomerStatus.CURRENT && i.getValue().clerk.equals(name.getText())){
                        customers.remove(i);
                    }
                }
                Date now = new Date();
                long diff = now.getTime() - currentCustomer.arrival.getTime();
                totalTime += diff;
                served++;
                long avarage = totalTime/served;
                info.setText("<html>Behandler nå billett: "+currentCustomer.ticket+"<br />"
                        + "Denne personen har ventet "+TimeUnit.MILLISECONDS.toMinutes(diff)+" minutter, "+TimeUnit.MILLISECONDS.toSeconds(diff)+" sekunder<br />"
                        + "Gjennomsnittlig ventetid er nå "+TimeUnit.MILLISECONDS.toMinutes(avarage)+" minutter, "+TimeUnit.MILLISECONDS.toSeconds(avarage)+" sekunder</html>");
                currentCustomer.status = CustomerStatus.CURRENT;
                currentCustomer.clerk = name.getText();
                ticket.setText(Integer.toString(currentCustomer.ticket));
                updateTables();
                saveCustomers();
                break;
            case "new":
                currentTicket++;
                customers.addFirst(new Customer(currentTicket));
                updateTables();
                saveCustomers();
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
}
