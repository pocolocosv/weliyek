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

import weliyek.serialization.WkSerdeDtreeOperationResultBasic;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.number.WkSerdeDtreeNumberReader;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;

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
                        ZXD extends WkSerdeDtreeNumberDefinition<ZT>,
                        ZXO extends WkSerdeDtreeNumberReader<
                                        ZT,
                                        WkSerdeDtreeOperationSettings,?,
                                        ?,ZXD>,
                        ZYD extends WkSerdeDtreeNumberDefinition<ZT>,
                        ZYO extends WkSerdeDtreeNumberWriter<
                                        ZT,
                                        WkSerdeDtreeOperationSettings,?,?,ZYD>,
                        ZD extends WkSerdeDtreeNumberDefinition<ZT>,
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
                        XO, XD,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YO, YD,
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
      ZT,WkSerdeDtreeOperationSettings,ZXD,ZXO,WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,WkSerdeDtreeOperationSettings,
      ZYD,ZYO,WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,ZD> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      T,WkSerdeDtreeOperationSettingsVariableLength,VXD,VXO,WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,WkSerdeDtreeOperationSettings,
      VYD,VYO,WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,VD> varseqComponentDefinitionFactory,
    WkSzPacketReaderOperationCoreFactory<
      T,WkSerdeDtreeOperationSettings,XD,
      WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      XO,WkSerdeDtreeBytestreamInputBase<?>> readingOpFactory,
    WkSzPacketWriterOperationCoreFactory<
      T,WkSerdeDtreeOperationSettings,YD,
      WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      YO,WkSerdeDtreeBytestreamOutputBase<?>> writingOpFactory,
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
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
