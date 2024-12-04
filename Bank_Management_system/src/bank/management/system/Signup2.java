package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {

    JComboBox comboBox,comboBox2,comboBox3,comboBox4,comboBox5;
    JTextField textpan,textaadhar;
    JRadioButton r1,r2,e1,e2;
    JButton next,back;
    String formno;
    String cardNo;
    Signup2(String formno,String cardNo)
    {

        super("APPLICATION FORM");

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));//to get the image link
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(150,5,100,100);
        add(image);

        this.formno=formno;

        JLabel l2=new JLabel("Additional Details");
        l2.setFont(new Font("Raleway",Font.BOLD,22));
        l2.setBounds(310,20,600,40);
        add(l2);

        JLabel l3=new JLabel("Religion:");
        l3.setFont(new Font("Raleway",Font.BOLD,18));
        l3.setBounds(100,120,100,43);
        add(l3);

        String religion[]={"Hindu","Muslim","Christian","Other"};
        comboBox=new JComboBox(religion);
        comboBox.setBackground(new Color(252,208,76));
        comboBox.setFont(new Font("Raleway",Font.BOLD,18));
        comboBox.setBounds(350,120,320,30);
        add(comboBox);

        JLabel l4=new JLabel("Category:");
        l4.setFont(new Font("Raleway",Font.BOLD,18));
        l4 .setBounds(100,170,320,30);
        add(l4);

        String category[]={"General","OBC","SC","ST","Other"};
        comboBox2=new JComboBox(category);
        comboBox2.setBackground(new Color(252,208,76));
        comboBox2.setFont(new Font("Raleway",Font.BOLD,18));
        comboBox2.setBounds(350,170,320,30);
        add(comboBox2);

        JLabel l5=new JLabel("Income :");
        l5.setFont(new Font("Raleway",Font.BOLD,18));
        l5 .setBounds(100,220,100,30);
        add(l5);

        String income[]={"Null","<1,50,000","<2,50,000","5,00,000","Uptp 10,00,000","Above 10,00,000"};
        comboBox3=new JComboBox(income);
        comboBox3.setBackground(new Color(252,208,76));
        comboBox3.setFont(new Font("Raleway",Font.BOLD,18));
        comboBox3.setBounds(350,220,320,30);
        add(comboBox3);


        JLabel l6=new JLabel("Educational :");
        l6.setFont(new Font("Raleway",Font.BOLD,18));
        l6 .setBounds(100,270,150,30);
        add(l6);

        String educational[]={"Non-Graduate","Graduate","Post-Graduate","Doctorate","Others"};
        comboBox4=new JComboBox(educational);
        comboBox4.setBackground(new Color(252,208,76));
        comboBox4.setFont(new Font("Raleway",Font.BOLD,18));
        comboBox4.setBounds(350,270,320,30);
        add(comboBox4);

        JLabel l7=new JLabel("Occupation :");
        l7.setFont(new Font("Raleway",Font.BOLD,18));
        l7 .setBounds(100,340,150,30);
        add(l7);

        String Occupation[]={"Salaried","Self-Employed","Business","Student","Retired","Others"};
        comboBox5=new JComboBox(Occupation);
        comboBox5.setBackground(new Color(252,208,76));
        comboBox5.setFont(new Font("Raleway",Font.BOLD,18));
        comboBox5.setBounds(350,340,320,30);
        add(comboBox5);


        JLabel l8=new JLabel("PAN Number :");
        l8.setFont(new Font("Raleway",Font.BOLD,18));
        l8 .setBounds(100,390,320,30);
        add(l8);

        textpan=new JTextField();
        textpan.setFont(new Font("Raleway",Font.BOLD,18));
        textpan.setBounds(350,390,320,30);
        add(textpan);


        JLabel l9=new JLabel("Aadhar Number :");
        l9.setFont(new Font("Raleway",Font.BOLD,18));
        l9 .setBounds(100,440,320,30);
        add(l9);

        textaadhar=new JTextField();
        textaadhar.setFont(new Font("Raleway",Font.BOLD,18));
        textaadhar.setBounds(350,440,320,30);
        add(textaadhar);

        JLabel l10=new JLabel("Communication :");
        l10.setFont(new Font("Raleway",Font.BOLD,18));
        l10.setBounds(100,490,180,30);
        add(l10);

        r1=new JRadioButton("Email");
        r1.setFont(new Font("Raleway",Font.BOLD,14));
        r1.setBackground(new Color(252,208,76));
        r1.setBounds(350,490,100,30);
        add(r1);


        r2=new JRadioButton("Phone");
        r2.setFont(new Font("Raleway",Font.BOLD,14));
        r2.setBackground(new Color(252,208,76));
        r2.setBounds(460,490,100,30);
        add(r2);

        ButtonGroup buttonGroup1=new ButtonGroup();
        buttonGroup1.add(r1);
        buttonGroup1.add(r2);


        JLabel l11=new JLabel("Source of fund :");
        l11.setFont(new Font("Raleway",Font.BOLD,18));
        l11.setBounds(100,540,180,30);
        add(l11);

        e1=new JRadioButton("Salary ");
        e1.setFont(new Font("Raleway",Font.BOLD,14));
        e1.setBackground(new Color(252,208,76));
        e1.setBounds(350,540,100,30);
        add(e1);


        e2=new JRadioButton("Business Income ");
        e2.setFont(new Font("Raleway",Font.BOLD,14));
        e2.setBackground(new Color(252,208,76));
        e2.setBounds(460,540,200,30);
        add(e2);

        ButtonGroup buttonGroup2=new ButtonGroup();
        buttonGroup2.add(e1);
        buttonGroup2.add(e2);


        JLabel l12=new JLabel("Form No :");
        l12.setFont(new Font("Raleway",Font.BOLD,14));
        l12.setBounds(700,10,100,30);
        add(l12);


        JLabel l13=new JLabel(formno);
        l13.setFont(new Font("Raleway",Font.BOLD,14)); //if the size is bigg reduceitt here
        l13.setBounds(765,10,60,30);
        add(l13);


        next=new JButton("Next");
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setForeground(Color.BLACK);
        next.setBackground(Color.WHITE);
        next.setBounds(570,640,100,30);
        next.addActionListener(this);
        add(next);

        back=new JButton("Back");
        back.setFont(new Font("Raleway",Font.BOLD,14));
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(460,640,100,30);
        back.addActionListener(this);
        add(back);

        setLayout(null);
       setSize(850,750);
       setLocation(450,80);
       getContentPane().setBackground(new Color(252,208,76));
       setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String rel=(String) comboBox.getSelectedItem();
        String cate=(String) comboBox2.getSelectedItem();
        String inc=(String) comboBox3.getSelectedItem();
        String edu=(String) comboBox4.getSelectedItem();
        String occ=(String) comboBox5.getSelectedItem();

        String pan=textpan.getText();
        String panPattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        String addhar=textaadhar.getText();


        String Communication =null;
        if((r1.isSelected())){
            Communication="Email";
        } else if (r2.isSelected()) {
            Communication ="Phone";
        }

        String Sourceoffunds=null;
        if((e1.isSelected())){
            Sourceoffunds="Salary";
        } else if (e2.isSelected()) {
            Sourceoffunds="Business Income";
        }


        if (e.getSource()==next) {
            try {

                if (textpan.getText().equals("") || textaadhar.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill all the Fields");
                } else if (!textpan.getText().matches(panPattern) || textpan.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, " Please enter the correct PAN cardnumber.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } else if (!textaadhar.getText().matches("\\d{12}+") || textaadhar.getText().equals("")||  textaadhar.getText().length()!=12 ) {
                    JOptionPane.showMessageDialog(null, " Please enter the correct aadhar cardnumber.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } else if (Communication == null) {
                    JOptionPane.showMessageDialog(null, "Please select a Communication Preference .", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } else if (Sourceoffunds == null) {
                    JOptionPane.showMessageDialog(null, "Please select a Source of funds .", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
                else if (e.getSource()==next) {
                    try {
                        System.out.println("Signup2");
                        Con c1 = new Con();
                        String q = "insert into Signuptwo values('" + formno + "','" + rel + "','" + cate + "','" + inc + "','" + edu + "','" + occ + "','" + pan + "','" + addhar + "','" + Communication + "','" + Sourceoffunds + "')";
                        c1.statement.executeUpdate(q);
                        new Signup3(formno,cardNo);
                        setVisible(false);
                    } catch (Exception E) {
                        E.printStackTrace();
                    }

                }
            }catch (Exception ee)
            {
                ee.printStackTrace();
            }

        }
        else if (e.getSource() == back) {
            setVisible(false); // Hide this frame
            new Signup("",""); // Go back to the previous signup page
        }

    }


    public static void main(String[] args) {

        new Signup2("","");
    }
}
