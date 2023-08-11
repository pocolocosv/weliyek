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
package weliyek.serialization.number;

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;

public final class WkSzSimplifiedNumberReaderCore<
                        X extends Number,
                        XO extends WkSzNumberReader<
                                      X,
                                      WkSzOperationSettings,
                                      WkSzReadingRuntime<WkSzInputBytestream>,
                                      WkSzReadingResult<X>,
                                      XD>,
                        XD extends WkSzNumberDefinition<X,XO>>
    extends WkSzNumberReaderCore<
                        X,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<X>,
                        XO,
                        WkSzSimplifiedNumberReaderCore<X,XO,XD>,
                        XD,
                        WkSzInputBytestreamBase<?>,
                        WkSzSimplifiedNumberSerializerDefinitionCore<X,XO,?,XD>>
{

  public WkSzSimplifiedNumberReaderCore(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<X,?,XD,?,?,?> readingfieldCore,
    WkSzSimplifiedNumberSerializerDefinitionCore<X,XO,?,XD> definitionCore,
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
  protected WkSzSimplifiedNumberReaderCore<X,XO,XD> getThis() {
    return this;
  }

  @Override
  protected void onDeserilizingOperationInitialization() {
    // Nothing to do.
  }

}
