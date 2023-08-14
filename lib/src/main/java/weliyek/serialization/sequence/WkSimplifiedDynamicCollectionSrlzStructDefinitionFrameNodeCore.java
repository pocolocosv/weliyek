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
package weliyek.serialization.sequence;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkBasicDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkBasicEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
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
import weliyek.util.array.WkDynamicSequenceSrlzStructDefinitionFrameNodeCore;

public final class WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XO extends WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                                        T,XS,
                                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkDecodingResultSrlzPacketOperationData<T>,
                                        XD,ZT,ZXO,?,ET,EXS,?,EXO,VXS>,
                        XD extends WkDynamicCollectionSrlzStructDefinitionFrameNode<
                                        T,XO,?,?,?,?,?,?,?,?,?,?,?,?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YO extends WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                                        T,YS,
                                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkEncodingResultSrlzPacketOperationData,
                                        YD,ZT,ZYO,?,ET,EYS,?,EYO,VYS>,
                        YD extends WkDynamicCollectionSrlzStructDefinitionFrameNode<
                                        T,?,YO,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZXS extends WkSettingsSrlzPacketOperationData,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,?>,
                        ZYS extends WkSettingsSrlzPacketOperationData,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,?>,
                        ZD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,ZXO>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                        ED extends WkSrlzStructDefinitionFrameNode<ET,EXO>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VYS extends WkSettingsSrlzPacketOperationData,
                        D extends WkDynamicCollectionSrlzStructDefinitionFrameNode<
                                      T, XO, YO, ZD, ET, EXS, EXD, EXO,
                                      EYS, EYD, EYO, ED, VXS, VYS>>
    extends WkDynamicSequenceSrlzStructDefinitionFrameNodeCore<
                        T, XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkDecodingResultSrlzPacketOperationData<T>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkEncodingResultSrlzPacketOperationData,
                        YO, YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                        VXS,
                        VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>, // VXO
                        VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>, // VXD
                        VYS,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>, // VYO
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>, // VYD
                        VariableSizeCollectionField<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>,
                        D,
                        WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>>
    implements WkDynamicCollectionSrlzStructDefinitionFrameNode<
                        T, XO, YO, ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS>
{

  public WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    WkOperationSettingsFactory<XO, ZXS> sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<YO, ZYS> sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<XO,VXS> collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<YO,VYS> collectionAndElementsSerializerSettingsFactory,
    String elementFieldLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ET, EXS, EXD, EXO, WkSzInputBytestreamBase<?>, EYS, EYD, EYO, WkSzOutputBytestreamBase<?>, ED>
      elementsDefinitionFactory,
    WkOperationSettingsFactory<VariableSizeCollectionFieldDeserializer<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    WkOperationSettingsFactory<VariableSizeCollectionFieldSerializer<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    //Function<InputBytestreamGeneralBase<?>, ReadingRuntimeControl<InputBytestream, InputBytestreamGeneralBase<? extends InputBytestream>, DeserializingRuntime<InputBytestream>>>
    //  deserializerRuntimeFactory,
    //BiFunction<XO, T, DeserializingResult<T>> deserializerResultFactory,
    WkSzPacketReaderOperationCoreFactory<T, XS, XD, WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>, XO, WkSzInputBytestreamBase<?>>
      deserializerFactory,
    //Function<OutputBytestreamGeneralBase<?>, WritingRuntimeControl<OutputBytestream, OutputBytestreamGeneralBase<? extends OutputBytestream>, SerializingRuntime<OutputBytestream>>>
    //  serializerRuntimeFactory,
    //Function<YO, SerializingResult> serializerResultFactory,
    WkSzPacketWriterOperationCoreFactory<T, YS, YD, WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>, YO, WkSzOutputBytestreamBase<?>>
      serializerFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> serializableClass,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    D definitionBody) {
    super(
          sizeFieldLabel,
          sizeDeserializerSettingsFactory,
          sizeSerializerSettingsFactory,
          (yk,yo,i) -> sizeValueFactory.apply(yo.serializable().size()),
          sizeDefinitionFactory,
          collectionAndElementsFieldLabel,
          collectionAndElementsDeserializerSettingsFactory,
          collectionAndElementsSerializerSettingsFactory,
          (yk, ayo, i) -> ayo.serializable(),
          (pc) -> VariableSizeCollectionField.
                        <T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>newCore(
                                elementFieldLabel,
                                minSize,
                                maxSize,
                                serializableClass,
                                elementDeserializerSettingsFactory,
                                elementSerializerSettingsFactory,
                                elementsDefinitionFactory,
                                collectionFactory,
                                pc),
          componentCore,
          WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicDecodingResultSrlzPacketOperationData::new,
          deserializerFactory,
          WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicEncodingResultSrlzPacketOperationData::empty,
          serializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected
  WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<T, XS, XO, XD, YS, YO, YD, ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS, D>
  getThis() {
    return this;
  }

}
