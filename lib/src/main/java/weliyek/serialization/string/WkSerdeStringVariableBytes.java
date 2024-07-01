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

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsOptionalLength;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.string.WkSerdeStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArray;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArrayWriter;

public class WkSerdeStringVariableBytes
    implements WkSerdeStringFromBytesDefinition<
                        WkSerdeStringVariableBytesReader,
                        WkSerdeStringVariableBytesWriter,
                        WkSerdeDtreeVariableSizeByteArray>
{

  public static WkSerdeDtreeStruct<
                      String,
                      WkSerdeDtreeOperationSettingsVariableLength,
                      WkSerdeStringVariableBytes,
                      WkSerdeStringVariableBytesReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettingsOptionalLength,
                      WkSerdeStringVariableBytes,
                      WkSerdeStringVariableBytesWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeStringVariableBytes>
  newStruct(
    String label,
    String bytesLabel,
    int minimalLength,
    int maximalLength,
    Charset defaultCharset) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      (pc) -> WkSerdeStringVariableBytes.newCore(
                                    bytesLabel, minimalLength, maximalLength, defaultCharset, pc),
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeStringFromBytesDefinitionCoreSimplified<
                      WkSerdeDtreeOperationSettingsVariableLength,
                      WkSerdeStringVariableBytesReader,
                      WkSerdeStringVariableBytes,
                      WkSerdeDtreeOperationSettingsOptionalLength,
                      WkSerdeStringVariableBytesWriter,
                      WkSerdeStringVariableBytes,
                      WkSerdeDtreeOperationSettingsVariableLength,
                      WkSerdeDtreeVariableSizeByteArrayReader,
                      WkSerdeDtreeVariableSizeByteArray,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeDtreeVariableSizeByteArrayWriter,
                      WkSerdeDtreeVariableSizeByteArray,
                      WkSerdeDtreeVariableSizeByteArray,
                      WkSerdeStringVariableBytes>
  newCore(
    String bytesLabel,
    int minSize,
    int maxSize,
    Charset defaultCharset,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeStringVariableBytes(bytesLabel, minSize, maxSize, defaultCharset, componentCore).definitionCore;
  }

  private final WkSerdeStringFromBytesDefinitionCoreSimplified<
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeStringVariableBytesReader,
                        WkSerdeStringVariableBytes,
                        WkSerdeDtreeOperationSettingsOptionalLength,
                        WkSerdeStringVariableBytesWriter,
                        WkSerdeStringVariableBytes,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeVariableSizeByteArrayWriter,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeStringVariableBytes> definitionCore;

  WkSerdeStringVariableBytes(
    String bytesLabel,
    int minSize,
    int maxSize,
    Charset defaultCharset,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeStringFromBytesDefinitionCoreSimplified<
                                  WkSerdeDtreeOperationSettingsVariableLength,
                                  WkSerdeStringVariableBytesReader,
                                  WkSerdeStringVariableBytes,
                                  WkSerdeDtreeOperationSettingsOptionalLength,
                                  WkSerdeStringVariableBytesWriter,
                                  WkSerdeStringVariableBytes,
                                  WkSerdeDtreeOperationSettingsVariableLength,
                                  WkSerdeDtreeVariableSizeByteArrayReader,
                                  WkSerdeDtreeVariableSizeByteArray,
                                  WkSerdeDtreeOperationSettings,
                                  WkSerdeDtreeVariableSizeByteArrayWriter,
                                  WkSerdeDtreeVariableSizeByteArray,
                                  WkSerdeDtreeVariableSizeByteArray,
                                  WkSerdeStringVariableBytes>(
                                    defaultCharset,
                                    componentCore,
                                    (i,xpc,dc) -> new WkSerdeStringVariableBytesReader(i,xpc,dc).operationCore,
                                    (i,ypc,dc) -> new WkSerdeStringVariableBytesWriter(i,ypc,dc).operationCore,
                                    bytesLabel,
                                    WkOperationSettingsFactory.parentSettingsReuser(), // reusing deserializing settings
                                    WkSerdeStringVariableBytes::aggragateByteArray,
                                    WkSerdeDtreeOperationSettings::none,
                                    new VariableLengthBytesDissagregatorFromString(),
                                    (pc) -> WkSerdeDtreeVariableSizeByteArray.newCore(minSize, maxSize, pc),
                                    this);
  }

  private static String aggragateByteArray(WkSerdeStringVariableBytesReader deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    WkByteArray wrapper = deserializingStringOp.bytes().get().firstOperation().get().result().get().serializable().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static class VariableLengthBytesDissagregatorFromString
      extends ByteArrayFromStringDisaggregator<
                        WkSerdeStringVariableBytesWriter,
                        WkSerdeDtreeVariableSizeByteArray>
  {

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      WkSerdeStringVariableBytesWriter stringSerializingOperation) {
      return stringSerializingOperation.settings().getOptionalLength();
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeStringVariableBytesReader, WkSerdeStringVariableBytesWriter, WkSerdeDtreeVariableSizeByteArray>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> serializableClass() {
    return String.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeStringVariableBytesReader, WkSerdeStringVariableBytesWriter, WkSerdeDtreeVariableSizeByteArray>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

}
