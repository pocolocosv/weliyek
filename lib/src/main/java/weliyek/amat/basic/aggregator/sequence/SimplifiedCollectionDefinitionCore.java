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
import java.util.function.ToIntFunction;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.basic.sequence.BasicSequenceReadingRuntime;
import weliyek.amat.basic.sequence.BasicSequenceWritingRuntime;
import weliyek.amat.basic.sequence.CollectionAndElementsFieldDeserializer;
import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;
import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;

public class SimplifiedCollectionDefinitionCore<
                        T extends Collection<ET>,
                        XS extends OperationSettings,
                        XD extends WkSzCollectionAndElementsDefinition<T,XO,?,ET,?>,
                        XO extends CollectionAndElementsFieldDeserializer<
                                        T,
                                        XS,
                                        SequenceReadingRuntime<WkSzInputBytestream>,
                                        DeserializingResult<T>,
                                        XD,
                                        ET,EXD,EXO>,
                        YS extends OperationSettings,
                        YD extends WkSzCollectionAndElementsDefinition<T,?,YO,ET,?>,
                        YO extends CollectionAndElementsFieldSerializer<
                                        T,
                                        YS,
                                        SequenceWritingRuntime<WkSzOutputBytestream>,
                                        SerializingResult,
                                        YD,
                                        ET,EYD,EYO>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        ED extends WkSzDefinition<ET,?>,
                        D extends WkSzCollectionAndElementsDefinition<T,XO,YO,ET,ED>>
    extends CollectionAndElementsFieldDefinitionCore<
                        T,
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        SequenceReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          SequenceReadingRuntime<WkSzInputBytestream>>,
                        DeserializingResult<T>,
                        XD,
                        XO,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        SequenceWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          SequenceWritingRuntime<WkSzOutputBytestream>>,
                        SerializingResult,
                        YD,
                        YO,
                        WkSzOutputBytestreamBase<?>,
                        ET,
                        EXS,
                        EXD,
                        EXO,
                        EYS,
                        EYD,
                        EYO,
                        ED,
                        D,
                        SimplifiedCollectionDefinitionCore<
                          T,XS,XD,XO,YS,YD,YO,
                          ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D>>
{

  protected SimplifiedCollectionDefinitionCore(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    PacketInputFieldReadingFactory<
      T,XS,XD,SimplifiedCollectionDefinitionCore<T,XS,XD,XO,YS,YD,YO,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D>,
      XO,WkSzInputBytestreamBase<?>> readingOpFactory,
    PacketOutputFieldWritingFactory<
      T,YS,YD,SimplifiedCollectionDefinitionCore<T,XS,XD,XO,YS,YD,YO,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D>,
      YO,WkSzOutputBytestreamBase<?>> writingOpFactory,
    String elementsLabel,
    ToIntFunction<? super XO> elementsDeserializingNumOfOps,
    OperationSubsegmentSettingsFactory<XO, EXS> elementsRxSettingsFactory,
    OperationSubsegmentSettingsFactory<YO, EYS> elementsTxSettingsFactory,
    ProtocolDefinitionFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,EYS,EYD,EYO,
      WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ED> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          BasicSequenceReadingRuntime::new,
          BasicReadingResult::new,
          readingOpFactory,
          BasicSequenceWritingRuntime::new,
          BasicWritingResult::empty,
          writingOpFactory,
          elementsLabel,
          elementsDeserializingNumOfOps,
          elementsRxSettingsFactory,
          elementsTxSettingsFactory,
          (k,yo,i) -> yo.serializableAsList().get(i),
          elementsDefinitionFactory,
          (xo) -> collectionFactory.apply(xo.element().field().get().collectAllOperationValues()),
          definitionBody,
          serializableClass);
  }

  @Override
  protected SimplifiedCollectionDefinitionCore<T,XS,XD,XO,YS,YD,YO,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D> getThis() {
    return this;
  }

}
