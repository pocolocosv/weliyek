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

public abstract class WkSerdeDtreeStructDefinitionCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<?,?,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        XDC extends WkSerdeDtreeStructDefinitionCore<T,XS,XQC,XR,XD,?,XO,XOC,AXBC,?,?,?,?,?,?,?,?,? extends XD,?>,
                        XO extends WkSerdeDtreeMsgReader<T,XS,?,XR,XD>,
                        XOC extends WkSerdeDtreeMsgReaderCore<T,XS,?,XQC,XR,XO,?,XD,XDC,AXBC>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<?,?,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        YDC extends WkSerdeDtreeStructDefinitionCore<T,?,?,?,?,?,?,?,?,YS,YQC,YR,YD,?,YO,YOC,AYBC,? extends YD,?>,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,?,YR,YD>,
                        YOC extends WkSerdeDtreeMsgWriterCore<T,YS,?,YQC,YR,YO,?,YD,YDC,AYBC>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreeStructDefinition<T>,
                        DC extends WkSerdeDtreeStructDefinitionCore<
                                      T,XS,XQC,XR,XD,XDC,XO,XOC,AXBC,YS,YQC,YR,YD,YDC,YO,YOC,AYBC,D,?>>
    implements WkSerdeDtreeStructDefinition<T>
{

    public static final char NAME_PREFIX = '<';
    public static final char NAME_SUFFIX = '>';

    private final WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore;
    private final Function<AXBC, XQC> rxRuntimeFactory;
    private final Function<AYBC, YQC> txRuntimeFactory;
    private final BiFunction<XO, T, XR> rxResultFactory;
    private final BiFunction<YO, T, YR> txResultFactory;
    public final WkSerdeDtreeMsgReaderFactory<XS,XDC,XOC,AXBC> readingOpFactory;
    public final WkSerdeDtreeMsgWriterFactory<T,YS,YDC,YOC,AYBC> writingOpFactory;
    private final D definitionBody;
    private final Class<T> serializableClass;
    private final List<Consumer<WkSerdeDtreeMsgWriterCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>>> serializerCreationObservers = new ArrayList<>();
    private final List<Consumer<WkSerdeDtreeMsgReaderCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>>> deserializerFullCompletionObservers = new ArrayList<>();
    private final List<Consumer<WkSerdeDtreeMsgReaderCore<T,? extends XS,?,?,?,? extends XO,?,? extends XD,?,?>>> onBeforeDeserializingObservers = new ArrayList<>();

    protected WkSerdeDtreeStructDefinitionCore(
      WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
      Function<AXBC,XQC> rxRuntimeFactory,
      BiFunction<XO,T,XR> rxResultFactory,
      WkSerdeDtreeMsgReaderFactory<XS,XDC,XOC,AXBC> readingOpFactory,
      Function<AYBC,YQC> txRuntimeFactory,
      BiFunction<YO,T,YR> txResultFactory,
      WkSerdeDtreeMsgWriterFactory<T,YS,YDC,YOC,AYBC> writingOpFactory,
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
      Consumer<WkSerdeDtreeMsgWriterCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>> onDeserializerCreation) {
      this.serializerCreationObservers.add(onDeserializerCreation);
    }

    public void onSerializerCreation(
      WkSerdeDtreeMsgWriterCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?> writingOperationCore) {
      for (Consumer<WkSerdeDtreeMsgWriterCore<T,?,?,?,?,? extends YO,?,? extends YD,?,?>> onSerializerCreation : serializerCreationObservers) {
        onSerializerCreation.accept(writingOperationCore);
      }
    }

    public void registerOnBeforeFullCompletionDeserialization(
      Consumer<WkSerdeDtreeMsgReaderCore<T,? extends XS,?,?,?,? extends XO,?,? extends  XD,?,?>> onBeforeDeserializing) {
      this.onBeforeDeserializingObservers.add(onBeforeDeserializing);
    }

    public void onBeforeFullCompletionDeserialization(
      WkSerdeDtreeMsgReaderCore<T,? extends XS,?,?,?,? extends XO,?,? extends  XD,?,?> deserializingOp) {
      for (Consumer<WkSerdeDtreeMsgReaderCore<T,? extends XS,?,?,?,? extends XO,?,? extends XD,?,?>> onBeforeFullCompletion : onBeforeDeserializingObservers) {
        onBeforeFullCompletion.accept(deserializingOp);
      }
    }

    public void registerOnAfterFullCompletionDeserialization(
      Consumer<WkSerdeDtreeMsgReaderCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>> onDeserializerFullCompletion) {
      this.deserializerFullCompletionObservers.add(onDeserializerFullCompletion);
    }

    public void onAfterFullCompletionDeserialization(
      WkSerdeDtreeMsgReaderCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?> readingOperationCore) {
      for (Consumer<WkSerdeDtreeMsgReaderCore<T,? extends XS,?,?,? extends XR,? extends XO,?,? extends XD,?,?>> onFullCompletion : deserializerFullCompletionObservers) {
        onFullCompletion.accept(readingOperationCore);
      }
    }

    @SuppressWarnings("unchecked")
    final public WkSerdeDtreeMsgReaderCore<?,?,?,?,?,XO,?,?,?,?>
    newReadingOperationCore(
      int index,
      XS settings,
      AXBC parentBytestream,
      @SuppressWarnings("rawtypes") WkSerdeDtreeMsgInputFieldCore fieldCore) {
      return this.readingOpFactory.newReadingCore(
          index, settings, parentBytestream, fieldCore, (XDC) this);
    }

    @SuppressWarnings("unchecked")
    public final WkSerdeDtreeMsgWriterCore<?,?,?,?,?,YO,?,?,?,?>
    newWritingOperationCore(
      int index,
      YS settings,
      AYBC parentBytestream,
      T serializable,
      @SuppressWarnings("rawtypes") WkSerdeDtreeMsgOutputFieldCore writingFieldCore) {
      return this.writingOpFactory.newWritingCore(
          index, serializable, settings, parentBytestream, writingFieldCore, (YDC) this);
    }

    protected abstract DC getThis();

    public final D definition() {
      return this.definitionBody;
    }

    public WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore() {
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

    public BiFunction<YO, T, YR> txResultFactory() {
      return txResultFactory;
    }

    public String simpleLabel() {
      return   serializableClassName();
    }

    public String rxSimpleLabel() {
      return NAME_PREFIX + "I:" + serializableClassName() + NAME_SUFFIX;
    }

    protected String serializableClassName() {
      return definition().serializableClass().getSimpleName();
    }

    @Override
    public final Class<T> serializableClass() {
    return this.serializableClass;
    }

}
