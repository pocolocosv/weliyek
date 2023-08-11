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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.basic.aggregator.SimplifiedAggregatorCore;
import weliyek.amat.basic.aggregator.WkSzAggregatorDefinition;
import weliyek.amat.basic.aggregator.WkSzAggregatorDefinitionCore;
import weliyek.amat.basic.aggregator.WkSzSubcomponentCore;
import weliyek.amat.basic.number.WkSzSignedBigEndianInteger;
import weliyek.amat.basic.number.WkSzSignedBigEndianIntegerReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianIntegerWriter;
import weliyek.amat.basic.number.WkSzSignedBigEndianLong;
import weliyek.amat.basic.number.WkSzSignedBigEndianLongReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianLongWriter;
import weliyek.amat.basic.number.WkSzSignedBigEndianShort;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortWriter;
import weliyek.amat.basic.number.WkSzSignedByte;
import weliyek.amat.basic.number.WkSzSignedByteReader;
import weliyek.amat.basic.number.WkSzSignedByteWriter;
import weliyek.amat.basic.string.StringWithDynamicSizeBytes;
import weliyek.amat.basic.string.StringWithDynamicSizeBytesDeserializing;
import weliyek.amat.basic.string.StringWithDynamicSizeBytesSerializing;
import weliyek.amat.basic.string.WkSzStringWithFixedLengthBytes;
import weliyek.amat.basic.string.WkSzStringWithFixedLengthBytesReader;
import weliyek.amat.basic.string.WkSzStringWithFixedLengthBytesWriter;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.base.WkSzStructComponentCoreBase;

public class PrimitivesGroupField
        implements WkSzAggregatorDefinition<
                        PrimitivesGroup,
                        PrimitivesGroupDeserializer>
{

  final SimplifiedAggregatorCore<
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupDeserializer,
                        WkSzOperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer,
                        PrimitivesGroupField> definitionCore;

  final WkSzSubcomponentCore<Byte,
                                WkSzOperationSettings,
                                WkSzSignedByte,
                                WkSzSignedByteReader,
                                PrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupDeserializer,
                                WkSzOperationSettings,
                                WkSzSignedByte,
                                WkSzSignedByteWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupSerializer,
                                WkSzSignedByte,
                                PrimitivesGroupField> byteSubcomponent;

  final WkSzSubcomponentCore<Short,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianShort,
                                WkSzSignedBigEndianShortReader,
                                PrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupDeserializer,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianShort,
                                WkSzSignedBigEndianShortWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupSerializer,
                                WkSzSignedBigEndianShort,
                                PrimitivesGroupField> shortSubcomponent;

  final WkSzSubcomponentCore<Integer,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianInteger,
                                WkSzSignedBigEndianIntegerReader,
                                PrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupDeserializer,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianInteger,
                                WkSzSignedBigEndianIntegerWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupSerializer,
                                WkSzSignedBigEndianInteger,
                                PrimitivesGroupField> intSubcomponent;

  final WkSzSubcomponentCore<Long,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianLong,
                                WkSzSignedBigEndianLongReader,
                                PrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupDeserializer,
                                WkSzOperationSettings,
                                WkSzSignedBigEndianLong,
                                WkSzSignedBigEndianLongWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupSerializer,
                                WkSzSignedBigEndianLong,
                                PrimitivesGroupField> longSubcomponent;

  final WkSzSubcomponentCore<String,
                                WkSzOperationSettings,
                                WkSzStringWithFixedLengthBytes,
                                WkSzStringWithFixedLengthBytesReader,
                                PrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupDeserializer,
                                WkSzOperationSettings,
                                WkSzStringWithFixedLengthBytes,
                                WkSzStringWithFixedLengthBytesWriter,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupSerializer,
                                WkSzStringWithFixedLengthBytes,
                                PrimitivesGroupField> fixedStrSubcomponent;

  final WkSzSubcomponentCore<String,
                                WkSzOperationSettings,
                                StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ? extends WkSzSignedBigEndianInteger>,
                                StringWithDynamicSizeBytesDeserializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>,
                                PrimitivesGroup,
                                WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupDeserializer,
                                WkSzOperationSettings,
                                StringWithDynamicSizeBytes<Integer, ?, ?, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ? extends WkSzSignedBigEndianInteger>,
                                StringWithDynamicSizeBytesSerializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>,
                                WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                                PrimitivesGroupField,
                                PrimitivesGroupSerializer,
                                StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>,
                                PrimitivesGroupField> dynStrSubcomponent;

  PrimitivesGroupField(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedAggregatorCore<
                                  PrimitivesGroup,
                                  WkSzOperationSettings,
                                  PrimitivesGroupField,
                                  PrimitivesGroupDeserializer,
                                  WkSzOperationSettings,
                                  PrimitivesGroupField,
                                  PrimitivesGroupSerializer,
                                  PrimitivesGroupField>(
                                      (i,xs,axb,xpc,dc) -> new PrimitivesGroupDeserializer(i,xs,axb,xpc,dc).operationCore,
                                      (i,y,ys,ayb,ypc,dc) -> new PrimitivesGroupSerializer(i,y,ys,ayb,ypc,dc).operationCore,
                                      componentCore,
                                      (oc) -> {},
                                      (oc) -> new PrimitivesGroup(oc.body()),
                                      (oc) -> {},
                                      this,
                                      PrimitivesGroup.class);
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
              PrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupDeserializer,
              WkSzOperationSettings,
              WkSzSignedByte,
              WkSzSignedByteWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupSerializer,
              WkSzSignedByte,
              PrimitivesGroupField>
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
              PrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupDeserializer,
              WkSzOperationSettings,
              WkSzSignedBigEndianShort,
              WkSzSignedBigEndianShortWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupSerializer,
              WkSzSignedBigEndianShort,
              PrimitivesGroupField>
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
              PrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupDeserializer,
              WkSzOperationSettings,
              WkSzSignedBigEndianInteger,
              WkSzSignedBigEndianIntegerWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupSerializer,
              WkSzSignedBigEndianInteger,
              PrimitivesGroupField>
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
              PrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupDeserializer,
              WkSzOperationSettings,
              WkSzSignedBigEndianLong,
              WkSzSignedBigEndianLongWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupSerializer,
              WkSzSignedBigEndianLong,
              PrimitivesGroupField>
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
              PrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupDeserializer,
              WkSzOperationSettings,
              WkSzStringWithFixedLengthBytes,
              WkSzStringWithFixedLengthBytesWriter,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupSerializer,
              WkSzStringWithFixedLengthBytes,
              PrimitivesGroupField>
  addFixedStrSubcomponent() {
    String bytesLabel = "BYTES";
    int expectedSize = PrimitivesGroup.FIXED_STRING_LENGTH;
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
              PrimitivesGroup,
              WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupDeserializer,
              WkSzOperationSettings,
              StringWithDynamicSizeBytes<Integer, ?, ?, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ? extends WkSzSignedBigEndianInteger>,
              StringWithDynamicSizeBytesSerializing<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>,
              WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
              PrimitivesGroupField,
              PrimitivesGroupSerializer,
              StringWithDynamicSizeBytes<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>,
              PrimitivesGroupField>
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
  makeTester(Predicate<? super PrimitivesGroupDeserializer> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<PrimitivesGroup> rxClass() {
    return PrimitivesGroup.class;
  }

}
