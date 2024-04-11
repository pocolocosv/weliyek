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

public class WkSerdeSignedBigEndianInteger
    implements WkSerdeDtreeNumberDefinition<Integer>
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
    return new WkSerdeDtreeNodeStructComponentCoreRoot<>(
                      label,
                      WkSerdeSignedBigEndianInteger::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                        Integer,
                        WkSerdeDtreeOperationSettings,?,?,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,?,?,
                        WkSerdeSignedBigEndianInteger,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeSignedBigEndianInteger,?>
  newCore(WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedBigEndianInteger(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeSignedBigEndianIntegerReader,
                        WkSerdeSignedBigEndianIntegerWriter,
                        WkSerdeSignedBigEndianInteger> definitionCore;

  private WkSerdeSignedBigEndianInteger(
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Integer,
                                  WkSerdeSignedBigEndianIntegerReader,
                                  WkSerdeSignedBigEndianIntegerWriter,
                                  WkSerdeSignedBigEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSerdeSignedBigEndianIntegerReader(i,xs,axb,xkc,dc).operationCore,
                                      WkSerdeDtreeIntegerSignedBigEndianReaderDecoderEngine.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSerdeSignedBigEndianIntegerWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSerdeDtreeIntSignedBigEndianWriterEncoderEngine.FACTORY,
                                      this,
                                      Integer.class);
  }

  @Override
  public Class<Integer> serializableClass() {
    return Integer.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
