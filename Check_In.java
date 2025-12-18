import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CheckIn extends JFrame {
    private JTextField[] textFields = new JTextField[17];
    private JComboBox<String> cbRoomType;
    private Connection con;

    public CheckIn() {
        setTitle("Lodge Check-In");
        setLayout(null);
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initDB();
        initUI();
        loadRoomTypes();
        setVisible(true);
    }

    private void initDB() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lodge", "root", "root");
        } catch (SQLException e) {
            showError("Database connection failed: " + e.getMessage());
        }
    }

    private void initUI() {
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        String[] labels = {
                "Customer ID:", "First Name:", "Middle Name:", "Last Name:", "Father Name:",
                "Mother Name:", "Phone Number:", "Aadhar No:", "Pan No:", "From:",
                "To:", "Reason:", "Room Type:", "Room No:", "Room Rate:",
                "Advance:", "M Date:"
        };

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setBounds(60 + (i % 3) * 370, 100 + (i / 3) * 50, 120, 25);
            lbl.setFont(labelFont);
            add(lbl);

            if (i == 12) {
                cbRoomType = new JComboBox<>();
                cbRoomType.setBounds(160 + (i % 3) * 370, 100 + (i / 3) * 50, 200, 25);
                cbRoomType.addActionListener(e -> updateRoomRate());
                add(cbRoomType);
            } else {
                textFields[i] = new JTextField(20);
                textFields[i].setFont(labelFont);
                textFields[i].setBounds(160 + (i % 3) * 370, 100 + (i / 3) * 50, 200, 25);
                add(textFields[i]);
            }
        }

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(250, 550, 200, 40);
        btnSave.addActionListener(e -> saveData());
        add(btnSave);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(500, 550, 200, 40);
        btnCancel.addActionListener(e -> dispose());
        add(btnCancel);
    }

    private void loadRoomTypes() {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DISTINCT room_type FROM lodge")) {
            cbRoomType.addItem("-- Select --");
            while (rs.next()) {
                cbRoomType.addItem(rs.getString("room_type"));
            }
        } catch (SQLException e) {
            showError("Failed to load room types: " + e.getMessage());
        }
    }

    private void updateRoomRate() {
        String type = (String) cbRoomType.getSelectedItem();
        if (type == null || type.equals("-- Select --")) {
            textFields[14].setText("");
            return;
        }

        try (PreparedStatement pst = con.prepareStatement(
                "SELECT room_rate FROM lodge WHERE room_type = ? LIMIT 1")) {
            pst.setString(1, type);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                textFields[14].setText(rs.getString("room_rate"));
            } else {
                textFields[14].setText("N/A");
            }
        } catch (SQLException e) {
            showError("Error fetching room rate: " + e.getMessage());
        }
    }

    private void saveData() {
        String sql = "INSERT INTO check_in (c_id, cf_name, cm_name, cl_name, cfa_name, cmo_name, " +
                "c_no1, aadhar_no, pan_no, fromw, tow, reason, room_type, room_no, rate, advance, m_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            for (int i = 0; i < textFields.length; i++) {
                pst.setString(i + 1, i == 12 ? (String) cbRoomType.getSelectedItem() : textFields[i].getText());
            }
            int inserted = pst.executeUpdate();
            if (inserted > 0) {
                JOptionPane.showMessageDialog(this, "Check-in saved successfully.");
            }
        } catch (SQLException e) {
            showError("Error saving data: " + e.getMessage());
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CheckIn::new);
    }
}
