package mvc;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import logic.Input;

public class Model {
	

	//ArrayList<String> splitEquation = new ArrayList<String>();
	
	public double solve(Input firstInput, Input operator, Input secondInput) {
		
		double firstNumber = firstInput.getDouble();
		double secondNumber = secondInput.getDouble();
		double answer;
		
		switch (operator.getText()) {
			case "*" : answer = multiply(firstNumber, secondNumber);	break;
			case "/" : answer = divide(firstNumber, secondNumber);		break;
			case "+" : answer = add(firstNumber, secondNumber);			break;
			case "-" : answer = subtract(firstNumber, secondNumber);	break;
			case "^" : answer = indice(firstNumber, secondNumber);		break;
			case "√" : answer = root(firstNumber, secondNumber);		break;
			case "%" : answer = modulus(firstNumber, secondNumber);		break;
			default:
				throw new IllegalStateException("Invalid operator.");
		}
		
		return answer;
	}
	
	
	
	private double indice(double firstNumber, double secondNumber) {
		
		return Math.pow(firstNumber, secondNumber);
	}

	
	private double root(double firstNumber, double secondNumber) {
		
		return firstNumber * Math.sqrt(secondNumber);
	}
	
	
	private double modulus(double firstNumber, double secondNumber) {
		
		long firstNumberLong = Long.parseLong(String.valueOf(firstNumber));
		long secondNumberLong = Long.parseLong(String.valueOf(secondNumber));
		
		return Math.floorMod(firstNumberLong, secondNumberLong);
	}

	
	private double multiply(double firstNumber, double secondNumber) {
		
		return firstNumber * secondNumber;
	}
	
	
	private double divide(double firstNumber, double secondNumber) {
		
		return firstNumber / secondNumber;
	}
	
	
	private double add(double firstNumber, double secondNumber) {
		
		return firstNumber + secondNumber;
	}
	
	
	private double subtract(double firstNumber, double secondNumber) {
		
		return firstNumber - secondNumber;
	}
	
	
	
	

	
	

}
