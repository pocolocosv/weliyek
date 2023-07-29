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
package weliyek.amat.base.input;

import weliyek.amat.base.ComponentSegment;
import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.DefinitionSegmentCore;
import weliyek.amat.base.Direction;
import weliyek.amat.base.FieldCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.basic.aggregator.AggregatorReading;
import weliyek.amat.basic.aggregator.AggregatorReadingCore;
import weliyek.amat2.protocol.filter.FilterResults;

public abstract class DeserializingFieldCore<
                        T,
                        XS extends OperationSettings,
                        XD extends DefinitionSegment<T,?>,
                        XO extends DeserializingOperation<
                                        T,XS,?,? extends DeserializingResult<T>,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        AXO extends AggregatorReading<?,?,? extends DeserializingRuntime<?>,?,?>>
    extends FieldCore<
                        T, XS, XD,
                        ComponentSegmentCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD>,
                        XO,
                        ReadingOperationCore<?,?,?,?,?,XO,?,XD,?,?>,
                        DeserializingField<T,XD,XO>,
                        AXBC,
                        AggregatorReadingCore<?,?,?,AXBC,?,?,?,?,AXO,?,?,?>>
    implements DeserializingField<T,XD,XO>
{

  private boolean requiredByFilter;

  protected DeserializingFieldCore(
    int initialOperationListCapacity,
    ComponentSegmentCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD> protocolHandler) {
    super(
        initialOperationListCapacity,
        protocolHandler,
        DeserializingFieldCore::getDeserializedFromReadingOp);
  }

  @Override
  protected ReadingOperationCore<?,?,?,?,?,XO,?,XD,?,?>
  newOperation(int index) {
    final XS settings = newSettings(index);
    final AXBC parentBytestream = parentBytestream();
    final ComponentSegmentCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD> componentCore = protocolFieldCore();
    DefinitionSegmentCore<T,XS,?,?,XD,XO,AXBC,?,?,?,?,?,?,? extends XD,?> definitionCore = componentCore.definitionCore();
    return definitionCore.newReadingOperationCore(index, settings, parentBytestream, this);
  }

  private static
  <XX,
   OO extends DeserializingOperation<?,?,?,? extends DeserializingResult<XX>,?>>
  XX getDeserializedFromReadingOp(OO readingOp) {
    final DeserializingResult<XX> result = readingOp.result().get();
    return result.deserialized().get();
  }

  protected abstract FilterResults filterResults();

  @Override
  public boolean deserializedRequiredByFilter() {
    return this.requiredByFilter;
  }

  @Override
  protected final void onPacketFieldInitialization() {
    setRequiredByFilterFlag();
    onPacketInputFieldHandlerInitialization();
  }

  protected abstract void onPacketInputFieldHandlerInitialization();

  private void setRequiredByFilterFlag() {
    if (this.filterResults().filter().hasToBeDeserialized(this.protocolField().definition())) {
      this.requiredByFilter = true;
    } else {
      this.requiredByFilter = false;
    }
  }

  @Override
  public boolean deserializedRequired() {
    return deserializedRequiredByFilter() || this.filterResults().filter().isEmpty();
  }

  @Override
  public final SegmentType type() {
    return SegmentType.PACKET;
  }

  @Override
  public final Direction direction() {
    return Direction.DESERIALIZING;
  }

  @Override
  public ComponentSegment<? extends XD> protocolField() {
    return protocolFieldCore().asProtocolField();
  }

}
