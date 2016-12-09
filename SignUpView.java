import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpView{
	 JButton Signup;
	 JTextField Username;
	 JFrame HotelWindow;
	 JPanel panel;
	 Container pane;
	public void SignUp(){
		
			
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
	 class SignUpbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
            ResPageView view= new ResPageView();
			view.ResPage();
			HotelWindow.dispose();
			
            }
	}
}