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

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzCountingInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.WkSzCountingOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.basic.aggregator.sequence.SequenceFixedSizeParameter;
import weliyek.amat2.protocol.filter.FieldTester;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.base.WkSzDefinitionCore;
import weliyek.serialization.base.WkSzStructComponentCoreBase;

public class FixedSizeByteArray
    implements WkSzByteArrayDefinition<
                        FixedSizeByteArrayDeserializing>,
               FixedSizePrimitiveArraySerializerDefinition<
                        ByteArrayWrapper,
                        FixedSizeByteArrayDeserializing>
{

  public static WkSzStruct<
                      ByteArrayWrapper,
                      WkSzOperationSettings,
                      FixedSizeByteArray,
                      FixedSizeByteArrayDeserializing,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      FixedSizeByteArray,
                      FixedSizeByteArraySerializing,
                      WkSzOutputBytestreamBase<?>,
                      FixedSizeByteArray>
  newPacketStructure(
    String label,
    int expectedLength) {
    return new WkSzStruct<>(
                      label,
                      (pc) -> FixedSizeByteArray.newCore(expectedLength, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                      ByteArrayWrapper,
                      WkSzOperationSettings,?,?,
                      FixedSizeByteArray,
                      FixedSizeByteArrayDeserializing,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,?,?,
                      FixedSizeByteArray,
                      FixedSizeByteArraySerializing,
                      WkSzOutputBytestreamBase<?>,
                      FixedSizeByteArray,?>
  newCore(
    int expectedLength,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new FixedSizeByteArray(expectedLength, componentCore).definitionCore;
  }

  private final SimplifiedPrimitiveArraySerializerCore<
                        ByteArrayWrapper,
                        WkSzOperationSettings,
                        FixedSizeByteArrayDeserializing,
                        WkSzOperationSettings,
                        FixedSizeByteArraySerializing,
                        FixedSizeByteArray> definitionCore;
  private final SequenceFixedSizeParameter<ByteArrayWrapper> fixedSizeParameter;

  private FixedSizeByteArray(
    int expectedLength,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedPrimitiveArraySerializerCore<
        ByteArrayWrapper,
        WkSzOperationSettings,
        FixedSizeByteArrayDeserializing,
        WkSzOperationSettings,
        FixedSizeByteArraySerializing,
        FixedSizeByteArray>(
                                    1024, // de/serialization step size
                                    componentCore,
                                    FixedSizeByteArray::getRxRequestedLengthFromDefinition,
                                    (i,xs,axb,xkc,dc) -> new FixedSizeByteArrayDeserializing(i,xs,axb,xkc,dc).operationCore,
                                    ByteArrayWrapperInputSerialization.FACTORY,
                                    (SerializingPrimitiveArrayLengthProvider<ByteArrayWrapper,WkSzOperationSettings,FixedSizeByteArray>)FixedSizeByteArray::getTxRequestedLengthFromDefinition,
                                    (i,y,ys,ayb,ykc,dc) -> new FixedSizeByteArraySerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                    ByteArrayWrapperOutputSerialization.FACTORY,
                                    this,
                                    ByteArrayWrapper.class);
    this.fixedSizeParameter = new SequenceFixedSizeParameter<ByteArrayWrapper>(expectedLength, this.definitionCore);
  }

  private static int getRxRequestedLengthFromDefinition(WkSzOperationSettings none, FixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  private static int getTxRequestedLengthFromDefinition(ByteArrayWrapper wrapper, WkSzOperationSettings none, FixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  @Override
  public Class<ByteArrayWrapper> rxClass() {
    return ByteArrayWrapper.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
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
