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
import java.util.function.Predicate;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.filter.WkSrlzPacketNodePredicate;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;

public class WkDynamicCollectionSrlzStructNode<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        YS extends WkSettingsSrlzPacketOperationData,
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
                        VYS extends WkSettingsSrlzPacketOperationData>
    implements WkDynamicCollectionSrlzStructDefinitionFrameNode<
                        T,
                        WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                          T, XS,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                          WkDecodingResultSrlzPacketOperationData<T>,
                          WkDynamicCollectionSrlzStructNode<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                        WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                          T, YS,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                          WkEncodingResultSrlzPacketOperationData,
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
                 VYS extends WkSettingsSrlzPacketOperationData>
  WkSrlzStruct<
                T, XS,
                WkDynamicCollectionSrlzStructNode<
                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                  T, XS,
                  WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                  WkDecodingResultSrlzPacketOperationData<T>,
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
                  WkEncodingResultSrlzPacketOperationData,
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
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
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
                 VYS extends WkSettingsSrlzPacketOperationData>
  WkSrlzStructDefinitionFrameNodeCore<
                 T, XS,?,?,
                 WkDynamicCollectionSrlzStructNode<
                   T,XS,?,ZT,ZXS,ZXO,ZXD,?,?, ?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                 WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                   T, XS,
                   WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                   WkDecodingResultSrlzPacketOperationData<T>,
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
                   WkEncodingResultSrlzPacketOperationData,
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
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
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
                          WkDecodingResultSrlzPacketOperationData<T>,
                          WkDynamicCollectionSrlzStructNode<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                          ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        WkDynamicCollectionSrlzStructNode<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                        YS,
                        WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                          T, YS,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                          WkEncodingResultSrlzPacketOperationData,
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
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZT, ZXS, ZXD, ZXO, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, ZYS, ZYD, ZYO, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, ZD>
      sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
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
                                    WkDecodingResultSrlzPacketOperationData<T>,
                                    WkDynamicCollectionSrlzStructNode<
                                      T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                    ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                                  WkDynamicCollectionSrlzStructNode<
                                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,VXS,?>,
                                  YS,
                                  WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                                    T, YS,
                                    WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                    WkEncodingResultSrlzPacketOperationData,
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
  WkSrlzStructSubcomponentFrameNode<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZD>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T, YS, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, WkVariableSizeCollectionSrlzStructNode<T, VXS, VYS, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED>>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public Class<T> rxClass() {
    return this.definitionCore.rxClass();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public WkSrlzPacketNodePredicate<?, ?> makeTester(
  Predicate<? super WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T, XS, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<T>, WkDynamicCollectionSrlzStructNode<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>> test,
  String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
