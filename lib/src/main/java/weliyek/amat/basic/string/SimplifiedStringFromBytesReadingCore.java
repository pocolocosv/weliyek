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

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.WkSzReadingRuntimeControl;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.ketza.util.array.WkSzByteArrayDefinition;
import weliyek.ketza.util.array.ByteArrayReading;

public class SimplifiedStringFromBytesReadingCore<
                        XS extends WkSzOperationSettings,
                        XO extends WkSzStringFromBytesReader<
                                      XS,
                                      WkSzReadingRuntime<WkSzInputBytestream>,
                                      WkSzReadingResult<String>,
                                      XD,SXD,SXO>,
                        XD extends WkSzStringFromBytesDefinition<XO,?,? extends SXD>,
                        SXS extends WkSzOperationSettings,
                        SXO extends ByteArrayReading<SXS,?,?,SXD>,
                        SXD extends WkSzByteArrayDefinition<SXO>>
    extends StringFromBytesReadingCore<
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<String>,
                        XO,
                        SimplifiedStringFromBytesReadingCore<XS,XO,XD,SXS,SXO,SXD>,
                        XD,
                        WkSzInputBytestreamBase<?>, SXS, SXO, SXD,
                        SimplifiedStringFromBytesCore<XS,XO,XD,?,?,?,SXS,SXO,SXD,?,?,?,?,?>>
{

  public SimplifiedStringFromBytesReadingCore(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<String,?,XD,?,?,?> packetfieldCore,
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
