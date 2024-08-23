

/**
 *
 * @author SIZA M
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class LoginPage extends JFrame {
    private Admin admin;
    private List<Student> students;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public LoginPage(Admin admin, List<Student> students) {
        this.admin = admin;
        this.students = students;
        initialize();
    }

    private void initialize() {
        setTitle("Timetable Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton studentButton = new JButton("Login as Student");
        JButton adminButton = new JButton("Login as Admin");

        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudentLogin();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAdminOptions();
            }
        });

        panel.add(studentButton);
        panel.add(adminButton);

        add(panel);
    }

    private void displayStudentLogin() {
        JFrame studentFrame = new JFrame("Student Login");
        studentFrame.setSize(300, 200);
        studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        studentFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                boolean studentExists = false;
                boolean passwordCorrect = false;

                for (Student student : students) {
                    if (student.getEmail().equals(email)) {
                        studentExists = true;
                        if (student.getPassword().equals(password)) {
                            passwordCorrect = true;
                            break;
                        }
                    }
                }

                if (!studentExists) {
                    emailField.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(studentFrame, "This student is not in this class");
                } else if (!passwordCorrect) {
                    emailField.setForeground(Color.GREEN);
                    JOptionPane.showMessageDialog(studentFrame, "Your password is incorrect, try again");
                } else {
                    JOptionPane.showMessageDialog(studentFrame, "Login successful");
                    studentFrame.dispose();
                }
            }
        });

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        studentFrame.add(panel);
        studentFrame.setVisible(true);
    }

private void displayAdminOptions() {
    JFrame adminOptionsFrame = new JFrame("Admin Options");
    adminOptionsFrame.setSize(600, 400);
    adminOptionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    adminOptionsFrame.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    JButton addStudentButton = new JButton("Add Student");
    JButton timetableButton = new JButton("Timetable");

    addStudentButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayAddStudent();
        }
    });

    timetableButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayTimetableManagement();
        }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1, 2));
    buttonPanel.add(addStudentButton);
    buttonPanel.add(timetableButton);

    panel.add(buttonPanel, BorderLayout.NORTH);

    // Table to display students
    String[] columnNames = {"Name", "Surname", "Email", "ID", "Password"};
    tableModel = new DefaultTableModel(columnNames, 0);
    studentTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(studentTable);
    panel.add(scrollPane, BorderLayout.CENTER);

    // Add mouse listener for right-click context menu
    studentTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger() && studentTable.getSelectedRow() != -1) {
                showContextMenu(e);
            }
        }
    });

    adminOptionsFrame.add(panel);
    adminOptionsFrame.setVisible(true);

    // Load existing students into the table
    updateStudentTable();
}


    private void displayAddStudent() {
        JFrame addStudentFrame = new JFrame("Add Student");
        addStudentFrame.setSize(400, 300);
        addStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addStudentFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel surnameLabel = new JLabel("Surname:");
        JTextField surnameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel idLabel = new JLabel("ID Number:");
        JTextField idField = new JTextField();
        JButton addButton = new JButton("Add to Class");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String email = emailField.getText();
                String id = idField.getText();

                if (id.length() != 13) {
                    JOptionPane.showMessageDialog(addStudentFrame, "ID number must be 13 digits");
                    return;
                }

                String password = name.charAt(0) + surname.substring(surname.length() - 1) + id.substring(id.length() - 5);
                Student student = new Student(name, surname, email, id, password);
                admin.addStudent(student);
                students.add(student);

                JOptionPane.showMessageDialog(addStudentFrame, "Student added successfully");
                nameField.setText("");
                surnameField.setText("");
                emailField.setText("");
                idField.setText("");

                // Update the table
                updateStudentTable();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel());
        panel.add(addButton);

        addStudentFrame.add(panel);
        addStudentFrame.setVisible(true);
    }

    private void updateStudentTable() {
        tableModel.setRowCount(0); // Clear existing data
        for (Student student : students) {
            Object[] row = {student.getName(), student.getSurname(), student.getEmail(), student.getId(), student.getPassword()};
            tableModel.addRow(row);
        }
    }

    private void showContextMenu(MouseEvent e) {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem removeItem = new JMenuItem("Remove");

        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    editStudent(selectedRow);
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    removeStudent(selectedRow);
                }
            }
        });

        contextMenu.add(editItem);
        contextMenu.add(removeItem);
        contextMenu.show(studentTable, e.getX(), e.getY());
    }

   private void editStudent(int row) {
    Student student = students.get(row);

    JFrame editStudentFrame = new JFrame("Edit Student");
    editStudentFrame.setSize(400, 300);
    editStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    editStudentFrame.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(6, 2));

    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField(student.getName());
    JLabel surnameLabel = new JLabel("Surname:");
    JTextField surnameField = new JTextField(student.getSurname());
    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField(student.getEmail());
    JLabel idLabel = new JLabel("ID Number:");
    JTextField idField = new JTextField(student.getId());
    JButton saveButton = new JButton("Save");

    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newName = nameField.getText();
            String newSurname = surnameField.getText();
            String newEmail = emailField.getText();
            String newId = idField.getText();

            boolean nameChanged = !newName.equals(student.getName());
            boolean surnameChanged = !newSurname.equals(student.getSurname());
            boolean idChanged = !newId.equals(student.getId());

            student.setName(newName);
            student.setSurname(newSurname);
            student.setEmail(newEmail);
            student.setId(newId);

            if (nameChanged || surnameChanged || idChanged) {
                String newPassword = newName.charAt(0) + newSurname.substring(newSurname.length() - 1) + newId.substring(newId.length() - 5);
                student.setPassword(newPassword);
            }

            JOptionPane.showMessageDialog(editStudentFrame, "Student updated successfully");
            editStudentFrame.dispose();

            // Update the table
            updateStudentTable();
        }
    });

    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(surnameLabel);
    panel.add(surnameField);
    panel.add(emailLabel);
    panel.add(emailField);
    panel.add(idLabel);
    panel.add(idField);
    panel.add(new JLabel());
    panel.add(saveButton);

    editStudentFrame.add(panel);
    editStudentFrame.setVisible(true);
}

private void removeStudent(int row) {
    Student student = students.get(row);
    admin.removeStudent(student);
    students.remove(row);
    JOptionPane.showMessageDialog(null, "Student removed successfully");
    // Update the table
    updateStudentTable();
}


public static void main(String[] args) {
    // Create a new Admin object
    Admin admin = new Admin("Admin", "User", "admin@example.com", "Computer Science", "admin123");


    // Create a list of students
    List<Student> students = new ArrayList<>();

    // Create a new LoginPage object
    LoginPage loginPage = new LoginPage(admin, students);

    // Set the login page visible
    loginPage.setVisible(true);
}

private void displayTimetableManagement() {
    JFrame timetableFrame = new JFrame("Timetable Management");
    timetableFrame.setSize(600, 400);
    timetableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    timetableFrame.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5, 2));

    JLabel timeLabel = new JLabel("Time:");
    JTextField timeField = new JTextField();
    JLabel subjectLabel = new JLabel("Subject:");
    JTextField subjectField = new JTextField();
    JLabel educatorLabel = new JLabel("Educator:");
    JTextField educatorField = new JTextField();
    JLabel venueLabel = new JLabel("Venue:");
    JTextField venueField = new JTextField();
    JButton saveButton = new JButton("Save");

    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String time = timeField.getText();
            String subject = subjectField.getText();
            String educator = educatorField.getText();
            String venue = venueField.getText();

            // Save the timetable entry (you can implement the logic to save it to a list or database)
            JOptionPane.showMessageDialog(timetableFrame, "Timetable entry saved successfully");

            // Clear the fields
            timeField.setText("");
            subjectField.setText("");
            educatorField.setText("");
            venueField.setText("");
        }
    });

    panel.add(timeLabel);
    panel.add(timeField);
    panel.add(subjectLabel);
    panel.add(subjectField);
    panel.add(educatorLabel);
    panel.add(educatorField);
    panel.add(venueLabel);
    panel.add(venueField);
    panel.add(new JLabel());
    panel.add(saveButton);

    timetableFrame.add(panel);
    timetableFrame.setVisible(true);
}

}


