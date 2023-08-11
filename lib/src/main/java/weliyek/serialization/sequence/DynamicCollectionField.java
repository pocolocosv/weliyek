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
import java.util.function.IntFunction;
import java.util.function.Predicate;

import weliyek.serialization.OperationSubsegmentSettingsFactory;
import weliyek.serialization.ProtocolDefinitionFactory;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzDefinition;
import weliyek.serialization.WkSzDefinitionCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketReaderOperation;
import weliyek.serialization.WkSzPacketWriterOperation;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.filter.FieldTester;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;
import weliyek.serialization.number.WkSzNumberWriter;

public class DynamicCollectionField<
                        T extends Collection<ET>,
                        XS extends WkSzOperationSettings,
                        YS extends WkSzOperationSettings,
                        ZT extends Number,
                        ZXS extends WkSzOperationSettings,
                        ZXO extends WkSzNumberReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,?>,
                        ZYS extends WkSzOperationSettings,
                        ZYO extends WkSzNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ZD extends WkSzNumberDefinition<ZT,ZXO>,
                        ET,
                        EXS extends WkSzOperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        EYS extends WkSzOperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        ED extends WkSzDefinition<ET,EXO>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VYS extends WkSzOperationSettings>
    implements WkSzDynamicCollectionDefinition<
                        T,
                        DynamicCollectionFieldDeserializer<
                          T, XS,
                          WkSzReadingRuntime<WkSzInputBytestream>,
                          WkSzReadingResult<T>,
                          DynamicCollectionField<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                        DynamicCollectionFieldSerializer<
                          T, YS,
                          WkSzWritingRuntime<WkSzOutputBytestream>,
                          WkSzWritingResult,
                          DynamicCollectionField<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS>
{

  public static <T extends Collection<ET>,
                 XS extends WkSzOperationSettings,
                 YS extends WkSzOperationSettings,
                 ZT extends Number,
                 ZXS extends WkSzOperationSettings,
                 ZXO extends WkSzNumberReader<ZT,ZXS,?,?,ZXD>,
                 ZXD extends WkSzNumberDefinition<ZT,?>,
                 ZYS extends WkSzOperationSettings,
                 ZYO extends WkSzNumberWriter<ZT,ZYS,?,?,ZYD>,
                 ZYD extends WkSzNumberDefinition<ZT,?>,
                 ZD extends WkSzNumberDefinition<ZT,ZXO>,
                 ET,
                 EXS extends WkSzOperationSettings,
                 EXD extends WkSzDefinition<ET,?>,
                 EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                 EYS extends WkSzOperationSettings,
                 EYD extends WkSzDefinition<ET,?>,
                 EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                 ED extends WkSzDefinition<ET,EXO>,
                 VXS extends WkSzVariableLengthOperationSettings,
                 VYS extends WkSzOperationSettings>
  WkSzStruct<
                T, XS,
                DynamicCollectionField<
                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                DynamicCollectionFieldDeserializer<
                  T, XS,
                  WkSzReadingRuntime<WkSzInputBytestream>,
                  WkSzReadingResult<T>,
                  DynamicCollectionField<
                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                  ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                WkSzInputBytestreamBase<?>,
                YS,
                DynamicCollectionField<
                  T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                DynamicCollectionFieldSerializer<
                  T, YS,
                  WkSzWritingRuntime<WkSzOutputBytestream>,
                  WkSzWritingResult,
                  DynamicCollectionField<
                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                  ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                WkSzOutputBytestreamBase<?>,
                DynamicCollectionField<
                T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>
  newPacketStructure(
    String dynamicCollectionLabel,
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldSerializer<T, YS, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    ProtocolDefinitionFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldSerializer<T, YS, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    ProtocolDefinitionFactory<ET, EXS, EXD, EXO, WkSzInputBytestreamBase<?>, EYS, EYD, EYO, WkSzOutputBytestreamBase<?>, ED>
      elementDefinitionFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldDeserializer<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldSerializer<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass) {
    return new WkSzStruct<>(
                  dynamicCollectionLabel,
                  (pc) -> DynamicCollectionField.newCore(
                      minSize,
                      maxSize,
                      sizeFieldLabel,
                      sizeDeserializerSettingsFactory,
                      sizeSerializerSettingsFactory,
                      sizeValueFactory,
                      sizeDefinitionFactory,
                      collectionAndElementsFieldLabel,
                      collectionAndElementsDeserializerSettingsFactory,
                      collectionAndElementsSerializerSettingsFactory,
                      elementLabel,
                      elementDefinitionFactory,
                      elementDeserializerSettingsFactory,
                      elementSerializerSettingsFactory,
                      collectionFactory,
                      collectionClass,
                      pc),
                  WkSzCountingInputBytestream::new,
                  WkSzCountingOutputBytestream::new);
  }

  public static <T extends Collection<ET>,
                 XS extends WkSzOperationSettings,
                 YS extends WkSzOperationSettings,
                 ZT extends Number,
                 ZXS extends WkSzOperationSettings,
                 ZXO extends WkSzNumberReader<ZT,ZXS,?,?,ZXD>,
                 ZXD extends WkSzNumberDefinition<ZT,?>,
                 ZYS extends WkSzOperationSettings,
                 ZYO extends WkSzNumberWriter<ZT,ZYS,?,?,ZYD>,
                 ZYD extends WkSzNumberDefinition<ZT,?>,
                 ZD extends WkSzNumberDefinition<ZT,ZXO>,
                 ET,
                 EXS extends WkSzOperationSettings,
                 EXD extends WkSzDefinition<ET,?>,
                 EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                 EYS extends WkSzOperationSettings,
                 EYD extends WkSzDefinition<ET,?>,
                 EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                 ED extends WkSzDefinition<ET,EXO>,
                 VXS extends WkSzVariableLengthOperationSettings,
                 VYS extends WkSzOperationSettings>
  WkSzDefinitionCore<
                 T, XS,?,?,
                 DynamicCollectionField<
                   T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                 DynamicCollectionFieldDeserializer<
                   T, XS,
                   WkSzReadingRuntime<WkSzInputBytestream>,
                   WkSzReadingResult<T>,
                   DynamicCollectionField<
                     T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                   ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                 WkSzInputBytestreamBase<?>,
                 YS,?,?,
                 DynamicCollectionField<
                   T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                 DynamicCollectionFieldSerializer<
                   T, YS,
                   WkSzWritingRuntime<WkSzOutputBytestream>,
                   WkSzWritingResult,
                   DynamicCollectionField<
                     T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                   ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                 WkSzOutputBytestreamBase<?>,
                 DynamicCollectionField<
                   T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                   ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>,?>
  newCore(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldSerializer<T, YS, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    ProtocolDefinitionFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldSerializer<T, YS, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    ProtocolDefinitionFactory<ET, EXS, EXD, EXO, WkSzInputBytestreamBase<?>, EYS, EYD, EYO, WkSzOutputBytestreamBase<?>, ED>
      elementDefinitionFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldDeserializer<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldSerializer<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass,
    WkSzStructComponentCoreBase<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> componentCore) {
    return new DynamicCollectionField<
                 T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                 ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>(
                     minSize,
                     maxSize,
                     sizeFieldLabel,
                     sizeDeserializerSettingsFactory,
                     sizeSerializerSettingsFactory,
                     sizeValueFactory,
                     sizeDefinitionFactory,
                     collectionAndElementsFieldLabel,
                     collectionAndElementsDeserializerSettingsFactory,
                     collectionAndElementsSerializerSettingsFactory,
                     elementLabel,
                     elementDefinitionFactory,
                     elementDeserializerSettingsFactory,
                     elementSerializerSettingsFactory,
                     collectionFactory,
                     collectionClass,
                     componentCore).definitionCore;
  }

  private final SimplifiedDynamicCollectionDefinitionCore<
                        T, XS,
                        DynamicCollectionFieldDeserializer<
                          T, XS,
                          WkSzReadingRuntime<WkSzInputBytestream>,
                          WkSzReadingResult<T>,
                          DynamicCollectionField<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        DynamicCollectionField<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        YS,
                        DynamicCollectionFieldSerializer<
                          T, YS,
                          WkSzWritingRuntime<WkSzOutputBytestream>,
                          WkSzWritingResult,
                          DynamicCollectionField<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        DynamicCollectionField<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                        ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS,
                        DynamicCollectionField<
                          T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                          ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>
                    definitionCore;

  private DynamicCollectionField(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldSerializer<T, YS, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    ProtocolDefinitionFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<DynamicCollectionFieldSerializer<T, YS, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    ProtocolDefinitionFactory<ET, EXS, EXD, EXO, WkSzInputBytestreamBase<?>, EYS, EYD, EYO, WkSzOutputBytestreamBase<?>, ED>
      elementDefinitionFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldDeserializer<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    OperationSubsegmentSettingsFactory<VariableSizeCollectionFieldSerializer<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass,
    WkSzStructComponentCoreBase<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> componentCore) {
    this.definitionCore = new SimplifiedDynamicCollectionDefinitionCore<
                                  T, XS,
                                  DynamicCollectionFieldDeserializer<
                                    T, XS,
                                    WkSzReadingRuntime<WkSzInputBytestream>,
                                    WkSzReadingResult<T>,
                                    DynamicCollectionField<
                                      T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                    ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                                  DynamicCollectionField<
                                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                  YS,
                                  DynamicCollectionFieldSerializer<
                                    T, YS,
                                    WkSzWritingRuntime<WkSzOutputBytestream>,
                                    WkSzWritingResult,
                                    DynamicCollectionField<
                                      T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                    ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                                  DynamicCollectionField<
                                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                  ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                                  ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS,
                                  DynamicCollectionField<
                                    T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                                    ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>(
                                        minSize,
                                        maxSize,
                                        sizeFieldLabel,
                                        sizeDeserializerSettingsFactory,
                                        sizeSerializerSettingsFactory,
                                        sizeValueFactory,
                                        sizeDefinitionFactory,
                                        collectionAndElementsFieldLabel,
                                        collectionAndElementsDeserializerSettingsFactory,
                                        collectionAndElementsSerializerSettingsFactory,
                                        elementLabel,
                                        elementDefinitionFactory,
                                        elementDeserializerSettingsFactory,
                                        elementSerializerSettingsFactory,
                                        //BasicReadingRuntime::new,
                                        //BasicReadingResult::new,
                                        (i,xs,axb,xkc,xdc) -> new DynamicCollectionFieldDeserializerImpl<>(i,xs,axb,xkc,xdc).operationCore,
                                        //BasicWritingRuntime::new,
                                        //BasicWritingResult::empty,
                                        (i,y,ys,ayb,ykc,ydc) -> new DynamicCollectionFieldSerializerImpl<>(i,y,ys,ayb,ykc,ydc).operationCore,
                                        collectionFactory,
                                        collectionClass,
                                        componentCore,
                                        this);
  }

  @Override
  public
  WkSzStructSubcomponent<DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, DynamicCollectionFieldSerializer<T, YS, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZD>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSzStructSubcomponent<DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, DynamicCollectionFieldSerializer<T, YS, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, DynamicCollectionField<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VariableSizeCollectionField<T, VXS, VYS, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED>>
  variableSequence() {
    return this.definitionCore.variableSequence();
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
  public FieldTester<?, ?> makeTester(
  Predicate<? super DynamicCollectionFieldDeserializer<T, XS, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<T>, DynamicCollectionField<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>> test,
  String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}