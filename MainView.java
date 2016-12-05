//package com.view;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Main window
 */
public class MainView extends JFrame {
    private JPanel mainPanel;
    private JButton buttonCreate;
    private JButton buttonDayBefore;
    private JButton buttonDayAfter;
    private JButton buttonExit;
    private JPanel panelTop;
    private JPanel panelQuit;
    private JPanel panelButtons;

    private DateChooserPanel panelCalendar;
    private DailyTasksPanel dailyTasks;

    public MainView() {
        setTitle("Calendar");
        setPreferredSize(new Dimension(800, 300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout(0, 0));
        mainPanel.add(panelTop, BorderLayout.NORTH);
        panelQuit = new JPanel();
        panelQuit.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelTop.add(panelQuit, BorderLayout.EAST);
        buttonExit = new JButton();
        buttonExit.setText("Quit");
        panelQuit.add(buttonExit);
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelTop.add(panelButtons, BorderLayout.WEST);
        buttonCreate = new JButton();
        buttonCreate.setText("Create");
        panelButtons.add(buttonCreate);
        buttonDayBefore = new JButton();
        buttonDayBefore.setText("<");
        panelButtons.add(buttonDayBefore);
        buttonDayAfter = new JButton();
        buttonDayAfter.setText(">");
        panelButtons.add(buttonDayAfter);
        panelCalendar = new DateChooserPanel();
        mainPanel.add(panelCalendar, BorderLayout.WEST);
        dailyTasks = new DailyTasksPanel(new Date());
        mainPanel.add(dailyTasks, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    public void launchFrame() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getButtonCreate() {
        return buttonCreate;
    }

    public JButton getButtonDayBefore() {
        return buttonDayBefore;
    }

    public JButton getButtonDayAfter() {
        return buttonDayAfter;
    }

    public JButton getButtonExit() {
        return buttonExit;
    }

    public JPanel getPanelTop() {
        return panelTop;
    }

    public JPanel getPanelQuit() {
        return panelQuit;
    }

    public JPanel getPanelButtons() {
        return panelButtons;
    }

    public DateChooserPanel getPanelCalendar() {
        return panelCalendar;
    }

    public DailyTasksPanel getDailyTasks() {
        return dailyTasks;
    }
}
