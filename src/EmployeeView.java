import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {
    private JTextField employeeIDField;
    private JTextField employeeNameField;
    private JTextField jobClassIDField;

    public EmployeeView(Employee employeeToUpdate) {
        setTitle(employeeToUpdate == null ? "Add Employee" : "Update Employee");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel employeeIDLabel = new JLabel("Employee ID:");
        employeeIDField = new JTextField();
        if (employeeToUpdate != null) {
            employeeIDField.setText(String.valueOf(employeeToUpdate.getEmployeeID()));
            employeeIDField.setEditable(false); // Disable editing for ID when updating
        }
        JLabel employeeNameLabel = new JLabel("Employee Name:");
        employeeNameField = new JTextField(employeeToUpdate != null ? employeeToUpdate.getEmployeeName() : "");
        JLabel jobClassIDLabel = new JLabel("Job Class ID:");
        jobClassIDField = new JTextField(employeeToUpdate != null ? String.valueOf(employeeToUpdate.getJobClassID()) : "");

        JButton saveButton = new JButton(employeeToUpdate == null ? "Add" : "Update");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int employeeID = Integer.parseInt(employeeIDField.getText());
                String employeeName = employeeNameField.getText();
                int jobClassID = Integer.parseInt(jobClassIDField.getText());
                Employee employee = new Employee(employeeID, employeeName, jobClassID);
                if (employeeToUpdate == null) {
                    DataAccess.addEmployee(employee);
                } else {
                    DataAccess.updateEmployee(employee);
                }
                dispose(); // Close the window after operation
            }
        });

        panel.add(employeeIDLabel);
        panel.add(employeeIDField);
        panel.add(employeeNameLabel);
        panel.add(employeeNameField);
        panel.add(jobClassIDLabel);
        panel.add(jobClassIDField);
        panel.add(saveButton);

        add(panel);
    }
}
