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

import weliyek.serialization.WkSzReadEngineFactory;
import weliyek.serialization.WkSzWriteEngineFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkPrimitiveArraySrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.WkBasicDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkBasicSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkBasicEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.sequence.WkPrimitiveArrayLengthGetter;
import weliyek.serialization.sequence.WkSimplifiedSequenceEncodingRuntimeSrlzPacketOperationCtrl;

public class WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<
                        T extends WkPrimitiveArray<?, ?>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XO extends WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode<
                                        T,
                                        XS,
                                        WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkDecodingResultSrlzPacketOperationData<T>,
                                        D>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YO extends WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<
                                        T,
                                        YS,
                                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkEncodingResultSrlzPacketOperationData,
                                        D>,
                        D extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<T,XO>>
    extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<
                        T,
                        XS,
                        WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkDecodingResultSrlzPacketOperationData<T>,
                        D, XO,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkEncodingResultSrlzPacketOperationData,
                        D, YO,
                        WkSzOutputBytestreamBase<?>,
                        D,
                        WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<T,XS,XO,YS,YO,D>>
{

  public WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore(
    int stepSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    ToIntBiFunction<? super XS, D> rxRequestedLengthEvaluator,
    WkSzPacketReaderOperationCoreFactory<T, XS, D, WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<T, XS, XO, YS, YO, D>, XO, WkSzInputBytestreamBase<?>> readingOpFactory,
    WkSzReadEngineFactory<T, ? super WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<WkSzInputBytestream, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>, ? super XO> rxSerializerFactory,
    WkPrimitiveArrayLengthGetter<? super T,? super YS,? super D> txRequestedLengthEvaluator,
    WkSzPacketWriterOperationCoreFactory<T, YS, D, WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<T, XS, XO, YS, YO, D>, YO, WkSzOutputBytestreamBase<?>> writingOpFactory,
    WkSzWriteEngineFactory<T, ? super WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<WkSzOutputBytestream, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          stepSize,
          componentCore,
          rxRequestedLengthEvaluator,
          WkBasicSequenceDecodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicDecodingResultSrlzPacketOperationData::new,
          readingOpFactory,
          rxSerializerFactory,
          txRequestedLengthEvaluator,
          WkSimplifiedSequenceEncodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicEncodingResultSrlzPacketOperationData::empty,
          writingOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<T,XS,XO,YS,YO,D> getThis() {
    return this;
  }

}
