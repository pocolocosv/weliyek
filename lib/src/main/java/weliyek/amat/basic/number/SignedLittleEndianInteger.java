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
import weliyek.amat.newserialization.basic.LittleEndianIntegerOutputSerializer;
import weliyek.amat2.protocol.filter.FieldTester;

public class SignedLittleEndianInteger
    implements NumberDefinition<
                        Integer,
                        SignedLittleEndianIntegerDeserializing>
{

  public static PacketStructure<
                        Integer,
                        OperationSettings,
                        SignedLittleEndianInteger,
                        SignedLittleEndianIntegerDeserializing,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,
                        SignedLittleEndianInteger,
                        SignedLittleEndianIntegerSerializing,
                        OutputBytestreamGeneralBase<?>,
                        SignedLittleEndianInteger>
  newPacketStructure(String label) {
    return new PacketStructure<>(
                      label,
                      SignedLittleEndianInteger::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static DefinitionSegmentCore<
                        Integer,
                        OperationSettings,?,?,
                        SignedLittleEndianInteger,
                        SignedLittleEndianIntegerDeserializing,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        SignedLittleEndianInteger,
                        SignedLittleEndianIntegerSerializing,
                        OutputBytestreamGeneralBase<?>,
                        SignedLittleEndianInteger,?>
  newCore(ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new SignedLittleEndianInteger(componentCore).definitionCore;
  }

  private final SimplifiedNumberSerializerCore<
                        Integer,
                        SignedLittleEndianIntegerDeserializing,
                        SignedLittleEndianIntegerSerializing,
                        SignedLittleEndianInteger> definitionCore;

  public SignedLittleEndianInteger(
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedNumberSerializerCore<
                                  Integer,
                                  SignedLittleEndianIntegerDeserializing,
                                  SignedLittleEndianIntegerSerializing,
                                  SignedLittleEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new SignedLittleEndianIntegerDeserializing(i,xs,axb,xkc,dc).operationCore,
                                      LittleEndianSignedIntegerInputSerialization.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new SignedLittleEndianIntegerSerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                      LittleEndianIntegerOutputSerializer.FACTORY,
                                      this,
                                      Integer.class);
  }

  @Override
  public Class<Integer> rxClass() {
    return Integer.class;
  }

  @Override
  public List<SubcomponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super SignedLittleEndianIntegerDeserializing> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
