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
import java.util.function.Predicate;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.filter.WkSrlzPacketNodePredicate;

public final class FixedSizeCollectionField<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        YS extends WkSettingsSrlzPacketOperationData,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSrlzStructDefinitionFrameNode<ET,EXO>,
                        EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                        ED extends WkSrlzStructDefinitionFrameNode<ET,EXO>>
    implements WkSzCollectionAndElementsDefinition<
                        T,
                        FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                        FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                        ET, ED>,
                WkFixedSizeSequenceSrlzStructDefinitionFrameNode<
                        T,
                        FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>>
{

  public static <T extends Collection<ET>,
                 XS extends WkSettingsSrlzPacketOperationData,
                 YS extends WkSettingsSrlzPacketOperationData,
                 ET,
                 EXS extends WkSettingsSrlzPacketOperationData,
                 EXD extends WkSrlzStructDefinitionFrameNode<ET,EXO>,
                 EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                 EYS extends WkSettingsSrlzPacketOperationData,
                 EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                 EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                 ED extends WkSrlzStructDefinitionFrameNode<ET,EXO>>
  WkSrlzStructComponentFrameNodeRootCore<T,
                  XS,
                  FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                  FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                  WkSzInputBytestreamBase<?>,
                  YS,
                  FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                  FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                  WkSzOutputBytestreamBase<?>,
                  FixedSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
  newPacketStructure(
    String label,
    String elementsLabel,
    int expectedCollectionSize,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> FixedSizeCollectionField.newCore(
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
                 EXD extends WkSrlzStructDefinitionFrameNode<ET,EXO>,
                 EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                 EYS extends WkSettingsSrlzPacketOperationData,
                 EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                 EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                 ED extends WkSrlzStructDefinitionFrameNode<ET,EXO>>
  WkSrlzStructDefinitionFrameNodeCore<
                      T,
                      XS,?,?,
                      FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                      FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                      WkSzInputBytestreamBase<?>,
                      YS,?,?,
                      FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                      FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                      WkSzOutputBytestreamBase<?>,
                      FixedSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>,?>
  newCore(
    int expectedCollectionSize,
    String elementsLabel,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new FixedSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>(
                        expectedCollectionSize,
                        componentCore,
                        elementsLabel,
                        collectionClass,
                        collectionFactory,
                        elementsRxSettingsFactory,
                        elementsTxSettingsFactory,
                        elementsDefinitionFactory).definitionCore;
  }

  private final SimplifiedCollectionDefinitionCore<
                        T, XS,
                        FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                        YS,
                        FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                        FixedSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>> definitionCore;
  private final SequenceFixedSizeParameter<T> fixedSizeParameter;

  private FixedSizeCollectionField(
    int expectedCollectionSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String elementsLabel,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory) {
    this.definitionCore = new SimplifiedCollectionDefinitionCore<
                                  T, XS,
                                  FixedSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                                  FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                                  YS,
                                  FixedSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                  FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                                  ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                                  FixedSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>(
                                      componentCore,
                                      (i,s,axb,xkc,dc) -> new FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>(i,s,axb,xkc,dc).operationCore,
                                      (i,y,s,ayb,ykc,dc) -> new FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>(i,y,s,ayb,ykc,dc).operationCore,
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
  WkSrlzStructSubcomponentFrameNode<
    FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
    FixedSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,ED>
  element() {
    return this.definitionCore.element();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<T> rxClass() {
    return this.definitionCore.rxClass();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public WkSrlzPacketNodePredicate<?, ?> makeTester(
    Predicate<? super FixedSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public int getExpectedLength() {
    return this.fixedSizeParameter.sequenceExpectedSize();
  }

}
