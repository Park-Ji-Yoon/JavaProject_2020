package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	ImageIcon background = new ImageIcon("images/background_4.png"); // 배경사진은 class못만들어서 그냥 넣음
	
	ManagerPanel() {
		ManagerLabel manager_label = new ManagerLabel();
		add(manager_label);

		ExitManagerBtn exit_manager_btn = new ExitManagerBtn();
		add(exit_manager_btn);
		
		setBounds(0, 0, 1862, 1055); // 위치와 크기 지정
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
class ManagerLabel extends JLabel {
	ManagerLabel() {
		setBounds(781, 0, 300, 120);
		setVisible(true);
		setText("지점장");
		setForeground(Color.black);
		setFont(new Font("서울남산 장체 B", Font.BOLD, 45));
		setHorizontalAlignment(JLabel.CENTER);
	}
}
class ExitManagerBtn extends JButton {
	ExitManagerBtn() {
		setBounds(1272, 755, 498, 160);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(112, 151, 168));
		setText("취소");
		setFont(new Font("서울남산 장체 B", Font.BOLD, 40));
		setVisible(true);
		setHorizontalAlignment(SwingConstants.CENTER);

		decorate(); // 버튼 테두리 둥글게

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