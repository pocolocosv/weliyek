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

public class WkSerdeSignedBigEndianInteger
    implements WkSerdeDtreeNumberStructDefinition<Integer>
{

  public static WkSerdeDtreeStruct<
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeSignedBigEndianInteger>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeSignedBigEndianInteger::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeSignedBigEndianIntegerReader,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSerdeSignedBigEndianInteger>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedBigEndianInteger(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeSignedBigEndianIntegerReader,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSerdeSignedBigEndianInteger> definitionCore;

  private WkSerdeSignedBigEndianInteger(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Integer,
                                  WkSerdeSignedBigEndianIntegerReader,
                                  WkSerdeSignedBigEndianIntegerWriter,
                                  WkSerdeSignedBigEndianInteger>(
                                      componentCore,
                                      (i,xkc,dc) -> new WkSerdeSignedBigEndianIntegerReader(i,xkc,dc).readerCore,
                                      WkSerdeDtreeIntegerSignedBigEndianReaderDecoderEngine.FACTORY,
                                      (i,ykc,dc) -> new WkSerdeSignedBigEndianIntegerWriter(i,ykc,dc).writerCore,
                                      WkSerdeDtreeIntSignedBigEndianWriterEncoderEngine.FACTORY,
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

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
