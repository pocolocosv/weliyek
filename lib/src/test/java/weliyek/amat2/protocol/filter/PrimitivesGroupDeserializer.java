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
package weliyek.amat2.protocol.filter;

import java.util.List;
import java.util.Optional;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.WkSzPacketReaderSubfieldCore;
import weliyek.amat.basic.aggregator.SimplifiedAggregatorCore;
import weliyek.amat.basic.aggregator.SimplifiedAggregatorDeserializingCore;
import weliyek.amat.basic.aggregator.WkSzAggregatorReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianInteger;
import weliyek.amat.basic.number.WkSzSignedBigEndianIntegerReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianLong;
import weliyek.amat.basic.number.WkSzSignedBigEndianLongReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianShort;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortReader;
import weliyek.amat.basic.number.WkSzSignedByte;
import weliyek.amat.basic.number.WkSzSignedByteReader;
import weliyek.amat.basic.string.StringWithDynamicSizeBytes;
import weliyek.amat.basic.string.StringWithDynamicSizeBytesDeserializing;
import weliyek.amat.basic.string.WkSzStringWithFixedLengthBytes;
import weliyek.amat.basic.string.WkSzStringWithFixedLengthBytesReader;
import weliyek.serialization.bytestream.InputBytestream;
import weliyek.serialization.bytestream.InputBytestreamGeneralBase;

public class PrimitivesGroupDeserializer
        implements WkSzAggregatorReader<
                        PrimitivesGroup,
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<PrimitivesGroup>,
                        PrimitivesGroupField>
{

  final SimplifiedAggregatorDeserializingCore<
                        PrimitivesGroup,
                        OperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupDeserializer> operationCore;
  final WkSzPacketReaderSubfieldCore<Byte,
                                         OperationSettings,
                                         WkSzSignedByte,
                                         WkSzSignedByteReader,
                                         PrimitivesGroup,
                                         InputBytestreamGeneralBase<? extends InputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> byteReadingSubfield;
  final WkSzPacketReaderSubfieldCore<Short,
                                         OperationSettings,
                                         WkSzSignedBigEndianShort,
                                         WkSzSignedBigEndianShortReader,
                                         PrimitivesGroup,
                                         InputBytestreamGeneralBase<? extends InputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> shortReadingSubfield;
  final WkSzPacketReaderSubfieldCore<Integer,
                                         OperationSettings,
                                         WkSzSignedBigEndianInteger,
                                         WkSzSignedBigEndianIntegerReader,
                                         PrimitivesGroup,
                                         InputBytestreamGeneralBase<? extends InputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> intReadingSubfield;
  final WkSzPacketReaderSubfieldCore<Long,
                                         OperationSettings,
                                         WkSzSignedBigEndianLong,
                                         WkSzSignedBigEndianLongReader,
                                         PrimitivesGroup,
                                         InputBytestreamGeneralBase<? extends InputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> longReadingSubfield;
  final WkSzPacketReaderSubfieldCore<String,
                                         OperationSettings,
                                         WkSzStringWithFixedLengthBytes,
                                         WkSzStringWithFixedLengthBytesReader,
                                         PrimitivesGroup,
                                         InputBytestreamGeneralBase<? extends InputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> fixedStrReadingSubfield;
  final WkSzPacketReaderSubfieldCore<String,
                                         OperationSettings,
                                         StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ? extends WkSzSignedBigEndianInteger>,
                                         StringWithDynamicSizeBytesDeserializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>,
                                         PrimitivesGroup,
                                         InputBytestreamGeneralBase<? extends InputBytestream>,
                                         PrimitivesGroupField,
                                         PrimitivesGroupDeserializer> dynStrReadingSubfield;

  PrimitivesGroupDeserializer(
    int index,
    OperationSettings settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      PrimitivesGroup,?,PrimitivesGroupField,?,?,?> deserializingFieldCore,
    SimplifiedAggregatorCore<PrimitivesGroup, OperationSettings, PrimitivesGroupField, PrimitivesGroupDeserializer, ?, ?, ?, PrimitivesGroupField>
      definitionCore) {
    this.operationCore = new SimplifiedAggregatorDeserializingCore<
                                PrimitivesGroup,
                                OperationSettings,
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
  public OperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public DeserializingRuntime<InputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<DeserializingResult<PrimitivesGroup>> result() {
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
