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

import java.util.function.ToIntBiFunction;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.WkSzSequenceReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.WkSzWritingResult;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.basic.sequence.WkSzBasicSequenceReadingRuntime;
import weliyek.amat.basic.sequence.WkSzBasicSequenceWritingRuntime;
import weliyek.amat.basic.sequence.WkSzSequenceReadingRuntimeControl;
import weliyek.amat.basic.sequence.WkSzSequenceWritingRuntime;
import weliyek.amat.basic.sequence.WkSzSequenceWritingRuntimeControl;
import weliyek.amat.basic.serializer.InputDeserializerFactory;
import weliyek.amat.basic.serializer.OutputSerializerFactory;
import weliyek.amat.basic.serializer.PrimitiveArraySerializerDefinition;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerReader;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerWriter;
import weliyek.serialization.base.WkSzStructComponentCoreBase;

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
          BasicReadingResult::new,
          readingOpFactory,
          rxSerializerFactory,
          txRequestedLengthEvaluator,
          WkSzBasicSequenceWritingRuntime::new,
          BasicWritingResult::empty,
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
