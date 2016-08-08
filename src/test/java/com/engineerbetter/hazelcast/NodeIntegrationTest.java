package com.engineerbetter.hazelcast;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;

public class NodeIntegrationTest
{
	private String remoteAddr;


	@Before
	public void setup()
	{
		remoteAddr = System.getenv("HAZELCAST_ADDR");

		if(remoteAddr == null)
		{
			fail("HAZELCAST_ADDR env var must be set for integration tests");
		}
	}


	@Test
	public void canConnect()
	{
		ClientNetworkConfig networkConfig = new ClientNetworkConfig().addAddress(remoteAddr);
		ClientConfig clientConfig = new ClientConfig().setNetworkConfig(networkConfig);
		HazelcastInstance hazelcast = HazelcastClient.newHazelcastClient(clientConfig);
		hazelcast.getMap("test").put("foo", "bar");
		String value = (String) hazelcast.getMap("test").get("foo");
		assertThat(value, is("bar"));
	}


	@Test
	public void indexReturnsHealthy()
	{
		TestRestTemplate rest = new TestRestTemplate();
		ResponseEntity<String> response = rest.getForEntity("http://"+remoteAddr+"/", String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), containsString("ClusterService{address=Address["));
	}
}
