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
import java.util.function.Predicate;

import weliyek.serialization.SimplifiedAggregatorCore;
import weliyek.serialization.WkSzAggregatorDefinition;
import weliyek.serialization.WkSzAggregatorDefinitionCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.WkSzSubcomponentCore;
import weliyek.serialization.filter.FieldTester;
import weliyek.serialization.number.WkSzSignedBigEndianInteger;
import weliyek.serialization.number.WkSzSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSzSignedBigEndianIntegerWriter;
import weliyek.serialization.number.WkSzSignedBigEndianLong;
import weliyek.serialization.number.WkSzSignedBigEndianLongReader;
import weliyek.serialization.number.WkSzSignedBigEndianLongWriter;
import weliyek.serialization.number.WkSzSignedBigEndianShort;
import weliyek.serialization.number.WkSzSignedBigEndianShortReader;
import weliyek.serialization.number.WkSzSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSzSignedByte;
import weliyek.serialization.number.WkSzSignedByteReader;
import weliyek.serialization.number.WkSzSignedByteWriter;
import weliyek.serialization.string.StringWithDynamicSizeBytes;
import weliyek.serialization.string.StringWithDynamicSizeBytesDeserializing;
import weliyek.serialization.string.StringWithDynamicSizeBytesSerializing;
import weliyek.serialization.string.WkSzStringWithFixedLengthBytes;
import weliyek.serialization.string.WkSzStringWithFixedLengthBytesReader;
import weliyek.serialization.string.WkSzStringWithFixedLengthBytesWriter;

