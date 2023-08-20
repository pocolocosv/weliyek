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
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;

public final class WkVariableSizeCollectionSrlzStructNode<
                        T extends Collection<ET>,
                        XS extends WkSzVariableLengthOperationSettings,
                        YS extends WkSettingsSrlzPacketOperationData,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSrlzStructDefinitionFrameNode<ET>,
                        EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                        ED extends WkSrlzStructDefinitionFrameNode<ET>>
    implements WkCollectionAndElementsSrlzStructDefinitionFrameNode<
                        T,
                        WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                        WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                        ET,
                        ED>,
               WkVariableSizeSequenceSrlzStructDefinitionFrameNode<T>
{

  public static <T extends Collection<ET>,
                 XS extends WkSzVariableLengthOperationSettings,
                 YS extends WkSettingsSrlzPacketOperationData,
                 ET,
                 EXS extends WkSettingsSrlzPacketOperationData,
                 EXD extends WkSrlzStructDefinitionFrameNode<ET>,
                 EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                 EYS extends WkSettingsSrlzPacketOperationData,
                 EYD extends WkSrlzStructDefinitionFrameNode<ET>,
                 EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                 ED extends WkSrlzStructDefinitionFrameNode<ET>>
  WkSrlzStruct<
                 T,
                 XS,
                 WkVariableSizeCollectionSrlzStructNode<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                 WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                 WkSzInputBytestreamBase<?>,
                 YS,
                 WkVariableSizeCollectionSrlzStructNode<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                 WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                 WkSzOutputBytestreamBase<?>,
                 WkVariableSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
  newStruct(
    String label,
    String elementsLabel,
    int minSize,
    int maxSize,
    Class<T> collectionClass,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory,
    WkOperationSettingsFactory<
      WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    Function<List<ET>, T> collectionFactory) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                  label,
        (pc) -> WkVariableSizeCollectionSrlzStructNode.newCore(
                    elementsLabel,
                    minSize,
                    maxSize,
                    collectionClass,
                    elementsRxSettingsFactory,
                    elementsTxSettingsFactory,
                    elementsDefinitionFactory,
                    collectionFactory,
                    pc),
        WkSzCountingInputBytestream::new,
        WkSzCountingOutputBytestream::new);
  }

  public static <T extends Collection<ET>,
                 XS extends WkSzVariableLengthOperationSettings,
                 YS extends WkSettingsSrlzPacketOperationData,
                 ET,
                 EXS extends WkSettingsSrlzPacketOperationData,
                 EXD extends WkSrlzStructDefinitionFrameNode<ET>,
                 EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                 EYS extends WkSettingsSrlzPacketOperationData,
                 EYD extends WkSrlzStructDefinitionFrameNode<ET>,
                 EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                 ED extends WkSrlzStructDefinitionFrameNode<ET>>
  WkSrlzStructDefinitionFrameNodeCore<
                 T,
                 XS,?,?,
                 WkVariableSizeCollectionSrlzStructNode<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                 WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                 WkSzInputBytestreamBase<?>,
                 YS,?,?,
                 WkVariableSizeCollectionSrlzStructNode<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                 WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                 WkSzOutputBytestreamBase<?>,
                 WkVariableSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>,?>
  newCore(
    String elementsLabel,
    int minSize,
    int maxSize,
    Class<T> collectionClass,
    WkOperationSettingsFactory<
      WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkVariableSizeCollectionSrlzStructNode<>(minSize, maxSize, componentCore, elementsLabel, collectionClass, elementsRxSettingsFactory, elementsTxSettingsFactory, elementsDefinitionFactory, collectionFactory).definitionCore;
  }

  private final WkSimplifiedCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
                        T,
                        XS,
                        WkVariableSizeCollectionSrlzStructNode<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                        YS,
                        WkVariableSizeCollectionSrlzStructNode<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                        WkVariableSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>> definitionCore;
  private final SequenceSizeParameters<T> sizeLimits;

  private WkVariableSizeCollectionSrlzStructNode(
    int minSize,
    int maxSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String elementsLabel,
    Class<T> collectionClass,
    WkOperationSettingsFactory<
      WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory) {
    this.definitionCore = new WkSimplifiedCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
                                T,
                                XS,
                                WkVariableSizeCollectionSrlzStructNode<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                                WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
                                YS,
                                WkVariableSizeCollectionSrlzStructNode<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>,
                                ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                                WkVariableSizeCollectionSrlzStructNode<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>(
                                    componentCore,
                                    (i,s,axb,xkc,dc) -> new WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>(i,s,axb,xkc,dc).operationCore,
                                    (i,y,s,ayb,ykc,dc) -> new WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>(i,y,s,ayb,ykc,dc).operationCore,
                                    elementsLabel,
                                    WkVariableSizeCollectionSrlzStructNode::getNumberOfDeserializingOperations,
                                    elementsRxSettingsFactory,
                                    elementsTxSettingsFactory,
                                    elementsDefinitionFactory,
                                    collectionFactory,
                                    this,
                                    collectionClass);
    this.sizeLimits = new SequenceSizeParameters<T>(minSize, maxSize, definitionCore);
  }

  private static <XS extends WkSzVariableLengthOperationSettings>
  int getNumberOfDeserializingOperations(WkVariableSizeCollectionSrlzInputNode<?,XS,?,?,?,?> aggregatingDeserializer) {
    return aggregatingDeserializer.settings().getRequestedLength();
  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<
    WkVariableSizeCollectionSrlzInputNode<T,XS,ET,EXS,EXD,EXO>,
    WkVariableSizeCollectionSrlzOutputNode<T,YS,ET,EYS,EYD,EYO>, ED>
  elements() {
    return this.definitionCore.elements();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<T> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int minimalSize() {
    return this.sizeLimits.maximalSize();
  }

  @Override
  public int maximalSize() {
    return this.sizeLimits.maximalSize();
  }

}
