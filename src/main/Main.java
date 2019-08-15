package main;

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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import swing2swt.layout.BorderLayout;

public class Main {

	HashMap<String, HashMap<String, Boolean>> selectedValues = new HashMap<String, HashMap<String, Boolean>>();

	protected Shell shlVirtualTrainer;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlVirtualTrainer.open();
		shlVirtualTrainer.layout();
		while(!shlVirtualTrainer.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlVirtualTrainer = new Shell();
		shlVirtualTrainer.setSize(814, 621);
		shlVirtualTrainer.setText("Virtual Trainer");
		shlVirtualTrainer.setLayout(new BorderLayout(0, 0));

		Label introLabel = new Label(shlVirtualTrainer, SWT.NONE);
		introLabel.setAlignment(SWT.CENTER);
		introLabel.setLayoutData(BorderLayout.NORTH);
		introLabel.setText("Welcome to Virtual Trainer! Please select an option below.");

		TabFolder tabFolder = new TabFolder(shlVirtualTrainer, SWT.NONE);
		tabFolder.setLayoutData(BorderLayout.CENTER);

		TabItem homeTab = new TabItem(tabFolder, SWT.NONE);
		homeTab.setText("Home");

		TabItem newWorkoutTab = new TabItem(tabFolder, SWT.NONE);
		newWorkoutTab.setText("New workout");

		// NewWorkoutTab newWorkoutTab = new NewWorkoutTab(tabFolder);

		Composite newWorkoutComposite = new Composite(tabFolder, SWT.NONE);
		newWorkoutTab.setControl(newWorkoutComposite);
		newWorkoutComposite.setLayout(null);

		Label newWorkoutIntroText = new Label(newWorkoutComposite, SWT.NONE);
		newWorkoutIntroText.setAlignment(SWT.CENTER);
		newWorkoutIntroText.setBounds(31, 10, 720, 15);
		newWorkoutIntroText.setText(
				"Tell the trainer your fitness goals! Hit submit when you're done to see your personalized workout.");

		Composite workoutDurationComposite = new Composite(newWorkoutComposite, SWT.NONE);
		workoutDurationComposite.setBounds(31, 114, 346, 16);

		Label lblWorkoutDuration = new Label(workoutDurationComposite, SWT.NONE);
		lblWorkoutDuration.setBounds(0, 1, 111, 15);
		lblWorkoutDuration.setText(workoutDurationKey);

		Button btnHalfHr = new Button(workoutDurationComposite, SWT.RADIO);
		btnHalfHr.setBounds(117, 0, 45, 16);
		btnHalfHr.setText(halfHrKey);

		btnHalfHr.addSelectionListener(new SelectionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
			public void widgetSelected(SelectionEvent event) {
				if(btnHalfHr.getSelection()) {
					selectedValues.put(workoutDurationKey, new HashMap() {
						{
							put(halfHrKey, true);
						}
					});
					System.out.println(selectedValues.get(workoutDurationKey).get(halfHrKey));
				} else {
					selectedValues.put(workoutDurationKey, new HashMap() {
						{
							put(halfHrKey, false);
						}
					});
					System.out.println(selectedValues.get(workoutDurationKey).get(halfHrKey));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}

		});

		Button btnHr = new Button(workoutDurationComposite, SWT.RADIO);
		btnHr.setBounds(168, 0, 42, 16);
		btnHr.setText(hrKey);

		Button btnHrAndHalf = new Button(workoutDurationComposite, SWT.RADIO);
		btnHrAndHalf.setBounds(216, 0, 55, 16);
		btnHrAndHalf.setText(hrAndHalfKey);

		Button btnTwoHrs = new Button(workoutDurationComposite, SWT.RADIO);
		btnTwoHrs.setBounds(279, 0, 90, 16);
		btnTwoHrs.setText(twoHrKey);

		Composite aestheticGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		aestheticGoalsComposite.setBounds(31, 70, 375, 16);

		Label aestheticGoalsLabel = new Label(aestheticGoalsComposite, SWT.NONE);
		aestheticGoalsLabel.setBounds(0, 0, 93, 15);
		aestheticGoalsLabel.setText(aestheticGoalsKey);

		Button btnBuildMuscle = new Button(aestheticGoalsComposite, SWT.RADIO);
		btnBuildMuscle.setBounds(108, -1, 93, 16);
		btnBuildMuscle.setText(buildMassKey);

		Button btnBurnFat = new Button(aestheticGoalsComposite, SWT.RADIO);
		btnBurnFat.setBounds(207, -1, 69, 16);
		btnBurnFat.setText(burnFatKey);

		Button btnNa = new Button(aestheticGoalsComposite, SWT.RADIO);
		btnNa.setLocation(282, -1);
		btnNa.setSize(83, 16);
		btnNa.setText(naKey);

		Composite performanceGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		performanceGoalsComposite.setBounds(31, 92, 391, 16);

		Label lblPerformanceGoals = new Label(performanceGoalsComposite, SWT.NONE);
		lblPerformanceGoals.setBounds(0, 0, 102, 15);
		lblPerformanceGoals.setText(performanceGoalsKey);

		Button btnGainStrength = new Button(performanceGoalsComposite, SWT.RADIO);
		btnGainStrength.setBounds(108, -1, 92, 16);
		btnGainStrength.setText(gainStrengthKey);

		Button btnGainEndurance = new Button(performanceGoalsComposite, SWT.RADIO);
		btnGainEndurance.setBounds(206, -1, 109, 16);
		btnGainEndurance.setText(gainEnduranceKey);

		Button btnNa_1 = new Button(performanceGoalsComposite, SWT.RADIO);
		btnNa_1.setLocation(321, -1);
		btnNa_1.setSize(59, 16);
		btnNa_1.setText("N/A");

	}
}
