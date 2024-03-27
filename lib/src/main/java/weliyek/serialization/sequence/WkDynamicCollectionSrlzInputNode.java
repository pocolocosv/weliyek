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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataReader;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeDTreeNumberReader;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;

public class WkDynamicCollectionSrlzInputNode<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        ZT extends Number,
                        ZXS extends WkSettingsSrlzPacketOperationData,
                        ZXO extends WkSerdeDTreeNumberReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                        VXS extends WkSzVariableLengthOperationSettings>
    implements WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                        T, XS,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<T>,
                        WkDynamicCollectionSrlzStructNode<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>
{

  final WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                        T, XS,
                        WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                          T, XS,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                          WkResultSrlzPacketOperationData<T>,
                          WkDynamicCollectionSrlzStructNode<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                        WkDynamicCollectionSrlzStructNode<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS> operationCore;

  WkDynamicCollectionSrlzInputNode(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<
      T,?,WkDynamicCollectionSrlzStructNode<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
      ?,?,?> packetfieldCore,
    WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
      T,XS,
      WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
        T,XS,WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,WkResultSrlzPacketOperationData<T>,
        WkDynamicCollectionSrlzStructNode<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
        ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
      WkDynamicCollectionSrlzStructNode<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
      ?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?,?> definitionCore) {
    this.operationCore = new WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                                T, XS,
                                WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                                T, XS,
                                WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                WkResultSrlzPacketOperationData<T>,
                                WkDynamicCollectionSrlzStructNode<
                                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                                WkDynamicCollectionSrlzStructNode<
                                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    packetfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public WkSrlzInputPacketSubfieldFrameNode<ZT, ZXD, ZXO> size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<T, WkVariableSizeCollectionSrlzStructNode<T, VXS, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?>, WkVariableSizeCollectionSrlzInputNode<T, VXS, ET, EXS, EXD, EXO>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public
  WkDynamicCollectionSrlzStructNode<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>
  definition() {
    return this.operationCore.definition();
  }

  @Override
  public XS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream> dashboard() {
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
  public
  WkSrlzInputPacketFieldFrameNode<T, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
