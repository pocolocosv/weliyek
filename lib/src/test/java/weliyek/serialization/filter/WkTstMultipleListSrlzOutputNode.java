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

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
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
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkSzTstMultipleLists>,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSerdeSignedBigEndianShortWriter,
                        WkSerdeSignedBigEndianShort,
                        WkSzTstPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSerdeDtreeOperationSettings>
{

    final WkSerdeDtreeDynamicCollectionWriterCore<
                        WkSzTstMultipleLists,
                        WkSerdeDtreeOperationSettings,
                        WkTstMultipleListSrlzOutputNode,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianShortWriter,
                        WkSerdeSignedBigEndianShort,
                        WkSzTstPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSerdeDtreeOperationSettings> operationCore;

  WkTstMultipleListSrlzOutputNode(
    int index,
    WkSzTstMultipleLists serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<WkSzTstMultipleLists, ?, WkTstMultipleListSrlzStructNode, ?, ?, ?>
      serializerpacketCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<WkSzTstMultipleLists, ?, ?, ?, WkSerdeDtreeOperationSettings, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, ?, WkSzTstPrimitivesGroupList, ?, ?, ?, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, ?, ?, WkSerdeDtreeOperationSettings, ?>
      definitionCore) {
    operationCore = new WkSerdeDtreeDynamicCollectionWriterCore<
                            WkSzTstMultipleLists, WkSerdeDtreeOperationSettings, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, WkSerdeDtreeOperationSettings>(
                                index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
  }

  @Override
  public Optional<WkSerdeDtreeNodeDataOutputComponent<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter>>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSerdeDtreeNodeDataOutputComponent<WkSzTstMultipleLists, WkSerdeVariableSizeElementCollection<WkSzTstMultipleLists, ?, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupList, ?, ?, ?, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, ?>, WkSerdeVariableSizeElementCollectionWriter<WkSzTstMultipleLists, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode>>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public WkTstMultipleListSrlzStructNode definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkSzTstMultipleLists>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeNodeDataOutputComponent<WkSzTstMultipleLists, WkTstMultipleListSrlzStructNode, ?> packetField() {
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
