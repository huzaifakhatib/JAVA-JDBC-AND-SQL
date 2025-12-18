import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class hotel_rate extends Frame implements ActionListener {
    Frame f1;
    Label l0,l1, l2, l3, l4;
    TextField t1, t2, t3, t4;
    Button b1, b2;
    ResultSet rs;
    Connection con;
    String sq1,p,u,sq2,i;
    int k,r,d;
    Statement st;
    public hotel_rate()
    {
        f1 = new Frame();
        f1.setLayout(null);
        t1 = new TextField(20);
        t2 = new TextField(20);
        t3 = new TextField(20);
        t4 = new TextField(20);
        l0=new Label("Hotel Rate");
        l1 = new Label("Username :");
        l2 = new Label("Password :");
        l3 = new Label("Item :");
        l4 = new Label("Rate :");
        b1 = new Button("OK ");
        b2 = new Button("ADD ");
        /* my NEW bounds coding starts from here  */
        l0.setBounds(170,70,250,60);

        l1.setBounds(100,150,130,50);
        t1.setBounds(250,150,200,40);


        l2.setBounds(100,250,130,50);
        t2.setBounds(250,250,200,40);

        b1.setBounds(120,340,200,40);


        l3.setBounds(100,150,130,50);
        t3.setBounds(250,150,200,40);


        l4.setBounds(100,250,130,50);
        t4.setBounds(250,250,200,40);

        b2.setBounds(120,350,200,40);
        Font font =new Font("Arial", Font.BOLD,24);
        l1.setFont(font);
        l2.setFont(font);
        t1.setFont(font);
        t2.setFont(font);
        b1.setFont(font);
        t3.setFont(font);
        t4.setFont(font);
        l3.setFont(font);
        l4.setFont(font);
        b2.setFont(font);

        Font ff =new Font("Arial", Font.BOLD,34);
        l0.setFont(ff);

        /*   BOUNDS   end  here*/

        /* important coding goes from here ******  */
        f1.add(l0);
        f1.add(l1);
        f1.add(t1);
        f1.add(l2);
        f1.add(t2);
        f1.add(b1);
        f1.add(l3);
        f1.add(t3);
        f1.add(l4);
        f1.add(t4);
        f1.add(b2);
        f1.setSize(1000, 1000);
        f1.setVisible(true);
        b1.addActionListener(this);
        b2.addActionListener(this);
        l3.setVisible(false);
        l4.setVisible(false);
        t3.setVisible(false);
        t4.setVisible(false);
        b2.setVisible(false);

        try {
            con = DriverManager.getConnection("JDBC:mysql://localhost:3306/lodge", "root", "root");
        } catch (
                SQLException ee) {
            System.out.println(ee);
        }
        class RectCanvas extends Canvas
        {
            @Override
            public void paint(Graphics g)
            {
                Graphics2D g2=(Graphics2D)g;
                BasicStroke thick= new BasicStroke(19.0f);
                g2.setStroke(thick);
                super.paint(g);
                g2.setColor(Color.RED);
                g2.drawRect(10, 10, 500, 480);
            }
        }
        RectCanvas canvas = new RectCanvas();
        canvas.setBounds(30, 30, 600,500);
        f1.add(canvas);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            try {
                p = t2.getText();
                u = t1.getText();

                sq1 = "select  count(user) from admin  where user='" + u + "' and password='" + p + "';";
                st = con.createStatement();
                rs = st.executeQuery(sq1);

                rs.next();
                k = Integer.parseInt(rs.getString(1));

                if (k == 1) {


                    l3.setVisible(true);
                    l4.setVisible(true);
                    t3.setVisible(true);
                    t4.setVisible(true);
                    b2.setVisible(true);
                    l1.setVisible(false);
                    l2.setVisible(false);
                    t1.setVisible(false);
                    t2.setVisible(false);
                    b1.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(null, "invalid user or password");
                }


            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else if (e.getSource() == b2)
        {
            try {
                i = t3.getText();
                r = Integer.parseInt(t4.getText());
                sq2 = "insert into hotelrate(item,rate)value('" + i + "','" + String.valueOf(r) + "');";
                st = con.createStatement();
                st.executeUpdate(sq2);

                JOptionPane.showMessageDialog(null, "record saved successfull");
            } catch (Exception ex){}
        }

    }


    public static void main(String[] args) {
        hotel_rate q= new hotel_rate ();
    }
}
