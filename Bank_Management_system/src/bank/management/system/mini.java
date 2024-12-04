package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class mini extends JFrame  implements ActionListener {
     String pin;
     JLabel label1,label3,label4;

       JButton button;

       String cardNo;
    mini(String pin, String cardNo)
    {
        this.pin=pin;
        this.cardNo=cardNo;

        getContentPane().setBackground(new Color(255,204,204));
        setSize(400,700);
        setLocation(20,20);
        setLayout(null);


        label1=new JLabel();
        label1.setBounds(20,20,360,500);
        add(label1);

        JLabel label2=new JLabel("Mini Statement") ;
        label2.setFont(new Font("System",Font.BOLD,15));
        label2.setBounds(150,20,200,20);
        add(label2);

        label3=new JLabel();
        label3 .setFont(new Font("System",Font.BOLD,15));
        label3.setBounds(20,70,360,20);
        add(label3);

        label4=new JLabel();
        label4.setBounds(20,500,360,20);
        add(label4);

        try {
                System.out.println("mini");
            Con c = new Con();
            // Query to get the card number based on the pin
            ResultSet resultSet = c.statement.executeQuery("SELECT card_number FROM login WHERE pin = '" + pin + "'");

            while (resultSet.next()){
                String cardNumber = resultSet.getString("card_number");
                label3.setText("Card Number:  "+ cardNumber.substring(0,4) + "XXXXXXXX"+ resultSet.getString("card_number").substring(12));

            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }


        int balance=0;

        try {

            Con c = new Con();


            ResultSet resultSet1 = c.statement.executeQuery("select * from bank where pin='" + pin + "' and card_number='"+cardNo+"' ORDER BY date DESC LIMIT 10");
            while (resultSet1.next())
            {
                label1.setText(label1.getText() + "<html>" + resultSet1.getString("date") + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + resultSet1.getString("type") + "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet1.getString("amount") + "<br><br><html>");
            }

            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin='" + pin + "' and card_number='"+cardNo+"'");
            while (resultSet.next()) {



                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else if (resultSet.getString("type").equals("withdrawl")){
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
            label4.setText("Your Total Balance is Rs:" + balance);

        }
        catch (Exception e2)
        {
            e2.printStackTrace();
        }


        button=new JButton("Exit");
        button.setBounds(20,600,100,25);
        button.addActionListener(this);
        button.setBackground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //label4.setText("Your Total balance is Rs " + balance);
        if (e.getSource()==button)
        {
            setVisible(false);
            new main_Class(pin, cardNo);
        }
    }

    public static void main(String[] args) {

        new mini("", "");
    }
}
