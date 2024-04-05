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
package weliyek.util.array;

import java.util.List;

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.sequence.SequenceFixedSizeParameter;
import weliyek.serialization.sequence.WkPrimitiveArrayLengthGetter;

public class WkSerdeDtreeFixedSizeByteArray
    implements WkSerdeDtreeByteArrayDefinition,
               WkSerdeDtreeFixedSizePrimitiveArrayDefinition<
                        WkByteArray>
{

  public static WkSrlzStruct<
                      WkByteArray,
                      WkSettingsSrlzPacketOperationData,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayReader,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSerdeDtreeFixedSizeByteArray>
  newStruct(
    String label,
    int expectedLength) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> WkSerdeDtreeFixedSizeByteArray.newCore(expectedLength, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                      WkByteArray,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayReader,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSerdeDtreeFixedSizeByteArray,?>
  newCore(
    int expectedLength,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeDtreeFixedSizeByteArray(expectedLength, componentCore).definitionCore;
  }

  private final WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
                        WkByteArray,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        WkSerdeDtreeFixedSizeByteArray> definitionCore;
  private final SequenceFixedSizeParameter<WkByteArray> fixedSizeParameter;

  private WkSerdeDtreeFixedSizeByteArray(
    int expectedLength,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
        WkByteArray,
        WkSettingsSrlzPacketOperationData,
        WkSerdeDtreeFixedSizeByteArrayReader,
        WkSettingsSrlzPacketOperationData,
        WkSerdeDtreeFixedSizeByteArrayWriter,
        WkSerdeDtreeFixedSizeByteArray>(
                                    1024, // de/serialization step size
                                    componentCore,
                                    WkSerdeDtreeFixedSizeByteArray::getRxRequestedLengthFromDefinition,
                                    (i,xs,axb,xkc,dc) -> new WkSerdeDtreeFixedSizeByteArrayReader(i,xs,axb,xkc,dc).operationCore,
                                    WkSerdeDtreeByteArrayReaderDecoderEngine.FACTORY,
                                    (WkPrimitiveArrayLengthGetter<WkByteArray,WkSettingsSrlzPacketOperationData,WkSerdeDtreeFixedSizeByteArray>)WkSerdeDtreeFixedSizeByteArray::getTxRequestedLengthFromDefinition,
                                    (i,y,ys,ayb,ykc,dc) -> new WkSerdeDtreeFixedSizeByteArrayWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                    WkSerdeDtreeByteArrayWriterEncoderEngine.FACTORY,
                                    this,
                                    WkByteArray.class);
    this.fixedSizeParameter = new SequenceFixedSizeParameter<WkByteArray>(expectedLength, this.definitionCore);
  }

  private static int getRxRequestedLengthFromDefinition(WkSettingsSrlzPacketOperationData none, WkSerdeDtreeFixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  private static int getTxRequestedLengthFromDefinition(WkByteArray wrapper, WkSettingsSrlzPacketOperationData none, WkSerdeDtreeFixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  @Override
  public Class<WkByteArray> serializableClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int getExpectedLength() {
    return this.fixedSizeParameter.sequenceExpectedSize();
  }

}
