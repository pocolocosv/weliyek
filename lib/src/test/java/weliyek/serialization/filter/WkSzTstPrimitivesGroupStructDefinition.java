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

import weliyek.serialization.WkSerdeDtreeAggregatorDefinition;
import weliyek.serialization.WkSerdeDtreeAggregatorDefinitionCore;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianLongReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianLongWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianLong;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedByteReader;
import weliyek.serialization.number.WkSerdeSignedByteWriter;
import weliyek.serialization.number.WkSerdeSignedByte;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzInputNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzOutputNode;
import weliyek.serialization.string.WkStringWithDynamicBytesSrlzStructNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzInputNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzOutputNode;
import weliyek.serialization.string.WkStringWithFixedLengthBytesSrlzStructNode;

public class WkSzTstPrimitivesGroupStructDefinition
        implements WkSerdeDtreeAggregatorDefinition<
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
                                WkSerdeSignedByte,
                                WkSerdeSignedByteReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkSerdeSignedByte,
                                WkSerdeSignedByteWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeSignedByte,
                                WkSzTstPrimitivesGroupStructDefinition> byteSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Short,
                                WkSettingsSrlzPacketOperationData,
                                WkSerdeSignedBigEndianShort,
                                WkSerdeSignedBigEndianShortReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkSerdeSignedBigEndianShort,
                                WkSerdeSignedBigEndianShortWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeSignedBigEndianShort,
                                WkSzTstPrimitivesGroupStructDefinition> shortSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Integer,
                                WkSettingsSrlzPacketOperationData,
                                WkSerdeSignedBigEndianInteger,
                                WkSerdeSignedBigEndianIntegerReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkSerdeSignedBigEndianInteger,
                                WkSerdeSignedBigEndianIntegerWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeSignedBigEndianInteger,
                                WkSzTstPrimitivesGroupStructDefinition> intSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Long,
                                WkSettingsSrlzPacketOperationData,
                                WkSerdeSignedBigEndianLong,
                                WkSerdeSignedBigEndianLongReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkSerdeSignedBigEndianLong,
                                WkSerdeSignedBigEndianLongWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeSignedBigEndianLong,
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
                                WkStringWithDynamicBytesSrlzStructNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
                                WkStringWithDynamicBytesSrlzInputNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                                WkStringWithDynamicBytesSrlzOutputNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>,
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
              WkSerdeSignedByte,
              WkSerdeSignedByteReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkSerdeSignedByte,
              WkSerdeSignedByteWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeSignedByte,
              WkSzTstPrimitivesGroupStructDefinition>
  addByteSubcomponent() {
    return this.definitionCore.<Byte, WkSettingsSrlzPacketOperationData, WkSerdeSignedByte, WkSerdeSignedByteReader, WkSettingsSrlzPacketOperationData, WkSerdeSignedByte, WkSerdeSignedByteWriter, WkSerdeSignedByte>
                               addSubcomponent(
                                    "BYTE",
                                    Optional.empty(),
                                    WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                                    WkSettingsSrlzPacketOperationData::none,
                                    Optional.empty(),
                                    WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                                    WkSettingsSrlzPacketOperationData::none,
                                    (k,ao,i) -> ao.serializable().b,
                                    false,
                                    WkSerdeSignedByte::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Short,
              WkSettingsSrlzPacketOperationData,
              WkSerdeSignedBigEndianShort,
              WkSerdeSignedBigEndianShortReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkSerdeSignedBigEndianShort,
              WkSerdeSignedBigEndianShortWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeSignedBigEndianShort,
              WkSzTstPrimitivesGroupStructDefinition>
  addShortSubcomponent() {
    return this.definitionCore.<Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort>
                    addSubcomponent(
                          "SHORT",
                          Optional.empty(),
                          WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                          WkSettingsSrlzPacketOperationData::none,
                          Optional.empty(),
                          WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                          WkSettingsSrlzPacketOperationData::none,
                          (k,ao,i) -> ao.serializable().s,
                          false,
                          WkSerdeSignedBigEndianShort::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Integer,
              WkSettingsSrlzPacketOperationData,
              WkSerdeSignedBigEndianInteger,
              WkSerdeSignedBigEndianIntegerReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkSerdeSignedBigEndianInteger,
              WkSerdeSignedBigEndianIntegerWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeSignedBigEndianInteger,
              WkSzTstPrimitivesGroupStructDefinition>
  addIntSubcomponent() {
    return this.definitionCore.<Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>
                    addSubcomponent(
                            "INT",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            (k,ao,i) -> ao.serializable().i,
                            false,
                            WkSerdeSignedBigEndianInteger::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Long,
              WkSettingsSrlzPacketOperationData,
              WkSerdeSignedBigEndianLong,
              WkSerdeSignedBigEndianLongReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkSerdeSignedBigEndianLong,
              WkSerdeSignedBigEndianLongWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeSignedBigEndianLong,
              WkSzTstPrimitivesGroupStructDefinition>
  addLongSubcomponent() {
    return this.definitionCore.<Long, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongReader, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongWriter, WkSerdeSignedBigEndianLong>
                      addSubcomponent(
                            "LONG",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            (k,ao,i) -> ao.serializable().l,
                            false,
                            WkSerdeSignedBigEndianLong::newCore);
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
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> WkStringWithFixedLengthBytesSrlzStructNode.newCore(bytesLabel, expectedSize, defaultCharset, pc));
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              String,
              WkSettingsSrlzPacketOperationData,
              WkStringWithDynamicBytesSrlzStructNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
              WkStringWithDynamicBytesSrlzInputNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSettingsSrlzPacketOperationData,
              WkStringWithDynamicBytesSrlzStructNode<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
              WkStringWithDynamicBytesSrlzOutputNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkStringWithDynamicBytesSrlzStructNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>,
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
                                WkStringWithDynamicBytesSrlzStructNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
                                WkStringWithDynamicBytesSrlzInputNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                                WkStringWithDynamicBytesSrlzOutputNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                                WkStringWithDynamicBytesSrlzStructNode<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>addSubcomponent(
                            "FIXEDSTR",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSettingsSrlzPacketOperationData::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> WkStringWithDynamicBytesSrlzStructNode.<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>
                                                               newCore(
                                                                  pc,
                                                                  bytesLabel,
                                                                  sizeLabel,
                                                                  arrayLabel,
                                                                  minLength,
                                                                  maxLength,
                                                                  charset,
                                                                  Integer::valueOf,
                                                                  WkSerdeSignedBigEndianInteger::newCore));
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<WkSzTstPrimitivesGroup> serializableClass() {
    return WkSzTstPrimitivesGroup.class;
  }

}
