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
package weliyek.serialization.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzDefinitionCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.filter.FieldTester;
import weliyek.serialization.string.WkSzStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSzFixedSizeByteArray;
import weliyek.util.array.WkSzFixedSizeByteArrayReader;
import weliyek.util.array.WkSzFixedSizeByteArrayWriter;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;

/**
 * Packet structure and data for handling fixed length bytes array. The serialization
 * handles padding and truncation of the bytes array in case the original String
 * object's own bytes array is not same length. Uses zero as padding for serializing
 * and deserializing.
 */
public class WkSzStringWithFixedLengthBytes
    implements WkSzStringFromBytesDefinition<
                        WkSzStringWithFixedLengthBytesReader,
                        WkSzStringWithFixedLengthBytesWriter,
                        WkSzFixedSizeByteArray>
{

  public static WkSzStruct<
                      String,
                      WkSzOperationSettings,
                      WkSzStringWithFixedLengthBytes,
                      WkSzStringWithFixedLengthBytesReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      WkSzStringWithFixedLengthBytes,
                      WkSzStringWithFixedLengthBytesWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzStringWithFixedLengthBytes>
  newPacketStructure(
    String label,
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset) {
    return new WkSzStruct<>(
                      label,
                      (pc) -> WkSzStringWithFixedLengthBytes.newCore(
                                    bytesLabel, expectedSize, defaultCharset, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                      String,
                      WkSzOperationSettings,?,?,
                      WkSzStringWithFixedLengthBytes,
                      WkSzStringWithFixedLengthBytesReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,?,?,
                      WkSzStringWithFixedLengthBytes,
                      WkSzStringWithFixedLengthBytesWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzStringWithFixedLengthBytes,?>
  newCore(
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzStringWithFixedLengthBytes(bytesLabel, expectedSize, defaultCharset, componentCore).definitionCore;
  }

  private final SimplifiedStringFromBytesCore<
                        WkSzOperationSettings,
                        WkSzStringWithFixedLengthBytesReader,
                        WkSzStringWithFixedLengthBytes,
                        WkSzOperationSettings,
                        WkSzStringWithFixedLengthBytesWriter,
                        WkSzStringWithFixedLengthBytes,
                        WkSzOperationSettings,
                        WkSzFixedSizeByteArrayReader,
                        WkSzFixedSizeByteArray,
                        WkSzOperationSettings,
                        WkSzFixedSizeByteArrayWriter,
                        WkSzFixedSizeByteArray,
                        WkSzFixedSizeByteArray,
                        WkSzStringWithFixedLengthBytes> definitionCore;

  private WkSzStringWithFixedLengthBytes(
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {

    this.definitionCore = new SimplifiedStringFromBytesCore<>(
                                  defaultCharset,
                                  componentCore,
                                  (i,xs,axb,xpc,dc) -> new WkSzStringWithFixedLengthBytesReader(i,xs,axb,xpc,dc).operationCore,
                                  (i,y,ys,ayb,ypc,dc) -> new WkSzStringWithFixedLengthBytesWriter(i,y,ys,ayb,ypc,dc).operationCore,
                                  bytesLabel,
                                  WkSzOperationSettings::none,
                                  WkSzStringWithFixedLengthBytes::aggragateByteArray,
                                  WkSzOperationSettings::none,
                                  new FixedLengthBytesDisaggregatorFromString(expectedSize),
                                  (pc) -> WkSzFixedSizeByteArray.newCore(expectedSize, pc),
                                  this);
  }

  private static String aggragateByteArray(WkSzStringWithFixedLengthBytesReader deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    WkByteArray wrapper = deserializingStringOp.bytes().field().get().firstOperation().get().result().get().deserialized().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static class FixedLengthBytesDisaggregatorFromString
      extends ByteArrayFromStringDisaggregator<
                        WkSzStringWithFixedLengthBytesWriter,
                        WkSzFixedSizeByteArray>
  {

    private final Optional<Integer> fixedLength;

    FixedLengthBytesDisaggregatorFromString(int fixedLength) {
      this.fixedLength = Optional.of(Integer.valueOf(fixedLength));
    }

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      WkSzStringWithFixedLengthBytesWriter stringSerializingOperation) {
      return this.fixedLength;
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSzStructSubcomponent<WkSzStringWithFixedLengthBytesReader, WkSzStringWithFixedLengthBytesWriter, WkSzFixedSizeByteArray>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSzStructSubcomponent<?,?,?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> rxClass() {
    return String.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?,?,?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(
    Predicate<? super WkSzStringWithFixedLengthBytesReader> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public
  WkSzStructSubcomponent<WkSzStringWithFixedLengthBytesReader, WkSzStringWithFixedLengthBytesWriter, WkSzFixedSizeByteArray>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

}
