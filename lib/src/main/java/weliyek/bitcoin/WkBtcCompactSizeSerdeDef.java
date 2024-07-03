/*
 * Copyright (C) 2024  Ricardo Villalobos - All Rights Reserved
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
package weliyek.bitcoin;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinition;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongWriter;
import weliyek.serialization.number.WkSerdeUnsignedByte;
import weliyek.serialization.number.WkSerdeUnsignedByteReader;
import weliyek.serialization.number.WkSerdeUnsignedByteWriter;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianShort;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianShortReader;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianShortWriter;

public class WkBtcCompactSizeSerdeDef
    implements WkSerdeDtreeAggregatorStructDefinition<Long>
{

  static public final  int HEADER_SHORT = 0xFD;
  static public final  int HEADER_INT   = 0xFE;
  static public final  int HEADER_LONG  = 0xFF;

  static public final long SHORT_LIMIT  = 0xFFFF;
  static public final long INT_LIMIT    = 0xFFFF_FFFFL;

  private final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkBtcCompactSizeSerdeDef,
                        WkBtcCompactSizeSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcCompactSizeSerdeDef,
                        WkBtcCompactSizeSerdeWriter,
                        WkBtcCompactSizeSerdeDef> definitionCore;

  final WkSerdeDtreeStructSubfieldCore<Integer, Long, WkSerdeDtreeOperationSettings,
            WkSerdeUnsignedByte, WkSerdeUnsignedByteReader, ? extends WkSerdeDtreeBytestreamInputBase<?>,
            WkBtcCompactSizeSerdeReader, WkSerdeDtreeOperationSettings, WkSerdeUnsignedByte,
            WkSerdeUnsignedByteWriter, ? extends WkSerdeDtreeBytestreamOutputBase<?>,
            WkBtcCompactSizeSerdeWriter, WkSerdeUnsignedByte> firstByte;

  final WkSerdeDtreeStructSubfieldCore<Integer, Long, WkSerdeDtreeOperationSettings,
            WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortReader,
            ? extends WkSerdeDtreeBytestreamInputBase<?>, WkBtcCompactSizeSerdeReader, WkSerdeDtreeOperationSettings,
            WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortWriter,
            ? extends WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeWriter,
            WkSerdeUnsignedLittleEndianShort> uint16le;

  final WkSerdeDtreeStructSubfieldCore<Long, Long, WkSerdeDtreeOperationSettings,
            WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerReader,
            ? extends WkSerdeDtreeBytestreamInputBase<?>, WkBtcCompactSizeSerdeReader, WkSerdeDtreeOperationSettings,
            WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerWriter,
            ? extends WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeWriter,
            WkSerdeUnsignedLittleEndianInteger> uint32le;

  final WkSerdeDtreeStructSubfieldCore<Long, Long, WkSerdeDtreeOperationSettings,
            WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader,
            ? extends WkSerdeDtreeBytestreamInputBase<?>, WkBtcCompactSizeSerdeReader, WkSerdeDtreeOperationSettings,
            WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter,
            ? extends WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeWriter,
            WkSerdeSignedLittleEndianLong> int64le;

  public static WkSerdeDtreeStruct<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkBtcCompactSizeSerdeDef,
                        WkBtcCompactSizeSerdeReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkBtcCompactSizeSerdeDef,
                        WkBtcCompactSizeSerdeWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcCompactSizeSerdeDef>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkBtcCompactSizeSerdeDef::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  static WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkBtcCompactSizeSerdeDef,
                        WkBtcCompactSizeSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcCompactSizeSerdeDef,
                        WkBtcCompactSizeSerdeWriter,
                        WkBtcCompactSizeSerdeDef>
  newCore(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore) {
    return new WkBtcCompactSizeSerdeDef(fieldCore).definitionCore;
  }

  WkBtcCompactSizeSerdeDef(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkBtcCompactSizeSerdeDef>(
                                (i,kc,dc) -> new WkBtcCompactSizeSerdeReader(i, kc, dc).readerCore,
                                (i,kc,dc) -> new WkBtcCompactSizeSerdeWriter(i, kc, dc).writerCore,
                                fieldCore,
                                WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnReadInitOrSkipped,
                                WkBtcCompactSizeSerdeDef::onReadFull,
                                WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnReadInitOrSkipped,
                                WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnWriteInit,
                                this,
                                Long.class);
    this.firstByte = WkSerdeUnsignedByte.<Long, WkBtcCompactSizeSerdeReader, WkBtcCompactSizeSerdeWriter>
                        addAsSingleOperationSubfield(
                                "HEADER",
                                definitionCore,
                                Optional.empty(),
                                Optional.empty(),
                                (yk,yo,i) -> getHeaderValueFor(yo.serializable().longValue()),
                                true);
    this.uint16le = WkSerdeUnsignedLittleEndianShort.<Long,WkBtcCompactSizeSerdeReader,WkBtcCompactSizeSerdeWriter>
                        addAsSingleOperationSubfield(
                                "UINT16LE",
                                definitionCore,
                                Optional.of((xo) -> HEADER_SHORT == xo.firstByte().get().firstOperation().get().result().get().serializable().get().longValue()),
                                Optional.of((yo) -> HEADER_SHORT == yo.firstByte().get().firstOperation().get().serializable()),
                                (yk,yo,i) -> yo.serializable().intValue(),
                                false);
    this.uint32le = WkSerdeUnsignedLittleEndianInteger.<Long,WkBtcCompactSizeSerdeReader,WkBtcCompactSizeSerdeWriter>
                        addAsSingleOperationSubfield(
                                "UINT32LE",
                                definitionCore,
                                Optional.of((xo) -> HEADER_INT == xo.firstByte().get().firstOperation().get().result().get().serializable().get().longValue()),
                                Optional.of((yo) -> HEADER_INT == yo.firstByte().get().firstOperation().get().serializable()),
                                (yk,yo,i) -> yo.serializable().longValue(),
                                false);
    this.int64le = WkSerdeSignedLittleEndianLong.<Long,WkBtcCompactSizeSerdeReader,WkBtcCompactSizeSerdeWriter>
                        addAsSingleOperationSubfield(
                                "SINT64LE",
                                definitionCore,
                                Optional.of((xo) -> HEADER_LONG == xo.firstByte().get().firstOperation().get().result().get().serializable().get().longValue()),
                                Optional.of((yo) -> HEADER_LONG == yo.firstByte().get().firstOperation().get().serializable()),
                                (yk,yo,i) -> yo.serializable().longValue(),
                                false);
  }

  static int getHeaderValueFor(long val) {
      if ((val >> 8 == 0L) && ((0xFF & val) < HEADER_SHORT)) {
        return (int) val;
      } else if ((val >> 16 == 0L) && ((SHORT_LIMIT & val) != 0)) {
        return HEADER_SHORT;
      } else if ((val >> 32 == 0) && ((INT_LIMIT & val) != 0)) {
        return HEADER_INT;
      } else {
        return HEADER_LONG;
      }
  }

  public WkSerdeDtreeStructSubfield<WkBtcCompactSizeSerdeReader, WkBtcCompactSizeSerdeWriter, WkSerdeUnsignedByte>
  firstByte() {
    return this.firstByte.asProtocolField();
  }

  static private Long onReadFull(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified< Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      readerCore) {
    int firstB = readerCore.body().firstByte().get().firstOperation().get().result().get()
        .serializable().get().intValue();
    return switch (firstB) {
      case HEADER_SHORT -> readerCore.body().uint16le().get().firstOperation().get().result().get()
                                     .serializable().get().longValue();
      case HEADER_INT -> readerCore.body().uint32le().get().firstOperation().get().result().get()
                                   .serializable().get().longValue();
      case HEADER_LONG -> readerCore.body().int64le().get().firstOperation().get().result().get()
                                    .serializable().get().longValue();
      default -> readerCore.body().firstByte().get().firstOperation().get().result().get()
                           .serializable().get().longValue();
    };
  }

  @Override
  public Class<Long> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
