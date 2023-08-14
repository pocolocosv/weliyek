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

public abstract class WkSrlzOutputPacketEncoderFrameLeafNodeCore<
                        T,
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YQC extends WkSzWritingRuntimeControl<?,?,YQ>,
                        YR extends WkSzWritingResult,
                        YO extends WkSrlzOutputPacketEncoderFrameLeafNode<T,YS,YQ,YR,YD>,
                        YOC extends WkSrlzOutputPacketEncoderFrameLeafNodeCore<T,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkSrlzStructDefinitionFrameLeafNode<T,?>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        DC extends WkSrlzStructDefinitionFrameLeafNodeCore<T,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,? extends YD,DC>>
    extends WkSrlzOutputPacketEncoderFrameNodeCore<T, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
    implements WkSrlzOutputPacketEncoderFrameLeafNode<T, YS, YQ, YR, YD>
{

    protected WkSrlzEngineEncoder<T, ? super YQC, ? super YO> rule;

    protected WkSrlzOutputPacketEncoderFrameLeafNodeCore(
            int index,
            T serializable,
            YS settings,
            AYB parentBytestream,
            WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> packetHandlerCore,
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
    protected final Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> onProcessingBytestream() {
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
    public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
      return Collections.emptyList();
    }

}
