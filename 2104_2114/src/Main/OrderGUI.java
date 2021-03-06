package Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.EventHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;

import Manager.ManagerDAO;

// 주문과 관련된 패널, 컴포넌트들이 있는 OrderGUI
public class OrderGUI extends JFrame {
	public static void main(String args[]) {
		OrderPanel order_panel = new OrderPanel();
	}
}

// 주문하기 패널
class OrderPanel extends JPanel {
	static Icon icon = new ImageIcon("images/back_2.png");

	static String choice_store;
	static JLabel which = new JLabel();

	JButton order_button = new JButton(icon);
	JButton menu_exit_button = new JButton(icon);

	// 메뉴 가격 변수 선언
	// 커피 가격
	static int ice_coffee_price = 2500;
	static int hot_coffee_price = 2500;

	// 스무디 가격
	static int orange_smoothie_price = 3500;
	static int kiwi_smoothie_price = 3500;
	static int grape_smoothie_price = 3500;
	static int strawberry_smoothie_price = 3500;
	static int watermelon_smoothie_price = 3500;

	// 차 가격
	static int black_tea_price = 3000;
	static int green_tea_price = 3000;

	// 버블티 가격
	static int brown_sugar_bubble_price = 4000;
	static int taro_bubble_price = 4000;
	static int green_bubble_price = 4000;

	// 케이크 가격
	static int cheese_cake_price = 5000;
	static int strawberry_cake_price = 5000;
	static int chocolate_cake_price = 5000;

	// 마카롱 가격
	static int berry_macaron_price = 2500;
	static int yogurt_macaron_price = 2500;
	static int fruit_macaron_price = 2500;

	// 주문 개수
	// 0으로 초기화
	// 더블클릭 시 1씩 추가되는 변수들

	// 커피 주문 갯수 세는 변수
	static int ice_coffee_count = 0;
	static int hot_coffee_count = 0;

	// 스무디 주문 갯수 세는 변수
	static int orange_smoothie_count = 0;
	static int kiwi_smoothie_count = 0;
	static int grape_smoothie_count = 0;
	static int strawberry_smoothie_count = 0;
	static int watermelon_smoothie_count = 0;

	// 차 주문 갯수 세는 변수
	static int black_tea_count = 0;
	static int green_tea_count = 0;

	// 버블티 주문 갯수 세는 변수
	static int brown_sugar_bubble_count = 0;
	static int taro_bubble_count = 0;
	static int green_bubble_count = 0;

	// 케이크 주문 갯수 세는 변수
	static int cheese_cake_count = 0;
	static int strawberry_cake_count = 0;
	static int chocolate_cake_count = 0;

	// 마카롱 주문 갯수 세는 변수
	static int berry_macaron_count = 0;
	static int yogurt_macaron_count = 0;
	static int fruit_macaron_count = 0;

	// 주문하는 메뉴들의 총 개수
	static int all_count = OrderPanel.ice_coffee_count + OrderPanel.hot_coffee_count + OrderPanel.orange_smoothie_count
			+ OrderPanel.kiwi_smoothie_count + OrderPanel.grape_smoothie_count + OrderPanel.strawberry_smoothie_count
			+ OrderPanel.watermelon_smoothie_count + black_tea_count + green_tea_count + brown_sugar_bubble_count
			+ taro_bubble_count + green_bubble_count + cheese_cake_count + strawberry_cake_count + chocolate_cake_count
			+ berry_macaron_count + yogurt_macaron_count + fruit_macaron_count;

	// 주문하는 메뉴들의 총 가격
	static int all_price;

	// 커피 Icon, Label, Button
	Icon ice_coffee = new ImageIcon("images/ice_coffee.png");
	Icon hot_coffee = new ImageIcon("images/hot_coffee.png");
	JLabel ice_coffee_label = new JLabel("", ice_coffee, JLabel.CENTER);
	JLabel hot_coffee_label = new JLabel("", hot_coffee, JLabel.CENTER);

	JButton add_ice_coffee = new JButton("Ice");
	JButton add_hot_coffee = new JButton("Hot");

	// 스무디(과일) Icon, Label, Button
	Icon smoo_orange = new ImageIcon("images/orange-juice.png");
	Icon smoo_kiwi = new ImageIcon("images/kiwi-juice.png");
	Icon smoo_grape = new ImageIcon("images/grape-juice.png");
	Icon smoo_strawberry = new ImageIcon("images/strawberry-juice.png");
	Icon smoo_watermelon = new ImageIcon("images/watermelon-juice.png");
	JLabel smoo_orange_label = new JLabel("", smoo_orange, JLabel.CENTER);
	JLabel smoo_kiwi_label = new JLabel("", smoo_kiwi, JLabel.CENTER);
	JLabel smoo_grape_label = new JLabel("", smoo_grape, JLabel.CENTER);
	JLabel smoo_strawberry_label = new JLabel("", smoo_strawberry, JLabel.CENTER);
	JLabel smoo_watermelon_label = new JLabel("", smoo_watermelon, JLabel.CENTER);

	JButton add_orange_smoo = new JButton("Orange");
	JButton add_kiwi_smoo = new JButton("Kiwi");
	JButton add_grape_smoo = new JButton("Grape");
	JButton add_strawberry_smoo = new JButton("Strawberry");
	JButton add_watermelon_smoo = new JButton("Watermelon");

	// 차 Icon, Label, Button
	Icon tea_hong = new ImageIcon("images/tea_hong.png");
	Icon tea_green = new ImageIcon("images/tea_green.png");
	JLabel tea_hong_label = new JLabel("", tea_hong, JLabel.CENTER);
	JLabel tea_green_label = new JLabel("", tea_green, JLabel.CENTER);

	JButton add_hong_tea = new JButton("Black tea");
	JButton add_green_tea = new JButton("Green tea");

	// 버블티 Icon, Label, Button
	Icon bubble_brown = new ImageIcon("images/bubble-tea-brown.png");
	Icon bubble_taro = new ImageIcon("images/bubble-tea-taro.png");
	Icon bubble_green = new ImageIcon("images/bubble-tea-green.png");
	JLabel bubble_brown_label = new JLabel("", bubble_brown, JLabel.CENTER);
	JLabel bubble_taro_label = new JLabel("", bubble_taro, JLabel.CENTER);
	JLabel bubble_green_label = new JLabel("", bubble_green, JLabel.CENTER);

	JButton add_brown_bubble = new JButton("Brownsugar");
	JButton add_taro_bubble = new JButton("Taro");
	JButton add_green_bubble = new JButton("Green tea");

	// 케익 Icon, Label, Button
	Icon cake_cheese = new ImageIcon("images/cake_cheese.png");
	Icon cake_strawberry = new ImageIcon("images/cake-strawberry.png");
	Icon cake_chocolate = new ImageIcon("images/cake-chocolate.png");
	JLabel cake_cheese_label = new JLabel("", cake_cheese, JLabel.CENTER);
	JLabel cake_strawberry_label = new JLabel("", cake_strawberry, JLabel.CENTER);
	JLabel cake_chocolate_label = new JLabel("", cake_chocolate, JLabel.CENTER);

	JButton add_cake_cheese = new JButton("Cheese");
	JButton add_cake_strawberry = new JButton("Strawberry");
	JButton add_cake_chocolate = new JButton("Chocolate");

	// 마카롱 Icon, Label, Button
	Icon macaron_berry = new ImageIcon("images/macaron-berry.png");
	Icon macaron_yogurt = new ImageIcon("images/macaron-yogurt.png");
	Icon macaron_fruit = new ImageIcon("images/macaron-fruit.png");
	JLabel macaron_berry_label = new JLabel("", macaron_berry, JLabel.CENTER);
	JLabel macaron_yogurt_label = new JLabel("", macaron_yogurt, JLabel.CENTER);
	JLabel macaron_fruit_label = new JLabel("", macaron_fruit, JLabel.CENTER);

	JButton add_macaron_berry = new JButton("Berry");
	JButton add_macaron_yogurt = new JButton("Yogurt");
	JButton add_macaron_fruit = new JButton("Fruit");

	CoffeeInfo coffee_info_btn = new CoffeeInfo();
	SmooInfo smoo_info_btn = new SmooInfo();
	TeaInfo tea_info_btn = new TeaInfo();
	BubbleInfo bubble_info_btn = new BubbleInfo();
	CakeInfo cake_info_btn = new CakeInfo();
	MacaronInfo macaron_info_btn = new MacaronInfo();

	// MainPanel에 올릴 컴포넌트들의 생성자 호출
	ImageIcon background = new ImageIcon("images/background_80.png");

	static OrderList order_list = new OrderList();

	// 메뉴를 더블클릭해서 추가 할 시 오른쪽 패널에 추가될 JLabel들 초기화
	// 더블클릭이 되지 않으면 사용이 안 됨
	static OrderDetails iceCoffee = new OrderDetails();
	static OrderDetails hotCoffee = new OrderDetails();
	static OrderDetails orangeSmoo = new OrderDetails();
	static OrderDetails kiwiSmoo = new OrderDetails();
	static OrderDetails grapeSmoo = new OrderDetails();
	static OrderDetails strawberrySmoo = new OrderDetails();
	static OrderDetails watermalonSmoo = new OrderDetails();
	static OrderDetails blackTea = new OrderDetails();
	static OrderDetails greenTea = new OrderDetails();
	static OrderDetails brownBubble = new OrderDetails();
	static OrderDetails taroBubble = new OrderDetails();
	static OrderDetails greenBubble = new OrderDetails();
	static OrderDetails cheeseCake = new OrderDetails();
	static OrderDetails strayberryCake = new OrderDetails();
	static OrderDetails chocolateCake = new OrderDetails();
	static OrderDetails berryMacaron = new OrderDetails();
	static OrderDetails yogurtMacaron = new OrderDetails();
	static OrderDetails fruitMacaron = new OrderDetails();

	static OrderDetails space = new OrderDetails();

	static PriceSum price_sum = new PriceSum();

	static int order_btn_count = 0;

	public static OrderDetails getIceCoffee() {
		return iceCoffee;
	}

