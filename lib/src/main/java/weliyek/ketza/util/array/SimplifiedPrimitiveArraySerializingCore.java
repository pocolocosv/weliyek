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
import java.util.function.Consumer;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;
import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;
import weliyek.amat.basic.serializer.PrimitiveArraySerializerDefinition;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerWriter;

public class SimplifiedPrimitiveArraySerializingCore<
                        Y extends PrimitiveArrayWrapperBase<?,?>,
                        YS extends OperationSettings,
                        D extends PrimitiveArraySerializerDefinition<Y,?>,
                        YO extends WkSzPrimitiveArraySerializerWriter<
                                        Y,
                                        YS,
                                        SequenceWritingRuntime<OutputBytestream>,
                                        SerializingResult,
                                        D>>
    extends PrimitiveArraySerializerWritingCore<
                        Y,
                        YS,
                        SequenceWritingRuntime<OutputBytestream>,
                        SequenceWritingRuntimeControl<
                          OutputBytestream,
                          OutputBytestreamGeneralBase<? extends OutputBytestream>,
                          SequenceWritingRuntime<OutputBytestream>>,
                        SerializingResult,
                        YO,
                        SimplifiedPrimitiveArraySerializingCore<Y,YS,D,YO>,
                        D,
                        OutputBytestreamGeneralBase<?>,
                        SimplifiedPrimitiveArraySerializerCore<Y,?,?,YS,YO,D>>
{

  private final Consumer<? super SimplifiedPrimitiveArraySerializingCore<Y,YS,D,YO>>
                    onInitializing;

  public SimplifiedPrimitiveArraySerializingCore(
    int index,
    Y serializable,
    YS settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<Y,?,D,?,?,?> serializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<Y,?,?,YS,YO,D> definitionCore,
    YO operationBody,
    Consumer<? super SimplifiedPrimitiveArraySerializingCore<Y,YS,D,YO>> onInitializing) {
    super(index, serializable, settings, parentBytestream, serializingfieldCore, definitionCore, operationBody);
    this.onInitializing = Objects.requireNonNull(onInitializing);
  }

  @Override
  protected void onSerializingOperationInitialization() {
    this.onInitializing.accept(getThis());
  }

  @Override
  protected SimplifiedPrimitiveArraySerializingCore<Y, YS, D, YO> getThis() {
    return this;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
