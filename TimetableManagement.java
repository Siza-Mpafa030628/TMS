
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 *
 * @author SIZA M
 */
public class TimetableManagement {

    private DefaultTableModel studentTableModel;
    private DefaultTableModel timetableTableModel;
    private JTable studentTable;
    private JTable timetableTable;
    private JTextField timeField, subjectField, educatorField, venueField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimetableManagement().displayTimetableManagement());
    }

    private void displayTimetableManagement() {
        JFrame timetableFrame = new JFrame("Timetable Management");
        timetableFrame.setSize(800, 600);
        timetableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        timetableFrame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Student Tab
        JPanel addStudentPanel = new JPanel(new BorderLayout());
        studentTableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Surname", "Email", "ID Number", "Password"}, 0);
        studentTable = new JTable(studentTableModel);
        JScrollPane studentScrollPane = new JScrollPane(studentTable);
        addStudentPanel.add(studentScrollPane, BorderLayout.CENTER);

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add student logic here
                studentTableModel.addRow(new Object[]{"1", "John", "Doe", "john.doe@example.com", "123456789", "password"});
            }
        });
        addStudentPanel.add(addStudentButton, BorderLayout.SOUTH);

        tabbedPane.addTab("Add Student", addStudentPanel);

        // Timetable Tab
        JPanel timetablePanel = new JPanel(new BorderLayout());
        timetableTableModel = new DefaultTableModel(new Object[]{"Time", "Subject", "Educator", "Venue"}, 0);
        timetableTable = new JTable(timetableTableModel);
        JScrollPane timetableScrollPane = new JScrollPane(timetableTable);
        timetablePanel.add(timetableScrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        JLabel timeLabel = new JLabel("Time:");
        timeField = new JTextField();
        JLabel subjectLabel = new JLabel("Subject:");
        subjectField = new JTextField();
        JLabel educatorLabel = new JLabel("Educator:");
        educatorField = new JTextField();
        JLabel venueLabel = new JLabel("Venue:");
        venueField = new JTextField();
        JButton saveButton = new JButton("Save");
        JButton editButton = new JButton("Edit");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = timeField.getText();
                String subject = subjectField.getText();
                String educator = educatorField.getText();
                String venue = venueField.getText();

                // Add the timetable entry to the table
                timetableTableModel.addRow(new Object[]{time, subject, educator, venue});

                // Clear the fields
                clearFields();

                JOptionPane.showMessageDialog(timetableFrame, "Timetable entry saved successfully");
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = timetableTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Update the selected row with new data
                    timetableTableModel.setValueAt(timeField.getText(), selectedRow, 0);
                    timetableTableModel.setValueAt(subjectField.getText(), selectedRow, 1);
                    timetableTableModel.setValueAt(educatorField.getText(), selectedRow, 2);
                    timetableTableModel.setValueAt(venueField.getText(), selectedRow, 3);

                    // Clear the fields
                    clearFields();

                    JOptionPane.showMessageDialog(timetableFrame, "Timetable entry updated successfully");
                } else {
                    JOptionPane.showMessageDialog(timetableFrame, "Please select a row to edit");
                }
            }
        });

        inputPanel.add(timeLabel);
        inputPanel.add(timeField);
        inputPanel.add(subjectLabel);
        inputPanel.add(subjectField);
        inputPanel.add(educatorLabel);
        inputPanel.add(educatorField);
        inputPanel.add(venueLabel);
        inputPanel.add(venueField);
        inputPanel.add(saveButton);
        inputPanel.add(editButton);

        timetablePanel.add(inputPanel, BorderLayout.NORTH);

        tabbedPane.addTab("Timetable", timetablePanel);

        timetableFrame.add(tabbedPane);
        timetableFrame.setVisible(true);
    }

    private void clearFields() {
        timeField.setText("");
        subjectField.setText("");
        educatorField.setText("");
        venueField.setText("");
    }
}