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

import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzBasicReadingResult;
import weliyek.serialization.WkSzBasicReadingRuntime;
import weliyek.serialization.WkSzBasicWritingResult;
import weliyek.serialization.WkSzBasicWritingRuntime;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;

public final class WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                        T extends WkPrimitiveArray<?,?>,
                        XD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,XO,?,? extends ZXD,? extends VXD>,
                        XO extends WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNode<
                                        T,
                                        WkSzOperationSettings,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        YD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,?,YO,? extends ZYD,? extends VYD>,
                        YO extends WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,
                                        WkSzOperationSettings,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        ZT extends Number,
                        ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,ZXO>,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<
                                        ZT,
                                        WkSzOperationSettings,?,
                                        ?,ZXD>,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,?>,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<
                                        ZT,
                                        WkSzOperationSettings,?,?,ZYD>,
                        ZD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,ZXO>,
                        VXD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T,VXO>,
                        VXO extends WkVariableSizePrimitiveArraySrlzInputPacketDecoderFrameNode<
                                        T,WkSzVariableLengthOperationSettings,?,?,VXD>,
                        VYD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T,?>,
                        VYO extends WkVariableSizePrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,WkSzOperationSettings,?,?,VYD>,
                        VD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T,VXO>,
                        D extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,XO,YO,ZD,VD>>
    extends WkDynamicSequenceSrlzStructDefinitionFrameNodeCore<
                        T,
                        WkSzOperationSettings,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YO, YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT,
                        WkSzOperationSettings,
                        ZXO, ZXD,
                        WkSzOperationSettings,
                        ZYO, ZYD,
                        ZD,
                        WkSzVariableLengthOperationSettings,
                        VXO, VXD,
                        WkSzOperationSettings,
                        VYO, VYD,
                        VD,
                        D,
                        WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                          T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>>
    implements WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T, XO, YO, ZD, VD>
{

  protected WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore(
    String sizeComponentLabel,
    IntFunction<ZT> sizeComponentIntToNumber,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT,WkSzOperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSzOperationSettings,
      ZYD,ZYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      T,WkSzVariableLengthOperationSettings,VXD,VXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSzOperationSettings,
      VYD,VYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,VD> varseqComponentDefinitionFactory,
    WkSzPacketReaderOperationCoreFactory<
      T,WkSzOperationSettings,XD,
      WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      XO,WkSzInputBytestreamBase<?>> readingOpFactory,
    WkSzPacketWriterOperationCoreFactory<
      T,WkSzOperationSettings,YD,
      WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      YO,WkSzOutputBytestreamBase<?>> writingOpFactory,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          sizeComponentLabel,
          WkSzOperationSettings::none,
          WkSzOperationSettings::none,
          (zk,yo,i) -> sizeComponentIntToNumber.apply(yo.serializable().getLength()), // Set size component value from wrapper length.
          sizeComponentDefinitionFactory,
          varseqComponentLabel,
          (i,xo) -> {
            ZT sizeNumber = xo.size().field().get()
                                     .firstOperation().get()
                                     .result().get()
                                     .deserialized().get();
            return WkSzVariableLengthOperationSettings.withLength(sizeNumber.intValue());
          },
          WkSzOperationSettings::none,
          (vk,yo,i) -> yo.serializable(),
          varseqComponentDefinitionFactory,
          componentCore,
          WkSzBasicReadingRuntime::new,
          WkSzBasicReadingResult::new,
          readingOpFactory,
          WkSzBasicWritingRuntime::new,
          WkSzBasicWritingResult::empty,
          writingOpFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected
  WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>
  getThis() {
    return this;
  }

  @Override
  public int extractLengthFromSerializablesSequence(T sequence) {
    return definition().extractLengthFromSerializablesSequence(sequence);
  }

}
