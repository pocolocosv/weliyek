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

public class SignedLittleEndianLong
    implements NumberDefinition<
                        Long,
                        SignedLittleEndianLongDeserializing>
{

  public static PacketStructure<
                        Long,
                        OperationSettings,
                        SignedLittleEndianLong,
                        SignedLittleEndianLongDeserializing,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,
                        SignedLittleEndianLong,
                        SignedLittleEndianLongSerializing,
                        OutputBytestreamGeneralBase<?>,
                        SignedLittleEndianLong>
  newPacketStructure(String label) {
    return new PacketStructure<>(
                      label,
                      SignedLittleEndianLong::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static DefinitionSegmentCore<
                        Long,
                        OperationSettings,?,?,
                        SignedLittleEndianLong,
                        SignedLittleEndianLongDeserializing,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        SignedLittleEndianLong,
                        SignedLittleEndianLongSerializing,
                        OutputBytestreamGeneralBase<?>,
                        SignedLittleEndianLong,?>
  newCore(ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new SignedLittleEndianLong(componentCore).definitionCore;
  }

  private final SimplifiedNumberSerializerCore<
                        Long,
                        SignedLittleEndianLongDeserializing,
                        SignedLittleEndianLongSerializing,
                        SignedLittleEndianLong> definitionCore;

  private SignedLittleEndianLong(
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedNumberSerializerCore<
                                  Long,
                                  SignedLittleEndianLongDeserializing,
                                  SignedLittleEndianLongSerializing,
                                  SignedLittleEndianLong>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new SignedLittleEndianLongDeserializing(i,xs,axb,xkc,dc).operationCore,
                                      LittleEndianSignedLongInputSerialization.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new SignedLittleEndianLongSerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                      LittleEndianLongOutputSerializationRule.FACTORY,
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
  makeTester(Predicate<? super SignedLittleEndianLongDeserializing> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
