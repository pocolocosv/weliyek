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
package weliyek.util.array;

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
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;

public class WkDynamicByteArraySrlzInputNode<
                        ZT extends Number,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,WkSzOperationSettings,?,?,ZXD>,
                        ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,ZXO>>
    implements WkByteArraySrlzInputPacketDecoderFrameNode<
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<WkByteArray>,
                        WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>>,
               WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNode<
                        WkByteArray,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<WkByteArray>,
                        WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT,
                        ZXO,
                        ZXD,
                        WkVariableSizeByteArraySrlzInputNode,
                        WkVariableSizeByteArraySrlzStructNode>
{

  final WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<
                        WkByteArray,
                        WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>,
                        WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT, ZXO, ZXD,
                        WkVariableSizeByteArraySrlzInputNode,
                        WkVariableSizeByteArraySrlzStructNode> operationCore;

  WkDynamicByteArraySrlzInputNode(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<
      WkByteArray,?,WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?> deserializingfieldCore,
    WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
      WkByteArray,WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
      WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>,?,?,ZT,ZXD,ZXO,?,?,?,
      WkVariableSizeByteArraySrlzStructNode,WkVariableSizeByteArraySrlzInputNode,?,?,?,
      ?> definitionCore) {
    this.operationCore = new WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<
                                WkByteArray,
                                WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>,
                                WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                ZT, ZXO, ZXD,
                                WkVariableSizeByteArraySrlzInputNode,
                                WkVariableSizeByteArraySrlzStructNode>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  /*
  public DynamicByteArrayDeserializing(
    int i,
    OperationSettings xs,
    InputBytestream<?> axb,
    DeserializingFieldCore<ByteArrayWrapper, ?, ? extends DynamicByteArray<ZX, ZXO, ?, ?, ZD>, ?, ?, ?> xkc,
    SimplifiedDynamicPrimitiveArrayDefinitionCore<ByteArrayWrapper, DynamicByteArrayDeserializing<ZX, ZXO, ZD>, DynamicByteArraySerialzing<ZY, ZYO, ZD>, ZX, ZXO, ZY, ZYO, ZD, VariableSizeByteArrayDeserializing, VariableSizeByteArraySerializing, VariableSizeByteArray, DynamicByteArray<ZX, ZXO, ZY, ZYO, ZD>> dc) {
    // TODO Auto-generated constructor stub
  }
  */

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD> definition() {
    return this.operationCore.definition();
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
  public Optional<WkSzReadingResult<WkByteArray>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzInputPacketFieldFrameNode<WkByteArray,WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkSrlzInputPacketSubfieldFrameNode<ZT, ZXD, ZXO> size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<WkByteArray, WkVariableSizeByteArraySrlzStructNode, WkVariableSizeByteArraySrlzInputNode>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

}
