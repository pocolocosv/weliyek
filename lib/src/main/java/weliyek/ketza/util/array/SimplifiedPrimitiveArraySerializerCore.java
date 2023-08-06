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

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.basic.sequence.BasicSequenceReadingRuntime;
import weliyek.amat.basic.sequence.BasicSequenceWritingRuntime;
import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;
import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;
import weliyek.amat.basic.serializer.InputDeserializerFactory;
import weliyek.amat.basic.serializer.OutputSerializerFactory;
import weliyek.amat.basic.serializer.PrimitiveArraySerializerDefinition;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerReader;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerWriter;
import weliyek.serialization.bytestream.InputBytestream;
import weliyek.serialization.bytestream.InputBytestreamGeneralBase;
import weliyek.serialization.bytestream.OutputBytestream;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public class SimplifiedPrimitiveArraySerializerCore<
                        T extends PrimitiveArrayWrapper<?, ?>,
                        XS extends OperationSettings,
                        XO extends WkSzPrimitiveArraySerializerReader<
                                        T,
                                        XS,
                                        SequenceReadingRuntime<InputBytestream>,
                                        DeserializingResult<T>,
                                        D>,
                        YS extends OperationSettings,
                        YO extends WkSzPrimitiveArraySerializerWriter<
                                        T,
                                        YS,
                                        SequenceWritingRuntime<OutputBytestream>,
                                        SerializingResult,
                                        D>,
                        D extends PrimitiveArraySerializerDefinition<T,XO>>
    extends PrimitiveArraySerializerCore<
                        T,
                        XS,
                        SequenceReadingRuntimeControl<
                          InputBytestream,
                          InputBytestreamGeneralBase<? extends InputBytestream>,
                          SequenceReadingRuntime<InputBytestream>>,
                        DeserializingResult<T>,
                        D, XO,
                        InputBytestreamGeneralBase<?>,
                        YS,
                        SequenceWritingRuntimeControl<
                          OutputBytestream,
                          OutputBytestreamGeneralBase<? extends OutputBytestream>,
                          SequenceWritingRuntime<OutputBytestream>>,
                        SerializingResult,
                        D, YO,
                        OutputBytestreamGeneralBase<?>,
                        D,
                        SimplifiedPrimitiveArraySerializerCore<T,XS,XO,YS,YO,D>>
{

  public SimplifiedPrimitiveArraySerializerCore(
    int stepSize,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    ToIntBiFunction<? super XS, D> rxRequestedLengthEvaluator,
    PacketInputFieldReadingFactory<T, XS, D, SimplifiedPrimitiveArraySerializerCore<T, XS, XO, YS, YO, D>, XO, InputBytestreamGeneralBase<?>> readingOpFactory,
    InputDeserializerFactory<T, ? super SequenceReadingRuntimeControl<InputBytestream, InputBytestreamGeneralBase<? extends InputBytestream>, SequenceReadingRuntime<InputBytestream>>, ? super XO> rxSerializerFactory,
    SerializingPrimitiveArrayLengthProvider<? super T,? super YS,? super D> txRequestedLengthEvaluator,
    PacketOutputFieldWritingFactory<T, YS, D, SimplifiedPrimitiveArraySerializerCore<T, XS, XO, YS, YO, D>, YO, OutputBytestreamGeneralBase<?>> writingOpFactory,
    OutputSerializerFactory<T, ? super SequenceWritingRuntimeControl<OutputBytestream, OutputBytestreamGeneralBase<? extends OutputBytestream>, SequenceWritingRuntime<OutputBytestream>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          stepSize,
          componentCore,
          rxRequestedLengthEvaluator,
          BasicSequenceReadingRuntime::new,
          BasicReadingResult::new,
          readingOpFactory,
          rxSerializerFactory,
          txRequestedLengthEvaluator,
          BasicSequenceWritingRuntime::new,
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
