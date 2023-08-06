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
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;
import weliyek.amat.basic.serializer.PrimitiveArraySerializerDefinition;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerReader;

public class SimplifiedPrimitiveArrayDeserializingCore<
                        X extends PrimitiveArrayWrapper<?,?>,
                        XS extends OperationSettings,
                        D extends PrimitiveArraySerializerDefinition<X,XO>,
                        XO extends WkSzPrimitiveArraySerializerReader<
                                          X,
                                          XS,
                                          SequenceReadingRuntime<WkSzInputBytestream>,
                                          DeserializingResult<X>,
                                          D>>
    extends PrimitiveArraySerializerReadingCore<
                        X,
                        XS,
                        SequenceReadingRuntime<WkSzInputBytestream>,
                        SequenceReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          SequenceReadingRuntime<WkSzInputBytestream>>,
                        DeserializingResult<X>,
                        XO,
                        SimplifiedPrimitiveArrayDeserializingCore<X,XS,D,XO>,
                        D,
                        WkSzInputBytestreamBase<?>,
                        SimplifiedPrimitiveArraySerializerCore<X,XS,XO,?,?,D>>
{

  private final Consumer<? super SimplifiedPrimitiveArrayDeserializingCore<X,XS,D,XO>>
                    onInitializing;

  public SimplifiedPrimitiveArrayDeserializingCore(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<X,?,D,?,?,?> deserializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<X,XS,XO,?,?,D> definitionCore,
    XO operationBody,
    Consumer<? super SimplifiedPrimitiveArrayDeserializingCore<X,XS,D,XO>> onInitializing) {
    super(index, settings, parentBytestream, deserializingfieldCore, definitionCore, operationBody);
    this.onInitializing = Objects.requireNonNull(onInitializing);
  }

  @Override
  protected void onDeserilizingOperationInitialization() {
    this.onInitializing.accept(getThis());
  }

  @Override
  protected void onSerializerFullReadingCompletion(X deserialized) {
    // Nothing to do.
  }

  @Override
  protected SimplifiedPrimitiveArrayDeserializingCore<X,XS,D,XO> getThis() {
    return this;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
