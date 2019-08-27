package tabs;

import static constants.Constants.saveWorkout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;

import engines.CompileWorkoutEngine;

public class YourWorkoutTab extends Tab {

	TabItem yourWorkoutTab;
	Composite yourWorkoutComposite;
	TabFolder tabFolder;
	Browser browser;
	String introLabelText;

	SavedWorkoutsTab savedWorkoutsTab;

	public YourWorkoutTab(TabFolder tFolder, SavedWorkoutsTab savedWkoutsTab) {

		super(tFolder);
		tabFolder = tFolder;
		yourWorkoutTab = this.tab;
		yourWorkoutTab.setText("Your workout");
		yourWorkoutComposite = this.composite;
		yourWorkoutTab.setControl(yourWorkoutComposite);

		savedWorkoutsTab = savedWkoutsTab;
	}

	public TabItem getTab() {
		return this.yourWorkoutTab;
	}

	public Composite getComposite() {
		return this.yourWorkoutComposite;
	}

	public void setIntroLabelText(String text) {
		this.introLabelText = text;
	}

	/*
	 * Display the workout in the tab. Called by NewWorkoutTab after user
	 * preferences are submitted.
	 */
	public void setWorkout(int[] index) {

		yourWorkoutComposite = new Composite(tabFolder, SWT.None);
		yourWorkoutTab.setControl(yourWorkoutComposite);
		CompileWorkoutEngine compileWorkoutEngine = new CompileWorkoutEngine(index);

		this.setLabel(yourWorkoutComposite, introLabelText, 89, 69, 822, 21, SWT.CENTER,
				SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));

		browser = new Browser(yourWorkoutComposite, SWT.NONE);
		browser.setBounds(89, 96, 822, 495);
		String workoutHtml = compileWorkoutEngine.getWorkoutHTML(compileWorkoutEngine.compileWorkout());
		browser.setText(workoutHtml);

		//save workout button
		Button b = setButton(yourWorkoutComposite, saveWorkout, SWT.NONE, 470, 608, 75, 25,
				SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		b.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				savedWorkoutsTab.saveWorkout(workoutHtml);
			}
		});

	}
}
