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
package weliyek.serialization.sequence;

import java.util.Objects;

import weliyek.serialization.WkSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSzDefinitionCoreException;
import weliyek.serialization.WkSzOperationException;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.WkSzVariableLengthOperationSettings;

public class SequenceSizeParameters<T>
{

    private final int minSize;
    private final int maxSize;
    private final WkSrlzStructDefinitionFrameNodeCore<
                      T,? extends WkSzVariableLengthOperationSettings,?,?,
                      ? extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,
                      ? extends WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode<T,?,?,?,?>,?,?,?,?,
                      ? extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,
                      ? extends WkVariableSizeSequenceSrlzOutputPacketEncoderFrameNode<T,?,?,?,?>,?,
                      ? extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,?> definitionCore;

    public SequenceSizeParameters(
      int minSize,
      int maxSize,
      WkSrlzStructDefinitionFrameNodeCore<
        T,? extends WkSzVariableLengthOperationSettings,?,?,
        ? extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,
        ? extends WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode<T,?,?,?,?>,?,?,?,?,
        ? extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,
        ? extends WkVariableSizeSequenceSrlzOutputPacketEncoderFrameNode<T,?,?,?,?>,?,
        ? extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,?> definitionCore) {
      if (minSize < 0) {
        throw new WkSzDefinitionCoreException(
                        definitionCore,
                        "Minimal size must be zero or positive");
      }
      if (maxSize < 0) {
        throw new WkSzDefinitionCoreException(
                        definitionCore,
                        "Maximal size must be zero or positive");
      }
      if (maxSize < minSize) {
        throw new WkSzDefinitionCoreException(
                        definitionCore,
                        "Maximal size must higher or equal than minimal size");
      }
      this.minSize = minSize;
      this.maxSize = maxSize;
      this.definitionCore = Objects.requireNonNull(definitionCore);
      this.definitionCore.registerOnSerializerCreationObserver(this::onSequenceSerializerCreation);
      this.definitionCore.registerOnBeforeFullCompletionDeserialization(this::onBeforeFullCompletionDeserialization);
    }

    void onSequenceSerializerCreation(
      WkSrlzOutputPacketEncoderFrameNodeCore<
        T,?,?,?,?,? extends WkVariableSizeSequenceSrlzOutputPacketEncoderFrameNode<T,?,?,?,?>,?,
        ? extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,?,?> serializer) {
      int sequenceSize = serializer.definition().extractLengthFromSerializablesSequence(serializer.serializable());
      if (sequenceSize < minSize) {
        throw new WkSzOperationException(
                        serializer,
                        "Serializable sequence is too small for this field");
      }
      if (sequenceSize > maxSize) {
        throw new WkSzOperationException(
                        serializer,
                        "Serializable sequence is too large for this field");
      }
    }

    void onBeforeFullCompletionDeserialization(
      WkSrlzInputPacketDecoderFrameNodeCore<
        ?,? extends WkSzVariableLengthOperationSettings,?,?,?,
        ? extends WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode<?,?,?,?,?>,?,
        ? extends WkSerdeDTreeVariableSizeSequenceDefinition<?>,?,?> deserializer) {
      WkSzVariableLengthOperationSettings settings = deserializer.settings();
      if (settings.getRequestedLength() < minSize) {
        throw new WkSzOperationException(
                        deserializer,
                        "Requested deserializable sequence size is shorter than permited by this field");
      }
      if (settings.getRequestedLength() > maxSize) {
        throw new WkSzOperationException(
                        deserializer,
                        "Requested deserializable sequence size is longer than permited by this field");
      }
    }

    public int minimalSize() {
        return this.minSize;
    }

    public int maximalSize() {
        return this.maxSize;
    }

}
