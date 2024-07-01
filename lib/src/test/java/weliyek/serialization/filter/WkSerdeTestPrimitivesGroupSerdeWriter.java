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

import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriter;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianLong;
import weliyek.serialization.number.WkSerdeSignedBigEndianLongWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSerdeSignedByte;
import weliyek.serialization.number.WkSerdeSignedByteWriter;
import weliyek.serialization.string.WkSerdeStringDynamicBytes;
import weliyek.serialization.string.WkSerdeStringDynamicBytesWriter;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytes;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytesWriter;

public class WkSerdeTestPrimitivesGroupSerdeWriter
        implements WkSerdeDtreeAggregatorMsgWriter<
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkSerdeTestPrimitivesGroup>,
                        WkSerdeTestPrimitivesGroupSerdeDef>
{

  final WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupSerdeDef,
                        WkSerdeTestPrimitivesGroupSerdeWriter> operationCore;

  WkSerdeTestPrimitivesGroupSerdeWriter(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<WkSerdeTestPrimitivesGroup,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
      writerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkSerdeTestPrimitivesGroup, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupSerdeDef, WkSerdeTestPrimitivesGroupSerdeWriter, ? extends WkSerdeTestPrimitivesGroupSerdeDef>
      definitionCore) {
    this.operationCore = new WkSerdeDtreeAggregatorMsgWriterCoreSimplified<>(
                                 index, writerFieldCore, definitionCore, this);
  }

  public Optional<WkSerdeDtreeMsgOutputField<Byte, WkSerdeSignedByte, WkSerdeSignedByteWriter>> singleByte() {
    return Optional.ofNullable(operationCore.getSubfieldpacketFor(definition().byteSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgOutputField<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter>> singleShort() {
    return Optional.ofNullable(operationCore.getSubfieldpacketFor(definition().shortSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgOutputField<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>> singleInt() {
    return Optional.ofNullable(operationCore.getSubfieldpacketFor(definition().intSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgOutputField<Long, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongWriter>> singleLong() {
    return Optional.ofNullable(operationCore.getSubfieldpacketFor(definition().longSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgOutputField<String, WkSerdeStringFixedLengthBytes, WkSerdeStringFixedLengthBytesWriter>> fixedString() {
    return Optional.ofNullable(operationCore.getSubfieldpacketFor(definition().fixedStrSubcomponent).asPacket());
  }

  public Optional<WkSerdeDtreeMsgOutputField<String, WkSerdeStringDynamicBytes<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeStringDynamicBytesWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>>> dynamicString() {
    return Optional.ofNullable(operationCore.getSubfieldpacketFor(definition().dynStrSubcomponent).asPacket());
  }

  @Override
  public WkSerdeTestPrimitivesGroupSerdeDef definition() {
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
  public Optional<WkSerdeDtreeOperationResult<WkSerdeTestPrimitivesGroup>> result() {
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
  public List<WkSerdeDtreeMsgOutputField<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkSerdeTestPrimitivesGroup serializable() {
    return this.operationCore.serializable();
  }

}
