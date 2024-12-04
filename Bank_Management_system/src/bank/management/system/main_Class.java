package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class main_Class extends JFrame implements ActionListener {
    String pin;

    JLabel label1,label2;

    JButton b1,b2,b3,b4,b5,b6,b7;
    String name;
    String cardNo;
    main_Class(String  pin, String cardNo){
        this .pin=pin;
        this .cardNo=cardNo;

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        label1=new JLabel("Please Select Your Transaction");
        label1.setBounds(430,180,700 ,35);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System",Font.BOLD,28));
        l3.add(label1);

        label2=new JLabel();
        label2.setBounds(430,230,700 ,35);
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("serif",Font.BOLD,23));
        l3.add(label2);

        b1=new JButton("DEPOSIT");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65,125, 128));
        b1.setBounds(410,274,150,35);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("CASH WITHDRAWL");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65,125, 128));
        b2.setBounds(700,274,150,35);
        b2.addActionListener(this);
        l3.add(b2);

        b3=new JButton("FAST CASH");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125, 128));
        b3.setBounds(410,318,150,35);
        b3.addActionListener(this);
        l3.add(b3);

        b4=new JButton("MINI STATEMENT");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125, 128));
        b4.setBounds(700,318,150,35);
        b4.addActionListener(this);
        l3.add(b4);

        b5=new JButton("PIN CHANGE");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65,125, 128));
        b5.setBounds(410,362,150,35);
        b5.addActionListener(this);
        l3.add(b5);

        b6=new JButton("BALANCE ENQUIRY");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65,125, 128));
        b6.setBounds(700,362,150,35);
        b6.addActionListener(this);
        l3.add(b6);

        b7=new JButton("EXIT");
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65,125, 128));
        b7.setBounds(700,406,150,35);
        b7.addActionListener(this);
        l3.add(b7);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);

        try {
            Con c = new Con();

            System.out.println("Mainclass");
            System.out.println("Pin passed: " + pin);
            System.out.println(cardNo);
            ResultSet resultSet1 = c.statement.executeQuery("select name from signup where pin='" + pin + "' and card_number='"+cardNo+"'");
            if (resultSet1.next()) {
                name = resultSet1.getString("name");
                System.out.println("Name found: " + name);

                label2.setText("Welcome " + name );

            }
            else {
                System.out.println("username not display ");
            }

        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {


            if (e.getSource() == b1) {
                new Deposit(pin,cardNo);
                setVisible(false);
            } else if (e.getSource() == b7) {
                //System.exit(0);
                setVisible(false);
                new Login();
            } else if (e.getSource() == b2) {
                new Withdrawl(pin,cardNo);
                setVisible(false);
            } else if (e.getSource() == b6) {
                new BalanceEnquriy(pin,cardNo);
                setVisible(false);
            } else if (e.getSource() == b3) {
                new FastCash(pin,cardNo);
                setVisible(false);
            } else if (e.getSource() == b5) {
                new Pin(pin,cardNo);
                JOptionPane.showMessageDialog(null, "When you change the pin it is updated and you can verify it in login another time.... .");
                setVisible(false);
            } else if (e.getSource() == b4) {
                new mini(pin,cardNo);
            }


    }

    public static void main(String[] args) {

        new main_Class("", "");
    }
}
