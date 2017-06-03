package mvc;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.Input;


//Anything affect UI gets done in controller
//	ELSE do in model

public class Controller {
	
	
	private Input firstInput;
	private Input secondInput;
	private Input operator;
	private double answer;
	private ArrayList<String> history = new ArrayList<String>();
	
	private Model model;								//Model of MVC
	
	
	@FXML
	private TextArea textArea = new TextArea();			//History
	
	@FXML
	private TextField textField;						//Current calculation
	
	@FXML
	private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, 
					btnModulus, btnMultiply, btnDivide, btnAdd, btnSubtract, btnUndo, 
					btnClear, btnOpen, btnClose, btnIndice, btnRoot, btnEquals;
	
	
	
	public Controller(Model model) {
		this.model = model;
	}
	
	
	//[start] condition-checking-methods
	
	/**
	 * Creates user input objects
	 *
	 * @since 1.0
	 */
	private void newNumber() {
		
		String userInput = textField.getText();
		
		if (firstInput == null) {
			firstInput = new Input(userInput, false);
			validityCheck(firstInput);
		} else if (secondInput == null) {
			secondInput = new Input(userInput, false);
			validityCheck(secondInput);
		 }
		
		textField.clear();
	}
	
	/**
	 * Checks if input is in a valid format.
	 * If not sets input object to null.
	 *
	 * @param  input is the user input
	 * @since 1.0
	 */
	private void validityCheck(Input input) {
		
		boolean isInputValid = input.isValid();
		
		if (isInputValid) {
			textField.setPromptText("");							//Removes error msg if present
		} else {
			
			//Removes instance of object as its incorrect
			if (firstInput.equals(input)) {
				firstInput = null;
			} else if (secondInput.equals(input)) {
				secondInput = null;
			}
			
			textField.setPromptText("Incorrect input");
		}		
	}
	
	/**
	 * After a calculation is selected by this updates all necessary variables.
	 * This includes history array, textarea and textField with the answer.
	 * Also clears previous input.
	 *
	 * @param  original is array to be reversed
	 * @return the char array which is an inversion of the param
	 * @since 1.0
	 */
	private void update() {
		
		String answerNoTrail = Double.toString(answer);
		
		//Removes trailing 0's
		answerNoTrail = answerNoTrail.indexOf(".") < 0 ? 											
				answerNoTrail : answerNoTrail.replaceAll("0*$", "").replaceAll("\\.$", "");
		
		
		
		//Adds calculation + answer to history array
		history.add(firstInput.getText() + " " + operator.getText() + " " + secondInput.getText() + " =  " 
				+ answerNoTrail + "\r");
		
		//Outputs history array to textArea
		textArea.setText("");
		
		//Updates textarea with current and previous calculations from history
		for (String calc : history) {
			textArea.appendText(calc + "\n");
			
		}
		
		//Resets variables/classes
		onClear();
		
		//Updates textfield with current answer so user can use next calculation
		textField.setText(answerNoTrail);
	}
	

	@FXML
	private void onEquals() {
		
		if (firstInput == null) {									//Just values in textfield
			return;
		}
		
		if (secondInput == null) {									//User presses to solve, as apposed to called by operator button events
			newNumber();												//Adds last number
		}
		
		
		if (secondInput == null) {									//Input error, new input was removed
			return;
		} else {
			answer = model.solve(firstInput, operator, secondInput);
			update();
		}
	}
	
	
	
	@FXML
	private void onUndo() {
		
		//Returns if no previous calculations
		if (history.size() == 0) {
			return;
		}
		
		
		onClear();
		
		//Gets previous answer
		String previousCalc = history.get(history.size()-1);
		int equalsPos = previousCalc.indexOf("=") + 1;
		String previousAnswer = previousCalc.substring(equalsPos).trim();
		
		//Sets previous answer
		firstInput = new Input(previousAnswer, false);
		textField.setText(previousAnswer);
		
		history.remove(history.size()-1);
		
		//Updates text area
		textArea.setText("");
		for (String calc : history) {
			textArea.appendText(calc + "\n");
			
		}
	}

	
	@FXML
	private void onClear() {
		textField.clear();
		firstInput = null;
		secondInput = null;
		operator = null;
		
		
	}
	//[end] condition-checking-methods
	
	
	
	//[start] number-button-events
	
	@FXML
	private void on0() {
		textField.appendText("0");
	}
	
	@FXML
	private void on1() {
		textField.appendText("1");
	}
	
	@FXML
	private void on2() {
		textField.appendText("2");
	}
	
	@FXML
	private void on3() {
		textField.appendText("3");
	}
	
	@FXML
	private void on4() {
		textField.appendText("4");
	}
	
	@FXML
	private void on5() {
		textField.appendText("5");
	}
	
	@FXML
	private void on6() {
		textField.appendText("6");
	}
	
	@FXML
	private void on7() {
		textField.appendText("7");
	}
	
	@FXML
	private void on8() {
		textField.appendText("8");
	}
	
	@FXML
	private void on9() {
		textField.appendText("9");
	}
	
	@FXML
	private void onDot() {
		textField.appendText(".");
	}
	//[end] number-button-events

	

	//[start] operator-button-events
	
	@FXML
	private void onModulus() {
		int length = textField.getText().length();
		
		if (length > 0) {
			operator = new Input("%", true);
			newNumber();
		}
		
	}
	
	@FXML
	private void onMultiply() {
		int length = textField.getText().length();
		
		if (length > 0) {
			operator = new Input("*", true);
			newNumber();
		}
	}
	
	@FXML
	private void onDivide() {
		int length = textField.getText().length();
		
		if (length > 0) {
			operator = new Input("/", true);
			newNumber();
		}
	}
	
	@FXML
	private void onAdd() {
		int length = textField.getText().length();
		
		if (length > 0) {
			operator = new Input("+", true);
			newNumber();
		}
	}
	
	@FXML
	private void onSubtract() {
		int length = textField.getText().length();
		
		if (length > 0) {
			operator = new Input("-", true);
			newNumber();
		}
	}
	
	@FXML
	private void onOpen() {
	}
	
	@FXML
	private void onClose() {
	}
	
	@FXML
	private void onIndice() {
		int length = textField.getText().length();
		
		if (length > 0) {
			operator = new Input("^", true);
			newNumber();
		}
	}
	
	@FXML
	private void onRoot() {
		int length = textField.getText().length();
		
		if (length > 0) {
			operator = new Input("√", true);
			newNumber();
		}
	}
	

	//[end] number-button-events
	
	
}

