import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
 
public class EditorPane extends JEditorPane {
 
	public EditorPane(final URLBar bar) {
		
		//This adds the hyperlink listener to the editor pane 
		setEditable(false);
        addHyperlinkListener(new HyperlinkListener() {
        	public void hyperlinkUpdate(HyperlinkEvent event) {
        		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
        			
        			//This section checks to see if the back button has been pressed, and if it has, remove all history 
        			//after the point the user has gone back to if they visit a new URL after going back
    				if (BackButton.backInt < 0) {
    					for (int i = URLBar.historyPosition + 1; i < URLBar.history.size(); i++) {
    						URLBar.history.remove(i);
    					}
    				}
        			
        			bar.loadContent(event.getURL().toString()); //Loads the content of the URL clicked
        			bar.addSessionHistoryMenuItem(event.getURL().toString()); //Adds a menu item to session history with the URL that you just clicked on
        			URLBar.history.add(event.getURL().toString()); //Adds the URL to the history List
        			URLBar.historyPosition += 1; //Increments the size of the historyPosition int by one so the browser knows where you are while navigating the history list
        			URLBar.persistentHistory.add(event.getURL().toString());
        			}
        		}
        	});
        }
}