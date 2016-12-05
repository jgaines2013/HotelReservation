//package com.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Dialog for create new task
 */
public class AddTaskView extends JDialog {

    private JPanel mainPanel;
    private JTextField textFieldDateName;
    private JTextField textFieldDate;
    private JTextField textFieldStart;
    private JTextField textFieldEnd;
    private JButton saveButton;

    private SimpleDateFormat sdf1;

    public AddTaskView(Date date, JFrame owner) {
        super(owner,"Create",Dialog.ModalityType.DOCUMENT_MODAL);
        setPreferredSize(new Dimension(380, 112));

        sdf1 = new SimpleDateFormat("MM/dd/yy");

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.setPreferredSize(new Dimension(680, 52));
        textFieldDateName = new JTextField();
        textFieldDateName.setHorizontalAlignment(10);
        textFieldDateName.setMargin(new Insets(0, 0, 0, 0));
        textFieldDateName.setPreferredSize(new Dimension(350, 24));
        mainPanel.add(textFieldDateName);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainPanel.add(panel1);
        textFieldDate = new JTextField();
        textFieldDate.setPreferredSize(new Dimension(80, 24));
        panel1.add(textFieldDate);
        textFieldStart = new JTextField();
        textFieldStart.setPreferredSize(new Dimension(70, 24));
        panel1.add(textFieldStart);
        final JLabel label1 = new JLabel();
        label1.setText("to");
        panel1.add(label1);
        textFieldEnd = new JTextField();
        textFieldEnd.setPreferredSize(new Dimension(70, 24));
        panel1.add(textFieldEnd);
        saveButton = new JButton();
        saveButton.setText("Save");

        panel1.add(saveButton);

        textFieldDate.setText(sdf1.format(date));
        textFieldDate.setEditable(false);

        textFieldStart.setText("10:30am");
        textFieldEnd.setText("11:30am");

        setContentPane(mainPanel);
    }

    public void launchFrame() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextField getTextFieldDateName() {
        return textFieldDateName;
    }

    public JTextField getTextFieldDate() {
        return textFieldDate;
    }

    public JTextField getTextFieldStart() {
        return textFieldStart;
    }

    public JTextField getTextFieldEnd() {
        return textFieldEnd;
    }
}
