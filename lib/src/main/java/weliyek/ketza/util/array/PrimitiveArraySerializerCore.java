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

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;

import weliyek.amat.base.WkSzStructComponentCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;
import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;
import weliyek.amat.basic.serializer.InputDeserializerFactory;
import weliyek.amat.basic.serializer.OutputSerializerFactory;
import weliyek.amat.basic.serializer.PrimitiveArraySerializerDefinition;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerReader;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerWriter;
import weliyek.amat.basic.serializer.WkSzSerializerDefinitionCore;

public abstract class PrimitiveArraySerializerCore<
                        T extends PrimitiveArrayWrapper<?, ?>,
                        XS extends OperationSettings,
                        XQC extends SequenceReadingRuntimeControl<?,?,?>,
                        XR extends DeserializingResult<T>,
                        XD extends PrimitiveArraySerializerDefinition<T,XO>,
                        XO extends WkSzPrimitiveArraySerializerReader<T,XS,?,XR,XD>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YQC extends SequenceWritingRuntimeControl<?,?,?>,
                        YR extends SerializingResult,
                        YD extends PrimitiveArraySerializerDefinition<T,?>,
                        YO extends WkSzPrimitiveArraySerializerWriter<T,YS,?,YR,YD>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        D extends PrimitiveArraySerializerDefinition<T,XO>,
                        DC extends PrimitiveArraySerializerCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends WkSzSerializerDefinitionCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
    implements PrimitiveArraySerializerDefinition<T, XO>
{

  private final int serializationStepSize;

  final ToIntBiFunction<? super XS, ? super XD> rxRequestedLengthEvaluator;
  final SerializingPrimitiveArrayLengthProvider<? super T, ? super YS, ? super YD> txRequestedLengthEvaluator;

  protected PrimitiveArraySerializerCore(
    int stepSize,
    WkSzStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    ToIntBiFunction<? super XS, ? super XD> rxRequestedLengthEvaluator,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    PacketInputFieldReadingFactory<T,XS,XD,DC,XO,AXB> readingOpFactory,
    InputDeserializerFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    SerializingPrimitiveArrayLengthProvider<? super T, ? super YS, ? super YD> txRequestedLengthEvaluator,
    Function<AYB,YQC> txRuntimeFactory,
    Function<YO,YR> txResultFactory,
    PacketOutputFieldWritingFactory<T,YS,YD,DC,YO,AYB> writingOpFactory,
    OutputSerializerFactory<T, ? super YQC, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          rxSerializerFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
    this.serializationStepSize = stepSize;
    this.rxRequestedLengthEvaluator = Objects.requireNonNull(rxRequestedLengthEvaluator);
    this.txRequestedLengthEvaluator = Objects.requireNonNull(txRequestedLengthEvaluator);
  }

  @Override
  public int getSerializationStepSize() {
    return this.serializationStepSize;
  }

}
