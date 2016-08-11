import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class HomeButton extends JButton {
	
	private final String path = "./homepage.txt";
	private String textData;
	
	public HomeButton(final URLBar bar) throws Exception {
		
		//This gives a name to the home button on screen
		super("Home");
		
		OpenHomepageFile(bar);
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				//This method takes the returned homepage URL textData and loads up that URL via the loadCOntent method when pressed
				try {
                //Execute when button is pressed
					
        			//This section checks to see if the back button has been pressed, and if it has, remove all history 
        			//after the point the user has gone back to if they visit a new URL after going back
					if (BackButton.backInt < 0) {
						for (int i = URLBar.historyPosition + 1; i < URLBar.history.size(); i++) {
							URLBar.history.remove(i);
						}
					}
					
					bar.loadContent(textData);
					bar.addSessionHistoryMenuItem(textData);
					URLBar.history.add(textData);
					URLBar.historyPosition += 1;
					URLBar.persistentHistory.add(textData);
					System.out.println("You clicked the home button");
					System.out.println("Page loaded: " + textData);
				}
				
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Either: \n - The homepage file isn't with the JAR file \n - The homepage/entered URL is invalid \n - You are not connected to the Internet");
		        	System.out.println("A wild exception appeared! Type: " + e);
				}
                }
			});
	}
	
	//This method opens the homepage.txt document and reads in the first line, and returns the value read in as a string
	private String OpenHomepageFile(final URLBar bar) throws Exception {
    	
    	FileReader fr = new FileReader(path);
    	BufferedReader textReader = new BufferedReader(fr);
    	
    	textData = textReader.readLine();
    	
    	textReader.close();
    	bar.setText(textData);
    	return textData;
    	
    }

}
