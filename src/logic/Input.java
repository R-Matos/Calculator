package logic;

public class Input {
	
	private String text;
	private boolean isOperator;
	
	
	public Input(String rawInput, boolean isOperator) {
		
		String input = rawInput;
		
		input = input.replaceAll("x", "*");						//Replace any 'x' with multiplication sign '*'
		input = input.replaceAll("\\s", "");					//Removes any whitespace
		
		this.text = input;
		this.isOperator = isOperator;
	}
	
	
	public String getText() {
		return text;
	}
	
	public double getDouble() {
		return Double.parseDouble(text);
	}
	
	
	
	
	
	
	
	public boolean isValid() {
		
		boolean isValid = false;
		
		if (isOperator) {
			isValid = operatorCheck();
		} else {
			isValid = numberCheck();
		}
		
		return isValid;
	}
	
	
	
	
	
	
	private boolean operatorCheck() {
		
		//If symbol isnt a single character
		if (!(text.length() == 1)) {
			throw new IllegalStateException("Operator > 1 character.");
		}
		

		switch (text) {
				case "x" : break;
				case "/" : break;
				case "+" : break;
				case "-" : break;
				case "(" : break;
				case ")" : break;
				case "^" : break;
				case "√" : break;
				case "%" : break;
			default:
				return false;
		}

		return true;
	}
	
	
	private boolean numberCheck() {

		String[] characters = text.split("");
		
		
		for (String character : characters) {

			switch (character) {
				case "0":	break;
				case "1":	break;
				case "2":	break;
				case "3":	break;
				case "4":	break;
				case "5":	break;
				case "6":	break;
				case "7":	break;
				case "8":	break;
				case "9":	break;
				case ".":	break;
			default:
				return false;
			}
		}
		
		return true;
	}

	
}
