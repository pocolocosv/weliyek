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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionWriter;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionWriterCore;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollectionWriter;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;

public class WkTstMultipleListSrlzOutputNode
        implements WkSerdeDtreeDynamicCollectionWriter<
                        WkSzTstMultipleLists,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<WkSzTstMultipleLists>,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSerdeSignedBigEndianShortWriter,
                        WkSerdeSignedBigEndianShort,
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSettingsSrlzPacketOperationData>
{

    final WkSerdeDtreeDynamicCollectionWriterCore<
                        WkSzTstMultipleLists,
                        WkSettingsSrlzPacketOperationData,
                        WkTstMultipleListSrlzOutputNode,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeSignedBigEndianShortWriter,
                        WkSerdeSignedBigEndianShort,
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSettingsSrlzPacketOperationData> operationCore;

  WkTstMultipleListSrlzOutputNode(
    int index,
    WkSzTstMultipleLists serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<WkSzTstMultipleLists, ?, WkTstMultipleListSrlzStructNode, ?, ?, ?>
      serializerpacketCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<WkSzTstMultipleLists, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, ?, WkSzTstPrimitivesGroupList, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, ?, ?, WkSettingsSrlzPacketOperationData, ?>
      definitionCore) {
    operationCore = new WkSerdeDtreeDynamicCollectionWriterCore<
                            WkSzTstMultipleLists, WkSettingsSrlzPacketOperationData, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, WkSettingsSrlzPacketOperationData>(
                                index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
  }

  @Override
  public Optional<WkSrlzOutputPacketFieldFrameNode<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter>>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSrlzOutputPacketFieldFrameNode<WkSzTstMultipleLists, WkSerdeVariableSizeElementCollection<WkSzTstMultipleLists, ?, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupList, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, ?>, WkSerdeVariableSizeElementCollectionWriter<WkSzTstMultipleLists, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode>>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public WkTstMultipleListSrlzStructNode definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSettingsSrlzPacketOperationData settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkResultSrlzPacketOperationData<WkSzTstMultipleLists>> result() {
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
