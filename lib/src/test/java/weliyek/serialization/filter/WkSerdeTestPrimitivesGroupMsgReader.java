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

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReader;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianLong;
import weliyek.serialization.number.WkSerdeSignedBigEndianLongReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedByte;
import weliyek.serialization.number.WkSerdeSignedByteReader;
import weliyek.serialization.string.WkSerdeStringDynamicBytes;
import weliyek.serialization.string.WkSerdeStringDynamicBytesReader;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytes;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytesReader;

public class WkSerdeTestPrimitivesGroupMsgReader
        implements WkSerdeDtreeAggregatorMsgReader<
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkSerdeTestPrimitivesGroup>,
                        WkSerdeTestPrimitivesGroupStructDefinition>
{

  final WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgReader> operationCore;

  WkSerdeTestPrimitivesGroupMsgReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkSerdeTestPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeTestPrimitivesGroupMsgReader, ?, ?, ?, ? extends WkSerdeTestPrimitivesGroupStructDefinition> definitionCore) {
    this.operationCore = new WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
                                WkSerdeTestPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeTestPrimitivesGroupStructDefinition,
                                WkSerdeTestPrimitivesGroupMsgReader>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    readerFieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public WkSerdeTestPrimitivesGroupStructDefinition definition() {
    return this.operationCore.definition();
  }

  public Optional<WkSerdeDtreeMsgInputField<Byte, WkSerdeSignedByte, WkSerdeSignedByteReader>> singleByte() {
    return Optional.ofNullable(this.operationCore.getSubfieldpacketFor(definition().byteSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgInputField<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader>> singleShort() {
    return Optional.ofNullable(this.operationCore.getSubfieldpacketFor(definition().shortSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgInputField<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>> singleInt() {
    return Optional.ofNullable(this.operationCore.getSubfieldpacketFor(definition().intSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgInputField<Long, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongReader>> singleLong() {
    return Optional.ofNullable(this.operationCore.getSubfieldpacketFor(definition().longSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgInputField<String, WkSerdeStringFixedLengthBytes, WkSerdeStringFixedLengthBytesReader>> fixedString() {
    return Optional.ofNullable(this.operationCore.getSubfieldpacketFor(definition().fixedStrSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgInputField<String, WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>>> dynamicString() {
    return Optional.ofNullable(this.operationCore.getSubfieldpacketFor(definition().dynStrSubcomponent).asPacket());
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
  public Optional<WkSerdeDtreeOperationResult<WkSerdeTestPrimitivesGroup>> result() {
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
  public List<WkSerdeDtreeMsgInputField<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

}
