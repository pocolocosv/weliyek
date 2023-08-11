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

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.output.WkSzWritingResult;
import weliyek.amat.base.output.WkSzWritingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.base.output.WkSzPacketWriterSubfieldCore;
import weliyek.amat.basic.aggregator.SimplifiedAggregatorCore;
import weliyek.amat.basic.aggregator.SimplifiedAggregatorSerializingCore;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriter;
import weliyek.amat.basic.number.WkSzSignedBigEndianInteger;
import weliyek.amat.basic.number.WkSzSignedBigEndianIntegerWriter;
import weliyek.amat.basic.number.WkSzSignedBigEndianLong;
import weliyek.amat.basic.number.WkSzSignedBigEndianLongWriter;
import weliyek.amat.basic.number.WkSzSignedBigEndianShort;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortWriter;
import weliyek.amat.basic.number.WkSzSignedByte;
import weliyek.amat.basic.number.WkSzSignedByteWriter;
import weliyek.amat.basic.string.StringWithDynamicSizeBytes;
import weliyek.amat.basic.string.StringWithDynamicSizeBytesSerializing;
import weliyek.amat.basic.string.WkSzStringWithFixedLengthBytes;
import weliyek.amat.basic.string.WkSzStringWithFixedLengthBytesWriter;

public class PrimitivesGroupSerializer
        implements WkSzAggregatorWriter<
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        PrimitivesGroupField>
{

  final SimplifiedAggregatorSerializingCore<
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer> operationCore;
  final WkSzPacketWriterSubfieldCore<
                        Byte,
                        WkSzOperationSettings,
                        WkSzSignedByte,
                        WkSzSignedByteWriter,
                        PrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer> byteWritingSubfield;
  final WkSzPacketWriterSubfieldCore<
                        Short,
                        WkSzOperationSettings,
                        WkSzSignedBigEndianShort,
                        WkSzSignedBigEndianShortWriter,
                        PrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer> shortWritingSubfield;
  final WkSzPacketWriterSubfieldCore<
                        Integer,
                        WkSzOperationSettings,
                        WkSzSignedBigEndianInteger,
                        WkSzSignedBigEndianIntegerWriter,
                        PrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer> intWritingSubfield;
  final WkSzPacketWriterSubfieldCore<
                        Long,
                        WkSzOperationSettings,
                        WkSzSignedBigEndianLong,
                        WkSzSignedBigEndianLongWriter,
                        PrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer> longWritingSubfield;
  final WkSzPacketWriterSubfieldCore<
                        String,
                        WkSzOperationSettings,
                        WkSzStringWithFixedLengthBytes,
                        WkSzStringWithFixedLengthBytesWriter,
                        PrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer> fixedStrWritingSubfield;
  final WkSzPacketWriterSubfieldCore<
                        String,
                        WkSzOperationSettings,
                        StringWithDynamicSizeBytes<Integer, ?, ?, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ? extends WkSzSignedBigEndianInteger>,
                        StringWithDynamicSizeBytesSerializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>,
                        PrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer> dynStrWritingSubfield;

  PrimitivesGroupSerializer(
    int index,
    PrimitivesGroup serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      PrimitivesGroup,?,PrimitivesGroupField,?,?,?> serializingFieldCore,
    SimplifiedAggregatorCore<PrimitivesGroup, ?, ?, ?, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, ? extends PrimitivesGroupField>
      definitionCore) {
    this.operationCore = new SimplifiedAggregatorSerializingCore<
                                PrimitivesGroup,
                                WkSzOperationSettings,
                                PrimitivesGroupField,
                                PrimitivesGroupSerializer>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    serializingFieldCore,
                                    definitionCore,
                                    this,
                                    (oc) -> {});
    this.byteWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().byteSubcomponent);
    this.shortWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().shortSubcomponent);
    this.intWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().intSubcomponent);
    this.longWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().longSubcomponent);
    this.fixedStrWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().fixedStrSubcomponent);
    this.dynStrWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().dynStrSubcomponent);
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
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketWriterField<PrimitivesGroup, PrimitivesGroupField, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public PrimitivesGroup serializable() {
    return this.operationCore.serializable();
  }

}
