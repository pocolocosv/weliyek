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
import weliyek.amat.basic.aggregator.sequence.SequenceSizeParameters;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableLengthOperationSettings;
import weliyek.amat2.protocol.filter.FieldTester;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.base.WkSzDefinitionCore;
import weliyek.serialization.base.WkSzStructComponentCoreBase;

public class VariableSizeByteArray
    implements WkSzByteArrayDefinition<
                        VariableSizeByteArrayDeserializing>,
               VariableSizePrimitiveArraySerializerDefinition<
                        ByteArrayWrapper,
                        VariableSizeByteArrayDeserializing>
{

  public static WkSzStruct<
                      ByteArrayWrapper,
                      WkSzVariableLengthOperationSettings,
                      VariableSizeByteArray,
                      VariableSizeByteArrayDeserializing,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      VariableSizeByteArray,
                      VariableSizeByteArraySerializing,
                      WkSzOutputBytestreamBase<?>,
                      VariableSizeByteArray>
  newPacketStructure(String label, int minSize, int maxSize) {
    return new WkSzStruct<>(
                      label,
                      (pc) -> VariableSizeByteArray.newCore(minSize, maxSize, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static
  WkSzDefinitionCore<
                      ByteArrayWrapper,
                      WkSzVariableLengthOperationSettings,?,?,
                      VariableSizeByteArray,
                      VariableSizeByteArrayDeserializing,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,?,?,
                      VariableSizeByteArray,
                      VariableSizeByteArraySerializing,
                      WkSzOutputBytestreamBase<?>,
                      VariableSizeByteArray,?>
  newCore(
    int minSize,
    int maxSize,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new VariableSizeByteArray(minSize, maxSize, componentCore).definitionCore;
  }

  private final SimplifiedPrimitiveArraySerializerCore<
                        ByteArrayWrapper,
                        WkSzVariableLengthOperationSettings,
                        VariableSizeByteArrayDeserializing,
                        WkSzOperationSettings,
                        VariableSizeByteArraySerializing,
                        VariableSizeByteArray> definitionCore;
  private final SequenceSizeParameters<ByteArrayWrapper> sizeLimits;

  private VariableSizeByteArray(
    int minSize,
    int maxSize,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedPrimitiveArraySerializerCore<
        ByteArrayWrapper,
        WkSzVariableLengthOperationSettings,
        VariableSizeByteArrayDeserializing,
        WkSzOperationSettings,
        VariableSizeByteArraySerializing,
        VariableSizeByteArray>(
                                  1024, // de/serialization step size
                                  componentCore,
                                  VariableSizeByteArray::getRxRequestedLength,
                                  (i,xs,axb,xkc,dc) -> new VariableSizeByteArrayDeserializing(i,xs,axb,xkc,dc).operationCore,
                                  ByteArrayWrapperInputSerialization.FACTORY,
                                  (SerializingPrimitiveArrayLengthProvider<ByteArrayWrapper,WkSzOperationSettings,VariableSizeByteArray>)VariableSizeByteArray::getTxRequestedLength,
                                  (i,y,ys,ayb,ykc,dc) -> new VariableSizeByteArraySerializing(i,y,ys,ayb,ykc,dc).operationCore,
                                  ByteArrayWrapperOutputSerialization.FACTORY,
                                  this,
                                  ByteArrayWrapper.class);
    this.sizeLimits = new SequenceSizeParameters<>(minSize, maxSize, definitionCore);
  }

  private static int getRxRequestedLength(WkSzVariableLengthOperationSettings settings, VariableSizeByteArray definition) {
    return settings.getRequestedLength();
  }

  private static int getTxRequestedLength(
    ByteArrayWrapper wrapper,
    WkSzOperationSettings settings,
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
