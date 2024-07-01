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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionReader;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionReaderCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollectionReader;

public class WkSerdeTestPrimitivesGroupListSerdeReader
    implements WkSerdeDtreeDynamicCollectionReader<
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkSerdeTestPrimitivesGroupList>,
                        WkSerdeTestPrimitivesGroupListSerdeDef,
                        Integer,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupSerdeDef,
                        WkSerdeTestPrimitivesGroupSerdeReader,
                        WkSerdeDtreeOperationSettingsVariableLength>
{

    final WkSerdeDtreeDynamicCollectionReaderCore<
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListSerdeReader,
                        WkSerdeTestPrimitivesGroupListSerdeDef,
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupSerdeDef,
                        WkSerdeTestPrimitivesGroupSerdeReader,
                        WkSerdeDtreeOperationSettingsVariableLength> readingCore;

    WkSerdeTestPrimitivesGroupListSerdeReader(
      int index,
      WkSerdeDtreeMsgInputFieldCore<?,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamInputBase<?>,?,?,?>
        readerFieldCore,
      WkSerdeDtreeDynamicCollectionDefinitionCore<WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListSerdeReader, WkSerdeTestPrimitivesGroupListSerdeDef, ?, ?, ?, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ? extends WkSerdeSignedLittleEndianInteger, WkSerdeTestPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupSerdeDef, WkSerdeTestPrimitivesGroupSerdeReader, ?, ?, ?, ? extends WkSerdeTestPrimitivesGroupSerdeDef, WkSerdeDtreeOperationSettingsVariableLength, ?, ? extends WkSerdeTestPrimitivesGroupListSerdeDef>
        definitionCore) {
      readingCore = new WkSerdeDtreeDynamicCollectionReaderCore<>(
                            index, readerFieldCore, definitionCore, this);
    }

    @Override
    public
    Optional<WkSerdeDtreeMsgInputField<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerReader>>
    size() {
      return this.readingCore.size();
    }

    @Override
    public
    Optional<WkSerdeDtreeMsgInputField<WkSerdeTestPrimitivesGroupList, WkSerdeVariableSizeElementCollection<WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettingsVariableLength, ?, WkSerdeTestPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupSerdeDef, WkSerdeTestPrimitivesGroupSerdeReader, ?, ?, ?, ?>, WkSerdeVariableSizeElementCollectionReader<WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeTestPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupSerdeDef, WkSerdeTestPrimitivesGroupSerdeReader>>>
    variableSequence() {
      return this.readingCore.variableSequence();
    }

    @Override
    public WkSerdeTestPrimitivesGroupListSerdeDef definition() {
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
    public Optional<WkSerdeDtreeOperationResult<WkSerdeTestPrimitivesGroupList>> result() {
      return this.readingCore.result();
    }

    @Override
    public int index() {
      return this.readingCore.index();
    }

    @Override
    public WkSerdeDtreeMsgInputField<?,?,?> parentField() {
      return this.readingCore.parentField();
    }

    @Override
    public String name() {
      return this.readingCore.name();
    }

    @Override
    public List<WkSerdeDtreeMsgInputField<?, ?, ?>> subfields() {
      return this.readingCore.subfields();
    }

}
