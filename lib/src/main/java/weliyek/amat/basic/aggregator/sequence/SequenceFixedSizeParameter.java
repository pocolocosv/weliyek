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

import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzPacketReaderOperationCore;
import weliyek.amat.base.output.WkSzPacketWriterOperationCore;
import weliyek.amat.basic.sequence.WkSzFixedSizeSequenceDefinition;
import weliyek.amat.basic.sequence.FixedSizeSequenceReading;
import weliyek.amat.basic.sequence.FixedSizeSequenceWriting;
import weliyek.amat2.protocol.WkSzPacketOperationException;
import weliyek.serialization.WkSzOperationException;
import weliyek.serialization.base.WkSzDefinitionCore;
import weliyek.amat2.protocol.WkSzDefinitionCoreException;

public class SequenceFixedSizeParameter<T>
{

  private final int sequenceExpectedSize;
  private final WkSzDefinitionCore<
                        T,?,?,
                        ? extends WkSzReadingResult<T>,
                        ? extends WkSzFixedSizeSequenceDefinition<T,?>,
                        ? extends FixedSizeSequenceReading<T,?,?,?,?>,
                        ?,?,?,?,
                        ? extends WkSzFixedSizeSequenceDefinition<T,?>,
                        ? extends FixedSizeSequenceWriting<T,?,?,?,?>,?,
                        ? extends WkSzFixedSizeSequenceDefinition<T,?>,?> definitionCore;

  public SequenceFixedSizeParameter(
    int sequenceExpectedSize,
    WkSzDefinitionCore<
      T,?,?,
      ? extends WkSzReadingResult<T>,
      ? extends WkSzFixedSizeSequenceDefinition<T,?>,
      ? extends FixedSizeSequenceReading<T,?,?,?,?>,
      ?,?,?,?,
      ? extends WkSzFixedSizeSequenceDefinition<T,?>,
      ? extends FixedSizeSequenceWriting<T,?,?,?,?>,?,
      ? extends WkSzFixedSizeSequenceDefinition<T,?>,?> definitionCore) {
    if (sequenceExpectedSize < 0) {
      throw new WkSzDefinitionCoreException(definitionCore,
                                            "Fixed sequence size cannot be negative");
    }
    this.sequenceExpectedSize = sequenceExpectedSize;
    this.definitionCore = Objects.requireNonNull(definitionCore);
    this.definitionCore.registerOnSerializerCreationObserver(this::onSequenceSerializerCreation);
    this.definitionCore.registerOnAfterFullCompletionDeserialization(this::onAfterFullCompletionDeserialization);
  }

  void onSequenceSerializerCreation(
    WkSzPacketWriterOperationCore<
      T,?,?,?,?,? extends FixedSizeSequenceWriting<T,?,?,?,?>,
      ?,? extends WkSzFixedSizeSequenceDefinition<T,?>,?,?> serializer) {
    int sequenceSize = serializer.definition().extractLengthFromSerializablesSequence(serializer.serializable());
    if (sequenceSize != this.sequenceExpectedSize) {
      throw new WkSzOperationException(serializer, "Supplied sequence size differs from expected size");
    }
  }

  void onAfterFullCompletionDeserialization(
    WkSzPacketReaderOperationCore<
      T,?,?,?,
      ? extends WkSzReadingResult<T>,
      ? extends FixedSizeSequenceReading<T,?,?,?,?>,?,
      ? extends WkSzFixedSizeSequenceDefinition<T,?>,?,?> deserializer) {
    T sequence = deserializer.result().get().deserialized().get();
    int seqLen = deserializer.definition().extractLengthFromSerializablesSequence(sequence);
    if (seqLen != this.sequenceExpectedSize) {
      throw new WkSzOperationException(
                      deserializer,
                      "Sequence resulting from deserialization does not comform to expected size");
    }
  }

  public int sequenceExpectedSize() {
    return this.sequenceExpectedSize;
  }

}
