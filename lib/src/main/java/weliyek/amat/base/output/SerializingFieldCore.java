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
package weliyek.amat.base.output;

import weliyek.amat.base.ComponentSegment;
import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.FieldCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.basic.aggregator.AggregatorWriting;
import weliyek.amat.basic.aggregator.AggregatorWritingCore;

public abstract class SerializingFieldCore<
                        T,
                        YS extends OperationSettings,
                        YD extends DefinitionSegment<T,?>,
                        YO extends SerializingOperation<T,YS,?,?,YD>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        AYO extends AggregatorWriting<?,?,? extends SerializingRuntime<?>,?,?>>
    extends FieldCore<
                        T, YS, YD,
                        ComponentSegmentCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>,
                        YO,
                        WritingOperationCore<?,?,?,?,?,YO,?,YD,?,?>,
                        SerializingField<T,YD,YO>,
                        AYBC,
                        AggregatorWritingCore<?,?,?,AYBC,?,?,?,?,AYO,?,?,?>>
    implements SerializingField<T,YD,YO>
{

  public SerializingFieldCore(
    int initialOperationListCapacity,
    ComponentSegmentCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD> protocolFieldCore) {
    super(
        initialOperationListCapacity,
        protocolFieldCore,
        SerializingOperation::serializable);
  }

  @Override
  protected WritingOperationCore<?,?,?,?,?,YO,?,YD,?,?>
  newOperation(int index) {
    YS settings = newSettings(index);
    T serializable = serializable(index);
    return protocolFieldCore().definitionCore().newWritingOperationCore(
        index, serializable, settings, parentBytestream(), this);
  }

  @Override
  protected abstract YS newSettings(int index);

  @Override
  protected abstract AYBC parentBytestream();

  protected abstract T serializable(int index);

  @Override
  protected void onDoneProcessing() {
    // Nothing to do
  }

  @Override
  public ComponentSegment<? extends YD> protocolField() {
    return protocolFieldCore().asProtocolField();
  }

}
