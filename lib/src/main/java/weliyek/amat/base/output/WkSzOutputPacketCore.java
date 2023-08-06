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

import java.util.Objects;
import java.util.Optional;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.WkSzPacketOperation;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriter;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriterCore;

public final class WkSzOutputPacketCore<
                        T,
                        YS extends OperationSettings,
                        YD extends WkSzDefinition<T,?>,
                        YO extends WkSzPacketWriterOperation<T,YS,?,?,YD>,
                        AYBC extends WkSzOutputBytestreamBase<?>>
    extends WkSzPacketWriterFieldCore<
                        T, YS, YD, YO, AYBC,
                        WkSzAggregatorWriter<?,?,? extends SerializingRuntime<?>,?,?>>
    implements WkSzOutputPacket<T, YD, YO>
{

  static public class WritingParameters<
                        Y,
                        S extends OperationSettings,
                        AYB extends WkSzOutputBytestreamBase<?>>
  {

    final Y serializable;
    final S settings;
    final AYB precursorBytestream;

    public WritingParameters(Y serializable, S settings, AYB precursorBytestream) {
      this.serializable = Objects.requireNonNull(serializable);
      this.settings = Objects.requireNonNull(settings);
      this.precursorBytestream = Objects.requireNonNull(precursorBytestream);
    }

  }

  private final WkSzOutputPacketCore.WritingParameters<T,YS,AYBC> parameters;
  private Optional<WkSzPacketOperation<?,?,?,?,?>> previousOpResult = Optional.empty();

  public WkSzOutputPacketCore(
    WkSzStructComponentCoreBase<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD> protocolFieldCore,
    WkSzOutputPacketCore.WritingParameters<T,YS,AYBC> parameters) {
    super(1, protocolFieldCore);
    this.parameters = parameters;
  }

  @Override
  public void processBytestream() {
    this.previousOpResult = super.processSingleStepBytestream();
  }

  @Override
public Optional<WkSzPacketOperation<?,?,?,?,?>> previousProcessingSteapResult() {
    return this.previousOpResult;
  }

  @Override
  protected YS newSettings(int index) {
    return this.parameters.settings;
  }

  @Override
  protected AYBC parentBytestream() {
    return this.parameters.precursorBytestream;
  }

  @Override
  protected T serializable(int index) {
    return this.parameters.serializable;
  }

  @Override
  protected int computeExpectedNumberOfOperations() {
    return 1;
  }

  @Override
  protected void onPacketFieldInitialization() {
    // TODO Nothing to do?
  }

  @Override
  protected void onPacketFieldSucccessfullyEnabled() {
    // TODO Nothing to do?
  }

  @Override
  protected WkSzAggregatorWriterCore<?,?,?,AYBC,?,?,?,?,WkSzAggregatorWriter<?,?,? extends SerializingRuntime<?>,?,?>,?,?,?>
  parentOperationCore() {
    return null;
  }

  public WkSzPacketWriterOperation<?, ?, ?, ?, ?> parentOperation() {
    return null;
  }

  @Override
  public WkSzOutputPacket<T, YD, YO> asPacket() {
    return this;
  }

}
