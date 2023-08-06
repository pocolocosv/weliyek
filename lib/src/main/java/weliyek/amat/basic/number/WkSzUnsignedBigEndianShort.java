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
package weliyek.amat.basic.number;

import java.util.List;
import java.util.function.Predicate;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.WkSzStruct;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.WkSzCountingInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.WkSzCountingOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat2.protocol.filter.FieldTester;

public class WkSzUnsignedBigEndianShort
    implements WkSzNumberDefinition<
                        Integer,
                        WkSzUnsignedBigEndianShortReader>
{

  public static WkSzStruct<
                        Integer,
                        OperationSettings,
                        WkSzUnsignedBigEndianShort,
                        WkSzUnsignedBigEndianShortReader,
                        WkSzInputBytestreamBase<?>,
                        OperationSettings,
                        WkSzUnsignedBigEndianShort,
                        WkSzUnsignedBigEndianShortWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSzUnsignedBigEndianShort>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzUnsignedBigEndianShort::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                        Integer,
                        OperationSettings,?,?,
                        WkSzUnsignedBigEndianShort,
                        WkSzUnsignedBigEndianShortReader,
                        WkSzInputBytestreamBase<?>,
                        OperationSettings,?,?,
                        WkSzUnsignedBigEndianShort,
                        WkSzUnsignedBigEndianShortWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSzUnsignedBigEndianShort,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzUnsignedBigEndianShort(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Integer,
                        WkSzUnsignedBigEndianShortReader,
                        WkSzUnsignedBigEndianShortWriter,
                        WkSzUnsignedBigEndianShort> definitionCore;

  private WkSzUnsignedBigEndianShort(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                                  Integer,
                                  WkSzUnsignedBigEndianShortReader,
                                  WkSzUnsignedBigEndianShortWriter,
                                  WkSzUnsignedBigEndianShort>(
                                    componentCore,
                                    (i,xs,axb,xkc,dc) -> new WkSzUnsignedBigEndianShortReader(i,xs,axb,xkc,dc).operationCore,
                                    BigEndianUnsignedShortInputSerialization.FACTORY,
                                    (i,y,ys,ayb,ykc,dc) -> new WkSzUnsignedBigEndianShortWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                    UnsignedBigEndianShortOutputSerializer.FACTORY,
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
  makeTester(Predicate<? super WkSzUnsignedBigEndianShortReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
