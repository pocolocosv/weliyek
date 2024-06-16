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
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;

public final class WkSerdeFixedSizeElementCollection<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        YS extends WkSerdeDtreeOperationSettings,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDtreeStructDefinition<ET>>
    implements WkSerdeElementCollectionDefinition<
                        T,
                        WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                        WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                        ET, ED>,
                WkSerdeDtreeFixedSizeSequenceDefinition<T>
{

  public static <T extends Collection<ET>,
                 XS extends WkSerdeDtreeOperationSettings,
                 YS extends WkSerdeDtreeOperationSettings,
                 ET,
                 EXS extends WkSerdeDtreeOperationSettings,
                 EXD extends WkSerdeDtreeStructDefinition<ET>,
                 EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSerdeDtreeOperationSettings,
                 EYD extends WkSerdeDtreeStructDefinition<ET>,
                 EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDtreeStructDefinition<ET>>
  WkSerdeDtreeStruct<T,
                  XS,
                  WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,? extends EXD>,
                  WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                  WkSerdeDtreeBytestreamInputBase<?>,
                  YS,
                  WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,? extends EYD>,
                  WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                  WkSerdeDtreeBytestreamOutputBase<?>,
                  WkSerdeFixedSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
  newStruct(
    String label,
    String elementsLabel,
    int expectedCollectionSize,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
                  ET,EXS,?,?,EXD,?,EXO,?,
                  WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                  EYS,?,?,EYD,?,EYO,?,
                  WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                  ED,?>> elementsDefinitionFactory) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      (pc) -> WkSerdeFixedSizeElementCollection.newCore(
                                    expectedCollectionSize,
                                    elementsLabel,
                                    collectionClass,
                                    collectionFactory,
                                    elementsRxSettingsFactory,
                                    elementsTxSettingsFactory,
                                    elementsDefinitionFactory,
                                    pc),
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static <T extends Collection<ET>,
                 XS extends WkSerdeDtreeOperationSettings,
                 YS extends WkSerdeDtreeOperationSettings,
                 ET,
                 EXS extends WkSerdeDtreeOperationSettings,
                 EXD extends WkSerdeDtreeStructDefinition<ET>,
                 EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSerdeDtreeOperationSettings,
                 EYD extends WkSerdeDtreeStructDefinition<ET>,
                 EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDtreeStructDefinition<ET>>
  WkSerdeElementCollectionDefinitionCoreSimplified<
    T, XS,
    WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,? extends EXD>,
    WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
    YS,
    WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,? extends EYD>,
    WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
    ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
    WkSerdeFixedSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
  newCore(
    int expectedCollectionSize,
    String elementsLabel,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
                ET,EXS,?,?,EXD,?,EXO,?,
                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                EYS,?,?,EYD,?,EYO,?,
                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                ED,?>> elementsDefinitionFactory,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore) {
    return new WkSerdeFixedSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>(
                        expectedCollectionSize,
                        componentCore,
                        elementsLabel,
                        collectionClass,
                        collectionFactory,
                        elementsRxSettingsFactory,
                        elementsTxSettingsFactory,
                        elementsDefinitionFactory).definitionCore;
  }

  private final WkSerdeElementCollectionDefinitionCoreSimplified<
                        T, XS,
                        WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,? extends EXD>,
                        WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                        YS,
                        WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,? extends EYD>,
                        WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                        WkSerdeFixedSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>> definitionCore;
  private final SequenceFixedSizeParameter<T> fixedSizeParameter;

  private WkSerdeFixedSizeElementCollection(
    int expectedCollectionSize,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
    String elementsLabel,
    Class<T> collectionClass,
    Function<List<ET>, T> collectionFactory,
    WkOperationSettingsFactory<
      WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
      EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
      EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
                  ET,EXS,?,?,EXD,?,EXO,?,
                  WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                  EYS,?,?,EYD,?,EYO,?,
                  WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                  ED,?>> elementsDefinitionFactory) {
    this.definitionCore = new WkSerdeElementCollectionDefinitionCoreSimplified<
                                  T, XS,
                                  WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,? extends EXD>,
                                  WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                                  YS,
                                  WkSerdeFixedSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,? extends EYD>,
                                  WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                                  ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                                  WkSerdeFixedSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>(
                                      componentCore,
                                      (i,s,axb,xkc,dc) -> new WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>(i,s,axb,xkc,dc).operationCore,
                                      (i,y,s,ayb,ykc,dc) -> new WkSerdeFixedSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>(i,y,s,ayb,ykc,dc).operationCore,
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
  public WkSerdeDtreeStructSubfield<
            WkSerdeFixedSizeElementCollectionReader<T, XS, ET, EXS, EXD, EXO>,
            WkSerdeFixedSizeElementCollectionWriter<T, YS, ET, EYS, EYD, EYO>, ED>
  elements() {
    return this.definitionCore.elements();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<T> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int getExpectedLength() {
    return this.fixedSizeParameter.sequenceExpectedSize();
  }

}
