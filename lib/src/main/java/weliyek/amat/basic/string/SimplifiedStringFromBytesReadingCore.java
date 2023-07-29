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
package weliyek.amat.basic.string;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.ketza.util.array.ByteArrayDefinition;
import weliyek.ketza.util.array.ByteArrayReading;

public class SimplifiedStringFromBytesReadingCore<
                        XS extends OperationSettings,
                        XO extends StringFromBytesReading<
                                      XS,
                                      DeserializingRuntime<InputBytestream>,
                                      DeserializingResult<String>,
                                      XD,SXD,SXO>,
                        XD extends StringFromBytesDefinition<XO,?,? extends SXD>,
                        SXS extends OperationSettings,
                        SXO extends ByteArrayReading<SXS,?,?,SXD>,
                        SXD extends ByteArrayDefinition<SXO>>
    extends StringFromBytesReadingCore<
                        XS,
                        InputBytestream,
                        InputBytestreamGeneralBase<? extends InputBytestream>,
                        DeserializingRuntime<InputBytestream>,
                        ReadingRuntimeControl<
                          InputBytestream,
                          InputBytestreamGeneralBase<? extends InputBytestream>,
                          DeserializingRuntime<InputBytestream>>,
                        DeserializingResult<String>,
                        XO,
                        SimplifiedStringFromBytesReadingCore<XS,XO,XD,SXS,SXO,SXD>,
                        XD,
                        InputBytestreamGeneralBase<?>, SXS, SXO, SXD,
                        SimplifiedStringFromBytesCore<XS,XO,XD,?,?,?,SXS,SXO,SXD,?,?,?,?,?>>
{

  public SimplifiedStringFromBytesReadingCore(
    int index,
    XS settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    DeserializingFieldCore<String,?,XD,?,?,?> packetfieldCore,
    SimplifiedStringFromBytesCore<XS,XO,XD,?,?,?,SXS,SXO,SXD,?,?,?,?,?> definitionCore,
    XO operationBody) {
    super(
        index,
        settings,
        parentBytestream,
        packetfieldCore,
        definitionCore,
        operationBody);
  }

  @Override
  protected void onStringFromPrimitiveReadingInitialization() {
    // Nothing to do.
  }

  @Override
  protected void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  protected SimplifiedStringFromBytesReadingCore<XS,XO,XD,SXS,SXO,SXD> getThis() {
    return this;
  }

}
