package tabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class YourWorkoutTab extends Tab {

	TabItem yourWorkoutTab;
	Composite yourWorkoutComposite;

	public YourWorkoutTab(TabFolder tabFolder) {
		yourWorkoutTab = new TabItem(tabFolder, SWT.NONE);
		yourWorkoutTab.setText("Your workout");
		yourWorkoutComposite = new Composite(tabFolder, SWT.NONE);
		yourWorkoutTab.setControl(yourWorkoutComposite);
	}

	public TabItem getTab() {
		return this.yourWorkoutTab;
	}
}
