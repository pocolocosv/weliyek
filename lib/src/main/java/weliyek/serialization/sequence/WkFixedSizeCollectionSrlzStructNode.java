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
import java.util.function.Function;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataReader;
import weliyek.serialization.WkSerdeDTreeNodeDataWriter;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinitionCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;

public final class WkFixedSizeCollectionSrlzStructNode<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        YS extends WkSettingsSrlzPacketOperationData,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDTreeNodeDataWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDTreeNodeStructDefinition<ET>>
    implements WkCollectionAndElementsSrlzStructDefinitionFrameNode<
                        T,
                        WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                        WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                        ET, ED>,
                WkSerdeDTreeFixedSizeSequenceDefinition<T>
{

  public static <T extends Collection<ET>,
                 XS extends WkSettingsSrlzPacketOperationData,
                 YS extends WkSettingsSrlzPacketOperationData,
                 ET,
                 EXS extends WkSettingsSrlzPacketOperationData,
                 EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                 EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSettingsSrlzPacketOperationData,
                 EYD extends WkSerdeDTreeNodeStructDefinition<ET>,
                 EYO extends WkSerdeDTreeNodeDataWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDTreeNodeStructDefinition<ET>>
  WkSrlzStruct<T,
                  XS,
                  WkFixedSizeCollectionSrlzStructNode<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                  WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                  WkSzInputBytestreamBase<?>,
                  YS,
                  WkFixedSizeCollectionSrlzStructNode<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                  WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                  WkSzOutputBytestreamBase<?>,
                  WkFixedSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
  newStruct(
    String label,
    String elementsLabel,
    int expectedCollectionSize,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> WkFixedSizeCollectionSrlzStructNode.newCore(
                                    expectedCollectionSize,
                                    elementsLabel,
                                    collectionClass,
                                    collectionFactory,
                                    elementsRxSettingsFactory,
                                    elementsTxSettingsFactory,
                                    elementsDefinitionFactory,
                                    pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static <T extends Collection<ET>,
                 XS extends WkSettingsSrlzPacketOperationData,
                 YS extends WkSettingsSrlzPacketOperationData,
                 ET,
                 EXS extends WkSettingsSrlzPacketOperationData,
                 EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                 EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSettingsSrlzPacketOperationData,
                 EYD extends WkSerdeDTreeNodeStructDefinition<ET>,
                 EYO extends WkSerdeDTreeNodeDataWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDTreeNodeStructDefinition<ET>>
  WkSerdeDTreeNodeStructDefinitionCore<
                      T,
                      XS,?,?,
                      WkFixedSizeCollectionSrlzStructNode<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                      WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                      WkSzInputBytestreamBase<?>,
                      YS,?,?,
                      WkFixedSizeCollectionSrlzStructNode<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                      WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                      WkSzOutputBytestreamBase<?>,
                      WkFixedSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>,?>
  newCore(
    int expectedCollectionSize,
    String elementsLabel,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkFixedSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>(
                        expectedCollectionSize,
                        componentCore,
                        elementsLabel,
                        collectionClass,
                        collectionFactory,
                        elementsRxSettingsFactory,
                        elementsTxSettingsFactory,
                        elementsDefinitionFactory).definitionCore;
  }

  private final WkSimplifiedCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
                        T, XS,
                        WkFixedSizeCollectionSrlzStructNode<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                        YS,
                        WkFixedSizeCollectionSrlzStructNode<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                        WkFixedSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>> definitionCore;
  private final SequenceFixedSizeParameter<T> fixedSizeParameter;

  private WkFixedSizeCollectionSrlzStructNode(
    int expectedCollectionSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String elementsLabel,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory) {
    this.definitionCore = new WkSimplifiedCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
                                  T, XS,
                                  WkFixedSizeCollectionSrlzStructNode<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                                  WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                                  YS,
                                  WkFixedSizeCollectionSrlzStructNode<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                  WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                                  ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                                  WkFixedSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>(
                                      componentCore,
                                      (i,s,axb,xkc,dc) -> new WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>(i,s,axb,xkc,dc).operationCore,
                                      (i,y,s,ayb,ykc,dc) -> new WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>(i,y,s,ayb,ykc,dc).operationCore,
                                      elementsLabel,
                                      (xo) -> expectedCollectionSize,
                                      elementsRxSettingsFactory,
                                      elementsTxSettingsFactory,
                                      elementsDefinitionFactory,
                                      collectionFactory,
                                      this,
                                      collectionClass);
    this.fixedSizeParameter = new SequenceFixedSizeParameter<T>(expectedCollectionSize, this.definitionCore);
  }

  @Override
  public
  WkSerdeDTreeNodeStructComponentHandler<
    WkFixedSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
    WkFixedSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,ED>
  elements() {
    return this.definitionCore.elements();
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<T> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int getExpectedLength() {
    return this.fixedSizeParameter.sequenceExpectedSize();
  }

}
