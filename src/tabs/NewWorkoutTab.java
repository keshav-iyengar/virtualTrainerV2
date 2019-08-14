package tabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class NewWorkoutTab implements Tab {

	TabItem newWorkoutTab;
	Composite composite;

	public NewWorkoutTab(TabFolder tabFolder) {

		newWorkoutTab = new TabItem(tabFolder, SWT.NONE);
		newWorkoutTab.setText("New workout");

	}

	@Override
	public void setLabels(Composite composite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCheckBoxes(Composite composite) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRadioButtons(Composite composite) {
		// TODO Auto-generated method stub

	}

}
