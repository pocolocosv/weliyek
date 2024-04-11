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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class WkSerdeDtreeNodeDataReaderDecoderCore<
                        X,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntime<?>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<?,?,XQ>,
                        XR extends WkSerdeDtreeOperationResult<X>,
                        XO extends WkSerdeDtreeNodeDataReader<X,XS,XQ,XR,XD>,
                        XOC extends WkSerdeDtreeNodeDataReaderDecoderCore<X,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends WkSerdeDtreeNodeStructDefinition<X>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        DC extends WkSerdeDtreeNodeLeafStructDefinitionCore<
                                      X,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,? extends XD,DC>>
        extends WkSerdeDtreeNodeDataReaderCore<X, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
{

    protected WkSerdeDtreeNodeDataReaderDecoderEngine<X, ? super XQC, ? super XO> rule;

    protected WkSerdeDtreeNodeDataReaderDecoderCore(
        int index,
        XS settings,
        AXB parentBytestream,
        WkSerdeDtreeNodeDataInputComponentCore<X,?,XD,?,?,?> packetField,
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
    protected final Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> onProcessingBytestream() {
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
    public final List<WkSrlzInputPacketSubfieldFrameNode<?,?,?>> subfields() {
      return Collections.emptyList();
    }

    @Override
    protected final void onPartialReadingCompletion() {
    }

    protected abstract void onSerializerFullReadingCompletion(X deserialized);

}
