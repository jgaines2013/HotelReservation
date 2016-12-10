import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerMenuViews{
	JButton LoadReservations;
	 JButton ViewInformation;
	 JButton SaveReservations;
	 JButton quit;
	 JTextField Username;
	 JFrame HotelWindow;
	 JPanel panel;
	 Container pane;
	public void ManagerSignin(){
		
		
		HotelWindow = new JFrame ("Manager Sign Up/In");
		HotelWindow.setSize(300, 200);
		pane= HotelWindow.getContentPane();
		pane.setLayout(null);
		HotelWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Control labelling
		LoadReservations= new JButton("Load Reservations");
		ViewInformation= new JButton("View Information");
		SaveReservations= new JButton("Save Reservations");
		quit= new JButton("Quit");
		Username= new JTextField("Username");
		panel = new JPanel(null);
		
		
		//register actionlisteners
		LoadReservations.addActionListener(new LoadResbtn() );
		ViewInformation.addActionListener(new ViewInfobtn() );
		SaveReservations.addActionListener(new SaveResbtn() );
		quit.addActionListener(new quitbtn() );
		
		//add to the window
		pane.add(panel);
		
		panel.add(LoadReservations);
		panel.add(ViewInformation);
		panel.add(SaveReservations);
		panel.add(quit);
		panel.add(Username);
		//HotelWindow.add(MRes);
		//HotelWindow.add(VRes);
		
		
		//setting bounds
		panel.setBounds(0, 0, 300, 200);
		LoadReservations.setBounds(15, 100, 140, 30);
		ViewInformation.setBounds(150, 100, 140, 30);
		SaveReservations.setBounds(15, 140, 140, 30);
		quit.setBounds(150, 140, 140, 30);
		Username.setBounds(100, 50, 100,20 );
		HotelWindow.setVisible(true);
	}
	class LoadResbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
			ResMenuView view=new ResMenuView();
            view.ResPage();
            HotelWindow.dispose();
            }
	}
	 class ViewInfobtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
			MgrMonthView view = new MgrMonthView();
            view.launchFrame();
            //HotelWindow.dispose();
            }
	}
	 class SaveResbtn implements ActionListener{
			public void actionPerformed (ActionEvent e){
				//save the reservations 
	            //HotelWindow.dispose();
	            }
		}
	 class quitbtn implements ActionListener{
			public void actionPerformed (ActionEvent e){
				//save the reservations then quit
	            HotelWindow.dispose();
	            }
		}
}