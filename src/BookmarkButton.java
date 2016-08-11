import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;


public class BookmarkButton extends JButton {
	
	public static ArrayList<String> bookmarks = new ArrayList<String>();
	private String bookmarkPath = "./bookmarks.txt";
	
	public BookmarkButton(final URLBar bar) {
		
		super("Add to Bookmarks");
		
		//This adds the current URL in the address bar to the bookmarks.txt document
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                try {
					WriteBookmark(bar.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
                }
			});
		
	}
	
	private void WriteBookmark(String bookmarkURL) throws Exception {
    	
    	FileWriter writer = new FileWriter(bookmarkPath, true);
    	BufferedWriter textWriter = new BufferedWriter(writer);
    	
    	textWriter.write(bookmarkURL);
    	textWriter.newLine();
    	textWriter.close();
    	
    }

}
