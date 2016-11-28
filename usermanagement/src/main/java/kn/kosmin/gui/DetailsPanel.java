package kn.kosmin.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kn.kosmin.User;
import kn.kosmin.db.DatabaseException;
import kn.kosmin.util.Messages;

public class DetailsPanel extends JPanel implements ActionListener {
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private Color bgColor;
	private User user;
	public DetailsPanel(MainFrame parent){
		this.parent = parent;
		initialize();
		bgColor = this.getBackground();
	}
	private void initialize() {
		this.setName("addPanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
	}
	private JPanel getButtonPanel() {
		if (buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}
	private JButton getCancelButton() {
		if (cancelButton == null){
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("AddPanel.cancel")); //$NON-NLS-1$
			cancelButton.setName("cancelButton"); //$NON-NLS-1$
			cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}
	private JButton getOkButton() {
		if (okButton == null){
			okButton = new JButton();
			okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
			okButton.setName("okButton"); //$NON-NLS-1$
			okButton.setActionCommand("ok"); //$NON-NLS-1$
			okButton.addActionListener(this);
		}
		return okButton;
	}
	private JPanel getFieldPanel() {
		if (fieldPanel == null){
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3, 2));
			addLabeledField(fieldPanel, Messages.getString("AddPanel.first_name"), getFirstNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.last_name"), getLastNameField()); //$NON-NLS-1$
			addLabeledField(fieldPanel, Messages.getString("AddPanel.date_of_birth"), getDateOfBirthField()); //$NON-NLS-1$
		}
		return fieldPanel;
	}
	private JTextField getDateOfBirthField() {
		if (dateOfBirthField == null){
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField"); //$NON-NLS-1$
			
		}
		return dateOfBirthField;
	}
	private JTextField getLastNameField() {
		if (lastNameField == null){
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField"); //$NON-NLS-1$
		}
		return lastNameField;
	}
	private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
		textField.setEditable(false);
		
	}
	private JTextField getFirstNameField() {
		if (firstNameField == null){
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField"); //$NON-NLS-1$
		}
		return firstNameField;
	}
	

	public void actionPerformed(ActionEvent e) {
		if ("ok".equalsIgnoreCase(e.getActionCommand())) {
		this.setVisible(false);
		}
		parent.showBrowsePanel();
	}

	private void clearFields() {
		getFirstNameField().setText("");
		getFirstNameField().setBackground(bgColor);

		getLastNameField().setText("");
		getLastNameField().setBackground(bgColor);

		getDateOfBirthField().setText("");
		getDateOfBirthField().setBackground(bgColor);
	}
	public void setFields(){
		this.user = parent.getSelectedUser();
		firstNameField.setText(user.getFirstName());
		lastNameField.setText(user.getLastName());
		DateFormat formatter = DateFormat.getDateInstance();
		dateOfBirthField.setText(formatter.format(user.getDateOfBirthd()));
	}
}
