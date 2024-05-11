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
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianLongWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianLong;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedByteWriter;
import weliyek.serialization.number.WkSerdeSignedByte;
import weliyek.serialization.string.WkSerdeStringDynamicBytesWriter;
import weliyek.serialization.string.WkSerdeStringDynamicBytes;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytesWriter;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytes;

public class WkSzTstPrimitivesGroupPacketWriter
        implements WkSerdeDtreeAggregatorMsgWriter<
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkSzTstPrimitivesGroup>,
                        WkSzTstPrimitivesGroupStructDefinition>
{

  final WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> operationCore;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Byte,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedByte,
                        WkSerdeSignedByteWriter,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> byteWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Short,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianShort,
                        WkSerdeSignedBigEndianShortWriter,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> shortWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> intWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianLong,
                        WkSerdeSignedBigEndianLongWriter,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> longWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        String,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringFixedLengthBytes,
                        WkSerdeStringFixedLengthBytesWriter,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> fixedStrWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        String,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringDynamicBytes<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                        WkSerdeStringDynamicBytesWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> dynStrWritingSubfield;

  WkSzTstPrimitivesGroupPacketWriter(
    int index,
    WkSzTstPrimitivesGroup serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<
      WkSzTstPrimitivesGroup,?,WkSzTstPrimitivesGroupStructDefinition,?,?,?> serializingFieldCore,
    WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<WkSzTstPrimitivesGroup, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, ? extends WkSzTstPrimitivesGroupStructDefinition>
      definitionCore) {
    this.operationCore = new WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<
                                WkSzTstPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    serializingFieldCore,
                                    definitionCore,
                                    this);
    this.byteWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().byteSubcomponent);
    this.shortWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().shortSubcomponent);
    this.intWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().intSubcomponent);
    this.longWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().longSubcomponent);
    this.fixedStrWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().fixedStrSubcomponent);
    this.dynStrWritingSubfield = operationCore.getSubfieldpacketFor(definitionCore.definition().dynStrSubcomponent);
  }

  @Override
  public WkSzTstPrimitivesGroupStructDefinition definition() {
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
  public Optional<WkSerdeDtreeOperationResult<WkSzTstPrimitivesGroup>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeMsgOutputField<WkSzTstPrimitivesGroup, WkSzTstPrimitivesGroupStructDefinition, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkSzTstPrimitivesGroup serializable() {
    return this.operationCore.serializable();
  }

}
