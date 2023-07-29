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
import weliyek.amat.newserialization.basic.LittleEndianShortOutputSerializer;
import weliyek.amat2.protocol.filter.FieldTester;

public class SignedLittleEndianShort
    implements NumberDefinition<
                        Short,
                        SignedLittleEndianShortDeserializing>
{

  public static PacketStructure<
                      Short,
                      OperationSettings,
                      SignedLittleEndianShort,
                      SignedLittleEndianShortDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,
                      SignedLittleEndianShort,
                      SignedLittleEndianShortSerializing,
                      OutputBytestreamGeneralBase<?>,
                      SignedLittleEndianShort>
  newPacketStructure(String label) {
    return new PacketStructure<>(
                      label,
                      SignedLittleEndianShort::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static DefinitionSegmentCore<
                      Short,
                      OperationSettings,?,?,
                      SignedLittleEndianShort,
                      SignedLittleEndianShortDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,?,?,
                      SignedLittleEndianShort,
                      SignedLittleEndianShortSerializing,
                      OutputBytestreamGeneralBase<?>,
                      SignedLittleEndianShort,?>
  newCore(ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new SignedLittleEndianShort(componentCore).definitionCore;
  }

  private final SimplifiedNumberSerializerCore<
                        Short,
                        SignedLittleEndianShortDeserializing,
                        SignedLittleEndianShortSerializing,
                        SignedLittleEndianShort> definitionCore;

  public SignedLittleEndianShort(
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedNumberSerializerCore<
                                  Short,
                                  SignedLittleEndianShortDeserializing,
                                  SignedLittleEndianShortSerializing,
                                  SignedLittleEndianShort>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new SignedLittleEndianShortDeserializing(i,xs,axb,xkc,dc).operationCore,
                                      LittleEndianSignedShortInputSerialization.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new SignedLittleEndianShortSerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                      LittleEndianShortOutputSerializer.FACTORY,
                                      this,
                                      Short.class);
  }

  @Override
  public Class<Short> rxClass() {
    return Short.class;
  }

  @Override
  public List<SubcomponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super SignedLittleEndianShortDeserializing> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
