import java.awt.Container;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AvailableRoomsView{
	JFrame AvailPage;
	JTextField RoomNumRes;
	JTextArea RoomNumResmsg;
	TextArea Rooms;
	JButton Confirm, MRes, Done;
	JPanel panel;
	Container pane;
	RoomScheduler rs= new RoomScheduler();
	public void AvailableRooms(Date checkin, Date checkout, boolean a){
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
		//Date d = null;
		//System.out.println(d);
		//rs.getByDay(checkin);
		//rs.getByDay(checkin);
		Rooms.setText(rs.getByDayString(checkin));
		AvailPage.setVisible(true);
		
		
		
	}
	 class Donebtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
           //all done'
			AvailPage.dispose();
		}
		
	}
	static class Confirmbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
           //Send info to model
		}
		
	}
	class MakeResbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
			CheckinView view = new CheckinView();
            view.Checkin();
            AvailPage.dispose();
		}
		
	}
}