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

public class WkSzUnsignedLittleEndianShort
    implements WkSzNumberDefinition<
                        Integer,
                        WkSzUnsignedLittleEndianShortReader>
{

  public static WkSzStruct<
                      Integer,
                      WkSzOperationSettings,
                      WkSzUnsignedLittleEndianShort,
                      WkSzUnsignedLittleEndianShortReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      WkSzUnsignedLittleEndianShort,
                      WkSzUnsignedLittleEndianShortWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzUnsignedLittleEndianShort>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzUnsignedLittleEndianShort::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                        Integer,
                        WkSzOperationSettings,?,?,
                        WkSzUnsignedLittleEndianShort,
                        WkSzUnsignedLittleEndianShortReader,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,?,?,
                        WkSzUnsignedLittleEndianShort,
                        WkSzUnsignedLittleEndianShortWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSzUnsignedLittleEndianShort,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzUnsignedLittleEndianShort(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Integer,
                        WkSzUnsignedLittleEndianShortReader,
                        WkSzUnsignedLittleEndianShortWriter,
                        WkSzUnsignedLittleEndianShort> definitionCore;

  private WkSzUnsignedLittleEndianShort(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                                  Integer,
                                  WkSzUnsignedLittleEndianShortReader,
                                  WkSzUnsignedLittleEndianShortWriter,
                                  WkSzUnsignedLittleEndianShort>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSzUnsignedLittleEndianShortReader(i,xs,axb,xkc,dc).operationCore,
                                      WkSzLittleEndianUnsignedShortReadEngine.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSzUnsignedLittleEndianShortWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSzUnsignedLittleEndianShortWriteEngine.FACTORY,
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
  makeTester(
    Predicate<? super WkSzUnsignedLittleEndianShortReader> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
