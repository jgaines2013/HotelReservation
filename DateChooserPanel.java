//package com.view;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A panel that allows the user to select a date.
 */
public class DateChooserPanel extends JPanel {

    /**
     * The color for the selected date.
     */
    private Color chosenDateButtonColor;

    /**
     * The color for dates in the current month.
     */
    private Color chosenMonthButtonColor;

    /**
     * The color for dates that are visible, but not in the current month.
     */
    private Color chosenOtherButtonColor;

    /**
     * The first day-of-the-week.
     */
    private int firstDayOfWeek;

    /**
     * A combo for selecting the month.
     */
    private JComboBox monthSelector;

    /**
     * A combo for selecting the year.
     */
    private JComboBox yearSelector;

    /**
     * A button for selecting today's date.
     */
    private JButton todayButton;

    /**
     * An array of buttons used to display the days-of-the-month.
     */
    private JButton[] buttons;

    private GregorianCalendar chosenDate;

    /**
     * A flag that indicates whether or not we are currently refreshing the
     * buttons.
     */
    public boolean refreshing = false;

    /**
     * The ordered set of all seven days of a week,
     * beginning with the 'firstDayOfWeek'.
     */
    private int[] WEEK_DAYS;

    /**
     * Constructs a new date chooser panel, using today's date as the initial
     * selection.
     */
    public DateChooserPanel() {
        this(true);
    }

