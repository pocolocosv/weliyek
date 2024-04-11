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
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
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
import weliyek.serialization.string.WkSerdeStringDynamicBytesReader;
import weliyek.serialization.string.WkSerdeStringDynamicBytesWriter;
import weliyek.serialization.string.WkSerdeStringDynamicBytes;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytesReader;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytesWriter;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytes;

public class WkSzTstPrimitivesGroupStructDefinition
        implements WkSerdeDtreeAggregatorDefinition<
                        WkSzTstPrimitivesGroup>
{

  final WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition> definitionCore;

  final WkSrlzStructSubcomponentFrameNodeCore<Byte,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedByte,
                                WkSerdeSignedByteReader,
                                WkSzTstPrimitivesGroup,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedByte,
                                WkSerdeSignedByteWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeSignedByte,
                                WkSzTstPrimitivesGroupStructDefinition> byteSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Short,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianShort,
                                WkSerdeSignedBigEndianShortReader,
                                WkSzTstPrimitivesGroup,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianShort,
                                WkSerdeSignedBigEndianShortWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeSignedBigEndianShort,
                                WkSzTstPrimitivesGroupStructDefinition> shortSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Integer,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianInteger,
                                WkSerdeSignedBigEndianIntegerReader,
                                WkSzTstPrimitivesGroup,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianInteger,
                                WkSerdeSignedBigEndianIntegerWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeSignedBigEndianInteger,
                                WkSzTstPrimitivesGroupStructDefinition> intSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<Long,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianLong,
                                WkSerdeSignedBigEndianLongReader,
                                WkSzTstPrimitivesGroup,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianLong,
                                WkSerdeSignedBigEndianLongWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeSignedBigEndianLong,
                                WkSzTstPrimitivesGroupStructDefinition> longSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<String,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringFixedLengthBytes,
                                WkSerdeStringFixedLengthBytesReader,
                                WkSzTstPrimitivesGroup,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringFixedLengthBytes,
                                WkSerdeStringFixedLengthBytesWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeStringFixedLengthBytes,
                                WkSzTstPrimitivesGroupStructDefinition> fixedStrSubcomponent;

  final WkSrlzStructSubcomponentFrameNodeCore<String,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
                                WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                                WkSzTstPrimitivesGroup,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringDynamicBytes<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                                WkSerdeStringDynamicBytesWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>,
                                WkSzTstPrimitivesGroupStructDefinition> dynStrSubcomponent;

  WkSzTstPrimitivesGroupStructDefinition(
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<
                                  WkSzTstPrimitivesGroup,
                                  WkSerdeDtreeOperationSettings,
                                  WkSzTstPrimitivesGroupStructDefinition,
                                  WkSzTstPrimitivesGroupPacketReader,
                                  WkSerdeDtreeOperationSettings,
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
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedByte,
              WkSerdeSignedByteReader,
              WkSzTstPrimitivesGroup,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedByte,
              WkSerdeSignedByteWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeSignedByte,
              WkSzTstPrimitivesGroupStructDefinition>
  addByteSubcomponent() {
    return this.definitionCore.<Byte, WkSerdeDtreeOperationSettings, WkSerdeSignedByte, WkSerdeSignedByteReader, WkSerdeDtreeOperationSettings, WkSerdeSignedByte, WkSerdeSignedByteWriter, WkSerdeSignedByte>
                               addSubcomponent(
                                    "BYTE",
                                    Optional.empty(),
                                    WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                                    WkSerdeDtreeOperationSettings::none,
                                    Optional.empty(),
                                    WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                                    WkSerdeDtreeOperationSettings::none,
                                    (k,ao,i) -> ao.serializable().b,
                                    false,
                                    WkSerdeSignedByte::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Short,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianShort,
              WkSerdeSignedBigEndianShortReader,
              WkSzTstPrimitivesGroup,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianShort,
              WkSerdeSignedBigEndianShortWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeSignedBigEndianShort,
              WkSzTstPrimitivesGroupStructDefinition>
  addShortSubcomponent() {
    return this.definitionCore.<Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort>
                    addSubcomponent(
                          "SHORT",
                          Optional.empty(),
                          WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                          WkSerdeDtreeOperationSettings::none,
                          Optional.empty(),
                          WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                          WkSerdeDtreeOperationSettings::none,
                          (k,ao,i) -> ao.serializable().s,
                          false,
                          WkSerdeSignedBigEndianShort::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Integer,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianInteger,
              WkSerdeSignedBigEndianIntegerReader,
              WkSzTstPrimitivesGroup,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianInteger,
              WkSerdeSignedBigEndianIntegerWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeSignedBigEndianInteger,
              WkSzTstPrimitivesGroupStructDefinition>
  addIntSubcomponent() {
    return this.definitionCore.<Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>
                    addSubcomponent(
                            "INT",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSerdeDtreeOperationSettings::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSerdeDtreeOperationSettings::none,
                            (k,ao,i) -> ao.serializable().i,
                            false,
                            WkSerdeSignedBigEndianInteger::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              Long,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianLong,
              WkSerdeSignedBigEndianLongReader,
              WkSzTstPrimitivesGroup,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianLong,
              WkSerdeSignedBigEndianLongWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeSignedBigEndianLong,
              WkSzTstPrimitivesGroupStructDefinition>
  addLongSubcomponent() {
    return this.definitionCore.<Long, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongWriter, WkSerdeSignedBigEndianLong>
                      addSubcomponent(
                            "LONG",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSerdeDtreeOperationSettings::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSerdeDtreeOperationSettings::none,
                            (k,ao,i) -> ao.serializable().l,
                            false,
                            WkSerdeSignedBigEndianLong::newCore);
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              String,
              WkSerdeDtreeOperationSettings,
              WkSerdeStringFixedLengthBytes,
              WkSerdeStringFixedLengthBytesReader,
              WkSzTstPrimitivesGroup,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeStringFixedLengthBytes,
              WkSerdeStringFixedLengthBytesWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeStringFixedLengthBytes,
              WkSzTstPrimitivesGroupStructDefinition>
  addFixedStrSubcomponent() {
    String bytesLabel = "BYTES";
    int expectedSize = WkSzTstPrimitivesGroup.FIXED_STRING_LENGTH;
    Charset defaultCharset = StandardCharsets.US_ASCII;
    return this.definitionCore.<String,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringFixedLengthBytes,
                                WkSerdeStringFixedLengthBytesReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringFixedLengthBytes,
                                WkSerdeStringFixedLengthBytesWriter,
                                WkSerdeStringFixedLengthBytes>addSubcomponent(
                            "FIXEDSTR",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSerdeDtreeOperationSettings::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSerdeDtreeOperationSettings::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> WkSerdeStringFixedLengthBytes.newCore(bytesLabel, expectedSize, defaultCharset, pc));
  }

  private WkSrlzStructSubcomponentFrameNodeCore<
              String,
              WkSerdeDtreeOperationSettings,
              WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
              WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
              WkSzTstPrimitivesGroup,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeStringDynamicBytes<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
              WkSerdeStringDynamicBytesWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>,
              WkSzTstPrimitivesGroupStructDefinition>
  addDynStrSubcomponent() {
    String bytesLabel = "BYTES";
    String sizeLabel = "SIZE";
    String arrayLabel = "BYTEARRAY";
    int minLength = 0;
    int maxLength = 1000;
    Charset charset = StandardCharsets.US_ASCII;
    return this.definitionCore.<String,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
                                WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringDynamicBytes<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                                WkSerdeStringDynamicBytesWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                                WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>addSubcomponent(
                            "FIXEDSTR",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSerdeDtreeOperationSettings::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorDefinitionCore.singleOperation(),
                            WkSerdeDtreeOperationSettings::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> WkSerdeStringDynamicBytes.<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>
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
