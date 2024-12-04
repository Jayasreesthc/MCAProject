package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    String pin;

    TextField textField;
    JPasswordField textFieldp;

    JButton b1,b2;
    String cardNo;
    Deposit(String pin, String cardNo)
    {
        this.pin=pin;
        this.cardNo=cardNo;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1=new JLabel("Enter AMOUNT YOU WANT TO  DEPOSIT  ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD,16 ));
        label1.setBounds(460,180,400,35);
        l3.add(label1);

        textField=new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,230,320,25);
        textField.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(textField);

        JLabel label2=new JLabel("Enter your Pin:  ");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD,16 ));
        label2.setBounds(470,260,400,35);
        l3.add(label2);

        textFieldp=new JPasswordField();
        textFieldp.setBackground(new Color(65,125,128));
        textFieldp .setForeground(Color.WHITE);
        textFieldp.setBounds(470,300, 220,25);
        textFieldp.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(textFieldp);

        b1=new JButton("DEPOSIT");
        b1.setBounds(700,362,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("BACK");
        b2.setBounds(700,406,150,35);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550,1000);
        setLocation(0,0);
        setVisible(true);
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            String pin1=textFieldp.getText();
            Date date = new Date();
            if (e.getSource()==b1)
            {
                System.out.println("Deposit");
                if (textField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please enter the amount you want to Deposit");
                }
                else if (textFieldp.getText().equals("")||(pin1.length()!=4))
                {
                    JOptionPane.showMessageDialog(null,"Please enter the pin");
                }
                else if(!pin1.equals("")&&pin1.length()==4&&pin1.equals(pin))
                {
                    Con c=new Con();
                    c.statement.executeUpdate("insert into bank values('"+pin+"','"+date+"','Deposit','"+amount+"','"+cardNo+"')");
                    if (pin.length()==4)
                    {
                        c=new Con();
                        System.out.println(cardNo);
                        String q="select pin from login where trim(pin)=? and card_number=?";
                        PreparedStatement pstmt = c.connection.prepareStatement(q);
                        pstmt.setString(1, pin);
                        pstmt.setString(2, cardNo);
                        ResultSet resultSet = pstmt.executeQuery();
                        if (resultSet.next())
                        {

                            JOptionPane.showMessageDialog(null, "Pin matches!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null,"Rs."+amount+" Deposited Successfully");
                            //setVisible(false);

                        }
                        else {

                            JOptionPane.showMessageDialog(null," pin doesnot match ,enter the correct pin.", "New User", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Pin does not match. Enter the correct pin.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource()==b2) {

                setVisible(false);
                new main_Class(pin, cardNo);

            }
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
       /*
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            String pin = textFieldp.getText();
            Date date = new Date();

            if (e.getSource() == b1) {
                // Validate if the amount is entered
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to Deposit");
                    return;
                }

                // Validate if the PIN is entered and is of correct length
                if (pin.equals("") || pin.length() != 4) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid 4-digit PIN.");
                    return;
                }

                Con c = new Con();

                // Check if the PIN exists in the login table
                String q = "SELECT pin FROM Login WHERE trim(pin) = ?";
                PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, pin);

                ResultSet resultSet = pstmt.executeQuery();

                // Debugging statement to check if ResultSet has any records
                if (resultSet.next()) {
                    // If PIN matches, proceed with the deposit
                    JOptionPane.showMessageDialog(null, "Pin matches!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    //String query = "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, 'Deposit', ?)";
                    //pstmt = c.connection.prepareStatement(query);
                    //pstmt.setString(1, pin);
                    //pstmt.setString(2, date.toString());
                    //pstmt.setString(3, amount);
                    //pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Rs." + amount + " Deposited Successfully");
                    setVisible(false);
                    new main_Class(pin);
                } else {
                    // If PIN does not match, show an error message
                    JOptionPane.showMessageDialog(null, "Pin does not match. Enter the correct pin.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == b2) {
                setVisible(false);
                new main_Class(pin);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/



    public static void main(String[] args) {

        new Deposit("", "");
    }
}
