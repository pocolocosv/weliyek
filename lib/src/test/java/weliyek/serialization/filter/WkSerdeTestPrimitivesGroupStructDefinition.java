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

import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinition;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianLong;
import weliyek.serialization.number.WkSerdeSignedBigEndianLongReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianLongWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSerdeSignedByte;
import weliyek.serialization.number.WkSerdeSignedByteReader;
import weliyek.serialization.number.WkSerdeSignedByteWriter;
import weliyek.serialization.string.WkSerdeStringDynamicBytes;
import weliyek.serialization.string.WkSerdeStringDynamicBytesReader;
import weliyek.serialization.string.WkSerdeStringDynamicBytesWriter;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytes;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytesReader;
import weliyek.serialization.string.WkSerdeStringFixedLengthBytesWriter;

public class WkSerdeTestPrimitivesGroupStructDefinition
        implements WkSerdeDtreeAggregatorStructDefinition<
                        WkSerdeTestPrimitivesGroup>
{

  final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgWriter,
                        WkSerdeTestPrimitivesGroupStructDefinition> definitionCore;

  final WkSerdeDtreeStructSubfieldCore<
                                Byte,
                                WkSerdeTestPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedByte,
                                WkSerdeSignedByteReader,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSerdeTestPrimitivesGroupMsgReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedByte,
                                WkSerdeSignedByteWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSerdeTestPrimitivesGroupMsgWriter,
                                WkSerdeSignedByte> byteSubcomponent;

  final WkSerdeDtreeStructSubfieldCore<
                                Short,
                                WkSerdeTestPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianShort,
                                WkSerdeSignedBigEndianShortReader,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSerdeTestPrimitivesGroupMsgReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianShort,
                                WkSerdeSignedBigEndianShortWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSerdeTestPrimitivesGroupMsgWriter,
                                WkSerdeSignedBigEndianShort> shortSubcomponent;

  final WkSerdeDtreeStructSubfieldCore<
                                Integer,
                                WkSerdeTestPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianInteger,
                                WkSerdeSignedBigEndianIntegerReader,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSerdeTestPrimitivesGroupMsgReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianInteger,
                                WkSerdeSignedBigEndianIntegerWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSerdeTestPrimitivesGroupMsgWriter,
                                WkSerdeSignedBigEndianInteger> intSubcomponent;

  final WkSerdeDtreeStructSubfieldCore<
                                Long,
                                WkSerdeTestPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianLong,
                                WkSerdeSignedBigEndianLongReader,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSerdeTestPrimitivesGroupMsgReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeSignedBigEndianLong,
                                WkSerdeSignedBigEndianLongWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSerdeTestPrimitivesGroupMsgWriter,
                                WkSerdeSignedBigEndianLong> longSubcomponent;

  final WkSerdeDtreeStructSubfieldCore<
                                String,
                                WkSerdeTestPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringFixedLengthBytes,
                                WkSerdeStringFixedLengthBytesReader,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSerdeTestPrimitivesGroupMsgReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringFixedLengthBytes,
                                WkSerdeStringFixedLengthBytesWriter,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSerdeTestPrimitivesGroupMsgWriter,
                                WkSerdeStringFixedLengthBytes> fixedStrSubcomponent;

  final WkSerdeDtreeStructSubfieldCore<
                                String,
                                WkSerdeTestPrimitivesGroup,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
                                WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                                WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                                WkSerdeTestPrimitivesGroupMsgReader,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeStringDynamicBytes<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                                WkSerdeStringDynamicBytesWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                                WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                                WkSerdeTestPrimitivesGroupMsgWriter,
                                WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>> dynStrSubcomponent;

  WkSerdeTestPrimitivesGroupStructDefinition(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                                  WkSerdeTestPrimitivesGroup,
                                  WkSerdeDtreeOperationSettings,
                                  WkSerdeTestPrimitivesGroupStructDefinition,
                                  WkSerdeTestPrimitivesGroupMsgReader,
                                  WkSerdeDtreeOperationSettings,
                                  WkSerdeTestPrimitivesGroupStructDefinition,
                                  WkSerdeTestPrimitivesGroupMsgWriter,
                                  WkSerdeTestPrimitivesGroupStructDefinition>(
                                      (i,xpc,dc) -> new WkSerdeTestPrimitivesGroupMsgReader(i,xpc,dc).operationCore,
                                      (i,ypc,dc) -> new WkSerdeTestPrimitivesGroupMsgWriter(i,ypc,dc).operationCore,
                                      componentCore,
                                      (ic) -> {},
                                      (ic) -> new WkSerdeTestPrimitivesGroup(ic.body()),
                                      (ic) -> {},
                                      (oc) -> {},
                                      this,
                                      WkSerdeTestPrimitivesGroup.class);
    this.byteSubcomponent = addByteSubcomponent();
    this.shortSubcomponent = addShortSubcomponent();
    this.intSubcomponent = addIntSubcomponent();
    this.longSubcomponent = addLongSubcomponent();
    this.fixedStrSubcomponent = addFixedStrSubcomponent();
    this.dynStrSubcomponent = addDynStrSubcomponent();
  }

  private WkSerdeDtreeStructSubfieldCore<
              Byte,
              WkSerdeTestPrimitivesGroup,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedByte,
              WkSerdeSignedByteReader,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSerdeTestPrimitivesGroupMsgReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedByte,
              WkSerdeSignedByteWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSerdeTestPrimitivesGroupMsgWriter,
              WkSerdeSignedByte>
  addByteSubcomponent() {
    return this.definitionCore.<Byte, WkSerdeDtreeOperationSettings, WkSerdeSignedByte, WkSerdeSignedByteReader, WkSerdeDtreeOperationSettings, WkSerdeSignedByte, WkSerdeSignedByteWriter, WkSerdeSignedByte>
                               addSubcomponent(
                                    "BYTE",
                                    Optional.empty(),
                                    WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                                    WkSerdeDtreeOperationSettings::none,
                                    Optional.empty(),
                                    WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                                    WkSerdeDtreeOperationSettings::none,
                                    (k,ao,i) -> ao.serializable().b,
                                    false,
                                    WkSerdeSignedByte::newCore);
  }

  private WkSerdeDtreeStructSubfieldCore<
              Short,
              WkSerdeTestPrimitivesGroup,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianShort,
              WkSerdeSignedBigEndianShortReader,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSerdeTestPrimitivesGroupMsgReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianShort,
              WkSerdeSignedBigEndianShortWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSerdeTestPrimitivesGroupMsgWriter,
              WkSerdeSignedBigEndianShort>
  addShortSubcomponent() {
    return this.definitionCore.<Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort>
                    addSubcomponent(
                          "SHORT",
                          Optional.empty(),
                          WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                          WkSerdeDtreeOperationSettings::none,
                          Optional.empty(),
                          WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                          WkSerdeDtreeOperationSettings::none,
                          (k,ao,i) -> ao.serializable().s,
                          false,
                          WkSerdeSignedBigEndianShort::newCore);
  }

  private WkSerdeDtreeStructSubfieldCore<
              Integer,
              WkSerdeTestPrimitivesGroup,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianInteger,
              WkSerdeSignedBigEndianIntegerReader,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSerdeTestPrimitivesGroupMsgReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianInteger,
              WkSerdeSignedBigEndianIntegerWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSerdeTestPrimitivesGroupMsgWriter,
              WkSerdeSignedBigEndianInteger>
  addIntSubcomponent() {
    return this.definitionCore.<Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>
                    addSubcomponent(
                            "INT",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            (k,ao,i) -> ao.serializable().i,
                            false,
                            WkSerdeSignedBigEndianInteger::newCore);
  }

  private WkSerdeDtreeStructSubfieldCore<
              Long,
              WkSerdeTestPrimitivesGroup,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianLong,
              WkSerdeSignedBigEndianLongReader,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSerdeTestPrimitivesGroupMsgReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeSignedBigEndianLong,
              WkSerdeSignedBigEndianLongWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSerdeTestPrimitivesGroupMsgWriter,
              WkSerdeSignedBigEndianLong>
  addLongSubcomponent() {
    return this.definitionCore.<Long, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongWriter, WkSerdeSignedBigEndianLong>
                      addSubcomponent(
                            "LONG",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            (k,ao,i) -> ao.serializable().l,
                            false,
                            WkSerdeSignedBigEndianLong::newCore);
  }

  private WkSerdeDtreeStructSubfieldCore<
              String,
              WkSerdeTestPrimitivesGroup,
              WkSerdeDtreeOperationSettings,
              WkSerdeStringFixedLengthBytes,
              WkSerdeStringFixedLengthBytesReader,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSerdeTestPrimitivesGroupMsgReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeStringFixedLengthBytes,
              WkSerdeStringFixedLengthBytesWriter,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSerdeTestPrimitivesGroupMsgWriter,
              WkSerdeStringFixedLengthBytes>
  addFixedStrSubcomponent() {
    String bytesLabel = "BYTES";
    int expectedSize = WkSerdeTestPrimitivesGroup.FIXED_STRING_LENGTH;
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
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> WkSerdeStringFixedLengthBytes.newCore(bytesLabel, expectedSize, defaultCharset, pc));
  }

  private WkSerdeDtreeStructSubfieldCore<
              String,
              WkSerdeTestPrimitivesGroup,
              WkSerdeDtreeOperationSettings,
              WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
              WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
              WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
              WkSerdeTestPrimitivesGroupMsgReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeStringDynamicBytes<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
              WkSerdeStringDynamicBytesWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
              WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
              WkSerdeTestPrimitivesGroupMsgWriter,
              WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
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
                                WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
                          addSubcomponent(
                            "DYNSTR",
                            Optional.empty(),
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            Optional.empty(),
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
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
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<WkSerdeTestPrimitivesGroup> serializableClass() {
    return WkSerdeTestPrimitivesGroup.class;
  }

}
