import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckinView{
	 JButton Lux;
	 JButton Econ;
	 JTextField Checkin;
	 JTextField Checkout;
	 JFrame HotelWindow;
	 JLabel checkin= new JLabel("Check in");
	 JLabel checkout= new JLabel("Check out");
	 JPanel panel;
	 Container pane;
	 public void Checkin(){
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
	  class Luxbtn implements ActionListener{
			public void actionPerformed (ActionEvent e){
	            //shows luxury rooms available for that stay
				AvailableRoomsView view = new AvailableRoomsView();
				view.AvailableRooms(null, null);
				HotelWindow.dispose();
	            }
		}
	class Econbtn implements ActionListener{
			public void actionPerformed (ActionEvent e){
	            //shows Economy room available for that stay period
				AvailableRoomsView view = new AvailableRoomsView();
				view.AvailableRooms(null, null);
				HotelWindow.dispose();
	            }
		}
}