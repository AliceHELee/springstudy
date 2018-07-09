package s03;

public class Calculator {
 
	public void addition(int firstNum, int secondNum) {
		System.out.println("ADD");
		int result = firstNum + secondNum;
		System.out.println(result);
	}
	
	public void subtraction(int firstNum, int secondNum) {
		System.out.println("SUBT");
		int result = firstNum - secondNum;
		System.out.println(result);
	}
	public void multiplication(int firstNum, int secondNum) {
		System.out.println("MUL");
		int result = firstNum * secondNum;
		System.out.println(result);
	}
	public void division(int firstNum, int secondNum) {
		System.out.println("DIV");
		int result = firstNum / secondNum;
		System.out.println(result);
	}	
}
