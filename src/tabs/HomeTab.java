package tabs;

import static constants.Constants.homeTabHeader;
import static constants.Constants.homeTabIntro;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class HomeTab extends Tab {

	TabItem homeTab;
	Composite homeTabComposite;

	public HomeTab(TabFolder tabFolder) {

		super(tabFolder);

		//homeTab = new TabItem(tabFolder, SWT.NONE);
		homeTab = this.tab;
		homeTab.setText("Home");

		//homeTabComposite = new Composite(tabFolder, SWT.NONE);
		homeTabComposite = this.composite;
		homeTab.setControl(homeTabComposite);
		homeTabComposite.setLayout(null);

		setLabel(homeTabComposite, homeTabIntro, 10, 73, 979, 70, 0,
				SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));

		setLabel(homeTabComposite, homeTabHeader, 268, 10, 471, 51, SWT.CENTER,
				SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
	}

}
