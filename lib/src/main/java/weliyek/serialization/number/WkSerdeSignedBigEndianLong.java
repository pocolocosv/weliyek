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

public class WkSerdeSignedBigEndianLong
    implements WkSerdeDtreeNumberStructDefinition<Long>
{

  public static WkSerdeDtreeStruct<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianLong,
                        WkSerdeSignedBigEndianLongReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianLong,
                        WkSerdeSignedBigEndianLongWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeSignedBigEndianLong>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeSignedBigEndianLong::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Long,
                        WkSerdeSignedBigEndianLongReader,
                        WkSerdeSignedBigEndianLongWriter,
                        WkSerdeSignedBigEndianLong>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedBigEndianLong(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Long,
                        WkSerdeSignedBigEndianLongReader,
                        WkSerdeSignedBigEndianLongWriter,
                        WkSerdeSignedBigEndianLong> definitionCore;

  private WkSerdeSignedBigEndianLong(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                            Long,
                            WkSerdeSignedBigEndianLongReader,
                            WkSerdeSignedBigEndianLongWriter,
                            WkSerdeSignedBigEndianLong>(
                                componentCore,
                                (i,xkc,dc) -> new WkSerdeSignedBigEndianLongReader(i,xkc,dc).operationCore,
                                WkSerdeDtreeLongSignedBigEndianReaderDecoderEngine.FACTORY,
                                (i,ykc,dc) -> new WkSerdeSignedBigEndianLongWriter(i,ykc,dc).operationCore,
                                WkSerdeDtreeLongSignedBigEndianWriterEncoderEngine.FACTORY,
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
