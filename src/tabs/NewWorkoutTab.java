package tabs;

import static constants.Constants.aestheticGoalsKey;
import static constants.Constants.buildMassKey;
import static constants.Constants.burnFatKey;
import static constants.Constants.currentWeightKey;
import static constants.Constants.gainEnduranceKey;
import static constants.Constants.gainStrengthKey;
import static constants.Constants.goalWeightKey;
import static constants.Constants.naKey;
import static constants.Constants.performanceGoalsKey;
import static constants.Constants.submitKey;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import engines.WorkoutIndexEngine;

public class NewWorkoutTab extends Tab {

	TabItem newWorkoutTab;

	Composite newWorkoutComposite;
	Composite aestheticGoalsComposite;
	Composite performanceGoalsComposite;

	Composite currentWeightComposite;
	Text currentWeightText;
	Composite goalWeightComposite;
	Text goalWeightText;

	CLabel lblInvalidInput;
	Button btnSubmit;
	StringBuilder yourWorkoutIntroBldr; //intro label for yourWorkoutTab

	HashMap<String, HashMap<String, Boolean>> selectedValues = new HashMap<String, HashMap<String, Boolean>>();

	StringBuilder errMsg = new StringBuilder("Error:");

	public NewWorkoutTab(TabFolder tabFolder, YourWorkoutTab tabYourWorkout) {

		newWorkoutTab = new TabItem(tabFolder, SWT.NONE);
		newWorkoutTab.setText("New workout");

		newWorkoutComposite = new Composite(tabFolder, SWT.NONE);
		newWorkoutTab.setControl(newWorkoutComposite);
		newWorkoutComposite.setLayout(null);

		setLabel(newWorkoutComposite,
				"Tell the trainer your fitness goals! Hit submit when you're done to see your personalized workout.",
				88, 10, 820, 25, SWT.CENTER, SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));

