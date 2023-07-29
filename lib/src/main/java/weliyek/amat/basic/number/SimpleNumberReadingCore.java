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
package weliyek.amat.basic.number;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.ReadingRuntimeControl;

public final class SimpleNumberReadingCore<
                        X extends Number,
                        XO extends NumberDeserializing<
                                      X,
                                      OperationSettings,
                                      DeserializingRuntime<InputBytestream>,
                                      DeserializingResult<X>,
                                      XD>,
                        XD extends NumberDefinition<X,XO>>
    extends NumberReadingCore<
                        X,
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        ReadingRuntimeControl<
                          InputBytestream,
                          InputBytestreamGeneralBase<? extends InputBytestream>,
                          DeserializingRuntime<InputBytestream>>,
                        DeserializingResult<X>,
                        XO,
                        SimpleNumberReadingCore<X,XO,XD>,
                        XD,
                        InputBytestreamGeneralBase<?>,
                        SimplifiedNumberSerializerCore<X,XO,?,XD>>
{

  public SimpleNumberReadingCore(
    int index,
    OperationSettings settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    DeserializingFieldCore<X,?,XD,?,?,?> readingfieldCore,
    SimplifiedNumberSerializerCore<X,XO,?,XD> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, readingfieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onSerializerFullReadingCompletion(X deserialized) {
    // Nothing to do.
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  protected SimpleNumberReadingCore<X,XO,XD> getThis() {
    return this;
  }

  @Override
  protected void onDeserilizingOperationInitialization() {
    // Nothing to do.
  }

}
