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
import weliyek.serialization.WkBasicDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkBasicEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;

public final class WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                        T extends WkPrimitiveArray<?,?>,
                        XD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,XO,?,? extends ZXD,? extends VXD>,
                        XO extends WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNode<
                                        T,
                                        WkSettingsSrlzPacketOperationData,
                                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkDecodingResultSrlzPacketOperationData<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        YD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,?,YO,? extends ZYD,? extends VYD>,
                        YO extends WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,
                                        WkSettingsSrlzPacketOperationData,
                                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkEncodingResultSrlzPacketOperationData,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        ZT extends Number,
                        ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT>,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<
                                        ZT,
                                        WkSettingsSrlzPacketOperationData,?,
                                        ?,ZXD>,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT>,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<
                                        ZT,
                                        WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                        ZD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT>,
                        VXD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T>,
                        VXO extends WkVariableSizePrimitiveArraySrlzInputPacketDecoderFrameNode<
                                        T,WkSzVariableLengthOperationSettings,?,?,VXD>,
                        VYD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T>,
                        VYO extends WkVariableSizePrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,WkSettingsSrlzPacketOperationData,?,?,VYD>,
                        VD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T>,
                        D extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,XO,YO,ZD,VD>>
    extends WkDynamicSequenceSrlzStructDefinitionFrameNodeCore<
                        T,
                        WkSettingsSrlzPacketOperationData,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkDecodingResultSrlzPacketOperationData<T>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkEncodingResultSrlzPacketOperationData,
                        YO, YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT,
                        WkSettingsSrlzPacketOperationData,
                        ZXO, ZXD,
                        WkSettingsSrlzPacketOperationData,
                        ZYO, ZYD,
                        ZD,
                        WkSzVariableLengthOperationSettings,
                        VXO, VXD,
                        WkSettingsSrlzPacketOperationData,
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
      ZT,WkSettingsSrlzPacketOperationData,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSettingsSrlzPacketOperationData,
      ZYD,ZYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      T,WkSzVariableLengthOperationSettings,VXD,VXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSettingsSrlzPacketOperationData,
      VYD,VYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,VD> varseqComponentDefinitionFactory,
    WkSzPacketReaderOperationCoreFactory<
      T,WkSettingsSrlzPacketOperationData,XD,
      WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      XO,WkSzInputBytestreamBase<?>> readingOpFactory,
    WkSzPacketWriterOperationCoreFactory<
      T,WkSettingsSrlzPacketOperationData,YD,
      WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<T,XD,XO,YD,YO,ZT,ZXD,ZXO,ZYD,ZYO,ZD,VXD,VXO,VYD,VYO,VD,D>,
      YO,WkSzOutputBytestreamBase<?>> writingOpFactory,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          sizeComponentLabel,
          WkSettingsSrlzPacketOperationData::none,
          WkSettingsSrlzPacketOperationData::none,
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
          WkSettingsSrlzPacketOperationData::none,
          (vk,yo,i) -> yo.serializable(),
          varseqComponentDefinitionFactory,
          componentCore,
          WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicDecodingResultSrlzPacketOperationData::new,
          readingOpFactory,
          WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicEncodingResultSrlzPacketOperationData::empty,
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
