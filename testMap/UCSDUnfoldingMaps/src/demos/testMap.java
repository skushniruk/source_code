package demos;

import java.util.HashMap;

public class testMap {
	public static void main(String[] args)
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("Serhiy", 22);
		map.put("Vittya", 23);
		map.put("Serhiy", 50);
		System.out.println(map);
	}

}