    /**
     * Constructs a new date chooser panel.
     *
     * @param controlPanel a flag that indicates whether or not the 'today'
     *                     button should appear on the panel.
     */
    public DateChooserPanel(final boolean controlPanel) {

        super(new BorderLayout());

        this.chosenDateButtonColor = UIManager.getColor("textHighlight");
        this.chosenMonthButtonColor = UIManager.getColor("control");
        this.chosenOtherButtonColor = UIManager.getColor("controlShadow");


        chosenDate = new GregorianCalendar();
        chosenDate.setTime(new Date());
        // the default date is today...
        this.firstDayOfWeek = chosenDate.getFirstDayOfWeek();
        this.WEEK_DAYS = new int[7];
        for (int i = 0; i < 7; i++) {
            this.WEEK_DAYS[i] = ((this.firstDayOfWeek + i - 1) % 7) + 1;
        }


        JPanel topPanel = new JPanel(new FlowLayout());

        topPanel.add(constructSelectionPanel());
        if (controlPanel) {
            topPanel.add(constructControlPanel());
        }
        add(getCalendarPanel(), BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
    }

    /**
     * Sets the date chosen in the panel.
     *
     * @param theDate the new date.
     */
    public void setDate(final Date theDate) {
        this.chosenDate.setTime(theDate);
        this.monthSelector.setSelectedIndex(this.chosenDate.get(
                Calendar.MONTH));
        refreshYearSelector();
        refreshButtons();
    }

    /**
     * Returns the date selected in the panel.
     *
     * @return the selected date.
     */
    public Date getDate() {
        return this.chosenDate.getTime();
    }


    /**
     * Returns a panel of buttons, each button representing a day in the month.
     * This is a sub-component of the DatePanel.
     *
     * @return the panel.
     */
    private JPanel getCalendarPanel() {

        final JPanel p = new JPanel(new GridLayout(7, 7));
        final DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        final String[] weekDays = dateFormatSymbols.getShortWeekdays();

        for (int i = 0; i < this.WEEK_DAYS.length; i++) {
            p.add(new JLabel(weekDays[this.WEEK_DAYS[i]].substring(0, 1),
                    SwingConstants.CENTER));
        }

        this.buttons = new JButton[42];
        for (int i = 0; i < 42; i++) {
            final JButton b = new JButton("");
            b.setMargin(new Insets(1, 1, 1, 1));
            b.setName(Integer.toString(i));
            b.setFocusPainted(false);
            this.buttons[i] = b;
            p.add(b);
        }
        return p;

    }

    /**
     * Returns the button color according to the specified date.
     *
     * @param theDate the date.
     * @return the color.
     */
    private Color getButtonColor(final Calendar theDate) {

        if (equalDates(theDate, this.chosenDate)) {
            return this.chosenDateButtonColor;
        } else if (theDate.get(Calendar.MONTH) == this.chosenDate.get(
                Calendar.MONTH)) {
            return this.chosenMonthButtonColor;
        } else {
            return this.chosenOtherButtonColor;
        }
    }

    /**
     * Returns true if the two dates are equal (time of day is ignored).
     *
     * @param c1 the first date.
     * @param c2 the second date.
     * @return boolean.
     */
    private boolean equalDates(final Calendar c1, final Calendar c2) {
        if ((c1.get(Calendar.DATE) == c2.get(Calendar.DATE))
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the first date that is visible in the grid.  This should always
     * be in the month preceding the month of the selected date.
     *
     * @return the date.
     */
    public Calendar getFirstVisibleDate() {
        final Calendar c = Calendar.getInstance();
        c.set(this.chosenDate.get(Calendar.YEAR), this.chosenDate.get(
                Calendar.MONTH), 1);
        c.add(Calendar.DATE, -1);
        while (c.get(Calendar.DAY_OF_WEEK) != getFirstDayOfWeek()) {
            c.add(Calendar.DATE, -1);
        }
        return c;
    }

    /**
     * Returns the first day of the week (controls the labels in the date
     * panel).
     *
     * @return the first day of the week.
     */
    private int getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    /**
     * Update the button labels and colors to reflect date selection.
     */
    public void refreshButtons() {
        final Calendar c = getFirstVisibleDate();
        for (int i = 0; i < 42; i++) {
            final JButton b = this.buttons[i];
            b.setText(Integer.toString(c.get(Calendar.DATE)));
            b.setBackground(getButtonColor(c));
            c.add(Calendar.DATE, 1);
        }
    }

    /**
     * Changes the contents of the year selection JComboBox to reflect the
     * chosen date and the year range.
     */
    public void refreshYearSelector() {
        if (!this.refreshing) {
            this.refreshing = true;
            this.yearSelector.removeAllItems();
            final Integer[] years = getYears();
            for (int i = 0; i < years.length; i++) {
                this.yearSelector.addItem(years[i]);
            }
            this.yearSelector.setSelectedItem(new Integer(this.chosenDate.get(
                    Calendar.YEAR)));
            this.refreshing = false;
        }
    }

    /**
     * Returns a vector of years preceding and following the specified year.
     * The number of years preceding and following is determined by the
     * yearSelectionRange attribute.
     *
     * @return a vector of years.
     */
    private Integer[] getYears() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0));
        final int min = calendar.get(Calendar.YEAR) + 1;
        final int max = min + 200;

        final Integer[] years = new Integer[max - min + 1];
        for (int i = 0; i <= max - min; i++) {
            years[i] = min + i;
        }
        return years;
    }

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    /**
     * Constructs a panel containing two JComboBoxes (for the month and year)
     * and a button (to reset the date to TODAY).
     *
     * @return the panel.
     */
    private JPanel constructSelectionPanel() {
        final JPanel p = new JPanel();

        final int minMonth = this.chosenDate.getMinimum(Calendar.MONTH);
        final int maxMonth = this.chosenDate.getMaximum(Calendar.MONTH);
        final String[] months = new String[maxMonth - minMonth + 1];
        for (int i = 0; i < months.length; i++) {
            months[i] = getMonthForInt(i);
        }

        this.monthSelector = new JComboBox<>(months);
        p.add(this.monthSelector);
        this.yearSelector = new JComboBox<>();

        p.add(this.yearSelector);

        return p;
    }

    /**
     * Returns a panel that appears at the bottom of the calendar panel -
     * contains a button for selecting today's date.
     *
     * @return the panel.
     */
    private JPanel constructControlPanel() {

        final JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        this.todayButton = new JButton("Today");
        p.add(this.todayButton);
        return p;
    }

    public JComboBox getMonthSelector() {
        return monthSelector;
    }

    public JComboBox getYearSelector() {
        return yearSelector;
    }

    public JButton getTodayButton() {
        return todayButton;
    }

    public JButton[] getButtons() {
        return buttons;
    }

}

