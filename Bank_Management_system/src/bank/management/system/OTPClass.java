package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
public class OTPClass extends JFrame implements ActionListener {
    String amount;


    private String generatedOTP;

    TextField textField;

    JButton b1;
    String pin;
    String cardNo;
    String email;
    private Timer otpTimer;
    private int timeLeft=60;
    JLabel timerLabel;
    public OTPClass(String pin,String email,String amount,String cardNo){
        this.pin=pin;
        this.cardNo=cardNo;
        this.amount=amount;
        this.email = email;

        generatedOTP = OTPGneraTor.generateOTP();  // Assuming you have an OTP generator class

        // Send OTP to email
        String subject = "Welcome! Mam/Sir to the ATM Interface Management System... Your OTP for Withdrawal";
        String message = "Your OTP is: " + generatedOTP;
        Emailsender.sendEmail(email, subject, message);


        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1=new JLabel("Enter OTP ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD,16 ));
        label1.setBounds(460,220,700,35);
        l3.add(label1);

        textField=new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,200,25);
        textField.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(textField);

        b1=new JButton("Withdraw");
        b1.setBounds(700,362,150,35);
        b1.setFont(new Font("Raleway",Font.BOLD,18));
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        timerLabel = new JLabel("Time left: " + timeLeft + " seconds");
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        timerLabel.setBounds(460, 300, 300, 35);
        l3.add(timerLabel);


        startTimer();

        setLayout(null);
        setSize(1550,1000);
        setLocation(0,0);
        setVisible(true);




    }
    private void startTimer() {
        otpTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String OTP = textField.getText();
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText("Time left:"+timeLeft+"seconds");
                } else {
                    otpTimer.stop();
                    if (OTP.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "OTP expired.Please generate another OTP .");

                        setVisible(false);
                        new Withdrawl("", "");
                    }
                }
            }
        });

        otpTimer.start();
    }

        @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() ==b1) {
            try
            {
                System.out.println("OTPClass");
                String OTP = textField.getText();
                if (textField.getText().equals("")||OTP.length()!=6||(!textField.getText().matches("\\d+"))) {
                    JOptionPane.showMessageDialog(null, "Please enter the valid OTP to withdarw");
                }
                else {

                    if (OTP.equals(generatedOTP)) {
                        JOptionPane.showMessageDialog(null, "OTP verified successfully. Proceed with withdrawal.");
                        // Add your withdrawal logic here

                        Con c=new Con();


                        Date date = new Date();
                        ResultSet resultset = c.statement.executeQuery("select * from bank where pin='" + pin + "' and card_number='"+cardNo+"'");
                           int balance = 0;
                            while (resultset.next()) {
                                if (resultset.getString("type").equals("Deposit")) {
                                    balance += Integer.parseInt(resultset.getString("amount"));
                                } else {
                                    balance -= Integer.parseInt(resultset.getString("amount"));
                                }
                            }
                            if (balance < Integer.parseInt(amount)) {
                                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                               return;
                            }

                            c.statement.executeUpdate("insert into bank values('" + pin + "','" + date + "','withdrawl','" + amount + "','"+cardNo+"')");
                            JOptionPane.showMessageDialog(null, "Rs. " + amount + "  Withdrawl Suceessfully");
                           setVisible(false);
                            new main_Class(pin, cardNo);

                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid OTP. Please try again.");
                    }
                }



        }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new OTPClass("","","","");
    }
}
