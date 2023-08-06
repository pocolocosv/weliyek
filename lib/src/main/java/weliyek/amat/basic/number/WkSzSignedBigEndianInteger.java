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

public class WkSzSignedBigEndianInteger
    implements WkSzNumberDefinition<
                        Integer,
                        WkSzSignedBigEndianIntegerReader>
{

  public static WkSzStruct<
                        Integer,
                        OperationSettings,
                        WkSzSignedBigEndianInteger,
                        WkSzSignedBigEndianIntegerReader,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,
                        WkSzSignedBigEndianInteger,
                        WkSzSignedBigEndianIntegerWriter,
                        OutputBytestreamGeneralBase<?>,
                        WkSzSignedBigEndianInteger>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzSignedBigEndianInteger::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                        Integer,
                        OperationSettings,?,?,
                        WkSzSignedBigEndianInteger,
                        WkSzSignedBigEndianIntegerReader,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        WkSzSignedBigEndianInteger,
                        WkSzSignedBigEndianIntegerWriter,
                        OutputBytestreamGeneralBase<?>,
                        WkSzSignedBigEndianInteger,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzSignedBigEndianInteger(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Integer,
                        WkSzSignedBigEndianIntegerReader,
                        WkSzSignedBigEndianIntegerWriter,
                        WkSzSignedBigEndianInteger> definitionCore;

  private WkSzSignedBigEndianInteger(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                                  Integer,
                                  WkSzSignedBigEndianIntegerReader,
                                  WkSzSignedBigEndianIntegerWriter,
                                  WkSzSignedBigEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSzSignedBigEndianIntegerReader(i,xs,axb,xkc,dc).operationCore,
                                      BigEndianSignedIntegerInputSerialization.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSzSignedBigEndianIntegerWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      BigEndianIntegerOutputSerializer.FACTORY,
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
  public FieldTester<?,?>
  makeTester(Predicate<? super WkSzSignedBigEndianIntegerReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
