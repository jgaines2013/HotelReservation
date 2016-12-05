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
		SignUp();
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
	public static void SignUp(){
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
	
	public static void AvailableRooms(){
		JFrame AvailPage;
		AvailPage = new JFrame("Available rooms");
		AvailPage.setSize(600, 300);
		pane= AvailPage.getContentPane();
		pane.setLayout(null);
		AvailPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(null);
		
		pane.add(panel);
		
		panel.setBounds(0,0,600,300);
		AvailPage.setVisible(true);
		
	}
	public static void ResPage(){
		JFrame Respage;
		Respage = new JFrame ("Hotel Window");
		Respage.setSize(500, 600);
		pane= Respage.getContentPane();
		pane.setLayout(null);
		Respage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Control labelling
		MRes= new JButton("Make Reservation");
		VRes= new JButton("View/cancel Reservation");
		
		//register actionlisteners
		MRes.addActionListener(new MakeResbtn());
		VRes.addActionListener(new ViewResbtn());
		
		
		//add to the window
		HotelWindow.add(MRes);
		HotelWindow.add(VRes);
	}
	
	//actionlistener class section
	static class MakeResbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            AvailableRooms();
		}
		
	}
	static class ViewResbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            AvailableRooms();
            }
	}
	static class SignInbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            ResPage();
            }
	}
	static class SignUpbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            AvailableRooms();
            }
	}
}
