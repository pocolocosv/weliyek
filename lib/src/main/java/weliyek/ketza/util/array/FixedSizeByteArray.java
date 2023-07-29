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
package weliyek.ketza.util.array;

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
import weliyek.amat.basic.aggregator.sequence.SequenceFixedSizeParameter;
import weliyek.amat2.protocol.filter.FieldTester;

public class FixedSizeByteArray
    implements ByteArrayDefinition<
                        FixedSizeByteArrayDeserializing>,
               FixedSizePrimitiveArraySerializerDefinition<
                        ByteArrayWrapper,
                        FixedSizeByteArrayDeserializing>
{

  public static PacketStructure<
                      ByteArrayWrapper,
                      OperationSettings,
                      FixedSizeByteArray,
                      FixedSizeByteArrayDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,
                      FixedSizeByteArray,
                      FixedSizeByteArraySerializing,
                      OutputBytestreamGeneralBase<?>,
                      FixedSizeByteArray>
  newPacketStructure(
    String label,
    int expectedLength) {
    return new PacketStructure<>(
                      label,
                      (pc) -> FixedSizeByteArray.newCore(expectedLength, pc),
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static DefinitionSegmentCore<
                      ByteArrayWrapper,
                      OperationSettings,?,?,
                      FixedSizeByteArray,
                      FixedSizeByteArrayDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,?,?,
                      FixedSizeByteArray,
                      FixedSizeByteArraySerializing,
                      OutputBytestreamGeneralBase<?>,
                      FixedSizeByteArray,?>
  newCore(
    int expectedLength,
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new FixedSizeByteArray(expectedLength, componentCore).definitionCore;
  }

  private final SimplifiedPrimitiveArraySerializerCore<
                        ByteArrayWrapper,
                        OperationSettings,
                        FixedSizeByteArrayDeserializing,
                        OperationSettings,
                        FixedSizeByteArraySerializing,
                        FixedSizeByteArray> definitionCore;
  private final SequenceFixedSizeParameter<ByteArrayWrapper> fixedSizeParameter;

  private FixedSizeByteArray(
    int expectedLength,
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedPrimitiveArraySerializerCore<
        ByteArrayWrapper,
        OperationSettings,
        FixedSizeByteArrayDeserializing,
        OperationSettings,
        FixedSizeByteArraySerializing,
        FixedSizeByteArray>(
                                    1024, // de/serialization step size
                                    componentCore,
                                    FixedSizeByteArray::getRxRequestedLengthFromDefinition,
                                    (i,xs,axb,xkc,dc) -> new FixedSizeByteArrayDeserializing(i,xs,axb,xkc,dc).operationCore,
                                    ByteArrayWrapperInputSerialization.FACTORY,
                                    (SerializingPrimitiveArrayLengthProvider<ByteArrayWrapper,OperationSettings,FixedSizeByteArray>)FixedSizeByteArray::getTxRequestedLengthFromDefinition,
                                    (i,y,ys,ayb,ykc,dc) -> new FixedSizeByteArraySerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                    ByteArrayWrapperOutputSerialization.FACTORY,
                                    this,
                                    ByteArrayWrapper.class);
    this.fixedSizeParameter = new SequenceFixedSizeParameter<ByteArrayWrapper>(expectedLength, this.definitionCore);
  }

  private static int getRxRequestedLengthFromDefinition(OperationSettings none, FixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  private static int getTxRequestedLengthFromDefinition(ByteArrayWrapper wrapper, OperationSettings none, FixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  @Override
  public Class<ByteArrayWrapper> rxClass() {
    return ByteArrayWrapper.class;
  }

  @Override
  public List<SubcomponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super FixedSizeByteArrayDeserializing> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public int getExpectedLength() {
    return this.fixedSizeParameter.sequenceExpectedSize();
  }

  @Override
  public int getSerializationStepSize() {
    return this.definitionCore.getSerializationStepSize();
  }

}
