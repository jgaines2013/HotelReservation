//package com.view;

//import com.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class DailyTasksPanel extends JPanel {

    private JLabel selectedDate;
    private JLayeredPane tasks;

    private List<JPanel> panels = new ArrayList<>();

    private int h = 30;
    private SimpleDateFormat sdf;

    public DailyTasksPanel(Date initDate) {
        super(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        sdf = new SimpleDateFormat("EEEE, M/d");

        selectedDate = new JLabel(sdf.format(initDate));
        selectedDate.setHorizontalAlignment(JLabel.CENTER);
        add(selectedDate, BorderLayout.NORTH);

        tasks = new JDesktopPane();
        tasks.setPreferredSize(new Dimension(500, 24 * h));
        tasks.setBackground(getBackground());

        JPanel tmpPanel, subPanel;
        String time;

        for (int i = 0; i < 24; i++) {
            tmpPanel = new JPanel(new BorderLayout());

            tmpPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            subPanel = new JPanel();

            if (i < 12) {
                time = "" + (i == 0 ? 12 : i) + "AM";
            } else {
                time = "" + (i - 12 == 0 ? 12 : i - 12) + "PM";
            }

            JLabel lab = new JLabel(time);
            subPanel.add(lab);
            subPanel.setPreferredSize(new Dimension(60, h));
            tmpPanel.add(subPanel, BorderLayout.WEST);

            subPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

            tmpPanel.setOpaque(true);
            tmpPanel.setBounds(0, h * i, 500, h);

            tasks.add(tmpPanel, 0, 0);
        }
        tasks.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (Component component : tasks.getComponents()) {
                    component.setBounds(component.getX(), component.getY(), e.getComponent().getWidth(), component.getHeight());
                    if (component instanceof Container) {
                        for (Component component1 : ((Container) (component)).getComponents()) {
                            component1.setBounds(component.getX(), component.getY(), e.getComponent().getWidth(), component.getHeight());
                        }
                    }
                }
            }
        });

        JScrollPane tasksScroller = new JScrollPane(tasks, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
        add(tasksScroller, BorderLayout.CENTER);
    }

    public void updateDate(Date initDate) {
        selectedDate.setText(sdf.format(initDate));
    }

    public void updateTasks(List<Task> tasksList) {
        for (JPanel panel : panels) {
            tasks.remove(panel);
        }
        tasks.revalidate();
        tasks.repaint();

        if (tasksList == null)
            return;
        GregorianCalendar calendar = new GregorianCalendar();
        for (Task task : tasksList) {
            calendar.setTime(task.getStart());
            int startHour = calendar.get(Calendar.HOUR);
            int startMinute = calendar.get(Calendar.MINUTE);
            calendar.setTime(task.getEnd());
            int endHour = calendar.get(Calendar.HOUR);
            int endMinute = calendar.get(Calendar.MINUTE);

            JPanel newPanel = new JPanel(new FlowLayout());

            newPanel.setOpaque(true);
            newPanel.setBackground(Color.ORANGE);
            int x0 = 60;
            int y0 = startHour * h + (int) (startMinute * (h / 60.0));
            int panelWidth = tasks.getWidth() - x0;
            int panelHeight = (endHour - startHour) * h + (int) ((endMinute - startMinute) * (h / 60.0));
            newPanel.setBounds(x0, y0, panelWidth, panelHeight);
            newPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

            JLabel label = new JLabel(task.getName());
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setVerticalAlignment(JLabel.TOP);
            newPanel.add(label);

            tasks.add(newPanel, 1, 0);

            panels.add(newPanel);
        }
    }
}
