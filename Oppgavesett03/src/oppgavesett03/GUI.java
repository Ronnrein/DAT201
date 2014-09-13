package oppgavesett03;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Ronnrein
 */
public class GUI {
    Object[] columns1 = {"Billett nr", "Kundebehandler"};
    Object[] columns2 = {"Billett nr", "Tid ventet"};
    
    JFrame f = new JFrame("Køsystem");
    JLabel ticket = new JLabel("0");
    JTextField name = new JTextField("Kundebehandler");
    DefaultTableModel currentTableModel = new DefaultTableModel(columns1, 0);
    DefaultTableModel waitingTableModel = new DefaultTableModel(columns2, 0);
    
    DoubleList<Customer> customers = new DoubleList<>();
    
    public GUI(){
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
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
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
                        Object[] row = {i.getValue().ticket, TimeUnit.MILLISECONDS.toMinutes(diff)+" minutter, "+TimeUnit.MILLISECONDS.toSeconds(diff)+" sekunder"};
                        waitingTableModel.addRow(row);
                        break;
                    case CURRENT:
                        Object[] row = {i.getValue().ticket, i.getValue().clerk};
                        currentTableModel.addRow(row);
                        break;
                }
            }
        }
        
    }
    
}
