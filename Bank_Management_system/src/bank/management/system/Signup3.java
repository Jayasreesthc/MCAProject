package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton r1,r2,r3,r4;

    JTextField cardnotext,pintext;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JButton s,c;

    String  formno;
    String cardNo;
    Signup3(String formno,String cardNo){
        this.formno=formno;
        this.cardNo=cardNo;
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));//to get the image link
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(150,5,100,100);
        add(image);

        /*JLabel l1=new JLabel("Page 3");
        l1.setFont(new Font("Raleway",Font.BOLD,22));
        l1.setBounds(280,40,400,40);
        add(l1);*/

        JLabel l2=new JLabel("Account Details");
        l2.setFont(new Font("Raleway",Font.BOLD,22));
        l2.setBounds(280,50,400,40);
        add(l2);


        JLabel l3=new JLabel("Account Type:");
        l3.setFont(new Font("Raleway",Font.BOLD,18));
        l3.setBounds(100,140,200,30);
        add(l3);

        r1=new JRadioButton("Savings Account ");
        r1.setFont(new Font("Raleway",Font.BOLD,16));
        r1.setBackground(new Color(215,252,252));
        r1.setBounds(100,180,200,30);
        add(r1);

        r2=new JRadioButton("Fixed Deposit Account ");
        r2.setFont(new Font("Raleway",Font.BOLD,16));
        r2.setBackground(new Color(215,252,252));
        r2.setBounds(350,180,300,30);
        add(r2);

        r3=new JRadioButton("Current Account ");
        r3.setFont(new Font("Raleway",Font.BOLD,16));
        r3.setBackground(new Color(215,252,252));
        r3.setBounds(100,220,250,30);
        add(r3);

        r4=new JRadioButton("Recurring  Deposit Account");
        r4.setFont(new Font("Raleway",Font.BOLD,16));
        r4.setBackground(new Color(215,252,252));
        r4.setBounds(350,220,250,30);
        add(r4);

        ButtonGroup buttonGroup1=new ButtonGroup();
        buttonGroup1.add(r1);
        buttonGroup1.add(r2);
        buttonGroup1.add(r3);
        buttonGroup1.add(r4);

        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);
        buttonGroup.add(r3);
        buttonGroup.add(r4);


        JLabel l4=new JLabel("Card Number:");
        l4.setFont(new Font("Raleway",Font.BOLD,18));
        l4.setBounds(100,300,200,30);
        add(l4);

        JLabel l5=new JLabel(" (Your 16 digit Card Number:)");
        l5.setFont(new Font("Raleway",Font.BOLD,15));
        l5.setBounds(100,330,220,20);
        add(l5);

        JLabel l6=new JLabel(" XXXX-XXXX-XXXX-4841");
        l6.setFont(new Font("Raleway",Font.BOLD,15));
        l6.setBounds(320,300,250,20);
        add(l6);

        cardnotext=new JTextField();
        cardnotext.setFont(new Font("Raleway",Font.BOLD,18));
        cardnotext.setBounds(550,300,250,20);
        add(cardnotext);

        JLabel l7=new JLabel(" (It would appear on atm card/cheque Book and Statements)");
        l7.setFont(new Font("Raleway",Font.BOLD,14));
        l7.setBounds(330,330,500,20);
        add(l7);



        JLabel l8=new JLabel(" PIN : ");
        l8.setFont(new Font("Raleway",Font.BOLD,14));
        l8.setBounds(100,370,200,30);
        add(l8);

        JLabel l9=new JLabel(" XXXX");
        l9.setFont(new Font("Raleway",Font.BOLD,14));
        l9.setBounds(330,370,200,30);
        add(l9);

        pintext=new JTextField();
        pintext.setFont(new Font("Raleway",Font.BOLD,18));
        pintext.setBounds(330,400,100,20);
        add(pintext);

        JLabel l10=new JLabel("(4- digit Password:)");
        l10.setFont(new Font("Raleway",Font.BOLD,14));
        l10.setBounds(100,400,200,20);
        add(l10);

        JLabel l11=new JLabel(" Services Required : ");
        l11.setFont(new Font("Raleway",Font.BOLD,16));
        l11.setBounds(100,470,200,30);
        add(l11);

        /*c1=new JCheckBox("ATM CARD");
        c1.setBackground(new Color(215,252,252));
        c1.setFont(new Font("Raleway",Font.BOLD,16 ));
        c1.setBounds(100,500,200,30);
        add(c1);

        c2=new JCheckBox("Internet Banking");
        c2.setBackground(new Color(215,252,252));
        c2.setFont(new Font("Raleway",Font.BOLD,16 ));
        c2.setBounds(350,500,200,30);
        add(c2);*/


        c3=new JCheckBox("Mobile Banking");
        c3.setBackground(new Color(215,252,252));
        c3.setFont(new Font("Raleway",Font.BOLD,16 ));
        c3.setBounds(100,550,200,30);
        add(c3);

        c4=new JCheckBox("Email Alerts");
        c4.setBackground(new Color(215,252,252));
        c4.setFont(new Font("Raleway",Font.BOLD,16 ));
        c4.setBounds(350,550,200,30);
        add(c4);

        c5=new JCheckBox("Cheque Book");
        c5.setBackground(new Color(215,252,252));
        c5.setFont(new Font("Raleway",Font.BOLD,16 ));
        c5.setBounds(100,600,200,30);
        add(c5);

        c6=new JCheckBox("E-Statement");
        c6.setBackground(new Color(215,252,252));
        c6.setFont(new Font("Raleway",Font.BOLD,16 ));
        c6.setBounds(350,600,200,30);
        add(c6);


         c7=new JCheckBox("I here by declares that the above entered details correct to the best of my knowledge.",true);
        c7.setBackground(new Color(215,252,252));
        c7.setFont(new Font("Raleway",Font.BOLD,16 ));
        c7.setBounds(100,680,700,20);
        add(c7);

        JLabel l12=new JLabel("Form No:");
        //JLabel l12=new JLabel("Form No :");
        l12.setFont(new Font("Raleway",Font.BOLD,18));
        l12.setBounds(650,10,100,30);
        add(l12);


        JLabel l13=new JLabel(formno);
        l13.setFont(new Font("Raleway",Font.BOLD,16));
        l13.setBounds(750,10,200,30);
        add(l13);


         s=new JButton("Submit");
         s.setFont(new Font("Raleway",Font.BOLD,14));
         s.setBackground(Color.BLACK);
         s.setForeground(Color.WHITE);
         s.setBounds(420,720,100,30);
         s.addActionListener(this);
         add(s);


        c=new JButton("Cancel");
        c.setFont(new Font("Raleway",Font.BOLD,14));
        c.setBackground(Color.BLACK);
        c.setForeground(Color.WHITE);
        c.setBounds(250,720,100,30);
        c.addActionListener(this);
        add(c);

        getContentPane().setBackground(new Color(215,252,252));
        setSize(850,800);
        setLayout(null);
        setLocation(400,20);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String atype=null;
        if(r1.isSelected())
        {
            atype="Saving Account";
        } else if (r2.isSelected()) {

            atype="Fixed Deposut Account";
        } else if (r3.isSelected()) {

            atype="Current Account";
        }
        else {
            atype="recurring Deposit Account";
        }

        /*Random ran=new Random();
        long first7=(ran.nextLong()%90000000L)+1409963000000000L;
        String cardno =" "+Math.abs(first7);

        long first3=(ran.nextLong()%9000L)+1000L;
        String pin=" "+Math.abs(first3);*/


        String cardno = cardnotext.getText();
        String pin=pintext.getText();


        String fac="";
        if (c3.isSelected()) {
            fac=fac+"Mobile Banking";
        }
        else if (c4.isSelected()) {

            fac=fac+"Email Alerts" ;
        }
        else if (c5.isSelected()) {

            fac=fac+"Cheque book" ;
        } else if (c6.isSelected()) {
            fac=fac+"E-Statement";

        }
        try
        {

            if (e.getSource()==s) {
                    if (atype == null ) {
                        JOptionPane.showMessageDialog(null, "Fill all fields");
                    } else if (!cardno.matches("\\d{16}") ||cardno.isEmpty() ) { // Validate card number
                        JOptionPane.showMessageDialog(null, "Card Number must be a 16-digit number");
                    } else if (!pin.matches("\\d{4}")||pin.isEmpty()) { // Validate PIN to be 4 digits
                        JOptionPane.showMessageDialog(null, "PIN must be a 4-digit number");
                    }
                    else if (fac.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill all fields");
                }
                    else if (!c7.isSelected()) { // Validation for checkbox
                        JOptionPane.showMessageDialog(null, "You must agree to the terms by selecting the checkbox");
                    }
                    try {
                        System.out.println("Signup3");
                        Con c1 = new Con();
                        String q1 = "insert into  signupthree values ('" + formno + "','" + atype + "','" + cardno + "','" + pin + "','" + fac + "')";
                        c1.statement.executeUpdate(q1);
                        JOptionPane.showMessageDialog(null, "Card Number:" + cardno + "\n Pin:" + pin);
                        if (!cardno.isEmpty()) {
                            Con c = new Con();
                            cardno = cardnotext.getText();
                            pin = pintext.getText();
                            String q = "select card_number from login where card_number=? AND pin=?";
                            PreparedStatement pstmt = c.connection.prepareStatement(q);
                            pstmt.setString(1, cardno);
                            pstmt.setString(2, pin);
                            ResultSet resultSet = pstmt.executeQuery();//resultSet.next() && q==cardno
                            if (resultSet.next()) {


                                    JOptionPane.showMessageDialog(null, "Card number Matches!", "Success", JOptionPane.INFORMATION_MESSAGE);

                                setVisible(false);
                                new main_Class(pin, cardno);
                            } else {

                                JOptionPane.showMessageDialog(null, " Card number is not Match that you are entered in login page.", "New User", JOptionPane.INFORMATION_MESSAGE);
                                //setVisible(false);
                                //new Signup();
                            }


                        }
                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();
                    }


            } else if (e.getSource()==c) {
                setVisible(false);
                new Signup2(pin,cardNo);
            }
        }
        catch(Exception E)
        {

        }

    }

    public static void main(String[] args) {

        new Signup3("","");
    }
}
