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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkAggregatorSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzOutputNode;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianLongSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianLongSrlzOutputNode;
import weliyek.serialization.number.WkSignedBigEndianLongSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzOutputNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedByteSrlzInputNode;
import weliyek.serialization.number.WkSignedByteSrlzOutputNode;
import weliyek.serialization.number.WkSignedByteSrlzStructNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzInputNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzOutputNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzStructNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzInputNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzOutputNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzStructNode;

public class WkSzTstPrimitivesGroupStructDefinition
        implements WkAggregatorSrlzStructDefinitionFrameNode<
                        WkSzTstPrimitivesGroup>
{

  final WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<
                        WkSzTstPrimitivesGroup,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition> definitionCore;

  final WkSrlzStructSubcomponentFrameNodeCore<Byte,
                                WkSettingsSrlzPacketOperationData,
                                WkSignedByteSrlzStructNode,
                                WkSignedByteSrlzInputNode,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkSignedByteSrlzStructNode,
                                WkSignedByteSrlzOutputNode,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSignedByteSrlzStructNode,
                                WkSzTstPrimitivesGroupStructDefinition> byteSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Short,
                                WkSettingsSrlzPacketOperationData,
                                WkSignedBigEndianShortSrlzStructNode,
                                WkSignedBigEndianShortSrlzInputNode,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkSignedBigEndianShortSrlzStructNode,
                                WkSignedBigEndianShortSrlzOutputNode,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSignedBigEndianShortSrlzStructNode,
                                WkSzTstPrimitivesGroupStructDefinition> shortSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Integer,
                                WkSettingsSrlzPacketOperationData,
                                WkSignedBigEndianIntegerSrlzStructNode,
                                WkSignedBigEndianIntegerSrlzInputNode,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkSignedBigEndianIntegerSrlzStructNode,
                                WkSignedBigEndianIntegerSrlzOutputNode,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSignedBigEndianIntegerSrlzStructNode,
                                WkSzTstPrimitivesGroupStructDefinition> intSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Long,
                                WkSettingsSrlzPacketOperationData,
                                WkSignedBigEndianLongSrlzStructNode,
                                WkSignedBigEndianLongSrlzInputNode,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkSignedBigEndianLongSrlzStructNode,
                                WkSignedBigEndianLongSrlzOutputNode,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSignedBigEndianLongSrlzStructNode,
                                WkSzTstPrimitivesGroupStructDefinition> longSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<String,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithFixedLengthBytesSrlzStructNode,
                                WkStringWithFixedLengthBytesSrlzInputNode,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithFixedLengthBytesSrlzStructNode,
                                WkStringWithFixedLengthBytesSrlzOutputNode,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkStringWithFixedLengthBytesSrlzStructNode,
                                WkSzTstPrimitivesGroupStructDefinition> fixedStrSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<String,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, ?, ?, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                                WkStringWithDynamicBytesSrlzInputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, ?, ?, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                                WkStringWithDynamicBytesSrlzOutputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, WkSignedBigEndianIntegerSrlzStructNode>,
                                WkSzTstPrimitivesGroupStructDefinition> dynStrSubcomponent;

  WkSzTstPrimitivesGroupStructDefinition(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<
                                  WkSzTstPrimitivesGroup,
                                  WkSettingsSrlzPacketOperationData,
                                  WkSzTstPrimitivesGroupStructDefinition,
                                  WkSzTstPrimitivesGroupPacketReader,
                                  WkSettingsSrlzPacketOperationData,
                                  WkSzTstPrimitivesGroupStructDefinition,
                                  WkSzTstPrimitivesGroupPacketWriter,
                                  WkSzTstPrimitivesGroupStructDefinition>(
                                      (i,xs,axb,xpc,dc) -> new WkSzTstPrimitivesGroupPacketReader(i,xs,axb,xpc,dc).operationCore,
                                      (i,y,ys,ayb,ypc,dc) -> new WkSzTstPrimitivesGroupPacketWriter(i,y,ys,ayb,ypc,dc).operationCore,
                                      componentCore,
                                      (ic) -> {},
                                      (ic) -> new WkSzTstPrimitivesGroup(ic.body()),
                                      (ic) -> {},
                                      (oc) -> {},
                                      this,
                                      WkSzTstPrimitivesGroup.class);
    this.byteSubcomponent = addByteSubcomponent();
    this.shortSubcomponent = addShortSubcomponent();
    this.intSubcomponent = addIntSubcomponent();
    this.longSubcomponent = addLongSubcomponent();
    this.fixedStrSubcomponent = addFixedStrSubcomponent();
    this.dynStrSubcomponent = addDynStrSubcomponent();
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Byte,
              WkSettingsSrlzPacketOperationData,
              WkSignedByteSrlzStructNode,
              WkSignedByteSrlzInputNode,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkSignedByteSrlzStructNode,
              WkSignedByteSrlzOutputNode,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSignedByteSrlzStructNode,
              WkSzTstPrimitivesGroupStructDefinition>
  addByteSubcomponent() {
    return this.definitionCore.<Byte, WkSettingsSrlzPacketOperationData, WkSignedByteSrlzStructNode, WkSignedByteSrlzInputNode, WkSettingsSrlzPacketOperationData, WkSignedByteSrlzStructNode, WkSignedByteSrlzOutputNode, WkSignedByteSrlzStructNode>
                               addSubcomponent(
                                    "BYTE",
                                    Optional.empty(),
                                    WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                                    WkSettingsSrlzPacketOperationData::none,
                                    Optional.empty(),
                                    WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                                    WkSettingsSrlzPacketOperationData::none,
                                    (k,ao,i) -> ao.serializable().b,
                                    false,
                                    WkSignedByteSrlzStructNode::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Short,
              WkSettingsSrlzPacketOperationData,
              WkSignedBigEndianShortSrlzStructNode,
              WkSignedBigEndianShortSrlzInputNode,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkSignedBigEndianShortSrlzStructNode,
              WkSignedBigEndianShortSrlzOutputNode,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSignedBigEndianShortSrlzStructNode,
              WkSzTstPrimitivesGroupStructDefinition>
  addShortSubcomponent() {
    return this.definitionCore.<Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode>
                    addSubcomponent(
                          "SHORT",
                          Optional.empty(),
                          WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                          WkSettingsSrlzPacketOperationData::none,
                          Optional.empty(),
                          WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                          WkSettingsSrlzPacketOperationData::none,
                          (k,ao,i) -> ao.serializable().s,
                          false,
                          WkSignedBigEndianShortSrlzStructNode::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Integer,
              WkSettingsSrlzPacketOperationData,
              WkSignedBigEndianIntegerSrlzStructNode,
              WkSignedBigEndianIntegerSrlzInputNode,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkSignedBigEndianIntegerSrlzStructNode,
              WkSignedBigEndianIntegerSrlzOutputNode,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSignedBigEndianIntegerSrlzStructNode,
              WkSzTstPrimitivesGroupStructDefinition>
  addIntSubcomponent() {
    return this.definitionCore.<Integer, WkSettingsSrlzPacketOperationData, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, WkSettingsSrlzPacketOperationData, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, WkSignedBigEndianIntegerSrlzStructNode>
                    addSubcomponent(
                            "INT",
                            Optional.empty(),
                            WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            Optional.empty(),
                            WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            (k,ao,i) -> ao.serializable().i,
                            false,
                            WkSignedBigEndianIntegerSrlzStructNode::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Long,
              WkSettingsSrlzPacketOperationData,
              WkSignedBigEndianLongSrlzStructNode,
              WkSignedBigEndianLongSrlzInputNode,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkSignedBigEndianLongSrlzStructNode,
              WkSignedBigEndianLongSrlzOutputNode,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSignedBigEndianLongSrlzStructNode,
              WkSzTstPrimitivesGroupStructDefinition>
  addLongSubcomponent() {
    return this.definitionCore.<Long, WkSettingsSrlzPacketOperationData, WkSignedBigEndianLongSrlzStructNode, WkSignedBigEndianLongSrlzInputNode, WkSettingsSrlzPacketOperationData, WkSignedBigEndianLongSrlzStructNode, WkSignedBigEndianLongSrlzOutputNode, WkSignedBigEndianLongSrlzStructNode>
                      addSubcomponent(
                            "LONG",
                            Optional.empty(),
                            WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            Optional.empty(),
                            WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            (k,ao,i) -> ao.serializable().l,
                            false,
                            WkSignedBigEndianLongSrlzStructNode::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              String,
              WkSettingsSrlzPacketOperationData,
              WkStringWithFixedLengthBytesSrlzStructNode,
              WkStringWithFixedLengthBytesSrlzInputNode,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkStringWithFixedLengthBytesSrlzStructNode,
              WkStringWithFixedLengthBytesSrlzOutputNode,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkStringWithFixedLengthBytesSrlzStructNode,
              WkSzTstPrimitivesGroupStructDefinition>
  addFixedStrSubcomponent() {
    String bytesLabel = "BYTES";
    int expectedSize = WkSzTstPrimitivesGroup.FIXED_STRING_LENGTH;
    Charset defaultCharset = StandardCharsets.US_ASCII;
    return this.definitionCore.<String,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithFixedLengthBytesSrlzStructNode,
                                WkStringWithFixedLengthBytesSrlzInputNode,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithFixedLengthBytesSrlzStructNode,
                                WkStringWithFixedLengthBytesSrlzOutputNode,
                                WkStringWithFixedLengthBytesSrlzStructNode>addSubcomponent(
                            "FIXEDSTR",
                            Optional.empty(),
                            WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            Optional.empty(),
                            WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> WkStringWithFixedLengthBytesSrlzStructNode.newCore(bytesLabel, expectedSize, defaultCharset, pc));
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              String,
              WkSettingsSrlzPacketOperationData,
              WkStringWithDynamicBytesSrlzStructNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, ?, ?, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
              WkStringWithDynamicBytesSrlzInputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkStringWithDynamicBytesSrlzStructNode<Integer, ?, ?, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
              WkStringWithDynamicBytesSrlzOutputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkStringWithDynamicBytesSrlzStructNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, WkSignedBigEndianIntegerSrlzStructNode>,
              WkSzTstPrimitivesGroupStructDefinition>
  addDynStrSubcomponent() {
    String bytesLabel = "BYTES";
    String sizeLabel = "SIZE";
    String arrayLabel = "BYTEARRAY";
    int minLength = 0;
    int maxLength = 1000;
    Charset charset = StandardCharsets.US_ASCII;
    return this.definitionCore.<String,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, ?, ?, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                                WkStringWithDynamicBytesSrlzInputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, ?, ?, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                                WkStringWithDynamicBytesSrlzOutputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, WkSignedBigEndianIntegerSrlzStructNode>>addSubcomponent(
                            "FIXEDSTR",
                            Optional.empty(),
                            WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            Optional.empty(),
                            WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> WkStringWithDynamicBytesSrlzStructNode.<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, WkSignedBigEndianIntegerSrlzStructNode>
                                                               newCore(
                                                                  pc,
                                                                  bytesLabel,
                                                                  sizeLabel,
                                                                  arrayLabel,
                                                                  minLength,
                                                                  maxLength,
                                                                  charset,
                                                                  Integer::valueOf,
                                                                  WkSignedBigEndianIntegerSrlzStructNode::newCore));
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<WkSzTstPrimitivesGroup> serializableClass() {
    return WkSzTstPrimitivesGroup.class;
  }

}
