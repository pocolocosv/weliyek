# Weliyek

Weli yek means to be skilled, to be powerful in Nawat, a indigenous
language spoken in El Salvador [1].

Weliyek is the umbrella term for all the software in this repository. For now
only serialization logic is present.

To do:

- [x] Import serialization code
- [ ] Refactor serialization code
- [ ] Add Bitcoin serialization logic
- [ ] Add Bitcoin protocol client
- [ ] Add Bitcoin test infrastructure using docker.

Later will look into creating a client that will be able to connect to a
Bitcoin node. 

See the following unit test for examples how the serialization is performed. Also see 
FilterBuilderTest.java for an example on how a search can be done.

- weliyek/lib/src/test/java/welikey.amat.basic.aggregator.sequence.DynamicCollectionFieldTest.java
- weliyek/lib/src/test/java/welikey.amat.basic.aggregator.sequence.FixedAndVariableSizeCollectionFieldsTest.java
- weliyek/lib/src/test/java/welikey.amat.basic.number.PrimitiveSerializationTests.java
- weliyek/lib/src/test/java/welikey.amat.basic.string.StringWithDynamicSizeBytesTest.java
- weliyek/lib/src/test/java/welikey.amat.basic.string.StringWithFixedLengthBytesTest.java
- weliyek/lib/src/test/java/welikey.ketza.util.array.ByteArrayWrapperTest.java
- weliyek/lib/src/test/java/welikey.ketza.util.array.DynamicByteArrayTest.java
- weliyek/lib/src/test/java/weliyek.amat2.protocol.filter.FilterBuilderTest.java

[1] Hernández G., Werner. NAWAT MUJMUSTA. TZUNHEJEKAT. 2016