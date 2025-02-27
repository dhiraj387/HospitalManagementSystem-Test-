package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Patient_Info extends JFrame {

    Patient_Info(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,990,590);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10,50,980,450);
        table.setBackground(new Color(90,156,163));
        table.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(table);

        JLabel label = new JLabel("ID");
        label.setBounds(7,15,80,20);
        label.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(label);

        JLabel label1 = new JLabel("Number");
        label1.setBounds(140,15,80,20);
        label1.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(label1);

        JLabel label2 = new JLabel("Name");
        label2.setBounds(250,15,130,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(label2);

        JLabel label3 = new JLabel("Gender");
        label3.setBounds(380,15,80,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(label3);

        JLabel label4 = new JLabel("Patient Disease");
        label4.setBounds(480,15,150,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(label4);

        JLabel label5 = new JLabel("Room NO");
        label5.setBounds(620,15,130,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(label5);

        JLabel label6 = new JLabel("Date/Time");
        label6.setBounds(740,15,130,20);
        label6.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(label6);

        JLabel label7 = new JLabel("Deposite");
        label7.setBounds(860,15,130,20);
        label7.setFont(new Font("Tahoma",Font.BOLD,16));
        panel.add(label7);

        try{
            conn c = new conn();
            String q = "select*from patient_info";
            ResultSet resultSet  = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton back = new JButton("Back");
        back.setBounds(700,500,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(1000,600);
        setLayout(null);
        setLocation(300,200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Patient_Info();
    }
}
