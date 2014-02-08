package PROJECT_2_LONG_VER_1_2;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Main class- Takes input file and performs the arithmetic operations as defined.
 * The output is directly printed onto console.
 */
public class BigNumberOperation {

	static final int nBase = 100000000;

	public static void main(String[] args) {

		try {
			FileInputStream in = new FileInputStream("/users/dany/downloads/project_2_inputFile1.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				try {
					boolean isPrevNumber = false;
					boolean isNegativeNumber = false;

					if (!"0".equals(currentLine)) {
						Stack<String> operatorStack = null;
						Stack<String> operandStack = null;
						String operator = null;
						int index = 0;
						StringBuffer tempNumber = new StringBuffer();
						while (null != currentLine
								&& index < currentLine.length()
								&& ' ' != currentLine.charAt(index)) {
							char op = currentLine.charAt(index);

							if (Character.isDigit(op)) {
								while (Character.isDigit(op)) {
									tempNumber.append(op);
									index++;
									if (index < currentLine.length()) {
										op = currentLine.charAt(index);
									} else {
										break;
									}
								}
								if (null != tempNumber
										&& !"".equals(tempNumber)) {
									if (null == operandStack) {
										operandStack = new Stack<String>();
										operandStack
												.push(tempNumber.toString());
									} else {
										operandStack
												.push(tempNumber.toString());
									}
									isPrevNumber = true;
								}
							}
							if (op == '+' || op == '-' || op == '*'
									|| op == '^' || op == '(' || op == ')') {

								if (null != operatorStack) {
									if (op == ')') {
										while (!operatorStack.empty()) {
											operator = operatorStack.pop();
											//Perform popped operator action on the operands in stack
											if (operator.charAt(0) != '('
													&& null != operandStack) {
												String op1 = operandStack.pop();
												String op2 = operandStack.pop();
												String output = performOperation(
														operator.charAt(0),
														op1, op2);
												operandStack.push(output);
											} else {
												break;
											}
										}
										index++;
										continue;
									}
									char topOperator = '0';
									if (operatorStack.size() > 0
											&& null != operatorStack.peek()) {
										topOperator = operatorStack.peek()
												.charAt(0);
									}
									if (op == '('
											|| topOperator == '('
											|| precedenceLevel(topOperator) < precedenceLevel(op)) {
										//Negative numbers not supported
										if (op == '-' && !isPrevNumber) {
											System.out.println("Negative Numbers are not supported.");
											isNegativeNumber = true;
											break;
										}
										operatorStack.push(Character
												.toString(op));
										isPrevNumber = false;
									}

									else {
										operator = operatorStack.pop();
										if (null != operandStack
												&& '(' != operator.charAt(0)) {
											String op1 = operandStack.pop();
											String op2 = operandStack.pop();
											String output;
											output = performOperation(
													operator.charAt(0), op1,
													op2);
											operandStack
													.push(output.toString());
										}
										operatorStack.push(Character
												.toString(op));
										isPrevNumber = false;
									}
								} else {
									operatorStack = new Stack<String>();
									operatorStack.push(Character.toString(op));
									isPrevNumber = false;
								}
								tempNumber = new StringBuffer();
							} else if (!Character.isDigit(op)) {
								System.out.println("Unknown Operation");
								break;
							}
							index++;
						}
						if (!isNegativeNumber && operatorStack.peek() != null) {
							//Push the output of the operation on operands back into stack
							while (!operatorStack.empty()) {
								operator = operatorStack.pop();
								if (null != operandStack) {
									String op1 = operandStack.pop();
									String op2 = operandStack.pop();
									String output = performOperation(
											operator.charAt(0), op1, op2);
									operandStack.push(output.toString());
								}
							}
						}
						//Pop the final output from the operand stack
						System.out.println(operandStack.pop());
					} else {
						//Exit input=0
						System.out.println("Bye!!!");
					}
				} catch (Exception e) {
					//If invalid expressions is detected
					System.out.println("Syntax Error");
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method for calling respective operation based on the operator encountered
	 * 
	 */
	private static String performOperation(char op, String op1, String op2)
			throws IllegalArgumentException {

		switch (op) {
		case '+':
			return LinkedListPolynomial.addNumber(op1, op2, nBase);
		case '-':
			return LinkedListPolynomial.subractNumber(op2, op1, nBase);
		case '*':
			return LinkedListPolynomial.multiplicationHandler(op1, op2,
					nBase);
		case '^':
			return LinkedListPolynomial.ExponentialHandler(op2, Integer.parseInt(op1));
		default:
			throw new IllegalArgumentException("Operator unknown: " + op);
		}
	}

	
	/**
	 * Method for checking precedence level of the operators
	 * '0' for unidentified operators
	 */
	private static int precedenceLevel(char op) throws IllegalArgumentException {
		switch (op) {
		case '+':
		case '-':
			return 0;
		case '*':
			return 1;
		case '/':
			return 2;
		case '^':
			return 3;
		case '0':
			return -1;
		default:
			throw new IllegalArgumentException("Operator unknown: " + op);
		}
	}

}
