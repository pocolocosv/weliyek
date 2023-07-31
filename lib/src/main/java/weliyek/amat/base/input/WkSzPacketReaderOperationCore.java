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

import java.util.Optional;
import java.util.function.BiFunction;

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.WkSzPacketOperation;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketOperationSegmentCore;

public abstract class WkSzPacketReaderOperationCore<
                        T,
                        XS extends OperationSettings,
                        XQ extends DeserializingRuntime<?>,
                        XQC extends ReadingRuntimeControl<?,?,XQ>,
                        XR extends DeserializingResult<T>,
                        XO extends WkSzPacketReaderOperation<T,XS,XQ,XR,XD>,
                        XOC extends WkSzPacketReaderOperationCore<T,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends WkSzDefinition<T,?>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        DC extends WkSzDefinitionCore<
                                      T,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,?,DC>>
        extends PacketOperationSegmentCore<
                        XS, XQ, XQC, XR, XD, DC, XO, XOC, AXB,
                        WkSzPacketReaderField<T,XD,?>,
                        WkSzPacketReaderFieldCore<T,?,XD,?,?,?>>
        implements WkSzPacketReaderOperation<T, XS, XQ, XR, XD>
{

    private final XQC runtime;

    protected WkSzPacketReaderOperationCore(
      int index,
      XS settings,
      AXB parentBytestream,
      WkSzPacketReaderFieldCore<T,?,XD,?,?,?> packetField,
      DC definitionCore,
      XO operationBody) {
      super(index, settings, parentBytestream, packetField, definitionCore, operationBody);
      this.runtime = definitionCore.rxRuntimeFactory().apply(parentBytestream);
    }

    @Override
    protected Optional<WkSzPacketOperation<?,?,?,?,?>> processBytestream() {
      return super.processBytestream();
    }

    @Override
    protected final void onStart() {
        onStartReading();
    }

    protected abstract void onStartReading();

    @Override
    protected void onBeforeOperationCompletion() {
      if (packet().deserializedRequired()) {
        this.definitionCore().onBeforeFullCompletionDeserialization(this);
      } else {
        // on before partial completion ...
      }
    }

    @Override
    protected XR onCompletion() {
      T deserialized = null;
      BiFunction<XO, T, XR> resultFactory = definitionCore().rxResultFactory();
      XR result = null;
      if (packet().deserializedRequired()) {
        deserialized = onFullReadingCompletion();
        result = resultFactory.apply(body(), deserialized);
      } else {
        onPartialReadingCompletion();
        result = resultFactory.apply(body(), null);
      }
      return result;
    }

    @Override
    protected void onAfterOperationCompletion() {
      if (packet().deserializedRequired()) {
        this.definitionCore().onAfterFullCompletionDeserialization(this);
        this.packetFieldCore().filterResults().runTestOnOpRes(this);;
      } else {
        // on after partial completion ...
      }
    }

    @Override
    public final XQC getRuntimeControl() {
      return this.runtime;
    }

    protected abstract T onFullReadingCompletion();

    protected abstract void onPartialReadingCompletion();

    @Override
    protected String label() {
      return definitionCore().rxSimpleLabel();
    }

}
