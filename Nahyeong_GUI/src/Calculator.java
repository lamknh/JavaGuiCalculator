import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Calculator extends JFrame {
	JLabel answer;
	String names [] = {"1", "2", "3", "+", 
						"4", "5", "6", "-",
						"7", "8", "9", "x",
						"CE", "0", "=", "¡À"	};
	
	private String func = "ADD";
	private int firstNumber;
	
	public class Answer extends JPanel{
		public Answer() {			
			setBorder(new EmptyBorder(10, 10, 10, 10));
			setLayout(new BorderLayout());
			setBackground(Color.BLACK);
			setPreferredSize(new Dimension(400, 300));
			
			answer = new JLabel("") {
				 @Override public void setBorder(Border border) {
				    }
			};
			answer.setPreferredSize(new Dimension(350, 270));
			answer.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 50));
			answer.setHorizontalAlignment(SwingConstants.RIGHT);
			add(answer);
			answer.setBackground(Color.BLACK);
			answer.setForeground(Color.WHITE);
		}
	}
	
	public void set(JButton btn) {
		btn.setSize(4,2);
		btn.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 20));
		add(btn, BorderLayout.CENTER);
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.GRAY);
	}
	
	public class Button extends JPanel{
		public Button() {
			setBorder(new EmptyBorder(5, 5, 5, 5));
			GridLayout grid = new GridLayout(4,4,5,5);
			setLayout(grid);
			
			JButton buttons[] = new JButton[names.length];
			for (int i = 0; i < names.length; i++) {
				buttons[i] = new JButton(names[i]) {
					@Override public void setBorder(Border border) {
				    }
				};
				set(buttons[i]);
				if(names[i].equals("+") || names[i].equals("-")
						|| names[i].equals("x") || names[i].equals("¡À")) {
					buttons[i].setForeground(Color.BLACK);
					buttons[i].setBackground(Color.ORANGE);
				}else if (names[i].equals("CE") || names[i].equals("=")) {
					buttons[i].setForeground(Color.BLACK);
					buttons[i].setBackground(Color.lightGray);
				}
				add(buttons[i]);
			}
			
			buttons[0].addActionListener(new NumberActionListener(answer, "1"));
			buttons[1].addActionListener(new NumberActionListener(answer, "2"));
			buttons[2].addActionListener(new NumberActionListener(answer, "3"));
			
			buttons[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String curr = answer.getText();
					firstNumber = Integer.parseInt(curr);
					answer.setText("0");
					func = "ADD";
				}
			});
			
			buttons[4].addActionListener(new NumberActionListener(answer, "4"));
			buttons[5].addActionListener(new NumberActionListener(answer, "5"));
			buttons[6].addActionListener(new NumberActionListener(answer, "6"));
			
			buttons[7].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String curr = answer.getText();
					firstNumber = Integer.parseInt(curr);
					answer.setText("0");
					func = "SUB";
				}
			});
			
			buttons[8].addActionListener(new NumberActionListener(answer, "7"));
			buttons[9].addActionListener(new NumberActionListener(answer, "8"));
			buttons[10].addActionListener(new NumberActionListener(answer, "9"));
			
			buttons[11].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String curr = answer.getText();
					firstNumber = Integer.parseInt(curr);
					answer.setText("0");
					func = "MUL";
				}
			});
			
			buttons[12].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					firstNumber = 0;
					answer.setText("0");
				}
			});
			
			buttons[13].addActionListener(new NumberActionListener(answer, "0"));
			
			buttons[14].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switch(func) {
						case "ADD" : {
							int currValue = Integer.parseInt(answer.getText());
							answer.setText((firstNumber+currValue) + "");
							break;
						}
						case "SUB" : {
							int currValue = Integer.parseInt(answer.getText());
							answer.setText((firstNumber-currValue) + "");
							break;
						}
						case "MUL" : {
							int currValue = Integer.parseInt(answer.getText());
							answer.setText((firstNumber*currValue) + "");
							break;
						}
						case "DIV" : {
							int currValue = Integer.parseInt(answer.getText());
							answer.setText((double)(firstNumber/currValue) + "");
							break;
						}
					}
				}
			});
			
			buttons[15].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String curr = answer.getText();
					firstNumber = Integer.parseInt(curr);
					answer.setText("0");
					func = "DIV";
				}
			});

			setBackground(Color.BLACK);
		}
	}
	
	class NumberActionListener implements ActionListener{
		private JLabel label;
		private String text;
		
		public NumberActionListener(JLabel l, String s) {
			label = l;
			text = s;
		}
		
		public void actionPerformed(ActionEvent e) {
			String curr = label.getText();
			if(curr.equals("0")) {
				label.setText(text);
			} else {
				label.setText(label.getText() + text);
			}
		}
	}
	
	public class Input extends JPanel{
		String formula;
		
		public Input() {
			setLayout(new FlowLayout(FlowLayout.LEFT));
			setPreferredSize(new Dimension(400, 50));
			
			JLabel inputLabel = new JLabel("¼ö½ÄÀÔ·Â");
			add(inputLabel);
			
			inputLabel.setFont(new Font("¸¼Àº°íµñ", Font.BOLD, 18));
			JTextField text = new JTextField("");		
			text.setPreferredSize(new Dimension(290, 35));
			add(text);
			
			text.addKeyListener(new KeyAdapter(){
			    public void keyTyped(KeyEvent evt){
			         String temp = ((JTextField)evt.getSource()).getText() + String.valueOf(evt.getKeyChar());
			         answer.setText(temp);
			         formula = temp;
					    
			         String operators[] = formula.split("[0-9]+");
					    String operands[] = formula.split("[-+*/]");
					    int agregate = Integer.parseInt(operands[0]);
					    
					    for(int i = 1; i < operands.length; i++){
					        if(operators[i].equals("+"))
					            agregate += Integer.parseInt(operands[i]);
					        else if (operators[i].equals("-"))
					            agregate -= Integer.parseInt(operands[i]);
					        else if(operators[i].equals("*"))
					        	agregate *= Integer.parseInt(operands[i]);
					        else if(operators[i].equals("/"))
					        	agregate /= Integer.parseInt(operands[i]);
					        else if(operators[i].equals("=")){
					        	answer.setText(""+agregate);
					        }
					    }		    
					 answer.setText(""+agregate);
			    }
			});
		}
	}

	public Calculator() {
		setTitle("±è³ªÇü");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();	
		
		c.setLayout(new BorderLayout());
		c.add(new Answer(), BorderLayout.NORTH);
		c.add(new Button(), BorderLayout.CENTER);
		c.add(new Input(), BorderLayout.SOUTH);
		
		setSize(400, 700);
		setResizable(false);
		setVisible(true);
		
		c.setFocusable(true);
		c.requestFocus();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();
	}

}
