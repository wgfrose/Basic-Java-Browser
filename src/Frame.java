import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
 
public class Frame extends JFrame {
	
	//These are all the variables I use in this class
	private final String homepagePath = "./homepage.txt";
	private final String historyPath = "./history.txt";
	private String textData;
	private EditorPane pane;
    private URLBar addressBar;
    private JButton refreshButton;
    private JButton homeButton;
    private JButton backButton;
    private JButton forwardButton;
    private JButton bookmarkButton;
    private JMenuBar menuBar;
    
    public Frame() throws Exception {
    	
    	//This gives a name to the JFrame that's displayed
    	super("Stitchy Explorer 9000");
    	
    	//This section creates the new objects that will be displayed in the JFrame
    	addressBar = new URLBar();
    	menuBar = new MenuBar();
    	homeButton = new HomeButton(addressBar);
    	refreshButton = new ReloadButton(addressBar);
    	backButton = new BackButton(addressBar);
    	forwardButton = new ForwardButton(addressBar);
    	bookmarkButton = new BookmarkButton(addressBar);
    	pane = new EditorPane(addressBar);
        addressBar.setPane(pane);
        addressBar.OpenBookmarkFile();
 
        //This section creates all the new JPanels needed to hold all the individual components
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JPanel centreTop = new JPanel(new BorderLayout());
 
        //The following two sections add the components to their respective JPanels, then adds those JPanels to others, then finally to the JFrame itself
        centreTop.add(addressBar, BorderLayout.CENTER);
        centreTop.add(menuBar, BorderLayout.NORTH);
        
        mainPanel.add(pane, BorderLayout.CENTER);
        
        bottomPanel.add(backButton);
        bottomPanel.add(forwardButton);
        bottomPanel.add(bookmarkButton);
        bottomPanel.add(homeButton);
        bottomPanel.add(refreshButton);
        
        topPanel.add(centreTop, BorderLayout.CENTER);
 
        add(mainPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        add(new JScrollPane(mainPanel));
        setVisible(true);
        
        //This section try's first to open a home page document, and if not display an error message
        try {
        OpenHomepageFile();
    	pane.setPage(new URL (textData));
    	URLBar.history.add(textData);
		URLBar.historyPosition += 1;
		MenuBar.sessionHistory.add(textData);
		URLBar.persistentHistory.add(textData);
        }
        
        //Displays an error message when loadContent can't be used
        catch (Exception e) {
        	JOptionPane.showMessageDialog(null, "Either: \n - The homepage file isn't with the JAR file \n - The homepage/entered URL is invalid \n - You are not connected to the Internet");
        	System.out.println("A wild exception appeared! Type: " + e);
        }
        
        //This method writes the session history to the history.txt file when the window closes
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try { WriteHistory(); } 
                catch (Exception e1) { }
            }
        });
        
    }
    
    //This method parses the home page document and then returns the result as a string
    private String OpenHomepageFile() throws Exception {
    	
    	FileReader fr = new FileReader(homepagePath);
    	BufferedReader textReader = new BufferedReader(fr);
    	
    	textData = textReader.readLine();
    	
    	textReader.close();
    	System.out.println("Homepage loaded: " + textData);
    	addressBar.setText(textData);
    	
    	return textData;
    	
    }
    
    //This method writes the current sessions' history to a text document, so a persistent history is kept
    private void WriteHistory() throws Exception {
    	
    	FileWriter writer = new FileWriter(historyPath, true);
    	BufferedWriter textWriter = new BufferedWriter(writer);
    	
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //Takes the current date when writing the history to file, so you can see what you viewed on each session
    	Date date = new Date();
    	
    	textWriter.newLine();
    	
    	for(int i = 0; i < URLBar.persistentHistory.size(); i++) {
    	  textWriter.write(dateFormat.format(date) + " " + URLBar.persistentHistory.get(i)); //Writes the persistent history List to file
    	  textWriter.newLine();
    	}
    	textWriter.close();
    	
    }
}
