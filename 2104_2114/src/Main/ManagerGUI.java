package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManagerGUI {
	public static void main(String args[]) {
		ManagerPanel manager_panel = new ManagerPanel();
	}
}

class ManagerPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/background_4.png"); // �������� class������ �׳� ����
	
	ManagerPanel() {
		GGreetLabel ggreet_label = new GGreetLabel();
		add(ggreet_label);
		
		ManagerLabel manager_label = new ManagerLabel();
		add(manager_label);
		
		SalesManagerBtn sales_manager_btn = new SalesManagerBtn();
		add(sales_manager_btn);
		
		StockManagerBtn stock_manager_btn = new StockManagerBtn();
		add(stock_manager_btn);
		
		EmployeeManagerBtn employee_manager_btn = new EmployeeManagerBtn();
		add(employee_manager_btn);

		ExitManagerBtn exit_manager_btn = new ExitManagerBtn();
		add(exit_manager_btn);
				
		setBounds(0, 0, 1862, 1055); // ��ġ�� ũ�� ����
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
class GGreetLabel extends JLabel{
	GGreetLabel() {
		setBounds(1050, 0, 800, 120);
		setVisible(true);
		setText(getGGreet());
		setForeground(new Color(112, 151, 168));
		setFont(new Font("���ﳲ�� ��ü B", Font.BOLD, 30));
		setHorizontalAlignment(JLabel.CENTER);
	}
	public String getGGreet(){
		String greet = "";
		String query;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rset = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "rootpassword");
            
            query = "SELECT G_BNAME FROM MANAGER_TABLE";
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			boolean result = true;
			
            while (result = rset.next()) {
            	greet = rset.getString("G_BNAME") + "�� �������! �ȳ��ϼ���:-)";
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
        return greet;
	}
}
class ManagerLabel extends JLabel {
	ManagerLabel() {
		setBounds(781, 0, 300, 120);
		setVisible(true);
		setText("������");
		setForeground(Color.black);
		setFont(new Font("���ﳲ�� ��ü B", Font.BOLD, 45));
		setHorizontalAlignment(JLabel.CENTER);
	}
}
class SalesManagerBtn extends JButton {
	SalesManagerBtn() {
		setBounds(320, 110, 600, 400);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(112, 151, 168));
		setText("SalesManagerBtn");
		setFont(new Font("���ﳲ�� ��ü B", Font.BOLD, 40));
		setVisible(true);
		setHorizontalAlignment(SwingConstants.CENTER);

		decorate(); // ��ư �׵θ� �ձ۰�

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			graphics.setColor(getBackground().darker());
		} else if (getModel().isRollover()) {
			graphics.setColor(getBackground().brighter());
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width, height, 50, 50);
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		graphics.setColor(getForeground());
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		super.paintComponent(g);
	}
}
class StockManagerBtn extends JButton {
	StockManagerBtn() {
		setBounds(960, 110, 600, 400);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(112, 151, 168));
		setText("StockManagerBtn");
		setFont(new Font("���ﳲ�� ��ü B", Font.BOLD, 40));
		setVisible(true);
		setHorizontalAlignment(SwingConstants.CENTER);

		decorate(); // ��ư �׵θ� �ձ۰�

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
			}
		});
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			graphics.setColor(getBackground().darker());
		} else if (getModel().isRollover()) {
			graphics.setColor(getBackground().brighter());
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width, height, 50, 50);
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		graphics.setColor(getForeground());
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		super.paintComponent(g);
	}
}
class EmployeeManagerBtn extends JButton {
	EmployeeManagerBtn() {
		setBounds(320, 560, 600, 400);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(112, 151, 168));
		setText("EmployeeManagerBtn");
		setFont(new Font("���ﳲ�� ��ü B", Font.BOLD, 40));
		setVisible(true);
		setHorizontalAlignment(SwingConstants.CENTER);

		decorate(); // ��ư �׵θ� �ձ۰�

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.MainFrame.getEmployee_panel().setVisible(true);
				Main.MainFrame.getManager_panel().setVisible(false);
			}
		});
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			graphics.setColor(getBackground().darker());
		} else if (getModel().isRollover()) {
			graphics.setColor(getBackground().brighter());
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width, height, 50, 50);
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		graphics.setColor(getForeground());
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		super.paintComponent(g);
	}
}
class ExitManagerBtn extends JButton {
	ExitManagerBtn() {
		setBounds(960, 560, 600, 400);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(112, 151, 168));
		setText("�α׾ƿ�");
		setFont(new Font("���ﳲ�� ��ü B", Font.BOLD, 40));
		setVisible(true);
		setHorizontalAlignment(SwingConstants.CENTER);

		decorate(); // ��ư �׵θ� �ձ۰�

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.MainFrame.getLogin_panel().setVisible(true);
				Main.MainFrame.getManager_panel().setVisible(false);
			}
		});
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			graphics.setColor(getBackground().darker());
		} else if (getModel().isRollover()) {
			graphics.setColor(getBackground().brighter());
		} else {
			graphics.setColor(getBackground());
		}
		graphics.fillRoundRect(0, 0, width, height, 50, 50);
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		graphics.setColor(getForeground());
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		super.paintComponent(g);
	}
}