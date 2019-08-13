package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import swing2swt.layout.BorderLayout;

public class Test {

	protected Shell shlVirtualTrainer;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Test window = new Test();
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

		Composite composite = new Composite(tabFolder, SWT.NONE);
		newWorkoutTab.setControl(composite);
		composite.setLayout(null);

		Label newWorkoutIntroText = new Label(composite, SWT.NONE);
		newWorkoutIntroText.setAlignment(SWT.CENTER);
		newWorkoutIntroText.setBounds(31, 10, 720, 15);
		newWorkoutIntroText.setText(
				"Tell the trainer your fitness goals! Hit submit when you're done to see your personalized workout.");

		Label fitnessGoalsLabel = new Label(composite, SWT.NONE);
		fitnessGoalsLabel.setBounds(31, 71, 81, 15);
		fitnessGoalsLabel.setText("Fitness goals:");

		Button btnBuildMuscle = new Button(composite, SWT.CHECK);
		btnBuildMuscle.setBounds(141, 70, 93, 16);
		btnBuildMuscle.setText("Build muscle");

		Button btnBurnFat = new Button(composite, SWT.CHECK);
		btnBurnFat.setBounds(251, 70, 93, 16);
		btnBurnFat.setText("Burn fat");

		Label lblWorkoutDuration = new Label(composite, SWT.NONE);
		lblWorkoutDuration.setBounds(31, 92, 104, 15);
		lblWorkoutDuration.setText("Workout duration:");

		Button btnHalfHr = new Button(composite, SWT.RADIO);
		btnHalfHr.setBounds(141, 92, 58, 16);
		btnHalfHr.setText(".5 hr");

		Button btnHr = new Button(composite, SWT.RADIO);
		btnHr.setBounds(206, 92, 42, 16);
		btnHr.setText("1 hr");

		Button btnHrAndHalf = new Button(composite, SWT.RADIO);
		btnHrAndHalf.setBounds(254, 92, 65, 16);
		btnHrAndHalf.setText("1.5 hrs");

		Button btnTwoHrs = new Button(composite, SWT.RADIO);
		btnTwoHrs.setBounds(325, 92, 90, 16);
		btnTwoHrs.setText("2+ hrs");

	}
}
