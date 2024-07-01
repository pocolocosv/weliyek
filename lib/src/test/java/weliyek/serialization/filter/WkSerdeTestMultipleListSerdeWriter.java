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
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionWriter;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionWriterCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollectionWriter;

public class WkSerdeTestMultipleListSerdeWriter
        implements WkSerdeDtreeDynamicCollectionWriter<
                        WkSerdeTestMultipleLists,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkSerdeTestMultipleLists>,
                        WkSerdeTestMultipleListSerdeDef,
                        Short,
                        WkSerdeSignedBigEndianShortWriter,
                        WkSerdeSignedBigEndianShort,
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListSerdeDef,
                        WkSerdeTestPrimitivesGroupListSerdeWriter,
                        WkSerdeDtreeOperationSettings>
{

    final WkSerdeDtreeDynamicCollectionWriterCore<
                        WkSerdeTestMultipleLists,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestMultipleListSerdeWriter,
                        WkSerdeTestMultipleListSerdeDef,
                        Short,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianShortWriter,
                        WkSerdeSignedBigEndianShort,
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListSerdeDef,
                        WkSerdeTestPrimitivesGroupListSerdeWriter,
                        WkSerdeDtreeOperationSettings> operationCore;

  WkSerdeTestMultipleListSerdeWriter(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<WkSerdeTestMultipleLists,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
      writerFieldCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<WkSerdeTestMultipleLists, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListSerdeWriter, WkSerdeTestMultipleListSerdeDef, Short, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, ? extends WkSerdeSignedBigEndianShort, WkSerdeTestPrimitivesGroupList, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListSerdeDef, WkSerdeTestPrimitivesGroupListSerdeWriter, ? extends WkSerdeTestPrimitivesGroupListSerdeDef, ?, WkSerdeDtreeOperationSettings, ? extends WkSerdeTestMultipleListSerdeDef>
      definitionCore) {
    operationCore = new WkSerdeDtreeDynamicCollectionWriterCore<>(
                                index, writerFieldCore, definitionCore, this);
  }

  @Override
  public Optional<WkSerdeDtreeMsgOutputField<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter>>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgOutputField<WkSerdeTestMultipleLists, WkSerdeVariableSizeElementCollection<WkSerdeTestMultipleLists, ?, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupList, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListSerdeDef, WkSerdeTestPrimitivesGroupListSerdeWriter, ?>, WkSerdeVariableSizeElementCollectionWriter<WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListSerdeDef, WkSerdeTestPrimitivesGroupListSerdeWriter>>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public WkSerdeTestMultipleListSerdeDef definition() {
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
  public Optional<WkSerdeDtreeOperationResult<WkSerdeTestMultipleLists>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeMsgOutputField<?,?,?> parentField() {
    return this.operationCore.parentField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkSerdeTestMultipleLists serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<WkSerdeDtreeMsgOutputField<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
