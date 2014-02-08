

package helpers;

import java.util.Scanner;
import models.Space;

public class Json {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(jsonObject("Tile",jsonObject));
		//String[] arr = {"FDJSK" , "FJKDSLJF", "FJDKLFJSD"};
		System.out.println((new Space()).getClass().getSimpleName());
		System.out.println(Space.SpaceType.IRRIGATION);
		
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
