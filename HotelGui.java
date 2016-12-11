import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class HotelGui{
//	static JFrame HotelWindow;
//	static JButton MRes;
//	static JButton VRes;
//	static JPanel panel;
//	static Container pane;
	private static HashMap<Integer, User> users;
	private static Set<Room> rooms;
	
	
	public static void main(String[] args)
	{
		StartUpView window= new StartUpView();
		window.startup();
		
	}
	
}
