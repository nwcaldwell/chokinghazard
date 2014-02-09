

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
		for(int x = 0; x < members.length; ++x) {
			ret.append(members[x] + (x == members.length - 1 ? "" : ",\n"));
		}
		return ret.toString();
	}
	
	public static String jsonPair(String type, String value) {
		return "\"" + type + "\" : " + value;
	}

	public static String jsonArray(String elements) {
		return "[\n" + addTab(elements) + "\n]";
	}
	
	public static String jsonElements(String... elements) {
		StringBuilder ret = new StringBuilder();
		for(int x = 0; x < elements.length; ++x) {
			ret.append(elements[x] + (x == elements.length - 1 ? "" : ",\n"));
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
    	return Json.jsonArray(Json.jsonElements(list.toArray((new String[list.size()]))));
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
	
	public static String[] splitPair(String serial) {
		return new String[] {serial.substring(0,serial.indexOf(":")), serial.substring(serial.indexOf(":") + 1)};
	}
	
	public static String[] split(String serial) {
		ArrayList<String> list = new ArrayList<String>();
		int lastIndex = 0; 
		int count = 0; 
		for(int x = 0; x < serial.length(); ++x) {
			char ch = serial.charAt(x);
			if(ch == '{' || ch == '[')
				count++;
			else if(ch == '}' || ch == ']')
				count--;
			else if(count == 0 && ch == ',') {
				list.add(serial.substring(lastIndex, x).trim());
				lastIndex = x + 1;
			}
		}
		if(lastIndex != serial.length() -1)
			list.add(serial.substring(lastIndex).trim());
		return list.toArray((new String[list.size()]));
	}
}
