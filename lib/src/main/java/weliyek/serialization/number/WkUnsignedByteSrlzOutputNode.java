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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;

public final class WkUnsignedByteSrlzOutputNode
    implements WkNumberSrlzOutputPacketEncoderFrameLeafNode<
                        Integer,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        WkUnsignedByteSrlzStructNode>
{

  final WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<
                        Integer,
                        WkUnsignedByteSrlzOutputNode,
                        WkUnsignedByteSrlzStructNode> writingCore;

  WkUnsignedByteSrlzOutputNode(
    int index,
    Integer serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<Integer,?,WkUnsignedByteSrlzStructNode,?,?,?> writingfieldCore,
    WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
      Integer,?,WkUnsignedByteSrlzOutputNode,WkUnsignedByteSrlzStructNode> defintionCore) {
    this.writingCore = new WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<>(
                                index,
                                serializable,
                                settings,
                                parentBytestream,
                                writingfieldCore,
                                defintionCore,
                                this);
  }

  @Override
  public WkUnsignedByteSrlzStructNode definition() {
    return this.writingCore.definition();
  }

  @Override
  public WkSzOperationSettings settings() {
    return this.writingCore.settings();
  }

  @Override
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.writingCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
    return this.writingCore.result();
  }

  @Override
  public int index() {
    return this.writingCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<Integer, WkUnsignedByteSrlzStructNode, ?> packetField() {
    return this.writingCore.packetField();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.writingCore.subfields();
  }

  @Override
  public String name() {
    return this.writingCore.name();
  }

  @Override
  public Integer serializable() {
    return this.writingCore.serializable();
  }

  @Override
  public String toString() {
    return this.writingCore.toString();
  }

}
