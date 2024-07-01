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

public class WkSerdeUnsignedLittleEndianShort
    implements WkSerdeDtreeNumberStructDefinition<Integer>
{

  public static WkSerdeDtreeStruct<
                      Integer,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeUnsignedLittleEndianShort,
                      WkSerdeUnsignedLittleEndianShortReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeUnsignedLittleEndianShort,
                      WkSerdeUnsignedLittleEndianShortWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeUnsignedLittleEndianShort>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeUnsignedLittleEndianShort::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeUnsignedLittleEndianShortReader,
                        WkSerdeUnsignedLittleEndianShortWriter,
                        WkSerdeUnsignedLittleEndianShort>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeUnsignedLittleEndianShort(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeUnsignedLittleEndianShortReader,
                        WkSerdeUnsignedLittleEndianShortWriter,
                        WkSerdeUnsignedLittleEndianShort> definitionCore;

  private WkSerdeUnsignedLittleEndianShort(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Integer,
                                  WkSerdeUnsignedLittleEndianShortReader,
                                  WkSerdeUnsignedLittleEndianShortWriter,
                                  WkSerdeUnsignedLittleEndianShort>(
                                      componentCore,
                                      (i,xkc,dc) -> new WkSerdeUnsignedLittleEndianShortReader(i,xkc,dc).operationCore,
                                      WkSerdeDtreeShortUnsignedLittleEndianReaderDecoderEngine.FACTORY,
                                      (i,ykc,dc) -> new WkSerdeUnsignedLittleEndianShortWriter(i,ykc,dc).operationCore,
                                      WkSerdeDtreeShortUnsignedLittleEndianWriterEncoderEngine.FACTORY,
                                      this,
                                      Integer.class);
  }

  @Override
  public Class<Integer> serializableClass() {
    return Integer.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

}
