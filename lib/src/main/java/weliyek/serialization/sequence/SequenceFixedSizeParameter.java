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

import weliyek.serialization.WkSerdeDtreeNodeDataOperationException;
import weliyek.serialization.WkSerdeDtreeMsgReaderCore;
import weliyek.serialization.WkSerdeDtreeMsgWriterCore;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSzDefinitionCoreException;

public class SequenceFixedSizeParameter<T>
{

  private final int sequenceExpectedSize;
  private final WkSerdeDtreeStructDefinitionCore<
                        T,?,?,
                        ? extends WkSerdeDtreeOperationResult<T>,
                        ? extends WkSerdeDtreeFixedSizeSequenceDefinition<T>,?,
                        ? extends WkSerdeDtreeFixedSizeSequenceReader<T,?,?,?,?>,?,
                        ?,?,?,?,
                        ? extends WkSerdeDtreeFixedSizeSequenceDefinition<T>,?,
                        ? extends WkSerdeDtreeFixedSizeSequenceWriter<T,?,?,?,?>,?,?,
                        ? extends WkSerdeDtreeFixedSizeSequenceDefinition<T>,?> definitionCore;

  public SequenceFixedSizeParameter(
    int sequenceExpectedSize,
    WkSerdeDtreeStructDefinitionCore<
      T,?,?,
      ? extends WkSerdeDtreeOperationResult<T>,
      ? extends WkSerdeDtreeFixedSizeSequenceDefinition<T>,?,
      ? extends WkSerdeDtreeFixedSizeSequenceReader<T,?,?,?,?>,?,
      ?,?,?,?,
      ? extends WkSerdeDtreeFixedSizeSequenceDefinition<T>,?,
      ? extends WkSerdeDtreeFixedSizeSequenceWriter<T,?,?,?,?>,?,?,
      ? extends WkSerdeDtreeFixedSizeSequenceDefinition<T>,?> definitionCore) {
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
    WkSerdeDtreeMsgWriterCore<
      T,?,?,?,?,? extends WkSerdeDtreeFixedSizeSequenceWriter<T,?,?,?,?>,
      ?,? extends WkSerdeDtreeFixedSizeSequenceDefinition<T>,?,?> serializer) {
    int sequenceSize = serializer.definition().extractLengthFromSerializablesSequence(serializer.serializable());
    if (sequenceSize != this.sequenceExpectedSize) {
      throw new WkSerdeDtreeNodeDataOperationException(serializer, "Supplied sequence size differs from expected size");
    }
  }

  void onAfterFullCompletionDeserialization(
    WkSerdeDtreeMsgReaderCore<
      T,?,?,?,
      ? extends WkSerdeDtreeOperationResult<T>,
      ? extends WkSerdeDtreeFixedSizeSequenceReader<T,?,?,?,?>,?,
      ? extends WkSerdeDtreeFixedSizeSequenceDefinition<T>,?,?> deserializer) {
    T sequence = deserializer.result().get().serializable().get();
    int seqLen = deserializer.definition().extractLengthFromSerializablesSequence(sequence);
    if (seqLen != this.sequenceExpectedSize) {
      throw new WkSerdeDtreeNodeDataOperationException(
                      deserializer,
                      "Sequence resulting from deserialization does not comform to expected size");
    }
  }

  public int sequenceExpectedSize() {
    return this.sequenceExpectedSize;
  }

}
