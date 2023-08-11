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

public abstract class WkSzPacketWriterOperationCore<
                        T,
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YQC extends WkSzWritingRuntimeControl<?,?,YQ>,
                        YR extends WkSzWritingResult,
                        YO extends WkSzPacketWriterOperation<T,YS,YQ,YR,YD>,
                        YOC extends WkSzPacketWriterOperationCore<T,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkSzDefinition<T,?>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        DC extends WkSzDefinitionCore<
                                      T,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,?,DC>>
        extends WkSzPacketOperationCore<
                        YS, YQ, YQC, YR, YD, DC, YO, YOC, AYB,
                        WkSzPacketWriterField<T,YD,?>,
                        WkSzPacketWriterFieldCore<T,?,YD,?,?,?>>
        implements WkSzPacketWriterOperation<T, YS, YQ, YR, YD>
{

    private final T serializable;
    private final YQC runtime;

    protected WkSzPacketWriterOperationCore(
      int index,
      T serializable,
      YS settings,
      AYB parentBytestream,
      WkSzPacketWriterFieldCore<T,?,YD,?,?,?> packetHandlerCore,
      DC definitionCore,
      YO operationBody) {
      super(
            index,
            settings,
            parentBytestream,
            packetHandlerCore,
            definitionCore,
            operationBody);
        this.serializable = Objects.requireNonNull(serializable);
        this.runtime = definitionCore.txRuntimeFactory().apply(parentBytestream);
        definitionCore.onSerializerCreation(this);
    }

    @Override
    public T serializable() {
      return this.serializable;
    }

    @Override
    protected void onBeforeOperationCompletion() {
      // Nothing to do
    }

    @Override
    protected YR onCompletion() {
      return definitionCore().txResultFactory().apply(body());
    }

    @Override
    protected void onAfterOperationCompletion() {
      // Nothing to do
    }

    @Override
    protected final Optional<WkSzPacketOperation<?,?,?,?,?>> processBytestream() {
      return super.processBytestream();
    }

    @Override
    public final YQC getRuntimeControl() {
      return this.runtime;
    }

    @Override
    protected String label() {
      return packetFieldCore().protocolFieldCore().definitionCore().txSimpleLabel();
    }

}
