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
package weliyek.serialization.number;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;

public final class WkSignedBigEndianShortSrlzInputNode
        implements WkNumberSrlzInputPacketDecoderFrameLeafNode<
                        Short,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<Short>,
                        WkSignedBigEndianShortSrlzStructNode>
{

    final WkSimplifiedNumberSrlzInputPacketDecoderFrameLeafNodeCore<
                        Short,
                        WkSignedBigEndianShortSrlzInputNode,
                        WkSignedBigEndianShortSrlzStructNode> operationCore;

    WkSignedBigEndianShortSrlzInputNode(
      int index,
      WkSzOperationSettings settings,
      WkSzInputBytestreamBase<?> parentBytestream,
      WkSrlzInputPacketFieldFrameNodeCore<
        Short,?,WkSignedBigEndianShortSrlzStructNode,?,?,?> deserializingfieldCore,
      WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
        Short,WkSignedBigEndianShortSrlzInputNode,?,WkSignedBigEndianShortSrlzStructNode> definitionCore) {
      operationCore = new WkSimplifiedNumberSrlzInputPacketDecoderFrameLeafNodeCore<>(
                                index,
                                settings,
                                parentBytestream,
                                deserializingfieldCore,
                                definitionCore,
                                this);
    }

    @Override
    public WkSzOperationSettings settings() {
      return this.operationCore.settings();
    }

    @Override
    public WkSzReadingRuntime<WkSzInputBytestream> dashboard() {
      return this.operationCore.dashboard();
    }

    @Override
    public Optional<WkSzReadingResult<Short>> result() {
      return this.operationCore.result();
    }

    @Override
    public int index() {
      return this.operationCore.index();
    }

    @Override
    public WkSrlzInputPacketFieldFrameNode<Short, WkSignedBigEndianShortSrlzStructNode, ?> packetField() {
      return this.operationCore.packet();
    }

    @Override
    public final List<WkSrlzInputPacketSubfieldFrameNode<?,?,?>> subfields() {
      return this.operationCore.subfields();
    }

    @Override
    public String name() {
      return this.operationCore.name();
    }

    @Override
    public String toString() {
      return this.operationCore.toString();
    }

    @Override
    public WkSignedBigEndianShortSrlzStructNode definition() {
      return this.operationCore.definition();
    }

}