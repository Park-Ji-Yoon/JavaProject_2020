package Main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends JFrame{
	public LoginGUI() {
	}
	public static void main(String args[]) {
		LoginPanel login_panel = new LoginPanel();
	}
}

// 로그인 패널 클래스
class LoginPanel extends JPanel{
	static Icon icon = new ImageIcon("images/back_2.png");
	
	// 배경화면 사진 선언 및 초기화
	ImageIcon background = new ImageIcon("images/background_16.png");

	JButton login_exit_button = new JButton(icon); // 로그인 취소 버튼
	JButton login_manager_button = new JButton(icon); // 매니저 로그인 버튼
	JButton login_master_button = new JButton(icon); // 관리자 로그인 버튼
	JButton login_button = new JButton(icon); // 로그인 버튼

	// LoginPanel 생성자
	LoginPanel() {
		setBounds(0, 0, 1862, 1055); // 위치와 크기 지정
		setLayout(null);
		setVisible(true);

		// 로그인 취소 버튼 속성 설정
		login_exit_button.setBounds(61, 35, 126, 35); // 크기와 위치 설정
		login_exit_button.setVisible(true);
		// 투명하게 만드는 코드
		login_exit_button.setBorderPainted(false);
		login_exit_button.setContentAreaFilled(false);
		login_exit_button.setFocusPainted(false);
		login_exit_button.setOpaque(false);
		// 로그인 취소 버튼 이벤트
		login_exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 지점장 로그인 id, pw field 비우기
            	GLoginPanel.getId_field().setText("");
            	GLoginPanel.getPw_field().setText("");

            	// 관리자 로그인 id, pw field 비우기
            	SLoginPanel.getId_field().setText("");
            	SLoginPanel.getPw_field().setText("");

        		login_button.setVisible(false);
        		
        		// LoginPanel에서 MainPanel로 이동
				Main.MainFrame.getMain_panel().setVisible(true);
				Main.MainFrame.getLogin_panel().setVisible(false);
			}
		});
		add(login_exit_button);
		
		// 매니저 버튼 속성 설정
		login_manager_button.setBounds(640, 309, 291, 95);
		login_manager_button.setVisible(true);
		login_manager_button.setBorderPainted(false);
		login_manager_button.setContentAreaFilled(false);
		login_manager_button.setFocusPainted(false);
		login_manager_button.setOpaque(false);
		// 매니저 버튼 이벤트
		login_manager_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_button.setVisible(false);
				Main.MainFrame.getGLogin_panel().setVisible(true);
				Main.MainFrame.getLogin_panel().setVisible(false);
			}
		});
		add(login_manager_button);
		
		// 마스터 버튼 속성 설정
		login_master_button.setBounds(932, 309, 291, 95);
		login_master_button.setVisible(true);
		login_master_button.setBorderPainted(false);
		login_master_button.setContentAreaFilled(false);
		login_master_button.setFocusPainted(false);
		login_master_button.setOpaque(false);
		// 마스터 버튼 이벤트
		login_master_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_button.setVisible(false);
				Main.MainFrame.getSLogin_panel().setVisible(true);
				Main.MainFrame.getLogin_panel().setVisible(false);
			}
		});
		add(login_master_button);
		
		// 로그인 버튼 속성 설정
		login_button.setBounds(639, 754, 585, 83);
		login_button.setVisible(true);
		login_button.setBorderPainted(false);
		login_button.setContentAreaFilled(false);
		login_button.setFocusPainted(false);
		login_button.setOpaque(false);
		// 로그인 버튼 이벤트
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "지점장, 관리자 중 하나를 선택해주십시오.", "Authority Error", JOptionPane.ERROR_MESSAGE);	
			}
		});
		add(login_button);		
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
}

// 지점장 로그인 패널 클래스
class GLoginPanel extends JPanel{
	static Icon icon = new ImageIcon("images/back_2.png");
	
