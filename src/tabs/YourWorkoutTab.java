package tabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import engines.CompileWorkoutEngine;

public class YourWorkoutTab extends Tab {

	TabItem yourWorkoutTab;
	Composite yourWorkoutComposite;
	TabFolder tabFolder;
	Browser browser;

	public YourWorkoutTab(TabFolder tFolder) {
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

	/*
	 * Display the workout in the tab.
	 */
	public void setWorkout(int[] index) {
		yourWorkoutComposite = new Composite(tabFolder, SWT.None);
		yourWorkoutTab.setControl(yourWorkoutComposite);
		CompileWorkoutEngine compileWorkoutEngine = new CompileWorkoutEngine(index);

		browser = new Browser(yourWorkoutComposite, SWT.NONE);
		browser.setBounds(89, 183, 420, 326);

		browser.setText(compileWorkoutEngine.getWorkoutHTML(compileWorkoutEngine.compileWorkout()));

	}
}