		//Aesthetic goals button group
		aestheticGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		aestheticGoalsComposite.setBounds(31, 70, 769, 49);
		setLabel(aestheticGoalsComposite, aestheticGoalsKey, 0, 0, 166, 39, 0,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		setButton(aestheticGoalsComposite, aestheticGoalsKey, buildMassKey, SWT.RADIO, 195, -1, 136, 40,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		setButton(aestheticGoalsComposite, aestheticGoalsKey, burnFatKey, SWT.RADIO, 401, -1, 116, 40,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		setButton(aestheticGoalsComposite, aestheticGoalsKey, naKey, SWT.RADIO, 594, -1, 122, 40,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		//Performance goals button group
		performanceGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		performanceGoalsComposite.setBounds(31, 125, 769, 49);
		setLabel(performanceGoalsComposite, performanceGoalsKey, 0, 0, 178, 39, 0,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		setButton(performanceGoalsComposite, performanceGoalsKey, gainStrengthKey, SWT.RADIO, 195, -1, 135, 40,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		setButton(performanceGoalsComposite, performanceGoalsKey, gainEnduranceKey, SWT.RADIO, 401, -1, 157, 40,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		setButton(performanceGoalsComposite, performanceGoalsKey, naKey, SWT.RADIO, 596, -1, 98, 40,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		//Current weight form
		currentWeightComposite = new Composite(newWorkoutComposite, SWT.NONE);
		currentWeightComposite.setBounds(31, 190, 769, 49);
		setLabel(currentWeightComposite, currentWeightKey, 0, 0, 179, 39, 0,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		currentWeightText = setTextInputField(currentWeightComposite, currentWeightText, SWT.BORDER, 185, 0, 160, 39,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		//Goal weight form
		goalWeightComposite = new Composite(newWorkoutComposite, SWT.NONE);
		goalWeightComposite.setBounds(31, 250, 769, 64);
		setLabel(goalWeightComposite, goalWeightKey, 0, 0, 164, 54, 0,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		goalWeightText = setTextInputField(goalWeightComposite, goalWeightText, SWT.BORDER, 185, 0, 160, 39,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		//Invalid input label
		lblInvalidInput = new CLabel(newWorkoutComposite, SWT.NONE);
		lblInvalidInput.setAlignment(SWT.CENTER);
		lblInvalidInput.setBounds(31, 396, 936, 21);
		lblInvalidInput.setText(errMsg.toString());
		lblInvalidInput.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblInvalidInput.setVisible(false);

		//Submit button
		btnSubmit = new Button(newWorkoutComposite, SWT.NONE);
		btnSubmit.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnSubmit.setBounds(439, 423, 90, 31);
		btnSubmit.setText(submitKey);
		btnSubmit.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
					case SWT.Selection:
						yourWorkoutIntroBldr = new StringBuilder("Based on your goal(s) of: ");
						if(isValidSelections() && isValidWeightData()) {
							lblInvalidInput.setVisible(false); //hide the error message
							yourWorkoutIntroBldr.append("your workout is:");
							//calculate the workout index
							WorkoutIndexEngine workoutIndexEngine = new WorkoutIndexEngine(selectedValues,
									Integer.parseInt(currentWeightText.getText()),
									Integer.parseInt(goalWeightText.getText()));
							tabYourWorkout.setIntroLabelText(yourWorkoutIntroBldr.toString()); //set the intro label in yourWorkout tab
							tabYourWorkout.setWorkout(workoutIndexEngine.getWorkoutIndex()); //send the workout index to the yourWorkout tab
							yourWorkoutIntroBldr.setLength(0); //reset the intro label
							tabFolder.setSelection(1); //navigate to the yourWorkout tab to display the results
						} else {
							lblInvalidInput.setText(errMsg.toString());
							lblInvalidInput.setVisible(true);
							errMsg.setLength(0); //reset error message
							errMsg.append("Error: ");
							yourWorkoutIntroBldr.setLength(0);
						}
				}
			}
		});

	}

	public boolean isValidSelections() {

		boolean inputIsValid = false;
		int validResponseCounter = 0; //less than 2 selections == invalid form
		int naSelectionsCounter = 0; //can only have 1 N/A selection

		boolean selected = false;
		for(String key : this.selectedValues.keySet()) {
			for(String k : selectedValues.get(key).keySet()) {
				selected = selectedValues.get(key).get(k);
				switch (k) {
					case buildMassKey:
						if(selected) {
							validResponseCounter++;
							yourWorkoutIntroBldr.append(buildMassKey + " - ");
							break;
						}
					case burnFatKey:
						if(selected) {
							validResponseCounter++;
							yourWorkoutIntroBldr.append(burnFatKey + " - ");
							break;
						}
					case gainStrengthKey:
						if(selected) {
							validResponseCounter++;
							yourWorkoutIntroBldr.append(gainStrengthKey + " - ");
							break;
						}
					case gainEnduranceKey:
						if(selected) {
							validResponseCounter++;
							yourWorkoutIntroBldr.append(gainEnduranceKey + " - ");
							break;
						}
					case naKey:
						if(selected) {
							validResponseCounter++;
							naSelectionsCounter++;
							break;
						}
					default:
						return false;
				}
			}
		}

		if(validResponseCounter == 2 && naSelectionsCounter <= 1) inputIsValid = true;
		else this.errMsg.append(" Only 1 NA selection is allowed. All responses required.");

		return inputIsValid;

	}

	public boolean isValidWeightData() {

		boolean weightIsValid = false;
		boolean goalIsValid = false;
		try {
			int currentWeight = Integer.parseInt(currentWeightText.getText());
			int goalWeight = Integer.parseInt(goalWeightText.getText());
			weightIsValid = true;
			int difference = goalWeight - currentWeight;

			//lose weight and build mass is invalid; vice versa as well.
			if(selectedValues.get(aestheticGoalsKey).get(buildMassKey) != null) {
				if(selectedValues.get(aestheticGoalsKey).get(buildMassKey) && (difference < 0)) {
					goalIsValid = false;
					this.errMsg.append(" Build mass/weight loss is contradictory.");
				} else goalIsValid = true;

			} else if(selectedValues.get(aestheticGoalsKey).get(burnFatKey) != null) {
				if(selectedValues.get(aestheticGoalsKey).get(burnFatKey) && (difference > 0)) {
					goalIsValid = false;
					this.errMsg.append(" Burn fat/weight gain is contradictory.");
				} else goalIsValid = true;

			} else goalIsValid = true;

		} catch(NumberFormatException ex) {
			weightIsValid = false;
			this.errMsg.append(" Weight must be digits only, rounded to the nearest pound.");
		}

		return goalIsValid && weightIsValid;

	}

	@Override
	public void setButton(Composite composite, String categoryKey, String buttonKey, int buttonType, int boundX,
			int boundY, int boundW, int boundH, Font font) {

		Button button = new Button(composite, buttonType);
		button.setBounds(boundX, boundY, boundW, boundH);
		button.setText(buttonKey);
		button.setFont(font);

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
				} else {
					selectedValues.put(categoryKey, new HashMap() {
						{
							put(buttonKey, false);
						}
					});
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}

		});

	}

}
