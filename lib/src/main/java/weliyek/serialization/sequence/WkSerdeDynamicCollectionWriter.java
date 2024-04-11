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

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataWriter;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinition;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;

public class WkSerdeDynamicCollectionWriter<
                        T extends Collection<ET>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeDynamicCollectionDefinition<
                                      T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZYS extends WkSerdeDtreeOperationSettings,
                        ZYO extends WkSerdeDtreeNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberDefinition<ZT>,
                        ET,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeNodeDataWriter<ET,EYS,?,?,EYD>,
                        VYS extends WkSerdeDtreeOperationSettings>
    implements WkSerdeDtreeDynamicCollectionWriter<
                        T, YS,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<T>,
                        WkSerdeDynamicCollection<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                        ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
{

  final WkSerdeDtreeDynamicCollectionWriterCore<
                        T, YS,
                        WkSerdeDtreeDynamicCollectionWriter<
                          T, YS,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationResult<T>,
                          WkSerdeDynamicCollection<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        WkSerdeDynamicCollection<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                        ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS> operationCore;

  WkSerdeDynamicCollectionWriter(
    int index,
    T serializable, YS settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<
      T,?,WkSerdeDynamicCollection<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
      ?,?,?> packetfieldCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<
      T,?,?,?,YS,
      WkSerdeDtreeDynamicCollectionWriter<T,YS,WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,WkSerdeDtreeOperationResult<T>,
        WkSerdeDynamicCollection<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
        ZT,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
      WkSerdeDynamicCollection<T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
      ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS,?> definitionCore) {
    this.operationCore =  new WkSerdeDtreeDynamicCollectionWriterCore<
                                T, YS,
                                WkSerdeDtreeDynamicCollectionWriter<
                                  T, YS,
                                  WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                  WkSerdeDtreeOperationResult<T>,
                                  WkSerdeDynamicCollection<
                                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                  ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                                WkSerdeDynamicCollection<
                                T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    packetfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public Optional<WkSerdeDtreeNodeDataOutputComponent<ZT, ZYD, ZYO>> size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSerdeDtreeNodeDataOutputComponent<T, WkSerdeVariableSizeElementCollection<T, ?, VYS, ET, ?, ?, ?, EYS, EYD, EYO, ?>, WkSerdeVariableSizeElementCollectionWriter<T, VYS, ET, EYS, EYD, EYO>>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public
  WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>
  definition() {
    return this.operationCore.definition();
  }

  @Override
  public YS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
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
  public
  WkSerdeDtreeNodeDataOutputComponent<T, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ?>
  packetField() {
    return this.operationCore.packetField();
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
  public List<WkSrlzOutputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
