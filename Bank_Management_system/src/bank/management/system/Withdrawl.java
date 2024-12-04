package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {
    String pin;
    private JTextField  email;  // This would be set up in your UI
    private JTextField otpField;
    private JButton verifyOTPButton;      // Button to verify OTP
    private String generatedOTP;

    TextField textField;

    JButton b1,b2,b3;

    String cardNo;

    public Withdrawl(String pin, String cardNo)
    {
        this.pin=pin;
        this.cardNo=cardNo;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1=new JLabel("Maximum withdrawl is Rs.10,000  ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD,16 ));
        label1.setBounds(460,180,400,35);
        l3.add(label1);

        JLabel label2=new JLabel("Please Enter Your Amount ");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD,16 ));
        label2.setBounds(460,220,700,35);
        l3.add(label2);

        textField=new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,290,25);
        textField.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(textField);

        JLabel label4=new JLabel(" Enter Email : ");
        label4.setForeground(Color.WHITE);
        label4.setFont(new Font("System", Font.BOLD,16 ));
        label4.setBounds(460,290,700,35);
        l3.add(label4);

        email=new JTextField();
        email.setBackground(new Color(65,125,128));
        email.setForeground(Color.WHITE);
        email.setBounds(460,330,300,25);
        email.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(email);


        b1=new JButton("Generate OTP");
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
        if (e.getSource() ==b1) {
            try {


                String amount=textField.getText().trim();
                Date date = new Date();
                String Email=email.getText().trim();

                String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
                if (textField.getText().equals("")||(!textField.getText().matches("\\d+"))) {
                    JOptionPane.showMessageDialog(null, "Please enter the valid amount you want to withdarw");
                }

                else if (email.getText().equals("")||(!Email.matches(emailPattern))){
                    JOptionPane.showMessageDialog(null, "Please enter the valid email field");
                }

                int maxamount = 10000;
                int amountint=Integer.parseInt(amount);

               if (amountint>maxamount){
                   JOptionPane.showMessageDialog(null, "Please enter the amount below 10,000");
                }

               else {
                   Con c = new Con();
                   System.out.println("emial:"+email);
                   System.out.println("email:"+Email);
                   System.out.println("Pin:"+pin);
                   System.out.println("Cardnumber :"+cardNo);
                   String q = "select email from signup where pin=? and card_number=?";
                   PreparedStatement pstmt = c.connection.prepareStatement(q);


                   pstmt.setString(1, pin);
                   pstmt.setString(2, cardNo);


                   ResultSet resultet = pstmt.executeQuery();

                   if (resultet.next()) {

                       System.out.println("valid");

                       String rstvalue = resultet.getString("email");

                       // Compare with the entered phone number
                       if (rstvalue.equals(Email)) {
                           JOptionPane.showMessageDialog(null, "Email matches successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);


                           new OTPClass(pin, Email,amount,cardNo);


                       } else {
                           JOptionPane.showMessageDialog(null, "Email does not match", "Error", JOptionPane.ERROR_MESSAGE);
                       }

                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null, "No matching records are found", "Error", JOptionPane.ERROR_MESSAGE);
                   }
               }

            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        else if (e.getSource()==b2)
        {
            setVisible(false );
            new main_Class(pin, cardNo);
        }
    }

    public static void main(String[] args) {

        new Withdrawl("", "");
    }
}
