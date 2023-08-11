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

import weliyek.serialization.SimplifiedAggregatorCore;
import weliyek.serialization.SimplifiedAggregatorDeserializingCore;
import weliyek.serialization.WkSzAggregatorReader;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzPacketReaderSubfieldCore;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.number.WkSzSignedBigEndianInteger;
import weliyek.serialization.number.WkSzSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSzSignedBigEndianLong;
import weliyek.serialization.number.WkSzSignedBigEndianLongReader;
import weliyek.serialization.number.WkSzSignedBigEndianShort;
import weliyek.serialization.number.WkSzSignedBigEndianShortReader;
import weliyek.serialization.number.WkSzSignedByte;
import weliyek.serialization.number.WkSzSignedByteReader;
import weliyek.serialization.string.StringWithDynamicSizeBytes;
import weliyek.serialization.string.StringWithDynamicSizeBytesDeserializing;
import weliyek.serialization.string.WkSzStringWithFixedLengthBytes;
import weliyek.serialization.string.WkSzStringWithFixedLengthBytesReader;

public class PrimitivesGroupDeserializer
        implements WkSzAggregatorReader<
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<PrimitivesGroup>,
                        PrimitivesGroupField>
{

  final SimplifiedAggregatorDeserializingCore<
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupDeserializer> operationCore;
  final WkSzPacketReaderSubfieldCore<Byte,
                                         WkSzOperationSettings,
                                         WkSzSignedByte,
                                         WkSzSignedByteReader,
                                         PrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> byteReadingSubfield;
  final WkSzPacketReaderSubfieldCore<Short,
                                         WkSzOperationSettings,
                                         WkSzSignedBigEndianShort,
                                         WkSzSignedBigEndianShortReader,
                                         PrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> shortReadingSubfield;
  final WkSzPacketReaderSubfieldCore<Integer,
                                         WkSzOperationSettings,
                                         WkSzSignedBigEndianInteger,
                                         WkSzSignedBigEndianIntegerReader,
                                         PrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> intReadingSubfield;
  final WkSzPacketReaderSubfieldCore<Long,
                                         WkSzOperationSettings,
                                         WkSzSignedBigEndianLong,
                                         WkSzSignedBigEndianLongReader,
                                         PrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> longReadingSubfield;
  final WkSzPacketReaderSubfieldCore<String,
                                         WkSzOperationSettings,
                                         WkSzStringWithFixedLengthBytes,
                                         WkSzStringWithFixedLengthBytesReader,
                                         PrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> fixedStrReadingSubfield;
  final WkSzPacketReaderSubfieldCore<String,
                                         WkSzOperationSettings,
                                         StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ? extends WkSzSignedBigEndianInteger>,
                                         StringWithDynamicSizeBytesDeserializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>,
                                         PrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> dynStrReadingSubfield;

  PrimitivesGroupDeserializer(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      PrimitivesGroup,?,PrimitivesGroupField,?,?,?> deserializingFieldCore,
    SimplifiedAggregatorCore<PrimitivesGroup, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupDeserializer, ?, ?, ?, PrimitivesGroupField>
      definitionCore) {
    this.operationCore = new SimplifiedAggregatorDeserializingCore<
                                PrimitivesGroup,
                                WkSzOperationSettings,
                                PrimitivesGroupField,
                                PrimitivesGroupDeserializer>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingFieldCore,
                                    definitionCore,
                                    this);
    this.byteReadingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().byteSubcomponent);
    this.shortReadingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().shortSubcomponent);
    this.intReadingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().intSubcomponent);
    this.longReadingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().longSubcomponent);
    this.fixedStrReadingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().fixedStrSubcomponent);
    this.dynStrReadingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().dynStrSubcomponent);
  }

  @Override
  public PrimitivesGroupField definition() {
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
  public Optional<WkSzReadingResult<PrimitivesGroup>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketReaderField<PrimitivesGroup, PrimitivesGroupField, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketReaderSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

}
