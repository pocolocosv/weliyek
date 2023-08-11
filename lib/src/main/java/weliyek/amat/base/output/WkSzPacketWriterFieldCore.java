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

import weliyek.amat.base.WkSzPacketFieldCore;
import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriter;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriterCore;
import weliyek.serialization.WkSzDefinition;
import weliyek.serialization.WkSzStructComponent;
import weliyek.serialization.base.WkSzStructComponentCoreBase;

public abstract class WkSzPacketWriterFieldCore<
                        T,
                        YS extends WkSzOperationSettings,
                        YD extends WkSzDefinition<T,?>,
                        YO extends WkSzPacketWriterOperation<T,YS,?,?,YD>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        AYO extends WkSzAggregatorWriter<?,?,? extends WkSzWritingRuntime<?>,?,?>>
    extends WkSzPacketFieldCore<
                        T, YS, YD,
                        WkSzStructComponentCoreBase<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>,
                        YO,
                        WkSzPacketWriterOperationCore<?,?,?,?,?,YO,?,YD,?,?>,
                        WkSzPacketWriterField<T,YD,YO>,
                        AYBC,
                        WkSzAggregatorWriterCore<?,?,?,AYBC,?,?,?,?,AYO,?,?,?>>
    implements WkSzPacketWriterField<T,YD,YO>
{

  public WkSzPacketWriterFieldCore(
    int initialOperationListCapacity,
    WkSzStructComponentCoreBase<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD> protocolFieldCore) {
    super(
        initialOperationListCapacity,
        protocolFieldCore,
        WkSzPacketWriterOperation::serializable);
  }

  @Override
  protected WkSzPacketWriterOperationCore<?,?,?,?,?,YO,?,YD,?,?>
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
  public WkSzStructComponent<? extends YD> structComponent() {
    return protocolFieldCore().asProtocolField();
  }

}
