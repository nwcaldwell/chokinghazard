package helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class JsonObject {
	HashMap<String, Object> map;

	public static void main(String[] args) {
		String str = "{ \"Space\" : { {\"turns\" : [{\"space\": \"yo\"}, \"2\", \"3\"] }}";
		JsonObject obj = new JsonObject(str);
		System.out.println(toString(obj));
	}
	
	public HashMap<String, Object> getMap() {
		return map;
	}
	
	public static String toString(Object obj) {
		String ret = "";
		if(obj instanceof JsonObject) {
			HashMap<String, Object> map = ((JsonObject) obj).getMap();
			for (Entry<String, Object> entry : map.entrySet())
			{	
				ret += "KEY[" + entry.getKey() + "] / VALUE[" + toString(entry.getValue()) + "]";
			}
		} else if(obj instanceof Object[]) {
			Object[] arr = (Object[]) obj;
			for(Object o : arr) 
				ret += "," + toString(o);
		}
		else 
			ret += (String) obj;
		return ret;
	}
	
	public JsonObject (String serial) { 
		map = new HashMap<String, Object>();
		loadSerial(serial);
	}
	
	public String getString(String key) {
		return (String) map.get(key);
	}
	
	public JsonObject getMap(String key) {
		return (JsonObject) map.get(key);
	}
	
	public String[] getStringArray(String key) {
		return (String[]) map.get(key);
	}
	
	public JsonObject[] getMapArray(String key) {
		return (JsonObject[]) map.get(key);
	}
	
	public Object loadValue(String serial) {
		if(serial.trim().charAt(0) == '{') {
			JsonObject object = new JsonObject(serial);
			return object;
		}
		else if(serial.trim().charAt(0) == '[') {
			Object[] arr = loadArray(serial);
			return arr;
		}
		return serial;
	}
	
	public void loadSerial(String serial) {
		serial = serial.trim();
		serial = serial.charAt(0) == '{' ? serial.substring(1, serial.length() - 1) : serial;

		String[] pairs = split(serial);
		for(String pair : pairs) {
			String[] keyValue = splitPair(pair);
			map.put(keyValue[0], loadValue(keyValue[1]));
		}		
	}
	
	public Object[] loadArray(String serial) {
		serial = serial.trim();
		serial = serial.charAt(0) == '[' ? serial.substring(1, serial.length() - 1) : serial;
		String[] arr = split(serial);
		Object[] ret = new Object[arr.length];
		for(int x = 0; x < arr.length; ++x) {
			ret[x] = loadValue(arr[x]);
		}
		return ret;
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
