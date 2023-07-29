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

public class SignedBigEndianLong
    implements NumberDefinition<
                        Long,
                        SignedBigEndianLongDeserializing>
{

  public static PacketStructure<
                        Long,
                        OperationSettings,
                        SignedBigEndianLong,
                        SignedBigEndianLongDeserializing,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,
                        SignedBigEndianLong,
                        SignedBigEndianLongSerializing,
                        OutputBytestreamGeneralBase<?>,
                        SignedBigEndianLong>
  newPacketStructure(String label) {
    return new PacketStructure<>(
                      label,
                      SignedBigEndianLong::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static DefinitionSegmentCore<
                        Long,
                        OperationSettings,?,?,
                        SignedBigEndianLong,
                        SignedBigEndianLongDeserializing,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        SignedBigEndianLong,
                        SignedBigEndianLongSerializing,
                        OutputBytestreamGeneralBase<?>,
                        SignedBigEndianLong,?>
  newCore(ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new SignedBigEndianLong(componentCore).definitionCore;
  }

  private final SimplifiedNumberSerializerCore<
                        Long,
                        SignedBigEndianLongDeserializing,
                        SignedBigEndianLongSerializing,
                        SignedBigEndianLong> definitionCore;

  private SignedBigEndianLong(
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    definitionCore = new SimplifiedNumberSerializerCore<
                            Long,
                            SignedBigEndianLongDeserializing,
                            SignedBigEndianLongSerializing,
                            SignedBigEndianLong>(
                                componentCore,
                                (i,xs,axb,xkc,dc) -> new SignedBigEndianLongDeserializing(i,xs,axb,xkc,dc).operationCore,
                                BigEndianSignedLongInputSerialization.FACTORY,
                                (i,y,ys,ayb,ykc,dc) -> new SignedBigEndianLongSerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                BigEndianLongOutputSerialization.FACTORY,
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
  public FieldTester<?,?>
  makeTester(Predicate<? super SignedBigEndianLongDeserializing> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
