import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class HotelGui{
	static JFrame HotelWindow;
	static JButton MRes;
	static JButton VRes;
	static JPanel panel;
	static Container pane;
	
	
	public static void main(String[] args)
	{
		StartUpView window= new StartUpView();
		window.startup();
		//startup();
		/*
		HotelWindow = new JFrame ("Hotel Window");
		HotelWindow.setSize(500, 600);
		HotelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Control labelling
		MRes= new JButton("Make Reservation");
		VRes= new JButton("View/cancel Reservation");
		
		
		//add to the window
		HotelWindow.add(MRes);
		HotelWindow.add(VRes);
		*/
		
		
		//HotelWindow.setVisible(true);
	}
	public static void startup(){
		 JButton Guest;
		 JButton Manager;
		 JFrame HotelWindow;
	
		
		HotelWindow = new JFrame ("Account page");
		HotelWindow.setSize(300, 200);
		pane= HotelWindow.getContentPane();
		pane.setLayout(null);
		HotelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Control labelling
		Guest= new JButton("Guest");
		Manager= new JButton("Manager");
		panel = new JPanel(null);
		
		
		//register actionlisteners
		Guest.addActionListener(new Guestbtn() );
		Manager.addActionListener(new Managerbtn() );
		
		//add to the window
		pane.add(panel);
		
		panel.add(Guest);
		panel.add(Manager);
		//HotelWindow.add(MRes);
		//HotelWindow.add(VRes);
		
		
		//setting bounds
		panel.setBounds(0, 0, 300, 200);
		Guest.setBounds(50, 100, 90, 30);
		Manager.setBounds(150, 100, 90, 30);
		HotelWindow.setVisible(true);
	}
	public static void SignIn(){
		 JButton Signin;
		 JButton Signup;
		 JTextField Username;
		 JFrame HotelWindow;
	
		
		HotelWindow = new JFrame ("Sign Up/In");
		HotelWindow.setSize(300, 200);
		pane= HotelWindow.getContentPane();
		pane.setLayout(null);
		HotelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Control labelling
		Signin= new JButton("Sign In");
		Signup= new JButton("Sign up");
		Username= new JTextField("Username");
		panel = new JPanel(null);
		
		
		//register actionlisteners
		Signin.addActionListener(new SignInbtn() );
		Signup.addActionListener(new SignUpbtn() );
		
		//add to the window
		pane.add(panel);
		
		panel.add(Signin);
		panel.add(Signup);
		panel.add(Username);
		//HotelWindow.add(MRes);
		//HotelWindow.add(VRes);
		
		
		//setting bounds
		panel.setBounds(0, 0, 300, 200);
		Signin.setBounds(50, 100, 90, 30);
		Signup.setBounds(150, 100, 90, 30);
		Username.setBounds(100, 50, 100,20 );
		HotelWindow.setVisible(true);
	}
	public static void SignUp(){
		 JButton Signup;
		 JTextField Username;
		 JFrame HotelWindow;
	
		
		HotelWindow = new JFrame ("Sign Up/In");
		HotelWindow.setSize(300, 200);
		pane= HotelWindow.getContentPane();
		pane.setLayout(null);
		HotelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Control labelling
		Signup= new JButton("Sign up");
		Username= new JTextField("Username");
		panel = new JPanel(null);
		
		
		//register actionlisteners
		Signup.addActionListener(new SignUpbtn() );
		
		//add to the window
		pane.add(panel);

		
		panel.add(Signup);
		panel.add(Username);
		//HotelWindow.add(MRes);
		//HotelWindow.add(VRes);
		
		
		//setting bounds
		panel.setBounds(0, 0, 300, 200);
		Signup.setBounds(150, 100, 90, 30);
		Username.setBounds(100, 50, 100,20 );
		HotelWindow.setVisible(true);
	}
	public static void Checkin(){
		 JButton Lux;
		 JButton Econ;
		 JTextField Checkin;
		 JTextField Checkout;
		 JFrame HotelWindow;
		 JLabel checkin= new JLabel("Check in");
		 JLabel checkout= new JLabel("Check out");
	
		
		HotelWindow = new JFrame ("Sign Up/In");
		HotelWindow.setSize(300, 200);
		pane= HotelWindow.getContentPane();
		pane.setLayout(null);
		HotelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Control labelling
		Lux= new JButton("Luxury");
		Econ= new JButton("Economy");
		Checkin= new JTextField("mmddyyyy");
		Checkout= new JTextField("mmddyyyy");
		panel = new JPanel(null);
		
		
		//register actionlisteners
		Lux.addActionListener(new Luxbtn() );
		Econ.addActionListener(new Econbtn() );
		
		//add to the window
		pane.add(panel);
		
		panel.add(Checkin);
		panel.add(Checkout);
		panel.add(Lux);
		panel.add(Econ);
		panel.add(checkin);
		panel.add(checkout);
		
		//HotelWindow.add(MRes);
		//HotelWindow.add(VRes);
		
		
		//setting bounds
		panel.setBounds(0, 0, 300, 200);
		Checkin.setBounds(50, 60, 90, 30);
		Checkout.setBounds(150, 60, 90, 30);
		checkin.setBounds(55, 35, 90, 30);
		checkout.setBounds(155, 35, 90, 30);
		Lux.setBounds(50, 135, 100,20 );
		Econ.setBounds(150, 135, 100,20 );
		HotelWindow.setVisible(true);
	}
	public static void AvailableRooms(String checkin, String checkout){
		JFrame AvailPage;
		JTextField RoomNumRes;
		JTextArea RoomNumResmsg;
		TextArea Rooms;
		JButton Confirm, MRes, Done;
		
		AvailPage = new JFrame("Available rooms");
		AvailPage.setSize(700, 300);
		pane= AvailPage.getContentPane();
		pane.setLayout(null);
		AvailPage.setDefaultCloseOperation(AvailPage.EXIT_ON_CLOSE);
		panel = new JPanel(null);
		
		
		//text area
		RoomNumResmsg= new JTextArea("Room number \n to reserve");
		RoomNumRes= new JTextField("Room number");
		Rooms = new TextArea();
		Confirm= new JButton("Confirm");
		MRes= new JButton("More reservations");
		Done= new JButton("Done");
		
		
		Rooms.setText("rooms go here");
		
		pane.add(panel);
		//add to window
		panel.add(Rooms);
		panel.add(RoomNumRes);
		panel.add(RoomNumResmsg);
		panel.add(Confirm);
		panel.add(MRes);
		panel.add(Done);
		
		
		//actionListener
		MRes.addActionListener(new MakeResbtn());
		Done.addActionListener(new Donebtn());
		Confirm.addActionListener(new Confirmbtn());
		
		
		Confirm.setBounds(325, 200, 100, 40);
		MRes.setBounds(435, 200, 150, 40);
		Done.setBounds(585, 200, 100, 40);
		RoomNumResmsg.setBounds(340, 5, 200, 60);
		RoomNumRes.setBounds(550, 5, 100, 60);
		Rooms.setBounds(5, 5, 300,250);
		panel.setBounds(0,0,700,300);
		AvailPage.setVisible(true);
		
	}
	public static void ResPage(){
		JFrame Respage;
		Respage = new JFrame ("Hotel Window");
		Respage.setSize(350, 150);
		pane= Respage.getContentPane();
		pane.setLayout(null);
		Respage.setDefaultCloseOperation(Respage.EXIT_ON_CLOSE);
		panel = new JPanel(null);
		
		
		
		//Control labelling
		MRes= new JButton("Make Reservation");
		VRes= new JButton("View/cancel Reservation");
		
		//register actionlisteners
		MRes.addActionListener(new MakeResbtn());
		VRes.addActionListener(new ViewResbtn());
		
		
		//add to the window
		Respage.add(MRes);
		Respage.add(VRes);
		
		//set bounds
		MRes.setBounds(20,50,140,40);
		VRes.setBounds(170, 50, 140, 40);
		
		Respage.setVisible(true);
	}
	
	//actionlistener class section
	static class Donebtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
           //all done
		}
		
	}
	static class Confirmbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
           //Send info to model
		}
		
	}
	static class MakeResbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            Checkin();
		}
		
	}
	static class ViewResbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            AvailableRooms(null, null);
            }
	}
	static class Luxbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            //shows luxury rooms available for that stay
			AvailableRooms(null, null);
            }
	}
	static class Econbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            //shows Economy room available for that stay period
			AvailableRooms(null, null);
            }
	}
	static class Guestbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
			
			SignIn();
			
            }
	}
	static class Managerbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            ResPage();
            }
	}
	static class SignInbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            ResPage();
            }
	}
	static class SignUpbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            SignUp();
            }
	}
}