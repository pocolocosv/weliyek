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
package weliyek.serialization.util.array;

import java.util.function.ToIntBiFunction;

import weliyek.serialization.InputDeserializerFactory;
import weliyek.serialization.OutputSerializerFactory;
import weliyek.serialization.PacketInputFieldReadingFactory;
import weliyek.serialization.PacketOutputFieldWritingFactory;
import weliyek.serialization.PrimitiveArraySerializerDefinition;
import weliyek.serialization.WkSzBasicReadingResult;
import weliyek.serialization.WkSzBasicSequenceReadingRuntime;
import weliyek.serialization.WkSzBasicWritingResult;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPrimitiveArraySerializerReader;
import weliyek.serialization.WkSzPrimitiveArraySerializerWriter;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzSequenceReadingRuntime;
import weliyek.serialization.WkSzSequenceReadingRuntimeControl;
import weliyek.serialization.WkSzSequenceWritingRuntime;
import weliyek.serialization.WkSzSequenceWritingRuntimeControl;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.sequence.WkSzBasicSequenceWritingRuntime;

public class SimplifiedPrimitiveArraySerializerCore<
                        T extends PrimitiveArrayWrapper<?, ?>,
                        XS extends WkSzOperationSettings,
                        XO extends WkSzPrimitiveArraySerializerReader<
                                        T,
                                        XS,
                                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        D>,
                        YS extends WkSzOperationSettings,
                        YO extends WkSzPrimitiveArraySerializerWriter<
                                        T,
                                        YS,
                                        WkSzSequenceWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        D>,
                        D extends PrimitiveArraySerializerDefinition<T,XO>>
    extends PrimitiveArraySerializerCore<
                        T,
                        XS,
                        WkSzSequenceReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzSequenceReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        D, XO,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzSequenceWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzSequenceWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        D, YO,
                        WkSzOutputBytestreamBase<?>,
                        D,
                        SimplifiedPrimitiveArraySerializerCore<T,XS,XO,YS,YO,D>>
{

  public SimplifiedPrimitiveArraySerializerCore(
    int stepSize,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    ToIntBiFunction<? super XS, D> rxRequestedLengthEvaluator,
    PacketInputFieldReadingFactory<T, XS, D, SimplifiedPrimitiveArraySerializerCore<T, XS, XO, YS, YO, D>, XO, WkSzInputBytestreamBase<?>> readingOpFactory,
    InputDeserializerFactory<T, ? super WkSzSequenceReadingRuntimeControl<WkSzInputBytestream, WkSzInputBytestreamBase<? extends WkSzInputBytestream>, WkSzSequenceReadingRuntime<WkSzInputBytestream>>, ? super XO> rxSerializerFactory,
    SerializingPrimitiveArrayLengthProvider<? super T,? super YS,? super D> txRequestedLengthEvaluator,
    PacketOutputFieldWritingFactory<T, YS, D, SimplifiedPrimitiveArraySerializerCore<T, XS, XO, YS, YO, D>, YO, WkSzOutputBytestreamBase<?>> writingOpFactory,
    OutputSerializerFactory<T, ? super WkSzSequenceWritingRuntimeControl<WkSzOutputBytestream, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, WkSzSequenceWritingRuntime<WkSzOutputBytestream>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          stepSize,
          componentCore,
          rxRequestedLengthEvaluator,
          WkSzBasicSequenceReadingRuntime::new,
          WkSzBasicReadingResult::new,
          readingOpFactory,
          rxSerializerFactory,
          txRequestedLengthEvaluator,
          WkSzBasicSequenceWritingRuntime::new,
          WkSzBasicWritingResult::empty,
          writingOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected SimplifiedPrimitiveArraySerializerCore<T,XS,XO,YS,YO,D> getThis() {
    return this;
  }

}
