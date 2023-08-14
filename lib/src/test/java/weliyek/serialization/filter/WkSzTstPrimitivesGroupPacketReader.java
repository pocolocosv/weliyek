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

import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.WkAggregatorSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianLongSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianLongSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzInputNode;
import weliyek.serialization.number.WkSignedByteSrlzStructNode;
import weliyek.serialization.number.WkSignedByteSrlzInputNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzStructNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzInputNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzStructNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzInputNode;

public class WkSzTstPrimitivesGroupPacketReader
        implements WkAggregatorSrlzInputPacketDecoderFrameNode<
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<WkSzTstPrimitivesGroup>,
                        WkSzTstPrimitivesGroupStructDefinition>
{

  final WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader> operationCore;
  final WkSrlzInputPacketSubfieldFrameNodeCore<Byte,
                                         WkSzOperationSettings,
                                         WkSignedByteSrlzStructNode,
                                         WkSignedByteSrlzInputNode,
                                         WkSzTstPrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> byteReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<Short,
                                         WkSzOperationSettings,
                                         WkSignedBigEndianShortSrlzStructNode,
                                         WkSignedBigEndianShortSrlzInputNode,
                                         WkSzTstPrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> shortReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<Integer,
                                         WkSzOperationSettings,
                                         WkSignedBigEndianIntegerSrlzStructNode,
                                         WkSignedBigEndianIntegerSrlzInputNode,
                                         WkSzTstPrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> intReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<Long,
                                         WkSzOperationSettings,
                                         WkSignedBigEndianLongSrlzStructNode,
                                         WkSignedBigEndianLongSrlzInputNode,
                                         WkSzTstPrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> longReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<String,
                                         WkSzOperationSettings,
                                         WkStringWithFixedLengthBytesSrlzStructNode,
                                         WkStringWithFixedLengthBytesSrlzInputNode,
                                         WkSzTstPrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> fixedStrReadingSubfield;
  final WkSrlzInputPacketSubfieldFrameNodeCore<String,
                                         WkSzOperationSettings,
                                         WkStringWithDynamicBytesSrlzStructNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, ?, ?, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                                         WkStringWithDynamicBytesSrlzInputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>,
                                         WkSzTstPrimitivesGroup,
                                         WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                         WkSzTstPrimitivesGroupStructDefinition,
                                         WkSzTstPrimitivesGroupPacketReader> dynStrReadingSubfield;

  WkSzTstPrimitivesGroupPacketReader(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<
      WkSzTstPrimitivesGroup,?,WkSzTstPrimitivesGroupStructDefinition,?,?,?> deserializingFieldCore,
    WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, ?, ?, ?, WkSzTstPrimitivesGroupStructDefinition>
      definitionCore) {
    this.operationCore = new WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<
                                WkSzTstPrimitivesGroup,
                                WkSzOperationSettings,
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
  public WkSzOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzReadingRuntime<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzReadingResult<WkSzTstPrimitivesGroup>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzInputPacketFieldFrameNode<WkSzTstPrimitivesGroup, WkSzTstPrimitivesGroupStructDefinition, ?> packetField() {
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