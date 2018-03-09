package Day8;

import java.beans.Expression;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class main {
	
	static Map<String,Integer> registers;

	public static void main(String[] args) {
		
		registers = new HashMap<String,Integer>();
		Scanner input = new Scanner("src/Day8/inputDay8.txt");
		File file = new File(input.nextLine());
		
		int maxValueEver = 0;

        try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("start");
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.println("===============================");
            System.out.println(line);
            
            String[] splittedLine = line.split(" ");
            String register = splittedLine[4];
            String condition = splittedLine[5];
            String sndCondition = splittedLine[6];
            
            checkAndCreateIfNotExists(register);
          	String lineToEval = registers.get(register).toString()+" "+
        						condition+" "+
        						sndCondition;
        	boolean evaluatedCondition = false;
        	evaluatedCondition = evalConditionExpression(lineToEval);
        	
        	if (evaluatedCondition) {
        		register = splittedLine[0];
        		checkAndCreateIfNotExists(register);
        		
        		String operator = splittedLine[1];
        		String sndNumber = "("+splittedLine[2]+")";
        		
        		String affectedRegisterValue = registers.get(register).toString();
        		String expression = "";
        		String sOperator="";
        		switch (operator) {
				case "inc":
					sOperator+="+";
					break;
				case "dec":
					sOperator+="-";
					break;					
				}
        		expression = affectedRegisterValue+sOperator+sndNumber;
        		
        		int operationResult = 0;
        		
        		System.out.println("Valor anterior al cambio: "+affectedRegisterValue);
        		operationResult = evalOperationExpression(expression);
        		
        		registers.put(register,operationResult);
        		
        		System.out.println("Valor despues del cambio: "+operationResult);
        		
        		if (maxValueEver<operationResult) {
        			maxValueEver=operationResult;
        		}
        	}
            
        }
        input.close();
        
        String key = Collections.max(registers.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Result 1 = "+registers.get(key));
        System.out.println("Result 2 = "+maxValueEver);
	}

	static boolean checkAndCreateIfNotExists(String registerToFind) {
		boolean result = false;
		if (registers.containsKey(registerToFind)) {
			result = true;
		}
		else {
			registers.put(registerToFind, 0);
			result = false;
		}
		return result;
	}
	
	static boolean evalConditionExpression(String expression) {
		boolean result = false;
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    String foo = expression;
	    try {
			boolean evaluatedResult = (Boolean) engine.eval(foo);
			result = evaluatedResult;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	static int evalOperationExpression(String expression) {
		int result = 0;
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    String foo = expression;
	    try {
			int evaluatedResult = (int) engine.eval(foo);
			result = evaluatedResult;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
//	sssss

}
