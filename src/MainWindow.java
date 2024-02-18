import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Main Window");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton addJobButton = new JButton("Add Job Class");
        JButton updateJobButton = new JButton("Update Job Class");
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton updateEmployeeButton = new JButton("Update Employee");

        addJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JobView jobView = new JobView(null); // Open add JobClass view
                jobView.setVisible(true);
            }
        });

        updateJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can implement code to open a view for updating JobClass here
                // For demonstration purposes, let's assume we want to update JobClass with ID = 1
                Job jobToUpdate = DataAccess.getJobClassByID(1);
                if (jobToUpdate != null) {
                    JobView jobView = new JobView(jobToUpdate);
                    jobView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Job Class with ID = 1 not found.");
                }
            }
        });

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeeView employeeView = new EmployeeView(null); // Open add Employee view
                employeeView.setVisible(true);
            }
        });

        updateEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can implement code to open a view for updating Employee here
                // For demonstration purposes, let's assume we want to update Employee with ID = 1
                Employee employeeToUpdate = DataAccess.getAllEmployees().stream().findFirst().orElse(null);
                if (employeeToUpdate != null) {
                    EmployeeView employeeView = new EmployeeView(employeeToUpdate);
                    employeeView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Employee with ID = 1 not found.");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(addJobButton);
        panel.add(updateJobButton);
        panel.add(addEmployeeButton);
        panel.add(updateEmployeeButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}

