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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDTreeVariableSizeByteArrayReader;
import weliyek.util.array.WkSerdeDTreeVariableSizeByteArray;

public class WkStringWithVariableBytesSrlzInputNode
    implements WkStringFromBytesSrlzInputPacketDecoderFrameNode<
                        WkSzVariableLengthOperationSettings,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<String>,
                        WkStringWithVariableBytesSrlzStructNode,
                        WkSerdeDTreeVariableSizeByteArray,
                        WkSerdeDTreeVariableSizeByteArrayReader>
{

  final WkStringFromBytesSrlzInputPacketDecoderFrameNodeSimplifiedCore<
                        WkSzVariableLengthOperationSettings,
                        WkStringWithVariableBytesSrlzInputNode,
                        WkStringWithVariableBytesSrlzStructNode,
                        WkSzVariableLengthOperationSettings,
                        WkSerdeDTreeVariableSizeByteArrayReader,
                        WkSerdeDTreeVariableSizeByteArray> operationCore;

  WkStringWithVariableBytesSrlzInputNode(
    int index,
    WkSzVariableLengthOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<
      String,?,WkStringWithVariableBytesSrlzStructNode,?,?,?> deserializingfieldCore,
    WkStringFromBytesSrlzStructDefinitionFrameNodeSimplifiedCore<
      WkSzVariableLengthOperationSettings,WkStringWithVariableBytesSrlzInputNode,
      WkStringWithVariableBytesSrlzStructNode,?,?,?,WkSzVariableLengthOperationSettings,
      WkSerdeDTreeVariableSizeByteArrayReader,WkSerdeDTreeVariableSizeByteArray,?,?,?,?,?> definitionCore) {
    this.operationCore = new WkStringFromBytesSrlzInputPacketDecoderFrameNodeSimplifiedCore<
                                WkSzVariableLengthOperationSettings,
                                WkStringWithVariableBytesSrlzInputNode,
                                WkStringWithVariableBytesSrlzStructNode,
                                WkSzVariableLengthOperationSettings,
                                WkSerdeDTreeVariableSizeByteArrayReader,
                                WkSerdeDTreeVariableSizeByteArray>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<WkByteArray, WkSerdeDTreeVariableSizeByteArray, WkSerdeDTreeVariableSizeByteArrayReader>
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
  public WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkResultSrlzPacketOperationData<String>> result() {
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
  WkSrlzInputPacketSubfieldFrameNode<WkByteArray, WkSerdeDTreeVariableSizeByteArray, WkSerdeDTreeVariableSizeByteArrayReader>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
