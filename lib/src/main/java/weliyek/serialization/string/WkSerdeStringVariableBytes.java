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
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOptionalLengthOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.string.WkSerdeStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArrayWriter;
import weliyek.util.array.WkSerdeDtreeVariableSizeByteArray;

public class WkSerdeStringVariableBytes
    implements WkSerdeStringFromBytesDefinition<
                        WkSerdeStringVariableBytesReader,
                        WkSerdeStringVariableBytesWriter,
                        WkSerdeDtreeVariableSizeByteArray>
{

  public static WkSrlzStruct<
                      String,
                      WkSzVariableLengthOperationSettings,
                      WkSerdeStringVariableBytes,
                      WkSerdeStringVariableBytesReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOptionalLengthOperationSettings,
                      WkSerdeStringVariableBytes,
                      WkSerdeStringVariableBytesWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSerdeStringVariableBytes>
  newStruct(
    String label,
    String bytesLabel,
    int minimalLength,
    int maximalLength,
    Charset defaultCharset) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> WkSerdeStringVariableBytes.newCore(
                                    bytesLabel, minimalLength, maximalLength, defaultCharset, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                      String,
                      WkSzVariableLengthOperationSettings,?,?,
                      WkSerdeStringVariableBytes,
                      WkSerdeStringVariableBytesReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOptionalLengthOperationSettings,?,?,
                      WkSerdeStringVariableBytes,
                      WkSerdeStringVariableBytesWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSerdeStringVariableBytes,?>
  newCore(
    String bytesLabel,
    int minSize,
    int maxSize,
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeStringVariableBytes(bytesLabel, minSize, maxSize, defaultCharset, componentCore).definitionCore;
  }

  private final WkSerdeStringFromBytesDefinitionCoreSimplified<
                        WkSzVariableLengthOperationSettings,
                        WkSerdeStringVariableBytesReader,
                        WkSerdeStringVariableBytes,
                        WkSzOptionalLengthOperationSettings,
                        WkSerdeStringVariableBytesWriter,
                        WkSerdeStringVariableBytes,
                        WkSzVariableLengthOperationSettings,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeDtreeVariableSizeByteArrayWriter,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeDtreeVariableSizeByteArray,
                        WkSerdeStringVariableBytes> definitionCore;

  WkSerdeStringVariableBytes(
    String bytesLabel,
    int minSize,
    int maxSize,
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeStringFromBytesDefinitionCoreSimplified<>(
                                    defaultCharset,
                                    componentCore,
                                    (i,xs,axb,xpc,dc) -> new WkSerdeStringVariableBytesReader(i,xs,axb,xpc,dc).operationCore,
                                    (i,y,ys,ayb,ypc,dc) -> new WkSerdeStringVariableBytesWriter(i,y,ys,ayb,ypc,dc).operationCore,
                                    bytesLabel,
                                    WkOperationSettingsFactory.parentSettingsReuser(), // reusing deserializing settings
                                    WkSerdeStringVariableBytes::aggragateByteArray,
                                    WkSettingsSrlzPacketOperationData::none,
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
  WkSerdeDtreeNodeStructComponentHandler<WkSerdeStringVariableBytesReader, WkSerdeStringVariableBytesWriter, WkSerdeDtreeVariableSizeByteArray>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> serializableClass() {
    return String.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
  WkSerdeDtreeNodeStructComponentHandler<WkSerdeStringVariableBytesReader, WkSerdeStringVariableBytesWriter, WkSerdeDtreeVariableSizeByteArray>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

}
