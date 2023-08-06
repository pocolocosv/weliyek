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
package weliyek.amat.basic.aggregator.sequence;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.WkSzStruct;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.WkSzCountingInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.output.WkSzCountingOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableSizeSequenceDefinition;
import weliyek.amat2.protocol.filter.FieldTester;

public final class VariableSizeCollectionField<
                        T extends Collection<ET>,
                        XS extends VariableLengthSettings,
                        YS extends OperationSettings,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        ED extends WkSzDefinition<ET,?>>
    implements WkSzCollectionAndElementsDefinition<
                        T,
                        VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                        VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                        ET,
                        ED>,
               WkSzVariableSizeSequenceDefinition<
                        T,
                        VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>>
{

  public static <T extends Collection<ET>,
                 XS extends VariableLengthSettings,
                 YS extends OperationSettings,
                 ET,
                 EXS extends OperationSettings,
                 EXD extends WkSzDefinition<ET,EXO>,
                 EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                 EYS extends OperationSettings,
                 EYD extends WkSzDefinition<ET,?>,
                 EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                 ED extends WkSzDefinition<ET,EXO>>
  WkSzStruct<
                 T,
                 XS,
                 VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                 VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                 WkSzInputBytestreamBase<?>,
                 YS,
                 VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                 VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                 WkSzOutputBytestreamBase<?>,
                 VariableSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
  newPacketStructure(
    String label,
    String elementsLabel,
    int minSize,
    int maxSize,
    Class<T> collectionClass,
    ProtocolDefinitionFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory,
    OperationSubsegmentSettingsFactory<
      VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    OperationSubsegmentSettingsFactory<
      VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    Function<List<ET>, T> collectionFactory) {
    return new WkSzStruct<>(
                  label,
        (pc) -> VariableSizeCollectionField.newCore(
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
                 XS extends VariableLengthSettings,
                 YS extends OperationSettings,
                 ET,
                 EXS extends OperationSettings,
                 EXD extends WkSzDefinition<ET,?>,
                 EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                 EYS extends OperationSettings,
                 EYD extends WkSzDefinition<ET,?>,
                 EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                 ED extends WkSzDefinition<ET,?>>
  WkSzDefinitionCore<
                 T,
                 XS,?,?,
                 VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                 VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                 WkSzInputBytestreamBase<?>,
                 YS,?,?,
                 VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                 VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                 WkSzOutputBytestreamBase<?>,
                 VariableSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>,?>
  newCore(
    String elementsLabel,
    int minSize,
    int maxSize,
    Class<T> collectionClass,
    OperationSubsegmentSettingsFactory<
      VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    OperationSubsegmentSettingsFactory<
      VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    ProtocolDefinitionFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new VariableSizeCollectionField<>(minSize, maxSize, componentCore, elementsLabel, collectionClass, elementsRxSettingsFactory, elementsTxSettingsFactory, elementsDefinitionFactory, collectionFactory).definitionCore;
  }

  private final SimplifiedCollectionDefinitionCore<
                        T,
                        XS,
                        VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                        YS,
                        VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                        VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                        VariableSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>> definitionCore;
  private final SequenceSizeParameters<T> sizeLimits;

  private VariableSizeCollectionField(
    int minSize,
    int maxSize,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    String elementsLabel,
    Class<T> collectionClass,
    OperationSubsegmentSettingsFactory<
      VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,EXS> elementsRxSettingsFactory,
    OperationSubsegmentSettingsFactory<
      VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,EYS> elementsTxSettingsFactory,
    ProtocolDefinitionFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<?>,
      EYS,EYD,EYO,WkSzOutputBytestreamBase<?>,ED> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory) {
    this.definitionCore = new SimplifiedCollectionDefinitionCore<
                                T,
                                XS,
                                VariableSizeCollectionField<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                                VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
                                YS,
                                VariableSizeCollectionField<T,?,YS,ET,?,?,?,EYS,EYD,EYO,?>,
                                VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>,
                                ET, EXS, EXD, EXO, EYS, EYD, EYO, ED,
                                VariableSizeCollectionField<T,XS,YS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>(
                                    componentCore,
                                    (i,s,axb,xkc,dc) -> new VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>(i,s,axb,xkc,dc).operationCore,
                                    (i,y,s,ayb,ykc,dc) -> new VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>(i,y,s,ayb,ykc,dc).operationCore,
                                    elementsLabel,
                                    VariableSizeCollectionField::getNumberOfDeserializingOperations,
                                    elementsRxSettingsFactory,
                                    elementsTxSettingsFactory,
                                    elementsDefinitionFactory,
                                    collectionFactory,
                                    this,
                                    collectionClass);
    this.sizeLimits = new SequenceSizeParameters<T>(minSize, maxSize, definitionCore);
  }

  private static <XS extends VariableLengthSettings>
  int getNumberOfDeserializingOperations(VariableSizeCollectionFieldDeserializer<?,XS,?,?,?,?> aggregatingDeserializer) {
    return aggregatingDeserializer.settings().getRequestedLength();
  }

  @Override
  public
  WkSzStructSubcomponent<
    VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>,
    VariableSizeCollectionFieldSerializer<T,YS,ET,EYS,EYD,EYO>, ED>
  element() {
    return this.definitionCore.element();
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<T> rxClass() {
    return this.definitionCore.rxClass();
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(
    Predicate<? super VariableSizeCollectionFieldDeserializer<T,XS,ET,EXS,EXD,EXO>> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
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
