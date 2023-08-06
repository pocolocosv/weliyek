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
package weliyek.amat.basic.serializer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.WkSzPacketOperation;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.base.output.WkSzPacketWriterOperationCore;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public abstract class WkSzSerializerWriterCore<
                        T,
                        YS extends OperationSettings,
                        YQ extends SerializingRuntime<?>,
                        YQC extends WritingRuntimeControl<?,?,YQ>,
                        YR extends SerializingResult,
                        YO extends WkSzPacketWriterOperation<T,YS,YQ,YR,YD>,
                        YOC extends WkSzSerializerWriterCore<T,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkSzDefinition<T,?>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        DC extends WkSzSerializerDefinitionCore<T,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,? extends YD,DC>>
    extends WkSzPacketWriterOperationCore<T, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
{

    protected OutputSerializationEngine<T, ? super YQC, ? super YO> rule;

    protected WkSzSerializerWriterCore(
            int index,
            T serializable,
            YS settings,
            AYB parentBytestream,
            WkSzPacketWriterFieldCore<T,?,YD,?,?,?> packetHandlerCore,
            DC definitionCore,
            YO operationBody) {
        super(
                index,
                serializable,
                settings,
                parentBytestream,
                packetHandlerCore,
                definitionCore,
                operationBody);
    }

    @Override
    protected final Optional<WkSzPacketOperation<?,?,?,?,?>> onProcessingBytestream() {
        if (this.rule.isDone())
          // Protect against calls after completing.
          return Optional.empty();

        this.rule.processBytestream();
        if (this.rule.isDone()) {
          this.completeOperation();
          return Optional.of(body());
        }
        // No subfield has changed in this call.
        return Optional.empty();
    }

    @Override
    protected void onStart() {
      this.rule = definitionCore().txSerializerFactory
                                  .newEngine(getRuntimeControl(), body());
      this.rule.start();
      onSerializingOperationInitialization();
    }

    protected abstract void onSerializingOperationInitialization();

    @Override
    public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
      return Collections.emptyList();
    }

}
