package ws.abhis.utils.ipkeeper;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EntryPoint {
	public static void main(String... strings ) throws IOException {
		
		if (strings.length <= 0) {
			System.out.println("Search term and file name required");
		}
		
		FetchByTag obj = new FetchByTag();
		obj.getInstances(strings[0], strings[1]);
	}
}
