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

import java.util.Objects;
import java.util.function.Consumer;

import weliyek.serialization.PrimitiveArraySerializerDefinition;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPrimitiveArraySerializerReader;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzSequenceReadingRuntime;
import weliyek.serialization.WkSzSequenceReadingRuntimeControl;

public class SimplifiedPrimitiveArrayDeserializingCore<
                        X extends PrimitiveArrayWrapper<?,?>,
                        XS extends WkSzOperationSettings,
                        D extends PrimitiveArraySerializerDefinition<X,XO>,
                        XO extends WkSzPrimitiveArraySerializerReader<
                                          X,
                                          XS,
                                          WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                                          WkSzReadingResult<X>,
                                          D>>
    extends PrimitiveArraySerializerReadingCore<
                        X,
                        XS,
                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                        WkSzSequenceReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzSequenceReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<X>,
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
