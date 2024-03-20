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
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.sequence.SequenceSizeParameters;
import weliyek.serialization.sequence.WkPrimitiveArrayLengthGetter;

public class WkSerdeDTreeVariableSizeByteArray
    implements WkSerdeDTreeByteArrayDefinition,
               WkSerdeDTreeVariableSizePrimitiveArrayLeafDefinition<
                        WkByteArray>
{

  public static WkSrlzStruct<
                      WkByteArray,
                      WkSzVariableLengthOperationSettings,
                      WkSerdeDTreeVariableSizeByteArray,
                      WkSerdeDTreeVariableSizeByteArrayReader,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkSerdeDTreeVariableSizeByteArray,
                      WkSerdeDTreeVariableSizeByteArrayWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSerdeDTreeVariableSizeByteArray>
  newStruct(String label, int minSize, int maxSize) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> WkSerdeDTreeVariableSizeByteArray.newCore(minSize, maxSize, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static
  WkSrlzStructDefinitionFrameNodeCore<
                      WkByteArray,
                      WkSzVariableLengthOperationSettings,?,?,
                      WkSerdeDTreeVariableSizeByteArray,
                      WkSerdeDTreeVariableSizeByteArrayReader,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSerdeDTreeVariableSizeByteArray,
                      WkSerdeDTreeVariableSizeByteArrayWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSerdeDTreeVariableSizeByteArray,?>
  newCore(
    int minSize,
    int maxSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeDTreeVariableSizeByteArray(minSize, maxSize, componentCore).definitionCore;
  }

  private final WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<
                        WkByteArray,
                        WkSzVariableLengthOperationSettings,
                        WkSerdeDTreeVariableSizeByteArrayReader,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeDTreeVariableSizeByteArrayWriter,
                        WkSerdeDTreeVariableSizeByteArray> definitionCore;
  private final SequenceSizeParameters<WkByteArray> sizeLimits;

  private WkSerdeDTreeVariableSizeByteArray(
    int minSize,
    int maxSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<
        WkByteArray,
        WkSzVariableLengthOperationSettings,
        WkSerdeDTreeVariableSizeByteArrayReader,
        WkSettingsSrlzPacketOperationData,
        WkSerdeDTreeVariableSizeByteArrayWriter,
        WkSerdeDTreeVariableSizeByteArray>(
                                  1024, // de/serialization step size
                                  componentCore,
                                  WkSerdeDTreeVariableSizeByteArray::getRxRequestedLength,
                                  (i,xs,axb,xkc,dc) -> new WkSerdeDTreeVariableSizeByteArrayReader(i,xs,axb,xkc,dc).operationCore,
                                  WkBasicByteArraySrlzEngineDecoder.FACTORY,
                                  (WkPrimitiveArrayLengthGetter<WkByteArray,WkSettingsSrlzPacketOperationData,WkSerdeDTreeVariableSizeByteArray>)WkSerdeDTreeVariableSizeByteArray::getTxRequestedLength,
                                  (i,y,ys,ayb,ykc,dc) -> new WkSerdeDTreeVariableSizeByteArrayWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                  WkByteArraySrlzEngineEncoder.FACTORY,
                                  this,
                                  WkByteArray.class);
    this.sizeLimits = new SequenceSizeParameters<>(minSize, maxSize, definitionCore);
  }

  private static int getRxRequestedLength(WkSzVariableLengthOperationSettings settings, WkSerdeDTreeVariableSizeByteArray definition) {
    return settings.getRequestedLength();
  }

  private static int getTxRequestedLength(
    WkByteArray wrapper,
    WkSettingsSrlzPacketOperationData settings,
    WkSerdeDTreeVariableSizeByteArray definition) {
    return wrapper.getLength();
  }

  @Override
  public Class<WkByteArray> serializableClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int minimalSize() {
    return this.sizeLimits.minimalSize();
  }

  @Override
  public int maximalSize() {
    return this.sizeLimits.maximalSize();
  }

  @Override
  public int getSerializationStepSize() {
    return this.definitionCore.getSerializationStepSize();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
