package net.sf.memoranda.ui;

import java.awt.*;

import javax.swing.*;

import net.sf.memoranda.util.Local;

/**
 * Allows user to create, edit, save, delete, and load cutomized view layouts.
 * @author Alexander Callos
 *
 */
public class LayoutViewEditor extends JDialog {
	
	private JPanel myPanel = new JPanel(new BorderLayout());
	private JTabbedPane myTabbedPanel = new JTabbedPane();
	private JPanel layoutViewOverviewTab = new JPanel(new GridBagLayout());
	private GridBagConstraints myGridBagConstraints;
	private JLabel jLabelTop;
	
	protected LayoutViewEditor(Frame someFrame) {
		super(someFrame, Local.getString("Layout Views"));
		
		try {
			windowInit();
		} catch (Exception someException) {
			new ExceptionDialog(someException);
		}
	}
	
	private void windowInit() throws Exception {
		
	}

}