# hazelcast-node-app

Used to demonstrate running Hazelcast as a Cloud Foundry app, which _should_ require TCP routing to be enabled.

## Testing

### Unit test

`mvn clean test`

### Integration test

Push the app somewhere and note the route assigned (probably `node.your-cf.com`).

`curl http://node.your-cf.com/` and you should see the IP address that the node itself is reporting.

Run `HAZELCAST_ADDR=node.your-cf.com mvn clean verify` or use the IP address `HAZELCAST_ADDR=123.123.123.123 mvn clean verify` if you want to prove that it doesn't work. Note that the index page lookup test will fail here, as if the IP address of the Cloud Foundry router is reported, then it won't know where to forward the request on to without a host header.

 