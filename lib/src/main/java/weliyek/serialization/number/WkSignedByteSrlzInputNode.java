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
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;

public final class WkSignedByteSrlzInputNode
        implements WkNumberSrlzInputPacketDecoderFrameLeafNode<
                        Byte,
                        WkSettingsSrlzPacketOperationData,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkDecodingResultSrlzPacketOperationData<Byte>,
                        WkSignedByteSrlzStructNode>
{

    final WkSimplifiedNumberSrlzInputPacketDecoderFrameLeafNodeCore<
                        Byte,
                        WkSignedByteSrlzInputNode,
                        WkSignedByteSrlzStructNode> operationCore;

    WkSignedByteSrlzInputNode(
      int index,
      WkSettingsSrlzPacketOperationData settings,
      WkSzInputBytestreamBase<?> parentBytestream,
      WkSrlzInputPacketFieldFrameNodeCore<Byte,?,WkSignedByteSrlzStructNode,?,?,?> readingfieldCore,
      WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
        Byte,WkSignedByteSrlzInputNode,?,WkSignedByteSrlzStructNode> definitionCore) {
      operationCore = new WkSimplifiedNumberSrlzInputPacketDecoderFrameLeafNodeCore<
                            Byte, WkSignedByteSrlzInputNode, WkSignedByteSrlzStructNode>(
                                index,
                                settings,
                                parentBytestream,
                                readingfieldCore,
                                definitionCore,
                                this);
    }

    @Override
    public WkSettingsSrlzPacketOperationData settings() {
      return this.operationCore.settings();
    }

    @Override
    public WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream> dashboard() {
      return this.operationCore.getRuntimeControl().asRuntime();
    }

    @Override
    public Optional<WkDecodingResultSrlzPacketOperationData<Byte>> result() {
      return this.operationCore.result();
    }

    @Override
    public int index() {
      return this.operationCore.index();
    }

    @Override
    public WkSrlzInputPacketFieldFrameNode<Byte, WkSignedByteSrlzStructNode, ?> packetField() {
      return this.operationCore.packetField();
    }

    @Override
    public List<WkSrlzInputPacketSubfieldFrameNode<?,?,?>> subfields() {
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
    public WkSignedByteSrlzStructNode definition() {
      return this.operationCore.definition();
    }

}
