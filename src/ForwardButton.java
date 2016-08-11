import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ForwardButton extends JButton {
	
	public ForwardButton(final URLBar bar) {
		
		super(">>>");
		
		//This button adds 1 to the position int, marking where the user is in the history list.
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				try {
	                //Execute when button is pressed
					URLBar.historyPosition += 1;
	                bar.loadContent(URLBar.history.get(URLBar.historyPosition));
	                System.out.println("You clicked the forward button");
	                System.out.println("Page loaded: " + URLBar.history.get(URLBar.historyPosition));
				}
				
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "There is nothing to move forward to! \n Browse more!");
		        	System.out.println("A wild exception appeared! Type: " + e);
		        	URLBar.historyPosition -= 1;  //If there is no page to move forward to, minus one from the position int
				}
                }
			});
		
	}

}