	// OrderPanel 생성자
	OrderPanel() {
		add(order_list);
		add(price_sum);

		setBounds(0, 0, 1862, 1055); // 패널의 위치와 크기 지정
		setLayout(null); // 패널 레이아웃 null로 설정
		setVisible(true); // 화면에 보이도록 설정

		add(coffee_info_btn);
		coffee_info_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getOrder_panel().setVisible(false);
				MainFrame.getCoffee_info_panel().setVisible(true);
			}
		});

		add(smoo_info_btn);
		smoo_info_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getOrder_panel().setVisible(false);
				MainFrame.getSmoo_info_panel().setVisible(true);
			}
		});
		
		add(tea_info_btn);
		tea_info_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getOrder_panel().setVisible(false);
				MainFrame.getTea_info_panel().setVisible(true);
			}
		});
		
		add(bubble_info_btn);
		bubble_info_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getOrder_panel().setVisible(false);
				MainFrame.getBubble_info_panel().setVisible(true);
			}
		});
		
		add(cake_info_btn);
		cake_info_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getOrder_panel().setVisible(false);
				MainFrame.getCake_info_panel().setVisible(true);
			}
		});
		
		add(macaron_info_btn);
		macaron_info_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getOrder_panel().setVisible(false);
				MainFrame.getMacaron_info_panel().setVisible(true);
			}
		});

		which.setBounds(120, 30, 400, 100);
		which.setFont(new Font("인터파크고딕 M", Font.PLAIN, 28));
		which.setForeground(new Color(255, 115, 125));
		add(which);

		order_button.setBounds(1300, 900, 200, 50);
		order_button.setVisible(true);
		order_button.setBorderPainted(false);
		order_button.setContentAreaFilled(false);
		order_button.setFocusPainted(false);
		order_button.setOpaque(false);

		// 주문하기 버튼 눌렀을 때 이벤트
		order_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 만약 주문하는 가격이 0이면 dialog창을 띄워서 경고를 함
				if (all_price == 0) {
					JOptionPane.showMessageDialog(null, "메뉴를 하나 이상 선택해주세요", "메뉴부족", JOptionPane.ERROR_MESSAGE);
					// 그렇지 않을 시 StockDB에 주문하는 메뉴 개수만큼의 재고가 존재하는지 확인
				} else {
					int co_re = StockDB.CoffeStockDB(which.getText(), ice_coffee_count, hot_coffee_count);
					int smoo_re = StockDB.SmoothieStockDB(which.getText(), orange_smoothie_count, kiwi_smoothie_count,
							grape_smoothie_count, strawberry_smoothie_count, watermelon_smoothie_count);
					int tea_re = StockDB.TeaStockDB(which.getText(), green_tea_count, black_tea_count);
					int bu_re = StockDB.BubbleStockDB(which.getText(), brown_sugar_bubble_count, taro_bubble_count,
							green_bubble_count);
					int cake_re = StockDB.CakeStockDB(which.getText(), cheese_cake_count, strawberry_cake_count,
							chocolate_cake_count);
					int maca_re = StockDB.MacaronStockDB(which.getText(), berry_macaron_count, yogurt_macaron_count,
							fruit_macaron_count);

					// 위의 int형 변수들이 받은 return값이 모두 0이면 결제하기 화면으로 넘어가고 Thread를 실행시킴
					if (co_re == 0 && smoo_re == 0 && tea_re == 0 && bu_re == 0 && cake_re == 0 && maca_re == 0) {
						order_btn_count++;
						if (order_btn_count == 1) {
							Main.MainFrame.getOrder_panel().setVisible(false);
							Main.MainFrame.getPayment_panel().setVisible(true);
							Payment.td2.start();
						} else {
							Main.MainFrame.getOrder_panel().setVisible(false);
							Main.MainFrame.getPayment_panel().setVisible(true);
							synchronized (Payment.td2) {
								Payment.td2.notify();
							}
						}
						// 결제하기 화면으로 넘어가는 로직이 끝나면
						// 주문하는 메뉴의 갯수를 0으로 설정
						all_count = 0;
						// 주문하는 메뉴의 가격를 0으로 설정
						all_price = 0;
						// 결제금액을 표시하는 Label에 "결제금액 : 0원"으로 text를 설정해준다
						price_sum.setText("결제금액 : " + all_price + "원");
					}
				}
			}
		});

		// menu_exit_button : 주문하기 화면에서 "취소하기" 버튼임
		menu_exit_button.setBounds(1550, 900, 200, 50);
		menu_exit_button.setVisible(true);
		// 버튼을 투명하게 함
		menu_exit_button.setBorderPainted(false);
		menu_exit_button.setContentAreaFilled(false);
		menu_exit_button.setFocusPainted(false);
		menu_exit_button.setOpaque(false);

		// 취소하기 버튼 추를 시 OrderPanel에서 MainPanel로 이동함
		menu_exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.MainFrame.getOrder_panel().setVisible(false);
				Main.MainFrame.getMain_panel().setVisible(true);
				OrderPanel.getOrder_list().removeAll();

				all_count = 0; // 주문하는 메뉴 갯수 0으로 설정
				all_price = 0; // 주문하는 메뉴 가격 0으로 설정
				price_sum.setText("결제금액 : " + all_price + "원"); // 결제금액도 0으로 나오도록 설정

				// 지점 선택 콤보박스가 인덱스 0번째를 선택하고 있도록 설정
				Main.MainFrame.getChoice_store_panel().remove(ChoiceArea.store_combobox);
				ChoiceArea.area_combobox.setSelectedIndex(0);
				ChoiceArea.store_combobox.setSelectedIndex(0);
				ChoiceArea.area_choice_label.setText("지역을 선택해 주세요");
				ChoiceArea.store_choice_label.setText("지점을 선택해 주세요");
			}
		});

		add(order_button);
		add(menu_exit_button);

		// 아이스 커피 라벨 설정
		ice_coffee_label.setBounds(140, 180, 145, 145);
		ice_coffee_label.setVerticalTextPosition(JLabel.CENTER);
		ice_coffee_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(ice_coffee_label);

		// 핫 커피 라벨 설정
		hot_coffee_label.setBounds(140, 180, 145, 145);
		hot_coffee_label.setVerticalTextPosition(JLabel.CENTER);
		hot_coffee_label.setHorizontalTextPosition(JLabel.RIGHT);
		hot_coffee_label.setVisible(false);
		add(hot_coffee_label);

		// 아이스 커피 추가 버튼 설정
		add_ice_coffee.setBounds(320, 210, 100, 40);
		add_ice_coffee.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_ice_coffee.setBorderPainted(false);
		add_ice_coffee.setBackground(new Color(255, 220, 212));
		add_ice_coffee.setFocusPainted(false);
		add(add_ice_coffee);
		add_ice_coffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ice_coffee_label.setVisible(true);
				hot_coffee_label.setVisible(false);
			}
		});
		add_ice_coffee.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(iceCoffee);
				
				// 더블 클릭 되었을 때 오른쪽에 쌓임
				if (e.getClickCount() == 2) {

					iceCoffee.setBounds(0, all_count * 70, 500, 50); // 쌓이는 위치는 (주문한 메뉴 종류 개수)*70
					iceCoffee.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					iceCoffee.setVisible(true);
					
					// 마이너스, 플러스 버튼 이미지 초기화
					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					// 메뉴 개수 추가(+), 메뉴 개수 삭제(-) 버튼 초기화
					JButton minus_ice_coffee = new JButton();
					minus_ice_coffee.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_ice_coffee.setBounds(10, 0, 30, 30);

					minus_ice_coffee.setBorderPainted(false); // 테두리색
					minus_ice_coffee.setFocusPainted(false);
					minus_ice_coffee.setOpaque(false);
					minus_ice_coffee.setIcon(minus);
					iceCoffee.add(minus_ice_coffee);
					
					JButton plus_ice_coffee = new JButton();
					plus_ice_coffee.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_ice_coffee.setBounds(45, 0, 30, 30);

					plus_ice_coffee.setBorderPainted(false); // 테두리색
					plus_ice_coffee.setFocusPainted(false);
					plus_ice_coffee.setOpaque(false);
					plus_ice_coffee.setIcon(plus);
					iceCoffee.add(plus_ice_coffee);

					// 아이스 커피 (-) 버튼 클릭 시 이벤트
					minus_ice_coffee.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ice_coffee_count--; // 아이스 커피 개수가 줄어듬
							// 오른쪽에 있는 JLabel의 text를 바꾸어줌
							iceCoffee.setText("          Ice-coffee  |  " + String.valueOf(ice_coffee_count) + "   |  "
									+ String.valueOf(ice_coffee_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원"); // 오른쪽 패널 밑에 있는 총액의 text를 바꿔줌
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText()); // 결제하기 버튼 누르면 나올 패널에서의 총액 text를 바꿔줌
							PaySuccessDetail.ice_coffee_pay.setText("Ice-coffee  " + ice_coffee_count + "개  "
									+ ice_coffee_count * ice_coffee_price + "원"); // 영수증 패널의 JLabel text바꿔줌
							// 만약 아이스 커피 갯수가 0이면 JLabel을 사라지게 함
							if (ice_coffee_count == 0) {
								OrderPanel.iceCoffee.setVisible(false);
							}
						}
					});
					
					// 아이스커피 JLabel에 플러스 버튼 추가
					iceCoffee.add(plus_ice_coffee);
					
					// 아이스커피 플러스버튼 이벤트
					plus_ice_coffee.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							// 아이스 커피 갯수 추가
							ice_coffee_count++;
							iceCoffee.setText("          Ice-coffee  |  " + String.valueOf(ice_coffee_count) + "   |  "
									+ String.valueOf(ice_coffee_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.ice_coffee_pay.setText("Ice-coffee  " + ice_coffee_count + "개  "
									+ ice_coffee_count * ice_coffee_price + "원");
							if (ice_coffee_count == 0) {
								OrderPanel.iceCoffee.setVisible(false);
							}
						}
					});
					iceCoffee.add(plus_ice_coffee);
					if (ice_coffee_count < 2) {
						ice_coffee_count++;
						iceCoffee.setText("          Ice-coffee  |  " + String.valueOf(ice_coffee_count) + "   |  "
								+ String.valueOf(ice_coffee_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.ice_coffee_pay.setText(
								"Ice-coffee  " + ice_coffee_count + "개  " + ice_coffee_count * ice_coffee_price + "원");
					} else {
						ice_coffee_count++;
						iceCoffee.setText("          Ice-coffee  |  " + String.valueOf(ice_coffee_count) + "   |  "
								+ String.valueOf(ice_coffee_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.ice_coffee_pay.setText(
							"Ice-coffee  " + ice_coffee_count + "개  " + ice_coffee_count * ice_coffee_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 핫 커피 추가 버튼 설정
		add_hot_coffee.setBounds(320, 260, 100, 40);
		add_hot_coffee.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_hot_coffee.setBorderPainted(false);
		add_hot_coffee.setBackground(new Color(255, 220, 212));
		add_hot_coffee.setFocusPainted(false);
		add(add_hot_coffee);
		add_hot_coffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ice_coffee_label.setVisible(false);
				hot_coffee_label.setVisible(true);
			}
		});
		add_hot_coffee.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(hotCoffee);
				if (e.getClickCount() == 2) {
					hotCoffee.setBounds(0, all_count * 70, 500, 50);
					hotCoffee.setHorizontalAlignment(JLabel.LEFT);
					hotCoffee.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					hotCoffee.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_hot_coffee = new JButton();
					minus_hot_coffee.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_hot_coffee.setBounds(10, 0, 30, 30);

					minus_hot_coffee.setBorderPainted(false); // 테두리색
					minus_hot_coffee.setFocusPainted(false);
					minus_hot_coffee.setOpaque(false);
					minus_hot_coffee.setIcon(minus);
					hotCoffee.add(minus_hot_coffee);
					
					JButton plus_hot_coffee = new JButton();
					plus_hot_coffee.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_hot_coffee.setBounds(45, 0, 30, 30);

					plus_hot_coffee.setBorderPainted(false); // 테두리색
					plus_hot_coffee.setFocusPainted(false);
					plus_hot_coffee.setOpaque(false);
					plus_hot_coffee.setIcon(plus);
					hotCoffee.add(plus_hot_coffee);

					minus_hot_coffee.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							hot_coffee_count--;
							hotCoffee.setText("          Hot-coffee  |  " + String.valueOf(hot_coffee_count) + "  |  "
									+ String.valueOf(hot_coffee_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.hot_coffee_pay.setText("Hot-coffee  " + hot_coffee_count + "개  "
									+ hot_coffee_count * hot_coffee_price + "원");
							if (hot_coffee_count == 0) {
								OrderPanel.hotCoffee.setVisible(false);
							}
						}
					});
					hotCoffee.add(minus_hot_coffee);
					plus_hot_coffee.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							hot_coffee_count++;
							hotCoffee.setText("          Hot-coffee  |  " + String.valueOf(hot_coffee_count) + "  |  "
									+ String.valueOf(hot_coffee_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.hot_coffee_pay.setText("Hot-coffee  " + hot_coffee_count + "개  "
									+ hot_coffee_count * hot_coffee_price + "원");
							if (hot_coffee_count == 0) {
								OrderPanel.hotCoffee.setVisible(false);
							}
						}
					});
					hotCoffee.add(plus_hot_coffee);
					if (hot_coffee_count < 2) {
						hot_coffee_count++;
						hotCoffee.setText("          Hot-coffee  |  " + String.valueOf(hot_coffee_count) + "  |  "
								+ String.valueOf(hot_coffee_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.hot_coffee_pay.setText(
								"Hot-coffee  " + hot_coffee_count + "개  " + hot_coffee_count * hot_coffee_price + "원");
					} else {
						hot_coffee_count++;
						hotCoffee.setText("          Hot-coffee  |  " + String.valueOf(hot_coffee_count) + "  |  "
								+ String.valueOf(hot_coffee_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.hot_coffee_pay.setText(
							"Hot-coffee  " + hot_coffee_count + "개  " + hot_coffee_count * hot_coffee_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 오렌지 스무디 추가 버튼 설정
		smoo_orange_label.setBounds(590, 180, 145, 145);
		smoo_orange_label.setVerticalTextPosition(JLabel.CENTER);
		smoo_orange_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(smoo_orange_label);
		smoo_orange_label.setVisible(true);

		smoo_kiwi_label.setBounds(590, 180, 145, 145);
		smoo_kiwi_label.setVerticalTextPosition(JLabel.CENTER);
		smoo_kiwi_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(smoo_kiwi_label);
		smoo_kiwi_label.setVisible(false);

		smoo_grape_label.setBounds(590, 180, 145, 145);
		smoo_grape_label.setVerticalTextPosition(JLabel.CENTER);
		smoo_grape_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(smoo_grape_label);
		smoo_grape_label.setVisible(false);

		smoo_strawberry_label.setBounds(590, 180, 145, 145);
		smoo_strawberry_label.setVerticalTextPosition(JLabel.CENTER);
		smoo_strawberry_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(smoo_strawberry_label);
		smoo_strawberry_label.setVisible(false);

		smoo_watermelon_label.setBounds(590, 180, 145, 145);
		smoo_watermelon_label.setVerticalTextPosition(JLabel.CENTER);
		smoo_watermelon_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(smoo_watermelon_label);
		smoo_watermelon_label.setVisible(false);

		add_orange_smoo.setBounds(740, 170, 170, 27);
		add_orange_smoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 22));
		add_orange_smoo.setBorderPainted(false);
		add_orange_smoo.setBackground(new Color(247, 255, 212));
		add_orange_smoo.setFocusPainted(false);
		add(add_orange_smoo);
		add_orange_smoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smoo_orange_label.setVisible(true);
			}
		});
		add_orange_smoo.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(orangeSmoo);
				if (e.getClickCount() == 2) {
					orangeSmoo.setBounds(0, all_count * 70, 500, 50);
					orangeSmoo.setHorizontalAlignment(JLabel.LEFT);
					orangeSmoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					orangeSmoo.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_orange_smoo = new JButton();
					minus_orange_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_orange_smoo.setBounds(10, 0, 30, 30);

					minus_orange_smoo.setBorderPainted(false); // 테두리색
					minus_orange_smoo.setFocusPainted(false);
					minus_orange_smoo.setOpaque(false);
					minus_orange_smoo.setIcon(minus);
					hotCoffee.add(minus_orange_smoo);
					
					JButton plus_orange_smoo = new JButton();
					plus_orange_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_orange_smoo.setBounds(45, 0, 30, 30);

					plus_orange_smoo.setBorderPainted(false); // 테두리색
					plus_orange_smoo.setFocusPainted(false);
					plus_orange_smoo.setOpaque(false);
					plus_orange_smoo.setIcon(plus);
					hotCoffee.add(plus_orange_smoo);

					minus_orange_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							orange_smoothie_count--;
							orangeSmoo.setText("          Orange-smoothie  |  " + String.valueOf(orange_smoothie_count)
									+ "  |  " + String.valueOf(orange_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.orange_smoo_pay.setText("Orange-smoothie  " + orange_smoothie_count + "개  "
									+ orange_smoothie_count * orange_smoothie_price + "원");
							if (orange_smoothie_count == 0) {
								OrderPanel.orangeSmoo.setVisible(false);
							}
						}
					});
					orangeSmoo.add(minus_orange_smoo);
					
					plus_orange_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							orange_smoothie_count++;
							orangeSmoo.setText("          Orange-smoothie  |  " + String.valueOf(orange_smoothie_count)
									+ "  |  " + String.valueOf(orange_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.orange_smoo_pay.setText("Orange-smoothie  " + orange_smoothie_count + "개  "
									+ orange_smoothie_count * orange_smoothie_price + "원");
							if (orange_smoothie_count == 0) {
								OrderPanel.orangeSmoo.setVisible(false);
							}
						}
					});
					orangeSmoo.add(plus_orange_smoo);
					
					if (orange_smoothie_count < 2) {
						orange_smoothie_count++;
						orangeSmoo.setText("          Orange-smoothie  |  " + String.valueOf(orange_smoothie_count) + "  |  "
								+ String.valueOf(orange_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					} else {
						orange_smoothie_count++;
						orangeSmoo.setText("          Orange-smoothie  |  " + String.valueOf(orange_smoothie_count) + "  |  "
								+ String.valueOf(orange_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.orange_smoo_pay.setText("Orange-smoothie  " + orange_smoothie_count + "개  "
								+ orange_smoothie_count * orange_smoothie_price + "원");
					}
					PaySuccessDetail.orange_smoo_pay.setText("Orange-smoothie  " + orange_smoothie_count + "개  "
							+ orange_smoothie_count * orange_smoothie_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 키위 스무디 추가 버튼 설정
		add_kiwi_smoo.setBounds(740, 205, 170, 27);
		add_kiwi_smoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 22));
		add_kiwi_smoo.setBorderPainted(false);
		add_kiwi_smoo.setBackground(new Color(247, 255, 212));
		add_kiwi_smoo.setFocusPainted(false);
		add(add_kiwi_smoo);
		add_kiwi_smoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smoo_orange_label.setVisible(false);
				smoo_kiwi_label.setVisible(true);
				smoo_grape_label.setVisible(false);
				smoo_strawberry_label.setVisible(false);
				smoo_watermelon_label.setVisible(false);
			}
		});
		add_kiwi_smoo.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(kiwiSmoo);
				if (e.getClickCount() == 2) {
					kiwiSmoo.setBounds(0, all_count * 70, 500, 50);
					kiwiSmoo.setHorizontalAlignment(JLabel.LEFT);
					kiwiSmoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					kiwiSmoo.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_kiwi_smoo = new JButton();
					minus_kiwi_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_kiwi_smoo.setBounds(10, 0, 30, 30);

					minus_kiwi_smoo.setBorderPainted(false); // 테두리색
					minus_kiwi_smoo.setFocusPainted(false);
					minus_kiwi_smoo.setOpaque(false);
					minus_kiwi_smoo.setIcon(minus);
					kiwiSmoo.add(minus_kiwi_smoo);
					
					JButton plus_kiwi_smoo = new JButton();
					plus_kiwi_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_kiwi_smoo.setBounds(45, 0, 30, 30);

					plus_kiwi_smoo.setBorderPainted(false); // 테두리색
					plus_kiwi_smoo.setFocusPainted(false);
					plus_kiwi_smoo.setOpaque(false);
					plus_kiwi_smoo.setIcon(plus);
					kiwiSmoo.add(plus_kiwi_smoo);

					minus_kiwi_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							kiwi_smoothie_count--;
							kiwiSmoo.setText("          Kiwi-smoothie  |  " + String.valueOf(kiwi_smoothie_count) + "  |  "
									+ String.valueOf(kiwi_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.kiwi_smoo_pay.setText("Kiwi-smoothie  " + kiwi_smoothie_count + "개  "
									+ kiwi_smoothie_count * kiwi_smoothie_price + "원");
							if (kiwi_smoothie_count == 0) {
								OrderPanel.kiwiSmoo.setVisible(false);
							}
						}
					});
					kiwiSmoo.add(minus_kiwi_smoo);
					
					plus_kiwi_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							kiwi_smoothie_count++;
							kiwiSmoo.setText("          Kiwi-smoothie  |  " + String.valueOf(kiwi_smoothie_count) + "  |  "
									+ String.valueOf(kiwi_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.kiwi_smoo_pay.setText("Kiwi-smoothie  " + kiwi_smoothie_count + "개  "
									+ kiwi_smoothie_count * kiwi_smoothie_price + "원");
							if (kiwi_smoothie_count == 0) {
								OrderPanel.kiwiSmoo.setVisible(false);
							}
						}
					});
					kiwiSmoo.add(plus_kiwi_smoo);
					
					if (kiwi_smoothie_count < 2) {
						kiwi_smoothie_count++;
						kiwiSmoo.setText("          Kiwi-smoothie  |  " + String.valueOf(kiwi_smoothie_count) + "  |  "
								+ String.valueOf(kiwi_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.kiwi_smoo_pay.setText("Kiwi-smoothie  " + kiwi_smoothie_count + "개  "
								+ kiwi_smoothie_count * kiwi_smoothie_price + "원");
					} else {
						kiwi_smoothie_count++;
						kiwiSmoo.setText("          Kiwi-smoothie  |  " + String.valueOf(kiwi_smoothie_count) + "  |  "
								+ String.valueOf(kiwi_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.kiwi_smoo_pay.setText("Kiwi-smoothie  " + kiwi_smoothie_count + "개  "
							+ kiwi_smoothie_count * kiwi_smoothie_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 포도 스무디 추가 버튼 설정
		add_grape_smoo.setBounds(740, 240, 170, 27);
		add_grape_smoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 22));
		add_grape_smoo.setBorderPainted(false);
		add_grape_smoo.setBackground(new Color(247, 255, 212));
		add_grape_smoo.setFocusPainted(false);
		add(add_grape_smoo);
		add_grape_smoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smoo_orange_label.setVisible(false);
				smoo_kiwi_label.setVisible(false);
				smoo_grape_label.setVisible(true);
				smoo_strawberry_label.setVisible(false);
				smoo_watermelon_label.setVisible(false);
			}
		});
		add_grape_smoo.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(grapeSmoo);
				if (e.getClickCount() == 2) {
					grapeSmoo.setBounds(0, all_count * 70, 500, 50);
					grapeSmoo.setHorizontalAlignment(JLabel.LEFT);
					grapeSmoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					grapeSmoo.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_grape_smoo = new JButton();
					minus_grape_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_grape_smoo.setBounds(10, 0, 30, 30);

					minus_grape_smoo.setBorderPainted(false); // 테두리색
					minus_grape_smoo.setFocusPainted(false);
					minus_grape_smoo.setOpaque(false);
					minus_grape_smoo.setIcon(minus);
					grapeSmoo.add(minus_grape_smoo);
					
					JButton plus_grape_smoo = new JButton();
					plus_grape_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_grape_smoo.setBounds(45, 0, 30, 30);

					plus_grape_smoo.setBorderPainted(false); // 테두리색
					plus_grape_smoo.setFocusPainted(false);
					plus_grape_smoo.setOpaque(false);
					plus_grape_smoo.setIcon(plus);
					grapeSmoo.add(plus_grape_smoo);

					minus_grape_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							grape_smoothie_count--;
							grapeSmoo.setText("          Grape-smoothie  |  " + String.valueOf(grape_smoothie_count)
									+ "  |  " + String.valueOf(grape_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.grape_smoo_pay.setText("Grape-smoothie  " + grape_smoothie_count + "개  "
									+ grape_smoothie_count * grape_smoothie_price + "원");
							if (grape_smoothie_count == 0) {
								OrderPanel.grapeSmoo.setVisible(false);
							}
						}
					});
					grapeSmoo.add(minus_grape_smoo);
					
					plus_grape_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							grape_smoothie_count++;
							grapeSmoo.setText("          Grape-smoothie  |  " + String.valueOf(grape_smoothie_count)
									+ "  |  " + String.valueOf(grape_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.grape_smoo_pay.setText("Grape-smoothie  " + grape_smoothie_count + "개  "
									+ grape_smoothie_count * grape_smoothie_price + "원");
							if (grape_smoothie_count == 0) {
								OrderPanel.grapeSmoo.setVisible(false);
							}
						}
					});
					grapeSmoo.add(plus_grape_smoo);
					
					if (grape_smoothie_count < 2) {
						grape_smoothie_count++;
						grapeSmoo.setText("          Grape-smoothie  |  " + String.valueOf(grape_smoothie_count) + "  |  "
								+ String.valueOf(grape_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.grape_smoo_pay.setText("Grape-smoothie  " + grape_smoothie_count + "개  "
								+ grape_smoothie_count * grape_smoothie_price + "원");
					} else {
						grape_smoothie_count++;
						grapeSmoo.setText("          Grape-smoothie  |  " + String.valueOf(grape_smoothie_count) + "  |  "
								+ String.valueOf(grape_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.grape_smoo_pay.setText("Grape-smoothie  " + grape_smoothie_count + "개  "
							+ grape_smoothie_count * grape_smoothie_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 딸기 스무디 추가 버튼 설정
		add_strawberry_smoo.setBounds(740, 275, 170, 27);
		add_strawberry_smoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 22));
		add_strawberry_smoo.setBorderPainted(false);
		add_strawberry_smoo.setBackground(new Color(247, 255, 212));
		add_strawberry_smoo.setFocusPainted(false);
		add(add_strawberry_smoo);
		add_strawberry_smoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smoo_orange_label.setVisible(false);
				smoo_kiwi_label.setVisible(false);
				smoo_grape_label.setVisible(false);
				smoo_strawberry_label.setVisible(true);
				smoo_watermelon_label.setVisible(false);
			}
		});
		add_strawberry_smoo.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(strawberrySmoo);
				if (e.getClickCount() == 2) {
					strawberrySmoo.setBounds(0, all_count * 70, 500, 50);
					strawberrySmoo.setHorizontalAlignment(JLabel.LEFT);
					strawberrySmoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					strawberrySmoo.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_strawberry_smoo = new JButton();
					minus_strawberry_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_strawberry_smoo.setBounds(10, 0, 30, 30);

					minus_strawberry_smoo.setBorderPainted(false); // 테두리색
					minus_strawberry_smoo.setFocusPainted(false);
					minus_strawberry_smoo.setOpaque(false);
					minus_strawberry_smoo.setIcon(minus);
					strawberrySmoo.add(minus_strawberry_smoo);
					
					JButton plus_strawberry_smoo = new JButton();
					plus_strawberry_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_strawberry_smoo.setBounds(45, 0, 30, 30);

					plus_strawberry_smoo.setBorderPainted(false); // 테두리색
					plus_strawberry_smoo.setFocusPainted(false);
					plus_strawberry_smoo.setOpaque(false);
					plus_strawberry_smoo.setIcon(plus);
					strawberrySmoo.add(plus_strawberry_smoo);

					minus_strawberry_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							strawberry_smoothie_count--;
							strawberrySmoo
									.setText("          SB-smoothie  |  " + String.valueOf(strawberry_smoothie_count)
											+ "  |  " + String.valueOf(strawberry_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.strawberry_smoo_pay
									.setText("Strawberry-smoothie  " + strawberry_smoothie_count + "개  "
											+ strawberry_smoothie_count * strawberry_smoothie_price + "원");
							if (strawberry_smoothie_count == 0) {
								OrderPanel.strawberrySmoo.setVisible(false);
							}
						}
					});
					strawberrySmoo.add(minus_strawberry_smoo);
					
					plus_strawberry_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							strawberry_smoothie_count++;
							strawberrySmoo
									.setText("          SB-smoothie  |  " + String.valueOf(strawberry_smoothie_count)
											+ "  |  " + String.valueOf(strawberry_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.strawberry_smoo_pay
									.setText("Strawberry-smoothie  " + strawberry_smoothie_count + "개  "
											+ strawberry_smoothie_count * strawberry_smoothie_price + "원");
							if (strawberry_smoothie_count == 0) {
								OrderPanel.strawberrySmoo.setVisible(false);
							}
						}
					});
					strawberrySmoo.add(plus_strawberry_smoo);
					
					if (strawberry_smoothie_count < 2) {
						strawberry_smoothie_count++;
						strawberrySmoo
								.setText("          SB-smoothie  |  " + String.valueOf(strawberry_smoothie_count)
										+ "  |  " + String.valueOf(strawberry_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.strawberry_smoo_pay.setText("Strawberry-smoothie  " + strawberry_smoothie_count
								+ "개  " + strawberry_smoothie_count * strawberry_smoothie_price + "원");
					} else {
						strawberry_smoothie_count++;
						strawberrySmoo
								.setText("          SB-smoothie  |  " + String.valueOf(strawberry_smoothie_count)
										+ "  |  " + String.valueOf(strawberry_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.strawberry_smoo_pay.setText("Strawberry-smoothie  " + strawberry_smoothie_count
							+ "개  " + strawberry_smoothie_count * strawberry_smoothie_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 수박 스무디 추가 버튼 설정
		add_watermelon_smoo.setBounds(740, 310, 170, 27);
		add_watermelon_smoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 22));
		add_watermelon_smoo.setBorderPainted(false);
		add_watermelon_smoo.setBackground(new Color(247, 255, 212));
		add_watermelon_smoo.setFocusPainted(false);
		add(add_watermelon_smoo);
		add_watermelon_smoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smoo_orange_label.setVisible(false);
				smoo_kiwi_label.setVisible(false);
				smoo_grape_label.setVisible(false);
				smoo_strawberry_label.setVisible(false);
				smoo_watermelon_label.setVisible(true);
			}
		});
		add_watermelon_smoo.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(watermalonSmoo);
				if (e.getClickCount() == 2) {
					watermalonSmoo.setBounds(0, all_count * 70, 500, 50);
					watermalonSmoo.setHorizontalAlignment(JLabel.LEFT);
					watermalonSmoo.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					watermalonSmoo.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_watermelon_smoo = new JButton();
					minus_watermelon_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_watermelon_smoo.setBounds(10, 0, 30, 30);

					minus_watermelon_smoo.setBorderPainted(false); // 테두리색
					minus_watermelon_smoo.setFocusPainted(false);
					minus_watermelon_smoo.setOpaque(false);
					minus_watermelon_smoo.setIcon(minus);
					watermalonSmoo.add(minus_watermelon_smoo);
					
					JButton plus_watermelon_smoo = new JButton();
					plus_watermelon_smoo.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_watermelon_smoo.setBounds(45, 0, 30, 30);

					plus_watermelon_smoo.setBorderPainted(false); // 테두리색
					plus_watermelon_smoo.setFocusPainted(false);
					plus_watermelon_smoo.setOpaque(false);
					plus_watermelon_smoo.setIcon(plus);
					watermalonSmoo.add(plus_watermelon_smoo);

					minus_watermelon_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							watermelon_smoothie_count--;
							watermalonSmoo
									.setText("          WM-smoothie  |  " + String.valueOf(watermelon_smoothie_count)
											+ "  |  " + String.valueOf(watermelon_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.watermelon_smoo_pay
									.setText("Watermelon-smoothie  " + watermelon_smoothie_count + "개  "
											+ watermelon_smoothie_count * watermelon_smoothie_price + "원");
							if (watermelon_smoothie_count == 0) {
								OrderPanel.watermalonSmoo.setVisible(false);
							}
						}
					});
					watermalonSmoo.add(minus_watermelon_smoo);
					
					plus_watermelon_smoo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							watermelon_smoothie_count++;
							watermalonSmoo.setText("          WM-smoothie  |  " + String.valueOf(watermelon_smoothie_count)
											+ "  |  " + String.valueOf(watermelon_smoothie_count * 3500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.watermelon_smoo_pay
									.setText("Watermelon-smoothie  " + watermelon_smoothie_count + "개  "
											+ watermelon_smoothie_count * watermelon_smoothie_price + "원");
							if (watermelon_smoothie_count == 0) {
								OrderPanel.watermalonSmoo.setVisible(false);
							}
						}
					});
					watermalonSmoo.add(plus_watermelon_smoo);
					
					if (watermelon_smoothie_count < 2) {
						watermelon_smoothie_count++;
						watermalonSmoo
								.setText("          WM-smoothie  |  " + String.valueOf(watermelon_smoothie_count)
										+ "  |  " + String.valueOf(watermelon_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.watermelon_smoo_pay.setText("Watermelon-smoothie  " + watermelon_smoothie_count
								+ "개  " + watermelon_smoothie_count * watermelon_smoothie_price + "원");
					} else {
						watermelon_smoothie_count++;
						watermalonSmoo
								.setText("          WM-smoothie  |  " + String.valueOf(watermelon_smoothie_count)
										+ "  |  " + String.valueOf(watermelon_smoothie_count * 3500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.watermelon_smoo_pay.setText("Watermelon-smoothie  " + watermelon_smoothie_count
							+ "개  " + watermelon_smoothie_count * watermelon_smoothie_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 홍차 추가 버튼 설정
		tea_hong_label.setBounds(140, 476, 145, 145);
		tea_hong_label.setVerticalTextPosition(JLabel.CENTER);
		tea_hong_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(tea_hong_label);

		tea_green_label.setBounds(140, 476, 145, 145);
		tea_green_label.setVerticalTextPosition(JLabel.CENTER);
		tea_green_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(tea_green_label);
		tea_green_label.setVisible(false);

		add_hong_tea.setBounds(320, 500, 150, 40);
		add_hong_tea.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_hong_tea.setBorderPainted(false);
		add_hong_tea.setBackground(new Color(191, 252, 255));
		add_hong_tea.setFocusPainted(false);
		add(add_hong_tea);
		add_hong_tea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tea_hong_label.setVisible(true);
				tea_green_label.setVisible(false);
			}
		});
		add_hong_tea.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(blackTea);
				if (e.getClickCount() == 2) {
					blackTea.setBounds(0, all_count * 70, 500, 50);
					blackTea.setHorizontalAlignment(JLabel.LEFT);
					blackTea.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					blackTea.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_black_tea = new JButton();
					minus_black_tea.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_black_tea.setBounds(10, 0, 30, 30);

					minus_black_tea.setBorderPainted(false); // 테두리색
					minus_black_tea.setFocusPainted(false);
					minus_black_tea.setOpaque(false);
					minus_black_tea.setIcon(minus);
					blackTea.add(minus_black_tea);
					
					JButton plus_black_tea = new JButton();
					plus_black_tea.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_black_tea.setBounds(45, 0, 30, 30);

					plus_black_tea.setBorderPainted(false); // 테두리색
					plus_black_tea.setFocusPainted(false);
					plus_black_tea.setOpaque(false);
					plus_black_tea.setIcon(plus);
					blackTea.add(plus_black_tea);

					minus_black_tea.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							black_tea_count--;
							blackTea.setText("          Black-tea  |  " + String.valueOf(black_tea_count) + "  |  "
									+ String.valueOf(black_tea_count * 3000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.black_tea_pay.setText(
									"Black-tea  " + black_tea_count + "개  " + black_tea_count * black_tea_price + "원");
							if (black_tea_count == 0) {
								OrderPanel.blackTea.setVisible(false);
							}
						}
					});
					blackTea.add(minus_black_tea);
					
					plus_black_tea.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							black_tea_count++;
							blackTea.setText("          Black-tea  |  " + String.valueOf(black_tea_count) + "  |  "
									+ String.valueOf(black_tea_count * 3000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.black_tea_pay.setText(
									"Black-tea  " + black_tea_count + "개  " + black_tea_count * black_tea_price + "원");
							if (black_tea_count == 0) {
								OrderPanel.blackTea.setVisible(false);
							}
						}
					});
					blackTea.add(plus_black_tea);
					
					if (black_tea_count < 2) {
						black_tea_count++;
						blackTea.setText("          Black-tea  |  " + String.valueOf(black_tea_count) + "  |  "
								+ String.valueOf(black_tea_count * 3000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.black_tea_pay.setText(
								"Black-tea  " + black_tea_count + "개  " + black_tea_count * black_tea_price + "원");
					} else {
						black_tea_count++;
						blackTea.setText("          Black-tea  |  " + String.valueOf(black_tea_count) + "  |  "
								+ String.valueOf(black_tea_count * 3000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.black_tea_pay
							.setText("Black-tea  " + black_tea_count + "개  " + black_tea_count * black_tea_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 녹차 추가 버튼 설정
		add_green_tea.setBounds(320, 550, 150, 40);
		add_green_tea.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_green_tea.setBorderPainted(false);
		add_green_tea.setBackground(new Color(191, 252, 255));
		add_green_tea.setFocusPainted(false);
		add(add_green_tea);
		add_green_tea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tea_hong_label.setVisible(false);
				tea_green_label.setVisible(true);
			}
		});
		add_green_tea.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(greenTea);
				if (e.getClickCount() == 2) {
					greenTea.setBounds(0, all_count * 70, 500, 50);
					greenTea.setHorizontalAlignment(JLabel.LEFT);
					greenTea.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					greenTea.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_green_tea = new JButton();
					minus_green_tea.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_green_tea.setBounds(10, 0, 30, 30);

					minus_green_tea.setBorderPainted(false); // 테두리색
					minus_green_tea.setFocusPainted(false);
					minus_green_tea.setOpaque(false);
					minus_green_tea.setIcon(minus);
					greenTea.add(minus_green_tea);
					
					JButton plus_green_tea = new JButton();
					plus_green_tea.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_green_tea.setBounds(45, 0, 30, 30);

					plus_green_tea.setBorderPainted(false); // 테두리색
					plus_green_tea.setFocusPainted(false);
					plus_green_tea.setOpaque(false);
					plus_green_tea.setIcon(plus);
					greenTea.add(plus_green_tea);

					minus_green_tea.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							green_tea_count--;
							greenTea.setText("          Green-tea  |  " + String.valueOf(green_tea_count) + "  |  "
									+ String.valueOf(green_tea_count * 3000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.green_tea_pay.setText(
									"Green-tea  " + green_tea_count + "개  " + green_tea_count * green_tea_price + "원");
							if (green_tea_count == 0) {
								OrderPanel.greenTea.setVisible(false);
							}
						}
					});
					greenTea.add(minus_green_tea);
					
					plus_green_tea.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							green_tea_count++;
							greenTea.setText("          Green-tea  |  " + String.valueOf(green_tea_count) + "  |  "
									+ String.valueOf(green_tea_count * 3000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.green_tea_pay.setText(
									"Green-tea  " + green_tea_count + "개  " + green_tea_count * green_tea_price + "원");
							if (green_tea_count == 0) {
								OrderPanel.greenTea.setVisible(false);
							}
						}
					});
					greenTea.add(plus_green_tea);
					
					if (green_tea_count < 2) {
						green_tea_count++;
						greenTea.setText("          Green-tea  |  " + String.valueOf(green_tea_count) + "  |  "
								+ String.valueOf(green_tea_count * 3000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.green_tea_pay.setText(
								"Green-tea  " + green_tea_count + "개  " + green_tea_count * green_tea_price + "원");
					} else {
						green_tea_count++;
						greenTea.setText("          Green-tea  |  " + String.valueOf(green_tea_count) + "  |  "
								+ String.valueOf(green_tea_count * 3000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.green_tea_pay
							.setText("Green-tea  " + green_tea_count + "개  " + green_tea_count * green_tea_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 브라운 슈가 버블티 추가 버튼 설정
		bubble_brown_label.setBounds(590, 472, 145, 145);
		bubble_brown_label.setVerticalTextPosition(JLabel.CENTER);
		bubble_brown_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(bubble_brown_label);

		bubble_taro_label.setBounds(590, 472, 145, 145);
		bubble_taro_label.setVerticalTextPosition(JLabel.CENTER);
		bubble_taro_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(bubble_taro_label);
		bubble_taro_label.setVisible(false);

		bubble_green_label.setBounds(590, 472, 145, 145);
		bubble_green_label.setVerticalTextPosition(JLabel.CENTER);
		bubble_green_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(bubble_green_label);
		bubble_green_label.setVisible(false);

		add_brown_bubble.setBounds(740, 475, 180, 40);
		add_brown_bubble.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_brown_bubble.setBorderPainted(false);
		add_brown_bubble.setBackground(new Color(201, 255, 245));
		add_brown_bubble.setFocusPainted(false);
		add(add_brown_bubble);
		add_brown_bubble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubble_brown_label.setVisible(true);
			}
		});
		add_brown_bubble.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(brownBubble);
				if (e.getClickCount() == 2) {
					brownBubble.setBounds(0, all_count * 70, 500, 50);
					brownBubble.setHorizontalAlignment(JLabel.LEFT);
					brownBubble.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					brownBubble.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_brown_bubble = new JButton();
					minus_brown_bubble.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_brown_bubble.setBounds(10, 0, 30, 30);

					minus_brown_bubble.setBorderPainted(false); // 테두리색
					minus_brown_bubble.setFocusPainted(false);
					minus_brown_bubble.setOpaque(false);
					minus_brown_bubble.setIcon(minus);
					brownBubble.add(minus_brown_bubble);
					
					JButton plus_brown_bubble = new JButton();
					plus_brown_bubble.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_brown_bubble.setBounds(45, 0, 30, 30);

					plus_brown_bubble.setBorderPainted(false); // 테두리색
					plus_brown_bubble.setFocusPainted(false);
					plus_brown_bubble.setOpaque(false);
					plus_brown_bubble.setIcon(plus);
					brownBubble.add(plus_brown_bubble);

					minus_brown_bubble.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							brown_sugar_bubble_count--;
							brownBubble.setText("          BS-bubbleT  |  " + String.valueOf(brown_sugar_bubble_count)
									+ "  |  " + String.valueOf(brown_sugar_bubble_count * 4000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.brown_bubble_pay.setText("Brownsugar-bubbleT  " + brown_sugar_bubble_count
									+ "개  " + brown_sugar_bubble_count * brown_sugar_bubble_price + "원");
							if (brown_sugar_bubble_count == 0) {
								OrderPanel.brownBubble.setVisible(false);
							}
						}
					});
					brownBubble.add(minus_brown_bubble);
					
					plus_brown_bubble.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							brown_sugar_bubble_count++;
							brownBubble.setText("          BS-bubbleT  |  " + String.valueOf(brown_sugar_bubble_count)
									+ "  |  " + String.valueOf(brown_sugar_bubble_count * 4000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.brown_bubble_pay.setText("Brownsugar-bubbleT  " + brown_sugar_bubble_count
									+ "개  " + brown_sugar_bubble_count * brown_sugar_bubble_price + "원");
							if (brown_sugar_bubble_count == 0) {
								OrderPanel.brownBubble.setVisible(false);
							}
						}
					});
					brownBubble.add(plus_brown_bubble);
					
					if (brown_sugar_bubble_count < 2) {
						brown_sugar_bubble_count++;
						brownBubble.setText("          BS-bubbleT  |  " + String.valueOf(brown_sugar_bubble_count)
								+ "  |  " + String.valueOf(brown_sugar_bubble_count * 4000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.brown_bubble_pay.setText("Brownsugar-bubbleT  " + brown_sugar_bubble_count
								+ "개  " + brown_sugar_bubble_count * brown_sugar_bubble_price + "원");
					} else {
						brown_sugar_bubble_count++;
						brownBubble.setText("          BS-bubbleT  |  " + String.valueOf(brown_sugar_bubble_count)
								+ "  |  " + String.valueOf(brown_sugar_bubble_count * 4000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.brown_bubble_pay.setText("Brownsugar-bubbleT  " + brown_sugar_bubble_count + "개  "
							+ brown_sugar_bubble_count * brown_sugar_bubble_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 타로 버블티 추가 버튼 설정
		add_taro_bubble.setBounds(740, 525, 180, 40);
		add_taro_bubble.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_taro_bubble.setBorderPainted(false);
		add_taro_bubble.setBackground(new Color(201, 255, 245));
		add_taro_bubble.setFocusPainted(false);
		add(add_taro_bubble);
		add_taro_bubble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubble_brown_label.setVisible(false);
				bubble_taro_label.setVisible(true);
				bubble_green_label.setVisible(false);
			}
		});
		add_taro_bubble.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(taroBubble);
				if (e.getClickCount() == 2) {
					taroBubble.setBounds(0, all_count * 70, 500, 50);
					taroBubble.setHorizontalAlignment(JLabel.LEFT);
					taroBubble.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					taroBubble.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_taro_bubble = new JButton();
					minus_taro_bubble.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_taro_bubble.setBounds(10, 0, 30, 30);

					minus_taro_bubble.setBorderPainted(false); // 테두리색
					minus_taro_bubble.setFocusPainted(false);
					minus_taro_bubble.setOpaque(false);
					minus_taro_bubble.setIcon(minus);
					taroBubble.add(minus_taro_bubble);
					
					JButton plus_taro_bubble = new JButton();
					plus_taro_bubble.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_taro_bubble.setBounds(45, 0, 30, 30);

					plus_taro_bubble.setBorderPainted(false); // 테두리색
					plus_taro_bubble.setFocusPainted(false);
					plus_taro_bubble.setOpaque(false);
					plus_taro_bubble.setIcon(plus);
					taroBubble.add(plus_taro_bubble);

					minus_taro_bubble.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							taro_bubble_count--;
							taroBubble.setText("          Taro-bubbleT  |  " + String.valueOf(taro_bubble_count) + "  |  "
									+ String.valueOf(taro_bubble_count * 4000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.taro_bubble_pay.setText("Taro-bubbleT  " + taro_bubble_count + "개  "
									+ taro_bubble_count * taro_bubble_price + "원");
							if (taro_bubble_count == 0) {
								OrderPanel.taroBubble.setVisible(false);
							}
						}
					});
					taroBubble.add(minus_taro_bubble);
					
					plus_taro_bubble.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							taro_bubble_count++;
							taroBubble.setText("          Taro-bubbleT  |  " + String.valueOf(taro_bubble_count) + "  |  "
									+ String.valueOf(taro_bubble_count * 4000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.taro_bubble_pay.setText("Taro-bubbleT  " + taro_bubble_count + "개  "
									+ taro_bubble_count * taro_bubble_price + "원");
							if (taro_bubble_count == 0) {
								OrderPanel.taroBubble.setVisible(false);
							}
						}
					});
					taroBubble.add(plus_taro_bubble);
					
					if (taro_bubble_count < 2) {
						taro_bubble_count++;
						taroBubble.setText("          Taro-bubbleT  |  " + String.valueOf(taro_bubble_count) + "  |  "
								+ String.valueOf(taro_bubble_count * 4000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.taro_bubble_pay.setText("Taro-bubbleT  " + taro_bubble_count + "개  "
								+ taro_bubble_count * taro_bubble_price + "원");
					} else {
						taro_bubble_count++;
						taroBubble.setText("          Taro-bubbleT  |  " + String.valueOf(taro_bubble_count) + "  |  "
								+ String.valueOf(taro_bubble_count * 4000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.taro_bubble_pay.setText(
							"Taro-bubbleT  " + taro_bubble_count + "개  " + taro_bubble_count * taro_bubble_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 녹차 버블티 추가 버튼 설정
		add_green_bubble.setBounds(740, 575, 180, 40);
		add_green_bubble.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_green_bubble.setBorderPainted(false);
		add_green_bubble.setBackground(new Color(201, 255, 245));
		add_green_bubble.setFocusPainted(false);
		add(add_green_bubble);
		add_green_bubble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubble_brown_label.setVisible(false);
				bubble_taro_label.setVisible(false);
				bubble_green_label.setVisible(true);
			}
		});
		add_green_bubble.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(greenBubble);
				if (e.getClickCount() == 2) {
					greenBubble.setBounds(0, all_count * 70, 500, 50);
					greenBubble.setHorizontalAlignment(JLabel.LEFT);
					greenBubble.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					greenBubble.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_green_bubble = new JButton();
					minus_green_bubble.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_green_bubble.setBounds(10, 0, 30, 30);

					minus_green_bubble.setBorderPainted(false); // 테두리색
					minus_green_bubble.setFocusPainted(false);
					minus_green_bubble.setOpaque(false);
					minus_green_bubble.setIcon(minus);
					greenBubble.add(minus_green_bubble);
					
					JButton plus_green_bubble = new JButton();
					plus_green_bubble.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_green_bubble.setBounds(45, 0, 30, 30);

					plus_green_bubble.setBorderPainted(false); // 테두리색
					plus_green_bubble.setFocusPainted(false);
					plus_green_bubble.setOpaque(false);
					plus_green_bubble.setIcon(plus);
					greenBubble.add(plus_green_bubble);

					minus_green_bubble.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							green_bubble_count--;
							greenBubble.setText("          Green-bubbleT  |  " + String.valueOf(green_bubble_count) + "  |  "
									+ String.valueOf(green_bubble_count * 4000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.green_bubble_pay.setText("Green-bubbleT  " + green_bubble_count + "개  "
									+ green_bubble_count * green_bubble_price + "원");
							if (green_bubble_count == 0) {
								OrderPanel.greenBubble.setVisible(false);
							}
						}
					});
					greenBubble.add(minus_green_bubble);
					
					plus_green_bubble.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							green_bubble_count++;
							greenBubble.setText("          Green-bubbleT  |  " + String.valueOf(green_bubble_count) + "  |  "
									+ String.valueOf(green_bubble_count * 4000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.green_bubble_pay.setText("Green-bubbleT  " + green_bubble_count + "개  "
									+ green_bubble_count * green_bubble_price + "원");
							if (green_bubble_count == 0) {
								OrderPanel.greenBubble.setVisible(false);
							}
						}
					});
					greenBubble.add(plus_green_bubble);
					if (green_bubble_count < 2) {
						green_bubble_count++;
						greenBubble.setText("          Green-bubbleT  |  " + String.valueOf(green_bubble_count) + "  |  "
								+ String.valueOf(green_bubble_count * 4000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.green_bubble_pay.setText("Green-bubbleT  " + green_bubble_count + "개  "
								+ green_bubble_count * green_bubble_price + "원");
					} else {
						green_bubble_count++;
						greenBubble.setText("          Green-bubbleT  |  " + String.valueOf(green_bubble_count) + "  |  "
								+ String.valueOf(green_bubble_count * 4000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.green_bubble_pay.setText("Green-bubbleT  " + green_bubble_count + "개  "
							+ green_bubble_count * green_bubble_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 치즈 케이크 추가 버튼 설정
		cake_cheese_label.setBounds(140, 764, 145, 145);
		cake_cheese_label.setVerticalTextPosition(JLabel.CENTER);
		cake_cheese_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(cake_cheese_label);

		cake_strawberry_label.setBounds(140, 764, 145, 145);
		cake_strawberry_label.setVerticalTextPosition(JLabel.CENTER);
		cake_strawberry_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(cake_strawberry_label);
		cake_strawberry_label.setVisible(false);

		cake_chocolate_label.setBounds(140, 764, 145, 145);
		cake_chocolate_label.setVerticalTextPosition(JLabel.CENTER);
		cake_chocolate_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(cake_chocolate_label);
		cake_chocolate_label.setVisible(false);

		add_cake_cheese.setBounds(315, 765, 170, 40);
		add_cake_cheese.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_cake_cheese.setBorderPainted(false);
		add_cake_cheese.setBackground(new Color(255, 212, 250));
		add_cake_cheese.setFocusPainted(false);
		add(add_cake_cheese);
		add_cake_cheese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cake_cheese_label.setVisible(true);
				cake_chocolate_label.setVisible(false);
				cake_strawberry_label.setVisible(false);
			}
		});
		add_cake_cheese.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(cheeseCake);
				if (e.getClickCount() == 2) {
					cheeseCake.setBounds(0, all_count * 70, 500, 50);
					cheeseCake.setHorizontalAlignment(JLabel.LEFT);
					cheeseCake.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					cheeseCake.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_cheese_cake = new JButton();
					minus_cheese_cake.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_cheese_cake.setBounds(10, 0, 30, 30);

					minus_cheese_cake.setBorderPainted(false); // 테두리색
					minus_cheese_cake.setFocusPainted(false);
					minus_cheese_cake.setOpaque(false);
					minus_cheese_cake.setIcon(minus);
					cheeseCake.add(minus_cheese_cake);
					
					JButton plus_cheese_cake = new JButton();
					plus_cheese_cake.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_cheese_cake.setBounds(45, 0, 30, 30);

					plus_cheese_cake.setBorderPainted(false); // 테두리색
					plus_cheese_cake.setFocusPainted(false);
					plus_cheese_cake.setOpaque(false);
					plus_cheese_cake.setIcon(plus);
					cheeseCake.add(plus_cheese_cake);

					minus_cheese_cake.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cheese_cake_count--;
							cheeseCake.setText("          Cheese-cake  |  " + String.valueOf(cheese_cake_count) + "  |  "
									+ String.valueOf(cheese_cake_count * 5000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.cheese_cake_pay.setText("Cheese-cake  " + cheese_cake_count + "개  "
									+ cheese_cake_count * cheese_cake_price + "원");
							if (cheese_cake_count == 0) {
								OrderPanel.cheeseCake.setVisible(false);
							}
						}
					});
					cheeseCake.add(minus_cheese_cake);
					
					plus_cheese_cake.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cheese_cake_count++;
							cheeseCake.setText("          Cheese-cake  |  " + String.valueOf(cheese_cake_count) + "  |  "
									+ String.valueOf(cheese_cake_count * 5000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.cheese_cake_pay.setText("Cheese-cake  " + cheese_cake_count + "개  "
									+ cheese_cake_count * cheese_cake_price + "원");
							if (cheese_cake_count == 0) {
								OrderPanel.cheeseCake.setVisible(false);
							}
						}
					});
					cheeseCake.add(plus_cheese_cake);
					
					if (cheese_cake_count < 2) {
						cheese_cake_count++;
						cheeseCake.setText("          Cheese-cake  |  " + String.valueOf(cheese_cake_count) + "  |  "
								+ String.valueOf(cheese_cake_count * 5000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.cheese_cake_pay.setText("Cheese-cake  " + cheese_cake_count + "개  "
								+ cheese_cake_count * cheese_cake_price + "원");
					} else {
						cheese_cake_count++;
						cheeseCake.setText("          Cheese-cake  |  " + String.valueOf(cheese_cake_count) + "  |  "
								+ String.valueOf(cheese_cake_count * 5000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.cheese_cake_pay.setText(
							"Cheese-cake  " + cheese_cake_count + "개  " + cheese_cake_count * cheese_cake_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 딸기 케이크 추가 버튼 설정
		add_cake_strawberry.setBounds(315, 815, 170, 40);
		add_cake_strawberry.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_cake_strawberry.setBorderPainted(false);
		add_cake_strawberry.setBackground(new Color(255, 212, 250));
		add_cake_strawberry.setFocusPainted(false);
		add(add_cake_strawberry);
		add_cake_strawberry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cake_cheese_label.setVisible(false);
				cake_strawberry_label.setVisible(true);
				cake_chocolate_label.setVisible(false);
			}
		});
		add_cake_strawberry.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(strayberryCake);
				if (e.getClickCount() == 2) {
					strayberryCake.setBounds(0, all_count * 70, 500, 50);
					strayberryCake.setHorizontalAlignment(JLabel.LEFT);
					strayberryCake.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					strayberryCake.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_strawberry_cake = new JButton();
					minus_strawberry_cake.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_strawberry_cake.setBounds(10, 0, 30, 30);

					minus_strawberry_cake.setBorderPainted(false); // 테두리색
					minus_strawberry_cake.setFocusPainted(false);
					minus_strawberry_cake.setOpaque(false);
					minus_strawberry_cake.setIcon(minus);
					strayberryCake.add(minus_strawberry_cake);
					
					JButton plus_strawberry_cake = new JButton();
					plus_strawberry_cake.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_strawberry_cake.setBounds(45, 0, 30, 30);

					plus_strawberry_cake.setBorderPainted(false); // 테두리색
					plus_strawberry_cake.setFocusPainted(false);
					plus_strawberry_cake.setOpaque(false);
					plus_strawberry_cake.setIcon(plus);
					strayberryCake.add(plus_strawberry_cake);

					minus_strawberry_cake.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							strawberry_cake_count--;
							strayberryCake.setText(" 뺌  Strawberry-cake  |  " + String.valueOf(strawberry_cake_count)
									+ "  |  " + String.valueOf(strawberry_cake_count * 5000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.strawberry_cake_pay.setText("Strawberry-cake  " + strawberry_cake_count
									+ "개  " + strawberry_cake_count * strawberry_cake_price + "원");
							if (strawberry_cake_count == 0) {
								OrderPanel.strayberryCake.setVisible(false);
							}
						}
					});
					strayberryCake.add(minus_strawberry_cake);
					
					plus_strawberry_cake.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							strawberry_cake_count++;
							strayberryCake.setText("          Strawberry-cake  |  " + String.valueOf(strawberry_cake_count)
									+ "  |  " + String.valueOf(strawberry_cake_count * 5000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.strawberry_cake_pay.setText("Strawberry-cake  " + strawberry_cake_count
									+ "개  " + strawberry_cake_count * strawberry_cake_price + "원");
							if (strawberry_cake_count == 0) {
								OrderPanel.strayberryCake.setVisible(false);
							}
						}
					});
					strayberryCake.add(plus_strawberry_cake);
					
					if (strawberry_cake_count < 2) {
						strawberry_cake_count++;
						strayberryCake.setText("          Strawberry-cake  |  " + String.valueOf(strawberry_cake_count)
								+ "  |  " + String.valueOf(strawberry_cake_count * 5000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.strawberry_cake_pay.setText("Strawberry-cake  " + strawberry_cake_count + "개  "
								+ strawberry_cake_count * strawberry_cake_price + "원");
					} else {
						strawberry_cake_count++;
						strayberryCake.setText("          Strawberry-cake  |  " + String.valueOf(strawberry_cake_count)
								+ "  |  " + String.valueOf(strawberry_cake_count * 5000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.strawberry_cake_pay.setText("Strawberry-cake  " + strawberry_cake_count + "개  "
							+ strawberry_cake_count * strawberry_cake_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 초코 케이크 추가 버튼 설정
		add_cake_chocolate.setBounds(315, 865, 170, 40);
		add_cake_chocolate.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_cake_chocolate.setBorderPainted(false);
		add_cake_chocolate.setBackground(new Color(255, 212, 250));
		add_cake_chocolate.setFocusPainted(false);
		add(add_cake_chocolate);
		add_cake_chocolate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cake_cheese_label.setVisible(false);
				cake_strawberry_label.setVisible(false);
				cake_chocolate_label.setVisible(true);
			}
		});
		add_cake_chocolate.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(chocolateCake);
				if (e.getClickCount() == 2) {
					chocolateCake.setBounds(0, all_count * 70, 500, 50);
					chocolateCake.setHorizontalAlignment(JLabel.LEFT);
					chocolateCake.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					chocolateCake.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_chocolate_cake = new JButton();
					minus_chocolate_cake.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_chocolate_cake.setBounds(10, 0, 30, 30);

					minus_chocolate_cake.setBorderPainted(false); // 테두리색
					minus_chocolate_cake.setFocusPainted(false);
					minus_chocolate_cake.setOpaque(false);
					minus_chocolate_cake.setIcon(minus);
					chocolateCake.add(minus_chocolate_cake);
					
					JButton plus_chocolate_cake = new JButton();
					plus_chocolate_cake.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_chocolate_cake.setBounds(45, 0, 30, 30);

					plus_chocolate_cake.setBorderPainted(false); // 테두리색
					plus_chocolate_cake.setFocusPainted(false);
					plus_chocolate_cake.setOpaque(false);
					plus_chocolate_cake.setIcon(plus);
					chocolateCake.add(plus_chocolate_cake);

					minus_chocolate_cake.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							chocolate_cake_count--;
							chocolateCake.setText("          Chocolate-cake  |  " + String.valueOf(chocolate_cake_count)
									+ "  |  " + String.valueOf(chocolate_cake_count * 5000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.chocolate_cake_pay.setText("Chocolate-cake  " + chocolate_cake_count
									+ "개  " + chocolate_cake_count * chocolate_cake_price + "원");
							if (chocolate_cake_count == 0) {
								OrderPanel.chocolateCake.setVisible(false);
							}
						}
					});
					chocolateCake.add(minus_chocolate_cake);
					
					plus_chocolate_cake.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							chocolate_cake_count++;
							chocolateCake.setText("          Chocolate-cake  |  " + String.valueOf(chocolate_cake_count)
									+ "  |  " + String.valueOf(chocolate_cake_count * 5000) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.chocolate_cake_pay.setText("Chocolate-cake  " + chocolate_cake_count
									+ "개  " + chocolate_cake_count * chocolate_cake_price + "원");
							if (chocolate_cake_count == 0) {
								OrderPanel.chocolateCake.setVisible(false);
							}
						}
					});
					chocolateCake.add(plus_chocolate_cake);
					
					if (chocolate_cake_count < 2) {
						chocolate_cake_count++;
						chocolateCake.setText("           Chocolate-cake  |  " + String.valueOf(chocolate_cake_count) + "  |  "
								+ String.valueOf(chocolate_cake_count * 5000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.chocolate_cake_pay.setText("Chocolate-cake  " + chocolate_cake_count + "개  "
								+ chocolate_cake_count * chocolate_cake_price + "원");
					} else {
						chocolate_cake_count++;
						chocolateCake.setText("          Chocolate-cake  |  " + String.valueOf(chocolate_cake_count) + "  |  "
								+ String.valueOf(chocolate_cake_count * 5000) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.chocolate_cake_pay.setText("Chocolate-cake  " + chocolate_cake_count + "개  "
							+ chocolate_cake_count * chocolate_cake_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 베리 마카롱 추가 버튼 설정
		macaron_berry_label.setBounds(590, 764, 145, 145);
		macaron_berry_label.setVerticalTextPosition(JLabel.CENTER);
		macaron_berry_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(macaron_berry_label);

		macaron_yogurt_label.setBounds(590, 764, 145, 145);
		macaron_yogurt_label.setVerticalTextPosition(JLabel.CENTER);
		macaron_yogurt_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(macaron_yogurt_label);
		macaron_yogurt_label.setVisible(false);

		macaron_fruit_label.setBounds(590, 764, 145, 145);
		macaron_fruit_label.setVerticalTextPosition(JLabel.CENTER);
		macaron_fruit_label.setHorizontalTextPosition(JLabel.RIGHT);
		add(macaron_fruit_label);
		macaron_fruit_label.setVisible(false);

		add_macaron_berry.setBounds(775, 765, 140, 40);
		add_macaron_berry.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_macaron_berry.setBorderPainted(false);
		add_macaron_berry.setBackground(new Color(243, 201, 255));
		add_macaron_berry.setFocusPainted(false);
		add(add_macaron_berry);
		add_macaron_berry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				macaron_berry_label.setVisible(true);
			}
		});
		add_macaron_berry.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(berryMacaron);
				if (e.getClickCount() == 2) {
					berryMacaron.setBounds(0, all_count * 70, 500, 50);
					berryMacaron.setHorizontalAlignment(JLabel.LEFT);
					berryMacaron.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					berryMacaron.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_berry_macaron = new JButton();
					minus_berry_macaron.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_berry_macaron.setBounds(10, 0, 30, 30);

					minus_berry_macaron.setBorderPainted(false); // 테두리색
					minus_berry_macaron.setFocusPainted(false);
					minus_berry_macaron.setOpaque(false);
					minus_berry_macaron.setIcon(minus);
					berryMacaron.add(minus_berry_macaron);
					
					JButton plus_berry_macaron = new JButton();
					plus_berry_macaron.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_berry_macaron.setBounds(45, 0, 30, 30);

					plus_berry_macaron.setBorderPainted(false); // 테두리색
					plus_berry_macaron.setFocusPainted(false);
					plus_berry_macaron.setOpaque(false);
					plus_berry_macaron.setIcon(plus);
					berryMacaron.add(plus_berry_macaron);

					minus_berry_macaron.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							berry_macaron_count--;
							berryMacaron.setText("          Berry-macaron  |  " + String.valueOf(berry_macaron_count) + "  |  "
									+ String.valueOf(berry_macaron_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.berry_macaron_pay.setText("Berry-macaron  " + berry_macaron_count + "개  "
									+ berry_macaron_count * berry_macaron_price + "원");
							if (berry_macaron_count == 0) {
								OrderPanel.berryMacaron.setVisible(false);
							}
						}
					});
					berryMacaron.add(minus_berry_macaron);
					
					plus_berry_macaron.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							berry_macaron_count++;
							berryMacaron.setText("          Berry-macaron  |  " + String.valueOf(berry_macaron_count) + "  |  "
									+ String.valueOf(berry_macaron_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.berry_macaron_pay.setText("Berry-macaron  " + berry_macaron_count + "개  "
									+ berry_macaron_count * berry_macaron_price + "원");
							if (berry_macaron_count == 0) {
								OrderPanel.berryMacaron.setVisible(false);
							}
						}
					});
					berryMacaron.add(plus_berry_macaron);
					
					if (berry_macaron_count < 2) {
						berry_macaron_count++;
						berryMacaron.setText("          Berry-macaron  |  " + String.valueOf(berry_macaron_count) + "  |  "
								+ String.valueOf(berry_macaron_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.berry_macaron_pay.setText("Berry-macaron  " + berry_macaron_count + "개  "
								+ berry_macaron_count * berry_macaron_price + "원");
					} else {
						berry_macaron_count++;
						berryMacaron.setText("          Berry-macaron  |  " + String.valueOf(berry_macaron_count) + "  |  "
								+ String.valueOf(berry_macaron_count * 200) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.berry_macaron_pay.setText("Berry-macaron  " + berry_macaron_count + "개  "
							+ berry_macaron_count * berry_macaron_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 요커트 마카롱 추가 버튼 설정
		add_macaron_yogurt.setBounds(775, 815, 140, 40);
		add_macaron_yogurt.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_macaron_yogurt.setBorderPainted(false);
		add_macaron_yogurt.setBackground(new Color(243, 201, 255));
		add_macaron_yogurt.setFocusPainted(false);
		add(add_macaron_yogurt);
		add_macaron_yogurt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				macaron_berry_label.setVisible(false);
				macaron_yogurt_label.setVisible(true);
				macaron_fruit_label.setVisible(false);
			}
		});
		add_macaron_yogurt.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(yogurtMacaron);
				if (e.getClickCount() == 2) {
					yogurtMacaron.setBounds(0, all_count * 70, 500, 50);
					yogurtMacaron.setHorizontalAlignment(JLabel.LEFT);
					yogurtMacaron.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					yogurtMacaron.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_yogurt_macaron = new JButton();
					minus_yogurt_macaron.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_yogurt_macaron.setBounds(10, 0, 30, 30);

					minus_yogurt_macaron.setBorderPainted(false); // 테두리색
					minus_yogurt_macaron.setFocusPainted(false);
					minus_yogurt_macaron.setOpaque(false);
					minus_yogurt_macaron.setIcon(minus);
					yogurtMacaron.add(minus_yogurt_macaron);
					
					JButton plus_yogurt_macaron = new JButton();
					plus_yogurt_macaron.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_yogurt_macaron.setBounds(45, 0, 30, 30);

					plus_yogurt_macaron.setBorderPainted(false); // 테두리색
					plus_yogurt_macaron.setFocusPainted(false);
					plus_yogurt_macaron.setOpaque(false);
					plus_yogurt_macaron.setIcon(plus);
					yogurtMacaron.add(plus_yogurt_macaron);

					minus_yogurt_macaron.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							yogurt_macaron_count--;
							yogurtMacaron.setText("          Yogurt-macaron  |  " + String.valueOf(yogurt_macaron_count)
									+ "  |  " + String.valueOf(yogurt_macaron_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.yogurt_macaron_pay.setText("Yogurt-macaron  " + yogurt_macaron_count
									+ "개  " + yogurt_macaron_count * yogurt_macaron_price + "원");
							if (yogurt_macaron_count == 0) {
								OrderPanel.yogurtMacaron.setVisible(false);
							}
						}
					});
					yogurtMacaron.add(minus_yogurt_macaron);
					
					plus_yogurt_macaron.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							yogurt_macaron_count++;
							yogurtMacaron.setText("          Yogurt-macaron  |  " + String.valueOf(yogurt_macaron_count)
									+ "  |  " + String.valueOf(yogurt_macaron_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.yogurt_macaron_pay.setText("Yogurt-macaron  " + yogurt_macaron_count
									+ "개  " + yogurt_macaron_count * yogurt_macaron_price + "원");
							if (yogurt_macaron_count == 0) {
								OrderPanel.yogurtMacaron.setVisible(false);
							}
						}
					});
					yogurtMacaron.add(plus_yogurt_macaron);
					
					if (yogurt_macaron_count < 2) {
						yogurt_macaron_count++;
						yogurtMacaron.setText("          Yogurt-macaron  |  " + String.valueOf(yogurt_macaron_count) + "  |  "
								+ String.valueOf(yogurt_macaron_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.yogurt_macaron_pay.setText("Yogurt-macaron  " + yogurt_macaron_count + "개  "
								+ yogurt_macaron_count * yogurt_macaron_price + "원");
					} else {
						yogurt_macaron_count++;
						yogurtMacaron.setText("          Yogurt-macaron  |  " + String.valueOf(yogurt_macaron_count) + "  |  "
								+ String.valueOf(yogurt_macaron_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.yogurt_macaron_pay.setText("Yogurt-macaron  " + yogurt_macaron_count + "개  "
							+ yogurt_macaron_count * yogurt_macaron_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		// 과일 마카롱 추가 버튼 설정
		add_macaron_fruit.setBounds(775, 865, 140, 40);
		add_macaron_fruit.setFont(new Font("인터파크고딕 M", Font.PLAIN, 24));
		add_macaron_fruit.setBorderPainted(false);
		add_macaron_fruit.setBackground(new Color(243, 201, 255));
		add_macaron_fruit.setFocusPainted(false);
		add(add_macaron_fruit);
		add_macaron_fruit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				macaron_berry_label.setVisible(false);
				macaron_yogurt_label.setVisible(false);
				macaron_fruit_label.setVisible(true);
			}
		});
		add_macaron_fruit.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (OrderPanel.all_count == 0) {
					OrderPanel.getOrder_list().add(space);
				}
				OrderPanel.getOrder_list().add(fruitMacaron);
				if (e.getClickCount() == 2) {
					fruitMacaron.setBounds(0, all_count * 70, 500, 50);
					fruitMacaron.setHorizontalAlignment(JLabel.LEFT);
					fruitMacaron.setFont(new Font("인터파크고딕 M", Font.PLAIN, 30));
					fruitMacaron.setVisible(true);

					ImageIcon minus = new ImageIcon("images/minus.png");
					ImageIcon plus = new ImageIcon("images/plus.png");

					JButton minus_fruit_macaron = new JButton();
					minus_fruit_macaron.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					minus_fruit_macaron.setBounds(10, 0, 30, 30);

					minus_fruit_macaron.setBorderPainted(false); // 테두리색
					minus_fruit_macaron.setFocusPainted(false);
					minus_fruit_macaron.setOpaque(false);
					minus_fruit_macaron.setIcon(minus);
					fruitMacaron.add(minus_fruit_macaron);
					
					JButton plus_fruit_macaron = new JButton();
					plus_fruit_macaron.setFont(new Font("인터파크고딕 L", Font.PLAIN, 20));
					plus_fruit_macaron.setBounds(45, 0, 30, 30);

					plus_fruit_macaron.setBorderPainted(false); // 테두리색
					plus_fruit_macaron.setFocusPainted(false);
					plus_fruit_macaron.setOpaque(false);
					plus_fruit_macaron.setIcon(plus);
					fruitMacaron.add(plus_fruit_macaron);

					minus_fruit_macaron.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							fruit_macaron_count--;
							fruitMacaron.setText("          Fruit-macaron  |  " + String.valueOf(fruit_macaron_count) + "  |  "
									+ String.valueOf(fruit_macaron_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.fruit_macaron_pay.setText("Fruit-macaron  " + fruit_macaron_count + "개  "
									+ fruit_macaron_count * fruit_macaron_price + "원");
							if (fruit_macaron_count == 0) {
								OrderPanel.fruitMacaron.setVisible(false);
							}
						}
					});
					fruitMacaron.add(minus_fruit_macaron);
					
					plus_fruit_macaron.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							fruit_macaron_count++;
							fruitMacaron.setText("          Fruit-macaron  |  " + String.valueOf(fruit_macaron_count) + "  |  "
									+ String.valueOf(fruit_macaron_count * 2500) + "원");
							all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
									+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
									+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
									+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
									+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
									+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
									+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
									+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
									+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
									+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
									+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
									+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
									+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
									+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
									+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
									+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
									+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
									+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
							price_sum.setText("결제금액 : " + all_price + "원");
							Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
							PaySuccessDetail.fruit_macaron_pay.setText("Fruit-macaron  " + fruit_macaron_count + "개  "
									+ fruit_macaron_count * fruit_macaron_price + "원");
							if (fruit_macaron_count == 0) {
								OrderPanel.fruitMacaron.setVisible(false);
							}
						}
					});
					fruitMacaron.add(plus_fruit_macaron);
					
					if (fruit_macaron_count < 2) {
						fruit_macaron_count++;
						fruitMacaron.setText("          Fruit-macaron  |  " + String.valueOf(fruit_macaron_count) + "  |  "
								+ String.valueOf(fruit_macaron_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
						PaySuccessDetail.fruit_macaron_pay.setText("Fruit-macaron  " + fruit_macaron_count + "개  "
								+ fruit_macaron_count * fruit_macaron_price + "원");
					} else {
						fruit_macaron_count++;
						fruitMacaron.setText("          Fruit-macaron  |  " + String.valueOf(fruit_macaron_count) + "  |  "
								+ String.valueOf(fruit_macaron_count * 2500) + "원");
						all_price = OrderPanel.ice_coffee_count * OrderPanel.ice_coffee_price
								+ OrderPanel.hot_coffee_count * OrderPanel.hot_coffee_price
								+ OrderPanel.orange_smoothie_count * OrderPanel.orange_smoothie_price
								+ OrderPanel.kiwi_smoothie_count * OrderPanel.kiwi_smoothie_price
								+ OrderPanel.grape_smoothie_count * OrderPanel.grape_smoothie_price
								+ OrderPanel.strawberry_smoothie_count * OrderPanel.strawberry_smoothie_price
								+ OrderPanel.watermelon_smoothie_count * OrderPanel.watermelon_smoothie_price
								+ OrderPanel.black_tea_count * OrderPanel.black_tea_price
								+ OrderPanel.green_tea_count * OrderPanel.green_tea_price
								+ OrderPanel.brown_sugar_bubble_count * OrderPanel.brown_sugar_bubble_price
								+ OrderPanel.taro_bubble_count * OrderPanel.taro_bubble_price
								+ OrderPanel.green_bubble_count * OrderPanel.green_bubble_price
								+ OrderPanel.cheese_cake_count * OrderPanel.cheese_cake_price
								+ OrderPanel.strawberry_cake_count * OrderPanel.strawberry_cake_price
								+ OrderPanel.chocolate_cake_count * OrderPanel.chocolate_cake_price
								+ OrderPanel.berry_macaron_count * OrderPanel.berry_macaron_price
								+ OrderPanel.yogurt_macaron_count * OrderPanel.yogurt_macaron_price
								+ OrderPanel.fruit_macaron_count * OrderPanel.fruit_macaron_price;
						price_sum.setText("결제금액 : " + all_price + "원");
						Payment.pay_price_sum.setText(OrderPanel.price_sum.getText());
					}
					PaySuccessDetail.fruit_macaron_pay.setText("Fruit-macaron  " + fruit_macaron_count + "개  "
							+ fruit_macaron_count * fruit_macaron_price + "원");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	public static OrderList getOrder_list() {
		return order_list;
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

class Payment extends JPanel {
	static PaymentTextCount payment_text_count = new PaymentTextCount();

	static Timer t2 = new Timer(payment_text_count);
	static Thread td2 = new Thread(t2);

	ImageIcon background = new ImageIcon("images/background_11.png"); // 배경사진은 class못만들어서 그냥 넣음
	static PriceSum pay_price_sum = new PriceSum();
	static TextField pay_text = new TextField();

	Payment() {

		setBounds(0, 0, 1862, 1055);
		setLayout(null);
		setVisible(false);
		setBackground(new Color(112, 151, 168));
		setForeground(Color.white);

		pay_price_sum.setForeground(new Color(255, 93, 82));
		pay_price_sum.setBounds(570, 560, 700, 100);
		pay_price_sum.setFont(new Font("인터파크고딕 M", Font.PLAIN, 60));
		pay_price_sum.setHorizontalAlignment(JLabel.CENTER);

		pay_text.setBounds(500, 500, 0, 0);
		pay_text.setVisible(true);

		add(pay_price_sum);
		add(payment_text_count);
		add(pay_text);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	public static Thread getTd2() {
		return td2;
	}
}

class PaymentTextCount extends JLabel {
	public PaymentTextCount() {
		setBounds(740, 290, 300, 150);
		setVisible(true);
		setForeground(Color.black);
		setFont(new Font("인터파크고딕 B", Font.PLAIN, 160));
		setHorizontalAlignment(JLabel.CENTER);
	}
}

class OrderList extends JPanel {
	static OrderDetails jumon_1 = new OrderDetails();
	static OrderDetails jumon_2 = new OrderDetails();
	static OrderDetails jumon_3 = new OrderDetails();
	static OrderDetails jumon_4 = new OrderDetails();
	static OrderDetails jumon_5 = new OrderDetails();
	static OrderDetails jumon_6 = new OrderDetails();
	static OrderDetails jumon_7 = new OrderDetails();
	static OrderDetails jumon_8 = new OrderDetails();
	static OrderDetails jumon_9 = new OrderDetails();
	static OrderDetails jumon_10 = new OrderDetails();
	static OrderDetails jumon_11 = new OrderDetails();
	static OrderDetails jumon_12 = new OrderDetails();
	static OrderDetails jumon_13 = new OrderDetails();
	static OrderDetails jumon_14 = new OrderDetails();
	static OrderDetails jumon_15 = new OrderDetails();
	static OrderDetails jumon_16 = new OrderDetails();
	static OrderDetails jumon_17 = new OrderDetails();
	static OrderDetails jumon_18 = new OrderDetails();
	static OrderDetails jumon_19 = new OrderDetails();

	OrderList() {
		setBounds(1140, 175, 570, 610);
		setVisible(true);
		setBackground(Color.WHITE);

		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEADING);
		setLayout(fl);
	}

	public static OrderDetails getJumon_1() {
		return jumon_1;
	}

	public static OrderDetails getJumon_2() {
		return jumon_2;
	}

	public static OrderDetails getJumon_3() {
		return jumon_3;
	}

	public static OrderDetails getJumon_4() {
		return jumon_4;
	}

	public static OrderDetails getJumon_5() {
		return jumon_5;
	}

	public static OrderDetails getJumon_6() {
		return jumon_6;
	}

	public static OrderDetails getJumon_7() {
		return jumon_7;
	}

	public static OrderDetails getJumon_8() {
		return jumon_8;
	}

	public static OrderDetails getJumon_9() {
		return jumon_9;
	}

	public static OrderDetails getJumon_10() {
		return jumon_10;
	}

	public static OrderDetails getJumon_11() {
		return jumon_11;
	}

	public static OrderDetails getJumon_12() {
		return jumon_12;
	}

	public static OrderDetails getJumon_13() {
		return jumon_13;
	}

	public static OrderDetails getJumon_14() {
		return jumon_14;
	}

	public static OrderDetails getJumon_15() {
		return jumon_15;
	}

	public static OrderDetails getJumon_16() {
		return jumon_16;
	}

	public static OrderDetails getJumon_17() {
		return jumon_17;
	}

	public static OrderDetails getJumon_18() {
		return jumon_18;
	}

	public static OrderDetails getJumon_19() {
		return jumon_19;
	}
}

// 각각 메뉴의 버튼 더블 클릭 했을 때 오른쪽 메뉴창에 올라가는 JLabel의 클래스
// 차곡차곡 쌓여야 하기 때문에 위치 지정 안 함
class OrderDetails extends JLabel {
	OrderDetails() {
		setFont(new Font("인터파크고딕 M", Font.PLAIN, 28));
		setVisible(true);
		setHorizontalAlignment(JLabel.LEFT); // 왼쪽 정렬
	}
}

class PriceSum extends JLabel {
	PriceSum() {
		setVisible(true);
		setBounds(1330, 750, 300, 100);
		setText("결제금액 : 0원");
		setFont(new Font("인터파크고딕 M", Font.PLAIN, 33));
	}
}

class PaySuccessPanel extends JPanel {
	ImageIcon background_2 = new ImageIcon("images/background_21.png");
	TextField pay_text = new TextField();
	PaySuccessDetail pay_success_detail = new PaySuccessDetail();

	JButton exit = new JButton();

	PaySuccessPanel() {
		
		setVisible(true);
		setBounds(0, 0, 1862, 1055);
		setLayout(null);

		exit.setVisible(true);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.setOpaque(false);
		exit.setBounds(815, 930, 200, 70);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.MainFrame.getPayment_panel().setVisible(false);
				Main.MainFrame.getPay_success_panel().setVisible(false);
				Main.MainFrame.getMain_panel().setVisible(true);
				OrderPanel.getOrder_list().removeAll();
				
				MainPageGUI.introMusic = new Music("background_music_3.mp3", true);
				MainPageGUI.introMusic.start();
			}
		});

		add(pay_success_detail);
		add(exit);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background_2.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}

class PaySuccessDetail extends JPanel {

	// 커피
	static PaySuccessLabel ice_coffee_pay = new PaySuccessLabel("images/ice_coffee_small.png");
	static PaySuccessLabel hot_coffee_pay = new PaySuccessLabel("images/hot_coffee_small.png");

	// 스무디
	static PaySuccessLabel orange_smoo_pay = new PaySuccessLabel("images/orange_juice_small.png");
	static PaySuccessLabel kiwi_smoo_pay = new PaySuccessLabel("images/kiwi_juice_small.png");
	static PaySuccessLabel grape_smoo_pay = new PaySuccessLabel("images/grape_juice_small.png");
	static PaySuccessLabel strawberry_smoo_pay = new PaySuccessLabel("images/strawberry_juice_small.png");
	static PaySuccessLabel watermelon_smoo_pay = new PaySuccessLabel("images/watermelon_juice_small.png");

	// 차
	static PaySuccessLabel black_tea_pay = new PaySuccessLabel("images/tea_hong_small.png");
	static PaySuccessLabel green_tea_pay = new PaySuccessLabel("images/tea_green_small.png");

	// 버블티
	static PaySuccessLabel brown_bubble_pay = new PaySuccessLabel("images/bubble_tea_brown_small.png");
	static PaySuccessLabel taro_bubble_pay = new PaySuccessLabel("images/bubble_tea_taro_small.png");
	static PaySuccessLabel green_bubble_pay = new PaySuccessLabel("images/bubble_tea_green_small.png");

	// 케이크
	static PaySuccessLabel cheese_cake_pay = new PaySuccessLabel("images/cake_cheese_small.png");
	static PaySuccessLabel strawberry_cake_pay = new PaySuccessLabel("images/cake_strawberry_small.png");
	static PaySuccessLabel chocolate_cake_pay = new PaySuccessLabel("images/cake_chocolate_small.png");

	// 마카롱
	static PaySuccessLabel berry_macaron_pay = new PaySuccessLabel("images/macaron_berry_small.png");
	static PaySuccessLabel yogurt_macaron_pay = new PaySuccessLabel("images/macaron_yogurt_small.png");
	static PaySuccessLabel fruit_macaron_pay = new PaySuccessLabel("images/macaron_fruit_small.png");

	PaySuccessDetail() {
		setVisible(true);
		setBackground(Color.WHITE);
		setBounds(600, 220, 650, 700);
		setLayout(new FlowLayout());

		add(ice_coffee_pay);
		add(hot_coffee_pay);
		add(orange_smoo_pay);
		add(kiwi_smoo_pay);
		add(grape_smoo_pay);
		add(strawberry_smoo_pay);
		add(watermelon_smoo_pay);
		add(black_tea_pay);
		add(green_tea_pay);
		add(brown_bubble_pay);
		add(taro_bubble_pay);
		add(green_bubble_pay);
		add(cheese_cake_pay);
		add(strawberry_cake_pay);
		add(chocolate_cake_pay);
		add(berry_macaron_pay);
		add(yogurt_macaron_pay);
		add(fruit_macaron_pay);
	}
}

class PaySuccessLabel extends JLabel {
	PaySuccessLabel(String string) {
		ImageIcon i = new ImageIcon(string);
		setIcon(i);
		setVisible(false);
		setFont(new Font("인터파크고딕 M", Font.PLAIN, 35));
	}
}

class ChoiceArea extends JPanel {
	static ArrayList<String> area_names = new ArrayList<String>(Arrays.asList("...", "부산", "대구", "울산", "경주", "창원"));

	ArrayList<String> busan_stores_name = new ArrayList<String>(Arrays.asList("..."));
	ArrayList<String> daegu_stores_name = new ArrayList<String>(Arrays.asList("..."));
	ArrayList<String> ulsan_stores_name = new ArrayList<String>(Arrays.asList("..."));
	ArrayList<String> kyeongju_stores_name = new ArrayList<String>(Arrays.asList("..."));
	ArrayList<String> changwon_stores_name = new ArrayList<String>(Arrays.asList("..."));

	ImageIcon background = new ImageIcon("images/choice_1.png");

	static ChoiceBtn area_choice_btn = new ChoiceBtn();
	static ExitBtn area_exit_btn = new ExitBtn();

	static Choice store_combobox;

	static Choice area_combobox = new Choice(area_names.toArray(new String[area_names.size()]));

	static String choisen_area, choisen_store;

	static ChoiceLabel area_choice_label = new ChoiceLabel("지역을 선택해 주세요");
	static ChoiceLabel store_choice_label = new ChoiceLabel("지점을 선택해 주세요");

	ChoiceArea() {
		String area = "";
		ArrayList<String> g_name_list = new ArrayList<String>();
		g_name_list = new ManagerDAO().getGnameList();
		for (int i = 0; i < g_name_list.size(); i++) {
			area = g_name_list.get(i).substring(0, 2);
			switch (area) {
			case "부산":
				busan_stores_name.add(g_name_list.get(i));
				break;
			case "대구":
				daegu_stores_name.add(g_name_list.get(i));
				break;
			case "울산":
				ulsan_stores_name.add(g_name_list.get(i));
				break;
			case "경주":
				kyeongju_stores_name.add(g_name_list.get(i));
				break;
			case "창원":
				changwon_stores_name.add(g_name_list.get(i));
				break;
			}
		}

		setBounds(0, 0, 1862, 1055);
		setLayout(null);

		add(area_combobox);
		add(area_choice_btn);
		add(area_exit_btn);
		add(area_choice_label);

		area_combobox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				choisen_area = area_combobox.getSelectedItem().toString();
				area_choice_label.setText(choisen_area + "이(가) 선택되었습니다");
			}
		});

		area_choice_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;

				if (choisen_area == "부산") {
					store_combobox = new Choice(busan_stores_name.toArray(new String[area_names.size()]));
					Main.MainFrame.getChoice_store_panel().add(store_combobox);
					flag = true;
				} else if (choisen_area == "대구") {
					store_combobox = new Choice(daegu_stores_name.toArray(new String[area_names.size()]));
					Main.MainFrame.getChoice_store_panel().add(store_combobox);
					flag = true;
				} else if (choisen_area == "울산") {
					store_combobox = new Choice(ulsan_stores_name.toArray(new String[area_names.size()]));
					Main.MainFrame.getChoice_store_panel().add(store_combobox);
					flag = true;
				} else if (choisen_area == "경주") {
					store_combobox = new Choice(kyeongju_stores_name.toArray(new String[area_names.size()]));
					Main.MainFrame.getChoice_store_panel().add(store_combobox);
					flag = true;
				} else if (choisen_area == "창원") {
					store_combobox = new Choice(changwon_stores_name.toArray(new String[area_names.size()]));
					Main.MainFrame.getChoice_store_panel().add(store_combobox);
					flag = true;
				} else {
					JOptionPane.showMessageDialog(null, "지역을 선택해주세요", "지역선택오류", JOptionPane.ERROR_MESSAGE);
				}
				if (flag == true) {
					Main.MainFrame.getChoice_area_panel().setVisible(false);
					Main.MainFrame.getChoice_store_panel().setVisible(true);
					store_combobox.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								choisen_store = ChoiceArea.store_combobox.getSelectedItem().toString();
								store_choice_label.setText(choisen_store + "이(가) 선택되었습니다");
							} catch (NullPointerException e1) {
								JOptionPane.showMessageDialog(null, "잘못된 지점을 선택했습니다", "지점 선택 오류",
										JOptionPane.ERROR_MESSAGE);
							}

						}
					});
				}
			}
		});
		area_exit_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.MainFrame.getChoice_area_panel().setVisible(false);
				Main.MainFrame.getMain_panel().setVisible(true);
				area_combobox.setSelectedIndex(0);
				ChoiceArea.area_choice_label.setText("지역을 선택해 주세요");
				ChoiceArea.store_choice_label.setText("지점을 선택해 주세요");
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}

class ChoiceStore extends JPanel {
	ImageIcon background = new ImageIcon("images/choice_2.png");

	static ChoiceBtn store_choice_btn = new ChoiceBtn();
	static ExitBtn store_exit_btn = new ExitBtn();

	ChoiceStore() {
		setBounds(0, 0, 1862, 1055);
		setLayout(null);

		add(store_choice_btn);
		add(store_exit_btn);
		add(ChoiceArea.store_choice_label);

		store_choice_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ChoiceArea.choisen_store == "...") {
					JOptionPane.showMessageDialog(null, "지점을 선택해주세요", "지점선택오류", JOptionPane.ERROR_MESSAGE);
				} else {
					Main.MainFrame.getOrder_panel().setVisible(true);
					Main.MainFrame.getChoice_store_panel().setVisible(false);
					OrderPanel.choice_store = ChoiceArea.choisen_store;
					OrderPanel.which.setText(OrderPanel.choice_store);
					SalesDB.CoffeSalesDB();
					SalesDB.SmoothieSalesDB();
					SalesDB.TeaSalesDB();
					SalesDB.BubbleSalesDB();
					SalesDB.CakeSalesDB();
					SalesDB.MacaronSalesDB();
					Main.MainFrame.getChoice_store_panel().remove(ChoiceArea.store_combobox);
					ChoiceArea.area_combobox.setSelectedIndex(0);
					ChoiceArea.store_combobox.setSelectedIndex(0);
					ChoiceArea.area_choice_label.setText("지역을 선택해 주세요");
					ChoiceArea.store_choice_label.setText("지점을 선택해 주세요");
				}
			}
		});
		store_exit_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.MainFrame.getChoice_area_panel().setVisible(true);
				Main.MainFrame.getChoice_store_panel().setVisible(false);
				Main.MainFrame.getChoice_store_panel().remove(ChoiceArea.store_combobox);
				ChoiceArea.area_combobox.setSelectedIndex(0);
				ChoiceArea.store_combobox.setSelectedIndex(0);
				ChoiceArea.area_choice_label.setText("지역을 선택해 주세요");
				ChoiceArea.store_choice_label.setText("지점을 선택해 주세요");
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}

class Choice extends JComboBox {
	Choice(String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			addItem(arr[i]);
		}
		setBounds(781, 350, 300, 70);
		setVisible(true);
		setBackground(Color.MAGENTA);
		setFont(new Font("인터파크고딕 M", Font.PLAIN, 36));
		setBackground(new Color(255, 212, 216));
	}
}

class ChoiceBtn extends JButton {
	ChoiceBtn() {
		setBounds(732, 700, 160, 80);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}

class ExitBtn extends JButton {
	ExitBtn() {
		setBounds(972, 700, 160, 80);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}

class ChoiceLabel extends JLabel {
	ChoiceLabel(String text) {
		setText(text);
		setFont(new Font("인터파크고딕 M", Font.PLAIN, 32));
		setBounds(581, 500, 700, 60);
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(new Color(255, 115, 125));
		setVisible(true);
	}
}

// Thread 클래스
class Timer implements Runnable {
	private JLabel timerLabel; // 초를 나타내는 Label

	static int n = 3;

	public Timer(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

	@Override
	public void run() {
		while (true) {
			timerLabel.setText(Integer.toString(n)); // 숫자를 string형으로 바꿔서 label의 text로 설정
			n--;
			try {
				Thread.sleep(1000); // 1/1000초 단위
			} catch (InterruptedException e) {
				return;
			}
			if (n == -1) {
				OrderDB.CoffeOrderDB(OrderPanel.which.getText(), OrderPanel.ice_coffee_count, OrderPanel.hot_coffee_count);

				OrderDB.SmoothieOrderDB(OrderPanel.which.getText(), OrderPanel.orange_smoothie_count,
						OrderPanel.kiwi_smoothie_count, OrderPanel.grape_smoothie_count,
						OrderPanel.strawberry_smoothie_count, OrderPanel.watermelon_smoothie_count);

				OrderDB.TeaOrderDB(OrderPanel.which.getText(), OrderPanel.green_tea_count, OrderPanel.black_tea_count);

				OrderDB.BubbleOrderDB(OrderPanel.which.getText(), OrderPanel.brown_sugar_bubble_count,
						OrderPanel.taro_bubble_count, OrderPanel.green_bubble_count);

				OrderDB.CakeOrderDB(OrderPanel.which.getText(), OrderPanel.cheese_cake_count,
						OrderPanel.strawberry_cake_count, OrderPanel.chocolate_cake_count);

				OrderDB.MacaronOrderDB(OrderPanel.which.getText(), OrderPanel.berry_macaron_count,
						OrderPanel.yogurt_macaron_count, OrderPanel.fruit_macaron_count);

				Main.MainFrame.getPay_success_panel().setVisible(true);
				Main.MainFrame.getPayment_panel().setVisible(false);
				if (OrderPanel.ice_coffee_count > 0) {
					PaySuccessDetail.ice_coffee_pay.setVisible(true);
				}
				if (OrderPanel.hot_coffee_count > 0) {
					PaySuccessDetail.hot_coffee_pay.setVisible(true);
				}
				if (OrderPanel.orange_smoothie_count > 0) {
					PaySuccessDetail.orange_smoo_pay.setVisible(true);
				}
				if (OrderPanel.kiwi_smoothie_count > 0) {
					PaySuccessDetail.kiwi_smoo_pay.setVisible(true);
				}
				if (OrderPanel.grape_smoothie_count > 0) {
					PaySuccessDetail.grape_smoo_pay.setVisible(true);
				}
				if (OrderPanel.strawberry_smoothie_count > 0) {
					PaySuccessDetail.strawberry_smoo_pay.setVisible(true);
				}
				if (OrderPanel.watermelon_smoothie_count > 0) {
					PaySuccessDetail.watermelon_smoo_pay.setVisible(true);
				}
				if (OrderPanel.green_tea_count > 0) {
					PaySuccessDetail.green_tea_pay.setVisible(true);
				}
				if (OrderPanel.black_tea_count > 0) {
					PaySuccessDetail.black_tea_pay.setVisible(true);
				}
				if (OrderPanel.brown_sugar_bubble_count > 0) {
					PaySuccessDetail.brown_bubble_pay.setVisible(true);
				}
				if (OrderPanel.taro_bubble_count > 0) {
					PaySuccessDetail.taro_bubble_pay.setVisible(true);
				}
				if (OrderPanel.green_bubble_count > 0) {
					PaySuccessDetail.green_bubble_pay.setVisible(true);
				}
				if (OrderPanel.cheese_cake_count > 0) {
					PaySuccessDetail.cheese_cake_pay.setVisible(true);
				}
				if (OrderPanel.strawberry_cake_count > 0) {
					PaySuccessDetail.strawberry_cake_pay.setVisible(true);
				}
				if (OrderPanel.chocolate_cake_count > 0) {
					PaySuccessDetail.chocolate_cake_pay.setVisible(true);
				}
				if (OrderPanel.berry_macaron_count > 0) {
					PaySuccessDetail.berry_macaron_pay.setVisible(true);
				}
				if (OrderPanel.yogurt_macaron_count > 0) {
					PaySuccessDetail.yogurt_macaron_pay.setVisible(true);
				}
				if (OrderPanel.fruit_macaron_count > 0) {
					PaySuccessDetail.fruit_macaron_pay.setVisible(true);
				}

				// 커피
				OrderPanel.ice_coffee_count = 0;
				OrderPanel.hot_coffee_count = 0;

				// 스무디
				OrderPanel.orange_smoothie_count = 0;
				OrderPanel.kiwi_smoothie_count = 0;
				OrderPanel.grape_smoothie_count = 0;
				OrderPanel.strawberry_smoothie_count = 0;
				OrderPanel.watermelon_smoothie_count = 0;

				// 차
				OrderPanel.black_tea_count = 0;
				OrderPanel.green_tea_count = 0;

				// 버블티
				OrderPanel.brown_sugar_bubble_count = 0;
				OrderPanel.taro_bubble_count = 0;
				OrderPanel.green_bubble_count = 0;

				// 케이크
				OrderPanel.cheese_cake_count = 0;
				OrderPanel.strawberry_cake_count = 0;
				OrderPanel.chocolate_cake_count = 0;

				// 마카롱
				OrderPanel.berry_macaron_count = 0;
				OrderPanel.yogurt_macaron_count = 0;
				OrderPanel.fruit_macaron_count = 0;

				// Thread 실행
				try {
					n = 10; // 10초
					synchronized (Payment.td2) {
						Payment.td2.wait();
					}
				} catch (InterruptedException e) {
					System.out.println("스레드 멈춤");
				}
			}
			if (n <= 3) { // 3초 이하일 때
				// 텍스트 색을 빨강색으로 변경
				Payment.payment_text_count.setForeground(Color.RED);
			}	
			if(n == 0) {
				MainPageGUI.introMusic.close();
				Music payment = new Music("payment_success_audio2.mp3", false);
				payment.start();
			}
		}
	}
}

class CoffeeInfoPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/kcal_1.png");
	CloseMenuInfo close_manu_info = new CloseMenuInfo();
	
	CoffeeInfoPanel() {
		setVisible(true);
		setBounds(0, 0, 1862, 1055); // 패널의 위치와 크기 지정
		setLayout(null); // 패널 레이아웃 null로 설정
		
		add(close_manu_info);
		close_manu_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getCoffee_info_panel().setVisible(false);
				MainFrame.getOrder_panel().setVisible(true);
			}
		});
	}

	// 배경화면 그려주는 메서드
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
class SmooInfoPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/kcal_2.png");
	CloseMenuInfo close_manu_info = new CloseMenuInfo();
	
	SmooInfoPanel() {
		setVisible(true);
		setBounds(0, 0, 1862, 1055); // 패널의 위치와 크기 지정
		setLayout(null); // 패널 레이아웃 null로 설정
		
		add(close_manu_info);
		close_manu_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getSmoo_info_panel().setVisible(false);
				MainFrame.getOrder_panel().setVisible(true);
			}
		});
	}

	// 배경화면 그려주는 메서드
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
class TeaInfoPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/kcal_3.png");
	CloseMenuInfo close_manu_info = new CloseMenuInfo();
	
	TeaInfoPanel() {
		setVisible(true);
		setBounds(0, 0, 1862, 1055); // 패널의 위치와 크기 지정
		setLayout(null); // 패널 레이아웃 null로 설정
		
		add(close_manu_info);
		close_manu_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getTea_info_panel().setVisible(false);
				MainFrame.getOrder_panel().setVisible(true);
			}
		});
	}

	// 배경화면 그려주는 메서드
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
class BubbleInfoPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/kcal_4.png");
	CloseMenuInfo close_manu_info = new CloseMenuInfo();
	
	BubbleInfoPanel() {
		setVisible(true);
		setBounds(0, 0, 1862, 1055); // 패널의 위치와 크기 지정
		setLayout(null); // 패널 레이아웃 null로 설정
		
		add(close_manu_info);
		close_manu_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getBubble_info_panel().setVisible(false);
				MainFrame.getOrder_panel().setVisible(true);
			}
		});
	}

	// 배경화면 그려주는 메서드
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
class CakeInfoPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/kcal_5.png");
	CloseMenuInfo close_manu_info = new CloseMenuInfo();
	
	CakeInfoPanel() {
		setVisible(true);
		setBounds(0, 0, 1862, 1055); // 패널의 위치와 크기 지정
		setLayout(null); // 패널 레이아웃 null로 설정
		
		add(close_manu_info);
		close_manu_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getCake_info_panel().setVisible(false);
				MainFrame.getOrder_panel().setVisible(true);
			}
		});
	}

	// 배경화면 그려주는 메서드
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}
class MacaronInfoPanel extends JPanel {
	ImageIcon background = new ImageIcon("images/kcal_6.png");
	CloseMenuInfo close_manu_info = new CloseMenuInfo();
	
