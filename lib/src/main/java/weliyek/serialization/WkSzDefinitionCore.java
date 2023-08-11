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
package weliyek.serialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import weliyek.serialization.filter.FieldTester;
import weliyek.serialization.filter.PacketInputFieldOperationPredicate;

public abstract class WkSzDefinitionCore<
                        T,
                        XS extends WkSzOperationSettings,
                        XQC extends WkSzReadingRuntimeControl<?,?,?>,
                        XR extends WkSzReadingResult<T>,
                        XD extends WkSzDefinition<T,?>,
                        XO extends WkSzPacketReaderOperation<T,XS,?,XR,XD>,
                        AXBC extends WkSzInputBytestreamBase<?>,
                        YS extends WkSzOperationSettings,
                        YQC extends WkSzWritingRuntimeControl<?,?,?>,
                        YR extends WkSzWritingResult,
                        YD extends WkSzDefinition<T,?>,
                        YO extends WkSzPacketWriterOperation<T,YS,?,YR,YD>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        D extends WkSzDefinition<T,?>,
                        DC extends WkSzDefinitionCore<
                                      T,XS,XQC,XR,XD,XO,AXBC,YS,YQC,YR,YD,YO,AYBC,D,?>>
    implements WkSzDefinition<T,XO>
{

    public static final char NAME_PREFIX = '<';
    public static final char NAME_SUFFIX = '>';

    private final WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore;
    private final Function<AXBC, XQC> rxRuntimeFactory;
    private final Function<AYBC, YQC> txRuntimeFactory;
    private final BiFunction<XO, T, XR> rxResultFactory;
    private final Function<YO, YR> txResultFactory;
    public final WkSzPacketReaderOperationCoreFactory<
                    T,XS,XD,DC,XO,AXBC> readingOpFactory;
    public final WkSzPacketWriterOperationCoreFactory<
                    T,YS,YD,DC,YO,AYBC> writingOpFactory;
    private final D definitionBody;
    private final Class<T> serializableClass;
    private final List<Consumer<WkSzPacketWriterOperationCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>>> serializerCreationObservers = new ArrayList<>();
    private final List<Consumer<WkSzPacketReaderOperationCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>>> deserializerFullCompletionObservers = new ArrayList<>();
    private final List<Consumer<WkSzPacketReaderOperationCore<T,? extends XS,?,?,?,? extends XO,?,? extends XD,?,?>>> onBeforeDeserializingObservers = new ArrayList<>();

    protected WkSzDefinitionCore(
      WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
      Function<AXBC,XQC> rxRuntimeFactory,
      BiFunction<XO,T,XR> rxResultFactory,
      WkSzPacketReaderOperationCoreFactory<T,XS,XD,DC,XO,AXBC> readingOpFactory,
      Function<AYBC,YQC> txRuntimeFactory,
      Function<YO,YR> txResultFactory,
      WkSzPacketWriterOperationCoreFactory<T,YS,YD,DC,YO,AYBC> writingOpFactory,
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
      Consumer<WkSzPacketWriterOperationCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>> onDeserializerCreation) {
      this.serializerCreationObservers.add(onDeserializerCreation);
    }

    public void onSerializerCreation(
      WkSzPacketWriterOperationCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?> writingOperationCore) {
      for (Consumer<WkSzPacketWriterOperationCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>> onSerializerCreation : serializerCreationObservers) {
        onSerializerCreation.accept(writingOperationCore);
      }
    }

    public void registerOnBeforeFullCompletionDeserialization(
      Consumer<WkSzPacketReaderOperationCore<T,? extends XS,?,?,?,? extends XO,?,? extends  XD,?,?>> onBeforeDeserializing) {
      this.onBeforeDeserializingObservers.add(onBeforeDeserializing);
    }

    public void onBeforeFullCompletionDeserialization(
      WkSzPacketReaderOperationCore<T,? extends XS,?,?,?,? extends XO,?,? extends  XD,?,?> deserializingOp) {
      for (Consumer<WkSzPacketReaderOperationCore<T,? extends XS,?,?,?,? extends XO,?,? extends XD,?,?>> onBeforeFullCompletion : onBeforeDeserializingObservers) {
        onBeforeFullCompletion.accept(deserializingOp);
      }
    }

    public void registerOnAfterFullCompletionDeserialization(
      Consumer<WkSzPacketReaderOperationCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>> onDeserializerFullCompletion) {
      this.deserializerFullCompletionObservers.add(onDeserializerFullCompletion);
    }

    public void onAfterFullCompletionDeserialization(
      WkSzPacketReaderOperationCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?> readingOperationCore) {
      for (Consumer<WkSzPacketReaderOperationCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>> onFullCompletion : deserializerFullCompletionObservers) {
        onFullCompletion.accept(readingOperationCore);
      }
    }

    final public WkSzPacketReaderOperationCore<?,?,?,?,?,XO,?,XD,?,?>
    newReadingOperationCore(
      int index,
      XS settings,
      AXBC parentBytestream,
      WkSzPacketReaderFieldCore<T,?,XD,?,?,?> fieldCore) {
      return this.readingOpFactory.newReadingCore(
          index,
          settings,
          parentBytestream,
          fieldCore,
          getThis());
    }

    public final WkSzPacketWriterOperationCore<?,?,?,?,?,YO,?,YD,?,?>
    newWritingOperationCore(
      int index,
      T serializable,
      YS settings,
      AYBC parentBytestream,
      WkSzPacketWriterFieldCore<T,?,YD,?,?,?> writingFieldCore) {
      return this.writingOpFactory.newWritingCore(
          index, serializable, settings, parentBytestream, writingFieldCore, getThis());
    }

    protected abstract DC getThis();

    public final D definition() {
      return this.definitionBody;
    }

    public WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore() {
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
