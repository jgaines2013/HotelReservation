import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ResMenuView{
	 //JFrame HotelWindow;
	 JButton MRes;
	 JButton VRes;
	 JPanel panel;
	 JFrame Respage;
	 Container pane;
	public void ResPage(){
		
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
	 class MakeResbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
			CheckinView view= new CheckinView();
            view.Checkin();
            Respage.dispose();
		}
		
	}
	 class ViewResbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
			AvailableRoomsView view = new AvailableRoomsView();
			view.AvailableRooms(null, null);
			Respage.dispose();
            }
	}
}