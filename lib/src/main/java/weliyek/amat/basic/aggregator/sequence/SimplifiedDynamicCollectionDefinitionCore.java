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
import java.util.function.IntFunction;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.BasicReadingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.BasicWritingRuntime;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.amat.basic.number.WkSzNumberWriter;
import weliyek.ketza.util.array.DynamicSequenceDefinitionCore;

public final class SimplifiedDynamicCollectionDefinitionCore<
                        T extends Collection<ET>,
                        XS extends OperationSettings,
                        XO extends DynamicCollectionFieldDeserializer<
                                        T,XS,
                                        DeserializingRuntime<WkSzInputBytestream>,
                                        DeserializingResult<T>,
                                        XD,ZT,ZXO,?,ET,EXS,?,EXO,VXS>,
                        XD extends WkSzDynamicCollectionDefinition<
                                        T,XO,?,?,?,?,?,?,?,?,?,?,?,?>,
                        YS extends OperationSettings,
                        YO extends DynamicCollectionFieldSerializer<
                                        T,YS,
                                        SerializingRuntime<WkSzOutputBytestream>,
                                        SerializingResult,
                                        YD,ZT,ZYO,?,ET,EYS,?,EYO,VYS>,
                        YD extends WkSzDynamicCollectionDefinition<
                                        T,?,YO,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZXS extends OperationSettings,
                        ZXO extends WkSzNumberReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,?>,
                        ZYS extends OperationSettings,
                        ZYO extends WkSzNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ZD extends WkSzNumberDefinition<ZT,ZXO>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        ED extends WkSzDefinition<ET,EXO>,
                        VXS extends VariableLengthSettings,
                        VYS extends OperationSettings,
                        D extends WkSzDynamicCollectionDefinition<
                                      T, XO, YO, ZD, ET, EXS, EXD, EXO,
                                      EYS, EYD, EYO, ED, VXS, VYS>>
    extends DynamicSequenceDefinitionCore<
                        T, XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        ReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          DeserializingRuntime<WkSzInputBytestream>>,
                        DeserializingResult<T>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          SerializingRuntime<WkSzOutputBytestream>>,
                        SerializingResult,
                        YO, YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                        VXS,
                        VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>, // VXO
                        VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>, // VXD
                        VYS,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>, // VYO
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>, // VYD
                        VariableSizeCollectionField<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>,
                        D,
                        SimplifiedDynamicCollectionDefinitionCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>>
    implements WkSzDynamicCollectionDefinition<
                        T, XO, YO, ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS>
{

  public SimplifiedDynamicCollectionDefinitionCore(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    OperationSubsegmentSettingsFactory<XO, ZXS> sizeDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<YO, ZYS> sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    ProtocolDefinitionFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    OperationSubsegmentSettingsFactory<XO,VXS> collectionAndElementsDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<YO,VYS> collectionAndElementsSerializerSettingsFactory,
    String elementFieldLabel,
    ProtocolDefinitionFactory<ET, EXS, EXD, EXO, WkSzInputBytestreamBase<?>, EYS, EYD, EYO, WkSzOutputBytestreamBase<?>, ED>
      elementsDefinitionFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldDeserializer<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldSerializer<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    //Function<InputBytestreamGeneralBase<?>, ReadingRuntimeControl<InputBytestream, InputBytestreamGeneralBase<? extends InputBytestream>, DeserializingRuntime<InputBytestream>>>
    //  deserializerRuntimeFactory,
    //BiFunction<XO, T, DeserializingResult<T>> deserializerResultFactory,
    PacketInputFieldReadingFactory<T, XS, XD, SimplifiedDynamicCollectionDefinitionCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>, XO, WkSzInputBytestreamBase<?>>
      deserializerFactory,
    //Function<OutputBytestreamGeneralBase<?>, WritingRuntimeControl<OutputBytestream, OutputBytestreamGeneralBase<? extends OutputBytestream>, SerializingRuntime<OutputBytestream>>>
    //  serializerRuntimeFactory,
    //Function<YO, SerializingResult> serializerResultFactory,
    PacketOutputFieldWritingFactory<T, YS, YD, SimplifiedDynamicCollectionDefinitionCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>, YO, WkSzOutputBytestreamBase<?>>
      serializerFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> serializableClass,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    D definitionBody) {
    super(
          sizeFieldLabel,
          sizeDeserializerSettingsFactory,
          sizeSerializerSettingsFactory,
          (yk,yo,i) -> sizeValueFactory.apply(yo.serializable().size()),
          sizeDefinitionFactory,
          collectionAndElementsFieldLabel,
          collectionAndElementsDeserializerSettingsFactory,
          collectionAndElementsSerializerSettingsFactory,
          (yk, ayo, i) -> ayo.serializable(),
          (pc) -> VariableSizeCollectionField.
                        <T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>newCore(
                                elementFieldLabel,
                                minSize,
                                maxSize,
                                serializableClass,
                                elementDeserializerSettingsFactory,
                                elementSerializerSettingsFactory,
                                elementsDefinitionFactory,
                                collectionFactory,
                                pc),
          componentCore,
          BasicReadingRuntime::new,
          BasicReadingResult::new,
          deserializerFactory,
          BasicWritingRuntime::new,
          BasicWritingResult::empty,
          serializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected
  SimplifiedDynamicCollectionDefinitionCore<T, XS, XO, XD, YS, YO, YD, ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS, D>
  getThis() {
    return this;
  }

}
