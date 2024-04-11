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

public abstract class WkSerdeDtreeNodeDataOutputComponentCore<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeNodeStructDefinition<T>,
                        YO extends WkSerdeDtreeNodeDataWriter<T,YS,?,?,YD>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        AYO extends WkSerdeDtreeAggregatorWriter<?,?,? extends WkSerdeDtreeOperationOutputRuntime<?>,?,?>>
    extends WkSerdeDtreeNodeDataComponentCore<
                        T, YS, YD,
                        WkSerdeDtreeNodeStructComponentCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>,
                        YO,
                        WkSerdeDtreeNodeDataWriterCore<?,?,?,?,?,YO,?,YD,?,?>,
                        WkSerdeDtreeNodeDataOutputComponent<T,YD,YO>,
                        AYBC,
                        WkSerdeDtreeAggregatorWriterCore<?,?,?,AYBC,?,?,?,?,AYO,?,?,?>>
    implements WkSerdeDtreeNodeDataOutputComponent<T,YD,YO>
{

  public WkSerdeDtreeNodeDataOutputComponentCore(
    int initialOperationListCapacity,
    WkSerdeDtreeNodeStructComponentCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD> protocolFieldCore) {
    super(
        initialOperationListCapacity,
        protocolFieldCore,
        WkSerdeDtreeNodeDataWriter::serializable);
  }

  @Override
  protected WkSerdeDtreeNodeDataWriterCore<?,?,?,?,?,YO,?,YD,?,?>
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
  public WkSerdeDtreeNodeStructComponent<? extends YD> structComponent() {
    return protocolFieldCore().asProtocolField();
  }

}
