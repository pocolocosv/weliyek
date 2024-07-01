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
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;

public class WkSerdeDynamicCollection<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        YS extends WkSerdeDtreeOperationSettings,
                        ZT extends Number,
                        ZXS extends WkSerdeDtreeOperationSettings,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZYS extends WkSerdeDtreeOperationSettings,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDtreeStructDefinition<ET>,
                        VXS extends WkSerdeDtreeOperationSettingsVariableLength,
                        VYS extends WkSerdeDtreeOperationSettings>
    implements WkSerdeDtreeDynamicCollectionDefinition<
                        T,
                        WkSerdeDtreeDynamicCollectionReader<
                          T, XS,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationResult<T>,
                          WkSerdeDynamicCollection<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                          ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                        WkSerdeDtreeDynamicCollectionWriter<
                          T, YS,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationResult<T>,
                          WkSerdeDynamicCollection<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS>
{

  public static <T extends Collection<ET>,
                 XS extends WkSerdeDtreeOperationSettings,
                 YS extends WkSerdeDtreeOperationSettings,
                 ZT extends Number,
                 ZXS extends WkSerdeDtreeOperationSettings,
                 ZXO extends WkSerdeDtreeNumberMsgReader<ZT,ZXS,?,?,ZXD>,
                 ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZYS extends WkSerdeDtreeOperationSettings,
                 ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,ZYS,?,?,ZYD>,
                 ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ET,
                 EXS extends WkSerdeDtreeOperationSettings,
                 EXD extends WkSerdeDtreeStructDefinition<ET>,
                 EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSerdeDtreeOperationSettings,
                 EYD extends WkSerdeDtreeStructDefinition<ET>,
                 EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDtreeStructDefinition<ET>,
                 VXS extends WkSerdeDtreeOperationSettingsVariableLength,
                 VYS extends WkSerdeDtreeOperationSettings>
  WkSerdeDtreeStruct<
                T, XS,
                WkSerdeDynamicCollection<
                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                WkSerdeDtreeDynamicCollectionReader<
                  T, XS,
                  WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                  WkSerdeDtreeOperationResult<T>,
                  WkSerdeDynamicCollection<
                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                  ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                WkSerdeDtreeBytestreamInputBase<?>,
                YS,
                WkSerdeDynamicCollection<
                  T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                WkSerdeDtreeDynamicCollectionWriter<
                  T, YS,
                  WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                  WkSerdeDtreeOperationResult<T>,
                  WkSerdeDynamicCollection<
                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                  ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                WkSerdeDtreeBytestreamOutputBase<?>,
                WkSerdeDynamicCollection<
                T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>
  newStruct(
    String dynamicCollectionLabel,
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionReader< T, XS, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionWriter<T, YS, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ? extends ZYD, ET, ?, ?, ?, EYS, EYD, EYO, ? extends EYD, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT,ZXS,ZXO,WkSerdeDtreeBytestreamInputBase<?>,
      ZYS,ZYO,WkSerdeDtreeBytestreamOutputBase<?>,ZD> sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionReader<T, XS, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ? extends ZXD, ET, EXS, EXD, EXO, ?, ?, ?, ? extends EXD, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionWriter<T, YS, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ? extends ZYD, ET, ?, ?, ?, EYS, EYD, EYO, ? extends EYD, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXO,WkSerdeDtreeBytestreamInputBase<?>,
      EYS,EYO,WkSerdeDtreeBytestreamOutputBase<?>, ED> elementDefinitionFactory,
    WkOperationSettingsFactory<WkSerdeVariableSizeElementCollectionReader<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeVariableSizeElementCollectionWriter<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass) {
    return new WkSerdeDtreeStructCore<
                      T, XS,
                      WkSerdeDynamicCollection<
                        T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                      WkSerdeDtreeDynamicCollectionReader<
                        T, XS,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<T>,
                        WkSerdeDynamicCollection<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                        ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      YS,
                      WkSerdeDynamicCollection<
                        T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                      WkSerdeDtreeDynamicCollectionWriter<
                        T, YS,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<T>,
                        WkSerdeDynamicCollection<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                        ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeDynamicCollection<
                      T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                      ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>(
                  dynamicCollectionLabel,
                  (pc) -> WkSerdeDynamicCollection.newCore(
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
                  WkSerdeDtreeBytestreamCountingInputStream::new,
                  WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static <T extends Collection<ET>,
                 XS extends WkSerdeDtreeOperationSettings,
                 YS extends WkSerdeDtreeOperationSettings,
                 ZT extends Number,
                 ZXS extends WkSerdeDtreeOperationSettings,
                 ZXO extends WkSerdeDtreeNumberMsgReader<ZT,ZXS,?,?,ZXD>,
                 ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZYS extends WkSerdeDtreeOperationSettings,
                 ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,ZYS,?,?,ZYD>,
                 ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ET,
                 EXS extends WkSerdeDtreeOperationSettings,
                 EXD extends WkSerdeDtreeStructDefinition<ET>,
                 EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                 EYS extends WkSerdeDtreeOperationSettings,
                 EYD extends WkSerdeDtreeStructDefinition<ET>,
                 EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                 ED extends WkSerdeDtreeStructDefinition<ET>,
                 VXS extends WkSerdeDtreeOperationSettingsVariableLength,
                 VYS extends WkSerdeDtreeOperationSettings>
      WkSerdeDtreeDynamicCollectionDefinitionCore<
                  T, XS,
                  WkSerdeDtreeDynamicCollectionReader<
                    T, XS,
                    WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                    WkSerdeDtreeOperationResult<T>,
                    WkSerdeDynamicCollection<
                      T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                    ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                  WkSerdeDynamicCollection<
                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                  YS,
                  WkSerdeDtreeDynamicCollectionWriter<
                    T, YS,
                    WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                    WkSerdeDtreeOperationResult<T>,
                    WkSerdeDynamicCollection<
                      T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                    ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                  WkSerdeDynamicCollection<
                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                  ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                  ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS,
                  WkSerdeDynamicCollection<
                    T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                    ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>
  newCore(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionReader< T, XS, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionWriter<T, YS, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ? extends ZYD, ET, ?, ?, ?, EYS, EYD, EYO, ? extends EYD, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT,ZXS,ZXO,WkSerdeDtreeBytestreamInputBase<?>,
      ZYS,ZYO,WkSerdeDtreeBytestreamOutputBase<?>,ZD> sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionReader<T, XS, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ? extends ZXD, ET, EXS, EXD, EXO, ?, ?, ?, ? extends EXD, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionWriter<T, YS, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ? extends ZYD, ET, ?, ?, ?, EYS, EYD, EYO, ? extends EYD, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXO,WkSerdeDtreeBytestreamInputBase<?>,
      EYS,EYO,WkSerdeDtreeBytestreamOutputBase<?>, ED> elementDefinitionFactory,
    WkOperationSettingsFactory<WkSerdeVariableSizeElementCollectionReader<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeVariableSizeElementCollectionWriter<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeDynamicCollection<
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

  private final WkSerdeDtreeDynamicCollectionDefinitionCore<
                        T, XS,
                        WkSerdeDtreeDynamicCollectionReader<
                          T, XS,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationResult<T>,
                          WkSerdeDynamicCollection<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                          ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        WkSerdeDynamicCollection<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                        YS,
                        WkSerdeDtreeDynamicCollectionWriter<
                          T, YS,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationResult<T>,
                          WkSerdeDynamicCollection<
                            T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                          ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                        WkSerdeDynamicCollection<
                          T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                        ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                        ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS,
                        WkSerdeDynamicCollection<
                          T,XS,YS,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                          ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS>>
                    definitionCore;

  private WkSerdeDynamicCollection(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionReader< T, XS, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, ZXS>
      sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionWriter<T, YS, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ? extends ZYD, ET, ?, ?, ?, EYS, EYD, EYO, ? extends EYD, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZYS>
      sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT,ZXS,ZXO,WkSerdeDtreeBytestreamInputBase<?>,
      ZYS,ZYO,WkSerdeDtreeBytestreamOutputBase<?>,ZD> sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionReader<T, XS, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ? extends ZXD, ET, EXS, EXD, EXO, ?, ?, ?, ? extends EXD, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, VXS>
      collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeDtreeDynamicCollectionWriter<T, YS, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ? extends ZYD, ET, ?, ?, ?, EYS, EYD, EYO, ? extends EYD, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, VYS>
      collectionAndElementsSerializerSettingsFactory,
    String elementLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXO,WkSerdeDtreeBytestreamInputBase<?>,
      EYS,EYO,WkSerdeDtreeBytestreamOutputBase<?>, ED> elementDefinitionFactory,
    WkOperationSettingsFactory<WkSerdeVariableSizeElementCollectionReader<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeVariableSizeElementCollectionWriter<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> collectionClass,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeDynamicCollectionDefinitionCore<
                                  T, XS,
                                  WkSerdeDtreeDynamicCollectionReader<
                                    T, XS,
                                    WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                    WkSerdeDtreeOperationResult<T>,
                                    WkSerdeDynamicCollection<
                                      T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                                    ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                                  WkSerdeDynamicCollection<
                                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                                  YS,
                                  WkSerdeDtreeDynamicCollectionWriter<
                                    T, YS,
                                    WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                    WkSerdeDtreeOperationResult<T>,
                                    WkSerdeDynamicCollection<
                                      T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                                    ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>,
                                  WkSerdeDynamicCollection<
                                    T,?,YS,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                                  ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                                  ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS,
                                  WkSerdeDynamicCollection<
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
                                        (i,xkc,xdc) -> new WkSerdeDynamicCollectionReader<T,XS,ZT,ZXS,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>(i,xkc,xdc).operationCore,
                                        (i,ykc,ydc) -> new WkSerdeDynamicCollectionWriter<T,YS,ZT,ZYS,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>(i,ykc,ydc).operationCore,
                                        collectionFactory,
                                        collectionClass,
                                        componentCore,
                                        this);
  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeDtreeDynamicCollectionReader<T, XS, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ? extends ZXD, ET, EXS, EXD, EXO, ?, ?, ?, ? extends EXD, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, WkSerdeDtreeDynamicCollectionWriter<T, YS, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ? extends ZYD, ET, ?, ?, ?, EYS, EYD, EYO, ? extends EYD, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, ZD>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeDtreeDynamicCollectionReader<T, XS, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ? extends ZXD, ET, EXS, EXD, EXO, ?, ?, ?, ? extends EXD, VXS, ?>, ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>, WkSerdeDtreeDynamicCollectionWriter<T, YS, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<T>, WkSerdeDynamicCollection<T, ?, YS, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ? extends ZYD, ET, ?, ?, ?, EYS, EYD, EYO, ? extends EYD, ?, VYS>, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>, WkSerdeVariableSizeElementCollection<T, VXS, VYS, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED>>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public Class<T> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
