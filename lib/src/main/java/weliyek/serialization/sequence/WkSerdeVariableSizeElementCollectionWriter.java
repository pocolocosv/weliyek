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

import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;

public final class WkSerdeVariableSizeElementCollectionWriter<
                        T extends Collection<ET>,
                        YS extends WkSerdeDtreeOperationSettings,
                        ET,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>>
    implements WkSerdeElementCollectionWriter<
                        T,
                        YS,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<T>,
                        WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        ET,
                        EYD,
                        EYO>,
               WkSerdeDtreeVariableSizeSequenceWriter<
                        T,
                        YS,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<T>,
                        WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>>
{

  final WkSerdeElementCollectionWriterCoreSimplified<
                        T, YS,
                        WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                        ET,EYS,EYD,EYO> operationCore;

  WkSerdeVariableSizeElementCollectionWriter(
    int index,
    T serializable,
    YS settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<
      T,?,WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,?,?,?> serializingfieldCore,
    WkSerdeElementCollectionDefinitionCoreSimplified<
      T,?,?,?,YS,WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
      WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
      ET,?,?,?,EYS,EYD,EYO,?,?> definitionCore) {
    this.operationCore = new WkSerdeElementCollectionWriterCoreSimplified<
                                T,
                                YS,
                                WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
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
  public WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?> definition() {
    return this.operationCore.definition();
  }

  @Override
  public YS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<T>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeMsgOutputField<T, WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>, ?>
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