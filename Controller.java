//package com.controller;

//import com.model.Task;
//import com.view.AddTaskView;
//import com.view.MainView;
//import com.model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Controller
 */
public class Controller implements PropertyChangeListener {

    private Model model;
    /**
     * Need to parse date values from text fields
     */
    private SimpleDateFormat sdf;

    private MainView mainView;
    private AddTaskView dialog;

    public Controller(Model model, MainView mainView) {
        this.model = model;
        this.mainView = mainView;

        sdf = new SimpleDateFormat("h:mma");

        this.model.addListener(this);

        setUpViewEvents();
    }

    /**
     * Set up all events
     */
    private void setUpViewEvents() {
        mainView.getButtonExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        final ActionListener saveButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date date = model.getChosenDate();
                    Date startTime = addTime(date, dialog.getTextFieldStart().getText());
                    Date endTime = addTime(date, dialog.getTextFieldEnd().getText());
                    String name = dialog.getTextFieldDateName().getText();
                    Task node = new Task(name, startTime, endTime);
                    model.addNewTask(date, node);
                    dialog.setVisible(false);
                    dialog.dispose();
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog,
                            "Date error",
                            "Date error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        mainView.getButtonCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dialog = new AddTaskView(model.getChosenDate(), mainView);
                dialog.getSaveButton().addActionListener(saveButtonListener);
                dialog.launchFrame();
            }
        });

        mainView.getButtonDayBefore().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(model.getChosenDate());
                calendar.add(Calendar.DATE, -1);
                model.setChosenDate(calendar.getTime());
            }
        });

        mainView.getButtonDayAfter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(model.getChosenDate());
                calendar.add(Calendar.DATE, 1);
                model.setChosenDate(calendar.getTime());
            }
        });

        ActionListener dataChooserActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GregorianCalendar chosenDate = new GregorianCalendar();
                if (e.getActionCommand().equals("monthSelectionChanged")) {
                    final JComboBox c = (JComboBox) e.getSource();

                    // In most cases, changing the month will not change the selected
                    // day.  But if the selected day is 29, 30 or 31 and the newly
                    // selected month doesn't have that many days, we revert to the
                    // last day of the newly selected month...

                    chosenDate.setTime(model.getChosenDate());
                    int dayOfMonth = chosenDate.get(Calendar.DAY_OF_MONTH);

                    chosenDate.set(Calendar.DAY_OF_MONTH, 1);
                    chosenDate.set(Calendar.MONTH, c.getSelectedIndex());
                    int maxDayOfMonth = chosenDate.getActualMaximum(
                            Calendar.DAY_OF_MONTH);
                    chosenDate.set(Calendar.DAY_OF_MONTH, Math.min(dayOfMonth,
                            maxDayOfMonth));
                    model.setChosenDate(chosenDate.getTime());
                } else if (e.getActionCommand().equals("yearSelectionChanged")) {
                    if (!mainView.getPanelCalendar().refreshing) {
                        final JComboBox c = (JComboBox) e.getSource();
                        final Integer y = (Integer) c.getSelectedItem();

                        // in most cases, changing the year will not change the
                        // selected day.  But if the selected day is Feb 29, and the
                        // newly selected year is not a leap year, we revert to
                        // Feb 28...
                        chosenDate.setTime(model.getChosenDate());
                        int dayOfMonth = chosenDate.get(Calendar.DAY_OF_MONTH);
                        chosenDate.set(Calendar.DAY_OF_MONTH, 1);
                        chosenDate.set(Calendar.YEAR, y.intValue());
                        int maxDayOfMonth = chosenDate.getActualMaximum(
                                Calendar.DAY_OF_MONTH);
                        chosenDate.set(Calendar.DAY_OF_MONTH, Math.min(dayOfMonth,
                                maxDayOfMonth));
                        model.setChosenDate(chosenDate.getTime());
                    }
                } else if (e.getActionCommand().equals("todayButtonClicked")) {
                    chosenDate.setTime(new Date());
                    model.setChosenDate(chosenDate.getTime());
                } else if (e.getActionCommand().equals("dateButtonClicked")) {
                    final JButton b = (JButton) e.getSource();
                    final int i = Integer.parseInt(b.getName());
                    final Calendar cal = mainView.getPanelCalendar().getFirstVisibleDate();
                    cal.add(Calendar.DATE, i);
                    chosenDate.setTime(cal.getTime());
                    model.setChosenDate(chosenDate.getTime());
                }
            }
        };
        for (JButton button : mainView.getPanelCalendar().getButtons()) {
            button.setActionCommand("dateButtonClicked");
            button.addActionListener(dataChooserActionListener);
        }
        mainView.getPanelCalendar().getMonthSelector().setActionCommand("monthSelectionChanged");
        mainView.getPanelCalendar().getMonthSelector().addActionListener(dataChooserActionListener);

        mainView.getPanelCalendar().getYearSelector().setActionCommand("yearSelectionChanged");
        mainView.getPanelCalendar().getYearSelector().addActionListener(dataChooserActionListener);

        mainView.getPanelCalendar().getTodayButton().addActionListener(dataChooserActionListener);
        mainView.getPanelCalendar().getTodayButton().setActionCommand("todayButtonClicked");
    }

    /**
     * Interception events from model
     * @param evt event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();
        Object newVal = evt.getNewValue();

        // Changed date
        if ("chosenDate".equalsIgnoreCase(propName)) {
            mainView.getDailyTasks().updateDate((Date)newVal);
            mainView.getDailyTasks().updateTasks(model.getTasks((Date)newVal));
            mainView.getPanelCalendar().setDate((Date)newVal);
        } else if ("addTask".equalsIgnoreCase(propName)) {
            // Add new task
            List<Task> nodes = (List<Task>)newVal;
            mainView.getDailyTasks().updateTasks(nodes);
        }
    }

    /**
     * Add hours and minutes to baseDate
     * @param baseDate base date
     * @param time time in format 'h:mma'
     * @return instance of Date
     * @throws ParseException incorrect values
     */
    private Date addTime(Date baseDate, String time) throws ParseException {
        Date pars = sdf.parse(time);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(baseDate);
        GregorianCalendar calendar1 = new GregorianCalendar();
        calendar1.setTime(pars);
        calendar.add(Calendar.HOUR, calendar1.get(Calendar.HOUR));
        calendar.add(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
        return calendar.getTime();
    }
}
