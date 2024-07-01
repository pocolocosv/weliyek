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

import java.util.Optional;
import java.util.function.BiFunction;

public abstract class WkSerdeDtreeMsgReaderCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntime<?>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<?,?,XQ>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XO extends WkSerdeDtreeMsgReader<T,XS,XQ,XR,XD>,
                        XOC extends WkSerdeDtreeMsgReaderCore<
                                      T,XS,XQ,XQC,XR,XO,?,XD,XDC,AXBC>,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        XDC extends WkSerdeDtreeStructDefinitionCore<
                                      T,XS,XQC,XR,XD,?,XO,XOC,AXBC,?,?,?,?,?,?,?,?,? extends XD,?>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>>
        extends WkSerdeDtreeMsgOperationCore<
                        XS, XQ, XQC, XR, XD, XDC, XO, XOC,
                        WkSerdeDtreeMsgInputFieldCore<?,XS,?,?,AXBC,?,?,?>>
        implements WkSerdeDtreeMsgReader<T, XS, XQ, XR, XD>
{

    private final XQC runtime;

    protected WkSerdeDtreeMsgReaderCore(
      int index,
      WkSerdeDtreeMsgInputFieldCore<?,XS,?,?,AXBC,?,?,?> readerFieldCore,
      XDC definitionCore,
      XO operationBody) {
      super(index, readerFieldCore, definitionCore, operationBody);
      this.runtime = definitionCore().rxRuntimeFactory().apply(readerFieldCore.parentBytestream());
    }

    @Override
    protected final XS
    extractSettingsFrom(WkSerdeDtreeMsgInputFieldCore<?,XS,?,?,AXBC,?,?,?> msgFieldCore) {
    return msgFieldCore.newSettings(index());
    }

    @Override
    protected Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> processBytestream() {
      return super.processBytestream();
    }

    @Override
    protected final void onStart() {
        onStartReading();
    }

    protected abstract void onStartReading();

    @Override
    protected void onBeforeOperationCompletion() {
      if (packetFieldCore().deserializedRequired()) {
        definitionCore().onBeforeFullCompletionDeserialization(this);
      } else {
        // on before partial completion ...
      }
    }

    @Override
    protected XR onCompletion() {
      T deserialized = null;
      BiFunction<XO, T, XR> resultFactory = definitionCore().rxResultFactory();
      XR result = null;
      if (packetFieldCore().deserializedRequired()) {
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
      if (packetFieldCore().deserializedRequired()) {
        definitionCore().onAfterFullCompletionDeserialization(this);
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

    @Override
    public WkSerdeDtreeMsgInputField<?,?,?> parentField() {
      return (WkSerdeDtreeMsgInputField<?, ?, ?>) super.parentField();
    }

}
