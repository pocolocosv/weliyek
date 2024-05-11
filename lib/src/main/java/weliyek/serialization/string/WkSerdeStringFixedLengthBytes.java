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

import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.string.WkSerdeStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayWriter;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArray;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;

/**
 * Packet structure and data for handling fixed length bytes array. The serialization
 * handles padding and truncation of the bytes array in case the original String
 * object's own bytes array is not same length. Uses zero as padding for serializing
 * and deserializing.
 */
public class WkSerdeStringFixedLengthBytes
    implements WkSerdeStringFromBytesDefinition<
                        WkSerdeStringFixedLengthBytesReader,
                        WkSerdeStringFixedLengthBytesWriter,
                        WkSerdeDtreeFixedSizeByteArray>
{

  public static WkSerdeDtreeStruct<
                      String,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeStringFixedLengthBytes,
                      WkSerdeStringFixedLengthBytesReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeStringFixedLengthBytes,
                      WkSerdeStringFixedLengthBytesWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeStringFixedLengthBytes>
  newStruct(
    String label,
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      (pc) -> WkSerdeStringFixedLengthBytes.newCore(
                                    bytesLabel, expectedSize, defaultCharset, pc),
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeStructDefinitionCore<
                      String,
                      WkSerdeDtreeOperationSettings,?,?,
                      WkSerdeStringFixedLengthBytes,
                      WkSerdeStringFixedLengthBytesReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,?,?,
                      WkSerdeStringFixedLengthBytes,
                      WkSerdeStringFixedLengthBytesWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeStringFixedLengthBytes,?>
  newCore(
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeStringFixedLengthBytes(bytesLabel, expectedSize, defaultCharset, componentCore).definitionCore;
  }

  private final WkSerdeStringFromBytesDefinitionCoreSimplified<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringFixedLengthBytesReader,
                        WkSerdeStringFixedLengthBytes,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringFixedLengthBytesWriter,
                        WkSerdeStringFixedLengthBytes,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeStringFixedLengthBytes> definitionCore;

  private WkSerdeStringFixedLengthBytes(
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?,?,?> componentCore) {

    this.definitionCore = new WkSerdeStringFromBytesDefinitionCoreSimplified<>(
                                  defaultCharset,
                                  componentCore,
                                  (i,xs,axb,xpc,dc) -> new WkSerdeStringFixedLengthBytesReader(i,xs,axb,xpc,dc).operationCore,
                                  (i,y,ys,ayb,ypc,dc) -> new WkSerdeStringFixedLengthBytesWriter(i,y,ys,ayb,ypc,dc).operationCore,
                                  bytesLabel,
                                  WkSerdeDtreeOperationSettings::none,
                                  WkSerdeStringFixedLengthBytes::aggragateByteArray,
                                  WkSerdeDtreeOperationSettings::none,
                                  new FixedLengthBytesDisaggregatorFromString(expectedSize),
                                  (pc) -> WkSerdeDtreeFixedSizeByteArray.newCore(expectedSize, pc),
                                  this);
  }

  private static String aggragateByteArray(WkSerdeStringFixedLengthBytesReader deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    WkByteArray wrapper = deserializingStringOp.bytes().get().firstOperation().get().result().get().serializable().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static class FixedLengthBytesDisaggregatorFromString
      extends ByteArrayFromStringDisaggregator<
                        WkSerdeStringFixedLengthBytesWriter,
                        WkSerdeDtreeFixedSizeByteArray>
  {

    private final Optional<Integer> fixedLength;

    FixedLengthBytesDisaggregatorFromString(int fixedLength) {
      this.fixedLength = Optional.of(Integer.valueOf(fixedLength));
    }

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      WkSerdeStringFixedLengthBytesWriter stringSerializingOperation) {
      return this.fixedLength;
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSerdeDtreeNodeStructComponentHandler<WkSerdeStringFixedLengthBytesReader, WkSerdeStringFixedLengthBytesWriter, WkSerdeDtreeFixedSizeByteArray>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> serializableClass() {
    return String.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
  WkSerdeDtreeNodeStructComponentHandler<WkSerdeStringFixedLengthBytesReader, WkSerdeStringFixedLengthBytesWriter, WkSerdeDtreeFixedSizeByteArray>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

}
