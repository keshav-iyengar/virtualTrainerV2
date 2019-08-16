package tabs;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public interface Tab {

	public void setLabel(Composite composite, String labelText, int boundX, int boundY, int boundW, int boundH, int alignment, Font font);

	/*
	 * Creates a button and add it to a composite.
	 * 
	 * @param composite the grouping to place the button in
	 * 
	 * @param categoryKey the name of the grouping for reference in a hashmap
	 * 
	 * @param buttonKey the name of the button for reference in a hashmap
	 */
	public void setButton(Composite composite, String categoryKey, String buttonKey, int buttonType, int boundX, int boundY, int boundW, int boundH, Font font);

	public void setTextInputField(Composite composite, Text text, int border, int boundX, int boundY, int boundW, int boundH, Font font);

}
