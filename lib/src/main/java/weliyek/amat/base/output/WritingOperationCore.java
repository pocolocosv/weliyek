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

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.DefinitionSegmentCore;
import weliyek.amat.base.OperationSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketOperationSegmentCore;

public abstract class WritingOperationCore<
                        T,
                        YS extends OperationSettings,
                        YQ extends SerializingRuntime<?>,
                        YQC extends WritingRuntimeControl<?,?,YQ>,
                        YR extends SerializingResult,
                        YO extends SerializingOperation<T,YS,YQ,YR,YD>,
                        YOC extends WritingOperationCore<T,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends DefinitionSegment<T,?>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        DC extends DefinitionSegmentCore<
                                      T,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,?,DC>>
        extends PacketOperationSegmentCore<
                        YS, YQ, YQC, YR, YD, DC, YO, YOC, AYB,
                        SerializingField<T,YD,?>,
                        SerializingFieldCore<T,?,YD,?,?,?>>
        implements SerializingOperation<T, YS, YQ, YR, YD>
{

    private final T serializable;
    private final YQC runtime;

    protected WritingOperationCore(
      int index,
      T serializable,
      YS settings,
      AYB parentBytestream,
      SerializingFieldCore<T,?,YD,?,?,?> packetHandlerCore,
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
    protected final Optional<OperationSegment<?,?,?,?,?>> processBytestream() {
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
