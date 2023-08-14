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
package weliyek.serialization.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkVariableSizeByteArraySrlzStructNode;
import weliyek.util.array.WkVariableSizeByteArraySrlzInputNode;

public class WkStringWithVariableBytesSrlzInputNode
    implements WkStringFromBytesSrlzInputPacketDecoderFrameNode<
                        WkSzVariableLengthOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<String>,
                        WkStringWithVariableBytesSrlzStructNode,
                        WkVariableSizeByteArraySrlzStructNode,
                        WkVariableSizeByteArraySrlzInputNode>
{

  final SimplifiedStringFromBytesReadingCore<
                        WkSzVariableLengthOperationSettings,
                        WkStringWithVariableBytesSrlzInputNode,
                        WkStringWithVariableBytesSrlzStructNode,
                        WkSzVariableLengthOperationSettings,
                        WkVariableSizeByteArraySrlzInputNode,
                        WkVariableSizeByteArraySrlzStructNode> operationCore;

  WkStringWithVariableBytesSrlzInputNode(
    int index,
    WkSzVariableLengthOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<
      String,?,WkStringWithVariableBytesSrlzStructNode,?,?,?> deserializingfieldCore,
    SimplifiedStringFromBytesCore<
      WkSzVariableLengthOperationSettings,WkStringWithVariableBytesSrlzInputNode,
      WkStringWithVariableBytesSrlzStructNode,?,?,?,WkSzVariableLengthOperationSettings,
      WkVariableSizeByteArraySrlzInputNode,WkVariableSizeByteArraySrlzStructNode,?,?,?,?,?> definitionCore) {
    this.operationCore = new SimplifiedStringFromBytesReadingCore<
                                WkSzVariableLengthOperationSettings,
                                WkStringWithVariableBytesSrlzInputNode,
                                WkStringWithVariableBytesSrlzStructNode,
                                WkSzVariableLengthOperationSettings,
                                WkVariableSizeByteArraySrlzInputNode,
                                WkVariableSizeByteArraySrlzStructNode>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<WkByteArray, WkVariableSizeByteArraySrlzStructNode, WkVariableSizeByteArraySrlzInputNode>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkStringWithVariableBytesSrlzStructNode definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzVariableLengthOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzReadingRuntime<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzReadingResult<String>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzInputPacketFieldFrameNode<String, WkStringWithVariableBytesSrlzStructNode, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<WkByteArray, WkVariableSizeByteArraySrlzStructNode, WkVariableSizeByteArraySrlzInputNode>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}