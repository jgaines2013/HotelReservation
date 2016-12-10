import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartUpView{
	 JButton Guest;
	 JButton Manager;
	 JFrame HotelWindow;
	 JButton MRes;
	 JButton VRes;
	 JPanel panel;
	 Container pane;
	 public void startup(){
	
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
	 class Guestbtn implements ActionListener{
			public void actionPerformed (ActionEvent e){
				SignInView view= new SignInView();
				view.SignIn();
				HotelWindow.dispose();
				
	            }
		}
	  class Managerbtn implements ActionListener{
			public void actionPerformed (ActionEvent e){
				ManagerSigninView view= new ManagerSigninView();
	            view.SignIn();
	            HotelWindow.dispose();
	            }
		}
	}
	 