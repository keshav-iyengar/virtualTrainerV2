package main;

import static constants.Constants.halfHrKey;
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
		} catch (Exception e) {
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
		while (!shlVirtualTrainer.isDisposed()) {
			if (!display.readAndDispatch()) {
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
		workoutDurationComposite.setBounds(31, 92, 346, 16);
//
		Button btnHalfHr = new Button(workoutDurationComposite, SWT.RADIO);
		btnHalfHr.setLocation(117, 0);
		btnHalfHr.setSize(45, 16);
		btnHalfHr.setText(".5 hr");

		btnHalfHr.addSelectionListener(new SelectionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
			public void widgetSelected(SelectionEvent event) {
				if (btnHalfHr.getSelection()) {
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
					System.out.println(selectedValues.get(workoutDurationKey).get(".5 hr"));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
//
		Button btnHr = new Button(workoutDurationComposite, SWT.RADIO);
		btnHr.setBounds(168, 0, 42, 16);
		btnHr.setText("1 hr");
//
		Button btnHrAndHalf = new Button(workoutDurationComposite, SWT.RADIO);
		btnHrAndHalf.setBounds(216, 0, 55, 16);
		btnHrAndHalf.setText("1.5 hrs");
//
		Button btnTwoHrs = new Button(workoutDurationComposite, SWT.RADIO);
		btnTwoHrs.setBounds(279, 0, 90, 16);
		btnTwoHrs.setText("2+ hrs");
//
		Label lblWorkoutDuration = new Label(workoutDurationComposite, SWT.NONE);
		lblWorkoutDuration.setBounds(0, 1, 104, 15);
		lblWorkoutDuration.setText("Workout duration:");

		Composite fitnessGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		fitnessGoalsComposite.setBounds(31, 70, 313, 16);

		Label fitnessGoalsLabel = new Label(fitnessGoalsComposite, SWT.NONE);
		fitnessGoalsLabel.setBounds(0, 0, 81, 15);
		fitnessGoalsLabel.setText("Fitness goals:");

		Button btnBuildMuscle = new Button(fitnessGoalsComposite, SWT.CHECK);
		btnBuildMuscle.setBounds(108, -1, 93, 16);
		btnBuildMuscle.setText("Build muscle");

		Button btnBurnFat = new Button(fitnessGoalsComposite, SWT.CHECK);
		btnBurnFat.setBounds(207, -1, 93, 16);
		btnBurnFat.setText("Burn fat");

	}
}
