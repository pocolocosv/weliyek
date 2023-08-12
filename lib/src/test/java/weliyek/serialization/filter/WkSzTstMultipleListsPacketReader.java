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
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSzSignedBigEndianShort;
import weliyek.serialization.number.WkSzSignedBigEndianShortReader;
import weliyek.serialization.sequence.WkSzDynamicCollectionPacketReaderOperation;
import weliyek.serialization.sequence.WkSzSimplifiedDynamicCollectionStructDefinition;
import weliyek.serialization.sequence.WkSzSimplifiedDynamicSequencePacketReaderCore;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldDeserializer;

public class WkSzTstMultipleListsPacketReader
        implements WkSzDynamicCollectionPacketReaderOperation<
                        WkSzTstMultipleLists,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<WkSzTstMultipleLists>,
                        WkSzTstMultipleListStructDefinition,
                        Short,
                        WkSzSignedBigEndianShortReader,
                        WkSzSignedBigEndianShort,
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        WkSzTstPrimitivesGroupListPacketReader,
                        WkSzVariableLengthOperationSettings>
{

    final WkSzSimplifiedDynamicSequencePacketReaderCore<
                        WkSzTstMultipleLists,
                        WkSzOperationSettings,
                        WkSzTstMultipleListsPacketReader,
                        WkSzTstMultipleListStructDefinition,
                        Short,
                        WkSzOperationSettings,
                        WkSzSignedBigEndianShortReader,
                        WkSzSignedBigEndianShort,
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        WkSzTstPrimitivesGroupListPacketReader,
                        WkSzVariableLengthOperationSettings>
                        operationCore;

  WkSzTstMultipleListsPacketReader(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<WkSzTstMultipleLists, ?, WkSzTstMultipleListStructDefinition, ?, ?, ?> packetfieldCore,
    WkSzSimplifiedDynamicCollectionStructDefinition<WkSzTstMultipleLists, WkSzOperationSettings, WkSzTstMultipleListsPacketReader, WkSzTstMultipleListStructDefinition, ?, ?, ?, Short, WkSzOperationSettings, WkSzSignedBigEndianShortReader, WkSzSignedBigEndianShort, ?, ?, ?, ?, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition, WkSzTstPrimitivesGroupListPacketReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?, ?>
      definitionCore) {
    this.operationCore = new WkSzSimplifiedDynamicSequencePacketReaderCore<
                                WkSzTstMultipleLists, WkSzOperationSettings, WkSzTstMultipleListsPacketReader, WkSzTstMultipleListStructDefinition, Short, WkSzOperationSettings, WkSzSignedBigEndianShortReader, WkSzSignedBigEndianShort, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition, WkSzTstPrimitivesGroupListPacketReader, WkSzVariableLengthOperationSettings>(
                                    index, settings, parentBytestream, packetfieldCore, definitionCore, this);
  }

  @Override
  public
  WkSzPacketReaderSubfield<Short, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSzPacketReaderSubfield<WkSzTstMultipleLists, VariableSizeCollectionField<WkSzTstMultipleLists, WkSzVariableLengthOperationSettings, ?, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition, WkSzTstPrimitivesGroupListPacketReader, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<WkSzTstMultipleLists, WkSzVariableLengthOperationSettings, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition, WkSzTstPrimitivesGroupListPacketReader>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public WkSzTstMultipleListStructDefinition definition() {
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
  public WkSzPacketReaderField<WkSzTstMultipleLists, WkSzTstMultipleListStructDefinition, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public List<WkSzPacketReaderSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
