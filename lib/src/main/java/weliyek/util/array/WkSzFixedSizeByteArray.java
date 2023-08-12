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
package weliyek.util.array;

import java.util.List;
import java.util.function.Predicate;

import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzDefinitionCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.filter.FieldTester;
import weliyek.serialization.sequence.SequenceFixedSizeParameter;

public class WkSzFixedSizeByteArray
    implements WkSzByteArrayDefinition<
                        WkSzFixedSizeByteArrayReader>,
               WkSzFixedSizePrimitiveArraySerializerDefinition<
                        WkByteArray,
                        WkSzFixedSizeByteArrayReader>
{

  public static WkSzStruct<
                      WkByteArray,
                      WkSzOperationSettings,
                      WkSzFixedSizeByteArray,
                      WkSzFixedSizeByteArrayReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      WkSzFixedSizeByteArray,
                      WkSzFixedSizeByteArrayWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzFixedSizeByteArray>
  newPacketStructure(
    String label,
    int expectedLength) {
    return new WkSzStruct<>(
                      label,
                      (pc) -> WkSzFixedSizeByteArray.newCore(expectedLength, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                      WkByteArray,
                      WkSzOperationSettings,?,?,
                      WkSzFixedSizeByteArray,
                      WkSzFixedSizeByteArrayReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,?,?,
                      WkSzFixedSizeByteArray,
                      WkSzFixedSizeByteArrayWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzFixedSizeByteArray,?>
  newCore(
    int expectedLength,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzFixedSizeByteArray(expectedLength, componentCore).definitionCore;
  }

  private final SimplifiedPrimitiveArraySerializerCore<
                        WkByteArray,
                        WkSzOperationSettings,
                        WkSzFixedSizeByteArrayReader,
                        WkSzOperationSettings,
                        WkSzFixedSizeByteArrayWriter,
                        WkSzFixedSizeByteArray> definitionCore;
  private final SequenceFixedSizeParameter<WkByteArray> fixedSizeParameter;

  private WkSzFixedSizeByteArray(
    int expectedLength,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedPrimitiveArraySerializerCore<
        WkByteArray,
        WkSzOperationSettings,
        WkSzFixedSizeByteArrayReader,
        WkSzOperationSettings,
        WkSzFixedSizeByteArrayWriter,
        WkSzFixedSizeByteArray>(
                                    1024, // de/serialization step size
                                    componentCore,
                                    WkSzFixedSizeByteArray::getRxRequestedLengthFromDefinition,
                                    (i,xs,axb,xkc,dc) -> new WkSzFixedSizeByteArrayReader(i,xs,axb,xkc,dc).operationCore,
                                    WkSzByteArrayWrapperReadEngine.FACTORY,
                                    (SerializingPrimitiveArrayLengthProvider<WkByteArray,WkSzOperationSettings,WkSzFixedSizeByteArray>)WkSzFixedSizeByteArray::getTxRequestedLengthFromDefinition,
                                    (i,y,ys,ayb,ykc,dc) -> new WkSzFixedSizeByteArrayWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                    WkSzByteArrayWrapperWriteEngine.FACTORY,
                                    this,
                                    WkByteArray.class);
    this.fixedSizeParameter = new SequenceFixedSizeParameter<WkByteArray>(expectedLength, this.definitionCore);
  }

  private static int getRxRequestedLengthFromDefinition(WkSzOperationSettings none, WkSzFixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  private static int getTxRequestedLengthFromDefinition(WkByteArray wrapper, WkSzOperationSettings none, WkSzFixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  @Override
  public Class<WkByteArray> rxClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super WkSzFixedSizeByteArrayReader> test, String description) {
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
