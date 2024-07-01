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

public class WkSerdeUnsignedLittleEndianInteger
    implements WkSerdeDtreeNumberStructDefinition<Long>
{

  public static WkSerdeDtreeStruct<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeUnsignedLittleEndianInteger,
                        WkSerdeUnsignedLittleEndianIntegerReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeUnsignedLittleEndianInteger,
                        WkSerdeUnsignedLittleEndianIntegerWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeUnsignedLittleEndianInteger>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeUnsignedLittleEndianInteger::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Long,
                        WkSerdeUnsignedLittleEndianIntegerReader,
                        WkSerdeUnsignedLittleEndianIntegerWriter,
                        WkSerdeUnsignedLittleEndianInteger>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeUnsignedLittleEndianInteger(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Long,
                        WkSerdeUnsignedLittleEndianIntegerReader,
                        WkSerdeUnsignedLittleEndianIntegerWriter,
                        WkSerdeUnsignedLittleEndianInteger> definitionCore;

  private WkSerdeUnsignedLittleEndianInteger(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Long,
                                  WkSerdeUnsignedLittleEndianIntegerReader,
                                  WkSerdeUnsignedLittleEndianIntegerWriter,
                                  WkSerdeUnsignedLittleEndianInteger>(
                                      componentCore,
                                      (i,xkc,dc) -> new WkSerdeUnsignedLittleEndianIntegerReader(i,xkc,dc).operationCore,
                                      WkSerdeDtreeLongUnsignedLittleEndianReaderDecoderEngine.FACTORY,
                                      (i,ykc,dc) -> new WkSerdeUnsignedLittleEndianIntegerWriter(i,ykc,dc).operationCore,
                                      WkSerdeDtreeIntegerUnsignedLittleEndianWriterEncoderEngine.FACTORY,
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
