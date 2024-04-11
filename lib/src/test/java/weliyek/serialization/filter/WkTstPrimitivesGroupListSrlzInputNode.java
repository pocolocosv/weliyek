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

import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionReader;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionReaderCore;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollectionReader;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;

public class WkTstPrimitivesGroupListSrlzInputNode
    implements WkSerdeDtreeDynamicCollectionReader<
                        WkSzTstPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkSzTstPrimitivesGroupList>,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSerdeDtreeOperationSettingsVariableLength>
{

    final WkSerdeDtreeDynamicCollectionReaderCore<
                        WkSzTstPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSerdeDtreeOperationSettingsVariableLength> readingCore;

    WkTstPrimitivesGroupListSrlzInputNode(
      int index,
      WkSerdeDtreeOperationSettings settings,
      WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
      WkSerdeDtreeNodeDataInputComponentCore<WkSzTstPrimitivesGroupList, ?, WkTstPrimitivesGroupListSrlzStructNode, ?, ?, ?>
        packetfieldCore,
      WkSerdeDtreeDynamicCollectionDefinitionCore<WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, ?, ?, ?, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ?, WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, ?, ?, ?, ?, WkSerdeDtreeOperationSettingsVariableLength, ?, ?>
        definitionCore) {
      readingCore = new WkSerdeDtreeDynamicCollectionReaderCore<WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSerdeDtreeOperationSettingsVariableLength>(
          index, settings, parentBytestream, packetfieldCore, definitionCore, this);
    }

    @Override
    public
    Optional<WkSerdeDtreeNodeDataInputComponent<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerReader>>
    size() {
      return this.readingCore.size();
    }

    @Override
    public
    Optional<WkSerdeDtreeNodeDataInputComponent<WkSzTstPrimitivesGroupList, WkSerdeVariableSizeElementCollection<WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettingsVariableLength, ?, WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, ?, ?, ?, ?>, WkSerdeVariableSizeElementCollectionReader<WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettingsVariableLength, WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader>>>
    variableSequence() {
      return this.readingCore.variableSequence();
    }

    @Override
    public WkTstPrimitivesGroupListSrlzStructNode definition() {
      return this.readingCore.definition();
    }

    @Override
    public WkSerdeDtreeOperationSettings settings() {
      return this.readingCore.settings();
    }

    @Override
    public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
      return this.readingCore.dashboard();
    }

    @Override
    public Optional<WkSerdeDtreeOperationResult<WkSzTstPrimitivesGroupList>> result() {
      return this.readingCore.result();
    }

    @Override
    public int index() {
      return this.readingCore.index();
    }

    @Override
    public WkSerdeDtreeNodeDataInputComponent<WkSzTstPrimitivesGroupList, WkTstPrimitivesGroupListSrlzStructNode, ?> packetField() {
      return this.readingCore.packetField();
    }

    @Override
    public String name() {
      return this.readingCore.name();
    }

    @Override
    public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
      return this.readingCore.subfields();
    }

}
