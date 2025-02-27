package hospital.management.system;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Pasient_dischaerge extends JFrame {

    Pasient_dischaerge(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel label  = new JLabel("CHECK-OUT");
        label.setBounds(100,20,150,20);
        label.setFont(new Font("Tahoma",Font.BOLD,20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel label1  = new JLabel("Customer Id :");
        label1.setBounds(30,80,150,20);
        label1.setFont(new Font("Tahoma",Font.BOLD,15));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        Choice choice = new Choice();
        choice.setBounds(200,80,150,25);
        panel.add(choice);

        try{
            conn c =  new conn();
            ResultSet resultSet = c.statement.executeQuery("select*from patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getNString("Number"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label2  = new JLabel("Room No :");
        label2.setBounds(30,130,150,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,15));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        JLabel RNo  = new JLabel("");
        RNo.setBounds(200,130,150,20);
        RNo.setFont(new Font("Tahoma",Font.BOLD,15));
        RNo.setForeground(Color.WHITE);
        panel.add(RNo);

        JLabel label3  = new JLabel("In Time :");
        label3.setBounds(30,180,150,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,15));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JLabel InT  = new JLabel("");
        InT.setBounds(200,180,250,20);
        InT.setFont(new Font("Tahoma",Font.BOLD,15));
        InT.setForeground(Color.WHITE);
        panel.add(InT);

        JLabel label4  = new JLabel("Out Time :");
        label4.setBounds(30,230,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,15));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        Date date = new Date();

        JLabel OuT  = new JLabel(""+date);
        OuT.setBounds(200,230,250,20);
        OuT.setFont(new Font("Tahoma",Font.BOLD,15));
        OuT.setForeground(Color.WHITE);
        panel.add(OuT);

        JButton button = new JButton("Discharge");
        button.setBounds(30,300,120,30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    c.statement.executeUpdate("delete from patient_info where number = '" + choice.getSelectedItem() + "'");
                    c.statement.executeUpdate("update room set Availability = 'Available' where room_no = '"+RNo.getText()+"'");
                    JOptionPane.showMessageDialog(null,"Done");
                    setVisible(false);
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });


        JButton Check = new JButton("Check");
        Check.setBounds(170,300,120,30);
        Check.setBackground(Color.BLACK);
        Check.setForeground(Color.WHITE);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    ResultSet resultSet = c.statement.executeQuery("select*from patient_info where number = '"+choice.getSelectedItem()+"'");
                    while (resultSet.next()){
                        RNo.setText(resultSet.getNString("Room_Number"));
                        InT.setText(resultSet.getNString("Time"));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton back = new JButton("Back");
        back.setBounds(300,300,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(800,400);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static void main(String[] args) {
       new Pasient_dischaerge();
    }
}
