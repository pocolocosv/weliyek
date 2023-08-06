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
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.WkSzPacketReaderOperationCore;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.input.WkSzInputBytestreamBase;

public abstract class SerializerReadingCore<
                        X,
                        XS extends OperationSettings,
                        XQ extends DeserializingRuntime<?>,
                        XQC extends ReadingRuntimeControl<?,?,XQ>,
                        XR extends DeserializingResult<X>,
                        XO extends WkSzPacketReaderOperation<X,XS,XQ,XR,XD>,
                        XOC extends SerializerReadingCore<X,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends WkSzDefinition<X,XO>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        DC extends WkSzSerializerDefinitionCore<
                                      X,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,? extends XD,DC>>
        extends WkSzPacketReaderOperationCore<X, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
{

    protected InputSerializationEngine<X, ? super XQC, ? super XO> rule;

    protected SerializerReadingCore(
        int index,
        XS settings,
        AXB parentBytestream,
        WkSzPacketReaderFieldCore<X,?,XD,?,?,?> packetField,
        DC definitionCore,
        XO operationBody) {
        super(index, settings, parentBytestream, packetField, definitionCore, operationBody);
    }

    @Override
    protected final void onStartReading() {
      this.rule = definitionCore().rxSerializerFactory
                                  .newEngine(getRuntimeControl(), body());
      this.rule.start();
      onDeserilizingOperationInitialization();
    }

    protected abstract void onDeserilizingOperationInitialization();

    @Override
    protected final Optional<WkSzPacketOperation<?,?,?,?,?>> onProcessingBytestream() {
        this.rule.processBytestream();
        if (this.rule.isDone()) {
          this.completeOperation();
          return Optional.of(body());
        }
        // No subfield has changed in this call.
        return Optional.empty();
    }

    @Override
    protected final X onFullReadingCompletion() {
        X deserialized = this.rule.getDeserialized();
        onSerializerFullReadingCompletion(deserialized);
        return deserialized;
    }

    @Override
    public final List<WkSzPacketReaderSubfield<?,?,?>> subfields() {
      return Collections.emptyList();
    }

    @Override
    protected final void onPartialReadingCompletion() {
    }

    protected abstract void onSerializerFullReadingCompletion(X deserialized);

}
