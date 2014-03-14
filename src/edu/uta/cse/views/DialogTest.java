package edu.uta.cse.views;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class DialogTest extends JDialog {

	/**
	 * Create the dialog.
	 */
	public DialogTest() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton button = new JButton("{");
		button.setBounds(61, 65, 39, 23);
		getContentPane().add(button);
		
		JButton button_2 = new JButton("(");
		button_2.setBounds(110, 65, 39, 23);
		getContentPane().add(button_2);
		
		JButton btnVoid = new JButton("void");
		btnVoid.setBounds(159, 65, 57, 23);
		getContentPane().add(btnVoid);
		
		JButton btnInt = new JButton("int");
		btnInt.setBounds(226, 65, 51, 23);
		getContentPane().add(btnInt);
		
		JButton button_3 = new JButton(")");
		button_3.setBounds(287, 65, 39, 23);
		getContentPane().add(button_3);
		
		JButton button_1 = new JButton("}");
		button_1.setBounds(336, 65, 39, 23);
		getContentPane().add(button_1);
		
		JLabel lblNewLabel = new JLabel("This Dialog is just for testing.");
		lblNewLabel.setBounds(40, 21, 332, 15);
		getContentPane().add(lblNewLabel);

	}
}
