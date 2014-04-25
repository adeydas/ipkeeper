package ws.abhis.utils.ipkeeper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;

public class FetchByTag {

	static AmazonEC2 ec2;

	public void getInstances(String tagName, String fileName) throws IOException {
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = "";
			int flag = 0;
			String accessKey = "";
			String accessSecret = "";
			while ((line = br.readLine()) != null) {
				if (flag == 0) {
					accessKey = line;
				} else {
					accessSecret = line;
				}
				flag ++;
			}

			//AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();

			ec2 = new AmazonEC2Client(new BasicAWSCredentials(accessKey, accessSecret));

			DescribeInstancesResult describeInstancesRequest = ec2
					.describeInstances();
			List<Reservation> reservations = describeInstancesRequest
					.getReservations();
			Set<Instance> instances = new HashSet<Instance>();

			for (Reservation reservation : reservations) {
				instances.addAll(reservation.getInstances());
			}
			
			System.out.println("AMI ID \t\t\t Public IP \t\t\t Public DNS \t\t\t\t Instance Name Tag");
			System.out.println("====== \t\t\t ========= \t\t\t ========== \t\t\t\t =================");
			
			for (Instance instance : instances) {

				String firstLine = instance.getInstanceId() + "\t\t"
						+ instance.getPublicIpAddress() + "\t\t" + instance.getPublicDnsName();
				String secondLine = "";

				List<Tag> tags = instance.getTags();
				for (int i = 0; i < tags.size(); i++) {

					if (tags.get(i).getKey().toLowerCase().equals("name")) {
						secondLine = tags.get(i).getValue();
					}

				}

				if (secondLine.toLowerCase().contains(tagName.toLowerCase())) {
					System.out.println(firstLine + "\t\t" + secondLine);
				}

			}

		} catch (AmazonServiceException ase) {
			System.out.println("Caught Exception: " + ase.getMessage());
			System.out.println("Reponse Status Code: " + ase.getStatusCode());
			System.out.println("Error Code: " + ase.getErrorCode());
			System.out.println("Request ID: " + ase.getRequestId());
		}
	}
}
