import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobView extends JFrame {
    private JTextField jobClassIDField;
    private JTextField jobTitleField;
    private JTextField hourlyWageField;

    public JobView(Job jobToUpdate) {
        setTitle(jobToUpdate == null ? "Add Job Class" : "Update Job Class");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel jobClassIDLabel = new JLabel("Job Class ID:");
        jobClassIDField = new JTextField();
        if (jobToUpdate != null) {
            jobClassIDField.setText(String.valueOf(jobToUpdate.getJobClassID()));
            jobClassIDField.setEditable(false); // Disable editing for ID when updating
        }
        JLabel jobTitleLabel = new JLabel("Job Title:");
        jobTitleField = new JTextField(jobToUpdate != null ? jobToUpdate.getJobTitle() : "");
        JLabel hourlyWageLabel = new JLabel("Hourly Wage:");
        hourlyWageField = new JTextField(jobToUpdate != null ? String.valueOf(jobToUpdate.getHourlyWage()) : "");

        JButton saveButton = new JButton(jobToUpdate == null ? "Add" : "Update");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jobClassID = Integer.parseInt(jobClassIDField.getText());
                String jobTitle = jobTitleField.getText();
                double hourlyWage = Double.parseDouble(hourlyWageField.getText());
                Job job = new Job(jobClassID, jobTitle, hourlyWage);
                if (jobToUpdate == null) {
                    DataAccess.addJobClass(job);
                } else {
                    DataAccess.updateJobClass(job);
                }
                dispose(); // Close the window after operation
            }
        });

        panel.add(jobClassIDLabel);
        panel.add(jobClassIDField);
        panel.add(jobTitleLabel);
        panel.add(jobTitleField);
        panel.add(hourlyWageLabel);
        panel.add(hourlyWageField);
        panel.add(saveButton);

        add(panel);
    }
}
