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

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class NewWorkoutTab implements Tab {

	TabItem newWorkoutTab;
	Composite newWorkoutComposite;
	Composite aestheticGoalsComposite;
	Composite performanceGoalsComposite;
	Composite currentWeightComposite;
	Composite goalWeightComposite;

	HashMap<String, HashMap<String, Boolean>> selectedValues = new HashMap<String, HashMap<String, Boolean>>();

	Text currentWeightText;
	Text goalWeightText;

	public NewWorkoutTab(TabFolder tabFolder) {

		newWorkoutTab = new TabItem(tabFolder, SWT.NONE);
		newWorkoutTab.setText("New workout");

		newWorkoutComposite = new Composite(tabFolder, SWT.NONE);
		newWorkoutTab.setControl(newWorkoutComposite);
		newWorkoutComposite.setLayout(null);

		setLabel(newWorkoutComposite, "Tell the trainer your fitness goals! Hit submit when you're done to see your personalized workout.", 88, 10, 820, 25, SWT.CENTER,
				SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));

		//Aesthetic goals button group
		aestheticGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		aestheticGoalsComposite.setBounds(31, 70, 769, 49);
		setLabel(aestheticGoalsComposite, aestheticGoalsKey, 0, 0, 166, 39, 0, SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		setButton(aestheticGoalsComposite, aestheticGoalsKey, buildMassKey, SWT.RADIO, 195, -1, 136, 40, SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		setButton(aestheticGoalsComposite, aestheticGoalsKey, burnFatKey, SWT.RADIO, 401, -1, 116, 40, SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		setButton(aestheticGoalsComposite, aestheticGoalsKey, naKey, SWT.RADIO, 594, -1, 122, 40, SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		//Performance goals button group
		performanceGoalsComposite = new Composite(newWorkoutComposite, SWT.NONE);
		performanceGoalsComposite.setBounds(31, 125, 769, 49);
		setLabel(performanceGoalsComposite, performanceGoalsKey, 0, 0, 178, 39, 0, SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		setButton(performanceGoalsComposite, performanceGoalsKey, gainStrengthKey, SWT.RADIO, 195, -1, 135, 40, SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		setButton(performanceGoalsComposite, performanceGoalsKey, gainEnduranceKey, SWT.RADIO, 401, -1, 157, 40, SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		setButton(performanceGoalsComposite, performanceGoalsKey, naKey, SWT.RADIO, 596, -1, 98, 40, SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		//Current weight form
		currentWeightComposite = new Composite(newWorkoutComposite, SWT.NONE);
		currentWeightComposite.setBounds(31, 190, 769, 49);
		setLabel(currentWeightComposite, currentWeightKey, 0, 0, 179, 39, 0, SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		setTextInputField(currentWeightComposite, currentWeightText, SWT.BORDER, 185, 0, 160, 39, SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

		//Goal weight form
		goalWeightComposite = new Composite(newWorkoutComposite, SWT.NONE);
		goalWeightComposite.setBounds(31, 250, 769, 64);
		setLabel(goalWeightComposite, goalWeightKey, 0, 0, 164, 54, 0, SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		setTextInputField(goalWeightComposite, goalWeightText, SWT.BORDER, 185, 0, 160, 39, SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));

	}

	@Override
	public void setLabel(Composite composite, String labelText, int boundX, int boundY, int boundW, int boundH, int alignment, Font font) {

		CLabel label = new CLabel(composite, SWT.NONE);
		if(alignment != 0) label.setAlignment(alignment);
		label.setBounds(boundX, boundY, boundW, boundH);
		label.setText(labelText);
		label.setFont(font);

	}

	@Override
	public void setButton(Composite composite, String categoryKey, String buttonKey, int buttonType, int boundX, int boundY, int boundW, int boundH, Font font) {

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

	@Override
	public void setTextInputField(Composite composite, Text text, int border, int boundX, int boundY, int boundW, int boundH, Font font) {
		text = new Text(composite, border);
		text.setFont(font);
		text.setBounds(boundX, boundY, boundW, boundH);

	}

}
