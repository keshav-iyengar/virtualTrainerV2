package tabs;

import static constants.Constants.aestheticGoalsKey;
import static constants.Constants.buildMassKey;
import static constants.Constants.burnFatKey;
import static constants.Constants.gainEnduranceKey;
import static constants.Constants.gainStrengthKey;
import static constants.Constants.halfHrKey;
import static constants.Constants.hrAndHalfKey;
import static constants.Constants.hrKey;
import static constants.Constants.naKey;
import static constants.Constants.performanceGoalsKey;
import static constants.Constants.twoHrKey;
import static constants.Constants.workoutDurationKey;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class NewWorkoutTab implements Tab {

	TabItem newWorkoutTab;
	Composite newWorkoutComposite;
	Composite workoutDurationComposite;
	Composite aestheticGoalsComposite;
	Composite performanceGoalsComposite;
	HashMap<String, HashMap<String, Boolean>> selectedValues = new HashMap<String, HashMap<String, Boolean>>();

	public NewWorkoutTab(TabFolder tabFolder) {

		newWorkoutTab = new TabItem(tabFolder, SWT.NONE);
		newWorkoutTab.setText("New workout");

		newWorkoutComposite = new Composite(tabFolder, SWT.NONE);
		newWorkoutTab.setControl(newWorkoutComposite);
		newWorkoutComposite.setLayout(null);

		setLabel(newWorkoutComposite,
				"Tell the trainer your fitness goals! Hit submit when you're done to see your personalized workout.",
				31, 10, 720, 15, SWT.CENTER);

		// Aesthetic goals button group
		aestheticGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		aestheticGoalsComposite.setBounds(31, 70, 313, 16);
		setLabel(aestheticGoalsComposite, aestheticGoalsKey, 0, 0, 81, 15, 0);
		setButton(aestheticGoalsComposite, aestheticGoalsKey, buildMassKey, SWT.RADIO, 108, -1, 93, 16);
		setButton(aestheticGoalsComposite, aestheticGoalsKey, burnFatKey, SWT.RADIO, 207, -1, 93, 16);
		setButton(aestheticGoalsComposite, aestheticGoalsKey, naKey, SWT.RADIO, 275, -1, 90, 16);

		// Performance goals button group
		performanceGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		performanceGoalsComposite.setBounds(31, 92, 391, 16);
		setLabel(performanceGoalsComposite, performanceGoalsKey, 0, 0, 114, 15, 0);
		setButton(performanceGoalsComposite, performanceGoalsKey, gainStrengthKey, SWT.RADIO, 108, -1, 92, 16);
		setButton(performanceGoalsComposite, performanceGoalsKey, gainEnduranceKey, SWT.RADIO, 206, -1, 109, 16);
		setButton(performanceGoalsComposite, performanceGoalsKey, naKey, SWT.RADIO, 321, -1, 59, 16);

		// Workout duration button group
		workoutDurationComposite = new Composite(newWorkoutComposite, SWT.NONE);
		workoutDurationComposite.setBounds(31, 114, 346, 16);
		setLabel(workoutDurationComposite, workoutDurationKey, 0, 1, 111, 15, 0);
		setButton(workoutDurationComposite, workoutDurationKey, halfHrKey, SWT.RADIO, 117, 0, 45, 16);
		setButton(workoutDurationComposite, workoutDurationKey, hrKey, SWT.RADIO, 168, 0, 42, 16);
		setButton(workoutDurationComposite, workoutDurationKey, hrAndHalfKey, SWT.RADIO, 216, 0, 55, 16);
		setButton(workoutDurationComposite, workoutDurationKey, twoHrKey, SWT.RADIO, 279, 0, 90, 16);

	}

	@Override
	public void setLabel(Composite composite, String labelText, int boundX, int boundY, int boundW, int boundH,
			int alignment) {

		Label label = new Label(composite, SWT.NONE);
		if(alignment != 0) label.setAlignment(alignment);
		label.setBounds(boundX, boundY, boundW, boundH);
		label.setText(labelText);

	}

	@Override
	public void setButton(Composite composite, String categoryKey, String buttonKey, int buttonType, int boundX,
			int boundY, int boundW, int boundH) {

		Button button = new Button(composite, buttonType);
		button.setBounds(boundX, boundY, boundW, boundH);
		button.setText(buttonKey);

		//populate the hashmap with boolean values corresponding to selection
		button.addSelectionListener(new SelectionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
			public void widgetSelected(SelectionEvent event) {

				if(button.getSelection()) {

					selectedValues.put(categoryKey, new HashMap() {
						{
							put(buttonKey, true);
						}
					});
					System.out.println(selectedValues.get(categoryKey).get(buttonKey));

				} else {

					selectedValues.put(categoryKey, new HashMap() {
						{
							put(buttonKey, false);
						}
					});
					System.out.println(selectedValues.get(categoryKey).get(buttonKey));

				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}

		});

	}

}
