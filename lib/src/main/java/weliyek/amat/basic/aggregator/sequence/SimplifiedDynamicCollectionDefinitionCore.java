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

import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.BasicReadingRuntime;
import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.BasicWritingRuntime;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberDeserializing;
import weliyek.amat.basic.number.NumberSerializing;
import weliyek.ketza.util.array.DynamicSequenceDefinitionCore;

public final class SimplifiedDynamicCollectionDefinitionCore<
                        T extends Collection<ET>,
                        XS extends OperationSettings,
                        XO extends DynamicCollectionFieldDeserializer<
                                        T,XS,
                                        DeserializingRuntime<InputBytestream>,
                                        DeserializingResult<T>,
                                        XD,ZT,ZXO,?,ET,EXS,?,EXO,VXS>,
                        XD extends DynamicCollectionFieldDefinition<
                                        T,XO,?,?,?,?,?,?,?,?,?,?,?,?>,
                        YS extends OperationSettings,
                        YO extends DynamicCollectionFieldSerializer<
                                        T,YS,
                                        SerializingRuntime<OutputBytestream>,
                                        SerializingResult,
                                        YD,ZT,ZYO,?,ET,EYS,?,EYO,VYS>,
                        YD extends DynamicCollectionFieldDefinition<
                                        T,?,YO,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZXS extends OperationSettings,
                        ZXO extends NumberDeserializing<ZT,ZXS,?,?,ZXD>,
                        ZXD extends NumberDefinition<ZT,?>,
                        ZYS extends OperationSettings,
                        ZYO extends NumberSerializing<ZT,ZYS,?,?,ZYD>,
                        ZYD extends NumberDefinition<ZT,?>,
                        ZD extends NumberDefinition<ZT,ZXO>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends DefinitionSegment<ET,?>,
                        EXO extends DeserializingOperation<ET,EXS,?,?,EXD>,
                        EYS extends OperationSettings,
                        EYD extends DefinitionSegment<ET,?>,
                        EYO extends SerializingOperation<ET,EYS,?,?,EYD>,
                        ED extends DefinitionSegment<ET,EXO>,
                        VXS extends VariableLengthSettings,
                        VYS extends OperationSettings,
                        D extends DynamicCollectionFieldDefinition<
                                      T, XO, YO, ZD, ET, EXS, EXD, EXO,
                                      EYS, EYD, EYO, ED, VXS, VYS>>
    extends DynamicSequenceDefinitionCore<
                        T, XS,
                        InputBytestream,
                        InputBytestreamGeneralBase<? extends InputBytestream>,
                        ReadingRuntimeControl<
                          InputBytestream,
                          InputBytestreamGeneralBase<? extends InputBytestream>,
                          DeserializingRuntime<InputBytestream>>,
                        DeserializingResult<T>,
                        XO, XD,
                        InputBytestreamGeneralBase<?>,
                        YS,
                        OutputBytestream,
                        OutputBytestreamGeneralBase<? extends OutputBytestream>,
                        WritingRuntimeControl<
                          OutputBytestream,
                          OutputBytestreamGeneralBase<? extends OutputBytestream>,
                          SerializingRuntime<OutputBytestream>>,
                        SerializingResult,
                        YO, YD,
                        OutputBytestreamGeneralBase<?>,
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
    implements DynamicCollectionFieldDefinition<
                        T, XO, YO, ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS>
{

  public SimplifiedDynamicCollectionDefinitionCore(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    OperationSubsegmentSettingsFactory<XO, ZXS> sizeDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<YO, ZYS> sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    ProtocolDefinitionFactory<ZT, ZXS, ZXD, ZXO, InputBytestreamGeneralBase<? extends InputBytestream>, ZYS, ZYD, ZYO, OutputBytestreamGeneralBase<? extends OutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    OperationSubsegmentSettingsFactory<XO,VXS> collectionAndElementsDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<YO,VYS> collectionAndElementsSerializerSettingsFactory,
    String elementFieldLabel,
    ProtocolDefinitionFactory<ET, EXS, EXD, EXO, InputBytestreamGeneralBase<?>, EYS, EYD, EYO, OutputBytestreamGeneralBase<?>, ED>
      elementsDefinitionFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldDeserializer<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldSerializer<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    //Function<InputBytestreamGeneralBase<?>, ReadingRuntimeControl<InputBytestream, InputBytestreamGeneralBase<? extends InputBytestream>, DeserializingRuntime<InputBytestream>>>
    //  deserializerRuntimeFactory,
    //BiFunction<XO, T, DeserializingResult<T>> deserializerResultFactory,
    PacketInputFieldReadingFactory<T, XS, XD, SimplifiedDynamicCollectionDefinitionCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>, XO, InputBytestreamGeneralBase<?>>
      deserializerFactory,
    //Function<OutputBytestreamGeneralBase<?>, WritingRuntimeControl<OutputBytestream, OutputBytestreamGeneralBase<? extends OutputBytestream>, SerializingRuntime<OutputBytestream>>>
    //  serializerRuntimeFactory,
    //Function<YO, SerializingResult> serializerResultFactory,
    PacketOutputFieldWritingFactory<T, YS, YD, SimplifiedDynamicCollectionDefinitionCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>, YO, OutputBytestreamGeneralBase<?>>
      serializerFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> serializableClass,
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
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
