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
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayWriter;

public class WkBtcHash256SerdeDef
    implements WkSerdeDtreeAggregatorStructDefinition<WkBtcHash256>
{

  static WkSerdeDtreeStruct<
                      WkBtcHash256,
                      WkSerdeDtreeOperationSettings,
                      WkBtcHash256SerdeDef,
                      WkBtcHash256SerdeFieldReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkBtcHash256SerdeDef,
                      WkBtcHash256SerdeFieldWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkBtcHash256SerdeDef>
  newStruct(String label) {
  return new WkSerdeDtreeStructCore<>(
                      label,
                      WkBtcHash256SerdeDef::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  static public WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                      WkBtcHash256,
                      WkSerdeDtreeOperationSettings,
                      WkBtcHash256SerdeDef,
                      WkBtcHash256SerdeFieldReader,
                      WkSerdeDtreeOperationSettings,
                      WkBtcHash256SerdeDef,
                      WkBtcHash256SerdeFieldWriter,
                      WkBtcHash256SerdeDef>
  newCore(
    WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    return new WkBtcHash256SerdeDef(fieldCore).definitionCore;
  }


  final WkSerdeDtreeStructSubfieldCore<
                      WkByteArray,
                      WkBtcHash256,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayReader,
                      ? extends WkSerdeDtreeBytestreamInputBase<?>,
                      WkBtcHash256SerdeFieldReader,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayWriter,
                      ? extends WkSerdeDtreeBytestreamOutputBase<?>,
                      WkBtcHash256SerdeFieldWriter,
                      WkSerdeDtreeFixedSizeByteArray> bytes;

  private final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                      WkBtcHash256,
                      WkSerdeDtreeOperationSettings,
                      WkBtcHash256SerdeDef,
                      WkBtcHash256SerdeFieldReader,
                      WkSerdeDtreeOperationSettings,
                      WkBtcHash256SerdeDef,
                      WkBtcHash256SerdeFieldWriter,
                      WkBtcHash256SerdeDef> definitionCore;

  private WkBtcHash256SerdeDef(
    WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcHash256, WkSerdeDtreeOperationSettings, WkBtcHash256SerdeDef, WkBtcHash256SerdeFieldReader, WkSerdeDtreeOperationSettings, WkBtcHash256SerdeDef, WkBtcHash256SerdeFieldWriter, WkBtcHash256SerdeDef>(
                                  (i,xkc,dc) -> new WkBtcHash256SerdeFieldReader(i,xkc,dc).readerCore,
                                  (i,ykc,dc) -> new WkBtcHash256SerdeFieldWriter(i,ykc,dc).writerCore,
                                  fieldCore,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnReadInitOrSkipped,
                                  WkBtcHash256SerdeDef::onReadFull,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnReadInitOrSkipped,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnWriteInit,
                                  this,
                                  WkBtcHash256.class);
    this.bytes = WkSerdeDtreeFixedSizeByteArray.addAsSubfieldWithSingleOperation(
                                  WkBtcHash256.CANONICAL_BYTE_LENGTH,
                                  definitionCore,
                                  "BYTES",
                                  Optional.empty(),
                                  Optional.empty(),
                                  (k,ao,i) -> ao.serializable().bytes(),
                                  false);
  }

  private static WkBtcHash256
  onReadFull(WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcHash256,
      WkSerdeDtreeOperationSettings,
      WkBtcHash256SerdeDef,
      WkBtcHash256SerdeFieldReader> readerCore) {
    WkByteArray byteArray = readerCore.body().bytes().get().firstOperation().get().result().get().serializable().get();
    return new WkBtcHash256(byteArray);
  }

  @Override
  public Class<WkBtcHash256> serializableClass() {
    return WkBtcHash256.class;
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
