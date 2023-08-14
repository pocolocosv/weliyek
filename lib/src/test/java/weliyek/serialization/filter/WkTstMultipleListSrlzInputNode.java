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
package weliyek.serialization.filter;

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
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzInputNode;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldDeserializer;

public class WkTstMultipleListSrlzInputNode
        implements WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                        WkSzTstMultipleLists,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<WkSzTstMultipleLists>,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSignedBigEndianShortSrlzInputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkSzVariableLengthOperationSettings>
{

    final WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                        WkSzTstMultipleLists,
                        WkSzOperationSettings,
                        WkTstMultipleListSrlzInputNode,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSzOperationSettings,
                        WkSignedBigEndianShortSrlzInputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkSzVariableLengthOperationSettings>
                        operationCore;

  WkTstMultipleListSrlzInputNode(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<WkSzTstMultipleLists, ?, WkTstMultipleListSrlzStructNode, ?, ?, ?> packetfieldCore,
    WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<WkSzTstMultipleLists, WkSzOperationSettings, WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzStructNode, ?, ?, ?, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzInputNode, WkSignedBigEndianShortSrlzStructNode, ?, ?, ?, ?, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?, ?>
      definitionCore) {
    this.operationCore = new WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                                WkSzTstMultipleLists, WkSzOperationSettings, WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzStructNode, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzInputNode, WkSignedBigEndianShortSrlzStructNode, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, WkSzVariableLengthOperationSettings>(
                                    index, settings, parentBytestream, packetfieldCore, definitionCore, this);
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<Short, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<WkSzTstMultipleLists, VariableSizeCollectionField<WkSzTstMultipleLists, WkSzVariableLengthOperationSettings, ?, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<WkSzTstMultipleLists, WkSzVariableLengthOperationSettings, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public WkTstMultipleListSrlzStructNode definition() {
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
  public Optional<WkSzReadingResult<WkSzTstMultipleLists>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzInputPacketFieldFrameNode<WkSzTstMultipleLists, WkTstMultipleListSrlzStructNode, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
