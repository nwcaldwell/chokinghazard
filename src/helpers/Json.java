

package helpers;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import models.Serializable;

public class Json {

	public static void main(String[] args) {
		
	}

	public static String jsonObject(String members) {
		return "{\n" + addTab(members) + "\n}";
	}
	
	public static String jsonMembers(String... members) {
		StringBuilder ret = new StringBuilder();
		for(String pair : members) {
			ret.append(pair + ",\n");
		}
		if(ret.charAt(ret.length()-2) == ',') {
			return ret.substring(0, ret.length()-2) + "\n";
		}
		return ret.toString();
	}
	
	public static String jsonPair(String type, String value) {
		return "\"" + type + "\" : " + value;
	}

	public static String jsonArray(String elements) {
		return "{\n" + addTab(elements) + "\n}";
	}
	
	public static String jsonElements(String... elements) {
		StringBuilder ret = new StringBuilder();
		for(String value : elements) {
			ret.append(value + ",\n");
		}
		if(ret.charAt(ret.length()-2) == ',') {
			return ret.substring(0, ret.length()-2) + "\n";
		}
		return ret.toString();
	}
	
	public static String jsonValue(String value) {
		return "\"" + value + "\"";
	}
	
	@SuppressWarnings("unchecked")
	public static String serializeArray(Object obj) {
    	ArrayList<String> list = new ArrayList<String>();
    	if(obj instanceof Object[]) {
    		Object[] arr =  (Object[]) obj;
    		for(int x = 0; x < arr.length; ++x) {
    			if(arr[x] instanceof Stack || arr[x] instanceof Object[]) {
    				list.add(serializeArray(arr[x]));
    			}
    			else {
    				list.add(((Serializable<Object>) arr[x]).serialize());
    			}
    		}
    	}
    	if(obj instanceof Stack) {
    		Stack<Object> stack = (Stack<Object>) obj;
    		stack = (Stack<Object>) stack.clone();
    		while(!stack.isEmpty()) {
    			Object element = stack.pop();
    			if(element instanceof Stack || element instanceof Object[]) {
    				list.add(serializeArray(element));
    			}
    			else {
    				list.add(((Serializable<Object>) element).serialize());
    			}
    		}
    	}
    	String[] ret = new String[list.size()];
    	for(int x = 0; x < list.size(); ++x)
    		ret[x] = (String) list.get(x);
    	return Json.jsonArray(Json.jsonElements(ret));
    }
	
	private static String addTab(String str) {
		Scanner in = new Scanner(str); 
		StringBuilder ret = new StringBuilder();
		while(in.hasNextLine()) {
			ret.append("  " + in.nextLine());
		}
		in.close();
		return ret.toString();
	}
}
