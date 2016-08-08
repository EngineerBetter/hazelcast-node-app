package com.engineerbetter.hazelcast;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;

@RestController
public class IndexController
{
	private final HazelcastInstance hazelcast;


	public IndexController(HazelcastInstance hazelcast)
	{
		this.hazelcast = hazelcast;
	}


	@RequestMapping("/")
	public String index()
	{
		return hazelcast.getCluster().toString();
	}
}
