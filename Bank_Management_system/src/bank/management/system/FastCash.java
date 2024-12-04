package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{
    String pin;
    String cardNo;

    JButton b1,b2,b3,b4,b5,b6,b7;

    JLabel label2;

    JPasswordField textFieldp;

    FastCash(String pin, String cardNo)
    {
        this.pin=pin;
        this.cardNo=cardNo;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label=new JLabel("Select Withdrawl Amount  ");
        label.setBounds(455,180,700 ,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,23));
        l3.add(label);

        b1=new JButton("Rs.100");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65,125, 128));
        b1.setBounds(410,274,150,35);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("Rs.500");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65,125, 128));
        b2.setBounds(700,274,150,35);
        b2.addActionListener(this);
        l3.add(b2);

        b3=new JButton("RS.1000");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125, 128));
        b3.setBounds(410,318,150,35);
        b3.addActionListener(this);
        l3.add(b3);

        b4=new JButton("Rs.2000");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125, 128));
        b4.setBounds(700,318,150,35);
        b4.addActionListener(this);
        l3.add(b4);
        /*

        b5=new JButton("Rs.5000");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65,125, 128));
        b5.setBounds(410,362,150,35);
        b5.addActionListener(this);
        l3.add(b5);

        b6=new JButton("Rs.10000");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65,125, 128));
        b6.setBounds(700,362,150,35);
        b6.addActionListener(this);
        l3.add(b6);*/

        label2=new JLabel("Enter your Pin:  ");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD,16 ));
        label2.setBounds(420,360,400,35);
        l3.add(label2);

        textFieldp=new JPasswordField();
        textFieldp.setBackground(new Color(65,125,128));
        textFieldp .setForeground(Color.WHITE);
        textFieldp.setBounds(420,390, 160,25);
        textFieldp.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(textFieldp);

        b7=new JButton("Back ");
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65,125, 128));
        b7.setBounds(700,406,150,35);
        b7.addActionListener(this);
        l3.add(b7);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==b7)
        {
            setVisible(false);
            new main_Class(pin, cardNo);
        }
         else if (e.getSource()==b1 || e.getSource()==b2 || e.getSource()==b3 || e.getSource()==b4)
        {
            System.out.println("Fastcash");
            String amount=((JButton)e.getSource()).getText().substring(3);
            Con c=new Con();
            Date date=new Date();
            String Jtextpin=textFieldp.getText();
            try {

                if (Jtextpin.isEmpty() || Jtextpin.length() != 4) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid pin number and select the amount ");
                    return;
                }


                String q="select pin from login where trim(pin)=? and card_number=?";

                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, Jtextpin);
                pstmt.setString(2, cardNo );
                ResultSet resultSet1 = pstmt.executeQuery();
                if (resultSet1.next())
                {
                int balance = 0;
                    System.out.println("Pin:"+pin);
                    System.out.println("cardnumber:"+cardNo);

                ResultSet resultSet = c.statement.executeQuery("select * from bank where pin='" + pin + "' and card_number='"+cardNo+"'");

                while (resultSet.next()) {


                    if (resultSet.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }


                }


                    int withdrawlamount = Integer.parseInt(amount);
                    if (balance < withdrawlamount) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");

                    } else {
                        balance -= withdrawlamount;
                        c.statement.executeUpdate("insert into bank values('" + pin + "','" + date + "','withdrawl','" + amount + "','"+cardNo+"')");
                        JOptionPane.showMessageDialog(null, "Rs. " + amount + "withdrawal  Successfully");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Pin doesnot match");
                }


            }
            catch (Exception E)
            {
                E.printStackTrace();
            }

        }
    }

    public static void main(String[] args)
    {

        new FastCash("", "");
    }
}
