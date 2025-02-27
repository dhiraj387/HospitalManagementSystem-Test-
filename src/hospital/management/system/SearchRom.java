package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SearchRom extends JFrame {

    Choice choice;
    JTable table;

    SearchRom(){

        JPanel panel =  new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel  For = new JLabel("Search for Room");
        For.setBounds(250,11,186,31);
        For.setForeground(Color.WHITE);
        For.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(For);

        JLabel  sta = new JLabel("Status :");
        sta.setBounds(70,70,80,20);
        sta.setForeground(Color.WHITE);
        sta.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(sta);

        choice = new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBounds(5,187,700,210);
        table.setBackground(new Color(90,156,163));
        table.setForeground(Color.WHITE);
        panel.add(table);

        try{
            conn c = new conn();
            String q = "select*from room";
            ResultSet  resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel  Rm = new JLabel("Room Number");
        Rm.setBounds(5,162,120,20);
        Rm.setForeground(Color.WHITE);
        Rm.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(Rm);

        JLabel  sd = new JLabel("Availability");
        sd.setBounds(170,162,80,20);
        sd.setForeground(Color.WHITE);
        sd.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(sd);

        JLabel  pr = new JLabel("Price");
        pr.setBounds(347,162,80,20);
        pr.setForeground(Color.WHITE);
        pr.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(pr);

        JLabel  bed = new JLabel("Bed Type");
        bed.setBounds(520,162,80,25);
        bed.setForeground(Color.WHITE);
        bed.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(bed);

        JButton  search = new JButton("Search");
        search.setBounds(200,450,100,25);
        search.setFont(new Font("Tahoma",Font.BOLD,14));
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select*from room where Availability   = '"+choice.getSelectedItem()+"'";
                try {
                    conn c = new conn();
                    ResultSet  resultSet = c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        JButton back  = new JButton("BACK");
        back.setBounds(500,450,100,20);
        back.setFont(new Font("Tahoma",Font.BOLD,14));
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
        setSize(700,500);
        setLayout(null);
        setLocation(450,250);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SearchRom();
    }
}
