package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_patient_detail extends JFrame {

    Update_patient_detail(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
        Image image = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500,60,300,300);
        panel.add(label);

        JLabel label1 =  new JLabel("Update  Patient details");
        label1.setBounds(124,11,260,25);
        label1.setFont(new Font("Tahoma",Font.BOLD,20));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2 = new JLabel("Name :");
        label2.setBounds(25,88,100,14);
        label2.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248,85,140,25);
        panel.add(choice);

        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select*from  patient_info");
            while (resultSet.next()){
                choice.add(resultSet.getNString("Name"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number :");
        label3.setBounds(25,129,150,14);
        label3.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(label3);

        JTextField rom = new JTextField();
        rom.setBounds(248,129,140,20);
        panel.add(rom);

        JLabel label4 = new JLabel("In-Time :");
        label4.setBounds(25,174,100,14);
        label4.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(label4);

        JTextField Intime = new JTextField();
        Intime.setBounds(248,174,140,20);
        panel.add(Intime);

        JLabel label5 = new JLabel("Amount Paid (Rs) :");
        label5.setBounds(25,216,150,14);
        label5.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(label5);

        JTextField money = new JTextField();
        money.setBounds(248,216,140,20);
        panel.add(money);

        JLabel label6 = new JLabel("Amount Pending (Rs) :");
        label6.setBounds(25,261,150,14);
        label6.setFont(new Font("Tahoma",Font.PLAIN,14));
        panel.add(label6);

        JTextField moneyb = new JTextField();
        moneyb.setBounds(248,261,140,20);
        panel.add(moneyb);

        JButton check  = new JButton("CHECK");
        check.setBounds(281,378,89,23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id  = choice.getSelectedItem();
                String q = "select*from patient_info where Name = '"+id+"'";
                try{
                    conn c = new conn();
                    ResultSet resultSet =  c.statement.executeQuery(q);
                    while (resultSet.next()){
                        rom.setText(resultSet.getNString("Room_Number"));
                        Intime.setText(resultSet.getNString("Time"));
                        money.setText(resultSet.getNString("Deposite"));
                    }
                    ResultSet resultSet1 = c.statement.executeQuery("select*from room where room_no = '"+rom+"'");
                    while (resultSet1.next()){
                        String Price1 = resultSet1.getNString("Price");
                        int amountPaid = Integer.parseInt(Price1) - Integer.parseInt(money.getText());
                        moneyb.setText(""+amountPaid);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton up = new JButton("UPDATE");
        up.setBounds(56,378,89,23);
        up.setBackground(Color.BLACK);
        up.setForeground(Color.white);
        panel.add(up);
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    conn c = new conn();
                    String q = choice.getSelectedItem();
                    String ro = rom.getText();
                    String tm = Intime.getText();
                    String amo = money.getText();
                    c.statement.executeUpdate("update patient_info set Room_Number = '"+ro+"',Time = '"+tm+"',Deposite = '"+amo+"'  where Name = '"+q+"' ");
                    JOptionPane.showMessageDialog(null,"Update Successfully");
                    setVisible(false);

                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(450,378,89,23);
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
        setSize(950,500);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Update_patient_detail();
    }
}
