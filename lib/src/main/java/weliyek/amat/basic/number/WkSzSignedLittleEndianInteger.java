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
import weliyek.amat.base.input.CountingInputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.CountingOutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.newserialization.basic.LittleEndianIntegerOutputSerializer;
import weliyek.amat2.protocol.filter.FieldTester;

public class WkSzSignedLittleEndianInteger
    implements WkSzNumberDefinition<
                        Integer,
                        WkSzSignedLittleEndianIntegerReader>
{

  public static WkSzStruct<
                        Integer,
                        OperationSettings,
                        WkSzSignedLittleEndianInteger,
                        WkSzSignedLittleEndianIntegerReader,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,
                        WkSzSignedLittleEndianInteger,
                        WkSzSignedLittleEndianIntegerWriter,
                        OutputBytestreamGeneralBase<?>,
                        WkSzSignedLittleEndianInteger>
  newPacketStructure(String label) {
    return new WkSzStruct<>(
                      label,
                      WkSzSignedLittleEndianInteger::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                        Integer,
                        OperationSettings,?,?,
                        WkSzSignedLittleEndianInteger,
                        WkSzSignedLittleEndianIntegerReader,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        WkSzSignedLittleEndianInteger,
                        WkSzSignedLittleEndianIntegerWriter,
                        OutputBytestreamGeneralBase<?>,
                        WkSzSignedLittleEndianInteger,?>
  newCore(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzSignedLittleEndianInteger(componentCore).definitionCore;
  }

  private final WkSzSimplifiedNumberSerializerDefinitionCore<
                        Integer,
                        WkSzSignedLittleEndianIntegerReader,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger> definitionCore;

  public WkSzSignedLittleEndianInteger(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSzSimplifiedNumberSerializerDefinitionCore<
                                  Integer,
                                  WkSzSignedLittleEndianIntegerReader,
                                  WkSzSignedLittleEndianIntegerWriter,
                                  WkSzSignedLittleEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSzSignedLittleEndianIntegerReader(i,xs,axb,xkc,dc).operationCore,
                                      LittleEndianSignedIntegerInputSerialization.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSzSignedLittleEndianIntegerWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      LittleEndianIntegerOutputSerializer.FACTORY,
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
  makeTester(Predicate<? super WkSzSignedLittleEndianIntegerReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
