
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Save and restore tasks to/from file
 */
public class ReservationsIO {
    public static final String fileName = "events.txt";

    public static void save(HashMap<String, List<Reservation>> Reservations) throws IOException {
        ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(fileName));
        ois.writeObject(Reservations);
    }

    public static HashMap<String, List<Reservation>> load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        return (HashMap<String, List<Reservation>>) ois.readObject();
    }

}
