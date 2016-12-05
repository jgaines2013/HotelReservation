//import com.controller.Controller;
//import com.model.Model;
//import com.model.Task;
//import com.TasksIO;
//import com.view.MainView;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Start class
 */
public class SimpleCalendar {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                HashMap<String, List<Task>> tasks;
                try {
                    tasks = TasksIO.load();
                } catch (IOException | ClassNotFoundException e) {
                    tasks = new HashMap<>();
                }

                Model model = new Model(tasks);
                MainView mainView = new MainView();

                new Controller(model, mainView);

                model.setChosenDate(new Date());

                mainView.launchFrame();
            }
        });

    }
}
