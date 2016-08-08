# hazelcast-node-app

Used to demonstrate running Hazelcast as a Cloud Foundry app, which _should_ require TCP routing to be enabled.

## Testing

### Unit test

`mvn clean test`

### Integration test

Push the app somewhere,and then `HAZELCAST_ADDR=some.host mvn clean verify`