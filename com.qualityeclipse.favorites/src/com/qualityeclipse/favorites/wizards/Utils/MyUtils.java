package com.qualityeclipse.favorites.wizards.Utils;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qualityeclipse.favorites.wizards.Utils.Constants.ConditionType;
import com.qualityeclipse.favorites.wizards.Utils.Constants.FieldType;
import com.qualityeclipse.favorites.wizards.Utils.Constants.LogicalOperand;

public class MyUtils {
	//----------------------------------------------------------------------------------------------------------------
	public static String moveTheBufferedReaderCursorToTheLineAfter(BufferedReader br, String startingString){
		String s;
		try{
		while( ((s = br.readLine()) != null) && (! s.startsWith(startingString)) ){
			//do nothing.
		} //while.
		return s;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	//----------------------------------------------------------------------------------------------------------------
	public static String indent(int indentationLevel){
		String s = "", ss = "";
		for (int i=0; i<Constants.NUMBER_OF_TAB_CHARACTERS; i++)
			s = s + " ";
		for (int i=0; i<indentationLevel-1; i++)
			ss = ss + s;
		return ss;
	}
	public static void println(String s, int indentationLevel){
		System.out.println(indent(indentationLevel) + s);
	}
	//----------------------------------------------------------------------------------------------------------------
	public static int indexOf_ifExists_LengthIfDoNotExist(String s1, String s2, int startingIndex){
		int result = 0;
		if (s1 != null){
			result = s1.indexOf(s2, startingIndex);
			if (result < 0) //: it means that there is no s2 (",") up to the end of the string, which means that we are at the end of the line and have an extra character (";").
				result = s1.length()-2;
		}
		return result;
	}
	//----------------------------------------------------------------------------------------------------------------
	public static String removeExtraCharactersFromTheEndOfRecord(String tabSeparatedRecord){//Removes ")\t" or ");\t" from the end (if there is).
		if (tabSeparatedRecord.endsWith(")\t"))
			return tabSeparatedRecord.substring(0, tabSeparatedRecord.length()-2);
		else
			if (tabSeparatedRecord.endsWith(");\t"))
				return tabSeparatedRecord.substring(0, tabSeparatedRecord.length()-3);
			else
				return tabSeparatedRecord;
	}
	//----------------------------------------------------------------------------------------------------------------
	public static String removeFromEnd(String s, int num){
		return s.substring(0, s.length()-num);
	}
	//----------------------------------------------------------------------------------------------------------------
	public static String applyRegexOnString(String regex, String value){
		Pattern pattern = Pattern.compile("[^"+regex+"]+");
		Matcher matcher = pattern.matcher(value);
		if (matcher.find())
			value = value.replaceAll("[^"+regex+"]+", " ");
		if (value.equals(""))
			value = " ";
		return value;
	}
	//----------------------------------------------------------------------------------------------------------------
	public static boolean compareTwoStringsBasedOnconditionType(String valueA, ConditionType conditionType, String valueB, FieldType fieldType){
		boolean result;
		switch (conditionType){
			case EQUALS:
				result = valueA.equals(valueB);
				break;
			case NOT_EQUALS:
				result = !valueA.equals(valueB);
				break;
			case GREATER_OR_EQUAL:
				switch(fieldType){
				case INTEGER:
					if (valueA.equals(" ") || valueA.equals(""))
						valueA = Integer.toString(Constants.AN_EXTREMELY_NEGATIVE_INT);
//					if (valueB.equals("") || valueB.equals(" "))
//						valueB = Integer.toString(Constants.AN_EXTREMELY_NEGATIVE_INT);
					result = (Integer.parseInt(valueA) >= Integer.parseInt(valueB));
					break;
				case STRING:
					result = valueA.compareTo(valueB) >= 0; //if result of str1.compareTo(str2) is positive, then str1 > str2
					break;
				default:
					result = true;
					System.out.println("Warning: You are comparing two fields with GREATER_OR_EQUAL, but with NOT_IMPORTANT fieldType!");
					break;
				}//switch.
				break;
			default:
				result = false;
				break;
		}//switch.
		return result;
	}//compareTwoStringsBasedOnconditionType().
	//------------------------------------------------------------------------------------------------------------------------------------------------
	public static boolean compareTwoStringArrays(String[] s1, String[] s2){
		boolean result = true;
		if (s1.length != s2.length)
			result = false;
		else
			for (int i=0; i<s1.length; i++)
				if (!s1[i].equals(s2[i])){
					result = false;
					break;
				}
		return result;
	}//compareTwoStringArrays().
	//------------------------------------------------------------------------------------------------------------------------------------------------
	public static boolean runLogicalComparison(LogicalOperand logicalOperand, 
			String value1A, ConditionType condition1Type, String value1B, FieldType field1Type, 
			String value2A, ConditionType condition2Type, String value2B, FieldType field2Type){
		boolean result = false;
		if (logicalOperand == LogicalOperand.NO_CONDITION)
			result = true;
		else{
			boolean resultOfCondition1 = compareTwoStringsBasedOnconditionType(value1A, condition1Type, value1B, field1Type);
			if (resultOfCondition1)
				if (logicalOperand == LogicalOperand.AND)
					result = compareTwoStringsBasedOnconditionType(value2A, condition2Type, value2B, field2Type);
				else
					result = true;
			else //i.e., when resultOfCondition1 is not true:
				if (logicalOperand == LogicalOperand.OR)
					result = compareTwoStringsBasedOnconditionType(value2A, condition2Type, value2B, field2Type);
				else
					result = false;
		}//else.
		return result;
	}//runLogicalComparison().
	//------------------------------------------------------------------------------------------------------------------------------------------------

}
