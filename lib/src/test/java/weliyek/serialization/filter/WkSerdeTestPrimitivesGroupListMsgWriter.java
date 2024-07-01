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

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionWriter;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionWriterCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollectionWriter;

public class WkSerdeTestPrimitivesGroupListMsgWriter
    implements WkSerdeDtreeDynamicCollectionWriter<
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkSerdeTestPrimitivesGroupList>,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        Integer,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgWriter,
                        WkSerdeDtreeOperationSettings>
{

    final WkSerdeDtreeDynamicCollectionWriterCore<
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListMsgWriter,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgWriter,
                        WkSerdeDtreeOperationSettings> writingCore;

    WkSerdeTestPrimitivesGroupListMsgWriter(
      int index,
      WkSerdeDtreeMsgOutputFieldCore<WkSerdeTestPrimitivesGroupList,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
        writerFieldCore,
      WkSerdeDtreeDynamicCollectionDefinitionCore<WkSerdeTestPrimitivesGroupList, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListMsgWriter, WkSerdeTestPrimitivesGroupListStructDefinition, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ? extends WkSerdeSignedLittleEndianInteger, WkSerdeTestPrimitivesGroup, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeTestPrimitivesGroupMsgWriter, ? extends WkSerdeTestPrimitivesGroupStructDefinition, ?, WkSerdeDtreeOperationSettings, ? extends WkSerdeTestPrimitivesGroupListStructDefinition>
        definitionCore) {
      writingCore = new WkSerdeDtreeDynamicCollectionWriterCore<>(
                            index, writerFieldCore, definitionCore, this);
    }

    @Override
    public
    Optional<WkSerdeDtreeMsgOutputField<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerWriter>>
    size() {
      return this.writingCore.size();
    }

    @Override
    public
    Optional<WkSerdeDtreeMsgOutputField<WkSerdeTestPrimitivesGroupList, WkSerdeVariableSizeElementCollection<WkSerdeTestPrimitivesGroupList, ?, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroup, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeTestPrimitivesGroupMsgWriter, ?>, WkSerdeVariableSizeElementCollectionWriter<WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeTestPrimitivesGroupMsgWriter>>>
    variableSequence() {
      return this.writingCore.variableSequence();
    }

    @Override
    public WkSerdeTestPrimitivesGroupListStructDefinition definition() {
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
    public Optional<WkSerdeDtreeOperationResult<WkSerdeTestPrimitivesGroupList>> result() {
      return this.writingCore.result();
    }

    @Override
    public int index() {
      return this.writingCore.index();
    }

    @Override
    public WkSerdeDtreeMsgOutputField<?,?,?> parentField() {
      return this.writingCore.parentField();
    }

    @Override
    public String name() {
      return this.writingCore.name();
    }

    @Override
    public WkSerdeTestPrimitivesGroupList serializable() {
      return this.writingCore.serializable();
    }

    @Override
    public List<WkSerdeDtreeMsgOutputField<?, ?, ?>> subfields() {
      return this.writingCore.subfields();
    }

}
