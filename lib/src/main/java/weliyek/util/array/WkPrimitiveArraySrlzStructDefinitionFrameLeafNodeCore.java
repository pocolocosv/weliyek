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

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;

import weliyek.serialization.WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.WkPrimitiveArraySrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameLeafNodeCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSzReadEngineFactory;
import weliyek.serialization.WkSzWriteEngineFactory;
import weliyek.serialization.sequence.WkPrimitiveArrayLengthGetter;

public abstract class WkPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<
                        T extends WkPrimitiveArray<?, ?>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQC extends WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<?,?,?>,
                        XR extends WkResultSrlzPacketOperationData<T>,
                        XD extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<T>,
                        XO extends WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode<T,XS,?,XR,XD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQC extends WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<?,?,?>,
                        YR extends WkResultSrlzPacketOperationData<T>,
                        YD extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<T>,
                        YO extends WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<T,YS,?,YR,YD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        D extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<T>,
                        DC extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends WkSrlzStructDefinitionFrameLeafNodeCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
    implements WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<T>
{

  private final int serializationStepSize;

  final ToIntBiFunction<? super XS, ? super XD> rxRequestedLengthEvaluator;
  final WkPrimitiveArrayLengthGetter<? super T, ? super YS, ? super YD> txRequestedLengthEvaluator;

  protected WkPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore(
    int stepSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    ToIntBiFunction<? super XS, ? super XD> rxRequestedLengthEvaluator,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<T,XS,XD,DC,XO,AXB> readingOpFactory,
    WkSzReadEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    WkPrimitiveArrayLengthGetter<? super T, ? super YS, ? super YD> txRequestedLengthEvaluator,
    Function<AYB,YQC> txRuntimeFactory,
    BiFunction<YO,T,YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<T,YS,YD,DC,YO,AYB> writingOpFactory,
    WkSzWriteEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          rxSerializerFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
    this.serializationStepSize = stepSize;
    this.rxRequestedLengthEvaluator = Objects.requireNonNull(rxRequestedLengthEvaluator);
    this.txRequestedLengthEvaluator = Objects.requireNonNull(txRequestedLengthEvaluator);
  }

  @Override
  public int getSerializationStepSize() {
    return this.serializationStepSize;
  }

}
