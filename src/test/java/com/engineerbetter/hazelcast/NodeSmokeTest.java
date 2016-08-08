package com.engineerbetter.hazelcast;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=NodeApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class NodeSmokeTest
{
	@LocalServerPort
	private String port;
	@Autowired
	private TestRestTemplate rest;


	@Test
	public void canConnect()
	{
		HazelcastInstance hazelcast = HazelcastClient.newHazelcastClient();
		hazelcast.getMap("test").put("foo", "bar");
		String value = (String) hazelcast.getMap("test").get("foo");
		assertThat(value, is("bar"));
	}


	@Test
	public void indexReturnsHealthy()
	{
		ResponseEntity<String> response = rest.getForEntity("/", String.class);
		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody(), containsString("ClusterService{address=Address["));
	}
}
