package tabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class Tab {

	public void setLabel(Composite composite, String labelText, int boundX, int boundY, int boundW, int boundH, int alignment, Font font) {
		CLabel label = new CLabel(composite, SWT.NONE);
		if(alignment != 0) label.setAlignment(alignment);
		label.setBounds(boundX, boundY, boundW, boundH);
		label.setText(labelText);
		label.setFont(font);
	}

	/*
	 * Creates a button and add it to a composite.
	 * 
	 * @param composite the grouping to place the button in
	 * 
	 * @param categoryKey the name of the grouping for reference in a hashmap
	 * 
	 * @param buttonKey the name of the button for reference in a hashmap
	 */
	public void setButton(Composite composite, String categoryKey, String buttonKey, int buttonType, int boundX, int boundY, int boundW, int boundH, Font font) {
		Button button = new Button(composite, buttonType);
		button.setBounds(boundX, boundY, boundW, boundH);
		button.setText(buttonKey);
		button.setFont(font);
	}

	public void setTextInputField(Composite composite, Text text, int border, int boundX, int boundY, int boundW, int boundH, Font font) {
		text = new Text(composite, border);
		text.setFont(font);
		text.setBounds(boundX, boundY, boundW, boundH);
	}

}
