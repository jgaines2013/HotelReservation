//package com.model;

//import com.TasksIO;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Model.
 */
public class HotelModel {

    private SwingPropertyChangeSupport propChangeFirer;
    /**
     * Key format for hashMap.
     */
    private SimpleDateFormat sdf;

    /**
     * All tasks.
     */
    private HashMap<String, List<Reservation>> reservations;

    /**
     * The date selected.
     */
    private Date chosenDate;

    public HotelModel(HashMap<String, List<Reservation>> tasks) {
        this.reservations = tasks;
        sdf = new SimpleDateFormat("MM/dd/yy");
        propChangeFirer = new SwingPropertyChangeSupport(this);
    }

    /**
     * Add new task to selected day
     *
     * @param date selected date
     * @param Reservation reservation
     * @throws Exception incorrect values or time conflicts
     */
    public void addNewTask(Date date, Reservation reservation) throws Exception {

        List<Reservation> oldVal;

        String dateStr = sdf.format(date);

        if (!reservations.containsKey(dateStr)) {
            oldVal = null;
            reservations.put(dateStr, new LinkedList<>(Arrays.asList(reservation)));
        } else {
            List<Reservation> list = reservations.get(dateStr);
            oldVal = new ArrayList<>(list);
            int i = 0;
            while (i < list.size()) {
                if (list.get(i).getCheckIn().after(reservation.getCheckOut())) {
                    break;
                }
                i++;
            }

            if (i == 0) {
                list.add(0, reservation);
            } else if (i == list.size()) {
                if (list.get(i - 1).getCheckOut().before(reservation.getCheckIn())) {
                    list.add(reservation);
                } else
                    throw new Exception("IncorrectDate");
            } else {
                if (list.get(i - 1).getCheckOut().before(reservation.getCheckIn()) && list.get(i).getCheckIn().after(reservation.getCheckOut())) {
                    list.add(i, reservation);
                } else
                    throw new Exception("IncorrectDate");

            }
        }
        propChangeFirer.firePropertyChange("addTask", oldVal, reservations.get(dateStr));
        ReservationsIO.save(reservations);
    }

    public List<Reservation> getTasks(Date date) {
        String dateStr = sdf.format(date);
        if (reservations.containsKey(dateStr)) {
            return reservations.get(dateStr);
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

    public void setTasks(HashMap<String, List<Reservation>> reservations) {
        HashMap<String, List<Reservation>> oldVal = this.reservations;
        this.reservations = reservations;
        propChangeFirer.firePropertyChange("tasks", oldVal, reservations);
    }
}
