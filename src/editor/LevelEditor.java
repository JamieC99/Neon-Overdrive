package editor;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LevelEditor 
{
	private JFrame editorFrame = new JFrame("Level Editor");
	private JButton button = new JButton("Toggle Edit Mode");
	
	private boolean editMode;
	private boolean showGrid;
	
	public LevelEditor()
	{
		editorFrame.setSize(256, 512);
		editorFrame.setLayout(new FlowLayout());
		
		button.setLocation(0, 0);
		editorFrame.add(button);

		editorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editorFrame.setVisible(true);
		
		buttonFunctions();
	}
	
	private void buttonFunctions()
	{
		button.addActionListener
		(
			e ->
			{
				editMode = !editMode;
			}
		);
	}
}