package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquriy extends JFrame implements ActionListener {

    JLabel label2,label3;

     JButton b1;
     String pin;
     String cardNo;
    BalanceEnquriy(String pin, String cardNo)
    {
        this.pin=pin;
        this.cardNo= cardNo;
        System.out.println("BalanceEnquiry");
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1=new JLabel("Your Current Balance Rs  ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD,16 ));
        label1.setBounds(430,230,400,35);
        l3.add(label1);

        label2=new JLabel( );
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD,16 ));
        label2.setBounds(430,260,700,35);
        l3.add(label2);

        label3=new JLabel( );
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("System", Font.BOLD,16 ));
        label3.setBounds(440,140,700,85);
        l3.add(label3);

        b1=new JButton("Back");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);


        int balance=0;
        try
        {
            Con c=new Con();
            //String cardNumber="";
            ResultSet rsCard = c.statement.executeQuery("SELECT card_number FROM login WHERE pin = '" + pin + "' and card_number='"+cardNo+"'");
            if (rsCard.next()) {
                String cardNumber = rsCard.getString("card_number");
                label3.setText("CardNumber:"+cardNumber);
            }

            ResultSet resultSet=c.statement.executeQuery("select amount,type from bank where pin='"+pin+"' and card_number='"+cardNo+"'");
            while(resultSet.next())
            {
                String type = resultSet.getString("type");
                int amount = Integer.parseInt(resultSet.getString("amount"));
                if (type.equals("Deposit")){
                    balance +=amount;
                }
                else if (type.equals("withdrawl")){
                    balance -=Integer.parseInt(resultSet.getString("amount"));
                }
            }
            label2.setText("Your current balance is Rs:"+balance);
        }
         catch(Exception E)
         {
             E.printStackTrace();
         }


        label2.setText(""+balance);
        setLayout(null);
        setSize(1550,1000);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin, cardNo);
    }

    public static void main(String[] args) {

        new BalanceEnquriy("", "");
    }
}
