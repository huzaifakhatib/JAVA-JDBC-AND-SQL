import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends Frame  implements ActionListener
{
    Frame f1;
    Label l1,l2;
    TextField t1,t2;
    Button b1;
    ResultSet rs;
    Connection con;
    int k;
    String sq1,u,p;
    Statement st5;
public login()
{
    try {
        con = DriverManager.getConnection("JDBC:MYSQL://localhost:3306/lodge", "root", "root");

    } catch (SQLException eee)
    {
        System.out.println(eee);
    }
    f1 = new Frame();
    f1.setLayout(new FlowLayout());
    t1 = new TextField(20);
    t2 = new TextField(20);
    l1 = new Label("username");
    l2 = new Label("password");
    b1 = new Button("ok");
    f1.add(l1);
    f1.add(t1);
    f1.add(l2);
    f1.add(t2);
    f1.add(b1);
    b1.addActionListener(this);
    f1.setVisible(true);
    f1.setSize(400, 400);



    Font font = new Font("Arial", Font.BOLD, 17);
    l1.setFont(font);
    l2.setFont(font);

    l1.setForeground(Color.BLUE);
    l2.setForeground(Color.blue);

    t1.setForeground(Color.BLACK);      // text color
    t1.setBackground(Color.LIGHT_GRAY); // background

    t2.setForeground(Color.BLACK);
    t2.setBackground(Color.LIGHT_GRAY);


    b1.setForeground(Color.black);



}
public void actionPerformed(ActionEvent e)
{
    if (e.getSource()==b1)
    {

            p = t2.getText();
            u = t1.getText();

            try {
                sq1 = "select count(user) from admin where user='" + u + "' and password='" + p + "';";
                st5 = con.createStatement();

                rs = st5.executeQuery(sq1);

                rs.next();

                k = Integer.parseInt(rs.getString(1));
                if (k == 1) {
                    JOptionPane.showMessageDialog(null, "login successfull");
                    new menu1();
                } else
                {
                    JOptionPane.showMessageDialog(null, "invalid username or password");
                }
            } catch (SQLException pp) {
                System.out.println(pp);
            }

        }

    }

public static void main(String[] args)
    {
        login l=new login();
    }
}
