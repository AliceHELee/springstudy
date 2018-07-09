package s03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		//Calculator calculator = new Calculator();
		
     /* MyCalculator myCalculator = new MyCalculator();
		myCalculator.setCalculator(new Calculator());
		myCalculator.setFirstNum(30);
		myCalculator.setSecondNum(5);*/
		
		String configLocation = "classpath:applicationCTX.xml";  								// string type의 객체를 담는 주소 객체 String
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);		//ref: ctx 생성만 하였음. bean을 만들지않음. spring에서 함IoC 
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);			//만들어진 bean을 연결하여 사용만 함
		 																						//Dependency 줄여줌
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
		
	}

}
