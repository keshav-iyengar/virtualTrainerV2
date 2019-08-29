package tabs;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import engines.DatabaseEngine;

public class SavedWorkoutsTab extends Tab {

	TabFolder tabFolder;
	TabItem savedWorkoutsTab;
	Composite savedWorkoutsComposite;
	DatabaseEngine dbEngine = new DatabaseEngine();
	int recordId;
	YourWorkoutTab yourWorkoutTab;
	String savedWorkoutHtml = "";
	ArrayList<String> savedWorkouts = new ArrayList<String>();

	public SavedWorkoutsTab(TabFolder tFolder, YourWorkoutTab yourWkoutTab) {

		super(tFolder, "Saved workouts");
		tabFolder = tFolder;
		savedWorkoutsTab = this.tab;
		savedWorkoutsComposite = this.composite;
		savedWorkoutsComposite.setLayout(new GridLayout(2, false));
		recordId = 1;
		yourWorkoutTab = yourWkoutTab;

	}

	public void setSavedWorkoutHtml(String html) {
		this.savedWorkoutHtml = html;
	}

	public void saveWorkout() {

		dbEngine.openConnection();
		dbEngine.writeToDB(
				"INSERT INTO workouts VALUES ('" + String.valueOf(recordId) + "', '" + savedWorkoutHtml + "')");
		recordId++;
		displaySavedWorkoutLinks();
		dbEngine.closeConnection();

	}

	public void displaySavedWorkoutLinks() {

		savedWorkoutsComposite = new Composite(tabFolder, SWT.NONE);
		savedWorkoutsComposite.setLayout(new GridLayout(2, false));
		savedWorkoutsTab.setControl(savedWorkoutsComposite);

		for(int i = 1; i < recordId; i++) {

			int id = i;
			dbEngine.openConnection();
			String html = dbEngine.readFromDB("SELECT * FROM workouts WHERE id = " + String.valueOf(id));
			dbEngine.closeConnection();

			if(!html.equals("Read failed")) {
				//link that points to saved workout
				Link linkSavedWorkout = new Link(savedWorkoutsComposite, SWT.NONE);
				linkSavedWorkout.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event event) {
						yourWorkoutTab.setIntroLabelText("Saved workout " + String.valueOf(id));
						yourWorkoutTab.setWorkout(null, html);
						tabFolder.setSelection(1);
					}
				});
				linkSavedWorkout.setText("<a>Saved workout " + String.valueOf(id) + "</a>");

				//link to remove saved workout
				Link linkRmWorkout = new Link(savedWorkoutsComposite, SWT.NONE);
				linkRmWorkout.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event event) {
						linkSavedWorkout.dispose();
						linkRmWorkout.dispose();
						dbEngine.openConnection();
						dbEngine.writeToDB("DELETE FROM workouts WHERE id = " + String.valueOf(id));
						dbEngine.closeConnection();
					}
				});
				linkRmWorkout.setText("<a>Remove workout " + String.valueOf(id) + "</a>");
			}

		}

	}

}
