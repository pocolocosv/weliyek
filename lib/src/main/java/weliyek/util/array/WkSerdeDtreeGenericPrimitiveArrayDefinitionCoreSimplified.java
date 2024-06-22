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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgPrimitiveReaderFactory;
import weliyek.serialization.WkSerdeDtreeMsgPrimitiveWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataDecoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataEncoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationResultBasic;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.sequence.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrlSimplified;
import weliyek.serialization.sequence.WkSerdeUtilsPrimitiveArrayLengthGetter;

public class WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
                        T extends WkPrimitiveArray<?, ?>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XO extends WkSerdeDtreePrimitiveArrayReader<
                                        T,
                                        XS,
                                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        D>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YO extends WkSerdeDtreePrimitiveArrayWriter<
                                        T,
                                        YS,
                                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        D>,
                        D extends WkSerdeDtreePrimitiveArrayDefinition<T>>
    extends WkSerdeDtreeGenericPrimitiveArrayDefinitionCore<
                        T,
                        XS,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        D,
                        WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T,XS,XO,?,?,D>,
                        XO,
                        WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<T,XS,D,XO>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        YS,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        D,
                        WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T,?,?,YS,YO,D>,
                        YO,
                        WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<T,YS,D,YO>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        D,
                        WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T,XS,XO,YS,YO,D>>
{

  public WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified(
    int stepSize,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    ToIntBiFunction<? super XS, D> rxRequestedLengthEvaluator,
    WkSerdeDtreeMsgPrimitiveReaderFactory<
      XS,
      WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T,XS,XO,?,?,D>,
      WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<T,XS,D,XO>,
      WkSerdeDtreeBytestreamInputBase<?>> readingOpFactory,
    WkSerdeDtreeNodeDataDecoderEngineFactory<T, ? super WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<WkSerdeDtreeBytestreamInput, WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>>, ? super XO> rxSerializerFactory,
    WkSerdeUtilsPrimitiveArrayLengthGetter<? super T,? super YS,? super D> txRequestedLengthEvaluator,
    WkSerdeDtreeMsgPrimitiveWriterCoreFactory<
      T,YS,
      WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<T,?,?,YS,YO,D>,
      WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<T,YS,D,YO>,
      WkSerdeDtreeBytestreamOutputBase<?>> writingOpFactory,
    WkSerdeDtreeNodeDataEncoderEngineFactory<T, ? super WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<WkSerdeDtreeBytestreamOutput, WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          stepSize,
          componentCore,
          rxRequestedLengthEvaluator,
          WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          readingOpFactory,
          rxSerializerFactory,
          txRequestedLengthEvaluator,
          WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
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
