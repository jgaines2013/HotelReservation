//package com.model;

//import com.TasksIO;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Model.
 */
public class Model {

    private SwingPropertyChangeSupport propChangeFirer;
    /**
     * Key format for hashMap.
     */
    private SimpleDateFormat sdf;

    /**
     * All tasks.
     */
    private HashMap<String, List<Task>> tasks;

    /**
     * The date selected.
     */
    private Date chosenDate;

    public Model(HashMap<String, List<Task>> tasks) {
        this.tasks = tasks;
        sdf = new SimpleDateFormat("MM/dd/yy");
        propChangeFirer = new SwingPropertyChangeSupport(this);
    }

    /**
     * Add new task to selected day
     *
     * @param date selected date
     * @param task task
     * @throws Exception incorrect values or time conflicts
     */
    public void addNewTask(Date date, Task task) throws Exception {

        List<Task> oldVal;

        String dateStr = sdf.format(date);

        if (!tasks.containsKey(dateStr)) {
            oldVal = null;
            tasks.put(dateStr, new LinkedList<>(Arrays.asList(task)));
        } else {
            List<Task> list = tasks.get(dateStr);
            oldVal = new ArrayList<>(list);
            int i = 0;
            while (i < list.size()) {
                if (list.get(i).getStart().after(task.getEnd())) {
                    break;
                }
                i++;
            }

            if (i == 0) {
                list.add(0, task);
            } else if (i == list.size()) {
                if (list.get(i - 1).getEnd().before(task.getStart())) {
                    list.add(task);
                } else
                    throw new Exception("IncorrectDate");
            } else {
                if (list.get(i - 1).getEnd().before(task.getStart()) && list.get(i).getStart().after(task.getEnd())) {
                    list.add(i, task);
                } else
                    throw new Exception("IncorrectDate");

            }
        }
        propChangeFirer.firePropertyChange("addTask", oldVal, tasks.get(dateStr));
        TasksIO.save(tasks);
    }

    public List<Task> getTasks(Date date) {
        String dateStr = sdf.format(date);
        if (tasks.containsKey(dateStr)) {
            return tasks.get(dateStr);
        }
        return null;
    }

    public void addListener(PropertyChangeListener prop) {
        propChangeFirer.addPropertyChangeListener(prop);
    }

    public Date getChosenDate() {
        return chosenDate;
    }

    /**
     * Set chosen data. Save only the value of year, month and day
     *
     * @param chosenDate chosen data
     */
    public void setChosenDate(Date chosenDate) {
        Date oldVal = this.chosenDate;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(chosenDate);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 1);
        calendar.set(Calendar.MILLISECOND, 0);

        this.chosenDate = calendar.getTime();
        propChangeFirer.firePropertyChange("chosenDate", oldVal, chosenDate);
    }

    public void setTasks(HashMap<String, List<Task>> tasks) {
        HashMap<String, List<Task>> oldVal = this.tasks;
        this.tasks = tasks;
        propChangeFirer.firePropertyChange("tasks", oldVal, tasks);
    }
}
