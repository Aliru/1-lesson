package listExample1;

import javax.swing.JOptionPane;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CalcImplementation //implements calc
{
	private double result;
	private String input;
	private  ArrayList <String> makedInput = new ArrayList <String>();
	private  ArrayList <String> output = new ArrayList <String>();
	private ArrayList <String> stack = new ArrayList <String>();
	
	public void getUserInput()
	{
		input = JOptionPane.showInputDialog( "Please input statement" );
	}
	
	public void translateInput()
	{
		Pattern pattern = Pattern.compile("[+-]?[0-9]*\\.?[0-9]+");
		Matcher m = pattern.matcher(input);
		int posNonDigit = 0;
		
		while(m.find(posNonDigit))
		{
			makedInput.add(input.substring(m.start(), m.end()));
			posNonDigit = m.end();
			System.out.println(input.substring(m.start(), m.end()));
			System.out.println(posNonDigit + "!!!!!!!");
			while( (posNonDigit < input.length()) && (!Character.isDigit(input.charAt(posNonDigit))))
			{
				posNonDigit++;
			}
			//2+(3-1)*(3+(45-3)-9/7)
			makedInput.add(input.substring(m.end() ,posNonDigit));
		}
		
	}
	
	public void getTranslateInput()
	{
		System.out.println(makedInput);
	}
	
	public void reversePolishNotation()
	{
		for (int i = 0; i < makedInput.size(); i += 2)
		{
			output.add(makedInput.get(i));
			if ((i + 1) < makedInput.size())
			{
			String str = makedInput.get(i + 1);
			int operator = 0;
			while(operator < str.length())
			{
				switch(str.substring(operator, operator + 1))
				{
				case "+":
				case "-":
					if (stack.isEmpty())
						stack.add(str.substring(operator, operator + 1));
					else
						{
							for (int j = stack.size() - 1; j >= 0 ; j--)
							{
								if (!stack.get(j).equals("("))
								{
									output.add(stack.get(j));
									stack.remove(j);
								}
								else
									break;
							}
						    stack.add(str.substring(operator, operator + 1));
						}
				break;
				case "*":
				case "/":
					if (stack.isEmpty())
						stack.add(str.substring(operator, operator + 1));
					else
						{
							for (int j = stack.size() - 1; j >= 0 ; j--)
							{
								if (stack.get(j).equals("*") || stack.get(j).equals("/") || stack.get(j).equals("^"))
								{
									output.add(stack.get(j));
									stack.remove(j);
								}
								else
									break;
							}
						    stack.add(str.substring(operator, operator + 1));
						}
				break;
				case "^":
					if (stack.isEmpty())
						stack.add(str.substring(operator, operator + 1));
					else
						{
							for (int j = stack.size() - 1; j >= 0 ; j--)
							{
								if (stack.get(j).equals("^"))
								{
									output.add(stack.get(j));
									stack.remove(j);
								}
								else
									break;
							}
						    stack.add(str.substring(operator, operator + 1));
						}
					break;
				case "(":
						stack.add(str.substring(operator, operator + 1));
					break;
				case ")":
					if (stack.isEmpty())
						System.out.println("Error");
					else
					{
						int j = stack.size() - 1;
						while (!stack.get(j).equals("("))
						{
							output.add(stack.get(j));
							stack.remove(j);
							j--;
						}
						stack.remove(j);
					}
					break;
				default:
					JOptionPane.showMessageDialog(null, input);
				break;
				}
				operator++;
			}
			}
		}
		if (!stack.isEmpty())
		{
			for (int i = stack.size() - 1; i >= 0; i--)
			{
				output.add(stack.get(i));
			}
		}
	}
	
	public void getReversePolishNotation()
	{
		System.out.println(output);
	}
	
	public void makeResult()
	{
		int i = 0;
		
		while (output.size() > 1)
		{
			Pattern pattern = Pattern.compile("[-+^*/]+");
			Matcher m = pattern.matcher(output.get(i));
			if (m.find())
			{
				System.out.println(output.get(i).length());
				switch (m.group())
				{
				
				case "+":
					this.getReversePolishNotation();
					double d1 = 0;
					d1 += (Double.parseDouble(output.get(i - 2)) + Double.parseDouble(output.get(i - 1)));
					String s1 = "" + d1;
					output.remove(i);
					output.remove(i - 1);
					output.remove(i - 2);
					output.add(i - 2, s1);
					output.trimToSize();
					i = 0;
					break;
				case "-":
					this.getReversePolishNotation();
					double d2 = 0;
					d2 += (Double.parseDouble(output.get(i - 2)) - Double.parseDouble(output.get(i - 1)));
					String s2 = "" + d2;
					output.remove(i);
					output.remove(i - 1);
					output.remove(i - 2);
					output.add(i - 2, s2);
					output.trimToSize();
					i = 0;
					break;
				case "/":
					double d3 = 0;
					d3 += (Double.parseDouble(output.get(i - 2)) / Double.parseDouble(output.get(i - 1)));
					String s3 = "" + d3;
					output.remove(i);
					output.remove(i - 1);
					output.remove(i - 2);
					output.add(i - 2, s3);
					output.trimToSize();
					i = 0;
					break;
				case "*":
					double d4 = 0;
					d4 += (Double.parseDouble(output.get(i - 2)) * Double.parseDouble(output.get(i - 1)));
					String s4 = "" + d4;
					output.remove(i);
					output.remove(i - 1);
					output.remove(i - 2);
					output.add(i - 2, s4);
					output.trimToSize();
					i = 0;
					break;
					default:
						//System.out.println("Titssss");
						break;
				}
			}
			else
				i++;
			}
		result = Double.parseDouble(output.get(0));
		}

	public void getResult()
	{
		String resultForPrint = "";
		resultForPrint = input + " = " + result;
		JOptionPane.showMessageDialog(null, resultForPrint);
	}
}
