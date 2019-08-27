package tabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import engines.DatabaseEngine;

public class SavedWorkoutsTab {

	TabItem savedWorkoutsTab;
	Composite savedWorkoutsComposite;
	DatabaseEngine dbEngine = new DatabaseEngine();
	int recordId;

	public SavedWorkoutsTab(TabFolder tabFolder) {

		savedWorkoutsTab = new TabItem(tabFolder, SWT.NONE);
		savedWorkoutsComposite = new Composite(tabFolder, SWT.NONE);
		savedWorkoutsComposite.setLayout(new GridLayout(2, false));
		recordId = 0;

	}

	public void saveWorkout(String html) {

		dbEngine.writeToDB("INSERT INTO workouts VALUES ('" + String.valueOf(recordId) + "', '" + html + "')");
		recordId++;

	}

	public void displaySavedWorkouts() {

	}

}
