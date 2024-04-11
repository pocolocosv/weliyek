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

import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeNodeLeafStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataDecoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataEncoderEngineFactory;
import weliyek.serialization.sequence.WkSerdeUtilsPrimitiveArrayLengthGetter;

public abstract class WkSerdeDtreeGenericPrimitiveArrayDefinitionCore<
                        T extends WkPrimitiveArray<?, ?>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQC extends WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<?,?,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreePrimitiveArrayDefinition<T>,
                        XO extends WkSerdeDtreePrimitiveArrayReader<T,XS,?,XR,XD>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<?,?,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreePrimitiveArrayDefinition<T>,
                        YO extends WkSerdeDtreePrimitiveArrayWriter<T,YS,?,YR,YD>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreePrimitiveArrayDefinition<T>,
                        DC extends WkSerdeDtreeGenericPrimitiveArrayDefinitionCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends WkSerdeDtreeNodeLeafStructDefinitionCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
    implements WkSerdeDtreePrimitiveArrayDefinition<T>
{

  final ToIntBiFunction<? super XS, ? super XD> rxRequestedLengthEvaluator;
  final WkSerdeUtilsPrimitiveArrayLengthGetter<? super T, ? super YS, ? super YD> txRequestedLengthEvaluator;

  protected WkSerdeDtreeGenericPrimitiveArrayDefinitionCore(
    int stepSize,
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    ToIntBiFunction<? super XS, ? super XD> rxRequestedLengthEvaluator,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<T,XS,XD,DC,XO,AXB> readingOpFactory,
    WkSerdeDtreeNodeDataDecoderEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    WkSerdeUtilsPrimitiveArrayLengthGetter<? super T, ? super YS, ? super YD> txRequestedLengthEvaluator,
    Function<AYB,YQC> txRuntimeFactory,
    BiFunction<YO,T,YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<T,YS,YD,DC,YO,AYB> writingOpFactory,
    WkSerdeDtreeNodeDataEncoderEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory,
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
    this.rxRequestedLengthEvaluator = Objects.requireNonNull(rxRequestedLengthEvaluator);
    this.txRequestedLengthEvaluator = Objects.requireNonNull(txRequestedLengthEvaluator);
  }

}