	MacaronInfoPanel() {
		setVisible(true);
		setBounds(0, 0, 1862, 1055); // 패널의 위치와 크기 지정
		setLayout(null); // 패널 레이아웃 null로 설정
		
		add(close_manu_info);
		close_manu_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getMacaron_info_panel().setVisible(false);
				MainFrame.getOrder_panel().setVisible(true);
			}
		});
	}

	// 배경화면 그려주는 메서드
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
}

// 커피 영양정보 버튼 클래스
class CoffeeInfo extends JButton {
	CoffeeInfo() {
		setBounds(475, 372, 30, 30);
		setVisible(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}

// 스무디 영양정보 버튼 클래스
class SmooInfo extends JButton {
	SmooInfo() {
		setBounds(917, 372, 30, 30);
		setVisible(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}

// 차 영양정보 버튼 클래스
class TeaInfo extends JButton {
	TeaInfo() {
		setBounds(475, 662, 30, 30);
		setVisible(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}

// 버블티 영양정보 버튼 클래스
class BubbleInfo extends JButton {
	BubbleInfo() {
		setBounds(917, 662, 30, 30);
		setVisible(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}

// 케이크 영양정보 버튼 클래스
class CakeInfo extends JButton {
	CakeInfo() {
		setBounds(475, 952, 30, 30);
		setVisible(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}

// 마카롱 영양정보 버튼 클래스
class MacaronInfo extends JButton {
	MacaronInfo() {
		setBounds(917, 952, 30, 30);
		setVisible(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}

// 영양정보 닫기 버튼 클래스
class CloseMenuInfo extends JButton{
	CloseMenuInfo() {
		setBounds(1350, 130, 100, 60);
		setVisible(true);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
	}
}