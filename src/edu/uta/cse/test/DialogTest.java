package edu.uta.cse.test;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class DialogTest extends JDialog {

	JLabel label;
	/**
	 * Create the dialog.
	 */
	public DialogTest() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("This Dialog is just for testing.");
		lblNewLabel.setBounds(40, 21, 332, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblYouSelect = new JLabel("You select:");
		lblYouSelect.setBounds(40, 57, 73, 15);
		getContentPane().add(lblYouSelect);
		
		label = new JLabel("");
		label.setBounds(123, 57, 119, 15);
		getContentPane().add(label);
		
		JLabel lblTheReplacedType = new JLabel("The replaced type may be:");
		lblTheReplacedType.setBounds(40, 101, 163, 15);
		getContentPane().add(lblTheReplacedType);
		
	}
	
	public void setOldType(String oldType){
		label.setText(oldType);
	}
	
	public void setNewTypes(List<String> newTypes){
		for(int i = 0; i < newTypes.size(); i++){
			JLabel labelNewType = new JLabel(newTypes.get(i));
			getContentPane().add(labelNewType);
		}
	}
}
