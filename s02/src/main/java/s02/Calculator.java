package s02;

public class Calculator {
	private int firstNum;
	private int secondNum;
	
	public int getFirstNum() {
		return firstNum;
	}

	public int getSecondNum() {
		return secondNum;
	}
	
	public void setFirstNum(int firstNum) {  
		this.firstNum = firstNum;
	}

	public void setSecondNum(int secondNum) {
		this.secondNum = secondNum;
	}

	public void Addition() {
		System.out.println("ADD");
		int result = firstNum + secondNum;
		System.out.println(result);
	}
	
	public void Subtraction() {
		System.out.println("SUBT");
		int result = firstNum - secondNum;
		System.out.println(result);
	}
	public void Multiplication() {
		System.out.println("MUL");
		int result = firstNum * secondNum;
		System.out.println(result);
	}
	public void Division() {
		System.out.println("DIV");
		int result = firstNum / secondNum;
		System.out.println(result);
	}	
}
