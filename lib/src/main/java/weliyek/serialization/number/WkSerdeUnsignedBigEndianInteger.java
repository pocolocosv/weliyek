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

import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCoreRoot;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;

public class WkSerdeUnsignedBigEndianInteger
    implements WkSerdeDtreeNumberDefinition<Long>
{

  public static WkSerdeDtreeStruct<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeUnsignedBigEndianInteger,
                        WkSerdeUnsignedBigEndianIntegerReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeUnsignedBigEndianInteger,
                        WkSerdeUnsignedBigEndianIntegerWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeUnsignedBigEndianInteger>
  newStruct(String label) {
    return new WkSerdeDtreeNodeStructComponentCoreRoot<>(
                      label,
                      WkSerdeUnsignedBigEndianInteger::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                        Long,
                        WkSerdeDtreeOperationSettings,?,?,
                        WkSerdeUnsignedBigEndianInteger,
                        WkSerdeUnsignedBigEndianIntegerReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,?,?,
                        WkSerdeUnsignedBigEndianInteger,
                        WkSerdeUnsignedBigEndianIntegerWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeUnsignedBigEndianInteger,?>
  newCore(WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeUnsignedBigEndianInteger(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Long,
                        WkSerdeUnsignedBigEndianIntegerReader,
                        WkSerdeUnsignedBigEndianIntegerWriter,
                        WkSerdeUnsignedBigEndianInteger> definitionCore;

  private WkSerdeUnsignedBigEndianInteger(
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Long,
                                  WkSerdeUnsignedBigEndianIntegerReader,
                                  WkSerdeUnsignedBigEndianIntegerWriter,
                                  WkSerdeUnsignedBigEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSerdeUnsignedBigEndianIntegerReader(i,xs,axb,xkc,dc).operationCore,
                                      WkSerdeDtreeLongUnsignedBigEndianReaderDecoderEngine.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSerdeUnsignedBigEndianIntegerWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSerdeDtreeIntegerUnsignedBigEndianWriterEncoderEngine.FACTORY,
                                      this,
                                      Long.class);
  }

  @Override
  public Class<Long> serializableClass() {
    return Long.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

}
