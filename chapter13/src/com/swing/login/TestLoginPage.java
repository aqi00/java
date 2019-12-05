package com.swing.login;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

//登录界面
public class TestLoginPage {

	public static void main(String[] args) {
		JFrame frame = new JFrame("登录窗口"); // 创建一个窗口对象
		frame.setSize(400, 200); // 必须设置宽高，否则没有窗体
		frame.setLocationRelativeTo(null);// 将窗口居中。若无该方法，窗口将位于屏幕左上角
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置默认的关闭操作：退出程序
		frame.setResizable(false); // 禁止调整窗口大小

		JPanel panel = new JPanel(); // 创建主面板
		GridLayout layout = new GridLayout(3, 1); // 创建一个网格布局，有三行一列
		panel.setLayout(layout); // 指定面板采用网格布局

		Font font = new Font("中号", Font.PLAIN, 20);
		JPanel userPanel = new JPanel(); // 创建用户名面板
		JLabel userLabel = new JLabel("用户名"); // 创建一个标签
		userLabel.setPreferredSize(new Dimension(100, 50)); // 设置标签的推荐宽高
		userLabel.setFont(font); // 设置标签文字的字体与大小
		userPanel.add(userLabel); // 在面板上添加标签
		JTextField userField = new JTextField(); // 创建一个单行输入框
		userField.setFont(font); // 设置输入框的文本字体及其大小
		userField.setPreferredSize(new Dimension(200, 30)); // 设置输入框的推荐宽高
		userPanel.add(userField); // 在面板上添加单行输入框
		panel.add(userPanel); // 在主面板上添加用户名面板

		JPanel passwordPanel = new JPanel(); // 创建密码面板
		JLabel passwordLabel = new JLabel("密码"); // 创建一个标签
		passwordLabel.setPreferredSize(new Dimension(100, 50)); // 设置标签的推荐宽高
		passwordLabel.setFont(font); // 设置标签文字的字体与大小
		passwordPanel.add(passwordLabel); // 在面板上添加标签
		JPasswordField passwordField = new JPasswordField(); // 创建一个密码输入框
		passwordField.setFont(font); // 设置密码框的文本字体及其大小
		passwordField.setPreferredSize(new Dimension(200, 30)); // 设置密码框的推荐宽高
		passwordPanel.add(passwordField); // 在面板上添加单行输入框
		panel.add(passwordPanel); // 在主面板上添加密码面板

		UIManager.put("Button.font", new FontUIResource(font)); // 设置对话框内部按钮的展示效果
		UIManager.put("Label.font", new FontUIResource(font)); // 设置对话框内部标签的展示效果
		
		JPanel buttonPanel = new JPanel(); // 创建按钮面板
		JButton button = new JButton("登录"); // 创建一个按钮
		button.setFont(font); // 设置按钮文字的字体与大小
		button.setPreferredSize(new Dimension(300, 35)); // 设置按钮的推荐宽高
		button.addActionListener(new ActionListener() { // 给按钮注册一个单击监听器
			@Override
			public void actionPerformed(ActionEvent e) { // 发生了单击事件
				String username = userField.getText(); // 获取单行输入框的用户名文本
				String password = passwordField.getText(); // 获取密码输入框的密码文本
				// 假定正确的用户名是admin，正确的密码是123
				if (!username.equals("admin") || !password.equals("123")) { // 用户名错误或密码错误
					// 用户名或者密码错误，弹出登录失败的提示窗
					JOptionPane.showMessageDialog(frame, "请输入正确的用户名和密码", 
							"登录失败", JOptionPane.ERROR_MESSAGE);
				} else { // 用户名与密码都正确
					frame.dispose(); // 关闭窗口
					MainWindow.show(); // 显示程序的主界面
				}
			}
		});
		buttonPanel.add(button); // 在面板上添加按钮
		panel.add(buttonPanel); // 在主面板上添加按钮面板
		
		frame.add(panel); // 在窗口上添加面板

		frame.setVisible(true); // 必须设置为true，否则看不见
	}

}
