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
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
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
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.util.array.WkSerdeDtreeDynamicSequenceDefinitionCore;

public final class WkSerdeDtreeDynamicCollectionDefinitionCore<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XO extends WkSerdeDtreeDynamicCollectionReader<
                                        T,XS,
                                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        XD,ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD extends WkSerdeDtreeDynamicCollectionDefinition<
                                        T,XO,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YO extends WkSerdeDtreeDynamicCollectionWriter<
                                        T,YS,
                                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        YD,ZT,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD extends WkSerdeDtreeDynamicCollectionDefinition<
                                        T,?,YO,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
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
                        VYS extends WkSerdeDtreeOperationSettings,
                        D extends WkSerdeDtreeDynamicCollectionDefinition<
                                      T, XO, YO, ZD, ET, EXS, EXD, EXO,
                                      EYS, EYD, EYO, ED, VXS, VYS>>
    extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                        T, XS,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        XO,
                        WkSerdeDtreeDynamicCollectionReaderCore<
                          T,XS,XO,XD,ZT,ZXS,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD,
                        WkSerdeDtreeDynamicCollectionDefinitionCore<
                          T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?,? extends XD>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        YS,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YO,
                        WkSerdeDtreeDynamicCollectionWriterCore<
                          T,YS,YO,YD,ZT,ZYS,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD,
                        WkSerdeDtreeDynamicCollectionDefinitionCore<
                          T,?,?,?,YS,YO,YD,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS,? extends YD>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD,
                        VXS,
                        WkSerdeVariableSizeElementCollectionReader<T,VXS,ET,EXS,EXD,EXO>, // VXO
                        WkSerdeVariableSizeElementCollection<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>, // VXD
                        VYS,
                        WkSerdeVariableSizeElementCollectionWriter<T,VYS,ET,EYS,EYD,EYO>, // VYO
                        WkSerdeVariableSizeElementCollection<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>, // VYD
                        WkSerdeVariableSizeElementCollection<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>,
                        D,
                        WkSerdeDtreeDynamicCollectionDefinitionCore<T,XS,XO,XD,YS,YO,YD,ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,VXS,VYS,D>>
    implements WkSerdeDtreeDynamicCollectionDefinition<
                        T, XO, YO, ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS>
{

  public WkSerdeDtreeDynamicCollectionDefinitionCore(
    int minSize,
    int maxSize,
    String sizeFieldLabel,
    WkOperationSettingsFactory<XO, ZXS> sizeDeserializerSettingsFactory,
    WkOperationSettingsFactory<YO, ZYS> sizeSerializerSettingsFactory,
    IntFunction<ZT> sizeValueFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<ZT,ZXS,?,?,ZXD,?,ZXO,?,WkSerdeDtreeBytestreamInputBase<?>,ZYS,?,?,ZYD,?,ZYO,?,WkSerdeDtreeBytestreamOutputBase<?>,ZD,?>>
          sizeDefinitionFactory,
    String collectionAndElementsFieldLabel,
    WkOperationSettingsFactory<XO,VXS> collectionAndElementsDeserializerSettingsFactory,
    WkOperationSettingsFactory<YO,VYS> collectionAndElementsSerializerSettingsFactory,
    String elementFieldLabel,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<
                  ET,EXS,?,?,EXD,?,EXO,?,WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                  EYS,?,?,EYD,?,EYO,?,WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                  ED,?>> elementsDefinitionFactory,
    WkOperationSettingsFactory<WkSerdeVariableSizeElementCollectionReader<T, VXS, ET, EXS, EXD, EXO>, EXS>
      elementDeserializerSettingsFactory,
    WkOperationSettingsFactory<WkSerdeVariableSizeElementCollectionWriter<T, VYS, ET, EYS, EYD, EYO>, EYS>
      elementSerializerSettingsFactory,
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<
      XS,
      WkSerdeDtreeDynamicCollectionDefinitionCore<T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?,? extends XD>,
      WkSerdeDtreeDynamicCollectionReaderCore<T,XS,XO,XD,ZT,ZXS,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
      WkSerdeDtreeBytestreamInputBase<?>> deserializerFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<
      T,
      YS,
      WkSerdeDtreeDynamicCollectionDefinitionCore<T,?,?,?,YS,YO,YD,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS,? extends YD>,
      WkSerdeDtreeDynamicCollectionWriterCore<T,YS,YO,YD,ZT,ZYS,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
      WkSerdeDtreeBytestreamOutputBase<?>> serializerFactory,
    Function<List<ET>, T> collectionFactory,
    Class<T> serializableClass,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
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
          (pc) -> WkSerdeVariableSizeElementCollection.
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
          WkSerdeDtreeOperationInputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          deserializerFactory,
          WkSerdeDtreeOperationOutputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          serializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected
  WkSerdeDtreeDynamicCollectionDefinitionCore<T, XS, XO, XD, YS, YO, YD, ZT, ZXS, ZXO, ZXD, ZYS, ZYO, ZYD, ZD, ET, EXS, EXD, EXO, EYS, EYD, EYO, ED, VXS, VYS, D>
  getThis() {
    return this;
  }

}
