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
package weliyek.amat.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingOperationCore;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.WritingOperationCore;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat2.protocol.filter.FieldTester;
import weliyek.amat2.protocol.filter.PacketInputFieldOperationPredicate;

public abstract class DefinitionSegmentCore<
                        T,
                        XS extends OperationSettings,
                        XQC extends ReadingRuntimeControl<?,?,?>,
                        XR extends DeserializingResult<T>,
                        XD extends DefinitionSegment<T,?>,
                        XO extends DeserializingOperation<T,XS,?,XR,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YQC extends WritingRuntimeControl<?,?,?>,
                        YR extends SerializingResult,
                        YD extends DefinitionSegment<T,?>,
                        YO extends SerializingOperation<T,YS,?,YR,YD>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        D extends DefinitionSegment<T,?>,
                        DC extends DefinitionSegmentCore<
                                      T,XS,XQC,XR,XD,XO,AXBC,YS,YQC,YR,YD,YO,AYBC,D,?>>
    implements DefinitionSegment<T,XO>
{

    public static final char NAME_PREFIX = '<';
    public static final char NAME_SUFFIX = '>';

    private final ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore;
    private final Function<AXBC, XQC> rxRuntimeFactory;
    private final Function<AYBC, YQC> txRuntimeFactory;
    private final BiFunction<XO, T, XR> rxResultFactory;
    private final Function<YO, YR> txResultFactory;
    public final PacketInputFieldReadingFactory<
                    T,XS,XD,DC,XO,AXBC> readingOpFactory;
    public final PacketOutputFieldWritingFactory<
                    T,YS,YD,DC,YO,AYBC> writingOpFactory;
    private final D definitionBody;
    private final Class<T> serializableClass;
    private final List<Consumer<WritingOperationCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>>> serializerCreationObservers = new ArrayList<>();
    private final List<Consumer<ReadingOperationCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>>> deserializerFullCompletionObservers = new ArrayList<>();
    private final List<Consumer<ReadingOperationCore<T,? extends XS,?,?,?,? extends XO,?,? extends XD,?,?>>> onBeforeDeserializingObservers = new ArrayList<>();

    protected DefinitionSegmentCore(
      ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
      Function<AXBC,XQC> rxRuntimeFactory,
      BiFunction<XO,T,XR> rxResultFactory,
      PacketInputFieldReadingFactory<T,XS,XD,DC,XO,AXBC> readingOpFactory,
      Function<AYBC,YQC> txRuntimeFactory,
      Function<YO,YR> txResultFactory,
      PacketOutputFieldWritingFactory<T,YS,YD,DC,YO,AYBC> writingOpFactory,
      D definitionBody,
      Class<T> serializableClass) {
      this.componentCore = componentCore;
      this.readingOpFactory = Objects.requireNonNull(readingOpFactory);
      this.rxRuntimeFactory = Objects.requireNonNull(rxRuntimeFactory);
      this.rxResultFactory = Objects.requireNonNull(rxResultFactory);
      this.writingOpFactory = Objects.requireNonNull(writingOpFactory);
      this.txRuntimeFactory = Objects.requireNonNull(txRuntimeFactory);
      this.txResultFactory = Objects.requireNonNull(txResultFactory);
      this.definitionBody = Objects.requireNonNull(definitionBody);
      this.serializableClass = Objects.requireNonNull(serializableClass);
    }

    public void initialize() {
      onInitialization();
    }

    protected abstract void onInitialization();

    public void registerOnSerializerCreationObserver(
      Consumer<WritingOperationCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>> onDeserializerCreation) {
      this.serializerCreationObservers.add(onDeserializerCreation);
    }

    public void onSerializerCreation(
      WritingOperationCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?> writingOperationCore) {
      for (Consumer<WritingOperationCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>> onSerializerCreation : serializerCreationObservers) {
        onSerializerCreation.accept(writingOperationCore);
      }
    }

    public void registerOnBeforeFullCompletionDeserialization(
      Consumer<ReadingOperationCore<T,? extends XS,?,?,?,? extends XO,?,? extends  XD,?,?>> onBeforeDeserializing) {
      this.onBeforeDeserializingObservers.add(onBeforeDeserializing);
    }

    public void onBeforeFullCompletionDeserialization(
      ReadingOperationCore<T,? extends XS,?,?,?,? extends XO,?,? extends  XD,?,?> deserializingOp) {
      for (Consumer<ReadingOperationCore<T,? extends XS,?,?,?,? extends XO,?,? extends XD,?,?>> onBeforeFullCompletion : onBeforeDeserializingObservers) {
        onBeforeFullCompletion.accept(deserializingOp);
      }
    }

    public void registerOnAfterFullCompletionDeserialization(
      Consumer<ReadingOperationCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>> onDeserializerFullCompletion) {
      this.deserializerFullCompletionObservers.add(onDeserializerFullCompletion);
    }

    public void onAfterFullCompletionDeserialization(
      ReadingOperationCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?> readingOperationCore) {
      for (Consumer<ReadingOperationCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>> onFullCompletion : deserializerFullCompletionObservers) {
        onFullCompletion.accept(readingOperationCore);
      }
    }

    final public ReadingOperationCore<?,?,?,?,?,XO,?,XD,?,?>
    newReadingOperationCore(
      int index,
      XS settings,
      AXBC parentBytestream,
      DeserializingFieldCore<T,?,XD,?,?,?> fieldCore) {
      return this.readingOpFactory.newReadingCore(
          index,
          settings,
          parentBytestream,
          fieldCore,
          getThis());
    }

    public final WritingOperationCore<?,?,?,?,?,YO,?,YD,?,?>
    newWritingOperationCore(
      int index,
      T serializable,
      YS settings,
      AYBC parentBytestream,
      SerializingFieldCore<T,?,YD,?,?,?> writingFieldCore) {
      return this.writingOpFactory.newWritingCore(
          index, serializable, settings, parentBytestream, writingFieldCore, getThis());
    }

    protected abstract DC getThis();

    public final D definition() {
      return this.definitionBody;
    }

    public ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore() {
      return this.componentCore;
    }

    public String label() {
      return NAME_PREFIX + simpleLabel() + NAME_SUFFIX;
    }

    public String name() {
      return componentCore().name() + label();
    }

    @Override
    public String toString() {
      return name();
    }

    public Function<AXBC, XQC> rxRuntimeFactory() {
      return rxRuntimeFactory;
    }

    public BiFunction<XO, T, XR> rxResultFactory() {
      return rxResultFactory;
    }

    public Function<AYBC, YQC> txRuntimeFactory() {
      return txRuntimeFactory;
    }

    public Function<YO, YR> txResultFactory() {
      return txResultFactory;
    }

    public String simpleLabel() {
      return   rxTargetName()
             + ","
             + txTargetName();
    }

    public String rxSimpleLabel() {
      return NAME_PREFIX + "I:" + rxTargetName() + NAME_SUFFIX;
    }

    protected String rxTargetName() {
      return definition().rxClass().getSimpleName();
    }

    public String txSimpleLabel() {
      return NAME_PREFIX + "O:" + txTargetName() + NAME_SUFFIX;
    }

    protected String txTargetName() {
      return rxTargetName();
    }

    @SuppressWarnings("unchecked")
    @Override
    public FieldTester<?,?> makeTester(Predicate<? super XO> test, String description) {
    return new PacketInputFieldOperationPredicate<XD,XO>((XD) definitionBody, test, description);
    }

    @Override
    public final Class<T> rxClass() {
    return this.serializableClass;
    }

}
