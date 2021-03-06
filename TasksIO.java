//package com;

//import com.model.Task;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Save and restore tasks to/from file
 */
public class TasksIO {
    public static final String fileName = "events.txt";

    public static void save(HashMap<String, List<Task>> tasks) throws IOException {
        ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(fileName));
        ois.writeObject(tasks);
    }

    public static HashMap<String, List<Task>> load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        return (HashMap<String, List<Task>>) ois.readObject();
    }

}
