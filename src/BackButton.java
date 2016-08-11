import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class BackButton extends JButton {
	
	public static int backInt = 0;
	
	public BackButton(final URLBar bar) {
		
		super("<<<");
		
		//This button minuses 1 from the position int, marking where the user is in the history list.
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				try {
                //Execute when button is pressed
				URLBar.historyPosition -= 1;
				bar.loadContent(URLBar.history.get(URLBar.historyPosition));
                backInt -= 1;
                System.out.println("You clicked the back button");
                System.out.println("Page loaded: " + URLBar.history.get(URLBar.historyPosition));

				}
				
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "There is nothing to move back to! \n Browse more!");
		        	System.out.println("A wild exception appeared! Type: " + e);
		        	URLBar.historyPosition += 1; //If there is no page to move back to, add one back onto the position int
				}
                }
			});
		
	}

}
