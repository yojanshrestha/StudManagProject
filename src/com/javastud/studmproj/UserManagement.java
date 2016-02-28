package com.javastud.studmproj;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sun.nio.ch.WindowsAsynchronousChannelProvider;

import com.javastud.studmproj.doa.UserDoa;
import com.javastud.studmproj.model.User;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class UserManagement extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JLabel rePasswordLbl;
	private JPasswordField rePasswordTxt;
	private JLabel statusLbl;
	private JButton btnSubmit;
	private JButton btnCancel;
	private UserDoa userDoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManagement userManagement = new UserManagement();
					WindowManager.ui.put("UserManagement", userManagement);
					userManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 324);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel());
		contentPane.add(getStatusLbl());
		contentPane.add(getBtnSubmit());
		contentPane.add(getBtnCancel());
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Register",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(43, 36, 350, 149);
			panel.add(getUsernameLbl());
			panel.add(getPasswordLbl());
			panel.add(getUsernameTxt());
			panel.add(getPasswordTxt());
			panel.add(getRePasswordLbl());
			panel.add(getRePasswordTxt());
		}
		return panel;
	}

	private JLabel getUsernameLbl() {
		if (usernameLbl == null) {
			usernameLbl = new JLabel("Username");
			usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			usernameLbl.setBounds(29, 28, 86, 14);
		}
		return usernameLbl;
	}

	private JLabel getPasswordLbl() {
		if (passwordLbl == null) {
			passwordLbl = new JLabel("Password");
			passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			passwordLbl.setBounds(31, 69, 72, 14);
		}
		return passwordLbl;
	}

	private JTextField getUsernameTxt() {
		if (usernameTxt == null) {
			usernameTxt = new JTextField();
			usernameTxt.setColumns(10);
			usernameTxt.setBounds(135, 26, 147, 20);
		}
		return usernameTxt;
	}

	private JPasswordField getPasswordTxt() {
		if (passwordTxt == null) {
			passwordTxt = new JPasswordField();
			passwordTxt.setColumns(10);
			passwordTxt.setBounds(135, 67, 147, 20);
		}
		return passwordTxt;
	}

	private JLabel getRePasswordLbl() {
		if (rePasswordLbl == null) {
			rePasswordLbl = new JLabel("Re-type Password");
			rePasswordLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
			rePasswordLbl.setBounds(10, 111, 122, 14);
		}
		return rePasswordLbl;
	}

	private JPasswordField getRePasswordTxt() {
		if (rePasswordTxt == null) {
			rePasswordTxt = new JPasswordField();
			rePasswordTxt.setBounds(135, 109, 147, 20);
			rePasswordTxt.setColumns(10);
		}
		return rePasswordTxt;
	}

	private JLabel getStatusLbl() {
		if (statusLbl == null) {
			statusLbl = new JLabel("Status:");
			statusLbl.setForeground(Color.RED);
			statusLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
			statusLbl.setBounds(65, 202, 313, 23);
		}
		return statusLbl;
	}

	private JButton getBtnSubmit() {
		if (btnSubmit == null) {
			btnSubmit = new JButton("Submit");
			btnSubmit.setHorizontalAlignment(SwingConstants.LEFT);
			btnSubmit.setIcon(new ImageIcon("resource\\save.png"));
			btnSubmit.setBounds(99, 236, 98, 23);
			btnSubmit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					User user = getUserData();
					if (user == null) {
						statusLbl
								.setText("Status: Password and Re-type Password doesnot match ");
					} else {
						try {
							userDoa = new UserDoa();
							if (userDoa.checkIfExists(user.getUsername()) == false) {								
								userDoa.addUser(user);
								LoginScreenLayout loginScreen = (LoginScreenLayout) WindowManager.ui
										.get("LoginScreenLayout");
								UserManagement userManagement = (UserManagement) WindowManager.ui
										.get("UserManagement");
								userManagement.dispose();
								loginScreen.setVisible(true);
							}else{
								JOptionPane.showMessageDialog(null, "Username already exists!!");
							}

						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}

					}

				}
			});
		}
		return btnSubmit;
	}

	private User getUserData() {
		User user = new User();
		String userName = getUsernameTxt().getText();
		String pass = new String(getPasswordTxt().getPassword());
		user.setUsername(userName);
		if (userName.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(null, "Blank Field is not allowed!!");
			return null;
		} else {
			if (pass.equals(new String(getRePasswordTxt().getPassword()))) {
				user.setPassword(pass);
				return user;
			} else {
				return null;
			}
		}
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setIcon(new ImageIcon("resource\\cancel.png"));
			btnCancel.setBounds(225, 236, 98, 23);
			btnCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					UserManagement userManagement = (UserManagement) WindowManager.ui
							.get("UserManagement");
					userManagement.dispose();
					LoginScreenLayout loginScreen = (LoginScreenLayout) WindowManager.ui
							.get("LoginScreenLayout");
					loginScreen.setVisible(true);
				}
			});
		}
		return btnCancel;
	}
}
