import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class MenuBar extends JMenuBar {
	
	public static String bookmarkPath = "./bookmarks.txt";
	public static JMenu sessionHistory = new JMenu("Session History");
	public static JMenu bookmarks = new JMenu("Bookmarks");
	public static ArrayList<String> bookmarkList = new ArrayList<String>();
	
	//This adds the two JMenu items I have to the JMenuBar
	public MenuBar() throws Exception {
		add(sessionHistory); //Adds the Session History menu to the menu bar
		add(bookmarks);	 //Adds the bookmarks menu to the menu bar
	}
}
