package com.engineerbetter.hazelcast;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=NodeApplication.class)
public class NodeSmokeTest
{
	@Test
	public void canConnect()
	{
		HazelcastInstance hazelcast = HazelcastClient.newHazelcastClient();
		hazelcast.getMap("test").put("foo", "bar");
		String value = (String) hazelcast.getMap("test").get("foo");
		assertThat(value, is("bar"));
	}
}
