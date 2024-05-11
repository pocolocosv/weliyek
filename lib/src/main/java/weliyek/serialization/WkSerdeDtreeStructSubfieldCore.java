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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import weliyek.serialization.filter.WkSrlzPacketNodePredicate;

public class WkSerdeDtreeStructSubfieldCore<
                        T,
                        AT,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        XO extends WkSerdeDtreeMsgReader<T,XS,?,?,XD>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        AXO extends WkSerdeDtreeAggregatorMsgReader<AT,?,? extends WkSerdeDtreeOperationInputRuntime<?>,?,?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,?,?,YD>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        AYO extends WkSerdeDtreeAggregatorMsgWriter<AT,?,?,?,?>,
                        D extends WkSerdeDtreeStructDefinition<T>>
    extends WkSerdeDtreeStructFieldCore<
                        T,XO,YO,D,
                        WkSerdeDtreeStructDefinitionCore<T,XS,?,?,XD,?,XO,?,AXBC,YS,?,?,YD,?,YO,?,AYBC,D,?>>
    implements WkSerdeDtreeStructSubfield<AXO, AYO, D>
{

  private final Optional<WkSerdeDtreeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?>> parentDefinitionCore;
  private final Optional<Predicate<? super AXO>> rxOptionalityTest;
  private final ToIntFunction<? super AXO> numberOfRxOperationsEvaluator;
  private final WkOperationSettingsFactory<AXO, XS> rxSettingsFactory;
  private final Optional<Predicate<? super AYO>> txOptionalityTest;
  private final ToIntFunction<? super AYO> numberOfTxOperationsEvaluator;
  private final WkOperationSettingsFactory<AYO, YS> txSettingsFactory;
  private final WkSzPacketWriteDisaggregator<T, YD, AT, AYO> disaggregator;
  private boolean deserializedRequiredByAggregator;
  private final WkSerdeDtreeStructSubfield<AXO, AYO, D> body;
  private int order;

  WkSerdeDtreeStructSubfieldCore(
    String label,
    Optional<Predicate<? super AXO>> rxOptionalityTest,
    ToIntFunction<? super AXO> numberOfRxOperationsEvaluator,
    WkOperationSettingsFactory<AXO, XS> rxSettingsFactory,
    Optional<Predicate<? super AYO>> txOptionalityTest,
    ToIntFunction<? super AYO> numberOfTxOperationsEvaluator,
    WkOperationSettingsFactory<AYO, YS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<T, YD, AT, AYO> disaggregator,
    boolean deserializedRequiredByAggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ? extends WkSerdeDtreeStructDefinitionCore<T,XS,?,?,XD,?,XO,?,AXBC,YS,?,?,YD,?,YO,?,AYBC,D,?>> definitionFactory,
    WkSerdeDtreeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore) {
    super(label, definitionFactory);
    this.parentDefinitionCore = Optional.of(parentDefinitionCore);
    this.rxOptionalityTest = rxOptionalityTest;
    this.numberOfRxOperationsEvaluator = numberOfRxOperationsEvaluator;
    this.rxSettingsFactory = Objects.requireNonNull(rxSettingsFactory);
    this.txOptionalityTest = txOptionalityTest;
    this.numberOfTxOperationsEvaluator = numberOfTxOperationsEvaluator;
    this.txSettingsFactory = Objects.requireNonNull(txSettingsFactory);
    this.disaggregator = Objects.requireNonNull(disaggregator);
    this.deserializedRequiredByAggregator = deserializedRequiredByAggregator;
    this.body = new WkSerdeDtreeStructSubfield<AXO, AYO, D>() {

      @Override
      public String label() {
        return WkSerdeDtreeStructSubfieldCore.this.label();
      }

      @Override
      public D definition() {
        return WkSerdeDtreeStructSubfieldCore.this.definition();
      }

      @Override
      public String name() {
        return WkSerdeDtreeStructSubfieldCore.this.name();
      }

      @Override
      public WkSrlzPacketNodePredicate<?, ?> makeTester(
        Predicate<WkSerdeDtreeMsgField<?, ?, ? super D>> test,
        String description) {
        return WkSerdeDtreeStructSubfieldCore.this.makeTester(test, description);
      }

      @Override
      public Optional<Predicate<? super AXO>> rxOptionalityTest() {
        return WkSerdeDtreeStructSubfieldCore.this.rxOptionalityTest();
      }

      @Override
      public Optional<Predicate<? super AYO>> txOptionalityTest() {
        return WkSerdeDtreeStructSubfieldCore.this.txOptionalityTest();
      }

      @Override
      public ToIntFunction<? super AXO> numberOfRxOperationsEvaluator() {
        return WkSerdeDtreeStructSubfieldCore.this.numberOfRxOperationsEvaluator();
      }

      @Override
      public ToIntFunction<? super AYO> numberOfTxOperationsEvaluator() {
        return WkSerdeDtreeStructSubfieldCore.this.numberOfTxOperationsEvaluator();
      }

      @Override
      public int executionOrder() {
        return WkSerdeDtreeStructSubfieldCore.this.executionOrder();
      }

    };
  }

  WkSerdeDtreeMsgInputSubfieldCore<T,XS,XD,XO,AT,AXBC,AXO>
  newReaderSubfieldCore(WkSerdeDtreeAggregatorMsgReaderCore<AT,?,?,AXBC,?,?,?,?,?,AXO,?,?> readingParentOp) {
    boolean enableIt = rxOptionalityTest().isEmpty();
    if (rxOptionalityTest().isPresent()) {
      enableIt = rxOptionalityTest().get().test(readingParentOp.body());
    }
    @SuppressWarnings("unchecked")
    WkSerdeDtreeMsgInputSubfieldCore<T,XS,XD,XO,AT,AXBC,AXO> readerSubfield =
      new WkSerdeDtreeMsgInputSubfieldCore<T,XS,XD,XO,AT,AXBC,AXO>(enableIt, (WkSerdeDtreeStructSubfieldCore<T,AT,XS,XD,XO,AXBC,AXO,?,?,?,?,?,? extends XD>) this, readingParentOp);
    readerSubfield.initialize();
    return readerSubfield;
  }

  WkSerdeDtreeMsgOutputSubfieldCore<T,YS,YD,YO,AT,AYBC,AYO>
  newWriterSubfieldCore(
    WkSerdeDtreeAggregatorMsgWriterCore<AT,?,?,AYBC,?,?,?,?,?,AYO,?,?> writerParentOp) {
    boolean enableIt = txOptionalityTest().isEmpty();
    if (txOptionalityTest().isPresent()) {
      enableIt = txOptionalityTest().get().test(writerParentOp.body());
    }
    @SuppressWarnings("unchecked")
    WkSerdeDtreeMsgOutputSubfieldCore<T,YS,YD,YO,AT,AYBC,AYO> newWriterSubfield =
      new WkSerdeDtreeMsgOutputSubfieldCore<T,YS,YD,YO,AT,AYBC,AYO>(
            enableIt,
            (WkSerdeDtreeStructSubfieldCore<T,AT,?,?,?,?,?,YS,YD,YO,AYBC,AYO,? extends YD>) this,
            writerParentOp);
    newWriterSubfield.initialize();
    return newWriterSubfield;
  }

  @Override
  protected Optional<WkSerdeDtreeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?>> parentDefinitionCore() {
    return this.parentDefinitionCore;
  }

  @Override
  public Optional<Predicate<? super AXO>> rxOptionalityTest() {
    return this.rxOptionalityTest;
  }

  @Override
  public Optional<Predicate<? super AYO>> txOptionalityTest() {
    return this.txOptionalityTest;
  }

  @Override
  public ToIntFunction<? super AXO> numberOfRxOperationsEvaluator() {
    return this.numberOfRxOperationsEvaluator;
  }

  @Override
  public ToIntFunction<? super AYO> numberOfTxOperationsEvaluator() {
    return this.numberOfTxOperationsEvaluator;
  }

  @Override
  public int executionOrder() {
    return this.order;
  }

  @Override
  public WkSerdeDtreeStructSubfield<AXO, AYO, D> asProtocolField() {
    return this.body;
  }

  final XS newRxSettings(int index, AXO parentOp) {
    return this.rxSettingsFactory.newSettings(index, parentOp);
  }

  final int expectedNumberOfRxOps(AXO parentOp) {
    return this.numberOfRxOperationsEvaluator.applyAsInt(parentOp);
  }

  final YS newTxSettings(int index, AYO parentOp) {
    return this.txSettingsFactory.newSettings(index, parentOp);
  }

  final int expectedNumberOfTxOps(AYO parentOp) {
    return this.numberOfTxOperationsEvaluator.applyAsInt(parentOp);
  }

  final T disaggregate(int index, AYO parentOp, WkSerdeDtreeMsgOutputField<T, YD, ?> dataField) {
    return this.disaggregator.disaggregate(dataField, parentOp, index);
  }

  boolean isDeserializedRequiredByAggregator() {
    return this.deserializedRequiredByAggregator;
  }

  void setOrder(int newOrderIndex) {
    this.order = newOrderIndex;
  }

}
