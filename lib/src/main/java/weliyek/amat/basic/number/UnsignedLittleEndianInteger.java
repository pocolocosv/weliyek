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

import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegmentCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketStructure;
import weliyek.amat.base.SubcomponentHandler;
import weliyek.amat.base.input.CountingInputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.CountingOutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat2.protocol.filter.FieldTester;

public class UnsignedLittleEndianInteger
    implements NumberDefinition<
                        Long,
                        UnsignedIntegerLittleEndianReading>
{

  public static PacketStructure<
                        Long,
                        OperationSettings,
                        UnsignedLittleEndianInteger,
                        UnsignedIntegerLittleEndianReading,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,
                        UnsignedLittleEndianInteger,
                        UnsignedLittleEndianIntegerSerializing,
                        OutputBytestreamGeneralBase<?>,
                        UnsignedLittleEndianInteger>
  newPacketStructure(String label) {
    return new PacketStructure<>(
                      label,
                      UnsignedLittleEndianInteger::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static DefinitionSegmentCore<
                        Long,
                        OperationSettings,?,?,
                        UnsignedLittleEndianInteger,
                        UnsignedIntegerLittleEndianReading,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        UnsignedLittleEndianInteger,
                        UnsignedLittleEndianIntegerSerializing,
                        OutputBytestreamGeneralBase<?>,
                        UnsignedLittleEndianInteger,?>
  newCore(ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new UnsignedLittleEndianInteger(componentCore).definitionCore;
  }

  private final SimplifiedNumberSerializerCore<
                        Long,
                        UnsignedIntegerLittleEndianReading,
                        UnsignedLittleEndianIntegerSerializing,
                        UnsignedLittleEndianInteger> definitionCore;

  private UnsignedLittleEndianInteger(
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedNumberSerializerCore<
                                  Long,
                                  UnsignedIntegerLittleEndianReading,
                                  UnsignedLittleEndianIntegerSerializing,
                                  UnsignedLittleEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new UnsignedIntegerLittleEndianReading(i,xs,axb,xkc,dc).operationCore,
                                      LittleEndianUnsignedIntegerInputSerialization.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new UnsignedLittleEndianIntegerSerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                      UnsignedLittleEndianIntegerOutputSerializer.FACTORY,
                                      this,
                                      Long.class);
  }

  @Override
  public Class<Long> rxClass() {
    return Long.class;
  }

  @Override
  public List<SubcomponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super UnsignedIntegerLittleEndianReading> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
