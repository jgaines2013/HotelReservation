import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerSigninView{
	JButton Signin;
	 JButton Signup;
	 JTextField Username;
	 JFrame HotelWindow;
	 JPanel panel;
	 //JLabel Congrats;
	 Container pane;
	 int ID;
	static AccountManager user = new AccountManager();
	 private static HashMap<Integer, User> users;
	public void SignIn(){
		
		
		HotelWindow = new JFrame (" Manager Sign Up/In");
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
		Username.setBounds(100, 60, 100,20 );
		HotelWindow.setVisible(true);
	}
	 class SignInbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
			
			
				
				//if ID is in List of Manager IDs, log in
				//take the textfield convert to number then take number compare  
				//to other numbers in Manager ID
				//Username.get
				ID = Integer.parseInt(Username.getText());
				try {
					if (user.login(ID) != null )
					{
						
					
					//ID= Integer.parseInt(Username.toString());
					ManagerMenuViews view=new ManagerMenuViews();
					//RoomScheduler rs = new RoomScheduler();
					view.ManagerSignin();
					HotelWindow.dispose();
					}
					else
					{
						System.out.println("not here");
					}
				} catch (UserNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			

            }
	}
	 class SignUpbtn implements ActionListener{
		public void actionPerformed (ActionEvent e){
			String username = Username.toString();
			
			ID = user.setupManager(username);
			
			JLabel Congrats = new JLabel("Congrats "+ Username.getText()+", your ID is "+ ID);
			panel.add(Congrats);
			Congrats.setBounds(20,5,280, 60);
			//ManagerSigninView view = new ManagerSigninView();
            //view.SignIn();
            //HotelWindow.dispose();
            }
	}
}