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

public class SignedByte
    implements NumberDefinition<
                        Byte,
                        SignedByteDeserializing>
{

  public static PacketStructure<
                      Byte,
                      OperationSettings,
                      SignedByte,
                      SignedByteDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,
                      SignedByte,
                      SignedByteSerializing,
                      OutputBytestreamGeneralBase<?>,
                      SignedByte>
  newPacketStructure(String label) {
    return new PacketStructure<>(
                      label,
                      SignedByte::newCore,
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static DefinitionSegmentCore<
                      Byte,
                      OperationSettings,?,?,
                      SignedByte,
                      SignedByteDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,?,?,
                      SignedByte,
                      SignedByteSerializing,
                      OutputBytestreamGeneralBase<?>,
                      SignedByte,?>
  newCore(ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new SignedByte(componentCore).definitionCore;
  }

  private SimplifiedNumberSerializerCore<
                        Byte,
                        SignedByteDeserializing,
                        SignedByteSerializing,
                        SignedByte> definitionCore;

  private SignedByte(ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedNumberSerializerCore<
                                Byte,
                                SignedByteDeserializing,
                                SignedByteSerializing,
                                SignedByte>(
                                    componentCore,
                                    (i,xs,xab,xkc,xdc) -> new SignedByteDeserializing(i,xs,xab,xkc,xdc).operationCore,
                                    SignedByteInputSerialization.FACTORY,
                                    (i,y,ys,yab,ykc,ydc) -> new SignedByteSerializing(i,y,ys,yab,ykc,ydc).writingCore,
                                    ByteOutputSerializer.FACTORY,
                                    this,
                                    Byte.class);
  }

  @Override
  public Class<Byte> rxClass() {
    return Byte.class;
  }

  @Override
  public List<SubcomponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?,?>
  makeTester(Predicate<? super SignedByteDeserializing> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
