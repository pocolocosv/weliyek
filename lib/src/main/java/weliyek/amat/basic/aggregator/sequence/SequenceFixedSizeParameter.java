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

import weliyek.amat.base.DefinitionSegmentCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.ReadingOperationCore;
import weliyek.amat.base.output.WritingOperationCore;
import weliyek.amat.basic.sequence.FixedSizeSequenceDefinition;
import weliyek.amat.basic.sequence.FixedSizeSequenceReading;
import weliyek.amat.basic.sequence.FixedSizeSequenceWriting;
import weliyek.amat2.protocol.PacketOperationCoreException;
import weliyek.amat2.protocol.ProtocolDefinitionException;

public class SequenceFixedSizeParameter<T>
{

  private final int sequenceExpectedSize;
  private final DefinitionSegmentCore<
                        T,?,?,
                        ? extends DeserializingResult<T>,
                        ? extends FixedSizeSequenceDefinition<T,?>,
                        ? extends FixedSizeSequenceReading<T,?,?,?,?>,
                        ?,?,?,?,
                        ? extends FixedSizeSequenceDefinition<T,?>,
                        ? extends FixedSizeSequenceWriting<T,?,?,?,?>,?,
                        ? extends FixedSizeSequenceDefinition<T,?>,?> definitionCore;

  public SequenceFixedSizeParameter(
    int sequenceExpectedSize,
    DefinitionSegmentCore<
      T,?,?,
      ? extends DeserializingResult<T>,
      ? extends FixedSizeSequenceDefinition<T,?>,
      ? extends FixedSizeSequenceReading<T,?,?,?,?>,
      ?,?,?,?,
      ? extends FixedSizeSequenceDefinition<T,?>,
      ? extends FixedSizeSequenceWriting<T,?,?,?,?>,?,
      ? extends FixedSizeSequenceDefinition<T,?>,?> definitionCore) {
    if (sequenceExpectedSize < 0) {
      throw new ProtocolDefinitionException(definitionCore,
                                            "Fixed sequence size cannot be negative");
    }
    this.sequenceExpectedSize = sequenceExpectedSize;
    this.definitionCore = Objects.requireNonNull(definitionCore);
    this.definitionCore.registerOnSerializerCreationObserver(this::onSequenceSerializerCreation);
    this.definitionCore.registerOnAfterFullCompletionDeserialization(this::onAfterFullCompletionDeserialization);
  }

  void onSequenceSerializerCreation(
    WritingOperationCore<
      T,?,?,?,?,? extends FixedSizeSequenceWriting<T,?,?,?,?>,
      ?,? extends FixedSizeSequenceDefinition<T,?>,?,?> serializer) {
    int sequenceSize = serializer.definition().extractLengthFromSerializablesSequence(serializer.serializable());
    if (sequenceSize != this.sequenceExpectedSize) {
      throw new PacketOperationCoreException(serializer, "Supplied sequence size differs from expected size");
    }
  }

  void onAfterFullCompletionDeserialization(
    ReadingOperationCore<
      T,?,?,?,
      ? extends DeserializingResult<T>,
      ? extends FixedSizeSequenceReading<T,?,?,?,?>,?,
      ? extends FixedSizeSequenceDefinition<T,?>,?,?> deserializer) {
    T sequence = deserializer.result().get().deserialized().get();
    int seqLen = deserializer.definition().extractLengthFromSerializablesSequence(sequence);
    if (seqLen != this.sequenceExpectedSize) {
      throw new PacketOperationCoreException(
                      deserializer,
                      "Sequence resulting from deserialization does not comform to expected size");
    }
  }

  public int sequenceExpectedSize() {
    return this.sequenceExpectedSize;
  }

}
