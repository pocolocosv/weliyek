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

public class WkSzUnsignedByte
    implements WkSzNumberDefinition<
                      Integer,
                      WkSzUnsignedByteReader>
{

  public static WkSzStruct<
                      Integer,
                      WkSzOperationSettings,
                      WkSzUnsignedByte,
                      WkSzUnsignedByteReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      WkSzUnsignedByte,
                      WkSzUnsignedByteWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzUnsignedByte>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzUnsignedByte::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                      Integer,
                      WkSzOperationSettings,?,?,
                      WkSzUnsignedByte,
                      WkSzUnsignedByteReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings, ?, ?,
                      WkSzUnsignedByte,
                      WkSzUnsignedByteWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzUnsignedByte, ?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzUnsignedByte(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Integer,
                        WkSzUnsignedByteReader,
                        WkSzUnsignedByteWriter,
                        WkSzUnsignedByte> definitionCore;

  private WkSzUnsignedByte(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<>(
                                  componentCore,
                                  (i,xs,axb,xkc,dc) -> new WkSzUnsignedByteReader(i,xs,axb,xkc,dc).operationCore,
                                  UnsignedByteInputSerialization.FACTORY,
                                  (i,y,ys,ayb,ykc,dc) -> new WkSzUnsignedByteWriter(i,y,ys,ayb,ykc,dc).writingCore,
                                  UnsignedByteOutputSerializer.FACTORY,
                                  this,
                                  Integer.class);
  }

  @Override
  public Class<Integer> rxClass() {
    return Integer.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super WkSzUnsignedByteReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
