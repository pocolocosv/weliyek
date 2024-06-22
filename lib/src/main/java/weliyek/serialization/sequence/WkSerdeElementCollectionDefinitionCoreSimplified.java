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
import java.util.function.ToIntFunction;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationResultBasic;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;

public class WkSerdeElementCollectionDefinitionCoreSimplified<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeElementCollectionDefinition<T,XO,?,ET,?>,
                        XO extends WkSerdeElementCollectionReader<
                                        T,
                                        XS,
                                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        XD,
                                        ET,EXD,EXO>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeElementCollectionDefinition<T,?,YO,ET,?>,
                        YO extends WkSerdeElementCollectionWriter<
                                        T,
                                        YS,
                                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        YD,
                                        ET,EYD,EYO>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDtreeStructDefinition<ET>,
                        D extends WkSerdeElementCollectionDefinition<T,XO,YO,ET,ED>>
    extends WkSerdeElementCollectionDefinitionCore<
                        T,
                        XS,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        XD,
                        WkSerdeElementCollectionDefinitionCoreSimplified<
                          T,XS,XD,XO,?,?,?,ET,EXS,EXD,EXO,?,?,?,? extends EXD,? extends XD>,
                        XO,
                        WkSerdeElementCollectionReaderCoreSimplified<
                          T,XS,XD,XO,ET,EXS,EXD,EXO>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        YS,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YD,
                        WkSerdeElementCollectionDefinitionCoreSimplified<
                          T,?,?,?,YS,YD,YO, ET,?,?,?,EYS,EYD,EYO,? extends EYD,? extends YD>,
                        YO,
                        WkSerdeElementCollectionWriterCoreSimplified<
                          T,YS,YD,YO,ET,EYS,EYD,EYO>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        ET,
                        EXS,
                        EXD,
                        EXO,
                        EYS,
                        EYD,
                        EYO,
                        ED,
                        D,
                        WkSerdeElementCollectionDefinitionCoreSimplified<
                          T,XS,XD,XO,YS,YD,YO,
                          ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D>>
{

  protected WkSerdeElementCollectionDefinitionCoreSimplified(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<
      XS,
      WkSerdeElementCollectionDefinitionCoreSimplified<
        T,XS,XD,XO,?,?,?,ET,EXS,EXD,EXO,?,?,?,? extends EXD,? extends XD>,
      WkSerdeElementCollectionReaderCoreSimplified<
        T,XS,XD,XO,ET,EXS,EXD,EXO>,
      WkSerdeDtreeBytestreamInputBase<?>> readingOpFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<
      T,YS,
      WkSerdeElementCollectionDefinitionCoreSimplified<
        T,?,?,?,YS,YD,YO, ET,?,?,?,EYS,EYD,EYO,? extends EYD,? extends YD>,
      WkSerdeElementCollectionWriterCoreSimplified<
        T,YS,YD,YO,ET,EYS,EYD,EYO>,
      WkSerdeDtreeBytestreamOutputBase<?>> writingOpFactory,
    String elementsLabel,
    ToIntFunction<? super XO> elementsDeserializingNumOfOps,
    WkOperationSettingsFactory<XO, EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<YO, EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXO, WkSerdeDtreeBytestreamInputBase<?>,
      EYS,EYO, WkSerdeDtreeBytestreamOutputBase<?>, ED> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          readingOpFactory,
          WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          writingOpFactory,
          elementsLabel,
          elementsDeserializingNumOfOps,
          elementsRxSettingsFactory,
          elementsTxSettingsFactory,
          (k,yo,i) -> yo.serializableAsList().get(i),
          elementsDefinitionFactory,
          (xo) -> collectionFactory.apply(xo.elements().get().collectAllOperationValues()),
          definitionBody,
          serializableClass);
  }

  @Override
  protected WkSerdeElementCollectionDefinitionCoreSimplified<T,XS,XD,XO,YS,YD,YO,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D> getThis() {
    return this;
  }

}
