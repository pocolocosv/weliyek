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
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionReader;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionReaderCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollectionReader;

public class WkSerdeTestMultipleListMsgReader
        implements WkSerdeDtreeDynamicCollectionReader<
                        WkSerdeTestMultipleLists,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkSerdeTestMultipleLists>,
                        WkSerdeTestMultipleListStructDefinition,
                        Short,
                        WkSerdeSignedBigEndianShortReader,
                        WkSerdeSignedBigEndianShort,
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        WkSerdeTestPrimitivesGroupListMsgReader,
                        WkSerdeDtreeOperationSettingsVariableLength>
{

    final WkSerdeDtreeDynamicCollectionReaderCore<
                        WkSerdeTestMultipleLists,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestMultipleListMsgReader,
                        WkSerdeTestMultipleListStructDefinition,
                        Short,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianShortReader,
                        WkSerdeSignedBigEndianShort,
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        WkSerdeTestPrimitivesGroupListMsgReader,
                        WkSerdeDtreeOperationSettingsVariableLength>
                        operationCore;

  WkSerdeTestMultipleListMsgReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListMsgReader, WkSerdeTestMultipleListStructDefinition, ?, ?, ?, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortReader, WkSerdeSignedBigEndianShort, ?, ?, ?, ? extends WkSerdeSignedBigEndianShort, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeTestPrimitivesGroupListMsgReader, ?, ?, ?, ? extends WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeDtreeOperationSettingsVariableLength, ?, ? extends WkSerdeTestMultipleListStructDefinition> definitionCore) {
    this.operationCore = new WkSerdeDtreeDynamicCollectionReaderCore<
                                WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListMsgReader, WkSerdeTestMultipleListStructDefinition, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortReader, WkSerdeSignedBigEndianShort, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeTestPrimitivesGroupListMsgReader, WkSerdeDtreeOperationSettingsVariableLength>(
                                    index, settings, parentBytestream, readerFieldCore, definitionCore, this);
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader>>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<WkSerdeTestMultipleLists, WkSerdeVariableSizeElementCollection<WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettingsVariableLength, ?, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeTestPrimitivesGroupListMsgReader, ?, ?, ?, ?>, WkSerdeVariableSizeElementCollectionReader<WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeTestPrimitivesGroupListMsgReader>>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public WkSerdeTestMultipleListStructDefinition definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkSerdeTestMultipleLists>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeMsgInputField<?,?,?> parentField() {
    return this.operationCore.parentField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
