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
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzPacketWriteDisaggregator;

public abstract class WkSerdeElementCollectionDefinitionCore<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<XB,XBC,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeElementCollectionDefinition<T,XO,?,ET,?>,
                        XDC extends WkSerdeElementCollectionDefinitionCore<
                                      T,XS,XB,XBC,XQC,XR,XD,?,XO,XOC,AXB,
                                      ?,?,?,?,?,?,?,?,?,?,
                                      ET,EXS,EXD,EXO,
                                      ?,?,?,? extends EXD,? extends XD,?>,
                        XO extends WkSerdeElementCollectionReader<
                                        T,XS,? extends WkSerdeDtreeOperationInputRuntime<XB>,XR,XD,ET,EXD,EXO>,
                        XOC extends WkSerdeElementCollectionReaderCore<
                                      T,XS,XB,XBC,?,XQC,XR,XD,XDC,XO,?,AXB,ET,EXS,EXD,EXO>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<YB,YBC,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeElementCollectionDefinition<T,?,YO,ET,?>,
                        YDC extends WkSerdeElementCollectionDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,?,YO,YOC,AYB,
                                        ET,?,?,?,EYS,EYD,EYO,? extends EYD,? extends YD,?>,
                        YO extends WkSerdeElementCollectionWriter<
                                        T,YS,? extends WkSerdeDtreeOperationOutputRuntime<YB>,YR,YD,ET,EYD,EYO>,
                        YOC extends WkSerdeElementCollectionWriterCore<
                                        T,YS,YB,YBC,?,YQC,YR,YD,YDC,YO,?,AYB,ET,EYS,EYD,EYO>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDtreeStructDefinition<ET>,
                        D extends WkSerdeElementCollectionDefinition<T,XO,YO,ET,ED>,
                        DC extends WkSerdeElementCollectionDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XDC,XO,XOC,AXB,
                                        YS,YB,YBC,YQC,YR,YD,YDC,YO,YOC,AYB,
                                        ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D,?>>
    extends WkSerdeDtreeAggregatorStructDefinitionCore<
                        T, XS, XB, XBC, XQC, XR, XD, XDC, XO, XOC, AXB,
                        YS, YB, YBC, YQC, YR, YD, YDC, YO, YOC, AYB, D, DC>
    implements WkSerdeElementCollectionDefinition<T, XO, YO, ET, ED>
{

  final WkSerdeDtreeStructSubfieldCore<ET,T,EXS,EXD,EXO,XBC,XO,EYS,EYD,EYO,YBC,YO,ED>
                        elementComponent;
  final Function<XO, T> collectionSerializingFactory;

  protected WkSerdeElementCollectionDefinitionCore(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    Function<AXB, XQC> rxRuntimeFactory,
    BiFunction<XO, T, XR> rxResultFactory,
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<XS,XDC,XOC,AXB> readingOpFactory,
    Function<AYB, YQC> txRuntimeFactory,
    BiFunction<YO, T, YR> txResultFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<T,YS,YDC,YOC,AYB> writingOpFactory,
    String elementsLabel,
    ToIntFunction<? super XO> elementsDeserializingNumOfOps,
    WkOperationSettingsFactory<XO, EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<YO, EYS> elementsTxSettingsFactory,
    WkSzPacketWriteDisaggregator<ET,EYD,T,YO> elementsDisaggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ET,EXS,EXO,XBC,EYS,EYO,YBC,ED> elementsDefinitionFactory,
    Function<XO,T> collectionSerializingFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          definitionBody,
          serializableClass);
    this.collectionSerializingFactory = Objects.requireNonNull(collectionSerializingFactory);
    this.elementComponent = this.<ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>addSubcomponent(
                                      elementsLabel,
                                      Optional.empty(),
                                      elementsDeserializingNumOfOps,
                                      elementsRxSettingsFactory,
                                      Optional.empty(),
                                      (yo) -> yo.serializable().size(),
                                      elementsTxSettingsFactory,
                                      WkSerdeElementCollectionDefinitionCore::disaggregateCollection,
                                      false,
                                      elementsDefinitionFactory);
  }

  private static <T extends Collection<ET>,
                  YO extends WkSerdeElementCollectionWriter<T,?,?,?,?,ET,EYD,?>,
                  ET,
                  EYD extends WkSerdeDtreeStructDefinition<ET>>
  ET disaggregateCollection(
    WkSerdeDtreeMsgOutputField<ET,EYD,?> serializingField,
    YO collectionWritingOp,
    int index) {
    if (collectionWritingOp.serializableAsList().size() <= index) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return collectionWritingOp.serializableAsList().get(index);
  }

  @Override
  public WkSerdeDtreeStructSubfield<XO, YO, ED> elements() {
    return this.elementComponent.asProtocolField();
  }

}
