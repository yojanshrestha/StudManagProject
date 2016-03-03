package com.javastud.studmproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.javastud.studmproj.doa.UserDoa;
import com.javastud.studmproj.model.User;

import javax.swing.JCheckBox;

public class LoginScreenLayout extends JFrame {

	private JPanel contentPane;
	private JPanel topPanel;
	private JPanel leftPanel;
	private JPanel bottomPanel;
	private JPanel rightPanel;
	private JPanel centerPanel;
	private JLabel usernameLbl;
	private JLabel passwordLbl;
	private JPanel panel;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JLabel statusLbl;
	private JButton signInBtn;
	private JButton cancelBtn;
	private JButton btnRegister;
	private JCheckBox chckbxRememberMe;
	Preferences prefs = Preferences.userNodeForPackage(getClass());
	static boolean skip = false;
	public static String prefUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					LoginScreenLayout loginWindow = new LoginScreenLayout();
					WindowManager.ui.put("LoginScreenLayout", loginWindow);
					loginWindow.setVisible(true);
					loginWindow.setResizable(false);
					if (skip == true) {
						staticSwitchToStudMangScreen();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void staticSwitchToStudMangScreen() {
		StudentManagement studMngWindow = new StudentManagement(prefUser);
		WindowManager.ui.put("StudentManagement", studMngWindow);
		studMngWindow.setVisible(true);
		LoginScreenLayout loginWindow = (LoginScreenLayout) WindowManager.ui
				.get("LoginScreenLayout");
		loginWindow.setVisible(false);

	}

	/**
	 * Create the frame.
	 */
	public LoginScreenLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTopPanel(), BorderLayout.NORTH);
		contentPane.add(getLeftPanel(), BorderLayout.WEST);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);
		contentPane.add(getRightPanel(), BorderLayout.EAST);
		contentPane.add(getCenterPanel(), BorderLayout.CENTER);
		prefUser = prefs.get("userName", "$$$$$$$$$$");
		if (prefUser.equals("$$$$$$$$$$") == false) {
			skip = true;
			WindowManager.ui.put("pref", prefs);
		}
	}

	private JPanel getTopPanel() {
		if (topPanel == null) {
			topPanel = new JPanel();
			topPanel.setBackground(SystemColor.activeCaption);
		}
		return topPanel;
	}

	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setBackground(SystemColor.activeCaption);
		}
		return leftPanel;
	}

	private JPanel getBottomPanel() {
		if (bottomPanel == null) {
			bottomPanel = new JPanel();
			bottomPanel.setBackground(SystemColor.activeCaption);
		}
		return bottomPanel;
	}

	private JPanel getRightPanel() {
		if (rightPanel == null) {
			rightPanel = new JPanel();
			rightPanel.setBackground(SystemColor.activeCaption);
		}
		return rightPanel;
	}

	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setBackground(new Color(153, 255, 153));
			centerPanel.setLayout(null);
			centerPanel.add(getPanel());
			centerPanel.add(getStatusLbl());
			centerPanel.add(getSignInBtn());
			centerPanel.add(getCancelBtn());
			centerPanel.add(getBtnRegister());
			centerPanel.add(getChckbxRememberMe());
		}
		return centerPanel;
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

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Login",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(28, 26, 350, 98);
			panel.setLayout(null);
			panel.add(getUsernameLbl());
			panel.add(getPasswordLbl());
			panel.add(getUsernameTxt());
			panel.add(getPasswordTxt());
		}
		return panel;
	}

	private JTextField getUsernameTxt() {
		if (usernameTxt == null) {
			usernameTxt = new JTextField();
			usernameTxt.setBounds(135, 26, 147, 20);
			usernameTxt.setColumns(10);
		}
		return usernameTxt;
	}

	private JPasswordField getPasswordTxt() {
		if (passwordTxt == null) {
			passwordTxt = new JPasswordField();
			passwordTxt.setBounds(135, 67, 147, 20);
			passwordTxt.setColumns(10);
		}
		return passwordTxt;
	}

	private JLabel getStatusLbl() {
		if (statusLbl == null) {
			statusLbl = new JLabel("Status: ");
			statusLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
			statusLbl.setForeground(Color.RED);
			statusLbl.setBounds(59, 166, 304, 14);
		}
		return statusLbl;
	}

	private JButton getSignInBtn() {
		if (signInBtn == null) {
			signInBtn = new JButton("Sign In");
			signInBtn.setHorizontalAlignment(SwingConstants.LEFT);
			signInBtn.setIcon(new ImageIcon("resource\\signin.png"));
			signInBtn.setBounds(46, 197, 98, 23);

			signInBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					User user = new User();
					user.setUsername(getUsernameTxt().getText()); // user.setUsername(usernameTxt.getText());
					user.setPassword(new String(getPasswordTxt().getPassword()));

					UserDoa userDoa;

					try {
						userDoa = new UserDoa();

						boolean valid = userDoa.validate(user);

						if (valid) {
							if (chckbxRememberMe.isSelected() == true) {

								prefs.put("userName", usernameTxt.getText());
								prefs.put("password",
										new String(passwordTxt.getPassword()));
								WindowManager.ui.put("pref", prefs);
							}

							switchToStudMangScreen();
						} else {
							statusLbl
									.setText("Status: Either Username or Password Invalid!!!");
						}

					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

				}
			});

		}
		return signInBtn;
	}

	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = new JButton("Cancel");
			cancelBtn.setHorizontalAlignment(SwingConstants.LEFT);
			cancelBtn.setIcon(new ImageIcon("resource\\cancel.png"));
			cancelBtn.setBounds(154, 197, 100, 23);

			cancelBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					WindowManager.ui.clear();
					System.exit(0);
				}
			});

		}

		return cancelBtn;
	}

	private void switchToStudMangScreen() {
		StudentManagement studMngWindow = new StudentManagement(
				usernameTxt.getText());
		WindowManager.ui.put("StudentManagement", studMngWindow);

		studMngWindow.setVisible(true);

		LoginScreenLayout loginWindow = (LoginScreenLayout) WindowManager.ui
				.get("LoginScreenLayout");

		loginWindow.clearData();
		loginWindow.setVisible(false);
		// }

	}

	private void clearData() {
		passwordTxt.setText("");
		statusLbl.setText("Status: ");
		chckbxRememberMe.setSelected(false);
	}

	private JButton getBtnRegister() {
		if (btnRegister == null) {
			btnRegister = new JButton("New User");
			btnRegister.setHorizontalAlignment(SwingConstants.LEFT);
			btnRegister.setIcon(new ImageIcon("resource\\new _user.png"));
			btnRegister.setBounds(263, 197, 115, 23);
			btnRegister.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					switchToUserManagement();

				}
			});
		}
		return btnRegister;
	}

	private void switchToUserManagement() {
		LoginScreenLayout loginScreen = (LoginScreenLayout) WindowManager.ui
				.get("LoginScreenLayout");
		loginScreen.setVisible(false);

		UserManagement userManagement = new UserManagement();
		WindowManager.ui.put("UserManagement", userManagement);
		userManagement.setVisible(true);

	}

	private JCheckBox getChckbxRememberMe() {
		if (chckbxRememberMe == null) {
			chckbxRememberMe = new JCheckBox("Remember Me");
			chckbxRememberMe.setBackground(new Color(153, 255, 153));
			chckbxRememberMe.setBounds(64, 131, 133, 23);
		}
		return chckbxRememberMe;
	}
}
