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

public abstract class WkSrlzInputPacketDecoderFrameLeafNodeCore<
                        X,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<?>,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<?,?,XQ>,
                        XR extends WkResultSrlzPacketOperationData<X>,
                        XO extends WkSrlzInputPacketDecoderFrameLeafNode<X,XS,XQ,XR,XD>,
                        XOC extends WkSrlzInputPacketDecoderFrameLeafNodeCore<X,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends WkSrlzStructDefinitionFrameLeafNode<X>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        DC extends WkSrlzStructDefinitionFrameLeafNodeCore<
                                      X,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,? extends XD,DC>>
        extends WkSrlzInputPacketDecoderFrameNodeCore<X, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
    implements WkSrlzInputPacketDecoderFrameLeafNode<X, XS, XQ, XR, XD>
{

    protected WkSrlzEngineDecoder<X, ? super XQC, ? super XO> rule;

    protected WkSrlzInputPacketDecoderFrameLeafNodeCore(
        int index,
        XS settings,
        AXB parentBytestream,
        WkSrlzInputPacketFieldFrameNodeCore<X,?,XD,?,?,?> packetField,
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
    protected final Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> onProcessingBytestream() {
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
