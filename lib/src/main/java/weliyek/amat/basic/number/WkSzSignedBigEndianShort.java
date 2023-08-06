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
import weliyek.amat2.protocol.filter.FieldTester;
import weliyek.serialization.bytestream.CountingInputBytestream;
import weliyek.serialization.bytestream.CountingOutputBytestream;
import weliyek.serialization.bytestream.InputBytestreamGeneralBase;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public class WkSzSignedBigEndianShort
    implements WkSzNumberDefinition<
                        Short,
                        WkSzSignedBigEndianShortReader>
{

  public static WkSzStruct<
                      Short,
                      OperationSettings,
                      WkSzSignedBigEndianShort,
                      WkSzSignedBigEndianShortReader,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,
                      WkSzSignedBigEndianShort,
                      WkSzSignedBigEndianShortWriter,
                      OutputBytestreamGeneralBase<?>,
                      WkSzSignedBigEndianShort>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzSignedBigEndianShort::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                      Short,
                      OperationSettings,?,?,
                      WkSzSignedBigEndianShort,
                      WkSzSignedBigEndianShortReader,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,?,?,
                      WkSzSignedBigEndianShort,
                      WkSzSignedBigEndianShortWriter,
                      OutputBytestreamGeneralBase<?>,
                      WkSzSignedBigEndianShort,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzSignedBigEndianShort(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Short,
                        WkSzSignedBigEndianShortReader,
                        WkSzSignedBigEndianShortWriter,
                        WkSzSignedBigEndianShort> definitionCore;

  public WkSzSignedBigEndianShort(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                                  Short,
                                  WkSzSignedBigEndianShortReader,
                                  WkSzSignedBigEndianShortWriter,
                                  WkSzSignedBigEndianShort>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSzSignedBigEndianShortReader(i,xs,axb,xkc,dc).operationCore,
                                      BigEndianSignedShortInputSerialization.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSzSignedBigEndianShortWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      BigEndianShortOutputSerializer.FACTORY,
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
  public FieldTester<?,?>
  makeTester(Predicate<? super WkSzSignedBigEndianShortReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
