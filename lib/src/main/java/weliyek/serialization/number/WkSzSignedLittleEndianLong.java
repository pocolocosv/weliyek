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

public class WkSzSignedLittleEndianLong
    implements WkSzNumberDefinition<
                        Long,
                        WkSzSignedLittleEndianLongReader>
{

  public static WkSzStruct<
                        Long,
                        WkSzOperationSettings,
                        WkSzSignedLittleEndianLong,
                        WkSzSignedLittleEndianLongReader,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkSzSignedLittleEndianLong,
                        WkSzSignedLittleEndianLongWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSzSignedLittleEndianLong>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzSignedLittleEndianLong::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                        Long,
                        WkSzOperationSettings,?,?,
                        WkSzSignedLittleEndianLong,
                        WkSzSignedLittleEndianLongReader,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,?,?,
                        WkSzSignedLittleEndianLong,
                        WkSzSignedLittleEndianLongWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSzSignedLittleEndianLong,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzSignedLittleEndianLong(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Long,
                        WkSzSignedLittleEndianLongReader,
                        WkSzSignedLittleEndianLongWriter,
                        WkSzSignedLittleEndianLong> definitionCore;

  private WkSzSignedLittleEndianLong(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                                  Long,
                                  WkSzSignedLittleEndianLongReader,
                                  WkSzSignedLittleEndianLongWriter,
                                  WkSzSignedLittleEndianLong>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSzSignedLittleEndianLongReader(i,xs,axb,xkc,dc).operationCore,
                                      LittleEndianSignedLongInputSerialization.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSzSignedLittleEndianLongWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      LittleEndianLongOutputSerializationRule.FACTORY,
                                      this,
                                      Long.class);
  }

  @Override
  public Class<Long> rxClass() {
    return Long.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super WkSzSignedLittleEndianLongReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
