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

public class WkSerdeSignedByte
    implements WkSerdeDtreeNumberDefinition<Byte>
{

  public static WkSerdeDtreeStruct<
                      Byte,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedByte,
                      WkSerdeSignedByteReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedByte,
                      WkSerdeSignedByteWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeSignedByte>
  newStruct(String label) {
    return new WkSerdeDtreeNodeStructComponentCoreRoot<>(
                      label,
                      WkSerdeSignedByte::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                      Byte,
                      WkSerdeDtreeOperationSettings,?,?,
                      WkSerdeSignedByte,
                      WkSerdeSignedByteReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,?,?,
                      WkSerdeSignedByte,
                      WkSerdeSignedByteWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeSignedByte,?>
  newCore(WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedByte(componentCore).definitionCore;
  }

  private WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Byte,
                        WkSerdeSignedByteReader,
                        WkSerdeSignedByteWriter,
                        WkSerdeSignedByte> definitionCore;

  private WkSerdeSignedByte(WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                Byte,
                                WkSerdeSignedByteReader,
                                WkSerdeSignedByteWriter,
                                WkSerdeSignedByte>(
                                    componentCore,
                                    (i,xs,xab,xkc,xdc) -> new WkSerdeSignedByteReader(i,xs,xab,xkc,xdc).operationCore,
                                    WkSerdeDtreeByteSignedReaderDecoderEngine.FACTORY,
                                    (i,y,ys,yab,ykc,ydc) -> new WkSerdeSignedByteWriter(i,y,ys,yab,ykc,ydc).writingCore,
                                    WkSerdeDtreeByteSignedWriterEncoderEngine.FACTORY,
                                    this,
                                    Byte.class);
  }

  @Override
  public Class<Byte> serializableClass() {
    return Byte.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

}
