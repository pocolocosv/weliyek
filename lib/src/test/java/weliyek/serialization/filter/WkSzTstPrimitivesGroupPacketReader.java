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

import weliyek.serialization.WkSerdeDtreeAggregatorReader;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianLongReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianLong;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedByteReader;
import weliyek.serialization.number.WkSerdeSignedByte;
import weliyek.serialization.string.WkSerdeStringDynamicBytesReader;
import weliyek.serialization.string.WkSerdeStringDynamicBytes;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytesReader;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytes;

public class WkSzTstPrimitivesGroupPacketReader
        implements WkSerdeDtreeAggregatorReader<
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkSzTstPrimitivesGroup>,
                        WkSzTstPrimitivesGroupStructDefinition>
{

  final WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader> operationCore;
  final WkSrlzInputPacketSubfieldFrameNodeCore<Byte,
                                         WkSerdeDtreeOperationSettings,
                                         WkSerdeSignedByte,
                                         WkSerdeSignedByteReader,
                                         WkSzTstPrimitivesGroup,
                                         WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> byteReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<Short,
                                         WkSerdeDtreeOperationSettings,
                                         WkSerdeSignedBigEndianShort,
                                         WkSerdeSignedBigEndianShortReader,
                                         WkSzTstPrimitivesGroup,
                                         WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> shortReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<Integer,
                                         WkSerdeDtreeOperationSettings,
                                         WkSerdeSignedBigEndianInteger,
                                         WkSerdeSignedBigEndianIntegerReader,
                                         WkSzTstPrimitivesGroup,
                                         WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> intReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<Long,
                                         WkSerdeDtreeOperationSettings,
                                         WkSerdeSignedBigEndianLong,
                                         WkSerdeSignedBigEndianLongReader,
                                         WkSzTstPrimitivesGroup,
                                         WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> longReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<String,
                                         WkSerdeDtreeOperationSettings,
                                         WkSerdeStringFixedLengthBytes,
                                         WkSerdeStringFixedLengthBytesReader,
                                         WkSzTstPrimitivesGroup,
                                         WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> fixedStrReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<String,
                                         WkSerdeDtreeOperationSettings,
                                         WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
                                         WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                                         WkSzTstPrimitivesGroup,
                                         WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> dynStrReadingSubfield;

  WkSzTstPrimitivesGroupPacketReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataInputComponentCore<
      WkSzTstPrimitivesGroup,?,WkSzTstPrimitivesGroupStructDefinition,?,?,?> deserializingFieldCore,
    WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, ?, ?, ?, WkSzTstPrimitivesGroupStructDefinition>
      definitionCore) {
    this.operationCore = new WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<
                                WkSzTstPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader>(
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
  public WkSzTstPrimitivesGroupStructDefinition definition() {
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
  public Optional<WkSerdeDtreeOperationResult<WkSzTstPrimitivesGroup>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeNodeDataInputComponent<WkSzTstPrimitivesGroup, WkSzTstPrimitivesGroupStructDefinition, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

}
