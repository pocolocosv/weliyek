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

import java.util.function.IntFunction;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationResultBasic;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;

public final class WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                        T extends WkPrimitiveArray<?,?>,
                        XD extends WkSerdeDtreeDynamicPrimitiveArrayDefinition<T,XO,?,? extends ZXD,? extends VXD>,
                        XO extends WkSerdeDtreeDynamicPrimitiveArrayReader<
                                        T,
                                        WkSerdeDtreeOperationSettings,
                                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        YD extends WkSerdeDtreeDynamicPrimitiveArrayDefinition<T,?,YO,? extends ZYD,? extends VYD>,
                        YO extends WkSerdeDtreeDynamicPrimitiveArrayWriter<
                                        T,
                                        WkSerdeDtreeOperationSettings,
                                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        ZT extends Number,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZXO extends WkSerdeDtreeNumberMsgReader<
                                        ZT,
                                        WkSerdeDtreeOperationSettings,?,
                                        ?,ZXD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<
                                        ZT,
                                        WkSerdeDtreeOperationSettings,?,?,ZYD>,
                        ZD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        VXD extends WkSerdeDtreeVariableSizePrimitiveArrayDefinition<T>,
                        VXO extends WkSerdeDtreeVariableSizePrimitiveArrayReader<
                                        T,WkSerdeDtreeOperationSettingsVariableLength,?,?,VXD>,
                        VYD extends WkSerdeDtreeVariableSizePrimitiveArrayDefinition<T>,
                        VYO extends WkSerdeDtreeVariableSizePrimitiveArrayWriter<
                                        T,WkSerdeDtreeOperationSettings,?,?,VYD>,
                        VD extends WkSerdeDtreeVariableSizePrimitiveArrayDefinition<T>,
                        D extends WkSerdeDtreeDynamicPrimitiveArrayDefinition<T,XO,YO,ZD,VD>>
    extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                        T,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        XO,
                        WkSerdeDtreeDynamicPrimitiveArrayReaderCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD,
                        WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                          T,XD,XO,?,?,
                          ZT,ZXD,ZXO,?,?,? extends ZXD,
                          VXD,VXO,?,?,? extends VXD,? extends XD>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YO,
                        WkSerdeDtreeDynamicPrimitiveArrayWriterCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD,
                        WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                          T,?,?,YD,YO,
                          ZT,?,?,ZYD,ZYO,? extends ZYD,
                          ?,?,VYD,VYO,? extends VYD,? extends YD>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT,
                        WkSerdeDtreeOperationSettings,
                        ZXO, ZXD,
                        WkSerdeDtreeOperationSettings,
                        ZYO, ZYD,
                        ZD,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        VXO, VXD,
                        WkSerdeDtreeOperationSettings,
                        VYO, VYD,
                        VD,
                        D,
                        WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                          T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>>
    implements WkSerdeDtreeDynamicPrimitiveArrayDefinition<T, XO, YO, ZD, VD>
{

  protected WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore(
    String sizeComponentLabel,
    IntFunction<ZT> sizeComponentIntToNumber,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
                  ZT,WkSerdeDtreeOperationSettings,?,?,ZXD,?,ZXO,?,
                  WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                  WkSerdeDtreeOperationSettings,?,?,ZYD,?,ZYO,?,
                  WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                  ZD,?>> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
                  T,WkSerdeDtreeOperationSettingsVariableLength,?,?,VXD,?,VXO,?,
                  WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                  WkSerdeDtreeOperationSettings,?,?,VYD,?,VYO,?,
                  WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                  VD,?>> varseqComponentDefinitionFactory,
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<
      WkSerdeDtreeOperationSettings,
      WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
        T,XD,XO,?,?, ZT,ZXD,ZXO,?,?,? extends ZXD,VXD,VXO,?,?,? extends VXD,? extends XD>,
      WkSerdeDtreeDynamicPrimitiveArrayReaderCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>,
      WkSerdeDtreeBytestreamInputBase<?>> readingOpFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<
      T,WkSerdeDtreeOperationSettings,
      WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
        T,?,?,YD,YO,ZT,?,?,ZYD,ZYO,? extends ZYD,?,?,VYD,VYO,? extends VYD,? extends YD>,
      WkSerdeDtreeDynamicPrimitiveArrayWriterCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>,
      WkSerdeDtreeBytestreamOutputBase<?>> writingOpFactory,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          sizeComponentLabel,
          WkSerdeDtreeOperationSettings::none,
          WkSerdeDtreeOperationSettings::none,
          (zk,yo,i) -> sizeComponentIntToNumber.apply(yo.serializable().getLength()), // Set size component value from wrapper length.
          sizeComponentDefinitionFactory,
          varseqComponentLabel,
          (i,xo) -> {
            ZT sizeNumber = xo.size().get()
                                     .firstOperation().get()
                                     .result().get()
                                     .serializable().get();
            return WkSerdeDtreeOperationSettingsVariableLength.withLength(sizeNumber.intValue());
          },
          WkSerdeDtreeOperationSettings::none,
          (vk,yo,i) -> yo.serializable(),
          varseqComponentDefinitionFactory,
          componentCore,
          WkSerdeDtreeOperationInputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          readingOpFactory,
          WkSerdeDtreeOperationOutputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          writingOpFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected
  WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>
  getThis() {
    return this;
  }

  @Override
  public int extractLengthFromSerializablesSequence(T sequence) {
    return definition().extractLengthFromSerializablesSequence(sequence);
  }

}
