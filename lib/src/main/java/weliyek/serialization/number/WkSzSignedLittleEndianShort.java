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
import weliyek.serialization.sequence.WkSzLittleEndianShortWriteEngine;

public class WkSzSignedLittleEndianShort
    implements WkSzNumberDefinition<
                        Short,
                        WkSzSignedLittleEndianShortReader>
{

  public static WkSzStruct<
                      Short,
                      WkSzOperationSettings,
                      WkSzSignedLittleEndianShort,
                      WkSzSignedLittleEndianShortReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      WkSzSignedLittleEndianShort,
                      WkSzSignedLittleEndianShortWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzSignedLittleEndianShort>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzSignedLittleEndianShort::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                      Short,
                      WkSzOperationSettings,?,?,
                      WkSzSignedLittleEndianShort,
                      WkSzSignedLittleEndianShortReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,?,?,
                      WkSzSignedLittleEndianShort,
                      WkSzSignedLittleEndianShortWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzSignedLittleEndianShort,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzSignedLittleEndianShort(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Short,
                        WkSzSignedLittleEndianShortReader,
                        WkSzSignedLittleEndianShortWriter,
                        WkSzSignedLittleEndianShort> definitionCore;

  public WkSzSignedLittleEndianShort(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                                  Short,
                                  WkSzSignedLittleEndianShortReader,
                                  WkSzSignedLittleEndianShortWriter,
                                  WkSzSignedLittleEndianShort>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSzSignedLittleEndianShortReader(i,xs,axb,xkc,dc).operationCore,
                                      WkSzLittleEndianSignedShortReadEngine.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSzSignedLittleEndianShortWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSzLittleEndianShortWriteEngine.FACTORY,
                                      this,
                                      Short.class);
  }

  @Override
  public Class<Short> rxClass() {
    return Short.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super WkSzSignedLittleEndianShortReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}