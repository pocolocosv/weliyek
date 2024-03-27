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
package weliyek.util.array;

import java.util.function.ToIntBiFunction;

import weliyek.serialization.WkBasicResultSrlzPacketOperationData;
import weliyek.serialization.WkBasicSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSzReadEngineFactory;
import weliyek.serialization.WkSzWriteEngineFactory;
import weliyek.serialization.sequence.WkPrimitiveArrayLengthGetter;
import weliyek.serialization.sequence.WkSimplifiedSequenceEncodingRuntimeSrlzPacketOperationCtrl;

public class WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
                        T extends WkPrimitiveArray<?, ?>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XO extends WkSerdeDtreeGenericPrimitiveArrayReader<
                                        T,
                                        XS,
                                        WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        D>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YO extends WkSerdeDtreeGenericPrimitiveArrayWriter<
                                        T,
                                        YS,
                                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        D>,
                        D extends WkSerdeDtreeGenericPrimitiveArrayDefinition<T>>
    extends WkSerdeDtreeGenericPrimitiveArrayDefinitionCore<
                        T,
                        XS,
                        WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        D, XO,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        D, YO,
                        WkSzOutputBytestreamBase<?>,
                        D,
                        WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T,XS,XO,YS,YO,D>>
{

  public WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified(
    int stepSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    ToIntBiFunction<? super XS, D> rxRequestedLengthEvaluator,
    WkSzPacketReaderOperationCoreFactory<T, XS, D, WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T, XS, XO, YS, YO, D>, XO, WkSzInputBytestreamBase<?>> readingOpFactory,
    WkSzReadEngineFactory<T, ? super WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<WkSzInputBytestream, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>, ? super XO> rxSerializerFactory,
    WkPrimitiveArrayLengthGetter<? super T,? super YS,? super D> txRequestedLengthEvaluator,
    WkSzPacketWriterOperationCoreFactory<T, YS, D, WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T, XS, XO, YS, YO, D>, YO, WkSzOutputBytestreamBase<?>> writingOpFactory,
    WkSzWriteEngineFactory<T, ? super WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<WkSzOutputBytestream, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          stepSize,
          componentCore,
          rxRequestedLengthEvaluator,
          WkBasicSequenceDecodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicResultSrlzPacketOperationData::new,
          readingOpFactory,
          rxSerializerFactory,
          txRequestedLengthEvaluator,
          WkSimplifiedSequenceEncodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicResultSrlzPacketOperationData::new,
          writingOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T,XS,XO,YS,YO,D> getThis() {
    return this;
  }

}
