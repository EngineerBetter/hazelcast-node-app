package com.engineerbetter.hazelcast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastConfig
{
	@Bean
	public HazelcastInstance hazelcastServer()
	{
		return Hazelcast.newHazelcastInstance();
	}
}
