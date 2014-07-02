package listExample1;

public class CalcApp {

	public static void main(String[] args) 
	{
		CalcImplementation ci = new CalcImplementation();
		//2+(3-1)+(3+(45-3))
		ci.getUserInput();
		//ci.translateInput();
		ci.otherTranslateInput();
		ci.getTranslateInput();
		ci.reversePolishNotation();
		ci.getReversePolishNotation();
		ci.makeResult();
		ci.getResult();
	}

}
