/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.amat.basic.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegmentCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketStructure;
import weliyek.amat.base.SubcomponentHandler;
import weliyek.amat.base.input.CountingInputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.CountingOutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.basic.string.StringFromBytesCore.ByteArrayFromStringDisaggregator;
import weliyek.amat2.protocol.filter.FieldTester;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.FixedSizeByteArray;
import weliyek.ketza.util.array.FixedSizeByteArrayDeserializing;
import weliyek.ketza.util.array.FixedSizeByteArraySerializing;
import weliyek.ketza.util.array.PrimitiveArrayWrapper.ContigousIntsCounter;

/**
 * Packet structure and data for handling fixed length bytes array. The serialization
 * handles padding and truncation of the bytes array in case the original String
 * object's own bytes array is not same length. Uses zero as padding for serializing
 * and deserializing.
 */
public class StringWithFixedLengthBytes
    implements StringFromBytesDefinition<
                        StringWithFixedLengthBytesDeserializing,
                        StringWithFixedLengthBytesSerializing,
                        FixedSizeByteArray>
{

  public static PacketStructure<
                      String,
                      OperationSettings,
                      StringWithFixedLengthBytes,
                      StringWithFixedLengthBytesDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,
                      StringWithFixedLengthBytes,
                      StringWithFixedLengthBytesSerializing,
                      OutputBytestreamGeneralBase<?>,
                      StringWithFixedLengthBytes>
  newPacketStructure(
    String label,
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset) {
    return new PacketStructure<>(
                      label,
                      (pc) -> StringWithFixedLengthBytes.newCore(
                                    bytesLabel, expectedSize, defaultCharset, pc),
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static DefinitionSegmentCore<
                      String,
                      OperationSettings,?,?,
                      StringWithFixedLengthBytes,
                      StringWithFixedLengthBytesDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,?,?,
                      StringWithFixedLengthBytes,
                      StringWithFixedLengthBytesSerializing,
                      OutputBytestreamGeneralBase<?>,
                      StringWithFixedLengthBytes,?>
  newCore(
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset,
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new StringWithFixedLengthBytes(bytesLabel, expectedSize, defaultCharset, componentCore).definitionCore;
  }

  private final SimplifiedStringFromBytesCore<
                        OperationSettings,
                        StringWithFixedLengthBytesDeserializing,
                        StringWithFixedLengthBytes,
                        OperationSettings,
                        StringWithFixedLengthBytesSerializing,
                        StringWithFixedLengthBytes,
                        OperationSettings,
                        FixedSizeByteArrayDeserializing,
                        FixedSizeByteArray,
                        OperationSettings,
                        FixedSizeByteArraySerializing,
                        FixedSizeByteArray,
                        FixedSizeByteArray,
                        StringWithFixedLengthBytes> definitionCore;

  private StringWithFixedLengthBytes(
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset,
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {

    this.definitionCore = new SimplifiedStringFromBytesCore<>(
                                  defaultCharset,
                                  componentCore,
                                  (i,xs,axb,xpc,dc) -> new StringWithFixedLengthBytesDeserializing(i,xs,axb,xpc,dc).operationCore,
                                  (i,y,ys,ayb,ypc,dc) -> new StringWithFixedLengthBytesSerializing(i,y,ys,ayb,ypc,dc).operationCore,
                                  bytesLabel,
                                  OperationSettings::none,
                                  StringWithFixedLengthBytes::aggragateByteArray,
                                  OperationSettings::none,
                                  new FixedLengthBytesDisaggregatorFromString(expectedSize),
                                  (pc) -> FixedSizeByteArray.newCore(expectedSize, pc),
                                  this);
  }

  private static String aggragateByteArray(StringWithFixedLengthBytesDeserializing deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    ByteArrayWrapper wrapper = deserializingStringOp.bytes().field().get().firstOperation().get().result().get().deserialized().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static class FixedLengthBytesDisaggregatorFromString
      extends ByteArrayFromStringDisaggregator<
                        StringWithFixedLengthBytesSerializing,
                        FixedSizeByteArray>
  {

    private final Optional<Integer> fixedLength;

    FixedLengthBytesDisaggregatorFromString(int fixedLength) {
      this.fixedLength = Optional.of(Integer.valueOf(fixedLength));
    }

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      StringWithFixedLengthBytesSerializing stringSerializingOperation) {
      return this.fixedLength;
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  SubcomponentHandler<StringWithFixedLengthBytesDeserializing, StringWithFixedLengthBytesSerializing, FixedSizeByteArray>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<SubcomponentHandler<?,?,?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> rxClass() {
    return String.class;
  }

  @Override
  public List<SubcomponentHandler<?,?,?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(
    Predicate<? super StringWithFixedLengthBytesDeserializing> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public
  SubcomponentHandler<StringWithFixedLengthBytesDeserializing, StringWithFixedLengthBytesSerializing, FixedSizeByteArray>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

}
