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

import weliyek.serialization.WkBasicResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeDTreeNumberReader;
import weliyek.serialization.number.WkSerdeDTreeNumberWriter;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;

public final class WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                        T extends WkPrimitiveArray<?,?>,
                        XD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,XO,?,? extends ZXD,? extends VXD>,
                        XO extends WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNode<
                                        T,
                                        WkSettingsSrlzPacketOperationData,
                                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        YD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,?,YO,? extends ZYD,? extends VYD>,
                        YO extends WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,
                                        WkSettingsSrlzPacketOperationData,
                                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        ZT extends Number,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ZXO extends WkSerdeDTreeNumberReader<
                                        ZT,
                                        WkSettingsSrlzPacketOperationData,?,
                                        ?,ZXD>,
                        ZYD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ZYO extends WkSerdeDTreeNumberWriter<
                                        ZT,
                                        WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                        ZD extends WkSerdeDTreeNumberDefinition<ZT>,
                        VXD extends WkSerdeDTreeGenericVariableSizePrimitiveArrayDefinition<T>,
                        VXO extends WkSerdeDTreeGenericVariableSizePrimitiveArrayReader<
                                        T,WkSzVariableLengthOperationSettings,?,?,VXD>,
                        VYD extends WkSerdeDTreeGenericVariableSizePrimitiveArrayDefinition<T>,
                        VYO extends WkSerdeDTreeGenericVariableSizePrimitiveArrayWriter<
                                        T,WkSettingsSrlzPacketOperationData,?,?,VYD>,
                        VD extends WkSerdeDTreeGenericVariableSizePrimitiveArrayDefinition<T>,
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
                        WkResultSrlzPacketOperationData<T>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
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
                                     .serializable().get();
            return WkSzVariableLengthOperationSettings.withLength(sizeNumber.intValue());
          },
          WkSettingsSrlzPacketOperationData::none,
          (vk,yo,i) -> yo.serializable(),
          varseqComponentDefinitionFactory,
          componentCore,
          WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicResultSrlzPacketOperationData::new,
          readingOpFactory,
          WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicResultSrlzPacketOperationData::new,
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
