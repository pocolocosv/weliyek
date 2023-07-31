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

import weliyek.amat.base.WkSzStructComponentCore;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketStructure;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.CountingInputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.CountingOutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.basic.aggregator.sequence.SequenceSizeParameters;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat2.protocol.filter.FieldTester;

public class VariableSizeByteArray
    implements WkSzByteArrayDefinition<
                        VariableSizeByteArrayDeserializing>,
               VariableSizePrimitiveArraySerializerDefinition<
                        ByteArrayWrapper,
                        VariableSizeByteArrayDeserializing>
{

  public static PacketStructure<
                      ByteArrayWrapper,
                      VariableLengthSettings,
                      VariableSizeByteArray,
                      VariableSizeByteArrayDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,
                      VariableSizeByteArray,
                      VariableSizeByteArraySerializing,
                      OutputBytestreamGeneralBase<?>,
                      VariableSizeByteArray>
  newPacketStructure(String label, int minSize, int maxSize) {
    return new PacketStructure<>(
                      label,
                      (pc) -> VariableSizeByteArray.newCore(minSize, maxSize, pc),
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static
  WkSzDefinitionCore<
                      ByteArrayWrapper,
                      VariableLengthSettings,?,?,
                      VariableSizeByteArray,
                      VariableSizeByteArrayDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,?,?,
                      VariableSizeByteArray,
                      VariableSizeByteArraySerializing,
                      OutputBytestreamGeneralBase<?>,
                      VariableSizeByteArray,?>
  newCore(
    int minSize,
    int maxSize,
    WkSzStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new VariableSizeByteArray(minSize, maxSize, componentCore).definitionCore;
  }

  private final SimplifiedPrimitiveArraySerializerCore<
                        ByteArrayWrapper,
                        VariableLengthSettings,
                        VariableSizeByteArrayDeserializing,
                        OperationSettings,
                        VariableSizeByteArraySerializing,
                        VariableSizeByteArray> definitionCore;
  private final SequenceSizeParameters<ByteArrayWrapper> sizeLimits;

  private VariableSizeByteArray(
    int minSize,
    int maxSize,
    WkSzStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedPrimitiveArraySerializerCore<
        ByteArrayWrapper,
        VariableLengthSettings,
        VariableSizeByteArrayDeserializing,
        OperationSettings,
        VariableSizeByteArraySerializing,
        VariableSizeByteArray>(
                                  1024, // de/serialization step size
                                  componentCore,
                                  VariableSizeByteArray::getRxRequestedLength,
                                  (i,xs,axb,xkc,dc) -> new VariableSizeByteArrayDeserializing(i,xs,axb,xkc,dc).operationCore,
                                  ByteArrayWrapperInputSerialization.FACTORY,
                                  (SerializingPrimitiveArrayLengthProvider<ByteArrayWrapper,OperationSettings,VariableSizeByteArray>)VariableSizeByteArray::getTxRequestedLength,
                                  (i,y,ys,ayb,ykc,dc) -> new VariableSizeByteArraySerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                  ByteArrayWrapperOutputSerialization.FACTORY,
                                  this,
                                  ByteArrayWrapper.class);
    this.sizeLimits = new SequenceSizeParameters<>(minSize, maxSize, definitionCore);
  }

  private static int getRxRequestedLength(VariableLengthSettings settings, VariableSizeByteArray definition) {
    return settings.getRequestedLength();
  }

  private static int getTxRequestedLength(
    ByteArrayWrapper wrapper,
    OperationSettings settings,
    VariableSizeByteArray definition) {
    return wrapper.getLength();
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
  makeTester(Predicate<? super VariableSizeByteArrayDeserializing> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public int minimalSize() {
    return this.sizeLimits.minimalSize();
  }

  @Override
  public int maximalSize() {
    return this.sizeLimits.maximalSize();
  }

  @Override
  public int getSerializationStepSize() {
    return this.definitionCore.getSerializationStepSize();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
