package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EmployeeGUI extends JFrame{
	public EmployeeGUI() {
	}
	public static void main(String args[]) {
		ManagerPanel manager_panel = new ManagerPanel();
	}
}

//직원 관리 클래스
class EmployeePanel extends JPanel{
	static Icon icon = new ImageIcon("images/back_2.png");
	
	ImageIcon background = new ImageIcon("images/background_22.png");

	JButton employee_exit_button = new JButton(icon);
	static JLabel bname_label = new JLabel();
	static JLabel employeecnt_label = new JLabel();

	JButton em_in = new JButton(icon);
	JButton em_de = new JButton(icon);
	static String bname;
	
	EmployeePanel() {
		setBounds(0, 0, 1862, 1055); // 위치와 크기 지정
		setLayout(null);
		setVisible(true);

		//지점장 메인 화면으로 돌아가기
		employee_exit_button.setBounds(61, 35, 126, 35);
		employee_exit_button.setVisible(true);
		employee_exit_button.setBorderPainted(false);
		employee_exit_button.setContentAreaFilled(false);
		employee_exit_button.setFocusPainted(false);
		employee_exit_button.setOpaque(false);
		employee_exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.MainFrame.getManager_panel().setVisible(true);
				Main.MainFrame.getEmployee_panel().setVisible(false);
			}
		});
		add(employee_exit_button);

		//해당 지점 이름
		bname_label.setBounds(691, 330, 480, 80);
		bname_label.setHorizontalAlignment(JLabel.CENTER);
		bname_label.setFont(new Font("인터파크고딕 M", Font.PLAIN, 55));
		add(bname_label);
		
		//해당 지점의 현재 직원 수
		employeecnt_label.setBounds(930, 440, 50, 40);
		employeecnt_label.setText(employee_db(bname));
		employeecnt_label.setHorizontalAlignment(JLabel.RIGHT);
		employeecnt_label.setFont(new Font("인터파크고딕 M", Font.PLAIN, 45));
		add(employeecnt_label);

		//직원 추가 버튼
		em_in.setBounds(642, 562, 253, 150);
		em_in.setVisible(true);
		em_in.setBorderPainted(false);
		em_in.setContentAreaFilled(false);
		em_in.setFocusPainted(false);
		em_in.setOpaque(false);
		em_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(employee_db(bname).equals("20")) {
					JOptionPane.showMessageDialog(null, "한 지점당 20명의 직원만 등록 가능합니다.", "Error", JOptionPane.ERROR_MESSAGE);	
				}else {
					employee_in(bname);
					employeecnt_label.setText(employee_db(bname));
				}
				ManagerInfo.getManager_name_label().setText(ManagerLogin.getG_Name());
		    	ManagerInfo.getManager_id_label().setText(ManagerLogin.getG_Id());
		    	ManagerInfo.getManager_pw_label().setText(ManagerLogin.getG_Pw());
		    	ManagerInfo.getManager_bname_label().setText(ManagerLogin.getG_BName());
		    	ManagerInfo.getManager_bphone_label().setText(ManagerLogin.getG_BPhone());
		    	ManagerInfo.getManager_em_label().setText(ManagerLogin.getG_Em());
			}
		});
		add(em_in);

		//직원 삭제 버튼
		em_de.setBounds(971, 562, 253, 150);
		em_de.setVisible(true);
		em_de.setBorderPainted(false);
		em_de.setContentAreaFilled(false);
		em_de.setFocusPainted(false);
		em_de.setOpaque(false);
		em_de.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(employee_db(bname).equals("0")) {
					JOptionPane.showMessageDialog(null, "직원 등록은 최소 0명부터 가능합니다.", "Error", JOptionPane.ERROR_MESSAGE);	
				}else {
					employee_de(bname);
					employeecnt_label.setText(employee_db(bname));
				}
				ManagerInfo.getManager_name_label().setText(ManagerLogin.getG_Name());
		    	ManagerInfo.getManager_id_label().setText(ManagerLogin.getG_Id());
		    	ManagerInfo.getManager_pw_label().setText(ManagerLogin.getG_Pw());
		    	ManagerInfo.getManager_bname_label().setText(ManagerLogin.getG_BName());
		    	ManagerInfo.getManager_bphone_label().setText(ManagerLogin.getG_BPhone());
		    	ManagerInfo.getManager_em_label().setText(ManagerLogin.getG_Em());
			}
		});
		add(em_de);
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
	
	//현재 직원 수 불러오는 DB
	public static String employee_db(String bname) {		
		String em = null;
		int em_cnt = 0;
		String query;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rset = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "rootpassword");
            
            query = "SELECT G_EM FROM MANAGER_TABLE WHERE G_BNAME = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bname);
			rset = pstmt.executeQuery();
             
			boolean result = true;
			
            while (result = rset.next()) {
                return Integer.toString(rset.getInt("G_EM"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rset.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return em;
	}
	
	//직원 수 1명 추가하는 DB
	public void employee_in(String bname) {		
		String query;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rset = null;
		int change_cnt;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "rootpassword");
            
            query = "SELECT G_EM FROM MANAGER_TABLE WHERE G_BNAME = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bname);
			rset = pstmt.executeQuery();
             
			boolean result = true;
			
            while (result = rset.next()) {
            	change_cnt = rset.getInt("G_EM")+1;
            	query = "UPDATE MANAGER_TABLE SET G_EM = ? WHERE G_BNAME = ?";
            	pstmt = conn.prepareStatement(query);
            	pstmt.setInt(1, change_cnt);
    			pstmt.setString(2, bname);
    			pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rset.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	//직원 수 1명 삭제하는 DB
	public void employee_de(String bname) {		
		String query;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rset = null;
		int change_cnt;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "rootpassword");
            
            query = "SELECT G_EM FROM MANAGER_TABLE WHERE G_BNAME = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bname);
			rset = pstmt.executeQuery();
             
			boolean result = true;
			
            while (result = rset.next()) {
            	change_cnt = rset.getInt("G_EM")-1;
            	query = "UPDATE MANAGER_TABLE SET G_EM = ? WHERE G_BNAME = ?";
            	pstmt = conn.prepareStatement(query);
    			pstmt.setInt(1, change_cnt);
    			pstmt.setString(2, bname);
    			pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rset.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
}