package main;

import static constants.Constants.aestheticGoalsKey;
import static constants.Constants.buildMassKey;
import static constants.Constants.burnFatKey;
import static constants.Constants.gainEnduranceKey;
import static constants.Constants.gainStrengthKey;
import static constants.Constants.naKey;
import static constants.Constants.performanceGoalsKey;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;

public class Main {

	HashMap<String, HashMap<String, Boolean>> selectedValues = new HashMap<String, HashMap<String, Boolean>>();

	protected Shell shlVirtualTrainer;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text currentWeightText;
	private Text goalWeightText;

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
		shlVirtualTrainer.setSize(1023, 739);
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

		//NewWorkoutTab newWorkoutTab = new NewWorkoutTab(tabFolder);

		TabItem newWorkoutTab = new TabItem(tabFolder, SWT.NONE);
		newWorkoutTab.setText("New workout");

		Composite newWorkoutComposite = new Composite(tabFolder, SWT.NONE);
		newWorkoutTab.setControl(newWorkoutComposite);
		newWorkoutComposite.setLayout(null);

		Label newWorkoutIntroText = new Label(newWorkoutComposite, SWT.NONE);
		newWorkoutIntroText.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		newWorkoutIntroText.setAlignment(SWT.CENTER);
		newWorkoutIntroText.setBounds(88, 10, 820, 25);
		newWorkoutIntroText.setText("Tell the trainer your fitness goals! Hit submit when you're done to see your personalized workout.");

		Composite aestheticGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		aestheticGoalsComposite.setBounds(31, 70, 769, 49);

		CLabel aestheticGoalsLabel = new CLabel(aestheticGoalsComposite, SWT.NONE);
		aestheticGoalsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		aestheticGoalsLabel.setBounds(0, 0, 166, 39);
		aestheticGoalsLabel.setText(aestheticGoalsKey);

		Button btnBuildMuscle = new Button(aestheticGoalsComposite, SWT.RADIO);
		btnBuildMuscle.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnBuildMuscle.setBounds(195, -1, 136, 40);
		btnBuildMuscle.setText(buildMassKey);

		Button btnBurnFat = new Button(aestheticGoalsComposite, SWT.RADIO);
		btnBurnFat.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnBurnFat.setBounds(401, -1, 116, 40);
		btnBurnFat.setText(burnFatKey);

		Button btnNa = new Button(aestheticGoalsComposite, SWT.RADIO);
		btnNa.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnNa.setLocation(594, -1);
		btnNa.setSize(122, 40);
		btnNa.setText(naKey);

		Composite performanceGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		performanceGoalsComposite.setBounds(31, 125, 769, 49);

		Button btnGainStrength = new Button(performanceGoalsComposite, SWT.RADIO);
		btnGainStrength.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnGainStrength.setBounds(195, -1, 135, 40);
		btnGainStrength.setText(gainStrengthKey);

		Button btnGainEndurance = new Button(performanceGoalsComposite, SWT.RADIO);
		btnGainEndurance.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnGainEndurance.setBounds(401, -1, 157, 40);
		btnGainEndurance.setText(gainEnduranceKey);

		Button btnNa_1 = new Button(performanceGoalsComposite, SWT.RADIO);
		btnNa_1.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnNa_1.setLocation(596, -1);
		btnNa_1.setSize(98, 40);
		btnNa_1.setText(naKey);
		//
		CLabel lblPerformanceGoals = new CLabel(performanceGoalsComposite, SWT.NONE);
		lblPerformanceGoals.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblPerformanceGoals.setBounds(0, 0, 178, 39);
		lblPerformanceGoals.setText(performanceGoalsKey);

		Composite currentWeightcomposite = new Composite(newWorkoutComposite, SWT.NONE);
		currentWeightcomposite.setBounds(31, 190, 769, 49);
		//formToolkit.adapt(currentWeightcomposite);
		//formToolkit.paintBordersFor(currentWeightcomposite);

		CLabel lblCurrentWeight = new CLabel(currentWeightcomposite, SWT.NONE);
		lblCurrentWeight.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblCurrentWeight.setBounds(0, 0, 179, 39);
		//formToolkit.adapt(lblCurrentWeight);
		//formToolkit.paintBordersFor(lblCurrentWeight);
		lblCurrentWeight.setText("Current weight:");

		currentWeightText = new Text(currentWeightcomposite, SWT.BORDER);
		currentWeightText.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		currentWeightText.setBounds(185, 0, 160, 39);
		currentWeightText.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				boolean test = true;
				final String text = e.text;
				try {
					int i = Integer.parseInt(text);
					System.out.println(test + text);
				} catch(NumberFormatException ex) {
					if(!text.equals("")) test = false;
					System.out.println(test + text);
				}

			}
		});
		//
		//		//currentWeightText.getText();
		//
		Composite goalWeightComposite = new Composite(newWorkoutComposite, SWT.NONE);
		goalWeightComposite.setBounds(31, 250, 769, 64);
		//		//formToolkit.adapt(goalWeightComposite);
		//		//formToolkit.paintBordersFor(goalWeightComposite);
		//
		CLabel lblGoalWeight = new CLabel(goalWeightComposite, SWT.NONE);
		lblGoalWeight.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblGoalWeight.setBounds(0, 0, 164, 54);
		//		//formToolkit.adapt(lblGoalWeight);
		//		//formToolkit.paintBordersFor(lblGoalWeight);
		lblGoalWeight.setText("Goal weight:");
		//
		goalWeightText = new Text(goalWeightComposite, SWT.BORDER);
		goalWeightText.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		goalWeightText.setBounds(185, 0, 160, 39);

		Button btnSubmit = new Button(newWorkoutComposite, SWT.NONE);
		btnSubmit.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		btnSubmit.setBounds(439, 423, 90, 31);
		formToolkit.adapt(btnSubmit, true, true);
		btnSubmit.setText("Submit");

		CLabel lblInvalidInput = new CLabel(newWorkoutComposite, SWT.NONE);
		lblInvalidInput.setAlignment(SWT.CENTER);
		lblInvalidInput.setBounds(385, 396, 189, 21);
		formToolkit.adapt(lblInvalidInput);
		formToolkit.paintBordersFor(lblInvalidInput);
		lblInvalidInput.setText("Invalid input!");
		lblInvalidInput.setVisible(false);

		btnSubmit.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
					case SWT.Selection:
						try {
							int i = Integer.parseInt(currentWeightText.getText());
							lblInvalidInput.setVisible(false);
						} catch(NumberFormatException ex) {
							lblInvalidInput.setVisible(true);
						}
				}
			}
		});

		//formToolkit.adapt(goalWeightText, true, true);

	}
}
