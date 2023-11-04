/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// [START functions_helloworld_get]

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

public class LogicF1 implements HttpFunction {
	private static final Logger logger = Logger.getLogger(LogicF1.class.getName());

	private static ThreadMXBean mgm = ManagementFactory.getThreadMXBean();
	// private static GammaDistribution dist = new GammaDistribution(0.5, 1);
	private static ExponentialDistribution dist = new ExponentialDistribution(1l);

	// Create a client with some reasonable defaults. This client can be reused for
	// multiple requests.
	// (java.net.httpClient also pools connections automatically by default.)
	private static HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(180)).build();;

	// Simple function to return "Hello World"
	@Override
	public void service(HttpRequest request, HttpResponse response) throws IOException {
		BufferedWriter writer = response.getWriter();
		mgm.setThreadCpuTimeEnabled(true);

		this.doWork();

		String url = "https://northamerica-northeast1-modellearning.cloudfunctions.net/f2";
		var getRequest = java.net.http.HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		try {
			var getResponse = LogicF1.client.send(getRequest, BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writer.write("ok");
	}

	private void doWork() {
		long delay = Long.valueOf(Math.round(dist.sample() * 1e9));
		long start = mgm.getCurrentThreadCpuTime();
		while ((mgm.getCurrentThreadCpuTime() - start) < delay) {
		}
	}
}
// [END functions_helloworld_get]
