import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.aspose.words.Cell;
import com.aspose.words.Document;
import com.aspose.words.Paragraph;
import com.aspose.words.Row;
import com.aspose.words.Run;
import com.aspose.words.Table;

public class Input {
	static JFrame frame;
	static JTextArea text;
	static JTextField line;
	static JButton generate;
	static JLabel errors;
	static JButton data;
	static JLabel print;
	static JButton memory;
	
	public Input() {
		frame = new JFrame("בוט מחסנית");
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int) (dimension.getWidth() / 2) - (int) (dimension.getWidth() / 6),
				(int) (dimension.getHeight() / 2) - (int) (dimension.getHeight() / 2.4));
		frame.setSize((int) (dimension.getWidth() / 3), (int) (dimension.getHeight() / 1.2));
		frame.getContentPane().setBackground(new Color(92, 92, 92));
		frame.setFocusable(true);
		text = new JTextArea();
		text.setSize(frame.getWidth(), frame.getHeight());
		text.setBackground(new Color(59, 59, 59));
		text.setForeground(Color.white);
		text.setCaretColor(Color.white);
		double ratio = (((double) frame.getWidth() + (double) frame.getHeight()) / 2d)
				/ ((1920d / 3d + (1080d / 1.2d)) / 2d);
		Font font = new Font("or", Font.ITALIC, (int) (20d * ratio));
		text.setFont(font);
		text.setSelectionColor(Color.lightGray);
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane.setBounds(10, 30, frame.getWidth() - 40, frame.getHeight() / 2);
		line = new JTextField("line");
		line.setFont(font);
		line.setBackground(new Color(59, 59, 59));
		line.setForeground(Color.white);
		line.setCaretColor(Color.white);
		line.setSelectionColor(Color.lightGray);
		line.setBounds(15, scrollPane.getHeight() + 40, frame.getWidth() / 5, frame.getHeight() / 20);
		Font font2 = new Font("amit", Font.BOLD, (int) (15d * ratio));
		generate = new JButton("<html><center>Generate Stack</center></html>");
		generate.setBackground(new Color(71, 150, 108));
		generate.setForeground(Color.white);
		generate.setBounds(frame.getWidth() - ((frame.getWidth() / 4) + 60),
				scrollPane.getHeight() + (frame.getHeight() - scrollPane.getHeight()) / 3, frame.getWidth() / 4,
				frame.getHeight() / 20);
		generate.setFont(font2);
		memory = new JButton("<html><center>Generate Memory</center></html>");
		memory.setBackground(new Color(255, 251, 8));
		memory.setForeground(Color.black);
		memory.setBounds(frame.getWidth() - ((int)((double)frame.getWidth() / 3.5d)+60),
				scrollPane.getHeight() + (int)((double)(frame.getHeight() - scrollPane.getHeight()) / 6.5d), (int)((double)frame.getWidth() / 3.5d),
				frame.getHeight() / 20);
		memory.setFont(font2);
		data = new JButton("<html><center>Generate Data</center></html>");
		data.setBackground(Color.CYAN);
		data.setForeground(Color.black);
		data.setBounds(40, scrollPane.getHeight() + (frame.getHeight() - scrollPane.getHeight()) / 3,
				frame.getWidth() / 4, frame.getHeight() / 20);
		data.setFont(font2);
		errors = new JLabel();
		errors.setBounds(10, scrollPane.getHeight() + (frame.getHeight() - scrollPane.getHeight()) / 3 + 80,
				frame.getWidth() - 20, 50);
		errors.setFont(font);
		errors.setBackground(Color.black);
		errors.setForeground(Color.white);
		print = new JLabel();
		Font font3 = new Font("or2", Font.ITALIC, (int) (22d * ratio));
		print.setFont(font3);
		print.setForeground(Color.black);
		JScrollPane scrollPane2 = new JScrollPane(print);
		scrollPane2.setBounds(10, scrollPane.getHeight() + (frame.getHeight() - scrollPane.getHeight()) / 3 + 180, frame.getWidth() - 40, 60);
		frame.add(memory);
		frame.add(scrollPane2);
		frame.add(data);
		frame.add(errors);
		frame.add(generate);
		frame.add(line);
		frame.add(scrollPane);
		frame.setLayout(null);
		frame.setVisible(true);

		data.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				errors.setText("");
				print.setText("");
				createData(text.getText());
			}
		});

		memory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				errors.setText("");
				try {
					Integer.parseInt(line.getText());
				} catch (Exception e) {
					errors.setText("You have to insert a number in the line textbox.");
					return;
				}
				String s = text.getText();
				Scanner sc = new Scanner(s);
				int ii = 0;
				while (sc.hasNextLine()) {
					sc.nextLine();
					ii++;
				}
				sc.close();
				if (ii < Integer.parseInt(line.getText())) {
					errors.setText("You have to enter a line that you have in your code!");
					return;
				}
				if (ii <= 0) {
					errors.setText("You have to enter a line that is bigger than 0!");
					return;
				}
				print.setText("");
				createTable(s, Integer.parseInt(line.getText()), false);
			}
		});
		
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				errors.setText("");
				try {
					Integer.parseInt(line.getText());
				} catch (Exception e) {
					errors.setText("You have to insert a number in the line textbox.");
					return;
				}
				String s = text.getText();
				Scanner sc = new Scanner(s);
				int ii = 0;
				while (sc.hasNextLine()) {
					sc.nextLine();
					ii++;
				}
				sc.close();
				if (ii < Integer.parseInt(line.getText())) {
					errors.setText("You have to enter a line that you have in your code!");
					return;
				}
				if (ii <= 0) {
					errors.setText("You have to enter a line that is bigger than 0!");
					return;
				}
				print.setText("");
				createTable(s, Integer.parseInt(line.getText()), true);
			}
		});
		// For hint.
		line.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				try {
					Integer.parseInt(line.getText());
				} catch (Exception ee) {
					line.setText("line");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (line.getText().equals("line"))
					line.setText("");
			}
		});

		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			// For feedback when the user clicks the frame where there is nothing but the
			// frame.
			@Override
			public void mouseClicked(MouseEvent arg0) {
				errors.setText("");
				frame.requestFocus();
			}
		});

		// Listener for auto line counting.
		text.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						String s = remove(text.getText());
						String finale = s.replace("\t", " ");
						if (!s.equals("")) {
							int lines = 1;
							int chars = 0;
							String str = s;
							if (isGood(s, -1)) {
								chars = 3;
								str = lines + ". " + s;
								finale = str;
							}
							for (int i = 0; i < s.length(); i++) {
								if (s.charAt(i) == '\n' && isGood(s, i) && (i != s.length()-1)) {
									String temp = str.substring(0, i + chars + 1);
									lines++;
									temp += lines + ". ";
									chars += String.valueOf(lines).length() + 2;
									str = temp + s.substring(i + 1);
									finale = str.replace("\t", " ");
								} else if (s.charAt(i) == '\n')
									lines++;
							}
						}
						text.setText(finale);
					}
				});

			}

			@Override
			public void focusGained(FocusEvent e) {

			}
		});
	}

	private String remove(String s) {
		Scanner sc = new Scanner(s);
		String all = "";
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			if(line.length() == 0) {
				if(!sc.hasNextLine())
					all+=line;
				else
					all+=line+"\n";
			}
			else {
				if (Character.isDigit(line.charAt(0))) {
				int i = 0;
				try {
					while (Character.isDigit(line.charAt(i)))
						i++;
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (i + 1 < s.length())
					if(line.charAt(i) == '.' && line.charAt(i+1) == ' ') {
						line = line.substring(i+2);
					}
			}
			if(!sc.hasNextLine())
				all+=line;
			else
				all+=line+"\n";
		}
		}
		sc.close();
		return all;
	}
	
	// Method that says if the line already has a number.
	private boolean isGood(String s, int i) {
		if (i + 3 < s.length()) {
			if (Character.isDigit(s.charAt(i + 1))) {
				try {
					while (Character.isDigit(s.charAt(i + 1)))
						i++;
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (i + 2 < s.length())
					return (s.charAt(i + 2) != ' ' && s.charAt(i + 2) != '\t') || s.charAt(i + 1) != '.';
			}
		}
		return true;
	}

	// The method that creates the stack table and scans the code.
	private void createTable(String text, int line, boolean Mahsanit) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Scanner scan = new Scanner(text);
				HashMap<String, Integer> variables = new HashMap<>();
				variables.put("bx", 0);
				variables.put("si", 0);
				variables.put("ax", 0);
				variables.put("dx", 0);
				variables.put("cx", 0);
				variables.put("di", 0);
				variables.put("bp", 0);
				variables.put("sp", 256);
				variables.put("ds", 0);
				HashMap<Integer, String> indexes = new HashMap<>();
				HashMap<Character, Integer> flags = new HashMap<>();
				flags.put('c', 0);
				flags.put('o', 0);
				flags.put('z', 0);
				flags.put('s', 0);
				Stack<Integer> stack = new Stack<>();
				HashMap<String, Integer> equs = new HashMap<>();
				try {
					int currentIndex = 0;

					// Now we scan the whole text:
					for (; scan.hasNextLine();) {
						String codeLine = scan.nextLine();
						int mm = 0;
						for (; codeLine.charAt(mm) != ' '; mm++)
							;
						codeLine = codeLine.substring(mm);
						codeLine = codeLine.toLowerCase();
						if(codeLine.contains(";"))
							codeLine = codeLine.substring(0, codeLine.indexOf(";"));
						if (codeLine.contains(".code"))
							break;
						if (codeLine.toLowerCase().contains(" db ")) {
							String var = codeLine.substring(0, codeLine.indexOf(" db "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							String operand = codeLine.substring(codeLine.indexOf(" db ") + " db ".length());
							if (operand.contains(",")) {
								String[] operands = operand.split(",");
								for (int f = 0; f < operands.length; f++) {
									operands[f] = operands[f].trim();

									if (operands[f].contains(" dup") && operands[f].contains("(")) {
										boolean isFirst = f == 0;
										String[] str = new String[operands.length - f];
										for (int i = f; i < operands.length; i++)
											str[i - f] = operands[i];
										String s = arrayToString(str);
										int i = getMul(s);
										s = s.substring(s.indexOf('(') + 1);
										String oper = dupExtract(s);
										currentIndex = indexByteDup(indexes, oper, i, currentIndex, var, isFirst);
										f = f + nextIndex(str);
									}

									else if (operands[f].contains("'")) {
										operands[f] = operands[f].replace("'", "");
										for (int io = 0; io < operands[f].length(); io++) {
											if (f == 0 && io == 0) {
												indexes.put(currentIndex, var + 'b');
											} else {
												indexes.put(currentIndex, String.valueOf(currentIndex));
											}
											currentIndex++;
										}
									} else {
										if (f == 0)
											indexes.put(currentIndex, var + 'b');
										else
											indexes.put(currentIndex, String.valueOf(currentIndex));
										currentIndex++;
									}
								}
							}

							else {
								if (operand.contains(" dup") && operand.contains("(")) {
									int y = getMul(operand);
									String s = operand;
									s = s.substring(s.indexOf('(') + 1);
									String oper = dupExtract(s);
									currentIndex = indexByteDup(indexes, oper, y, currentIndex, var, true);
								} else if (operand.contains("'")) {
									operand = operand.replace("'", "");
									for (int io = 0; io < operand.length(); io++) {
										if (io == 0) {
											indexes.put(currentIndex, var + 'b');
										} else {
											indexes.put(currentIndex, String.valueOf(currentIndex));
										}
										currentIndex++;
									}
								} else {
									indexes.put(currentIndex, var + 'b');
									currentIndex++;
								}
							}
						}

						if (codeLine.toLowerCase().contains(" dw ")) {
							String var = codeLine.substring(0, codeLine.indexOf(" dw "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							String operand = codeLine.substring(codeLine.indexOf(" dw ") + " dw ".length());
							operand = operand.trim();
							if (operand.contains(",")) {
								String[] operands = operand.split(",");
								for (int f = 0; f < operands.length; f++) {
									operands[f] = operands[f].trim();
									if (operands[f].contains(" dup") && operands[f].contains("(")) {
										boolean isFirst = f == 0;
										String[] str = new String[operands.length - f];
										for (int i = f; i < operands.length; i++)
											str[i - f] = operands[i];
										String s = arrayToString(str);
										int i = getMul(s);
										s = s.substring(s.indexOf('(') + 1);
										String oper = dupExtract(s);
										currentIndex = indexWordDup(indexes, oper, i, currentIndex, var, isFirst);
										f = f + nextIndex(str);
									} else {
										if (operands[f].contains("'")) {
											operands[f] = operands[f].replace("'", "");
											for (int io = 0; io < operands[f].length(); io++) {
												if (f == 0 && io == 0) {
													indexes.put(currentIndex, var + 'w');
												} else {
													indexes.put(currentIndex, String.valueOf(currentIndex));
												}
												currentIndex++;
												indexes.put(currentIndex, String.valueOf(currentIndex));
												currentIndex++;
											}
										} else {
											if (f == 0)
												indexes.put(currentIndex, var + 'w');
											else
												indexes.put(currentIndex, String.valueOf(currentIndex));
											currentIndex++;
										}
										indexes.put(currentIndex, String.valueOf(currentIndex));
										currentIndex++;
									}
								}
							} else {
								if (operand.contains(" dup") && operand.contains("(")) {
									int y = getMul(operand);
									String s = operand;
									s = s.substring(s.indexOf('(') + 1);
									String oper = dupExtract(s);
									currentIndex = indexWordDup(indexes, oper, y, currentIndex, var, true);
								} else if (operand.contains("'")) {
									operand = operand.replace("'", "");
									for (int io = 0; io < operand.length(); io++) {
										if (io == 0) {
											indexes.put(currentIndex, var + 'w');
										} else {
											indexes.put(currentIndex, String.valueOf(currentIndex));
										}
										currentIndex++;
										indexes.put(currentIndex, String.valueOf(currentIndex));
										currentIndex++;
									}
								} else {
									indexes.put(currentIndex, var + 'w');
									currentIndex++;
									indexes.put(currentIndex, String.valueOf(currentIndex));
									currentIndex++;
								}
							}
						}
					}
					currentIndex = 0;
					scan.close();
					scan = new Scanner(text);
					for (int i = 0; i < line; i++) {
						String codeLine = scan.nextLine();
						int mm = 0;
						for (; codeLine.charAt(mm) != ' '; mm++)
							;
						codeLine = codeLine.substring(mm);
						codeLine = codeLine.toLowerCase();
						if(codeLine.contains(";"))
							codeLine = codeLine.substring(0, codeLine.indexOf(";"));
						if (codeLine.contains(".code"))
							break;

						if (codeLine.toLowerCase().contains(" db ")) {
							String var = codeLine.substring(0, codeLine.indexOf(" db "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							String operand = codeLine.substring(codeLine.indexOf(" db ") + " db ".length());
							if (operand.contains(",")) {
								String[] operands = operand.split(",");
								for (int f = 0; f < operands.length; f++) {
									operands[f] = operands[f].trim();
									if (operands[f].contains(" dup") && operands[f].contains("(")) {
										boolean isFirst = f == 0;
										String[] str = new String[operands.length - f];
										for (int y = f; y < operands.length; y++)
											str[y - f] = operands[y];
										String s = arrayToString(str);
										int y = getMul(s);
										s = s.substring(s.indexOf('(') + 1);
										String oper = dupExtract(s);
										currentIndex = varByteDup(indexes, oper, y, currentIndex, var, isFirst,
												variables);
										f = f + nextIndex(str);
									} else {
										if (operands[f].contains("'")) {
											operands[f] = operands[f].replace("'", "");
											for (int io = 0; io < operands[f].length(); io++) {
												if (f == 0 && io == 0) {
													variables.put(var + 'b', (int) operands[f].charAt(io));
												} else
													variables.put(String.valueOf(currentIndex),
															(int) operands[f].charAt(io));
												currentIndex++;
											}
										} else {
											if (operands[f].contains("[") || operands[f].contains("+") || operands[f].contains("-")) {
												int ind = operandToIndex(variables, indexes, operands[f], equs);
												if (f == 0) {
													variables.put(var + 'b', ind);
												} else {
													variables.put(String.valueOf(currentIndex), ind);
												}
												currentIndex++;
											} else { 
												if (operands[f].contains("offset ")) {
												operands[f] = operands[f]
														.substring(operands[f].indexOf("offset ") + "offset ".length())
														.trim();
											}
											if (f == 0) {
												if (operands[f].equals("?")) {
													variables.put(var + 'b', 0);
												} else if (indexes.containsValue(operands[f] + 'b')
														|| indexes.containsValue(operands[f] + 'w')) {
													for (Integer in : indexes.keySet())
														if (indexes.get(in).equals(operands[f] + 'b')
																|| indexes.get(in).equals(operands[f] + 'w')) {
															variables.put(var + 'b', in);
															break;
														}
												} else
													variables.put(var + 'b', getValue(operands[f]));
											} else {
												if (operands[f].equals("?")) {
													variables.put(String.valueOf(currentIndex), 0);
												} else if (indexes.containsValue(operands[f] + 'b')
														|| indexes.containsValue(operands[f] + 'w')) {
													for (Integer in : indexes.keySet())
														if (indexes.get(in).equals(operands[f] + 'b')
																|| indexes.get(in).equals(operands[f] + 'w')) {
															variables.put(String.valueOf(currentIndex), in);
															break;
														}
												} else
													variables.put(String.valueOf(currentIndex), getValue(operands[f]));
											}
											currentIndex++;
										}
									}
									}
								}
							} else {
								if (operand.contains(" dup") && operand.contains("(")) {
									int y = getMul(operand);
									String s = operand;
									s = s.substring(s.indexOf('(') + 1);
									String oper = dupExtract(s);
									currentIndex = varByteDup(indexes, oper, y, currentIndex, var, true, variables);
								} else {
									if (operand.contains("'")) {
										operand = operand.replace("'", "");
										for (int io = 0; io < operand.length(); io++) {
											if (io == 0) {
												variables.put(var + 'b', (int) operand.charAt(io));
											} else
												variables.put(String.valueOf(currentIndex), (int) operand.charAt(io));
											currentIndex++;
										}
									} else {
										if (operand.contains("offset ")) {
											operand = operand.substring(operand.indexOf("offset ") + "offset ".length())
													.trim();
										}
										if (operand.contains("[") || operand.contains("+") || operand.contains("-")) {
											int ind = operandToIndex(variables, indexes, operand, equs);
											variables.put(var + 'b', ind);
											currentIndex++;
										} else if (operand.equals("?")) {
											variables.put(var + 'b', 0);
										} else if (indexes.containsValue(operand + 'b')
												|| indexes.containsValue(operand + 'w')) {
											for (Integer in : indexes.keySet())
												if (indexes.get(in).equals(operand + 'b')
														|| indexes.get(in).equals(operand + 'w')) {
													variables.put(var + 'b', in);
													break;
												}
										} else
											variables.put(var + 'b', getValue(operand));
										currentIndex++;
									}
								}
							}
						}
						if (codeLine.toLowerCase().contains(" dw ")) {
							String var = codeLine.substring(0, codeLine.indexOf(" dw "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							String operand = codeLine.substring(codeLine.indexOf(" dw ") + " dw ".length());
							operand = operand.trim();
							if (operand.contains(",")) {
								String[] operands = operand.split(",");
								for (int f = 0; f < operands.length; f++) {
									operands[f] = operands[f].trim();
									if (operands[f].contains(" dup") && operands[f].contains("(")) {
										boolean isFirst = f == 0;
										String[] str = new String[operands.length - f];
										for (int y = f; y < operands.length; y++)
											str[y - f] = operands[y];
										String s = arrayToString(str);
										int y = getMul(s);
										s = s.substring(s.indexOf('(') + 1);
										String oper = dupExtract(s);
										currentIndex = varWordDup(indexes, oper, y, currentIndex, var, isFirst,
												variables);
										f = f + nextIndex(str);
									} else {
										if (operands[f].contains("'")) {
											operands[f] = operands[f].replace("'", "");
											for (int io = 0; io < operands[f].length(); io++) {
												int value = (int) operands[f].charAt(io);
												int[] arr = getByteValues(value);
												value = arr[0];
												int high = arr[1];
												if (f == 0 && io == 0) {
													variables.put(var + 'w', value);
												} else
													variables.put(String.valueOf(currentIndex), value);
												currentIndex++;
												variables.put(String.valueOf(currentIndex), high);
												currentIndex++;
											}
										} else {
											int value = 0;
											int high = 0;
											if (operands[f].contains("[") || operands[f].contains("+") || operands[f].contains("-")) {
												int[] arr = getByteValues(operandToIndex(variables, indexes, operands[f], equs));
												value = arr[0];
												high = arr[1];
												if (f == 0) {
													variables.put(var + 'w', value);
												} else {
													variables.put(String.valueOf(currentIndex), value);
												}
											} else {
											if (operands[f].contains("offset ")) {
												operands[f] = operands[f]
														.substring(operands[f].indexOf("offset ") + "offset ".length())
														.trim();
											}
											if (f == 0) {
												if (operands[f].equals("?")) {
													variables.put(var + 'w', 0);
												} else if (indexes.containsValue(operands[f] + 'b')
														|| indexes.containsValue(operands[f] + 'w')) {
													for (Integer in : indexes.keySet())
														if (indexes.get(in).equals(operands[f] + 'b')
																|| indexes.get(in).equals(operands[f] + 'w')) {
															variables.put(var + 'w', in);
															break;
														}
												} else {
													value = getValue(operands[f]);
													int[] arr = getByteValues(value);
													value = arr[0];
													high = arr[1];
													variables.put(var + 'w', value);
												}
											} else {
												if (operands[f].equals("?")) {
													variables.put(String.valueOf(currentIndex), 0);
												} else if (indexes.containsValue(operands[f] + 'b')
														|| indexes.containsValue(operands[f] + 'w')) {
													for (Integer in : indexes.keySet())
														if (indexes.get(in).equals(operands[f] + 'b')
																|| indexes.get(in).equals(operands[f] + 'w')) {
															variables.put(String.valueOf(currentIndex), in);
															break;
														}
												} else {
													value = getValue(operands[f]);
													int[] arr = getByteValues(value);
													value = arr[0];
													high = arr[1];
													variables.put(String.valueOf(currentIndex), value);
												}
											}
											}
											currentIndex++;
											variables.put(String.valueOf(currentIndex), high);
											currentIndex++;
										}
									}
								}
							} else {
								if (operand.contains("offset ")) {
									operand = operand.substring(operand.indexOf("offset ") + "offset ".length()).trim();
								}
								if (operand.contains(" dup") && operand.contains("(")) {
									int y = getMul(operand);
									String s = operand;
									s = s.substring(s.indexOf('(') + 1);
									String oper = dupExtract(s);
									currentIndex = varWordDup(indexes, oper, y, currentIndex, var, true, variables);
								} else {
									if (operand.contains("'")) {
										operand = operand.replace("'", "");
										for (int io = 0; io < operand.length(); io++) {
											int value = (int) operand.charAt(io);
											int[] arr = getByteValues(value);
											value = arr[0];
											int high = arr[1];
											if (io == 0) {
												variables.put(var + 'w', value);
											} else
												variables.put(String.valueOf(currentIndex), value);
											currentIndex++;
											variables.put(String.valueOf(currentIndex), high);
											currentIndex++;
										}
									} else {
										int value = 0;
										int high = 0;
										if (operand.contains("[") || operand.contains("+") || operand.contains("-")) {
											int ind = operandToIndex(variables, indexes, operand, equs);
											variables.put(var + 'w', ind);
										} else if (operand.equals("?")) {
											variables.put(var + 'w', 0);
										} else if (indexes.containsValue(operand + 'b')
												|| indexes.containsValue(operand + 'w')) {
											for (Integer in : indexes.keySet())
												if (indexes.get(in).equals(operand + 'b')
														|| indexes.get(in).equals(operand + 'w')) {
													variables.put(var + 'w', in);
													break;
												}
										} else {
											value = getValue(operand);
											int[] arr = getByteValues(value);
											value = arr[0];
											high = arr[1];
											variables.put(var + 'w', getValue(operand));
										}
										currentIndex++;
										variables.put(String.valueOf(currentIndex), high);
										currentIndex++;
									}
								}
							}
						}
					}
					currentIndex = 0;
					scan.close();
					String[] lines = text.split("\n");
					for (int i = 0; i < line; i++) {
						String codeLine = lines[i];
						int mm = 0;
						for (; codeLine.charAt(mm) != ' '; mm++)
							;
						codeLine = codeLine.substring(mm);
						codeLine = codeLine.toLowerCase();
						if(codeLine.contains(";"))
							codeLine = codeLine.substring(0, codeLine.indexOf(";"));
						if (codeLine.contains(".exit"))
							break;
						if (codeLine.contains(" pop ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" pop ") + " pop ".length());
							operand = operand.trim();
							operand = deletePtr(operand);
							if (operand.contains("+") || operand.contains("[") || operand.contains("-")) {
								int ind = operandToIndex(variables, indexes, operand, equs);
								if (!isMahsanit(operand)) {
									int low = stack.pop();
									int high = stack.pop();
									variables.put(indexes.get(ind), low);
									variables.put(indexes.get(ind+1), high);
								} else {
									ind = 255 - ind;
									int low = stack.pop();
									int high = stack.pop();
									int value = numberJoiner(low, high);
									int[] arr = getByteValues(value);
									value = arr[0];
									int high1 = arr[1];
									stack.setElementAt(value, ind);
									stack.setElementAt(high1, ind - 1);
								}
							} else {
								int low = stack.pop();
								int high = stack.pop();
								if (isRegister(operand)) {
									insertRegister(operand, numberJoiner(low, high), variables);
								} else {
									setVariable(operand, variables, low);
									variables.put(indexes.get(getOffset(operand, indexes) + 1), high);
								}
							}
							insertRegister("sp", getRegisterValue("sp", variables)+2, variables);
						}
						
						else if (codeLine.contains(" neg ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" neg ") + " neg ".length());
							operand = operand.trim();
							operand = operand.trim();
							String str = operand;
							boolean isWord = isWord(variables, operand);
							operand = deletePtr(operand);
							String value = getWordStr(getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0]);
							Mov(str, "0", variables, indexes, equs, stack);
							Sub(str, value+"b", variables, indexes, stack, flags, equs, false);
						}
						
						else if (codeLine.contains(" mov ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" mov ") + " mov ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							Mov(operands[0], operands[1], variables, indexes, equs, stack);
						}
						else if(codeLine.contains(" cmp ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" cmp ")+" cmp ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							Boolean isWord = isWord(variables, operands[0], operands[1]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int operand1 = 0;
							int operand2 = 0;
							operand1 = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							operand2 = getOperandValueIsIndex(operands[1], variables, indexes, equs, isWord, stack)[0];

							if(isWord) {
								if(operand1>=0 && operand1<32678 && operand2>=0 && operand2<32678) {
									if(operand1-operand2 < 0 || operand1-operand2 >= 32678) {
										flags.put('o', 1);
									}
									else {
										flags.put('o', 0);
									}
								}
								else if(operand1<0 || operand1>=32678 && operand2<0 || operand2>=32678) {
									if(operand1-operand2 >= 0 || operand1-operand2 < 32678) {
										flags.put('o', 1);
									}
									else {
										flags.put('o', 0);
									}
								}
								if(operand1>operand2) {
									flags.put('c', 1);
								}
								else
									flags.put('c', 0);
								if(operand1-operand2 == 0)
									flags.put('z', 1);
								else
									flags.put('z', 0);
								if(operand1-operand2<0 || operand1-operand2>=32678)
									flags.put('s', 1);
								else
									flags.put('s', 0);
							}
							else {
								int[] arr = getByteValues(operand1);
								int[] arr2 = getByteValues(operand2);
								operand1 = arr[0];
								operand2 = arr2[0];
								if(operand1>=0 && operand1<128 && operand2>=0 && operand2<128) {
									if(operand1-operand2 < 0 || operand1-operand2 >= 32678) {
										flags.put('o', 1);
									}
									else {
										flags.put('o', 0);
									}
								}
								else if(operand1<0 || operand1>=128 && operand2<0 || operand2>=128) {
									if(operand1-operand2 >= 0 || operand1-operand2 < 128) {
										flags.put('o', 1);
									}
									else {
										flags.put('o', 0);
									}
								}
								if(operand1>operand2) {
									flags.put('c', 1);
								}
								else
									flags.put('c', 0);
								if(operand1-operand2 == 0)
									flags.put('z', 1);
								else
									flags.put('z', 0);
								if(operand1-operand2<0 || operand1-operand2>=128)
									flags.put('s', 1);
								else
									flags.put('s', 0);
							}
						}
						
						else if(codeLine.contains(" jmp ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jmp ") + " jmp ".length());
							operand = operand.trim();
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" rol ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" rol ") + " rol ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int value = 0;
							int times = 0;
							if (operands[1].equals("cl"))
								times = getLowRegisterValue("cl", variables);
							else if (equs.containsKey(operands[1])) {
								times = equs.get(operands[1]);
							} else
								times = getValue(operands[1]);
							value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							for(int ii = 0; ii<times; ii++) {
								int temp = value;
								int[] arr = Shl(value, isWord);
								value = arr[0];
								flags.put('c', arr[1]);
								String s = "";
								if(isWord) {
								s = getWordStr(value);
								s = s.substring(0, 15);
								s +=flags.get('c');
								if(s.charAt(0) != getWordStr(temp).charAt(0))
									flags.put('o', 1);
								else
									flags.put('o', 0);
								}
								else {
									s = getWordStr(value).substring(8);
									s = s.substring(0, 7);
									s +=flags.get('c');
									if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
								}
								value = getValue(s+'b');
							}
							Mov(str, value+"", variables, indexes, equs, stack);
						}
						
						else if(codeLine.contains(" ror ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" ror ") + " ror ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int value = 0;
							int times = 0;
							if (operands[1].equals("cl"))
								times = getLowRegisterValue("cl", variables);
							else if (equs.containsKey(operands[1])) {
								times = equs.get(operands[1]);
							} else
								times = getValue(operands[1]);
							value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							for(int ii = 0; ii<times; ii++) {
								int temp = value;
								int[] arr = Shr(value, isWord);
								value = arr[0];
								flags.put('c', arr[1]);
								String s = "";
								if(isWord) {
								s = getWordStr(value);
								s = s.substring(1);
								s = flags.get('c') + s;
								if(s.charAt(0) != getWordStr(temp).charAt(0))
									flags.put('o', 1);
								else
									flags.put('o', 0);
								}
								else {
									s = getWordStr(value).substring(8);
									s = s.substring(1);
									s = flags.get('c') + s;
									if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
								}
								value = getValue(s+'b');
							}
							Mov(str, value+"", variables, indexes, equs, stack);
						}
						
						else if(codeLine.contains(" rcl ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" rcl ") + " rcl ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int value = 0;
							int times = 0;
							if (operands[1].equals("cl"))
								times = getLowRegisterValue("cl", variables);
							else if (equs.containsKey(operands[1])) {
								times = equs.get(operands[1]);
							} else
								times = getValue(operands[1]);
							value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							for(int ii = 0; ii<times; ii++) {
								int temp = value;
								int temp2 = flags.get('c');
								int[] arr = Shl(value, isWord);
								value = arr[0];
								flags.put('c', arr[1]);
								String s = "";
								if(isWord) {
								s = getWordStr(value);
								s = s.substring(0, 15);
								s += temp2;
								if(s.charAt(0) != getWordStr(temp).charAt(0))
									flags.put('o', 1);
								else
									flags.put('o', 0);
								}
								else {
									s = getWordStr(value).substring(8);
									s = s.substring(0, 7);
									s +=flags.get('c');
									if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
								}
								value = getValue(s+'b');
							}
							Mov(str, value+"", variables, indexes, equs, stack);
						}
						
						else if(codeLine.contains(" rcr ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" rcr ") + " rcr ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int value = 0;
							int times = 0;
							if (operands[1].equals("cl"))
								times = getLowRegisterValue("cl", variables);
							else if (equs.containsKey(operands[1])) {
								times = equs.get(operands[1]);
							} else
								times = getValue(operands[1]);
							value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							for(int ii = 0; ii<times; ii++) {
								int temp = value;
								int temp2 = flags.get('c');
								int[] arr = Shr(value, isWord);
								value = arr[0];
								flags.put('c', arr[1]);
								String s = "";
								if(isWord) {
								s = getWordStr(value);
								s = s.substring(1);
								s = temp2 + s;
								if(s.charAt(0) != getWordStr(temp).charAt(0))
									flags.put('o', 1);
								else
									flags.put('o', 0);
								}
								else {
									s = getWordStr(value).substring(8);
									s = s.substring(1);
									s = flags.get('c') + s;
									if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
								}
								value = getValue(s+'b');
							}
							Mov(str, value+"", variables, indexes, equs, stack);
						}
						
						else if(codeLine.contains(" mul ")) {

							String operand = codeLine.substring(codeLine.lastIndexOf(" mul ")+" mul ".length()).trim();
							boolean isWord = isWord(variables, operand);
							int value = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0];
							int mul = getRegisterValue("al", variables);
							if(isWord) {

							mul = getRegisterValue("ax", variables); 

							if(mul < 0){ 

							mul = 65536 + mul; 

							} 

							if(value < 0){ 

							value = 65536+value; 

							}
							if(mul*value == 0) {
								flags.put('z', 1);
							}
							else {
								flags.put('z', 0);
							}
							if(mul*value>65535) {
								flags.put('c', 1);
								flags.put('o', 1);
							}
							else {
								flags.put('c', 0);
								flags.put('o', 0);
							}
							int[] arr = getWordValues(mul*value);
							int ax = arr[0];
							int dx = arr[1];
							insertRegister("ax", ax, variables);
							insertRegister("dx", dx, variables);
							} 
							else { 

							if(mul < 0){ 

							mul = 256 + mul; 

							} 

							if(value < 0) {
							value = 256+value; 

							}
							if(mul*value == 0) {
								flags.put('z', 1);
							}
							else {
								flags.put('z', 0);
							}
							if(mul*value>255) {
								flags.put('c', 1);
								flags.put('o', 1);
							}
							else {
								flags.put('c', 0);
								flags.put('o', 0);
							}
							int ax = mul*value;
							insertRegister("ax", ax, variables);
							}

							}
						
						else if(codeLine.contains(" imul ")) {

							String operand = codeLine.substring(codeLine.lastIndexOf(" imul ")+" imul ".length()).trim();
							boolean isWord = isWord(variables, operand);
							int value = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0];
							int mul = getRegisterValue("al", variables);
							if(isWord) {

							mul = getRegisterValue("ax", variables); 

							if(mul > 32767){ 

							mul = -1*(65536 - mul); 

							} 

							if(value > 32767){ 

							value = -1*(65536-value); 

							}
							if(mul*value == 0) {
								flags.put('z', 1);
							}
							else {
								flags.put('z', 0);
							}
							if(mul*value<65535) {
								flags.put('c', 1);
								flags.put('o', 1);
							}
							else {
								flags.put('c', 0);
								flags.put('o', 0);
							}
							int[] arr = getWordValues(mul*value);
							int ax = arr[0];
							int dx = arr[1];
							insertRegister("ax", ax, variables);
							insertRegister("dx", dx, variables);
							} 
							else { 

							if(mul > 127){ 

							mul = -1*(256 - mul); 

							} 

							if(value > 127) {
							value = -1*(256-value); 

							}
							if(value > 127) {
								value = -1*(256-value); 

							}
							if(mul*value == 0) {
								flags.put('z', 1);
							}
							else {
								flags.put('z', 0);
							}
								if(mul*value>255) {
									flags.put('c', 1);
									flags.put('o', 1);
								}
								else {
									flags.put('c', 0);
									flags.put('o', 0);
								}
							int ax = mul*value;
							insertRegister("ax", ax, variables);
							} 

							}
						
						else if(codeLine.contains(" div ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" div ") + " div ".length());
							operand = operand.trim();
							boolean isWord = isWord(variables, operand);
							operand = deletePtr(operand);
							int value = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0];
							int ax = getRegisterValue("ax", variables);
							if(isWord) {
								if(value<0) {
									value = 65536 + value;
								}
								ax = numberJoiner(ax, getRegisterValue("dx", variables));
								if(ax<0) {
									ax = 65536*2 + ax;
								}
								insertRegister("ax", ax/value, variables);
								insertRegister("dx", ax%value, variables);
							}
							else {
								if(value<0) {
									value = 256 + value;
								}
								if(ax<0) {
									ax = 65536 + ax;
								}
								insertRegister("al", ax/value, variables);
								insertRegister("ah", ax%value, variables);
							}
						}
						
						else if(codeLine.contains(" idiv ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" idiv ") + " idiv ".length());
							operand = operand.trim();
							boolean isWord = isWord(variables, operand);
							operand = deletePtr(operand);
							int value = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0];
							int ax = getRegisterValue("ax", variables);
							if(isWord) {
								if(value>32767) {
									value = -1*(65536 - value);
								}
								ax = numberJoiner(ax, getRegisterValue("dx", variables));
								if(ax>65535) {
									ax = -1*(65536*2 - ax);
								}
								insertRegister("ax", ax/value, variables);
								insertRegister("dx", ax%value, variables);
							}
							else {
								if(value>127) {
									value = -1*(256 - value);
								}
								if(ax>32767) {
									ax = -1*(65536 - ax);
								}
								insertRegister("al", ax/value, variables);
								insertRegister("ah", ax%value, variables);
							}
						}
						
						else if(codeLine.contains(" jz ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jz ") + " jz ".length());
							operand = operand.trim();
							if(flags.get('z')==1)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jnz ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jnz ") + " jnz ".length());
							operand = operand.trim();
							if(flags.get('z')==0)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jc ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jc ") + " jc ".length());
							operand = operand.trim();
							if(flags.get('c')==1)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jnc ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jnc ") + " jnc ".length());
							operand = operand.trim();
							if(flags.get('c')==0)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" js ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" js ") + " js ".length());
							operand = operand.trim();
							if(flags.get('s')==1)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jns ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jns ") + " jns ".length());
							operand = operand.trim();
							if(flags.get('s')==0)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jo ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jo ") + " jo ".length());
							operand = operand.trim();
							if(flags.get('o')==1)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jno ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jno ") + " jno ".length());
							operand = operand.trim();
							if(flags.get('o')==0)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jb ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jb ") + " jb ".length());
							operand = operand.trim();
							if(flags.get('c')==1)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jbe ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jbe ") + " jbe ".length());
							operand = operand.trim();
							if(flags.get('z')==1 || flags.get('c')==1)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jl ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jl ") + " jl ".length());
							operand = operand.trim();
							if(flags.get('s')!=flags.get('o'))
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jle ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jle ") + " jle ".length());
							operand = operand.trim();
							if(flags.get('z')==1 || flags.get('s')!=flags.get('o'))
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" ja ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" ja ") + " ja ".length());
							operand = operand.trim();
							if(flags.get('c')==0)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jae ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jae ") + " jae ".length());
							operand = operand.trim();
							if(flags.get('z')==1 || flags.get('c')==0)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jg ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jg ") + " jg ".length());
							operand = operand.trim();
							if(flags.get('o')==flags.get('s'))
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jge ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jge ") + " jge ".length());
							operand = operand.trim();
							if(flags.get('z')==1 || (flags.get('s')==flags.get('o')))
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" je ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" je ") + " je ".length());
							operand = operand.trim();
							if(flags.get('z')==1)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" jne ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" jne ") + " jne ".length());
							operand = operand.trim();
							if(flags.get('z')==0)
							i = findJmp(operand, text);
						}
						
						else if(codeLine.contains(" inc ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" inc ") + " inc ".length());
							operand = operand.trim();
							Add(operand, "1", variables, indexes, stack, flags, equs, false);
						}
						
						else if(codeLine.contains(" call ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" call ") + " call ".length());
							operand = operand.trim();
							stack.push(0);
							stack.push(70000);
							insertRegister("sp", getRegisterValue("sp", variables)-2, variables);
							boolean is = false;
							for(int f = 0; f<lines.length; f++) {
								String s = lines[f];
								int m = 0;
								for (; s.charAt(m) != ' '; m++)
									;
								s = s.toLowerCase();
								s = s.substring(m);
								if(s.contains(" end "))
									break;
								if (s.contains(".exit"))
									is = true;
								if(is) {
									if(s.contains("proc ") && s.contains(operand + " ") && s.contains(" near")) {
										try {
											procedure(text, f, variables, equs, indexes, stack, flags, line, operand);
											}
											catch (Exception e) {
												errors.setText("There were errors while scanning the procedure " + operand);
												return;
											}
										}
								}
								}
							
						}
						
						else if(codeLine.contains(" xchg ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" xchg ") + " xchg ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							boolean isWord = isWord(variables, operands[0], operands[1]);
							operands[0] = deletePtr(operands[0]);
							int value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							Mov(str, operands[1], variables, indexes, equs, stack);
							Mov(operands[1], value+"", variables, indexes, equs, stack);
						}
						else if (codeLine.contains(" ret ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" ret ") + " ret ".length());
							operand = operand.trim();
							stack.pop();
							stack.pop();
							if(!operand.equals("")) {
							for (int m = 0; m < getValue(operand); m++)
								stack.pop();
							}
							insertRegister("sp", getRegisterValue("sp", variables)+getValue(operand), variables);
						}
						else if (codeLine.contains(" shl ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" shl ") + " shl ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int value = 0;
							int times = 0;
							if (operands[1].equals("cl"))
								times = getLowRegisterValue("cl", variables);
							else if (equs.containsKey(operands[1])) {
								times = equs.get(operands[1]);
							} else
								times = getValue(operands[1]);
							value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							for (int t = 0; t < times; t++) {
								int temp = value;
								int[] arr = Shl(value, isWord);
								value = arr[0];
								flags.put('c', arr[1]);
								if(isWord) {
									String s = getWordStr(value);
									if(s.charAt(0) != getWordStr(temp).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
									if(value == 0)
										flags.put('z', 1);
									else
										flags.put('z', 0);
									if(s.charAt(0) == 1)
										flags.put('s', 1);
									else
										flags.put('s', 0);
								}
								else {
									String s = getWordStr(value).substring(8);
									if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
									if(value == 0)
										flags.put('z', 1);
									else
										flags.put('z', 0);
									if(s.charAt(0) == 1)
										flags.put('s', 1);
									else
										flags.put('s', 0);
								}
							}
							Mov(str, String.valueOf(value), variables, indexes, equs, stack);
						}
						
						else if (codeLine.contains(" sal ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" sal ") + " sal ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int value = 0;
							int times = 0;
							if (operands[1].equals("cl"))
								times = getLowRegisterValue("cl", variables);
							else if (equs.containsKey(operands[1])) {
								times = equs.get(operands[1]);
							} else
								times = getValue(operands[1]);
							value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							for (int t = 0; t < times; t++) {
								int temp = value;
								int[] arr = Shl(value, isWord);
								value = arr[0];
								flags.put('c', arr[1]);
								if(isWord) {
									String s = getWordStr(value);
									if(s.charAt(0) != getWordStr(temp).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
									if(value == 0)
										flags.put('z', 1);
									else
										flags.put('z', 0);
									if(s.charAt(0) == 1)
										flags.put('s', 1);
									else
										flags.put('s', 0);
								}
								else {
									String s = getWordStr(value).substring(8);
									if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
									if(value == 0)
										flags.put('z', 1);
									else
										flags.put('z', 0);
									if(s.charAt(0) == 1)
										flags.put('s', 1);
									else
										flags.put('s', 0);
								}
							}
							Mov(str, String.valueOf(value), variables, indexes, equs, stack);
						}

						else if (codeLine.contains(" shr ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" shr ") + " shr ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int value = 0;
							int times = 0;
							if (operands[1].equals("cl"))
								times = getLowRegisterValue("cl", variables);
							else if (equs.containsKey(operands[1])) {
								times = equs.get(operands[1]);
							} else
								times = getValue(operands[1]);
							value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							for (int t = 0; t < times; t++) {
								int temp = value;
								int[] arr = Shr(value, isWord);
								value = arr[0];
								flags.put('c', arr[1]);
								if(isWord) {
									String s = getWordStr(value);
									if(s.charAt(0) != getWordStr(temp).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
									if(value == 0)
										flags.put('z', 1);
									else
										flags.put('z', 0);
									if(s.charAt(0) == 1)
										flags.put('s', 1);
									else
										flags.put('s', 0);
								}
								else {
									String s = getWordStr(value).substring(8);
									if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
									if(value == 0)
										flags.put('z', 1);
									else
										flags.put('z', 0);
									if(s.charAt(0) == 1)
										flags.put('s', 1);
									else
										flags.put('s', 0);
								}
							}
							Mov(str, String.valueOf(value), variables, indexes, equs, stack);
						}
						
						else if (codeLine.contains(" sar ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" sar ") + " sar ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							int value = 0;
							int times = 0;
							if (operands[1].equals("cl"))
								times = getLowRegisterValue("cl", variables);
							else if (equs.containsKey(operands[1])) {
								times = equs.get(operands[1]);
							} else
								times = getValue(operands[1]);
							value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
							for (int t = 0; t < times; t++) {
								int temp = value;
								int[] arr = Sar(value, isWord);
								value = arr[0];
								flags.put('c', arr[1]);
								if(isWord) {
									String s = getWordStr(value);
									if(s.charAt(0) != getWordStr(temp).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
									if(value == 0)
										flags.put('z', 1);
									else
										flags.put('z', 0);
									if(s.charAt(0) == 1)
										flags.put('s', 1);
									else
										flags.put('s', 0);
								}
								else {
									String s = getWordStr(value).substring(8);
									if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
										flags.put('o', 1);
									else
										flags.put('o', 0);
									if(value == 0)
										flags.put('z', 1);
									else
										flags.put('z', 0);
									if(s.charAt(0) == 1)
										flags.put('s', 1);
									else
										flags.put('s', 0);
								}
							}
							Mov(str, String.valueOf(value), variables, indexes, equs, stack);
						}

						else if (codeLine.contains(" push ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" push ") + " push ".length());
							operand = operand.trim();
							operand = deletePtr(operand);
							int value = getOperandValueIsIndex(operand, variables, indexes, equs, true, stack)[0];
							if(value == 80000) {
								stack.push(0);
								stack.push(80000);
							}
							else {
							int[] arr2 = getByteValues(value);
							value = arr2[0];
							int high = arr2[1];
							stack.push(high);
							stack.push(value);
							insertRegister("sp", getRegisterValue("sp", variables)-2, variables);
							}
						}
						
						else if(codeLine.contains(" and ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" and ") + " and ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0], operands[1]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							String o1 = getWordStr(getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0]);
							String o2 = getWordStr(getOperandValueIsIndex(operands[1], variables, indexes, equs, isWord, stack)[0]);
							if(!isWord) {
								o1 = o1.substring(8);
								o2 = o2.substring(8);
							}
							int ii = 7;
					        if (isWord)
					            ii += 8;

					        String after = "";
					        for (int k = 0; k <= ii; k++) {
					            char bit = '0';
					            if (o1.charAt(k) == o2.charAt(k) && o1.charAt(k) == '1')
					                bit = '1';

					            after = after + bit;
					        }
					        Mov(str, after+'b', variables, indexes, equs, stack);

					        if ((isWord && after.equals("0000000000000000")) || (!(isWord) && after.equals("00000000")))
					            flags.put('z', 1);
					        else
					            flags.put('z', 0);
					        if (after.charAt(0) == '1')
					            flags.put('s', 1);
					        else
					            flags.put('s', 0);
						}
						
						else if(codeLine.contains(" or ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" or ") + " or ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0], operands[1]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							String o1 = getWordStr(getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0]);
							String o2 = getWordStr(getOperandValueIsIndex(operands[1], variables, indexes, equs, isWord, stack)[0]);
							if(!isWord) {
								o1 = o1.substring(8);
								o2 = o2.substring(8);
							}
							int ii = 7;
					        if (isWord)
					            ii += 8;

					        String after = "";
					        for (int k = 0; k <= ii; k++) {
					            char bit = '0';
					            if (o1.charAt(k) == '1' || o2.charAt(k) == '1')
					                bit = '1';

					            after = after + bit;
					        }
					        Mov(str, after+'b', variables, indexes, equs, stack);
					        if ((isWord && after.equals("0000000000000000")) || (!(isWord) && after.equals("00000000")))
					            flags.put('z', 1);
					        else
					            flags.put('z', 0);
					        if (after.charAt(0) == '1')
					            flags.put('s', 1);
					        else
					            flags.put('s', 0);
						}
						
						else if(codeLine.contains(" xor ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" xor ") + " xor ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							String str = operands[0];
							Boolean isWord = isWord(variables, operands[0], operands[1]);
							operands[0] = deletePtr(operands[0]);
							operands[1] = deletePtr(operands[1]);
							String o1 = getWordStr(getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0]);
							String o2 = getWordStr(getOperandValueIsIndex(operands[1], variables, indexes, equs, isWord, stack)[0]);
							if(!isWord) {
								o1 = o1.substring(8);
								o2 = o2.substring(8);
							}
							int ii = 7;
						        if (isWord)
						            ii += 8;

						        String after = "";
						        for (int k = 0; k <= ii; k++) {
						            char bit = '0';
						            if (o1.charAt(k) != o2.charAt(k))
						                bit = '1';

						            after = after + bit;
						        }
						        Mov(str, after+'b', variables, indexes, equs, stack);

						        if ((isWord && after.equals("0000000000000000")) || (!(isWord) && after.equals("00000000")))
						            flags.put('z', 1);
						        else
						            flags.put('z', 0);
						        if (after.charAt(0) == '1')
						            flags.put('s', 1);
						        else
						            flags.put('s', 0);
						}
						
						else if (codeLine.contains(" equ ")) {
							String operand = codeLine.substring(codeLine.indexOf(" equ ") + " equ ".length());
							operand = operand.trim();
							String var = codeLine.substring(0, codeLine.indexOf(" equ "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							boolean isOffset = false;
							if (operand.contains(" offset ")) {
								operand = operand.substring(operand.indexOf(" offset ") + " offset ".length()).trim();
								isOffset = true;
							}
							if (variables.containsKey(operand + 'b')) {
								if (isOffset) {
									equs.put(operand, getOffset(operand, indexes));
								} else
									equs.put(var, variables.get(operand + 'b'));
							} else if (variables.containsKey(operand + 'w')) {
								if (isOffset) {
									equs.put(operand, getOffset(operand, indexes));
								} else
									equs.put(var, variables.get(operand + 'w'));
							} else
								equs.put(var, getValue(operand));
						}

						else if (codeLine.contains(" add ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" add ") + " add ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							Add(operands[0], operands[1], variables, indexes, stack, flags, equs, false);
						}
						else if (codeLine.contains(" adc ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" adc ") + " adc ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							Add(operands[0], operands[1], variables, indexes, stack, flags, equs, true);
						}
						
						else if (codeLine.contains(" sub ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" sub ") + " sub ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							Sub(operands[0], operands[1], variables, indexes, stack, flags, equs, false);
						}
						
						else if (codeLine.contains(" not ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" not ") + " not ".length());
							operand = operand.trim();
							String str = operand;
							Boolean isWord = isWord(variables, operand);
							operand = deletePtr(operand);
							String s = getWordStr(getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0]);
							String s2 = "";
							if(isWord) {
								for(int is = 0; is<s.length(); is++)
									if(s.charAt(is) == '0') {
										s2+='1';
									}
									else
										s2+='0';
							}
							else {
								s = s.substring(8);
								for(int is = 0; is<s.length(); is++)
									if(s.charAt(is) == '0') {
										s2+='1';
									}
									else
										s2+='0';
							}
							BigInteger b = new BigInteger(s2, 2);
							b.add(new BigInteger("1"));
							if(isWord) {
								s2 = getWordStr(b.intValue());
							}
							else {
								s2 = getWordStr(b.intValue()).substring(8);
							}
						    Mov(str, s2+'b', variables, indexes, equs, stack);
						    
						}
						
						else if (codeLine.contains(" dec ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" dec ") + " dec ".length());
							operand = operand.trim();
							Sub(operand, "1", variables, indexes, stack, flags, equs, false);
						}
						
						else if (codeLine.contains(" sub ")) {
							String operand = codeLine.substring(codeLine.lastIndexOf(" sub ") + " sub ".length());
							operand = operand.trim();
							String[] operands = operand.split(",");
							operands[0] = operands[0].trim();
							operands[1] = operands[1].trim();
							Sub(operands[0], operands[1], variables, indexes, stack, flags, equs, true);
						}
						
						else if (codeLine.contains(" cbw ")) {
							int value = getRegisterValue("al", variables);
							if(value<0 || value>127)
								Mov("ah", "255", variables, indexes, equs, stack);
							else
								Mov("ah", "0", variables, indexes, equs, stack);
						}
						
						else if (codeLine.contains(" cwd ")) {
							int value = getRegisterValue("ax", variables);
							if(value<0 || value>32767)
								Mov("dx", "65536", variables, indexes, equs, stack);
							else
								Mov("dx", "0", variables, indexes, equs, stack);
						}
						else if(codeLine.contains(" int ") && codeLine.contains("21")){

							if(getRegisterValue("ah", variables) == 9){
							
								SwingUtilities.invokeLater(new Runnable() {
									
									@Override
									public void run() {
										int copyDx = getRegisterValue("dx", variables); 

										while(true){ 

										int valueAt = variables.get(indexes.get(copyDx)); 

										if(valueAt < 0)
										valueAt = 256 + valueAt; 

										if(((char)valueAt) == '$')
										break; 
										print.setText(print.getText()+(char)valueAt);
										copyDx++;
										}
									}
								});
							}
							else if(getRegisterValue("ah", variables) == 2) {

							int t = getRegisterValue("dl", variables);

							if(t<0)
							t = 256 + t; 

							print.setText(print.getText()+(char)t);
							}
							}
						}
					try {
						if(Mahsanit) {
							ArrayList<String> bytes = new ArrayList<>();
							for (int i = 0; i < stack.size(); i++)
								bytes.add("");
							int n = 1;
							int size = stack.size();
							while(!stack.isEmpty()) {
								int value = stack.pop();
								if(value == 70000) {
									bytes.set(bytes.size()-n, "ip בית יותר משמעותי");
									bytes.set(bytes.size()-++n, "ip בית פחות משמעותי");
									stack.pop();
								}
								else if(value == 80000) {
									bytes.set(bytes.size()-n, "@data בית יותר משמעותי");
									bytes.set(bytes.size()-++n, "@data בית פחות משמעותי");
									stack.pop();
								}
								else {
								String s = Integer.toBinaryString(value);
								for (; s.length() < 8;)
									s = '0' + s;
								if (s.length() > 8)
									s = s.substring(s.length() - 8);
								bytes.set(bytes.size()-n, s);
								}
								n++;
							}
							String dataDir = System.getProperty("user.home") + "\\Desktop";
							File file = new File(dataDir + "\\TestDataTable.docx");
							file.createNewFile();
							Locale.setDefault(new Locale("en-us"));
							Document doc = new Document(dataDir + "\\TestDataTable.docx");
							Table table = new Table(doc);
							doc.getFirstSection().getBody().appendChild(table);
							Row firstRow = new Row(doc);
							table.appendChild(firstRow);

							Cell secondCell = new Cell(doc);
							firstRow.appendChild(secondCell);
							Paragraph paragraph2 = new Paragraph(doc);
							secondCell.appendChild(paragraph2);
							Run run2 = new Run(doc, "Value (binary)");
							paragraph2.appendChild(run2);

							Cell thirdCell = new Cell(doc);
							firstRow.appendChild(thirdCell);
							Paragraph paragraph3 = new Paragraph(doc);
							thirdCell.appendChild(paragraph3);
							Run run3 = new Run(doc, "Offset (hex)");
							paragraph3.appendChild(run3);

							for (int i = 0; i < size; i++) {
								Row nextRow = new Row(doc);
								table.appendChild(nextRow);

								Cell nextCell2 = new Cell(doc);
								nextRow.appendChild(nextCell2);
								Cell nextCell3 = new Cell(doc);
								nextRow.appendChild(nextCell3);
								Paragraph nextParagraph2 = new Paragraph(doc);
								nextCell2.appendChild(nextParagraph2);
								Paragraph nextParagraph3 = new Paragraph(doc);
								nextCell3.appendChild(nextParagraph3);
								String byteText = bytes.get(i);
								Run nextRun2 = new Run(doc, byteText);
								nextParagraph2.appendChild(nextRun2);
								String offsetText = new BigInteger((255-i) + "").toString(16);
								Run nextRun3 = new Run(doc, offsetText);
								nextParagraph3.appendChild(nextRun3);
							}
							doc.save(dataDir + "\\TestDataTable.docx");
						}
						else {
						ArrayList<String> bytes = new ArrayList<>();
						for (int i = 0; i < indexes.size(); i++)
							bytes.add("");
						for (int i = 0; i<indexes.size(); i++) {
							int value = variables.get(indexes.get(i));
							if(value == 70000) {
								bytes.set(i, "ip בית פחות משמעותי");
								bytes.set(i+1, "ip בית יותר משמעותי");
								i++;
							}
							else if(value == 80000) {
								bytes.set(i, "@data בית פחות משמעותי");
								bytes.set(i+1, "@data בית יותר משמעותי");
								i++;
							}
							else {
							String s = Integer.toBinaryString(value);
							for (; s.length() < 8;)
								s = '0' + s;
							if (s.length() > 8)
								s = s.substring(s.length() - 8);
							bytes.set(i, s);
							}
						}
						String dataDir = System.getProperty("user.home") + "\\Desktop";
						File file = new File(dataDir + "\\TestDataTable.docx");
						file.createNewFile();
						Locale.setDefault(new Locale("en-us"));
						Document doc = new Document(dataDir + "\\TestDataTable.docx");
						Table table = new Table(doc);
						doc.getFirstSection().getBody().appendChild(table);
						Row firstRow = new Row(doc);
						table.appendChild(firstRow);

						Cell firstCell = new Cell(doc);
						firstRow.appendChild(firstCell);

						Paragraph paragraph = new Paragraph(doc);
						firstCell.appendChild(paragraph);
						Run run = new Run(doc, "Variable Name");
						paragraph.appendChild(run);

						Cell secondCell = new Cell(doc);
						firstRow.appendChild(secondCell);
						Paragraph paragraph2 = new Paragraph(doc);
						secondCell.appendChild(paragraph2);
						Run run2 = new Run(doc, "Value (binary)");
						paragraph2.appendChild(run2);

						Cell thirdCell = new Cell(doc);
						firstRow.appendChild(thirdCell);
						Paragraph paragraph3 = new Paragraph(doc);
						thirdCell.appendChild(paragraph3);
						Run run3 = new Run(doc, "Offset (hex)");
						paragraph3.appendChild(run3);

						for (int i = 0; i < indexes.size(); i++) {
							Row nextRow = new Row(doc);
							table.appendChild(nextRow);

							Cell nextCell1 = new Cell(doc);
							nextRow.appendChild(nextCell1);
							Cell nextCell2 = new Cell(doc);
							nextRow.appendChild(nextCell2);
							Cell nextCell3 = new Cell(doc);
							nextRow.appendChild(nextCell3);
							Paragraph nextParagraph = new Paragraph(doc);
							nextCell1.appendChild(nextParagraph);
							Paragraph nextParagraph2 = new Paragraph(doc);
							nextCell2.appendChild(nextParagraph2);
							Paragraph nextParagraph3 = new Paragraph(doc);
							nextCell3.appendChild(nextParagraph3);
							String nameText = indexes.get(i).substring(0, indexes.get(i).length() - 1);
							try {
								Integer.parseInt(nameText);
								nameText = "";
							} catch (Exception e) {
							}
							Run nextRun = new Run(doc, nameText);
							nextParagraph.appendChild(nextRun);
							String byteText = bytes.get(i);
							Run nextRun2 = new Run(doc, byteText);
							nextParagraph2.appendChild(nextRun2);
							String offsetText = new BigInteger(i + "").toString(16);
							Run nextRun3 = new Run(doc, offsetText);
							nextParagraph3.appendChild(nextRun3);
						}
						doc.save(dataDir + "\\TestDataTable.docx");
						}
						errors.setText("The table created successfully!");
					} catch (Exception e) {
						errors.setText("There were errors while creating the table.");
					}
				} catch (Exception e) {
					errors.setText("There were errors while scanning the code.");
					scan.close();
				}
				
			}

		});
		t.start();

	}

	private void procedure(String text, int i, HashMap<String, Integer> variables, HashMap<String, Integer> equs, HashMap<Integer, String> indexes, Stack<Integer> stack, HashMap<Character, Integer> flags, int line, String name) {
		String[] lines = text.split("\n");
		for (; i != line; i++) {
			String codeLine = lines[i];
			int mm = 0;
			for (; codeLine.charAt(mm) != ' '; mm++)
				;
			codeLine = codeLine.substring(mm);
			codeLine = codeLine.toLowerCase();
			if(codeLine.contains(";"))
				codeLine = codeLine.substring(0, codeLine.indexOf(";"));
			if (codeLine.contains(" endp") && codeLine.contains(" "+name))
				break;
			if (codeLine.contains(" pop ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" pop ") + " pop ".length());
				operand = operand.trim();
				operand = deletePtr(operand);
				if (operand.contains("+") || operand.contains("[") || operand.contains("-")) {
					int ind = operandToIndex(variables, indexes, operand, equs);
					if (!isMahsanit(operand)) {
						int low = stack.pop();
						int high = stack.pop();
						variables.put(indexes.get(ind), low);
						variables.put(indexes.get(ind), high);
					} else {
						ind = 255 - ind;
						int low = stack.pop();
						int high = stack.pop();
						int value = numberJoiner(low, high);
						int[] arr = getByteValues(value);
						value = arr[0];
						int high1 = arr[1];
						stack.setElementAt(value, ind);
						stack.setElementAt(high1, ind - 1);
					}
				} else {
					int low = stack.pop();
					int high = stack.pop();
					if (isRegister(operand)) {
						insertRegister(operand, numberJoiner(low, high), variables);
					} else {
						setVariable(operand, variables, low);
						variables.put(indexes.get(getOffset(operand, indexes) + 1), high);
					}
				}
				insertRegister("sp", getRegisterValue("sp", variables)+2, variables);
			}
			
			else if (codeLine.contains(" neg ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" neg ") + " neg ".length());
				operand = operand.trim();
				operand = operand.trim();
				String str = operand;
				boolean isWord = isWord(variables, operand);
				operand = deletePtr(operand);
				String value = getWordStr(getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0]);
				Mov(str, "0", variables, indexes, equs, stack);
				Sub(str, value+"b", variables, indexes, stack, flags, equs, false);
			}
			
			else if (codeLine.contains(" not ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" not ") + " not ".length());
				operand = operand.trim();
				String str = operand;
				Boolean isWord = isWord(variables, operand);
				operand = deletePtr(operand);
				String s = getWordStr(getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0]);
				String s2 = "";
				if(isWord) {
					for(int is = 0; is<s.length(); is++)
						if(s.charAt(is) == '0') {
							s2+='1';
						}
						else
							s2+='0';
				}
				else {
					s = s.substring(8);
					for(int is = 0; is<s.length(); is++)
						if(s.charAt(is) == '0') {
							s2+='1';
						}
						else
							s2+='0';
				}
				BigInteger b = new BigInteger(s2, 2);
				b.add(new BigInteger("1"));
				if(isWord) {
					s2 = getWordStr(b.intValue());
				}
				else {
					s2 = getWordStr(b.intValue()).substring(8);
				}
			    Mov(str, s2+'b', variables, indexes, equs, stack);
			    
			}
			
			else if (codeLine.contains(" mov ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" mov ") + " mov ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				Mov(operands[0], operands[1], variables, indexes, equs, stack);
			}
			else if(codeLine.contains(" cmp ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" cmp ")+" cmp ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				Boolean isWord = isWord(variables, operands[0], operands[1]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int operand1 = 0;
				int operand2 = 0;
				operand1 = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				operand2 = getOperandValueIsIndex(operands[1], variables, indexes, equs, isWord, stack)[0];

				if(isWord) {
					if(operand1>=0 && operand1<32678 && operand2>=0 && operand2<32678) {
						if(operand1-operand2 < 0 || operand1-operand2 >= 32678) {
							flags.put('o', 1);
						}
						else {
							flags.put('o', 0);
						}
					}
					else if(operand1<0 || operand1>=32678 && operand2<0 || operand2>=32678) {
						if(operand1-operand2 >= 0 || operand1-operand2 < 32678) {
							flags.put('o', 1);
						}
						else {
							flags.put('o', 0);
						}
					}
					if(operand1>operand2) {
						flags.put('c', 1);
					}
					else
						flags.put('c', 0);
					if(operand1-operand2 == 0)
						flags.put('z', 1);
					else
						flags.put('z', 0);
					if(operand1-operand2<0 || operand1-operand2>=32678)
						flags.put('s', 1);
					else
						flags.put('s', 0);
				}
				else {
					int[] arr = getByteValues(operand1);
					int[] arr2 = getByteValues(operand2);
					operand1 = arr[0];
					operand2 = arr2[0];
					if(operand1>=0 && operand1<128 && operand2>=0 && operand2<128) {
						if(operand1-operand2 < 0 || operand1-operand2 >= 32678) {
							flags.put('o', 1);
						}
						else {
							flags.put('o', 0);
						}
					}
					else if(operand1<0 || operand1>=128 && operand2<0 || operand2>=128) {
						if(operand1-operand2 >= 0 || operand1-operand2 < 128) {
							flags.put('o', 1);
						}
						else {
							flags.put('o', 0);
						}
					}
					if(operand1>operand2) {
						flags.put('c', 1);
					}
					else
						flags.put('c', 0);
					if(operand1-operand2 == 0)
						flags.put('z', 1);
					else
						flags.put('z', 0);
					if(operand1-operand2<0 || operand1-operand2>=128)
						flags.put('s', 1);
					else
						flags.put('s', 0);
				}
			}
			
			else if(codeLine.contains(" jmp ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jmp ") + " jmp ".length());
				operand = operand.trim();
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" rol ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" rol ") + " rol ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int value = 0;
				int times = 0;
				if (operands[1].equals("cl"))
					times = getLowRegisterValue("cl", variables);
				else if (equs.containsKey(operands[1])) {
					times = equs.get(operands[1]);
				} else
					times = getValue(operands[1]);
				value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				for(int ii = 0; ii<times; ii++) {
					int temp = value;
					int[] arr = Shl(value, isWord);
					value = arr[0];
					flags.put('c', arr[1]);
					String s = "";
					if(isWord) {
					s = getWordStr(value);
					s = s.substring(0, 15);
					s +=flags.get('c');
					if(s.charAt(0) != getWordStr(temp).charAt(0))
						flags.put('o', 1);
					else
						flags.put('o', 0);
					}
					else {
						s = getWordStr(value).substring(8);
						s = s.substring(0, 7);
						s +=flags.get('c');
						if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
					}
					value = getValue(s+'b');
				}
				Mov(str, value+"", variables, indexes, equs, stack);
			}
			
			else if(codeLine.contains(" ror ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" ror ") + " ror ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int value = 0;
				int times = 0;
				if (operands[1].equals("cl"))
					times = getLowRegisterValue("cl", variables);
				else if (equs.containsKey(operands[1])) {
					times = equs.get(operands[1]);
				} else
					times = getValue(operands[1]);
				value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				for(int ii = 0; ii<times; ii++) {
					int temp = value;
					int[] arr = Shr(value, isWord);
					value = arr[0];
					flags.put('c', arr[1]);
					String s = "";
					if(isWord) {
					s = getWordStr(value);
					s = s.substring(1);
					s = flags.get('c') + s;
					if(s.charAt(0) != getWordStr(temp).charAt(0))
						flags.put('o', 1);
					else
						flags.put('o', 0);
					}
					else {
						s = getWordStr(value).substring(8);
						s = s.substring(1);
						s = flags.get('c') + s;
						if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
					}
					value = getValue(s+'b');
				}
				Mov(str, value+"", variables, indexes, equs, stack);
			}
			
			else if(codeLine.contains(" rcl ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" rcl ") + " rcl ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int value = 0;
				int times = 0;
				if (operands[1].equals("cl"))
					times = getLowRegisterValue("cl", variables);
				else if (equs.containsKey(operands[1])) {
					times = equs.get(operands[1]);
				} else
					times = getValue(operands[1]);
				value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				for(int ii = 0; ii<times; ii++) {
					int temp = value;
					int temp2 = flags.get('c');
					int[] arr = Shl(value, isWord);
					value = arr[0];
					flags.put('c', arr[1]);
					String s = "";
					if(isWord) {
					s = getWordStr(value);
					s = s.substring(0, 15);
					s += temp2;
					if(s.charAt(0) != getWordStr(temp).charAt(0))
						flags.put('o', 1);
					else
						flags.put('o', 0);
					}
					else {
						s = getWordStr(value).substring(8);
						s = s.substring(0, 7);
						s +=flags.get('c');
						if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
					}
					value = getValue(s+'b');
				}
				Mov(str, value+"", variables, indexes, equs, stack);
			}
			
			else if(codeLine.contains(" rcr ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" rcr ") + " rcr ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int value = 0;
				int times = 0;
				if (operands[1].equals("cl"))
					times = getLowRegisterValue("cl", variables);
				else if (equs.containsKey(operands[1])) {
					times = equs.get(operands[1]);
				} else
					times = getValue(operands[1]);
				value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				for(int ii = 0; ii<times; ii++) {
					int temp = value;
					int temp2 = flags.get('c');
					int[] arr = Shr(value, isWord);
					value = arr[0];
					flags.put('c', arr[1]);
					String s = "";
					if(isWord) {
					s = getWordStr(value);
					s = s.substring(1);
					s = temp2 + s;
					if(s.charAt(0) != getWordStr(temp).charAt(0))
						flags.put('o', 1);
					else
						flags.put('o', 0);
					}
					else {
						s = getWordStr(value).substring(8);
						s = s.substring(1);
						s = flags.get('c') + s;
						if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
					}
					value = getValue(s+'b');
				}
				Mov(str, value+"", variables, indexes, equs, stack);
			}
			
			else if(codeLine.contains(" mul ")) {

				String operand = codeLine.substring(codeLine.lastIndexOf(" mul ")+" mul ".length()).trim();
				boolean isWord = isWord(variables, operand);
				int value = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0];
				int mul = getRegisterValue("al", variables);
				if(isWord) {

				mul = getRegisterValue("ax", variables); 

				if(mul < 0){ 

				mul = 65536 + mul; 

				} 

				if(value < 0){ 

				value = 65536+value; 

				}
				if(mul*value == 0) {
					flags.put('z', 1);
				}
				else {
					flags.put('z', 0);
				}
				if(mul*value>65535) {
					flags.put('c', 1);
					flags.put('o', 1);
				}
				else {
					flags.put('c', 0);
					flags.put('o', 0);
				}
				int[] arr = getWordValues(mul*value);
				int ax = arr[0];
				int dx = arr[1];
				insertRegister("ax", ax, variables);
				insertRegister("dx", dx, variables);
				} 
				else { 

				if(mul < 0){ 

				mul = 256 + mul; 

				} 

				if(value < 0) {
				value = 256+value; 

				}
				if(mul*value == 0) {
					flags.put('z', 1);
				}
				else {
					flags.put('z', 0);
				}
				if(mul*value>255) {
					flags.put('c', 1);
					flags.put('o', 1);
				}
				else {
					flags.put('c', 0);
					flags.put('o', 0);
				}
				int ax = mul*value;
				insertRegister("ax", ax, variables);
				}

				}
			
			else if(codeLine.contains(" imul ")) {

				String operand = codeLine.substring(codeLine.lastIndexOf(" imul ")+" imul ".length()).trim();
				boolean isWord = isWord(variables, operand);
				int value = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0];
				int mul = getRegisterValue("al", variables);
				if(isWord) {

				mul = getRegisterValue("ax", variables); 

				if(mul > 32767){ 

				mul = -1*(65536 - mul); 

				} 

				if(value > 32767){ 

				value = -1*(65536-value); 

				}
				if(mul*value == 0) {
					flags.put('z', 1);
				}
				else {
					flags.put('z', 0);
				}
				if(mul*value<65535) {
					flags.put('c', 1);
					flags.put('o', 1);
				}
				else {
					flags.put('c', 0);
					flags.put('o', 0);
				}
				int[] arr = getWordValues(mul*value);
				int ax = arr[0];
				int dx = arr[1];
				insertRegister("ax", ax, variables);
				insertRegister("dx", dx, variables);
				} 
				else { 

				if(mul > 127){ 

				mul = -1*(256 - mul); 

				} 

				if(value > 127) {
				value = -1*(256-value); 

				}
				if(value > 127) {
					value = -1*(256-value); 

				}
				if(mul*value == 0) {
					flags.put('z', 1);
				}
				else {
					flags.put('z', 0);
				}
					if(mul*value>255) {
						flags.put('c', 1);
						flags.put('o', 1);
					}
					else {
						flags.put('c', 0);
						flags.put('o', 0);
					}
				int ax = mul*value;
				insertRegister("ax", ax, variables);
				} 

				}
			
			else if(codeLine.contains(" div ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" div ") + " div ".length());
				operand = operand.trim();
				boolean isWord = isWord(variables, operand);
				operand = deletePtr(operand);
				int value = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0];
				int ax = getRegisterValue("ax", variables);
				if(isWord) {
					if(value<0) {
						value = 65536 + value;
					}
					ax = numberJoiner(ax, getRegisterValue("dx", variables));
					if(ax<0) {
						ax = 65536*2 + ax;
					}
					insertRegister("ax", ax/value, variables);
					insertRegister("dx", ax%value, variables);
				}
				else {
					if(value<0) {
						value = 256 + value;
					}
					if(ax<0) {
						ax = 65536 + ax;
					}
					insertRegister("al", ax/value, variables);
					insertRegister("ah", ax%value, variables);
				}
			}
			
			else if(codeLine.contains(" idiv ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" idiv ") + " idiv ".length());
				operand = operand.trim();
				boolean isWord = isWord(variables, operand);
				operand = deletePtr(operand);
				int value = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack)[0];
				int ax = getRegisterValue("ax", variables);
				if(isWord) {
					if(value>32767) {
						value = -1*(65536 - value);
					}
					ax = numberJoiner(ax, getRegisterValue("dx", variables));
					if(ax>65535) {
						ax = -1*(65536*2 - ax);
					}
					insertRegister("ax", ax/value, variables);
					insertRegister("dx", ax%value, variables);
				}
				else {
					if(value>127) {
						value = -1*(256 - value);
					}
					if(ax>32767) {
						ax = -1*(65536 - ax);
					}
					insertRegister("al", ax/value, variables);
					insertRegister("ah", ax%value, variables);
				}
			}
			
			else if(codeLine.contains(" jz ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jz ") + " jz ".length());
				operand = operand.trim();
				if(flags.get('z')==1)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jnz ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jnz ") + " jnz ".length());
				operand = operand.trim();
				if(flags.get('z')==0)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jc ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jc ") + " jc ".length());
				operand = operand.trim();
				if(flags.get('c')==1)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jnc ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jnc ") + " jnc ".length());
				operand = operand.trim();
				if(flags.get('c')==0)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" js ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" js ") + " js ".length());
				operand = operand.trim();
				if(flags.get('s')==1)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jns ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jns ") + " jns ".length());
				operand = operand.trim();
				if(flags.get('s')==0)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jo ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jo ") + " jo ".length());
				operand = operand.trim();
				if(flags.get('o')==1)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jno ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jno ") + " jno ".length());
				operand = operand.trim();
				if(flags.get('o')==0)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jb ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jb ") + " jb ".length());
				operand = operand.trim();
				if(flags.get('c')==1)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jbe ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jbe ") + " jbe ".length());
				operand = operand.trim();
				if(flags.get('z')==1 || flags.get('c')==1)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jl ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jl ") + " jl ".length());
				operand = operand.trim();
				if(flags.get('s')!=flags.get('o'))
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jle ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jle ") + " jle ".length());
				operand = operand.trim();
				if(flags.get('z')==1 || flags.get('s')!=flags.get('o'))
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" ja ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" ja ") + " ja ".length());
				operand = operand.trim();
				if(flags.get('c')==0)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jae ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jae ") + " jae ".length());
				operand = operand.trim();
				if(flags.get('z')==1 || flags.get('c')==0)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jg ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jg ") + " jg ".length());
				operand = operand.trim();
				if(flags.get('o')==flags.get('s'))
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jge ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jge ") + " jge ".length());
				operand = operand.trim();
				if(flags.get('z')==1 || (flags.get('s')==flags.get('o')))
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" je ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" je ") + " je ".length());
				operand = operand.trim();
				if(flags.get('z')==1)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" jne ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" jne ") + " jne ".length());
				operand = operand.trim();
				if(flags.get('z')==0)
				i = findJmp(operand, text);
			}
			
			else if(codeLine.contains(" inc ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" inc ") + " inc ".length());
				operand = operand.trim();
				Add(operand, "1", variables, indexes, stack, flags, equs, false);
			}
			
			else if(codeLine.contains(" call ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" call ") + " call ".length());
				operand = operand.trim();
				stack.push(0);
				stack.push(70000);
				insertRegister("sp", getRegisterValue("sp", variables)-2, variables);
				boolean is = false;
				for(int f = 0; f<lines.length; f++) {
					String s = lines[f];
					int m = 0;
					for (; s.charAt(m) != ' '; m++)
						;
					s = s.toLowerCase();
					s = s.substring(m);
					if(s.contains(" end "))
						break;
					if (s.contains(".exit"))
						is = true;
					if(is) {
						if(s.contains("proc ") && s.contains(operand + " ") && s.contains(" near")) {
							try {
							procedure(text, f, variables, equs, indexes, stack, flags, line, operand);
							}
							catch (Exception e) {
								errors.setText("There were errors while scanning the procedure " + operand);
								return;
							}
							}
					}
					}
				
			}
			
			else if(codeLine.contains(" xchg ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" xchg ") + " xchg ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				boolean isWord = isWord(variables, operands[0], operands[1]);
				operands[0] = deletePtr(operands[0]);
				int value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				Mov(str, operands[1], variables, indexes, equs, stack);
				Mov(operands[1], value+"", variables, indexes, equs, stack);
			}
			else if (codeLine.contains(" ret ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" ret ") + " ret ".length());
				operand = operand.trim();
				stack.pop();
				stack.pop();
				if(!operand.equals("")) {
				for (int m = 0; m < getValue(operand); m++)
					stack.pop();
				}
				insertRegister("sp", getRegisterValue("sp", variables)+getValue(operand), variables);
			}
			else if (codeLine.contains(" shl ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" shl ") + " shl ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int value = 0;
				int times = 0;
				if (operands[1].equals("cl"))
					times = getLowRegisterValue("cl", variables);
				else if (equs.containsKey(operands[1])) {
					times = equs.get(operands[1]);
				} else
					times = getValue(operands[1]);
				value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				for (int t = 0; t < times; t++) {
					int temp = value;
					int[] arr = Shl(value, isWord);
					value = arr[0];
					flags.put('c', arr[1]);
					if(isWord) {
						String s = getWordStr(value);
						if(s.charAt(0) != getWordStr(temp).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
						if(value == 0)
							flags.put('z', 1);
						else
							flags.put('z', 0);
						if(s.charAt(0) == 1)
							flags.put('s', 1);
						else
							flags.put('s', 0);
					}
					else {
						String s = getWordStr(value).substring(8);
						if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
						if(value == 0)
							flags.put('z', 1);
						else
							flags.put('z', 0);
						if(s.charAt(0) == 1)
							flags.put('s', 1);
						else
							flags.put('s', 0);
					}
				}
				Mov(str, String.valueOf(value), variables, indexes, equs, stack);
			}
			
			else if (codeLine.contains(" sal ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" sal ") + " sal ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int value = 0;
				int times = 0;
				if (operands[1].equals("cl"))
					times = getLowRegisterValue("cl", variables);
				else if (equs.containsKey(operands[1])) {
					times = equs.get(operands[1]);
				} else
					times = getValue(operands[1]);
				value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				for (int t = 0; t < times; t++) {
					int temp = value;
					int[] arr = Shl(value, isWord);
					value = arr[0];
					flags.put('c', arr[1]);
					if(isWord) {
						String s = getWordStr(value);
						if(s.charAt(0) != getWordStr(temp).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
						if(value == 0)
							flags.put('z', 1);
						else
							flags.put('z', 0);
						if(s.charAt(0) == 1)
							flags.put('s', 1);
						else
							flags.put('s', 0);
					}
					else {
						String s = getWordStr(value).substring(8);
						if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
						if(value == 0)
							flags.put('z', 1);
						else
							flags.put('z', 0);
						if(s.charAt(0) == 1)
							flags.put('s', 1);
						else
							flags.put('s', 0);
					}
				}
				Mov(str, String.valueOf(value), variables, indexes, equs, stack);
			}

			else if (codeLine.contains(" shr ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" shr ") + " shr ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int value = 0;
				int times = 0;
				if (operands[1].equals("cl"))
					times = getLowRegisterValue("cl", variables);
				else if (equs.containsKey(operands[1])) {
					times = equs.get(operands[1]);
				} else
					times = getValue(operands[1]);
				value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				for (int t = 0; t < times; t++) {
					int temp = value;
					int[] arr = Shr(value, isWord);
					value = arr[0];
					flags.put('c', arr[1]);
					if(isWord) {
						String s = getWordStr(value);
						if(s.charAt(0) != getWordStr(temp).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
						if(value == 0)
							flags.put('z', 1);
						else
							flags.put('z', 0);
						if(s.charAt(0) == 1)
							flags.put('s', 1);
						else
							flags.put('s', 0);
					}
					else {
						String s = getWordStr(value).substring(8);
						if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
						if(value == 0)
							flags.put('z', 1);
						else
							flags.put('z', 0);
						if(s.charAt(0) == 1)
							flags.put('s', 1);
						else
							flags.put('s', 0);
					}
				}
				Mov(str, String.valueOf(value), variables, indexes, equs, stack);
			}
			
			else if (codeLine.contains(" sar ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" sar ") + " sar ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				int value = 0;
				int times = 0;
				if (operands[1].equals("cl"))
					times = getLowRegisterValue("cl", variables);
				else if (equs.containsKey(operands[1])) {
					times = equs.get(operands[1]);
				} else
					times = getValue(operands[1]);
				value = getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0];
				for (int t = 0; t < times; t++) {
					int temp = value;
					int[] arr = Sar(value, isWord);
					value = arr[0];
					flags.put('c', arr[1]);
					if(isWord) {
						String s = getWordStr(value);
						if(s.charAt(0) != getWordStr(temp).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
						if(value == 0)
							flags.put('z', 1);
						else
							flags.put('z', 0);
						if(s.charAt(0) == 1)
							flags.put('s', 1);
						else
							flags.put('s', 0);
					}
					else {
						String s = getWordStr(value).substring(8);
						if(s.charAt(0) != getWordStr(temp).substring(8).charAt(0))
							flags.put('o', 1);
						else
							flags.put('o', 0);
						if(value == 0)
							flags.put('z', 1);
						else
							flags.put('z', 0);
						if(s.charAt(0) == 1)
							flags.put('s', 1);
						else
							flags.put('s', 0);
					}
				}
				Mov(str, String.valueOf(value), variables, indexes, equs, stack);
			}

			else if (codeLine.contains(" push ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" push ") + " push ".length());
				operand = operand.trim();
				operand = deletePtr(operand);
				int value = getOperandValueIsIndex(operand, variables, indexes, equs, true, stack)[0];
				if(value == 80000) {
					stack.push(0);
					stack.push(80000);
				}
				else {
				int[] arr2 = getByteValues(value);
				value = arr2[0];
				int high = arr2[1];
				stack.push(high);
				stack.push(value);
				insertRegister("sp", getRegisterValue("sp", variables)-2, variables);
				}
			}
			
			else if(codeLine.contains(" and ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" and ") + " and ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0], operands[1]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				String o1 = getWordStr(getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0]);
				String o2 = getWordStr(getOperandValueIsIndex(operands[1], variables, indexes, equs, isWord, stack)[0]);
				if(!isWord) {
					o1 = o1.substring(8);
					o2 = o2.substring(8);
				}
				int ii = 7;
		        if (isWord)
		            ii += 8;

		        String after = "";
		        for (int k = 0; k <= ii; k++) {
		            char bit = '0';
		            if (o1.charAt(k) == o2.charAt(k) && o1.charAt(k) == '1')
		                bit = '1';

		            after = after + bit;
		        }
		        Mov(str, after+'b', variables, indexes, equs, stack);

		        if ((isWord && after.equals("0000000000000000")) || (!(isWord) && after.equals("00000000")))
		            flags.put('z', 1);
		        else
		            flags.put('z', 0);
		        if (after.charAt(0) == '1')
		            flags.put('s', 1);
		        else
		            flags.put('s', 0);
			}
			
			else if(codeLine.contains(" or ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" or ") + " or ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0], operands[1]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				String o1 = getWordStr(getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0]);
				String o2 = getWordStr(getOperandValueIsIndex(operands[1], variables, indexes, equs, isWord, stack)[0]);
				if(!isWord) {
					o1 = o1.substring(8);
					o2 = o2.substring(8);
				}
				int ii = 7;
		        if (isWord)
		            ii += 8;

		        String after = "";
		        for (int k = 0; k <= ii; k++) {
		            char bit = '0';
		            if (o1.charAt(k) == '1' || o2.charAt(k) == '1')
		                bit = '1';

		            after = after + bit;
		        }
		        Mov(str, after+'b', variables, indexes, equs, stack);
		        if ((isWord && after.equals("0000000000000000")) || (!(isWord) && after.equals("00000000")))
		            flags.put('z', 1);
		        else
		            flags.put('z', 0);
		        if (after.charAt(0) == '1')
		            flags.put('s', 1);
		        else
		            flags.put('s', 0);
			}
			
			else if(codeLine.contains(" xor ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" xor ") + " xor ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				String str = operands[0];
				Boolean isWord = isWord(variables, operands[0], operands[1]);
				operands[0] = deletePtr(operands[0]);
				operands[1] = deletePtr(operands[1]);
				String o1 = getWordStr(getOperandValueIsIndex(operands[0], variables, indexes, equs, isWord, stack)[0]);
				String o2 = getWordStr(getOperandValueIsIndex(operands[1], variables, indexes, equs, isWord, stack)[0]);
				if(!isWord) {
					o1 = o1.substring(8);
					o2 = o2.substring(8);
				}
				int ii = 7;
			        if (isWord)
			            ii += 8;

			        String after = "";
			        for (int k = 0; k <= ii; k++) {
			            char bit = '0';
			            if (o1.charAt(k) != o2.charAt(k))
			                bit = '1';

			            after = after + bit;
			        }
			        Mov(str, after+'b', variables, indexes, equs, stack);

			        if ((isWord && after.equals("0000000000000000")) || (!(isWord) && after.equals("00000000")))
			            flags.put('z', 1);
			        else
			            flags.put('z', 0);
			        if (after.charAt(0) == '1')
			            flags.put('s', 1);
			        else
			            flags.put('s', 0);
			}
			
			else if (codeLine.contains(" equ ")) {
				String operand = codeLine.substring(codeLine.indexOf(" equ ") + " equ ".length());
				operand = operand.trim();
				String var = codeLine.substring(0, codeLine.indexOf(" equ "));
				var = var.trim();
				if (var.contains(" "))
					var = var.substring(var.lastIndexOf(" "));
				boolean isOffset = false;
				if (operand.contains(" offset ")) {
					operand = operand.substring(operand.indexOf(" offset ") + " offset ".length()).trim();
					isOffset = true;
				}
				if (variables.containsKey(operand + 'b')) {
					if (isOffset) {
						equs.put(operand, getOffset(operand, indexes));
					} else
						equs.put(var, variables.get(operand + 'b'));
				} else if (variables.containsKey(operand + 'w')) {
					if (isOffset) {
						equs.put(operand, getOffset(operand, indexes));
					} else
						equs.put(var, variables.get(operand + 'w'));
				} else
					equs.put(var, getValue(operand));
			}

			else if (codeLine.contains(" add ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" add ") + " add ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				Add(operands[0], operands[1], variables, indexes, stack, flags, equs, false);
			}
			else if (codeLine.contains(" adc ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" adc ") + " adc ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				Add(operands[0], operands[1], variables, indexes, stack, flags, equs, true);
			}
			
			else if (codeLine.contains(" sub ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" sub ") + " sub ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				Sub(operands[0], operands[1], variables, indexes, stack, flags, equs, false);
			}
			
			else if (codeLine.contains(" dec ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" dec ") + " dec ".length());
				operand = operand.trim();
				Sub(operand, "1", variables, indexes, stack, flags, equs, false);
			}
			
			else if (codeLine.contains(" sub ")) {
				String operand = codeLine.substring(codeLine.lastIndexOf(" sub ") + " sub ".length());
				operand = operand.trim();
				String[] operands = operand.split(",");
				operands[0] = operands[0].trim();
				operands[1] = operands[1].trim();
				Sub(operands[0], operands[1], variables, indexes, stack, flags, equs, true);
			}
			
			else if (codeLine.contains(" cbw ")) {
				int value = getRegisterValue("al", variables);
				if(value<0 || value>127)
					Mov("ah", "255", variables, indexes, equs, stack);
				else
					Mov("ah", "0", variables, indexes, equs, stack);
			}
			
			else if (codeLine.contains(" cwd ")) {
				int value = getRegisterValue("ax", variables);
				if(value<0 || value>32767)
					Mov("dx", "65536", variables, indexes, equs, stack);
				else
					Mov("dx", "0", variables, indexes, equs, stack);
			}
			else if(codeLine.contains(" int ") && codeLine.contains("21")){

				if(getRegisterValue("ah", variables) == 9){
				
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							int copyDx = getRegisterValue("dx", variables); 

							while(true){ 

							int valueAt = variables.get(indexes.get(copyDx)); 

							if(valueAt < 0)
							valueAt = 256 + valueAt; 

							if(((char)valueAt) == '$')
							break; 
							print.setText(print.getText()+(char)valueAt);
							copyDx++;
							}
						}
					});
				}
				else if(getRegisterValue("ah", variables) == 2) {

				int t = getRegisterValue("dl", variables);

				if(t<0)
				t = 256 + t; 

				print.setText(print.getText()+(char)t);
				}
				}
			
			}
	}
	
	private int findJmp(String operand, String code) {
		int i = 0;
		try(Scanner scan = new Scanner(code)){
		for (; scan.hasNextLine(); i++) {
			String codeLine = scan.nextLine().toLowerCase();
			int mm = 0;
			for (; codeLine.charAt(mm) != ' '; mm++);
			codeLine = codeLine.substring(mm);
			if(codeLine.contains(":")) {
			String str = codeLine.substring(0, codeLine.indexOf(":"));
			str = str.trim();
			if (str.equals(operand))
				break;
			}
		}
		}
		return i;
	}
	
	private void Add(String operand, String operandTwo, HashMap<String, Integer> variables, HashMap<Integer, String> indexes, Stack<Integer> stack, HashMap<Character, Integer> flags, HashMap<String, Integer> equs, boolean isCarry) {
		int operand1;
		Integer inde = null;
		int operand2;
		Boolean isWord = isWord(variables, operand, operandTwo);
		operand = deletePtr(operand);
		operandTwo = deletePtr(operandTwo);
		operand2 = getOperandValueIsIndex(operandTwo, variables, indexes, equs, isWord, stack)[0];
		Integer[] arr2 = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack);
		operand1 = arr2[0];
		inde = arr2[1];
		if(isCarry)
			operand2+=flags.get('c');
		if (!isWord) {
			int sum = operand1 + operand2;
			if (operand1 + operand2 >= 256) {
				flags.put('c', 1);
			} else
				flags.put('c', 0);
			if (operand1 > 0 && operand1 < 128 && operand2 > 0 && operand2 < 128
					&& (sum < 0 || sum > 128)) {
				flags.put('o', 1);
			} else if ((operand1 < 0 || operand1 >= 128) && (operand2 < 0 || operand2 >= 128)
					&& sum > 0 && sum <= 128) {
				flags.put('o', 1);
			} else
				flags.put('o', 0);
			if (sum > 127 || sum < 0)
				flags.put('s', 1);
			else
				flags.put('s', 0);
			if (sum == 0)
				flags.put('z', 1);
			else
				flags.put('z', 0);
			int value = operand1 + operand2;
			int[] arr = getByteValues(value);
			value = arr[0];
			if (inde != null) {
				if (!isMahsanit(operand))
					variables.put(indexes.get(inde), value);
				else
					stack.setElementAt(value, inde);
			} else {
				if (isRegister(operand)) {
					insertRegister(operand, value, variables);
				} else
					setVariable(operand, variables, value);
			}
		} else {
			int sum = operand1 + operand2;
			if (operand1 + operand2 >= 65536) {
				flags.put('c', 1);
			} else
				flags.put('c', 0);
			if (operand1 > 0 && operand1 < 32768 && operand2 > 0 && operand2 < 32768
					&& (sum < 0 || sum > 32768)) {
				flags.put('o', 1);
			} else if ((operand1 < 0 || operand1 >= 32768) && (operand2 < 0 || operand2 >= 32768)
					&& sum > 0 && sum <= 32768) {
				flags.put('o', 1);
			} else
				flags.put('o', 0);
			if (sum > 32767 || sum < 0)
				flags.put('s', 1);
			else
				flags.put('s', 0);
			if (sum == 0)
				flags.put('z', 1);
			else
				flags.put('z', 0);
			int value = operand1 + operand2;
			int[] arr = getByteValues(value);
			value = arr[0];
			int high = arr[1];
			if (inde != null) {
				if (!isMahsanit(operand)) {
					variables.put(indexes.get(inde), value);
					variables.put(indexes.get(inde + 1), high);
				} else {
					stack.setElementAt(value, stack.get(inde));
					stack.setElementAt(high, stack.get(inde - 1));
				}
			} else {
				if (isRegister(operand)) {
					insertRegister(operand, numberJoiner(value, high), variables);
				} else {
					setVariable(operand, variables, value);
					variables.put(indexes.get(getOffset(operand, indexes) + 1), high);
				}
			}
		}
	}
	
	private void Sub(String operand, String operandTwo, HashMap<String, Integer> variables, HashMap<Integer, String> indexes, Stack<Integer> stack, HashMap<Character, Integer> flags, HashMap<String, Integer> equs, boolean isCarry) {
		int operand1;
		Integer inde = null;
		int operand2;
		Boolean isWord = isWord(variables, operand, operandTwo);
		operand = deletePtr(operand);
		operandTwo = deletePtr(operandTwo);
		operand2 = getOperandValueIsIndex(operandTwo, variables, indexes, equs, isWord, stack)[0];
		Integer[] arr2 = getOperandValueIsIndex(operand, variables, indexes, equs, isWord, stack);
		operand1 = arr2[0];
		inde = arr2[1];
		if(isCarry)
			operand2 -= flags.get('c');
		if (!isWord) {
			int sum = operand1 - operand2;
			if (operand1 < operand2) {
				flags.put('c', 1);
			} else
				flags.put('c', 0);
			if((operand1<0 && sum<128) || (operand1>127 && (-1*(256-operand1))-operand2<128))
				flags.put('o', 1);
			else
				flags.put('o', 0);
			if (sum > 127 || sum < 0)
				flags.put('s', 1);
			else
				flags.put('s', 0);
			if (sum == 0)
				flags.put('z', 1);
			else
				flags.put('z', 0);
			int value = operand1 - operand2;
			int[] arr = getByteValues(value);
			value = arr[0];
			if (inde != null) {
				if (!isMahsanit(operand))
					variables.put(indexes.get(inde), value);
				else
					stack.setElementAt(value, inde);
			} else {
				if (isRegister(operand)) {
					insertRegister(operand, value, variables);
				} else
					setVariable(operand, variables, value);
			}
		} else {
			int sum = operand1 - operand2;
			if (operand1 < operand2) {
				flags.put('c', 1);
			} else
				flags.put('c', 0);
			if (sum > 32767 || sum < 0)
				flags.put('s', 1);
			else
				flags.put('s', 0);
			if (sum == 0)
				flags.put('z', 1);
			else
				flags.put('z', 0);
			int value = operand1 - operand2;
			int[] arr = getByteValues(value);
			value = arr[0];
			int high = arr[1];
			if (inde != null) {
				if (!isMahsanit(operand)) {
					variables.put(indexes.get(inde), value);
					variables.put(indexes.get(inde + 1), high);
				} else {
					stack.setElementAt(value, stack.get(inde));
					stack.setElementAt(high, stack.get(inde - 1));
				}
			} else {
				if (isRegister(operand)) {
					insertRegister(operand, numberJoiner(value, high), variables);
				} else {
					setVariable(operand, variables, value);
					variables.put(indexes.get(getOffset(operand, indexes) + 1), high);
				}
			}
		}
	}
	
	private int[] Shr(int value, boolean isWord) {
		int arr[] = new int[2];
		String s = getWordStr(value);
		if (isWord) {
			arr[1] = Integer.parseInt(s.charAt(15)+"");
			s = s.substring(0, 15);
			s = '0' + s;
			arr[0] = new BigInteger(s, 2).intValue();
		} else {
			s = s.substring(8);
			arr[1] = Integer.parseInt(s.charAt(7)+"");
			s = s.substring(0, 7);
			s = '0' + s;
			arr[0] = new BigInteger(s, 2).intValue();
		}
		return arr;
	}
	
	private int[] Sar(int value, boolean isWord) {
		int arr[] = new int[2];
		String s = getWordStr(value);
		if (isWord) {
			arr[1] = Integer.parseInt(s.charAt(15)+"");
			s = s.substring(0, 15);
			s = s.charAt(0) + s;
			arr[0] = new BigInteger(s, 2).intValue();
		} else {
			s = s.substring(8);
			arr[1] = Integer.parseInt(s.charAt(7)+"");
			s = s.substring(0, 7);
			s = s.charAt(0) + s;
			arr[0] = new BigInteger(s, 2).intValue();
		}
		return arr;
	}

	private int[] Shl(int value, boolean isWord) {
		int arr[] = new int[2];
		String s = getWordStr(value);
		if (isWord) {
			arr[1] = Integer.parseInt(s.charAt(0)+"");
			s = s.substring(1);
			s = s + '0';
			arr[0] = new BigInteger(s, 2).intValue();
		} else {
			s = s.substring(8);
			arr[1] = Integer.parseInt(s.charAt(0)+"");
			s = s.substring(1);
			s = s + '0';
			arr[0] = new BigInteger(s, 2).intValue();
		}
		
		return arr;
	}
	
	// The method that creates the data table and scans the code.

	private void createData(String text) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Scanner scan = new Scanner(text);
				HashMap<String, Integer> variables = new HashMap<>();
				HashMap<Integer, String> indexes = new HashMap<>();
				HashMap<String, Integer> equs = new HashMap<>();
				try {
					int currentIndex = 0;

					// Now we scan the whole text:
					for (; scan.hasNextLine();) {
						String codeLine = scan.nextLine();
						int mm = 0;
						for (; codeLine.charAt(mm) != ' '; mm++)
							;
						codeLine = codeLine.toLowerCase().substring(mm);
						if(codeLine.contains(";"))
							codeLine = codeLine.substring(0, codeLine.indexOf(";"));
						if (codeLine.contains(".code"))
							break;
						if (codeLine.toLowerCase().contains(" db ")) {
							String var = codeLine.substring(0, codeLine.indexOf(" db "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							String operand = codeLine.substring(codeLine.indexOf(" db ") + " db ".length());
							if (operand.contains(",")) {
								String[] operands = operand.split(",");
								for (int f = 0; f < operands.length; f++) {
									operands[f] = operands[f].trim();

									if (operands[f].contains(" dup") && operands[f].contains("(")) {
										boolean isFirst = f == 0;
										String[] str = new String[operands.length - f];
										for (int i = f; i < operands.length; i++)
											str[i - f] = operands[i];
										String s = arrayToString(str);
										int i = getMul(s);
										s = s.substring(s.indexOf('(') + 1);
										String oper = dupExtract(s);
										currentIndex = indexByteDup(indexes, oper, i, currentIndex, var, isFirst);
										f = f + nextIndex(str);
									}

									else if (operands[f].contains("'")) {
										operands[f] = operands[f].replace("'", "");
										for (int io = 0; io < operands[f].length(); io++) {
											if (f == 0 && io == 0) {
												indexes.put(currentIndex, var + 'b');
											} else {
												indexes.put(currentIndex, String.valueOf(currentIndex));
											}
											currentIndex++;
										}
									} else {
										if (f == 0)
											indexes.put(currentIndex, var + 'b');
										else
											indexes.put(currentIndex, String.valueOf(currentIndex));
										currentIndex++;
									}
								}
							}

							else {
								if (operand.contains(" dup") && operand.contains("(")) {
									int y = getMul(operand);
									String s = operand;
									s = s.substring(s.indexOf('(') + 1);
									String oper = dupExtract(s);
									currentIndex = indexByteDup(indexes, oper, y, currentIndex, var, true);
								} else if (operand.contains("'")) {
									operand = operand.replace("'", "");
									for (int io = 0; io < operand.length(); io++) {
										if (io == 0) {
											indexes.put(currentIndex, var + 'b');
										} else {
											indexes.put(currentIndex, String.valueOf(currentIndex));
										}
										currentIndex++;
									}
								} else {
									indexes.put(currentIndex, var + 'b');
									currentIndex++;
								}
							}
						}

						if (codeLine.toLowerCase().contains(" dw ")) {
							String var = codeLine.substring(0, codeLine.indexOf(" dw "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							String operand = codeLine.substring(codeLine.indexOf(" dw ") + " dw ".length());
							operand = operand.trim();
							if (operand.contains(",")) {
								String[] operands = operand.split(",");
								for (int f = 0; f < operands.length; f++) {
									operands[f] = operands[f].trim();
									if (operands[f].contains(" dup") && operands[f].contains("(")) {
										boolean isFirst = f == 0;
										String[] str = new String[operands.length - f];
										for (int i = f; i < operands.length; i++)
											str[i - f] = operands[i];
										String s = arrayToString(str);
										int i = getMul(s);
										s = s.substring(s.indexOf('(') + 1);
										String oper = dupExtract(s);
										currentIndex = indexWordDup(indexes, oper, i, currentIndex, var, isFirst);
										f = f + nextIndex(str);
									} else {
										if (operands[f].contains("'")) {
											operands[f] = operands[f].replace("'", "");
											for (int io = 0; io < operands[f].length(); io++) {
												if (f == 0 && io == 0) {
													indexes.put(currentIndex, var + 'w');
												} else {
													indexes.put(currentIndex, String.valueOf(currentIndex));
												}
												currentIndex++;
												indexes.put(currentIndex, String.valueOf(currentIndex));
												currentIndex++;
											}
										} else {
											if (f == 0)
												indexes.put(currentIndex, var + 'w');
											else
												indexes.put(currentIndex, String.valueOf(currentIndex));
											currentIndex++;
										}
										indexes.put(currentIndex, String.valueOf(currentIndex));
										currentIndex++;
									}
								}
							} else {
								if (operand.contains(" dup") && operand.contains("(")) {
									int y = getMul(operand);
									String s = operand;
									s = s.substring(s.indexOf('(') + 1);
									String oper = dupExtract(s);
									currentIndex = indexWordDup(indexes, oper, y, currentIndex, var, true);
								} else if (operand.contains("'")) {
									operand = operand.replace("'", "");
									for (int io = 0; io < operand.length(); io++) {
										if (io == 0) {
											indexes.put(currentIndex, var + 'w');
										} else {
											indexes.put(currentIndex, String.valueOf(currentIndex));
										}
										currentIndex++;
										indexes.put(currentIndex, String.valueOf(currentIndex));
										currentIndex++;
									}
								} else {
									indexes.put(currentIndex, var + 'w');
									currentIndex++;
									indexes.put(currentIndex, String.valueOf(currentIndex));
									currentIndex++;
								}
							}
						}
					}
					currentIndex = 0;
					scan.close();
					scan = new Scanner(text);
					for (; scan.hasNextLine();) {
						String codeLine = scan.nextLine();
						int mm = 0;
						for (; codeLine.charAt(mm) != ' '; mm++)
							;
						codeLine = codeLine.toLowerCase().substring(mm);
						if(codeLine.contains(";"))
							codeLine = codeLine.substring(0, codeLine.indexOf(";"));
						if (codeLine.contains(".code"))
							break;

						if (codeLine.toLowerCase().contains(" db ")) {
							String var = codeLine.substring(0, codeLine.indexOf(" db "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							String operand = codeLine.substring(codeLine.indexOf(" db ") + " db ".length());
							if (operand.contains(",")) {
								String[] operands = operand.split(",");
								for (int f = 0; f < operands.length; f++) {
									operands[f] = operands[f].trim();
									if (operands[f].contains(" dup") && operands[f].contains("(")) {
										boolean isFirst = f == 0;
										String[] str = new String[operands.length - f];
										for (int y = f; y < operands.length; y++)
											str[y - f] = operands[y];
										String s = arrayToString(str);
										int y = getMul(s);
										s = s.substring(s.indexOf('(') + 1);
										String oper = dupExtract(s);
										currentIndex = varByteDup(indexes, oper, y, currentIndex, var, isFirst,
												variables);
										f = f + nextIndex(str);
									} else {
										if (operands[f].contains("'")) {
											operands[f] = operands[f].replace("'", "");
											for (int io = 0; io < operands[f].length(); io++) {
												if (f == 0 && io == 0) {
													variables.put(var + 'b', (int) operands[f].charAt(io));
												} else
													variables.put(String.valueOf(currentIndex),
															(int) operands[f].charAt(io));
												currentIndex++;
											}
										} else {
											if (operands[f].contains("[") || operands[f].contains("+") || operands[f].contains("-")) {
												int ind = operandToIndex(variables, indexes, operands[f], equs);
												if (f == 0) {
													variables.put(var + 'b', ind);
												} else {
													variables.put(String.valueOf(currentIndex), ind);
												}
												currentIndex++;
											} else { 
												if (operands[f].contains("offset ")) {
												operands[f] = operands[f]
														.substring(operands[f].indexOf("offset ") + "offset ".length())
														.trim();
											}
											if (f == 0) {
												if (operands[f].equals("?")) {
													variables.put(var + 'b', 0);
												} else if (indexes.containsValue(operands[f] + 'b')
														|| indexes.containsValue(operands[f] + 'w')) {
													for (Integer in : indexes.keySet())
														if (indexes.get(in).equals(operands[f] + 'b')
																|| indexes.get(in).equals(operands[f] + 'w')) {
															variables.put(var + 'b', in);
															break;
														}
												} else
													variables.put(var + 'b', getValue(operands[f]));
											} else {
												if (operands[f].equals("?")) {
													variables.put(String.valueOf(currentIndex), 0);
												} else if (indexes.containsValue(operands[f] + 'b')
														|| indexes.containsValue(operands[f] + 'w')) {
													for (Integer in : indexes.keySet())
														if (indexes.get(in).equals(operands[f] + 'b')
																|| indexes.get(in).equals(operands[f] + 'w')) {
															variables.put(String.valueOf(currentIndex), in);
															break;
														}
												} else
													variables.put(String.valueOf(currentIndex), getValue(operands[f]));
											}
											currentIndex++;
										}
									}
									}
								}
							} else {
								if (operand.contains(" dup") && operand.contains("(")) {
									int y = getMul(operand);
									String s = operand;
									s = s.substring(s.indexOf('(') + 1);
									String oper = dupExtract(s);
									currentIndex = varByteDup(indexes, oper, y, currentIndex, var, true, variables);
								} else {
									if (operand.contains("'")) {
										operand = operand.replace("'", "");
										for (int io = 0; io < operand.length(); io++) {
											if (io == 0) {
												variables.put(var + 'b', (int) operand.charAt(io));
											} else
												variables.put(String.valueOf(currentIndex), (int) operand.charAt(io));
											currentIndex++;
										}
									} else {
										if (operand.contains("offset ")) {
											operand = operand.substring(operand.indexOf("offset ") + "offset ".length())
													.trim();
										}
										if (operand.contains("[") || operand.contains("+") || operand.contains("-")) {
											int ind = operandToIndex(variables, indexes, operand, equs);
											variables.put(var + 'b', ind);
											currentIndex++;
										} else if (operand.equals("?")) {
											variables.put(var + 'b', 0);
										} else if (indexes.containsValue(operand + 'b')
												|| indexes.containsValue(operand + 'w')) {
											for (Integer in : indexes.keySet())
												if (indexes.get(in).equals(operand + 'b')
														|| indexes.get(in).equals(operand + 'w')) {
													variables.put(var + 'b', in);
													break;
												}
										} else
											variables.put(var + 'b', getValue(operand));
										currentIndex++;
									}
								}
							}
						}
						if (codeLine.toLowerCase().contains(" dw ")) {
							String var = codeLine.substring(0, codeLine.indexOf(" dw "));
							var = var.trim();
							if (var.contains(" "))
								var = var.substring(var.lastIndexOf(" "));
							String operand = codeLine.substring(codeLine.indexOf(" dw ") + " dw ".length());
							operand = operand.trim();
							if (operand.contains(",")) {
								String[] operands = operand.split(",");
								for (int f = 0; f < operands.length; f++) {
									operands[f] = operands[f].trim();
									if (operands[f].contains(" dup") && operands[f].contains("(")) {
										boolean isFirst = f == 0;
										String[] str = new String[operands.length - f];
										for (int y = f; y < operands.length; y++)
											str[y - f] = operands[y];
										String s = arrayToString(str);
										int y = getMul(s);
										s = s.substring(s.indexOf('(') + 1);
										String oper = dupExtract(s);
										currentIndex = varWordDup(indexes, oper, y, currentIndex, var, isFirst,
												variables);
										f = f + nextIndex(str);
									} else {
										if (operands[f].contains("'")) {
											operands[f] = operands[f].replace("'", "");
											for (int io = 0; io < operands[f].length(); io++) {
												int value = (int) operands[f].charAt(io);
												int[] arr = getByteValues(value);
												value = arr[0];
												int high = arr[1];
												if (f == 0 && io == 0) {
													variables.put(var + 'w', value);
												} else
													variables.put(String.valueOf(currentIndex), value);
												currentIndex++;
												variables.put(String.valueOf(currentIndex), high);
												currentIndex++;
											}
										} else {
											int value = 0;
											int high = 0;
											if (operands[f].contains("[") || operands[f].contains("+") || operands[f].contains("-")) {
												int[] arr = getByteValues(operandToIndex(variables, indexes, operands[f], equs));
												value = arr[0];
												high = arr[1];
												if (f == 0) {
													variables.put(var + 'w', value);
												} else {
													variables.put(String.valueOf(currentIndex), value);
												}
											} else {
											if (operands[f].contains("offset ")) {
												operands[f] = operands[f]
														.substring(operands[f].indexOf("offset ") + "offset ".length())
														.trim();
											}
											if (f == 0) {
												if (operands[f].equals("?")) {
													variables.put(var + 'w', 0);
												} else if (indexes.containsValue(operands[f] + 'b')
														|| indexes.containsValue(operands[f] + 'w')) {
													for (Integer in : indexes.keySet())
														if (indexes.get(in).equals(operands[f] + 'b')
																|| indexes.get(in).equals(operands[f] + 'w')) {
															variables.put(var + 'w', in);
															break;
														}
												} else {
													value = getValue(operands[f]);
													int[] arr = getByteValues(value);
													value = arr[0];
													high = arr[1];
													variables.put(var + 'w', value);
												}
											} else {
												if (operands[f].equals("?")) {
													variables.put(String.valueOf(currentIndex), 0);
												} else if (indexes.containsValue(operands[f] + 'b')
														|| indexes.containsValue(operands[f] + 'w')) {
													for (Integer in : indexes.keySet())
														if (indexes.get(in).equals(operands[f] + 'b')
																|| indexes.get(in).equals(operands[f] + 'w')) {
															variables.put(String.valueOf(currentIndex), in);
															break;
														}
												} else {
													value = getValue(operands[f]);
													int[] arr = getByteValues(value);
													value = arr[0];
													high = arr[1];
													variables.put(String.valueOf(currentIndex), value);
												}
											}
											}
											currentIndex++;
											variables.put(String.valueOf(currentIndex), high);
											currentIndex++;
										}
									}
								}
							} else {
								if (operand.contains("offset ")) {
									operand = operand.substring(operand.indexOf("offset ") + "offset ".length()).trim();
								}
								if (operand.contains(" dup") && operand.contains("(")) {
									int y = getMul(operand);
									String s = operand;
									s = s.substring(s.indexOf('(') + 1);
									String oper = dupExtract(s);
									currentIndex = varWordDup(indexes, oper, y, currentIndex, var, true, variables);
								} else {
									if (operand.contains("'")) {
										operand = operand.replace("'", "");
										for (int io = 0; io < operand.length(); io++) {
											int value = (int) operand.charAt(io);
											int[] arr = getByteValues(value);
											value = arr[0];
											int high = arr[1];
											if (io == 0) {
												variables.put(var + 'w', value);
											} else
												variables.put(String.valueOf(currentIndex), value);
											currentIndex++;
											variables.put(String.valueOf(currentIndex), high);
											currentIndex++;
										}
									} else {
										int value = 0;
										int high = 0;
										if (operand.contains("[") || operand.contains("+") || operand.contains("-")) {
											int ind = operandToIndex(variables, indexes, operand, equs);
											variables.put(var + 'w', ind);
										} else if (operand.equals("?")) {
											variables.put(var + 'w', 0);
										} else if (indexes.containsValue(operand + 'b')
												|| indexes.containsValue(operand + 'w')) {
											for (Integer in : indexes.keySet())
												if (indexes.get(in).equals(operand + 'b')
														|| indexes.get(in).equals(operand + 'w')) {
													variables.put(var + 'w', in);
													break;
												}
										} else {
											value = getValue(operand);
											int[] arr = getByteValues(value);
											value = arr[0];
											high = arr[1];
											variables.put(var + 'w', getValue(operand));
										}
										currentIndex++;
										variables.put(String.valueOf(currentIndex), high);
										currentIndex++;
									}
								}
							}
						}
					}
					try {
						ArrayList<String> bytes = new ArrayList<>();
						for (int i = 0; i < indexes.size(); i++)
							bytes.add("");
						for (int i : indexes.keySet()) {
							String s = Integer.toBinaryString(variables.get(indexes.get(i)));
							for (; s.length() < 8;)
								s = '0' + s;
							if (s.length() > 8)
								s = s.substring(s.length() - 8);
							bytes.set(i, s);
						}
						String dataDir = System.getProperty("user.home") + "\\Desktop";
						File file = new File(dataDir + "\\TestDataTable.docx");
						file.createNewFile();
						Locale.setDefault(new Locale("en-us"));
						Document doc = new Document(dataDir + "\\TestDataTable.docx");
						Table table = new Table(doc);
						doc.getFirstSection().getBody().appendChild(table);
						Row firstRow = new Row(doc);
						table.appendChild(firstRow);

						Cell firstCell = new Cell(doc);
						firstRow.appendChild(firstCell);

						Paragraph paragraph = new Paragraph(doc);
						firstCell.appendChild(paragraph);
						Run run = new Run(doc, "Variable Name");
						paragraph.appendChild(run);

						Cell secondCell = new Cell(doc);
						firstRow.appendChild(secondCell);
						Paragraph paragraph2 = new Paragraph(doc);
						secondCell.appendChild(paragraph2);
						Run run2 = new Run(doc, "Value (binary)");
						paragraph2.appendChild(run2);

						Cell thirdCell = new Cell(doc);
						firstRow.appendChild(thirdCell);
						Paragraph paragraph3 = new Paragraph(doc);
						thirdCell.appendChild(paragraph3);
						Run run3 = new Run(doc, "Offset (hex)");
						paragraph3.appendChild(run3);

						for (int i = 0; i < variables.size(); i++) {
							Row nextRow = new Row(doc);
							table.appendChild(nextRow);

							Cell nextCell1 = new Cell(doc);
							nextRow.appendChild(nextCell1);
							Cell nextCell2 = new Cell(doc);
							nextRow.appendChild(nextCell2);
							Cell nextCell3 = new Cell(doc);
							nextRow.appendChild(nextCell3);
							Paragraph nextParagraph = new Paragraph(doc);
							nextCell1.appendChild(nextParagraph);
							Paragraph nextParagraph2 = new Paragraph(doc);
							nextCell2.appendChild(nextParagraph2);
							Paragraph nextParagraph3 = new Paragraph(doc);
							nextCell3.appendChild(nextParagraph3);
							String nameText = indexes.get(i).substring(0, indexes.get(i).length() - 1);
							try {
								Integer.parseInt(nameText);
								nameText = "";
							} catch (Exception e) {
							}
							Run nextRun = new Run(doc, nameText);
							nextParagraph.appendChild(nextRun);
							String byteText = bytes.get(i);
							Run nextRun2 = new Run(doc, byteText);
							nextParagraph2.appendChild(nextRun2);
							String offsetText = new BigInteger(i + "").toString(16);
							Run nextRun3 = new Run(doc, offsetText);
							nextParagraph3.appendChild(nextRun3);
						}
						doc.save(dataDir + "\\TestDataTable.docx");
						errors.setText("The table created successfully!");
					} catch (Exception e) {
						errors.setText("There were errors while creating the table.");
					}
					scan.close();
				} catch (Exception e) {
					errors.setText("There were errors while scanning the code.");
					scan.close();
				}
				
			}
		});
		t.start();
	}

	// The method gets the map of variables and operands. It returns true if the
	// action is supposed to happen on word and false if it is supposed to happen on
	// byte.

	private boolean isWord(HashMap<String, Integer> variables, String... operand) {
		for (int i = 0; i < operand.length; i++) {
			if (operand[i].contains("word ptr"))
				return true;
			if (operand[i].contains("byte ptr"))
				return false;
			operand[i] = deletePtr(operand[i]);
			if (operand[i].contains("[") || operand[i].contains("+") || operand[i].contains("-")) {
				String[] parts = null;
				if (operand[i].contains("+") || operand[i].contains("-")) {
					parts = operand[i].split("\\+|\\-");
				}
				if (parts == null) {
					operand[i] = operand[i].replace("[", "");
					operand[i] = operand[i].replace("]", "");
					for (String s : variables.keySet()) {
						if (s.equals(operand[i] + 'w')) {
							return true;
						}
					}
				} else {
					for (int o = 0; o < parts.length; o++) {
						parts[o] = parts[o].replace("[", "");
						parts[o] = parts[o].replace("]", "");
						parts[o] = parts[o].trim();
						if (parts[o].contains("offset ")) {
							parts[o] = parts[o].substring(parts[o].indexOf("offset ") + "offset ".length()).trim();
						}
						for (String s : variables.keySet()) {
							if (s.equals(parts[o] + 'w')) {
								return true;
							}
						}
					}
				}
			} else {
				ArrayList<String> registers = new ArrayList<>();
				registers.add("bx");
				registers.add("si");
				registers.add("ax");
				registers.add("cx");
				registers.add("sp");
				registers.add("dx");
				registers.add("di");
				registers.add("bp");
				registers.add("si");
				registers.add("ds");
				if (matches(registers, operand[i]))
					return true;
				for (String s : variables.keySet()) {
					if (s.equals(operand[i] + 'w')) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private int getRegisterValue(String register, HashMap<String, Integer> variables) {
		if (isLow(register)) {
			register = register.replace('l', 'x');
			return getLowRegisterValue(register, variables);
		} else if (isHigh(register)) {
			register = register.replace('h', 'x');
			return getHighRegisterValue(register, variables);
		} else {
			return variables.get(register);
		}
	}

	// Returns the value of the number (if it is a number) in base 10, or null if it
	// is not a number.
	private Integer getValue(String operand) {
		if(operand.toLowerCase().contains("'")) {
			int sum = 0;
			operand = operand.replace("'", "");
			for (int io = 0; io < operand.length(); io++) {
				sum += (int) operand.charAt(io);
			}
			return sum;
		}
		if (operand.toLowerCase().charAt(operand.length() - 1) == 'h' && hasNumbers(operand)) {
			operand = operand.substring(0, operand.length() - 1);
			BigInteger num = new BigInteger(operand, 16);
			return Integer.parseInt(num.toString(10));
		}
		if (operand.toLowerCase().charAt(operand.length() - 1) == 'b' && hasNumbers(operand)) {
			operand = operand.substring(0, operand.length() - 1);
			BigInteger num = new BigInteger(operand, 2);
			return Integer.parseInt(num.toString(10));
		}
		if (AllNumbers(operand))
			return Integer.parseInt(operand);
		return null;
	}

	// Returns if there are numbers in the string
	private boolean hasNumbers(String operand) {
		for (int i = 0; i < operand.length(); i++)
			if (Character.isDigit(operand.charAt(i)))
				return true;
		return false;
	}
	
	private boolean AllNumbers(String operand) {
		for (int i = 0; i < operand.length(); i++)
			if (!Character.isDigit(operand.charAt(i)) && operand.charAt(i)!='-')
				return false;
		return true;
	}

	private boolean isRegister(String operand) {
		ArrayList<String> registers = new ArrayList<>();
		registers.add("bx");
		registers.add("si");
		registers.add("ax");
		registers.add("cx");
		registers.add("dx");
		registers.add("bp");
		registers.add("sp");
		registers.add("di");
		registers.add("bp");
		registers.add("si");
		registers.add("bl");
		registers.add("cl");
		registers.add("al");
		registers.add("dl");
		registers.add("bh");
		registers.add("ds");
		registers.add("ch");
		registers.add("ah");
		registers.add("dh");
		return registers.contains(operand);
	}

	// Returns if all of the string is numbers (the base does not matter).
	private boolean isAllNums(String operand) {
		String[] parts = operand.split("\\+");
		for (int o = 0; o < parts.length; o++) {
			parts[o] = parts[o].replace("[", "");
			parts[o] = parts[o].replace("]", "");
			if (getValue(parts[o]) == null) {
				return false;
			}
		}
		return true;
	}

	// Returns whether or not the offset of the operand will go to the stack.
	private boolean isMahsanit(String operand) {
		String[] parts = null;
		if (operand.contains("+") || operand.contains("-")) {
			parts = operand.split("\\+|\\-");
		}
		if (parts == null) {
			operand = operand.replace("[", "");
			operand = operand.replace("]", "");
			if (operand.equals("bp"))
				return true;
		} else {
			for (int o = 0; o < parts.length; o++) {
				parts[o] = parts[o].replace("[", "");
				parts[o] = parts[o].replace("]", "");
				if (parts[o].equals("bp"))
					return true;
			}
		}
		return false;
	}

	// Returns the index of the operand (if it has [] or it is variable with +).
	private int operandToIndex(HashMap<String, Integer> variables, HashMap<Integer, String> indexes, String operand,
			HashMap<String, Integer> equs) {
		String[] parts = null;
		boolean isclose = false;
		for(int l = 0; l<operand.length(); l++) {
			if(!isclose && operand.charAt(l) == '[') {
				isclose = true;
				operand = operand.substring(0, l)+'+'+operand.substring(l+1);
			}
			else if(isclose && operand.charAt(l) == ']') {
				isclose = false;
				operand = operand.substring(0, l)+'+'+operand.substring(l+1);
			}
		}
		char c = '+';
		if(operand.charAt(0) == '+')
			operand = operand.substring(1);
		if(operand.charAt(operand.length()-1) == '+')
			operand = operand.substring(0, operand.length()-1);
		if(operand.charAt(0) == '-') {
			c = '-';
			operand = operand.substring(1);
		}
		if (operand.contains("+") || operand.contains("-")) {
			parts = operand.split("\\+|\\-");
		}
		if (parts == null) {
			ArrayList<String> registers = new ArrayList<>();
			registers.add("bx");
			registers.add("si");
			registers.add("di");
			registers.add("bp");
			if(c == '+') {
			if(operand.contains("[")) {
				return operandToIndex(variables, indexes, operand, equs);
			}
			else if (operand.contains("offset ")) {
				operand = operand.substring(operand.indexOf("offset ") + "offset ".length()).trim();
				return getOffset(operand, indexes);
			}
			else if (variables.containsKey(operand) && matches(registers, operand))
				return variables.get(operand);
			else if (getValue(operand) != null)
				return getValue(operand);
			else if (equs.containsKey(operand))
				return equs.get(operand);
			else {
			for (Integer in : indexes.keySet())
				if (indexes.get(in).equals(operand + 'b') || indexes.get(in).equals(operand + 'w'))
					return in;
			}
			}
			else {
				if(operand.contains("[")) {
					return -1*operandToIndex(variables, indexes, operand, equs);
				}
				else if (operand.contains("offset ")) {
					operand = operand.substring(operand.indexOf("offset ") + "offset ".length()).trim();
					return -1*getOffset(operand, indexes);
				}
				else if (variables.containsKey(operand) && matches(registers, operand))
					return -1*variables.get(operand);
				else if (getValue(operand) != null)
					return -1*getValue(operand);
				else if (equs.containsKey(operand))
					return -1*equs.get(operand);
				else {
				for (Integer in : indexes.keySet())
					if (indexes.get(in).equals(operand + 'b') || indexes.get(in).equals(operand + 'w'))
						return -1*in;
				}
			}
		} else {
			operand = c+operand;
			ArrayList<Character> operands = new ArrayList<>();
			for(int m = 0; m<operand.length(); m++) {
				if(operand.charAt(m) == '+')
					operands.add('+');
				else if(operand.charAt(m) == '-')
					operands.add('-');
			}
			int sum = 0;
			for (int o = 0; o < parts.length; o++) {
				parts[o] = parts[o].trim();
				ArrayList<String> registers = new ArrayList<>();
				registers.add("bx");
				registers.add("si");
				registers.add("di");
				registers.add("bp");
				if(operands.get(o) == '+') {
				if(operand.contains("[")) {
					sum += operandToIndex(variables, indexes, operand, equs);
				}
				else if (parts[o].contains("offset ")) {
					parts[o] = parts[o].substring(parts[o].indexOf("offset ") + "offset ".length()).trim();
					sum+=getOffset(parts[o], indexes);
				} else if (variables.containsKey(parts[o]) && matches(registers, parts[o])) {
					sum += variables.get(parts[o]);
				} else if (getValue(parts[o]) != null) {
					sum += getValue(parts[o]);
				} else if (equs.containsKey(parts[o])) {
					sum += equs.get(parts[o]);
				} else {
					for (Integer in : indexes.keySet())
						if (indexes.get(in).equals(parts[o] + 'b') || indexes.get(in).equals(parts[o] + 'w')) {
							sum += in;
							break;
					}
				}
				}
				else {
					if(operand.contains("[")) {
						sum -= operandToIndex(variables, indexes, operand, equs);
					}
					else if (parts[o].contains("offset ")) {
						parts[o] = parts[o].substring(parts[o].indexOf("offset ") + "offset ".length()).trim();
						sum-=getOffset(parts[o], indexes);
					} else if (variables.containsKey(parts[o]) && matches(registers, parts[o])) {
						sum -= variables.get(parts[o]);
					} else if (getValue(parts[o]) != null) {
						sum -= getValue(parts[o]);
					} else if (equs.containsKey(parts[o])) {
						sum -= equs.get(parts[o]);
					} else {
						for (Integer in : indexes.keySet())
							if (indexes.get(in).equals(parts[o] + 'b') || indexes.get(in).equals(parts[o] + 'w')) {
								sum -= in;
								break;
						}
					}
				}
			}
			return sum;
		}
		return 0;
	}
	/*
	 * private ArrayList<String> getRegisters(String operand) { ArrayList<String>
	 * registers = new ArrayList<>(); registers.add(" ax "); registers.add(" bx ");
	 * registers.add(" cx "); registers.add(" dx "); registers.add(" si ");
	 * registers.add(" di "); registers.add(" sp "); registers.add(" bp ");
	 * ArrayList<String> myRegisters = new ArrayList<>(); for(int i = 0;
	 * i<operand.length()-3; i++) { if(matches(registers,
	 * operand.charAt(i)+operand.charAt(i+1)+operand.charAt(i+2)+operand.charAt(i+3)
	 * +""))
	 * myRegisters.add(operand.charAt(i)+operand.charAt(i+1)+operand.charAt(i+2)+
	 * operand.charAt(i+3)+""); } return myRegisters; }
	 */

	private boolean matches(ArrayList<String> arr, String s) {
		for (String str : arr) {
			if (str.equals(s))
				return true;
		}
		return false;
	}

	// This method gets a string of everything after the '(' of a dup. It returns
	// the string inside the current dup (between the '(' and the ')').
	private String dupExtract(String operand) {
		String dup = "";
		int counter = 1;
		for (int i = 0; i < operand.length(); i++) {
			if (operand.charAt(i) == '(')
				counter++;
			else if (operand.charAt(i) == ')')
				counter--;
			if (counter == 0) {
				dup = operand.substring(0, i);
				break;
			}
		}
		return dup;
	}

	// Returns how much times will the dup iterate.
	private int getMul(String operand) {
		return Integer.parseInt(operand.substring(0, operand.indexOf(" dup")).trim());
	}

	// Returns the index that the loop should move to after the dup call.
	private int nextIndex(String[] operands) {
		int counter = 0;
		boolean isStarted = false;
		for (int y = 0; y < operands.length; y++)
			for (int i = 0; i < operands[y].length(); i++) {
				if (operands[y].charAt(i) == '(') {
					isStarted = true;
					counter++;
				} else if (operands[y].charAt(i) == ')')
					counter--;
				if (counter == 0 && isStarted) {
					return y;
				}
			}
		return operands.length;
	}

	private String arrayToString(String[] operands) {
		String str = "";
		for (int i = 0; i < operands.length; i++) {
			str += operands[i] + ',';
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	// This method gets the map of the indexes of the variables, the string of the
	// dup operand, an integer that says how much times will the dup iterate, an int
	// that says in what index are we in the memory and a string that has the name
	// of the variable. The method will insert the values from
	// the string to the memory as many times as needed and if there is another dup
	// inside it will call itself.
	private int indexWordDup(HashMap<Integer, String> indexes, String operand, int times, int currentIndex, String var,
			boolean isFirst) {
		for (int i = 0; i < times; i++) {

			if (operand.contains(",")) {
				String[] operands = operand.split(",");
				for (int f = 0; f < operands.length; f++) {
					operands[f] = operands[f].trim();
					if (operands[f].contains(" dup") && operands[f].contains("(")) {
						if (isFirst)
							isFirst = f == 0;
						String[] str = new String[operands.length - f];
						for (int y = f; y < operands.length; y++)
							str[y - f] = operands[y];
						String s = arrayToString(str);
						int y = getMul(s);
						s = s.substring(s.indexOf('(') + 1);
						String oper = dupExtract(s);
						currentIndex = indexWordDup(indexes, oper, y, currentIndex, var, isFirst);
						f = f + nextIndex(str);
					} else if (operands[f].contains("'")) {
						operands[f] = operands[f].replace("'", "");
						for (int io = 0; io < operands[f].length(); io++) {
							if (f == 0 && isFirst && i == 0 && io == 0) {
								indexes.put(currentIndex, var + 'w');
							} else {
								indexes.put(currentIndex, String.valueOf(currentIndex));
							}
							currentIndex++;
							indexes.put(currentIndex, String.valueOf(currentIndex));
							currentIndex++;
						}
					} else {
						if (f == 0 && i == 0 && isFirst)
							indexes.put(currentIndex, var + 'w');
						else
							indexes.put(currentIndex, String.valueOf(currentIndex));
						currentIndex++;
						indexes.put(currentIndex, String.valueOf(currentIndex));
						currentIndex++;
					}
				}
			} else {
				if (operand.contains(" dup") && operand.contains("(")) {
					int y = getMul(operand);
					String s = operand;
					s = s.substring(s.indexOf('(') + 1);
					String oper = dupExtract(s);
					currentIndex = indexWordDup(indexes, oper, y, currentIndex, var, isFirst);
				} else if (operand.contains("'")) {
					String s = operand.replace("'", "");
					for (int io = 0; io < s.length(); io++) {
						if (io == 0 && isFirst && i == 0) {
							indexes.put(currentIndex, var + 'w');
						} else {
							indexes.put(currentIndex, String.valueOf(currentIndex));
						}
						currentIndex++;
						indexes.put(currentIndex, String.valueOf(currentIndex));
						currentIndex++;
					}
				} else {
					if (i == 0 && isFirst)
						indexes.put(currentIndex, var + 'w');
					else
						indexes.put(currentIndex, String.valueOf(currentIndex));
					currentIndex++;
					indexes.put(currentIndex, String.valueOf(currentIndex));
					currentIndex++;
				}
			}
		}
		return currentIndex;
	}

	// This method gets the map of the indexes of the variables, the string of the
	// dup operand, an integer that says how much times will the dup iterate, an int
	// that says in what index are we in the memory and a string that has the name
	// of the variable. The method will insert the values from
	// the string to the memory as many times as needed and if there is another dup
	// inside it will call itself.
	private int indexByteDup(HashMap<Integer, String> indexes, String operand, int times, int currentIndex, String var,
			boolean isFirst) {
		for (int i = 0; i < times; i++) {

			if (operand.contains(",")) {
				String[] operands = operand.split(",");
				for (int f = 0; f < operands.length; f++) {
					operands[f] = operands[f].trim();
					if (operands[f].contains(" dup") && operands[f].contains("(")) {
						if (isFirst)
							isFirst = f == 0;
						String[] str = new String[operands.length - f];
						for (int y = f; y < operands.length; y++)
							str[y - f] = operands[y];
						String s = arrayToString(str);
						int y = getMul(s);
						s = s.substring(s.indexOf('(') + 1);
						String oper = dupExtract(s);
						currentIndex = indexByteDup(indexes, oper, y, currentIndex, var, isFirst);
						f = f + nextIndex(str);
					} else {
						if (operands[f].contains("'")) {
							operands[f] = operands[f].replace("'", "");
							for (int io = 0; io < operands[f].length(); io++) {
								if (f == 0 && i == 0 && io == 0 && isFirst) {
									indexes.put(currentIndex, var + 'b');
								} else {
									indexes.put(currentIndex, String.valueOf(currentIndex));
								}
								currentIndex++;
							}
						} else {
							if (f == 0 && i == 0 && isFirst)
								indexes.put(currentIndex, var + 'b');
							else
								indexes.put(currentIndex, String.valueOf(currentIndex));
							currentIndex++;
						}
					}
				}
			} else {
				if (operand.contains(" dup") && operand.contains("(")) {
					int y = getMul(operand);
					String s = operand;
					s = s.substring(s.indexOf('(') + 1);
					String oper = dupExtract(operand);
					currentIndex = indexByteDup(indexes, oper, y, currentIndex, var, isFirst);
				} else if (operand.contains("'")) {
					String s = operand.replace("'", "");
					for (int io = 0; io < s.length(); io++) {
						if (io == 0 && isFirst && i == 0) {
							indexes.put(currentIndex, var + 'b');
						} else {
							indexes.put(currentIndex, String.valueOf(currentIndex));
						}
						currentIndex++;
					}
				} else if (i == 0 && isFirst) {
					indexes.put(currentIndex, var + 'b');
					currentIndex++;
				} else {
					indexes.put(currentIndex, String.valueOf(currentIndex));
					currentIndex++;
				}
			}
		}
		return currentIndex;
	}

	// This method gets the map of the indexes of the variables, the string of the
	// dup operand, an integer that says how much times will the dup iterate, an int
	// that says in what index are we in the memory, string that has the name of the
	// variable and a
	// map of the variables and their values. The method will insert the values from
	// the string to the memory as many times as needed and if there is another dup
	// inside it will call itself.
	private int varWordDup(HashMap<Integer, String> indexes, String operand, int times, int currentIndex, String var,
			boolean isFirst, HashMap<String, Integer> variables) {
		for (int i = 0; i < times; i++) {

			if (operand.contains(",")) {
				String[] operands = operand.split(",");
				for (int f = 0; f < operands.length; f++) {
					operands[f] = operands[f].trim();
					if (operands[f].contains(" dup") && operands[f].contains("(")) {
						if (isFirst)
							isFirst = f == 0;
						String[] str = new String[operands.length - f];
						for (int y = f; y < operands.length; y++)
							str[y - f] = operands[y];
						String s = arrayToString(str);
						int y = getMul(s);
						s = s.substring(s.indexOf('(') + 1);
						String oper = dupExtract(s);
						currentIndex = varWordDup(indexes, oper, y, currentIndex, var, isFirst, variables);
						f = f + nextIndex(str);
					} else {
						if (operands[f].contains("'")) {
							operands[f] = operands[f].replace("'", "");
							for (int io = 0; io < operands[f].length(); io++) {
								int value = (int) operands[f].charAt(io);
								int[] arr = getByteValues(value);
								value = arr[0];
								int high = arr[1];
								if (f == 0 && isFirst && i == 0 && io == 0) {
									variables.put(var + 'w', value);
								} else
									variables.put(String.valueOf(currentIndex), value);
								currentIndex++;
								variables.put(String.valueOf(currentIndex), high);
								currentIndex++;
							}
						} else {
							int value = 0;
							int high = 0;
							if (operands[f].contains("offset ")) {
								operands[f] = operands[f].substring(operands[f].indexOf("offset ") + "offset ".length())
										.trim();
							}
							if (f == 0 && isFirst && i == 0) {
								if (operands[f].equals("?")) {
									variables.put(var + 'w', 0);
								} else if (indexes.containsValue(operands[f] + 'b')
										|| indexes.containsValue(operands[f] + 'w')) {
									for (Integer in : indexes.keySet())
										if (indexes.get(in).equals(operands[f] + 'b')
												|| indexes.get(in).equals(operands[f] + 'w')) {
											variables.put(var + 'w', in);
											break;
										}
								} else {
									value = getValue(operands[f]);
									int[] arr = getByteValues(value);
									value = arr[0];
									high = arr[1];
									variables.put(var + 'w', value);
								}
							} else {
								if (operands[f].equals("?")) {
									variables.put(String.valueOf(currentIndex), 0);
								} else if (indexes.containsValue(operands[f] + 'b')
										|| indexes.containsValue(operands[f] + 'w')) {
									for (Integer in : indexes.keySet())
										if (indexes.get(in).equals(operands[f] + 'b')
												|| indexes.get(in).equals(operands[f] + 'w')) {
											variables.put(String.valueOf(currentIndex), in);
											break;
										}
								} else {
									value = getValue(operands[f]);
									int[] arr = getByteValues(value);
									value = arr[0];
									high = arr[1];
									variables.put(String.valueOf(currentIndex), value);
								}
							}
							currentIndex++;
							variables.put(String.valueOf(currentIndex), high);
							currentIndex++;
						}
					}
				}
			} else {

				if (operand.contains(" dup") && operand.contains("(")) {
					int y = getMul(operand);
					String s = operand;
					s = s.substring(s.indexOf('(') + 1);
					String oper = dupExtract(s);
					currentIndex = varWordDup(indexes, oper, y, currentIndex, var, isFirst, variables);
				} else {
					if (operand.contains("'")) {
						String s = operand.replace("'", "");
						for (int io = 0; io < s.length(); io++) {
							int value = (int) s.charAt(io);
							int[] arr = getByteValues(value);
							value = arr[0];
							int high = arr[1];
							if (isFirst && i == 0 && io == 0) {
								variables.put(var + 'w', value);
							} else
								variables.put(String.valueOf(currentIndex), value);
							currentIndex++;
							variables.put(String.valueOf(currentIndex), high);
							currentIndex++;
						}
					} else {
						int value = 0;
						int high = 0;
						if (operand.contains("offset ")) {
							operand = operand.substring(operand.indexOf("offset ") + "offset ".length()).trim();
						}
						if (isFirst && i == 0) {
							if (operand.equals("?")) {
								variables.put(var + 'w', 0);
							} else if (indexes.containsValue(operand + 'b') || indexes.containsValue(operand + 'w')) {
								for (Integer in : indexes.keySet())
									if (indexes.get(in).equals(operand + 'b')
											|| indexes.get(in).equals(operand + 'w')) {
										variables.put(var + 'w', in);
										break;
									}
							} else {
								value = getValue(operand);
								int[] arr = getByteValues(value);
								value = arr[0];
								high = arr[1];
								variables.put(var + 'w', getValue(operand));
							}
						} else {
							if (operand.equals("?")) {
								variables.put(String.valueOf(currentIndex), 0);
							} else if (indexes.containsValue(operand + 'b') || indexes.containsValue(operand + 'w')) {
								for (Integer in : indexes.keySet())
									if (indexes.get(in).equals(operand + 'b')
											|| indexes.get(in).equals(operand + 'w')) {
										variables.put(String.valueOf(currentIndex), in);
										break;
									}
							} else {
								value = getValue(operand);
								int[] arr = getByteValues(value);
								value = arr[0];
								high = arr[1];
								variables.put(String.valueOf(currentIndex), value);
							}
						}
						currentIndex++;
						variables.put(String.valueOf(currentIndex), high);
						currentIndex++;
					}
				}
			}
		}
		return currentIndex;
	}

	// This method gets the map of the indexes of the variables, the string of the
	// dup operand, an integer that says how much times will the dup iterate, an int
	// that says in what index are we in the memory, string that has the name of the
	// variable and a
	// map of the variables and their values. The method will insert the values from
	// the string to the memory as many times as needed and if there is another dup
	// inside it will call itself.
	private int varByteDup(HashMap<Integer, String> indexes, String operand, int times, int currentIndex, String var,
			boolean isFirst, HashMap<String, Integer> variables) {
		for (int i = 0; i < times; i++) {

			if (operand.contains(",")) {
				String[] operands = operand.split(",");
				for (int f = 0; f < operands.length; f++) {
					operands[f] = operands[f].trim();
					if (operands[f].contains(" dup") && operands[f].contains("(")) {
						if (isFirst)
							isFirst = f == 0;
						String[] str = new String[operands.length - f];
						for (int y = f; y < operands.length; y++)
							str[y - f] = operands[y];
						String s = arrayToString(str);
						int y = getMul(s);
						s = s.substring(s.indexOf('(') + 1);
						String oper = dupExtract(s);
						currentIndex = varByteDup(indexes, oper, y, currentIndex, var, isFirst, variables);
						f = f + nextIndex(str);
					} else {
						if (operands[f].contains("'")) {
							operands[f] = operands[f].replace("'", "");
							for (int io = 0; io < operands[f].length(); io++) {
								if (f == 0 && isFirst && i == 0 && io == 0) {
									variables.put(var + 'b', (int) operands[f].charAt(io));
								} else
									variables.put(String.valueOf(currentIndex), (int) operands[f].charAt(io));
								currentIndex++;
							}
						} else {
							if (operands[f].contains("offset ")) {
								operands[f] = operands[f].substring(operands[f].indexOf("offset ") + "offset ".length())
										.trim();
							}
							if (f == 0 && i == 0 && isFirst) {
								if (operands[f].equals("?")) {
									variables.put(var + 'b', 0);
								} else if (indexes.containsValue(operands[f] + 'b')
										|| indexes.containsValue(operands[f] + 'w')) {
									for (Integer in : indexes.keySet())
										if (indexes.get(in).equals(operands[f] + 'b')
												|| indexes.get(in).equals(operands[f] + 'w')) {
											variables.put(var + 'b', in);
											break;
										}
								} else
									variables.put(var + 'b', getValue(operands[f]));
							} else {
								if (operands[f].equals("?")) {
									variables.put(String.valueOf(currentIndex), 0);
								} else if (indexes.containsValue(operands[f] + 'b')
										|| indexes.containsValue(operands[f] + 'w')) {
									for (Integer in : indexes.keySet())
										if (indexes.get(in).equals(operands[f] + 'b')
												|| indexes.get(in).equals(operands[f] + 'w')) {
											variables.put(String.valueOf(currentIndex), in);
											break;
										}
								} else
									variables.put(String.valueOf(currentIndex), getValue(operands[f]));
							}
							currentIndex++;
						}
					}
				}
			} else {
				if (operand.contains("offset ")) {
					operand = operand.substring(operand.indexOf("offset ") + "offset ".length()).trim();
				}
				if (operand.contains(" dup") && operand.contains("(")) {
					int y = getMul(operand);
					String s = operand;
					s = s.substring(s.indexOf('(') + 1);
					String oper = dupExtract(s);
					currentIndex = varByteDup(indexes, oper, y, currentIndex, var, isFirst, variables);
				} else {
					if (operand.contains("'")) {
						String s = operand.replace("'", "");
						for (int io = 0; io < s.length(); io++) {
							if (isFirst && i == 0 && io == 0) {
								variables.put(var + 'b', (int) s.charAt(io));
							} else
								variables.put(String.valueOf(currentIndex), (int) s.charAt(io));
							currentIndex++;
						}
					} else {
						if (i == 0 && isFirst) {
							if (operand.equals("?")) {
								variables.put(var + 'b', 0);
							} else if (indexes.containsValue(operand + 'b') || indexes.containsValue(operand + 'w')) {
								for (Integer in : indexes.keySet())
									if (indexes.get(in).equals(operand + 'b')
											|| indexes.get(in).equals(operand + 'w')) {
										variables.put(var + 'b', in);
										break;
									}
							} else
								variables.put(var + 'b', getValue(operand));
						} else {
							if (operand.equals("?")) {
								variables.put(String.valueOf(currentIndex), 0);
							} else if (indexes.containsValue(operand + 'b') || indexes.containsValue(operand + 'w')) {
								for (Integer in : indexes.keySet())
									if (indexes.get(in).equals(operand + 'b')
											|| indexes.get(in).equals(operand + 'w')) {
										variables.put(String.valueOf(currentIndex), in);
										break;
									}
							} else
								variables.put(String.valueOf(currentIndex), getValue(operand));
						}
						currentIndex++;
					}
				}
			}
		}
		return currentIndex;
	}

	// Returns the two's complement representation of the string.

	static String findTwoscomplement(StringBuffer str) {
		int n = str.length();

		// Traverse the string to get first '1' from
		// the last of string
		int i;
		for (i = n - 1; i >= 0; i--)
			if (str.charAt(i) == '1')
				break;

		// If there exists no '1' concat 1 at the
		// starting of string
		if (i == -1)
			return "1" + str;

		// Continue traversal after the position of
		// first '1'
		for (int k = i - 1; k >= 0; k--) {
			// Just flip the values
			if (str.charAt(k) == '1')
				str.replace(k, k + 1, "0");
			else
				str.replace(k, k + 1, "1");
		}

		// return the modified string
		return str.toString();
	}

	private void Mov(String operand, String operandTwo, HashMap<String, Integer> variables,
			HashMap<Integer, String> indexes, HashMap<String, Integer> equs, Stack<Integer> stack) {
		Boolean isWord = isWord(variables, operand, operandTwo);
		operand = deletePtr(operand);
		operandTwo = deletePtr(operandTwo);
		int operand2 = 0;
		Integer inde = null;
		if (operand.contains("+") || operand.contains("[") || operand.contains("-")) {
			inde = operandToIndex(variables, indexes, operand, equs);
			if (isMahsanit(operand))
				inde = 255 - inde;
		}
		
		operand2 = getOperandValueIsIndex(operandTwo, variables, indexes, equs, isWord, stack)[0];
		
		if (!isWord) {
			if (inde != null) {
				if (!isMahsanit(operand)) {
					int[] arr = getByteValues(operand2);
					operand2 = arr[0];
					variables.put(indexes.get(inde), operand2);
				} else {
					int[] arr = getByteValues(operand2);
					operand2 = arr[0];
					stack.setElementAt(operand2, inde);
				}
			} else {
				int[] arr = getByteValues(operand2);
				operand2 = arr[0];
				if (isRegister(operand))
					insertRegister(operand, operand2, variables);
				else
					setVariable(operand, variables, operand2);
			}
		} else {
			if (inde != null) {
				if (!isMahsanit(operand)) {
					if(operand2 == 80000) {
						variables.put(indexes.get(inde), 80000);
						variables.put(indexes.get(inde + 1), 0);
					}
					else {
					int[] arr = getByteValues(operand2);
					operand2 = arr[0];
					int high = arr[1];
					variables.put(indexes.get(inde), operand2);
					variables.put(indexes.get(inde + 1), high);
					}
				} else {
					if(operand2 == 80000) {
						stack.setElementAt(80000, inde);
						stack.setElementAt(0, inde - 1);
					}
					else {
					int[] arr = getByteValues(operand2);
					operand2 = arr[0];
					int high = arr[1];
					stack.setElementAt(operand2, inde);
					stack.setElementAt(high, inde - 1);
					}
				}
			} else {
				if(operand2 == 80000) {
					if (isRegister(operand)) {
						insertRegister(operand, operand2, variables);
					} else {
						setVariable(operand, variables, 80000);
						variables.put(indexes.get(getOffset(operand, indexes) + 1), 0);
					}
				}
				else if (isRegister(operand)) {
					insertRegister(operand, operand2, variables);
				} else {
					int[] arr = getByteValues(operand2);
					operand2 = arr[0];
					int high = arr[1];
					setVariable(operand, variables, operand2);
					variables.put(indexes.get(getOffset(operand, indexes) + 1), high);
				}
			}
		}
	}

	private Integer[] getOperandValueIsIndex(String operand, HashMap<String, Integer> variables, HashMap<Integer, String> indexes, HashMap<String, Integer> equs, boolean isWord, Stack<Integer> stack) {
		int operand2 = 0;
		Integer inde = null;
		if (operand.contains("+") || operand.contains("[") || operand.contains("-")) {
			int ind = operandToIndex(variables, indexes, operand, equs);
			if(ind < 0)
				operand2 = 0;
			else {
			inde = ind;
			if (!isAllNums(operand)) {
				if(!toMemory(operand, equs)) {
					operand2 = ind;
				}
				else if (!isMahsanit(operand)) {
					if (isWord) {
						operand2 = numberJoiner(variables.get(indexes.get(ind)), variables.get(indexes.get(ind + 1)));
					} else
						operand2 = variables.get(indexes.get(ind));
				} else {
					ind = 255 - ind;
					inde = 255 - inde;
					if (isWord) {
						operand2 = numberJoiner(stack.elementAt(ind), stack.elementAt(ind - 1));
					} else
						operand2 = stack.elementAt(ind);
				}
			} else {
				operand2 = ind;
			}
			}
		} else if (!isAllNums(operand)) {
			if (operand.contains("offset ")) {
				operand = operand.substring(operand.indexOf("offset ") + "offset ".length()).trim();
				operand2 = getOffset(operand, indexes);
			} else {
				if(operand.equals("@data")) {
					operand2 = 80000;
				}
				else if (isRegister(operand)) {
					operand2 = getRegisterValue(operand, variables);
				} else {
					if (equs.containsKey(operand))
						operand2 = equs.get(operand);
					else if (isWord) {
						operand2 = numberJoiner(getVariable(operand, variables), variables.get(indexes.get(getOffset(operand, indexes) + 1)));
					} else
						operand2 = getVariable(operand, variables);
				}
			}
		} else {
			operand2 = getValue(operand);
		}
		Integer[] arr = new Integer[2];
		arr[0] = operand2;
		arr[1] = inde;
		return arr;
	}
	
	private boolean toMemory(String operand, HashMap<String, Integer> equs) {
		if(!operand.contains("offset")) {
			if(!isOnlyEquAndNums(operand, equs))
			return true;
			else
				return false;
		}
		if(!operand.contains("["))
			return false;
		int isIn = 0;
		for(int i = 0; i<operand.length()-"offset".length(); i++) {
			if(operand.charAt(i) == '[')
				isIn++;
			if(operand.charAt(i) == ']')
				isIn--;
			if(isIn == 0 && operand.substring(i, i+"offset".length()).equals("offset")) {
				return false;
			}
		}
		return true;
	}

	private boolean isOnlyEquAndNums(String operand, HashMap<String, Integer> equs) {
		if(!operand.contains("[")) {
		String[] str = operand.split("\\+|\\-");
		for(int i = 0; i<str.length; i++) {
			str[i] = str[i].trim();
			if(getValue(str[i]) == null &&  !equs.containsKey(str[i]))
				return false;
		}
		return true;
		}
		return false;
	}
	
	// This method gets an operand and returns it's offset.
	private int getOffset(String operand, HashMap<Integer, String> indexes) {
		operand = operand.trim();
		if (indexes.containsValue(operand + 'b') || indexes.containsValue(operand + 'w')) {
			for (Integer in : indexes.keySet())
				if (indexes.get(in).equals(operand + 'b') || indexes.get(in).equals(operand + 'w')) {
					return in;
				}
		}
		return 0;
	}

	// Returns the value of the low side of the register.
	private int getLowRegisterValue(String register, HashMap<String, Integer> variables) {
		BigInteger i = new BigInteger(String.valueOf(variables.get(register)));
		String s = i.toString(2);
		for (; s.length() < 16;)
			s = '0' + s;
		BigInteger b = new BigInteger(s.substring(s.length() - 8), 2);
		return b.intValue();
	}

	// Returns the value of the high side of the register.
	private int getHighRegisterValue(String register, HashMap<String, Integer> variables) {
		BigInteger i = new BigInteger(String.valueOf(variables.get(register)));
		String s = i.toString(2);
		for (; s.length() < 16;)
			s = '0' + s;
		BigInteger b = new BigInteger(s.substring(0, s.length() - 8), 2);
		return b.intValue();
	}

	// Sets the value of the low side of the register.
	private void SetLowRegisterValue(String register, HashMap<String, Integer> variables, int newValue) {
		String s = getWordStr(newValue).substring(getWordStr(newValue).length()-8);
		String bb = getWordStr(getHighRegisterValue(register, variables)).substring(8);
		BigInteger result = new BigInteger(bb + s, 2);
		variables.put(register, result.intValue());
	}

	// Sets the value of the high side of the register.
	private void SetHighRegisterValue(String register, HashMap<String, Integer> variables, int newValue) {
		String s = getWordStr(newValue).substring(getWordStr(newValue).length()-8);
		String bb = getWordStr(getLowRegisterValue(register, variables)).substring(8);
		BigInteger result = new BigInteger(s + bb, 2);
		variables.put(register, result.intValue());
	}

	// The method gets two numbers that together represents a word. It finds the
	// word in int value and returns it.

	private int numberJoiner(int low, int high) {
		String s = getWordStr(low);
		s = s.substring(8);
		String s2 = getWordStr(high);
		s2 = s2.substring(8);
		BigInteger result = new BigInteger(s2 + s, 2);
		return result.intValue();
	}

	// This method checks if a register is low.

	private boolean isLow(String register) {
		if (register.equals("bl") || register.equals("cl") || register.equals("al") || register.equals("dl"))
			return true;
		return false;
	}

	// This method checks if a register is high.

	private boolean isHigh(String register) {
		if (register.equals("bh") || register.equals("ch") || register.equals("ah") || register.equals("dh"))
			return true;
		return false;
	}

	// This method gets a string and returns it without the "word ptr" or "byte
	// ptr".

	private String deletePtr(String operand) {
		String s1 = "";
		String s2 = "";
		if (operand.contains("word ptr")) {
			s1 = operand.substring(0, operand.indexOf("word ptr"));
			s2 = operand.substring(operand.indexOf("word ptr") + "word ptr".length());
			operand = s1 + s2;
		}
		if (operand.contains("byte ptr")) {
			s1 = operand.substring(0, operand.indexOf("byte ptr"));
			s2 = operand.substring(operand.indexOf("byte ptr") + "byte ptr".length());
			operand = s1 + s2;
		}
		return operand.trim();
	}

	// Gets the value of the variable.

	private int getVariable(String var, HashMap<String, Integer> variables) {
		if (variables.containsKey(var + 'w'))
			return variables.get(var + 'w');
		else
			return variables.get(var + 'b');
	}

	// Inserts the value into the variable.

	private void setVariable(String var, HashMap<String, Integer> variables, int value) {
		if (variables.containsKey(var + 'w'))
			variables.put(var + 'w', value);
		else
			variables.put(var + 'b', value);
	}

	// This method gets the name of the register, the value that you want to insert
	// to it and the map of variables and it inserts the value into the register.

	private void insertRegister(String register, int value, HashMap<String, Integer> variables) {
		if (isLow(register)) {
			SetLowRegisterValue(register.replace('l', 'x'), variables, value);
		} else if (isHigh(register)) {
			SetHighRegisterValue(register.replace('h', 'x'), variables, value);
		} else {
			variables.put(register, value);
		}
	}

	// This method gets an int and splits it into two bytes. Every byte has the
	// value it is supposed to have (it returns an array that its first number is
	// the low byte and the second is the high byte).

	private int[] getByteValues(int i) {
		int[] arr = new int[2];
		String s = getWordStr(i);
		arr[1] = new BigInteger(s.substring(0, s.length() - 8), 2).intValue();
		arr[0] = new BigInteger(s.substring(s.length() - 8), 2).intValue();
		return arr;
	}

	private String getWordStr(int i) {
		BigInteger b = new BigInteger(String.valueOf(i));
		String s = b.toString(2);
		if (s.charAt(0) == '-') {
			s = s.substring(1);
			StringBuffer str = new StringBuffer(s);
			s = findTwoscomplement(str);
			for (; s.length() < 16;)
				s = '1' + s;
		} else {
			for (; s.length() < 16;)
				s = '0' + s;

		}
		return s;
	}

	private int[] getWordValues(int operand){ 

		int[] arr = new int[2];
		String str = new BigInteger(operand+"").toString(2); 

		if(str.charAt(0) == '-'){
		str = str.substring(1);
		StringBuffer str2 = new StringBuffer(str);
		str = findTwoscomplement(str2);
		} 
		if(str.length()>16) {
		arr[0] = new BigInteger(str.substring(str.length()-16), 2).intValue();
		arr[1] = new BigInteger(str.substring(0, str.length()-16), 2).intValue();
		}
		else {
			arr[0] = new BigInteger(str, 2).intValue();
			arr[1] = 0;
		}
		return arr;
		}
	
	public static void main(String[] args) {
		new Input();
	}

}
