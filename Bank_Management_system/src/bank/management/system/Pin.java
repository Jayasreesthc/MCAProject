package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Pin extends JFrame implements ActionListener {
    JButton b1, b2;
    JPasswordField p1,p2;

    JTextField textField2 ;
    String pin;
    String cardNo;
    Pin(String pin, String cardNo) {
        this.pin=pin;
        this.cardNo=cardNo;

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1=new JLabel("Change Your PIN  ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD,18 ));
        label1.setBounds(470,160,400,35);
        l3.add(label1);

        JLabel label2=new JLabel("New PIN  ");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD,16 ));
        label2.setBounds(450,210,150,35);
        l3.add(label2);

        p1=new JPasswordField();
        p1.setBackground(new Color(65,125,128));
        p1.setForeground(Color.WHITE);
        p1.setBounds(600,220,180,25);
        p1.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(p1);

        JLabel label3=new JLabel("Enter old-PIN  ");
        label3.setForeground(Color.WHITE);
        label3.setFont(new Font("System", Font.BOLD,16 ));
        label3.setBounds(430,250,400,35);
        l3.add(label3);

        p2=new JPasswordField();
        p2.setBackground(new Color(65,125,128));
        p2.setForeground(Color.WHITE);
        p2.setBounds(600,255,180,25);
        p2.setFont(new Font("Raleway",Font.BOLD,22));
        l3.add(p2);

        JLabel label4=new JLabel("Enter card-number  ");
        label4.setForeground(Color.WHITE);
        label4.setFont(new Font("System", Font.BOLD,16 ));
        label4.setBounds(430,290,400,35);
        l3.add(label4);

        textField2=new JTextField();
        textField2.setBackground(new Color(65,125,128));
        textField2.setForeground(Color.WHITE);
        textField2.setBounds(600,290,180,25);
        textField2.setFont(new Font("Raleway",Font.BOLD,17));
        l3.add(textField2);

        b1=new JButton("CHANGE");
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

       setSize(1550,1080);
       setLayout(null);
       setLocation(0,0);
       setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

             Con c=new Con();

            System.out.println("Pin");
            String pin1 = p1.getText();
            String pin2 = p2.getText().trim();
            String cardno=textField2.getText();

            if ((!pin1.isEmpty())&&(!pin2.isEmpty())&&pin1.equals(pin2)) {
                JOptionPane.showMessageDialog(null, "Both pin are same!");
                return;
            }
            if (e.getSource() == b1) {
                if (p1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter new PIN");
                    return;
                }
                if (p2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-enter  New PIN");
                    return;
                }
                if (cardno.equals("")||cardno.length()!=16) {
                    JOptionPane.showMessageDialog(null, "Enter correct card number ");
                    return;
                }else {
                    c = new Con();
                    String q = "select * from login where trim(pin)=? and trim(card_number)=?";
                    PreparedStatement pstmt = c.connection.prepareStatement(q);

                    pstmt.setString(1, pin2);
                    pstmt.setString(2, cardno);
                    ResultSet resultSet = pstmt.executeQuery();
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "PIN Change Failed: No records found with the current PIN.");
                        return;
                    }

                    String q1 = "update bank set pin=? where pin=? and card_number='"+cardNo+"'";
                    String q2 = "update login set pin=? where pin=? and card_number=?";
                    String q3 = "update signupthree set pin=? where pin=? and card_number=?";
                    String q4 = "update signup set pin=? where pin=? and card_number='"+cardNo+"'";

                    System.out.println("Executing query: " + q1);
                    System.out.println("Executing query: " + q2);
                    System.out.println("Executing query: " + q3);
                    System.out.println("Executing query: " + q4);


                    PreparedStatement updateStmt1 = c.connection.prepareStatement(q1);
                    PreparedStatement updateStmt2 = c.connection.prepareStatement(q2);
                    PreparedStatement updateStmt3 = c.connection.prepareStatement(q3);
                    PreparedStatement updateStmt4 = c.connection.prepareStatement(q4);

                    updateStmt1.setString(1, pin1);
                    updateStmt1.setString(2, pin2);
                    //updateStmt1.setString(3, cardno);
                    updateStmt2.setString(1, pin1);
                    updateStmt2.setString(2, pin2);
                    updateStmt2.setString(3, cardno);
                    updateStmt3.setString(1, pin1);
                    updateStmt3.setString(2, pin2);
                    updateStmt3.setString(3, cardno);
                    updateStmt4.setString(1, pin1);
                    updateStmt4.setString(2, pin2);
                    //updateStmt4.setString(3, cardno);


                    int result1 = updateStmt1.executeUpdate();
                    int result2 = updateStmt2.executeUpdate();
                    int result3 = updateStmt3.executeUpdate();
                    int result4 = updateStmt4.executeUpdate();

                    // Debugging: Check how many rows were affected
                    System.out.println("Rows affected in bank: " + result1);
                    System.out.println("Rows affected in login: " + result2);
                    System.out.println("Rows affected in signupthree: " + result3);
                    System.out.println("Rows affected in signup: " + result4);


                    if (result1 > 0 || result2 > 0 || result3 > 0||result4>0) {


                        JOptionPane.showMessageDialog(null, "PIN Changed Successfully, the next time when you login you can use the new pin");

                    }
                }
            }
            if (e.getSource() == b2)
            {
                if (pin1.length()==4) {
                    this.pin = pin1;
                    new main_Class(pin, cardNo);
                    setVisible(false);
                }else {
                    this.pin=pin;
                    new main_Class(pin,cardNo);
                }
            }


        }

         /*   if (pin1.equals(pin2)) {
                JOptionPane.showMessageDialog(null, "New PIN cannot be the same as the old PIN.");
                return;
            }

            // Check if any field is empty
            if (pin1.isEmpty() || pin2.isEmpty() || cardno.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required.");
                return;
            }

            // Check if card number is valid (16 digits)
            if (cardno.length() != 16 || !cardno.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Enter a valid 16-digit card number.");
                return;
            }

            // Check if the old PIN is present in the database
            String checkQuery = "SELECT * FROM login WHERE trim(pin) = ? AND trim(card_number) = ?";
            PreparedStatement pstmt = c.connection.prepareStatement(checkQuery);
            pstmt.setString(1, pin2);
            pstmt.setString(2, cardno);
            ResultSet resultSet = pstmt.executeQuery();

            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Old PIN does not match any records.");
                return;
            }

            // If the old PIN is present, proceed to update it
            String updateQuery1 = "UPDATE bank SET pin = ? WHERE pin = ?";
            String updateQuery2 = "UPDATE login SET pin = ? WHERE pin = ?";
            String updateQuery3 = "UPDATE signupthree SET pin = ? WHERE pin = ?";

            PreparedStatement updateStmt1 = c.connection.prepareStatement(updateQuery1);
            PreparedStatement updateStmt2 = c.connection.prepareStatement(updateQuery2);
            PreparedStatement updateStmt3 = c.connection.prepareStatement(updateQuery3);

            updateStmt1.setString(1, pin1);
            updateStmt1.setString(2, pin2);
            updateStmt2.setString(1, pin1);
            updateStmt2.setString(2, pin2);
            updateStmt3.setString(1, pin1);
            updateStmt3.setString(2, pin2);

            int result1 = updateStmt1.executeUpdate();
            int result2 = updateStmt2.executeUpdate();
            int result3 = updateStmt3.executeUpdate();

            if (result1 > 0 || result2 > 0 || result3 > 0) {
                JOptionPane.showMessageDialog(null, "PIN Changed Successfully");
                //setVisible(false);
                //new main_Class(pin1); // Update with new PIN
            }
            else if (e.getSource() == b2) {
                new main_Class(pin);
                setVisible(false);
            }else {
                JOptionPane.showMessageDialog(null, "PIN Change Failed. Please try again.");
            }

        } */

            catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin("", "");
    }
}
