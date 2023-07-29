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
package weliyek.amat.basic.aggregator.sequence;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.SubcomponentHandler;
import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.output.Disaggregator;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingField;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.aggregator.AggregatorDefinitionCore;
import weliyek.amat.basic.aggregator.SubcomponentHandlerCore;
import weliyek.amat.basic.sequence.CollectionAndElementsFieldDeserializer;
import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;

public abstract class CollectionAndElementsFieldDefinitionCore<
                        T extends Collection<ET>,
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQC extends SequenceReadingRuntimeControl<XB,XBC,?>,
                        XR extends DeserializingResult<T>,
                        XD extends CollectionAndElementsFieldDefinition<T,XO,?,ET,?>,
                        XO extends CollectionAndElementsFieldDeserializer<
                                        T,XS,? extends DeserializingRuntime<XB>,XR,XD,ET,EXD,EXO>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,?>,
                        YR extends SerializingResult,
                        YD extends CollectionAndElementsFieldDefinition<T,?,YO,ET,?>,
                        YO extends CollectionAndElementsFieldSerializer<
                                        T,YS,? extends SerializingRuntime<YB>,YR,YD,ET,EYD,EYO>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends DefinitionSegment<ET,?>,
                        EXO extends DeserializingOperation<ET,EXS,?,?,EXD>,
                        EYS extends OperationSettings,
                        EYD extends DefinitionSegment<ET,?>,
                        EYO extends SerializingOperation<ET,EYS,?,?,EYD>,
                        ED extends DefinitionSegment<ET,?>,
                        D extends CollectionAndElementsFieldDefinition<T,XO,YO,ET,ED>,
                        DC extends CollectionAndElementsFieldDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XO,AXB,
                                        YS,YB,YBC,YQC,YR,YD,YO,AYB,
                                        ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D,?>>
    extends AggregatorDefinitionCore<
                        T, XS, XB, XBC, XQC, XR, XD, XO, AXB,
                        YS, YB, YBC, YQC, YR, YD, YO, AYB, D, DC>
    implements CollectionAndElementsFieldDefinition<T, XO, YO, ET, ED>
{

  final SubcomponentHandlerCore<ET,EXS,EXD,EXO,T,XBC,XD,XO,EYS,EYD,EYO,YBC,YD,YO,ED,D>
                        elementComponent;
  final Function<XO, T> collectionSerializingFactory;

  protected CollectionAndElementsFieldDefinitionCore(
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB, XQC> rxRuntimeFactory,
    BiFunction<XO, T, XR> rxResultFactory,
    PacketInputFieldReadingFactory<T, XS, XD, DC, XO, AXB> readingOpFactory,
    Function<AYB, YQC> txRuntimeFactory,
    Function<YO, YR> txResultFactory,
    PacketOutputFieldWritingFactory<T, YS, YD, DC, YO, AYB> writingOpFactory,
    String elementsLabel,
    ToIntFunction<? super XO> elementsDeserializingNumOfOps,
    OperationSubsegmentSettingsFactory<XO, EXS> elementsRxSettingsFactory,
    OperationSubsegmentSettingsFactory<YO, EYS> elementsTxSettingsFactory,
    Disaggregator<ET,EYD,T,YO> elementsDisaggregator,
    ProtocolDefinitionFactory<ET,EXS,EXD,EXO,XBC,EYS,EYD,EYO,YBC,ED> elementsDefinitionFactory,
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
                                      CollectionAndElementsFieldDefinitionCore::disaggregateCollection,
                                      false,
                                      elementsDefinitionFactory);
  }

  private static <T extends Collection<ET>,
                  YO extends CollectionAndElementsFieldSerializer<T,?,?,?,?,ET,EYD,?>,
                  ET,
                  EYD extends DefinitionSegment<ET,?>>
  ET disaggregateCollection(
    SerializingField<ET,EYD,?> serializingField,
    YO collectionWritingOp,
    int index) {
    if (collectionWritingOp.serializableAsList().size() <= index) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return collectionWritingOp.serializableAsList().get(index);
  }

  @Override
  public SubcomponentHandler<XO, YO, ED> element() {
    return this.elementComponent.body();
  }

}