public class WkSzTstPrimitivesGroupStructDefinition
        implements WkSzAggregatorDefinition<
                        WkSzTstPrimitivesGroup,
                        WkSzTstPrimitivesGroupPacketReader>
{

  final SimplifiedAggregatorCore<
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition> definitionCore;

  final WkSzSubcomponentCore<Byte,
                                WkSzOperationSettings,
                                WkSzSignedByte,
                                WkSzSignedByteReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSzOperationSettings,
                                WkSzSignedByte,
                                WkSzSignedByteWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSzSignedByte,
                                WkSzTstPrimitivesGroupStructDefinition> byteSubcomponent;

  final WkSzSubcomponentCore<Short,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianShort,
                                WkSzSignedBigEndianShortReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianShort,
                                WkSzSignedBigEndianShortWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSzSignedBigEndianShort,
                                WkSzTstPrimitivesGroupStructDefinition> shortSubcomponent;

  final WkSzSubcomponentCore<Integer,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianInteger,
                                WkSzSignedBigEndianIntegerReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianInteger,
                                WkSzSignedBigEndianIntegerWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSzSignedBigEndianInteger,
                                WkSzTstPrimitivesGroupStructDefinition> intSubcomponent;

  final WkSzSubcomponentCore<Long,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianLong,
                                WkSzSignedBigEndianLongReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianLong,
                                WkSzSignedBigEndianLongWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSzSignedBigEndianLong,
                                WkSzTstPrimitivesGroupStructDefinition> longSubcomponent;

  final WkSzSubcomponentCore<String,
                                WkSzOperationSettings,
                                WkSzStringWithFixedLengthBytes,
                                WkSzStringWithFixedLengthBytesReader,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSzOperationSettings,
                                WkSzStringWithFixedLengthBytes,
                                WkSzStringWithFixedLengthBytesWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                WkSzStringWithFixedLengthBytes,
                                WkSzTstPrimitivesGroupStructDefinition> fixedStrSubcomponent;

  final WkSzSubcomponentCore<String,
                                WkSzOperationSettings,
                                StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ? extends WkSzSignedBigEndianInteger>,
                                StringWithDynamicSizeBytesDeserializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>,
                                WkSzTstPrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketReader,
                                WkSzOperationSettings,
                                StringWithDynamicSizeBytes<Integer, ?, ?, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ? extends WkSzSignedBigEndianInteger>,
                                StringWithDynamicSizeBytesSerializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                WkSzTstPrimitivesGroupStructDefinition,
                                WkSzTstPrimitivesGroupPacketWriter,
                                StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>,
                                WkSzTstPrimitivesGroupStructDefinition> dynStrSubcomponent;

  WkSzTstPrimitivesGroupStructDefinition(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedAggregatorCore<
                                  WkSzTstPrimitivesGroup,
                                  WkSzOperationSettings,
                                  WkSzTstPrimitivesGroupStructDefinition,
                                  WkSzTstPrimitivesGroupPacketReader,
                                  WkSzOperationSettings,
                                  WkSzTstPrimitivesGroupStructDefinition,
                                  WkSzTstPrimitivesGroupPacketWriter,
                                  WkSzTstPrimitivesGroupStructDefinition>(
                                      (i,xs,axb,xpc,dc) -> new WkSzTstPrimitivesGroupPacketReader(i,xs,axb,xpc,dc).operationCore,
                                      (i,y,ys,ayb,ypc,dc) -> new WkSzTstPrimitivesGroupPacketWriter(i,y,ys,ayb,ypc,dc).operationCore,
                                      componentCore,
                                      (oc) -> {},
                                      (oc) -> new WkSzTstPrimitivesGroup(oc.body()),
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

  private WkSzSubcomponentCore<
              Byte,
              WkSzOperationSettings,
              WkSzSignedByte,
              WkSzSignedByteReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSzOperationSettings,
              WkSzSignedByte,
              WkSzSignedByteWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSzSignedByte,
              WkSzTstPrimitivesGroupStructDefinition>
  addByteSubcomponent() {
    return this.definitionCore.<Byte, WkSzOperationSettings, WkSzSignedByte, WkSzSignedByteReader, WkSzOperationSettings, WkSzSignedByte, WkSzSignedByteWriter, WkSzSignedByte>
                               addSubcomponent(
                                    "BYTE",
                                    Optional.empty(),
                                    WkSzAggregatorDefinitionCore.singleOperation(),
                                    WkSzOperationSettings::none,
                                    Optional.empty(),
                                    WkSzAggregatorDefinitionCore.singleOperation(),
                                    WkSzOperationSettings::none,
                                    (k,ao,i) -> ao.serializable().b,
                                    false,
                                    WkSzSignedByte::newCore);
  }

  private WkSzSubcomponentCore<
              Short,
              WkSzOperationSettings,
              WkSzSignedBigEndianShort,
              WkSzSignedBigEndianShortReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSzOperationSettings,
              WkSzSignedBigEndianShort,
              WkSzSignedBigEndianShortWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSzSignedBigEndianShort,
              WkSzTstPrimitivesGroupStructDefinition>
  addShortSubcomponent() {
    return this.definitionCore.<Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort>
                    addSubcomponent(
                          "SHORT",
                          Optional.empty(),
                          WkSzAggregatorDefinitionCore.singleOperation(),
                          WkSzOperationSettings::none,
                          Optional.empty(),
                          WkSzAggregatorDefinitionCore.singleOperation(),
                          WkSzOperationSettings::none,
                          (k,ao,i) -> ao.serializable().s,
                          false,
                          WkSzSignedBigEndianShort::newCore);
  }

  private WkSzSubcomponentCore<
              Integer,
              WkSzOperationSettings,
              WkSzSignedBigEndianInteger,
              WkSzSignedBigEndianIntegerReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSzOperationSettings,
              WkSzSignedBigEndianInteger,
              WkSzSignedBigEndianIntegerWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSzSignedBigEndianInteger,
              WkSzTstPrimitivesGroupStructDefinition>
  addIntSubcomponent() {
    return this.definitionCore.<Integer, WkSzOperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, WkSzOperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>
                    addSubcomponent(
                            "INT",
                            Optional.empty(),
                            WkSzAggregatorDefinitionCore.singleOperation(),
                            WkSzOperationSettings::none,
                            Optional.empty(),
                            WkSzAggregatorDefinitionCore.singleOperation(),
                            WkSzOperationSettings::none,
                            (k,ao,i) -> ao.serializable().i,
                            false,
                            WkSzSignedBigEndianInteger::newCore);
  }

  private WkSzSubcomponentCore<
              Long,
              WkSzOperationSettings,
              WkSzSignedBigEndianLong,
              WkSzSignedBigEndianLongReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSzOperationSettings,
              WkSzSignedBigEndianLong,
              WkSzSignedBigEndianLongWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSzSignedBigEndianLong,
              WkSzTstPrimitivesGroupStructDefinition>
  addLongSubcomponent() {
    return this.definitionCore.<Long, WkSzOperationSettings, WkSzSignedBigEndianLong, WkSzSignedBigEndianLongReader, WkSzOperationSettings, WkSzSignedBigEndianLong, WkSzSignedBigEndianLongWriter, WkSzSignedBigEndianLong>
                      addSubcomponent(
                            "LONG",
                            Optional.empty(),
                            WkSzAggregatorDefinitionCore.singleOperation(),
                            WkSzOperationSettings::none,
                            Optional.empty(),
                            WkSzAggregatorDefinitionCore.singleOperation(),
                            WkSzOperationSettings::none,
                            (k,ao,i) -> ao.serializable().l,
                            false,
                            WkSzSignedBigEndianLong::newCore);
  }

  private WkSzSubcomponentCore<
              String,
              WkSzOperationSettings,
              WkSzStringWithFixedLengthBytes,
              WkSzStringWithFixedLengthBytesReader,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSzOperationSettings,
              WkSzStringWithFixedLengthBytes,
              WkSzStringWithFixedLengthBytesWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              WkSzStringWithFixedLengthBytes,
              WkSzTstPrimitivesGroupStructDefinition>
  addFixedStrSubcomponent() {
    String bytesLabel = "BYTES";
    int expectedSize = WkSzTstPrimitivesGroup.FIXED_STRING_LENGTH;
    Charset defaultCharset = StandardCharsets.US_ASCII;
    return this.definitionCore.<String,
                                WkSzOperationSettings,
                                WkSzStringWithFixedLengthBytes,
                                WkSzStringWithFixedLengthBytesReader,
                                WkSzOperationSettings,
                                WkSzStringWithFixedLengthBytes,
                                WkSzStringWithFixedLengthBytesWriter,
                                WkSzStringWithFixedLengthBytes>addSubcomponent(
                            "FIXEDSTR",
                            Optional.empty(),
                            WkSzAggregatorDefinitionCore.singleOperation(),
                            WkSzOperationSettings::none,
                            Optional.empty(),
                            WkSzAggregatorDefinitionCore.singleOperation(),
                            WkSzOperationSettings::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> WkSzStringWithFixedLengthBytes.newCore(bytesLabel, expectedSize, defaultCharset, pc));
  }

  private WkSzSubcomponentCore<
              String,
              WkSzOperationSettings,
              StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ? extends WkSzSignedBigEndianInteger>,
              StringWithDynamicSizeBytesDeserializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>,
              WkSzTstPrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketReader,
              WkSzOperationSettings,
              StringWithDynamicSizeBytes<Integer, ?, ?, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ? extends WkSzSignedBigEndianInteger>,
              StringWithDynamicSizeBytesSerializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              WkSzTstPrimitivesGroupStructDefinition,
              WkSzTstPrimitivesGroupPacketWriter,
              StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>,
              WkSzTstPrimitivesGroupStructDefinition>
  addDynStrSubcomponent() {
    String bytesLabel = "BYTES";
    String sizeLabel = "SIZE";
    String arrayLabel = "BYTEARRAY";
    int minLength = 0;
    int maxLength = 1000;
    Charset charset = StandardCharsets.US_ASCII;
    return this.definitionCore.<String,
                                WkSzOperationSettings,
                                StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ? extends WkSzSignedBigEndianInteger>,
                                StringWithDynamicSizeBytesDeserializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>,
                                WkSzOperationSettings,
                                StringWithDynamicSizeBytes<Integer, ?, ?, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ? extends WkSzSignedBigEndianInteger>,
                                StringWithDynamicSizeBytesSerializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>,
                                StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>>addSubcomponent(
                            "FIXEDSTR",
                            Optional.empty(),
                            WkSzAggregatorDefinitionCore.singleOperation(),
                            WkSzOperationSettings::none,
                            Optional.empty(),
                            WkSzAggregatorDefinitionCore.singleOperation(),
                            WkSzOperationSettings::none,
                            (k,ao,i) -> ao.serializable().fixedLengthStr,
                            false,
                            (pc) -> StringWithDynamicSizeBytes.<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>
                                                               newCore(
                                                                  pc,
                                                                  bytesLabel,
                                                                  sizeLabel,
                                                                  arrayLabel,
                                                                  minLength,
                                                                  maxLength,
                                                                  charset,
                                                                  Integer::valueOf,
                                                                  WkSzSignedBigEndianInteger::newCore));
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?,?>
  makeTester(Predicate<? super WkSzTstPrimitivesGroupPacketReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<WkSzTstPrimitivesGroup> rxClass() {
    return WkSzTstPrimitivesGroup.class;
  }

}
