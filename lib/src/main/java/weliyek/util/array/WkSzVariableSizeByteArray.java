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
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.filter.FieldTester;
import weliyek.serialization.sequence.SequenceSizeParameters;

public class WkSzVariableSizeByteArray
    implements WkSzByteArrayDefinition<
                        WkSzVariableSizeByteArrayReader>,
               WkSzVariableSizePrimitiveArraySerializerDefinition<
                        WkByteArray,
                        WkSzVariableSizeByteArrayReader>
{

  public static WkSzStruct<
                      WkByteArray,
                      WkSzVariableLengthOperationSettings,
                      WkSzVariableSizeByteArray,
                      WkSzVariableSizeByteArrayReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,
                      WkSzVariableSizeByteArray,
                      WkSzVariableSizeByteArrayWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzVariableSizeByteArray>
  newPacketStructure(String label, int minSize, int maxSize) {
    return new WkSzStruct<>(
                      label,
                      (pc) -> WkSzVariableSizeByteArray.newCore(minSize, maxSize, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static
  WkSzDefinitionCore<
                      WkByteArray,
                      WkSzVariableLengthOperationSettings,?,?,
                      WkSzVariableSizeByteArray,
                      WkSzVariableSizeByteArrayReader,
                      WkSzInputBytestreamBase<?>,
                      WkSzOperationSettings,?,?,
                      WkSzVariableSizeByteArray,
                      WkSzVariableSizeByteArrayWriter,
                      WkSzOutputBytestreamBase<?>,
                      WkSzVariableSizeByteArray,?>
  newCore(
    int minSize,
    int maxSize,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSzVariableSizeByteArray(minSize, maxSize, componentCore).definitionCore;
  }

  private final SimplifiedPrimitiveArraySerializerCore<
                        WkByteArray,
                        WkSzVariableLengthOperationSettings,
                        WkSzVariableSizeByteArrayReader,
                        WkSzOperationSettings,
                        WkSzVariableSizeByteArrayWriter,
                        WkSzVariableSizeByteArray> definitionCore;
  private final SequenceSizeParameters<WkByteArray> sizeLimits;

  private WkSzVariableSizeByteArray(
    int minSize,
    int maxSize,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedPrimitiveArraySerializerCore<
        WkByteArray,
        WkSzVariableLengthOperationSettings,
        WkSzVariableSizeByteArrayReader,
        WkSzOperationSettings,
        WkSzVariableSizeByteArrayWriter,
        WkSzVariableSizeByteArray>(
                                  1024, // de/serialization step size
                                  componentCore,
                                  WkSzVariableSizeByteArray::getRxRequestedLength,
                                  (i,xs,axb,xkc,dc) -> new WkSzVariableSizeByteArrayReader(i,xs,axb,xkc,dc).operationCore,
                                  WkSzByteArrayWrapperReadEngine.FACTORY,
                                  (SerializingPrimitiveArrayLengthProvider<WkByteArray,WkSzOperationSettings,WkSzVariableSizeByteArray>)WkSzVariableSizeByteArray::getTxRequestedLength,
                                  (i,y,ys,ayb,ykc,dc) -> new WkSzVariableSizeByteArrayWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                  WkSzByteArrayWrapperWriteEngine.FACTORY,
                                  this,
                                  WkByteArray.class);
    this.sizeLimits = new SequenceSizeParameters<>(minSize, maxSize, definitionCore);
  }

  private static int getRxRequestedLength(WkSzVariableLengthOperationSettings settings, WkSzVariableSizeByteArray definition) {
    return settings.getRequestedLength();
  }

  private static int getTxRequestedLength(
    WkByteArray wrapper,
    WkSzOperationSettings settings,
    WkSzVariableSizeByteArray definition) {
    return wrapper.getLength();
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
  makeTester(Predicate<? super WkSzVariableSizeByteArrayReader> test, String description) {
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
