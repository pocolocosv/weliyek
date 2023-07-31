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
package weliyek.amat.basic.aggregator.sequence;

import java.util.Objects;

import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.input.WkSzPacketReaderOperationCore;
import weliyek.amat.base.output.WkSzPacketWriterOperationCore;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableSizeSequenceDefinition;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceReading;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceWriting;
import weliyek.amat2.protocol.PacketOperationCoreException;
import weliyek.amat2.protocol.ProtocolDefinitionException;

public class SequenceSizeParameters<T>
{

    private final int minSize;
    private final int maxSize;
    private final WkSzDefinitionCore<
                      T,? extends VariableLengthSettings,?,?,
                      ? extends WkSzVariableSizeSequenceDefinition<T,?>,
                      ? extends VariableSizeSequenceReading<T,?,?,?,?>,?,?,?,?,
                      ? extends WkSzVariableSizeSequenceDefinition<T,?>,
                      ? extends VariableSizeSequenceWriting<T,?,?,?,?>,?,
                      ? extends WkSzVariableSizeSequenceDefinition<T,?>,?> definitionCore;

    public SequenceSizeParameters(
      int minSize,
      int maxSize,
      WkSzDefinitionCore<
        T,? extends VariableLengthSettings,?,?,
        ? extends WkSzVariableSizeSequenceDefinition<T,?>,
        ? extends VariableSizeSequenceReading<T,?,?,?,?>,?,?,?,?,
        ? extends WkSzVariableSizeSequenceDefinition<T,?>,
        ? extends VariableSizeSequenceWriting<T,?,?,?,?>,?,
        ? extends WkSzVariableSizeSequenceDefinition<T,?>,?> definitionCore) {
      if (minSize < 0) {
        throw new ProtocolDefinitionException(
                        definitionCore,
                        "Minimal size must be zero or positive");
      }
      if (maxSize < 0) {
        throw new ProtocolDefinitionException(
                        definitionCore,
                        "Maximal size must be zero or positive");
      }
      if (maxSize < minSize) {
        throw new ProtocolDefinitionException(
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
      WkSzPacketWriterOperationCore<
        T,?,?,?,?,? extends VariableSizeSequenceWriting<T,?,?,?,?>,?,
        ? extends WkSzVariableSizeSequenceDefinition<T,?>,?,?> serializer) {
      int sequenceSize = serializer.definition().extractLengthFromSerializablesSequence(serializer.serializable());
      if (sequenceSize < minSize) {
        throw new PacketOperationCoreException(
                        serializer,
                        "Serializable sequence is too small for this field");
      }
      if (sequenceSize > maxSize) {
        throw new PacketOperationCoreException(
                        serializer,
                        "Serializable sequence is too large for this field");
      }
    }

    void onBeforeFullCompletionDeserialization(
      WkSzPacketReaderOperationCore<
        ?,? extends VariableLengthSettings,?,?,?,
        ? extends VariableSizeSequenceReading<?,?,?,?,?>,?,
        ? extends WkSzVariableSizeSequenceDefinition<?,?>,?,?> deserializer) {
      VariableLengthSettings settings = deserializer.settings();
      if (settings.getRequestedLength() < minSize) {
        throw new PacketOperationCoreException(
                        deserializer,
                        "Requested deserializable sequence size is shorter than permited by this field");
      }
      if (settings.getRequestedLength() > maxSize) {
        throw new PacketOperationCoreException(
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