	ImageIcon background = new ImageIcon("images/background_17.png");

	// 컴포넌트 선언 및 초기화
	JButton login_exit_button = new JButton(icon);
	JButton login_master_button = new JButton(icon);
	JButton login_button = new JButton(icon);
	static JTextField id_field = new JTextField();
	static JPasswordField pw_field = new JPasswordField();

	// GLoginPanel생성자
	GLoginPanel() {
		setBounds(0, 0, 1862, 1055); // 위치와 크기 지정
		setLayout(null);
		setVisible(true);

		// 로그인 취소 버튼 속성 설정
		login_exit_button.setBounds(61, 35, 126, 35);
		login_exit_button.setVisible(true);
		login_exit_button.setBorderPainted(false);
		login_exit_button.setContentAreaFilled(false);
		login_exit_button.setFocusPainted(false);
		login_exit_button.setOpaque(false);
		// 로그인 취소 버튼 이벤트
		login_exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	GLoginPanel.getId_field().setText("");
            	GLoginPanel.getPw_field().setText("");

            	SLoginPanel.getId_field().setText("");
            	SLoginPanel.getPw_field().setText("");
            	
				Main.MainFrame.getMain_panel().setVisible(true);
				Main.MainFrame.getGLogin_panel().setVisible(false);
			}
		});
		add(login_exit_button);
		
		// 마스터 버튼 속성 설정
		login_master_button.setBounds(932, 309, 291, 95);
		login_master_button.setVisible(true);
		login_master_button.setBorderPainted(false);
		login_master_button.setContentAreaFilled(false);
		login_master_button.setFocusPainted(false);
		login_master_button.setOpaque(false);
		// 마스터 버튼 이벤트
		login_master_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	GLoginPanel.getId_field().setText("");
            	GLoginPanel.getPw_field().setText("");
            	
				Main.MainFrame.getSLogin_panel().setVisible(true);
				Main.MainFrame.getGLogin_panel().setVisible(false);
			}
		});
		add(login_master_button);
		
		//로그인 버튼 속성 설정
		login_button.setBounds(639, 754, 585, 83);
		login_button.setVisible(true);
		login_button.setBorderPainted(false);
		login_button.setContentAreaFilled(false);
		login_button.setFocusPainted(false);
		login_button.setOpaque(false);
		// 로그인 버튼 이벤트
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManagerLogin();
			}
		});
		add(login_button);
		
		// 아이디 텍스트필드 속성 설정
		id_field.setBounds(705, 460, 450, 81);
		id_field.setVisible(true);
		id_field.setOpaque(false);
		id_field.setBorder(BorderFactory.createEmptyBorder());
		id_field.setFont(new Font("인터파크고딕 M", Font.PLAIN, 45));
		// 아이디 텍스트필드 key event
		id_field.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					// id, pw field 중 하나라도 비어 있으면
					if(id_field.getText().equals("") || pw_field.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 입력해주십시오.", "Error", JOptionPane.ERROR_MESSAGE);	
					}else { // id, pw field 둘 다 채워져 있으면
						new ManagerLogin();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		add(id_field);

		// 비밀번호 텍스트필드
		pw_field.setBounds(705, 600, 450, 81);
		pw_field.setVisible(true);
		pw_field.setOpaque(false);
		pw_field.setBorder(BorderFactory.createEmptyBorder());
		pw_field.setFont(new Font("서울남산 장체 B", Font.BOLD, 45));
		// 비밀번호 텍스트필드 key event
		pw_field.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(id_field.getText().equals("") || pw_field.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 입력해주십시오.", "Error", JOptionPane.ERROR_MESSAGE);	
					}else {
						new ManagerLogin();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		add(pw_field);
	}
	
	// id, pw 텍스트필드 getter메서드
	public static JTextField getId_field() {
		return id_field;
	}
	public static JPasswordField getPw_field() {
		return pw_field;
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
}

// 관리자 로그인 패널 클래스
class SLoginPanel extends JPanel{
	static Icon icon = new ImageIcon("images/back_2.png");
	
	ImageIcon background = new ImageIcon("images/background_18.png");

	JButton login_exit_button = new JButton(icon);
	JButton login_manager_button = new JButton(icon);
	JButton login_button = new JButton(icon);
	static JTextField id_field = new JTextField();
	static JPasswordField pw_field = new JPasswordField();

	SLoginPanel() {
		setBounds(0, 0, 1862, 1055); // 위치와 크기 지정
		setLayout(null);
		setVisible(true);

		// 로그인 취소 버튼 속성 설정
		login_exit_button.setBounds(61, 35, 126, 35);
		login_exit_button.setVisible(true);
		login_exit_button.setBorderPainted(false);
		login_exit_button.setContentAreaFilled(false);
		login_exit_button.setFocusPainted(false);
		login_exit_button.setOpaque(false);
		// 로그인 취소 버튼 이벤트
		login_exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	GLoginPanel.getId_field().setText("");
            	GLoginPanel.getPw_field().setText("");

            	SLoginPanel.getId_field().setText("");
            	SLoginPanel.getPw_field().setText("");
            	
				Main.MainFrame.getMain_panel().setVisible(true);
				Main.MainFrame.getSLogin_panel().setVisible(false);
			}
		});
		add(login_exit_button);
		
		// 매니저 버튼 버튼 속성 설정
		login_manager_button.setBounds(640, 309, 291, 95);
		login_manager_button.setVisible(true);
		login_manager_button.setBorderPainted(false);
		login_manager_button.setContentAreaFilled(false);
		login_manager_button.setFocusPainted(false);
		login_manager_button.setOpaque(false);
		// 매니저 버튼 버튼 이벤트
		login_manager_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	SLoginPanel.getId_field().setText("");
            	SLoginPanel.getPw_field().setText("");
            	
				Main.MainFrame.getGLogin_panel().setVisible(true);
				Main.MainFrame.getSLogin_panel().setVisible(false);
			}
		});
		add(login_manager_button);
		
		// 로그인 버튼 속성 설정
		login_button.setBounds(639, 754, 585, 83);
		login_button.setVisible(true);
		login_button.setBorderPainted(false);
		login_button.setContentAreaFilled(false);
		login_button.setFocusPainted(false);
		login_button.setOpaque(false);
		// 로그인 버튼 이벤트
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MasterLogin();
			}
		});
		add(login_button);
		
		// 아이디 텍스트필드 속성 설정
		id_field.setBounds(705, 460, 450, 81);
		id_field.setVisible(true);
		id_field.setOpaque(false);
		id_field.setBorder(BorderFactory.createEmptyBorder());
		id_field.setFont(new Font("인터파크고딕 M", Font.PLAIN, 45));
		// 아이디 텍스트필드 키보드 이벤트
		id_field.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(id_field.getText().equals("") || pw_field.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 입력해주십시오.", "Error", JOptionPane.ERROR_MESSAGE);	
					}else {
						new MasterLogin();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		add(id_field);

		// 비밀번호 텍스트필드 속성 설정
		pw_field.setBounds(705, 600, 450, 81);
		pw_field.setVisible(true);
		pw_field.setOpaque(false);
		pw_field.setBorder(BorderFactory.createEmptyBorder());
		pw_field.setFont(new Font("서울남산 장체 B", Font.BOLD, 45));
		// 비밀번호 텍스트필드 키보드 이벤트
		pw_field.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(id_field.getText().equals("") || pw_field.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 입력해주십시오.", "Error", JOptionPane.ERROR_MESSAGE);	
					}else {
						new MasterLogin();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		add(pw_field);
	}
	public static JTextField getId_field() {
		return id_field;
	}
	public static JPasswordField getPw_field() {
		return pw_field;
	}
	// 배경화면 그려주는 메서드
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
}