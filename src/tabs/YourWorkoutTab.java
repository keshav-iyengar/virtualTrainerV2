package tabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class YourWorkoutTab extends Tab {

	TabItem yourWorkoutTab;
	Composite yourWorkoutComposite;
	int workoutIndex;
	CLabel lblTestWorkout;
	TabFolder tabFolder;
	Browser browser;

	//HashMap<Integer, HashMap<String, Exercise>> workouts = new HashMap<>();
	//HashMap<Integer, Exercise> workouts = new HashMap<>();

	public YourWorkoutTab(TabFolder tFolder, int wktIndex) {
		tabFolder = tFolder;
		yourWorkoutTab = new TabItem(tabFolder, SWT.NONE);
		yourWorkoutTab.setText("Your workout");
		yourWorkoutComposite = new Composite(tabFolder, SWT.NONE);
		yourWorkoutTab.setControl(yourWorkoutComposite);
	}

	public TabItem getTab() {
		return this.yourWorkoutTab;
	}

	public Composite getComposite() {
		return this.yourWorkoutComposite;
	}

	public void setWorkoutIndex(int index) {
		this.workoutIndex = index;
	}

	public void setWorkout(int[] index) {
		this.yourWorkoutComposite = new Composite(tabFolder, SWT.None);
		yourWorkoutTab.setControl(yourWorkoutComposite);

		browser = new Browser(yourWorkoutComposite, SWT.NONE);
		browser.setBounds(89, 183, 420, 326);
		browser.setText("<html>line1<br>line2<br>" + "index: " + index + "</html>");

	}
}
