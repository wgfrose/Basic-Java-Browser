import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ReloadButton extends JButton {

	public ReloadButton(final URLBar bar) {
		
		super("Reload");
		
		//Tells the button to get the current URL from the address bar and feed it back in to my loadContent method to refresh the page
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
                bar.loadContent(bar.getText());
                System.out.println("You clicked the reload button");
                System.out.println("Page loaded: " + bar.getText());
                }
			});
		}
}
