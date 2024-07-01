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
package weliyek.serialization.number;

import java.util.List;

import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;

public class WkSerdeSignedLittleEndianLong
    implements WkSerdeDtreeNumberStructDefinition<Long>
{

  public static WkSerdeDtreeStruct<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianLong,
                        WkSerdeSignedLittleEndianLongReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianLong,
                        WkSerdeSignedLittleEndianLongWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeSignedLittleEndianLong>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeSignedLittleEndianLong::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                      Long,
                      WkSerdeSignedLittleEndianLongReader,
                      WkSerdeSignedLittleEndianLongWriter,
                      WkSerdeSignedLittleEndianLong>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedLittleEndianLong(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Long,
                        WkSerdeSignedLittleEndianLongReader,
                        WkSerdeSignedLittleEndianLongWriter,
                        WkSerdeSignedLittleEndianLong> definitionCore;

  private WkSerdeSignedLittleEndianLong(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Long,
                                  WkSerdeSignedLittleEndianLongReader,
                                  WkSerdeSignedLittleEndianLongWriter,
                                  WkSerdeSignedLittleEndianLong>(
                                      componentCore,
                                      (i,xkc,dc) -> new WkSerdeSignedLittleEndianLongReader(i,xkc,dc).operationCore,
                                      WkSerdeDtreeLongSignedLittleEndianReaderDecoderEngine.FACTORY,
                                      (i,ykc,dc) -> new WkSerdeSignedLittleEndianLongWriter(i,ykc,dc).operationCore,
                                      WkSerdeDtreeLongSignedLittleEndianWriterEncoderEngine.FACTORY,
                                      this,
                                      Long.class);
  }

  @Override
  public Class<Long> serializableClass() {
    return Long.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

}
