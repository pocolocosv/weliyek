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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataReader;
import weliyek.serialization.WkSerdeDTreeNodeDataWriter;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;

public class WkDynamicCollectionSrlzStructNode<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        YS extends WkSettingsSrlzPacketOperationData,
                        ZT extends Number,
                        ZXS extends WkSettingsSrlzPacketOperationData,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ZYS extends WkSettingsSrlzPacketOperationData,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ZD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDTreeNodeDataWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDTreeNodeStructDefinition<ET>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VYS extends WkSettingsSrlzPacketOperationData>
    implements WkDynamicCollectionSrlzStructDefinitionFrameNode<
                        T,
                        WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                          T, XS,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                          WkResultSrlzPacketOperationData<T>,
                          WkDynamicCollectionSrlzStructNode<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                        WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                          T, YS,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                          WkResultSrlzPacketOperationData<T>,
                          WkDynamicCollectionSrlzStructNode<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS>
{

  public static <T extends Collection<ET>,
                 XS extends WkSettingsSrlzPacketOperationData,
                 YS extends WkSettingsSrlzPacketOperationData,
                 ZT extends Number,
                 ZXS extends WkSettingsSrlzPacketOperationData,
                 ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,ZXS,?,?,ZXD>,
                 ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                 ZYS extends WkSettingsSrlzPacketOperationData,
                 ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,ZYS,?,?,ZYD>,
                 ZYD extends WkSerdeDTreeNumberDefinition<ZT>,
                 ZD extends WkSerdeDTreeNumberDefinition<ZT>,
                 ET,
                 EXS extends WkSettingsSrlzPacketOperationData,
                 EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                 EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSettingsSrlzPacketOperationData,
                 EYD extends WkSerdeDTreeNodeStructDefinition<ET>,
                 EYO extends WkSerdeDTreeNodeDataWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDTreeNodeStructDefinition<ET>,
                 VXS extends WkSzVariableLengthOperationSettings,
                 VYS extends WkSettingsSrlzPacketOperationData>
  WkSrlzStruct<
                T, XS,
                WkDynamicCollectionSrlzStructNode<
                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                  T, XS,
                  WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                  WkResultSrlzPacketOperationData<T>,
                  WkDynamicCollectionSrlzStructNode<
                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                  ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                WkSzInputBytestreamBase<?>,
                YS,
                WkDynamicCollectionSrlzStructNode<
                  T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                  T, YS,
                  WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                  WkResultSrlzPacketOperationData<T>,
                  WkDynamicCollectionSrlzStructNode<
                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                  ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                WkSzOutputBytestreamBase<?>,
                WkDynamicCollectionSrlzStructNode<
                T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>
  newStruct(
    String dynamicCollectionLabel,
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ET, EXS, EXD, EXO, WkSzInputBytestreamBase<?>, EYS, EYD, EYO, WkSzOutputBytestreamBase<?>, ED>
      elementDefinitionFactory,
    WkOperationSettingsFactory<WkVariableSizeCollectionSrlzInputNode<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkVariableSizeCollectionSrlzOutputNode<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                  dynamicCollectionLabel,
                  (pc) -> WkDynamicCollectionSrlzStructNode.newCore(
                      minSize,
                      maxSize,
                      sizeFieldLabel,
                      sizeDeserializerSettingsFactory,
                      sizeSerializerSettingsFactory,
                      sizeValueFactory,
                      sizeDefinitionFactory,
                      collectionAndElementsFieldLabel,
                      collectionAndElementsDeserializerSettingsFactory,
                      collectionAndElementsSerializerSettingsFactory,
                      elementLabel,
                      elementDefinitionFactory,
                      elementDeserializerSettingsFactory,
                      elementSerializerSettingsFactory,
                      collectionFactory,
                      collectionClass,
                      pc),
                  WkSzCountingInputBytestream::new,
                  WkSzCountingOutputBytestream::new);
  }

  public static <T extends Collection<ET>,
                 XS extends WkSettingsSrlzPacketOperationData,
                 YS extends WkSettingsSrlzPacketOperationData,
                 ZT extends Number,
                 ZXS extends WkSettingsSrlzPacketOperationData,
                 ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,ZXS,?,?,ZXD>,
                 ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                 ZYS extends WkSettingsSrlzPacketOperationData,
                 ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,ZYS,?,?,ZYD>,
                 ZYD extends WkSerdeDTreeNumberDefinition<ZT>,
                 ZD extends WkSerdeDTreeNumberDefinition<ZT>,
                 ET,
                 EXS extends WkSettingsSrlzPacketOperationData,
                 EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                 EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSettingsSrlzPacketOperationData,
                 EYD extends WkSerdeDTreeNodeStructDefinition<ET>,
                 EYO extends WkSerdeDTreeNodeDataWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDTreeNodeStructDefinition<ET>,
                 VXS extends WkSzVariableLengthOperationSettings,
                 VYS extends WkSettingsSrlzPacketOperationData>
  WkSrlzStructDefinitionFrameNodeCore<
                 T, XS,?,?,
                 WkDynamicCollectionSrlzStructNode<
                   T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                 WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                   T, XS,
                   WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                   WkResultSrlzPacketOperationData<T>,
                   WkDynamicCollectionSrlzStructNode<
                     T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                   ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                 WkSzInputBytestreamBase<?>,
                 YS,?,?,
                 WkDynamicCollectionSrlzStructNode<
                   T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                 WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                   T, YS,
                   WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                   WkResultSrlzPacketOperationData<T>,
                   WkDynamicCollectionSrlzStructNode<
                     T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                   ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                 WkSzOutputBytestreamBase<?>,
                 WkDynamicCollectionSrlzStructNode<
                   T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                   ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>,?>
  newCore(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ET, EXS, EXD, EXO, WkSzInputBytestreamBase<?>, EYS, EYD, EYO, WkSzOutputBytestreamBase<?>, ED>
      elementDefinitionFactory,
    WkOperationSettingsFactory<WkVariableSizeCollectionSrlzInputNode<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkVariableSizeCollectionSrlzOutputNode<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass,
    WkSrlzStructComponentFrameNodeCore<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> componentCore) {
    return new WkDynamicCollectionSrlzStructNode<
                 T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                 ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>(
                     minSize,
                     maxSize,
                     sizeFieldLabel,
                     sizeDeserializerSettingsFactory,
                     sizeSerializerSettingsFactory,
                     sizeValueFactory,
                     sizeDefinitionFactory,
                     collectionAndElementsFieldLabel,
                     collectionAndElementsDeserializerSettingsFactory,
                     collectionAndElementsSerializerSettingsFactory,
                     elementLabel,
                     elementDefinitionFactory,
                     elementDeserializerSettingsFactory,
                     elementSerializerSettingsFactory,
                     collectionFactory,
                     collectionClass,
                     componentCore).definitionCore;
  }

  private final WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                        T, XS,
                        WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                          T, XS,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                          WkResultSrlzPacketOperationData<T>,
                          WkDynamicCollectionSrlzStructNode<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        WkDynamicCollectionSrlzStructNode<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        YS,
                        WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                          T, YS,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                          WkResultSrlzPacketOperationData<T>,
                          WkDynamicCollectionSrlzStructNode<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        WkDynamicCollectionSrlzStructNode<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                        ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS,
                        WkDynamicCollectionSrlzStructNode<
                          T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                          ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>
                    definitionCore;

  private WkDynamicCollectionSrlzStructNode(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ET, EXS, EXD, EXO, WkSzInputBytestreamBase<?>, EYS, EYD, EYO, WkSzOutputBytestreamBase<?>, ED>
      elementDefinitionFactory,
    WkOperationSettingsFactory<WkVariableSizeCollectionSrlzInputNode<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkVariableSizeCollectionSrlzOutputNode<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass,
    WkSrlzStructComponentFrameNodeCore<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> componentCore) {
    this.definitionCore = new WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                                  T, XS,
                                  WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                                    T, XS,
                                    WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                    WkResultSrlzPacketOperationData<T>,
                                    WkDynamicCollectionSrlzStructNode<
                                      T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                    ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                                  WkDynamicCollectionSrlzStructNode<
                                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                  YS,
                                  WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                                    T, YS,
                                    WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                    WkResultSrlzPacketOperationData<T>,
                                    WkDynamicCollectionSrlzStructNode<
                                      T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                    ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                                  WkDynamicCollectionSrlzStructNode<
                                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS>,
                                  ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                                  ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS,
                                  WkDynamicCollectionSrlzStructNode<
                                    T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                                    ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>(
                                        minSize,
                                        maxSize,
                                        sizeFieldLabel,
                                        sizeDeserializerSettingsFactory,
                                        sizeSerializerSettingsFactory,
                                        sizeValueFactory,
                                        sizeDefinitionFactory,
                                        collectionAndElementsFieldLabel,
                                        collectionAndElementsDeserializerSettingsFactory,
                                        collectionAndElementsSerializerSettingsFactory,
                                        elementLabel,
                                        elementDefinitionFactory,
                                        elementDeserializerSettingsFactory,
                                        elementSerializerSettingsFactory,
                                        //BasicReadingRuntime::new,
                                        //BasicReadingResult::new,
                                        (i,xs,axb,xkc,xdc) -> new WkDynamicCollectionSrlzInputNode<>(i,xs,axb,xkc,xdc).operationCore,
                                        //BasicWritingRuntime::new,
                                        //BasicWritingResult::empty,
                                        (i,y,ys,ayb,ykc,ydc) -> new WkDynamicCollectionSrlzOutputNode<>(i,y,ys,ayb,ykc,ydc).operationCore,
                                        collectionFactory,
                                        collectionClass,
                                        componentCore,
                                        this);
  }

  @Override
  public
  WkSerdeDTreeNodeStructComponentHandler<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZD>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSerdeDTreeNodeStructComponentHandler<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, WkVariableSizeCollectionSrlzStructNode<T, VXS, VYS, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED>>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public Class<T> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
