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
import java.util.function.Predicate;

import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzDefinitionCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.filter.FieldTester;

public class WkSzSignedByte
    implements WkSzNumberDefinition<
                        Byte,
                        WkSzSignedByteReader>
{

  public static WkSzStruct<
                      Byte,
                      WkSzOperationSettings,
                      WkSzSignedByte,
                      WkSzSignedByteReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      WkSzSignedByte,
                      WkSzSignedByteWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzSignedByte>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzSignedByte::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                      Byte,
                      WkSzOperationSettings,?,?,
                      WkSzSignedByte,
                      WkSzSignedByteReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,?,?,
                      WkSzSignedByte,
                      WkSzSignedByteWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzSignedByte,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzSignedByte(componentCore).definitionCore;
  }

  private WkSzSimplifiedNumberSerializerDefinitionCore<
                        Byte,
                        WkSzSignedByteReader,
                        WkSzSignedByteWriter,
                        WkSzSignedByte> definitionCore;

  private WkSzSignedByte(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                                Byte,
                                WkSzSignedByteReader,
                                WkSzSignedByteWriter,
                                WkSzSignedByte>(
                                    componentCore,
                                    (i,xs,xab,xkc,xdc) -> new WkSzSignedByteReader(i,xs,xab,xkc,xdc).operationCore,
                                    SignedByteInputSerialization.FACTORY,
                                    (i,y,ys,yab,ykc,ydc) -> new WkSzSignedByteWriter(i,y,ys,yab,ykc,ydc).writingCore,
                                    ByteOutputSerializer.FACTORY,
                                    this,
                                    Byte.class);
  }

  @Override
  public Class<Byte> rxClass() {
    return Byte.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?,?>
  makeTester(Predicate<? super WkSzSignedByteReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
