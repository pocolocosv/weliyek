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
package weliyek.serialization.sequence;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDtreeNodeDataWriter;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinition;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;

public final class WkSerdeFixedSizeElementCollectionWriter<
                        T extends Collection<ET>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        ET,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSerdeDtreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeNodeDataWriter<ET,EYS,?,?,EYD>>
    implements WkSerdeElementCollectionWriter<
                        T,
                        YS,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<T>,
                        WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        ET,
                        EYD,
                        EYO>,
                WkSerdeDtreeFixedSizeSequenceWriter<
                        T,
                        YS,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<T>,
                        WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>>
{

  final WkSerdeElementCollectionWriterCoreSimplified<
                        T, YS,
                        WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                        ET,EYS,EYD,EYO> operationCore;

  WkSerdeFixedSizeElementCollectionWriter(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      T,?,WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
      ?,?,?> serializingfieldCore,
    WkSerdeElementCollectionDefinitionCoreSimplified<
      T,?,?,?,YS,WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
      WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
      ET,?,?,?,EYS,EYD,EYO,?,?> definitionCore) {
    this.operationCore = new WkSerdeElementCollectionWriterCoreSimplified<
                                  T, YS,
                                  WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                  WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                                  ET,EYS,EYD,EYO>(
                                      index,
                                      serializable,
                                      settings,
                                      parentBytestream,
                                      serializingfieldCore,
                                      definitionCore,
                                      this);
  }

  @Override
  public WkSrlzOutputPacketSubfieldFrameNode<ET, EYD, EYO> elements() {
    return this.operationCore.elements();
  }

  @Override
  public WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?> definition() {
    return this.operationCore.definition();
  }

  @Override
  public YS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkResultSrlzPacketOperationData<T>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<T, WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public T serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<ET> serializableAsList() {
    return this.operationCore.serializableAsList();
  }

}
