package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame  implements ActionListener {
    JLabel label1,label2,label3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1,button2,button3;
    Login()
    {
        super("bank Management System");//to set the title
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));//to get the image link
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(350,10,100,100);
        add(image);

        ImageIcon ii1=new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));//to get the image link
        Image ii2=ii1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon ii3=new ImageIcon(ii2);
        JLabel iimage=new JLabel(ii3);
        iimage.setBounds(630,350,100,100);
        add(iimage);


        label1=new JLabel("WELCOME TO ATM");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("AvantGrade",Font.BOLD,38));
        label1.setBounds(230,125,450,40);
        add(label1);

        label2=new JLabel("Card No:");
        label2.setFont(new Font("Ralway",Font.BOLD,28));
        label2.setForeground(Color.WHITE);
        label2.setBounds(150,190,375,30);
        add(label2);

        textField2=new JTextField(15);
        textField2.setBounds(325,190,230,30);
        textField2.setFont(new Font("Arial",Font.BOLD,14));
        add(textField2);

        label3=new JLabel("PIN:");
        label3.setFont(new Font("Ralway",Font.BOLD,28));
        label3.setForeground(Color.WHITE);
        label3.setBounds(150,250,375,30);
        add(label3);

        passwordField3=new JPasswordField(15);
        passwordField3.setBounds(325,250,230,30);
        passwordField3.setFont(new Font("Arial",Font.BOLD,14));
        add(passwordField3);

        button1=new JButton("SIGN IN");
        button1.setFont(new Font("Arial",Font.BOLD,14));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);
        button1.setBounds(300,300,100,30);
        button1.addActionListener(this);
        add(button1);

        button2=new JButton("CLEAR");
        button2.setFont(new Font("Arial",Font.BOLD,14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setBounds(430,300,100,30);
        button2.addActionListener(this);
        add(button2);

        button3=new JButton("SIGN UP");
        button3.setFont(new Font("Arial",Font.BOLD,14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setBounds(300,350,230,30);
        button3.addActionListener(this);
        add(button3);

        ImageIcon iii1=new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));//to get the image link
        Image iii2=iii1.getImage().getScaledInstance(850,480, Image.SCALE_DEFAULT);
        ImageIcon iii3=new ImageIcon(iii2);
        JLabel iiimage=new JLabel(iii3);
        iiimage.setBounds(0,0,850,480);
        add(iiimage);

        setLayout(null);
        setSize(850,480);//to set size
        setLocation(450,200);//default open in leftcorner to open it in center
        setVisible(true);//default invisible to seeit set visible
    }


    @Override
    public  void actionPerformed(ActionEvent e)
    {
        try{
            if (e.getSource()==button1)
            {
                System.out.println("Login");
                String cardNo = textField2.getText();
                String pin = new String(passwordField3.getPassword());
                if (!cardNo.matches("\\d+")|| (cardNo.length()!=16)) { // Check if the card number contains only digits
                    JOptionPane.showMessageDialog(null, "Please enter a valid card number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!pin.matches("\\d+")|| (pin.length()!=4))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid pin number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (cardNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the Card No.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // Check if the PIN field is empty
                else if (pin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {

                    Con c=new Con();
                     cardNo=textField2.getText().trim();
                    pin = passwordField3.getText();
                    String q="select * from login where trim(card_number)=? AND pin=?";//and pin ='"+pin+"'

                    PreparedStatement pstmt = c.connection.prepareStatement(q);
                    pstmt.setString(1, cardNo);
                    pstmt.setString(2, pin);
                    ResultSet resultSet = pstmt.executeQuery();


                    if (resultSet.next())
                    {

                        JOptionPane.showMessageDialog(null, "Sign in successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new main_Class(pin,cardNo);
                        setVisible(false);


                    }
                    else {

                        String insertQuery = "INSERT INTO login (card_number, pin) VALUES (?, ?)";
                        PreparedStatement insertStmt = c.connection.prepareStatement(insertQuery);
                        insertStmt.setString(1, cardNo);
                        insertStmt.setString(2, pin);
                        int rowsAffected = insertStmt.executeUpdate();
                        if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null," You are a new user. We welcome you! Please enter your details through the 'signup'.", "New User", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("User registered successfully in the database");
                        new Signup(pin,cardNo);
                        setVisible(false);


                        } else {
                            JOptionPane.showMessageDialog(null, "Sign up failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            System.out.println("Failed to insert the user in the database");
                        }
                    }
                }
            } else if (e.getSource()==button2) {
                textField2.setText("");
                passwordField3.setText("");

            } else if (e.getSource()==button3) {
                System.out.println("Login");
                String cardNo = textField2.getText();
                String pin = new String(passwordField3.getPassword());
                if (cardNo.isEmpty()|| (cardNo.length()!=16)) {
                    JOptionPane.showMessageDialog(null, "Please enter the Card No.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (!pin.matches("\\d+")|| (pin.length()!=4))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid pin number.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                else if (pin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!cardNo.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid card number.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {

                        Con c=new Con();
                        cardNo=textField2.getText().trim();
                        pin = passwordField3.getText();
                        String q="select * from login where trim(card_number)=? AND pin=?";
                        PreparedStatement pstmt = c.connection.prepareStatement(q);
                        pstmt.setString(1, cardNo);
                        pstmt.setString(2, pin);
                        ResultSet resultSet = pstmt.executeQuery();
                        if (resultSet.next())
                        {
                            JOptionPane.showMessageDialog(null, "You are already a registered user. Please sign in.", "Already Registered", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("User found in the database");
                        }
                        else
                        {

                            String insertQuery = "INSERT INTO login (card_number, pin) VALUES (?, ?)";
                            PreparedStatement insertStmt = c.connection.prepareStatement(insertQuery);
                            insertStmt.setString(1, cardNo);
                            insertStmt.setString(2, pin);
                            int rowsAffected = insertStmt.executeUpdate();

                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                // Your sign-up logic goes here
                                System.out.println("User registered successfully in the database");
                                new Signup(pin, cardNo);
                                setVisible(false);
                                //System.out.println("User not found, proceeding to sign-up");

                            }
                        }
                    }
                    catch(Exception ee)
                    {
                        ee.printStackTrace();
                    }
                }

            }
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Login();
    }
}
