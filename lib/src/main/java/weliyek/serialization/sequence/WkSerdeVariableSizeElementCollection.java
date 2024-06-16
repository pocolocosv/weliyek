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
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;

public final class WkSerdeVariableSizeElementCollection<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettingsVariableLength,
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
                        WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                        WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                        ET,
                        ED>,
               WkSerdeDtreeVariableSizeSequenceDefinition<T>
{

  public static <T extends Collection<ET>,
                 XS extends WkSerdeDtreeOperationSettingsVariableLength,
                 YS extends WkSerdeDtreeOperationSettings,
                 ET,
                 EXS extends WkSerdeDtreeOperationSettings,
                 EXD extends WkSerdeDtreeStructDefinition<ET>,
                 EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSerdeDtreeOperationSettings,
                 EYD extends WkSerdeDtreeStructDefinition<ET>,
                 EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDtreeStructDefinition<ET>>
  WkSerdeDtreeStruct<
                 T,
                 XS,
                 WkSerdeVariableSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                 WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                 WkSerdeDtreeBytestreamInputBase<?>,
                 YS,
                 WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                 WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                 WkSerdeDtreeBytestreamOutputBase<?>,
                 WkSerdeVariableSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
  newStruct(
    String label,
    String elementsLabel,
    int minSize,
    int maxSize,
    Class<T> collectionClass,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
        ET,EXS,?,?,EXD,?,EXO,?,
        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
        EYS,?,?,EYD,?,EYO,?,
        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
        ED,?>> elementsDefinitionFactory,
    WkOperationSettingsFactory<
      WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    Function<List<ET>, T> collectionFactory) {
    return new WkSerdeDtreeStructCore<>(
                  label,
        (pc) -> WkSerdeVariableSizeElementCollection.newCore(
                    elementsLabel,
                    minSize,
                    maxSize,
                    collectionClass,
                    elementsRxSettingsFactory,
                    elementsTxSettingsFactory,
                    elementsDefinitionFactory,
                    collectionFactory,
                    pc),
        WkSerdeDtreeBytestreamCountingInputStream::new,
        WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static <T extends Collection<ET>,
                 XS extends WkSerdeDtreeOperationSettingsVariableLength,
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
                  T,
                  XS,
                  WkSerdeVariableSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                  WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                  YS,
                  WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                  WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                  ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                  WkSerdeVariableSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
  newCore(
    String elementsLabel,
    int minSize,
    int maxSize,
    Class<T> collectionClass,
    WkOperationSettingsFactory<
      WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
        ET,EXS,?,?,EXD,?,EXO,?,
        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
        EYS,?,?,EYD,?,EYO,?,
        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
        ED,?>> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore) {
    return new WkSerdeVariableSizeElementCollection<>(minSize, maxSize, componentCore, elementsLabel, collectionClass, elementsRxSettingsFactory, elementsTxSettingsFactory, elementsDefinitionFactory, collectionFactory).definitionCore;
  }

  private final WkSerdeElementCollectionDefinitionCoreSimplified<
                        T,
                        XS,
                        WkSerdeVariableSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                        YS,
                        WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                        WkSerdeVariableSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>> definitionCore;
  private final SequenceSizeParameters<T> sizeLimits;

  private WkSerdeVariableSizeElementCollection(
    int minSize,
    int maxSize,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
    String elementsLabel,
    Class<T> collectionClass,
    WkOperationSettingsFactory<
      WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<
      WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
        ET,EXS,?,?,EXD,?,EXO,?,
        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
        EYS,?,?,EYD,?,EYO,?,
        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
        ED,?>> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory) {
    this.definitionCore = new WkSerdeElementCollectionDefinitionCoreSimplified<
                                T,
                                XS,
                                WkSerdeVariableSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                                WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                                YS,
                                WkSerdeVariableSizeElementCollection<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>,
                                ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                                WkSerdeVariableSizeElementCollection<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>(
                                    componentCore,
                                    (i,s,axb,xkc,dc) -> new WkSerdeVariableSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>(i,s,axb,xkc,dc).operationCore,
                                    (i,y,s,ayb,ykc,dc) -> new WkSerdeVariableSizeElementCollectionWriter<T,YS,ET,EYS,EYD,EYO>(i,y,s,ayb,ykc,dc).operationCore,
                                    elementsLabel,
                                    WkSerdeVariableSizeElementCollection::getNumberOfDeserializingOperations,
                                    elementsRxSettingsFactory,
                                    elementsTxSettingsFactory,
                                    elementsDefinitionFactory,
                                    collectionFactory,
                                    this,
                                    collectionClass);
    this.sizeLimits = new SequenceSizeParameters<T>(minSize, maxSize, definitionCore);
  }

  private static <XS extends WkSerdeDtreeOperationSettingsVariableLength>
  int getNumberOfDeserializingOperations(WkSerdeVariableSizeElementCollectionReader<?,XS,?,?,?,?> aggregatingDeserializer) {
    return aggregatingDeserializer.settings().getRequestedLength();
  }

  @Override
  public WkSerdeDtreeStructSubfield<
            WkSerdeVariableSizeElementCollectionReader<T, XS, ET, EXS, EXD, EXO>,
            WkSerdeVariableSizeElementCollectionWriter<T, YS, ET, EYS, EYD, EYO>, ED>
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
  public int minimalSize() {
    return this.sizeLimits.maximalSize();
  }

  @Override
  public int maximalSize() {
    return this.sizeLimits.maximalSize();
  }

}
