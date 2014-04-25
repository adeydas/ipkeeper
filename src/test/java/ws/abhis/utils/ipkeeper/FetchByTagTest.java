package ws.abhis.utils.ipkeeper;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;

public class FetchByTagTest extends TestCase {
	public void testFetchByTag() throws IOException {
		FetchByTag obj = new FetchByTag();
		//Put the credentials file in src/test/resources
		obj.getInstances("Drive", "AwsCredentials.properties");
		assertTrue(true);
	}
}
