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
import weliyek.serialization.WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.WkAggregatorSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzOutputNode;
import weliyek.serialization.number.WkSignedBigEndianLongSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianLongSrlzOutputNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzOutputNode;
import weliyek.serialization.number.WkSignedByteSrlzStructNode;
import weliyek.serialization.number.WkSignedByteSrlzOutputNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzStructNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzOutputNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzStructNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzOutputNode;

public class WkSzTstPrimitivesGroupPacketWriter
        implements WkAggregatorSrlzOutputPacketEncoderFrameNode<
                        WkSzTstPrimitivesGroup,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingResultSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition>
{

  final WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<
                        WkSzTstPrimitivesGroup,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> operationCore;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Byte,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedByteSrlzStructNode,
                        WkSignedByteSrlzOutputNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> byteWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Short,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSignedBigEndianShortSrlzOutputNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> shortWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Integer,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedBigEndianIntegerSrlzStructNode,
                        WkSignedBigEndianIntegerSrlzOutputNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> intWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Long,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedBigEndianLongSrlzStructNode,
                        WkSignedBigEndianLongSrlzOutputNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> longWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        String,
                        WkSettingsSrlzPacketOperationData,
                        WkStringWithFixedLengthBytesSrlzStructNode,
                        WkStringWithFixedLengthBytesSrlzOutputNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> fixedStrWritingSubfield;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        String,
                        WkSettingsSrlzPacketOperationData,
                        WkStringWithDynamicBytesSrlzStructNode<Integer, ?, ?, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                        WkStringWithDynamicBytesSrlzOutputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>,
                        WkSzTstPrimitivesGroup,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter> dynStrWritingSubfield;

  WkSzTstPrimitivesGroupPacketWriter(
    int index,
    WkSzTstPrimitivesGroup serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      WkSzTstPrimitivesGroup,?,WkSzTstPrimitivesGroupStructDefinition,?,?,?> serializingFieldCore,
    WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<WkSzTstPrimitivesGroup, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, ? extends WkSzTstPrimitivesGroupStructDefinition>
      definitionCore) {
    this.operationCore = new WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<
                                WkSzTstPrimitivesGroup,
                                WkSettingsSrlzPacketOperationData,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter>(
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
  public WkSzTstPrimitivesGroupStructDefinition definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSettingsSrlzPacketOperationData settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkEncodingResultSrlzPacketOperationData> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<WkSzTstPrimitivesGroup, WkSzTstPrimitivesGroupStructDefinition, ?> packetField() {
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
