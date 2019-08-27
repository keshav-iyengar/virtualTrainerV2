package tabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class SavedWorkoutsTab {

	TabItem savedWorkoutsTab;
	Composite savedWorkoutsComposite;

	public SavedWorkoutsTab(TabFolder tabFolder) {

		savedWorkoutsTab = new TabItem(tabFolder, SWT.NONE);
		savedWorkoutsComposite = new Composite(tabFolder, SWT.NONE);
		savedWorkoutsComposite.setLayout(new GridLayout(2, false));

	}

}
