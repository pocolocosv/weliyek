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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzOutputNode;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldSerializer;

public class WkTstMultipleListSrlzOutputNode
        implements WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                        WkSzTstMultipleLists,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSignedBigEndianShortSrlzOutputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSzOperationSettings>
{

    final WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<
                        WkSzTstMultipleLists,
                        WkSzOperationSettings,
                        WkTstMultipleListSrlzOutputNode,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSzOperationSettings,
                        WkSignedBigEndianShortSrlzOutputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSzOperationSettings> operationCore;

  WkTstMultipleListSrlzOutputNode(
    int index,
    WkSzTstMultipleLists serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<WkSzTstMultipleLists, ?, WkTstMultipleListSrlzStructNode, ?, ?, ?>
      serializerpacketCore,
    WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<WkSzTstMultipleLists, ?, ?, ?, WkSzOperationSettings, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianShortSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode, ?, WkSzTstPrimitivesGroupList, ?, ?, ?, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, ?, ?, WkSzOperationSettings, ?>
      definitionCore) {
    operationCore = new WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<
                            WkSzTstMultipleLists, WkSzOperationSettings, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, WkSzOperationSettings>(
                                index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
  }

  @Override
  public WkSrlzOutputPacketSubfieldFrameNode<Short, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSrlzOutputPacketSubfieldFrameNode<WkSzTstMultipleLists, VariableSizeCollectionField<WkSzTstMultipleLists, ?, WkSzOperationSettings, WkSzTstPrimitivesGroupList, ?, ?, ?, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, ?>, VariableSizeCollectionFieldSerializer<WkSzTstMultipleLists, WkSzOperationSettings, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode>>
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
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<WkSzTstMultipleLists, WkTstMultipleListSrlzStructNode, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkSzTstMultipleLists serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
