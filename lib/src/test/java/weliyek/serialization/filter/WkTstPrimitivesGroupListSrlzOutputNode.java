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
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionWriter;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionWriterCore;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollectionWriter;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;

public class WkTstPrimitivesGroupListSrlzOutputNode
    implements WkSerdeDtreeDynamicCollectionWriter<
                        WkSzTstPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkSzTstPrimitivesGroupList>,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSerdeDtreeOperationSettings>
{

    final WkSerdeDtreeDynamicCollectionWriterCore<
                        WkSzTstPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSerdeDtreeOperationSettings> writingCore;

    WkTstPrimitivesGroupListSrlzOutputNode(
      int index,
      WkSzTstPrimitivesGroupList serializable,
      WkSerdeDtreeOperationSettings settings,
      WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
      WkSerdeDtreeMsgOutputFieldCore<WkSzTstPrimitivesGroupList, ?, WkTstPrimitivesGroupListSrlzStructNode, ?, ?, ?>
        serializerpacketCore,
      WkSerdeDtreeDynamicCollectionDefinitionCore<WkSzTstPrimitivesGroupList, ?, ?, ?, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ?, WkSzTstPrimitivesGroup, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, ?, ?, WkSerdeDtreeOperationSettings, ?>
        definitionCore) {
      writingCore = new WkSerdeDtreeDynamicCollectionWriterCore<WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSerdeDtreeOperationSettings>(
            index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
    }

    @Override
    public
    Optional<WkSerdeDtreeMsgOutputField<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerWriter>>
    size() {
      return this.writingCore.size();
    }

    @Override
    public
    Optional<WkSerdeDtreeMsgOutputField<WkSzTstPrimitivesGroupList, WkSerdeVariableSizeElementCollection<WkSzTstPrimitivesGroupList, ?, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroup, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, ?>, WkSerdeVariableSizeElementCollectionWriter<WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter>>>
    variableSequence() {
      return this.writingCore.variableSequence();
    }

    @Override
    public WkTstPrimitivesGroupListSrlzStructNode definition() {
      return this.writingCore.definition();
    }

    @Override
    public WkSerdeDtreeOperationSettings settings() {
      return this.writingCore.settings();
    }

    @Override
    public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
      return this.writingCore.dashboard();
    }

    @Override
    public Optional<WkSerdeDtreeOperationResult<WkSzTstPrimitivesGroupList>> result() {
      return this.writingCore.result();
    }

    @Override
    public int index() {
      return this.writingCore.index();
    }

    @Override
    public WkSerdeDtreeMsgOutputField<WkSzTstPrimitivesGroupList, WkTstPrimitivesGroupListSrlzStructNode, ?> packetField() {
      return this.writingCore.packetField();
    }

    @Override
    public String name() {
      return this.writingCore.name();
    }

    @Override
    public WkSzTstPrimitivesGroupList serializable() {
      return this.writingCore.serializable();
    }

    @Override
    public List<WkSrlzOutputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
      return this.writingCore.subfields();
    }

}
