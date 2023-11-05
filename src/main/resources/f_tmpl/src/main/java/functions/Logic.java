package functions;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.logging.Logger;

import org.apache.commons.math3.distribution.ExponentialDistribution;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

public class Logic implements HttpFunction {
	private static final Logger logger = Logger.getLogger("$f.getName()");
	private static ThreadMXBean mgm = ManagementFactory.getThreadMXBean();
	private static HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(180)).build();
	private HttpResponse response = null;

	/*
	 * activities
	 */
	#foreach ( $act in $f.getActivities() )
	private void $act.getName()(){
		this.doWork($act.getDemand());
	}
	#end

	/*
	 * Dnodes
	 */

	@Override
	public void service(HttpRequest request, HttpResponse response) throws IOException {
		// BufferedWriter writer = response.getWriter();
		this.response = response;
		mgm.setThreadCpuTimeEnabled(true);

//		this.doWork();
//		String url = "https://northamerica-northeast1-modellearning.cloudfunctions.net/f2";
//		var getRequest = java.net.http.HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
//
//		try {
//			var getResponse = LogicF1.client.send(getRequest, BodyHandlers.ofString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		writer.write("ok");
	}

	private void doWork(Double stime) {
		ExponentialDistribution dist = new ExponentialDistribution(stime);
		long delay = Long.valueOf(Math.round(dist.sample() * 1e9));
		long start = mgm.getCurrentThreadCpuTime();
		while ((mgm.getCurrentThreadCpuTime() - start) < delay) {
		}
	}
}
