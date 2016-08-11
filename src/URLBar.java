import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
 
public class URLBar extends JTextField {
	
	public static int historyPosition = -1; //This int tracks the position of where the user is in the history list
	public static ArrayList<String> history = new ArrayList<String>(); //This stores the current sessions history and is the one navigated by the forward and back buttons
	public static ArrayList<String> persistentHistory = new ArrayList<String>(); //This stores a record of every site visited and then is written to the external txt file
	private EditorPane pane;
	
	public URLBar() {
		
		//Below is the URLBar listener that opens a new page when enter is pressed
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
    			//This section checks to see if the back button has been pressed, and if it has, remove all history 
    			//after the point the user has gone back to if they visit a new URL after going back
				if (BackButton.backInt < 0) {
					for (int i = historyPosition + 1; i < history.size(); i++) {
						history.remove(i);
					}
				}
				
				loadContent(event.getActionCommand());
				history.add(event.getActionCommand());
				persistentHistory.add(event.getActionCommand());
				historyPosition += 1;
				addSessionHistoryMenuItem(event.getActionCommand());
				System.out.println("Page loaded: " + event.getActionCommand());
				
				}
			});
		}
	
	public void setPane(EditorPane pane) {
		
		this.pane = pane;
		
        }
 
	//This is the method that is called whenever a page is loaded up onto the JEditorPane
	public void loadContent(String userInput) {
 
		try {
			//Set it via the setPane method
			if (pane == null) throw new Exception("Pane is null");
			pane.setPage(userInput);
			setText(userInput);
			OpenBookmarkFile();
		}
		catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Either: \n - The homepage file isn't with the JAR file \n - The homepage/entered URL is invalid \n - You are not connected to the Internet");
        	System.out.println("A wild exception appeared! Type: " + e);
		}
		
		}
	
	//This method adds the current session history list to a JMenu in my menu bar
	public void addSessionHistoryMenuItem(String text) {
	    JMenuItem newMenuItem = new JMenuItem(text);
	    newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
    			//This section checks to see if the back button has been pressed, and if it has, remove all history 
    			//after the point the user has gone back to if they visit a new URL after going back
				if (BackButton.backInt < 0) {
					for (int i = historyPosition + 1; i < history.size(); i++) {
						history.remove(i);
					}
				}
				
                loadContent(text);
                history.add(text);
                historyPosition += 1;
                System.out.println("Page loaded: " + text);
                }
			});
	    MenuBar.sessionHistory.add(newMenuItem); //Adds the new item to the history drop down on the menu bar
	}
	
	//This method reads in the bookmarks.txt file
    public void OpenBookmarkFile() throws Exception {
    	
    	FileReader fr = new FileReader(MenuBar.bookmarkPath); 
    	BufferedReader textReader = new BufferedReader(fr);
    	String bookmark = null;
    	
    	MenuBar.bookmarkList.clear();
    	
    	MenuBar.bookmarks.removeAll();
    	
    	//This bit says that while the current line doesn't have nothing on it, read it in
    	while(!((bookmark = textReader.readLine())== null)){
    		
    		MenuBar.bookmarkList.add(bookmark);
    		
    	}
    	
    	textReader.close();
    	
    	//This takes the list of read in bookmarks and adds them as new JMenuItems along with new actionListeners
    	for (int i = 0; i < MenuBar.bookmarkList.size(); i++) {
    		
    	JMenuItem newMenuItem = new JMenuItem(MenuBar.bookmarkList.get(i));
    	String bookmark2 = MenuBar.bookmarkList.get(i);
    	
	    newMenuItem.addActionListener(new ActionListener() {
	    	
			public void actionPerformed(ActionEvent event) {
				
    			//This section checks to see if the back button has been pressed, and if it has, remove all history 
    			//after the point the user has gone back to if they visit a new URL after going back
				if (BackButton.backInt < 0) {
					for (int i = historyPosition + 1; i < history.size(); i++) {
						history.remove(i);
					}
				}
				
                loadContent(bookmark2);
                System.out.println("Page loaded: " + bookmark2);
                addSessionHistoryMenuItem(bookmark2);
                URLBar.history.add(bookmark2);
                URLBar.historyPosition += 1;
                URLBar.persistentHistory.add(bookmark2);
                
                }
			});
	    
	    MenuBar.bookmarks.add(newMenuItem);
	    
    	}
    }
	
}