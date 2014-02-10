

package helpers;

import java.util.LinkedList;
import java.util.List;
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
    	LinkedList<String> list = new LinkedList<String>();
    	if(obj instanceof Stack) {
    		obj = ((Stack<Object>) obj).toArray();
    	}
    	else if(obj instanceof List) {
    		obj = ((List<Object>) obj).toArray();
    	}
    	
		Object[] arr =  (Object[]) obj;
		for(int x = 0; x < arr.length; ++x) {
			if(arr[x] instanceof Stack || arr[x] instanceof List || arr[x] instanceof Object[]) {
				list.add(serializeArray(arr[x]));
			}
			else {
				list.add(Json.jsonObject(((Serializable<Object>) arr[x]).serialize()));
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
}
