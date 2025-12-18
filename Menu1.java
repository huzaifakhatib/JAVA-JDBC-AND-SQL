import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Enhanced Menu Example using Swing
 * Author: Your Name
 * Date: YYYY-MM-DD
 */
public class MenuDemo extends JFrame implements ActionListener {

    // Menu items
    private JMenuItem checkInItem, checkOutItem, enquiryItem, serviceItem;
    private JMenuItem tableItem, billItem, reportItem;
    private JMenuItem hotelRateItem, lodgeRateItem, roomRateItem, employeeItem;

    public MenuDemo() {
        // Frame setup
        setTitle("Hotel Management Menu");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();

        // Lodge menu
        JMenu lodgeMenu = new JMenu("Lodge");
        checkInItem = new JMenuItem("Check In");
        checkOutItem = new JMenuItem("Check Out");
        enquiryItem = new JMenuItem("Enquiry");
        serviceItem = new JMenuItem("Service");

        lodgeMenu.add(checkInItem);
        lodgeMenu.add(checkOutItem);
        lodgeMenu.add(enquiryItem);
        lodgeMenu.add(serviceItem);

        // Hotel menu
        JMenu hotelMenu = new JMenu("Hotel");
        tableItem = new JMenuItem("Table New");
        billItem = new JMenuItem("Bill");
        reportItem = new JMenuItem("Report");

        hotelMenu.add(tableItem);
        hotelMenu.add(billItem);
        hotelMenu.add(reportItem);

        // Admin menu
        JMenu adminMenu = new JMenu("Admin");
        hotelRateItem = new JMenuItem("Hotel Rate Charge");
        lodgeRateItem = new JMenuItem("Lodge Rate Charge");
        roomRateItem = new JMenuItem("Room Rate Charge");
        employeeItem = new JMenuItem("Employee");

        adminMenu.add(hotelRateItem);
        adminMenu.add(lodgeRateItem);
        adminMenu.add(roomRateItem);
        adminMenu.add(employeeItem);

        // Add menus to menu bar
        menuBar.add(lodgeMenu);
        menuBar.add(hotelMenu);
        menuBar.add(adminMenu);

        // Set menu bar
        setJMenuBar(menuBar);

        // Register listeners
        checkInItem.addActionListener(this);
        tableItem.addActionListener(this);
        hotelRateItem.addActionListener(this);
        checkOutItem.addActionListener(this);
        billItem.addActionListener(this);

        // Make frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == tableItem) {
            new TableEnter(); // Replace with your class
        } else if (source == checkInItem) {
            new CheckIn(); // Replace with your class
        } else if (source == hotelRateItem) {
            new HotelRate(); // Replace with your class
        } else if (source == checkOutItem) {
            new Checkout(); // Replace with your class
        } else if (source == billItem) {
            new HotelBill(); // Replace with your class
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuDemo::new);
    }
}
