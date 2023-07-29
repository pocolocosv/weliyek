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

import java.nio.charset.Charset;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.ketza.util.array.ByteArrayDefinition;
import weliyek.ketza.util.array.ByteArrayReading;
import weliyek.ketza.util.array.ByteArrayWrapper;

public abstract class StringFromBytesReadingCore<
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQ extends DeserializingRuntime<XB>,
                        XQC extends ReadingRuntimeControl<XB,XBC,XQ>,
                        XR extends DeserializingResult<String>,
                        XO extends StringFromBytesReading<XS,XQ,XR,XD,SXD,SXO>,
                        XOC extends StringFromBytesReadingCore<XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,SXS,SXO,SXD,DC>,
                        XD extends StringFromBytesDefinition<XO,?,? extends SXD>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        SXS extends OperationSettings,
                        SXO extends ByteArrayReading<SXS,?,?,SXD>,
                        SXD extends ByteArrayDefinition<SXO>,
                        DC extends StringFromBytesCore<
                                      XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                      ?,?,?,?,?,?,?,?,
                                      SXS,SXO,SXD,?,?,?,?,?,DC>>
    extends StringFromPrimitiveReadingCore<
                        XS, XB, XBC, XQ, XQC, XR, XO, XOC, XD, AXB,
                        ByteArrayWrapper, SXS, SXO, SXD, DC>
    implements StringFromBytesReading<XS, XQ, XR, XD, SXD, SXO>
{

  protected StringFromBytesReadingCore(
    int index,
    XS settings,
    AXB parentBytestream,
    DeserializingFieldCore<String,?,XD,?,?,?> packetField,
    DC definitionCore,
    XO operationBody) {
    super(
        index,
        settings,
        parentBytestream,
        packetField,
        definitionCore,
        operationBody);
  }

  @Override
  public final DeserializingSubfieldHandler<ByteArrayWrapper, SXD, SXO> bytes() {
    return this.primitiveArray();
  }

  @Override
  public final Charset charset() {
    return definition().charset();
  }

  /*
  @Override
  protected String newStringFromWrapper(ByteArrayWrapper wrapper, int lenWithoutPadding) {
    return wrapper.convertToString(charset(), 0, lenWithoutPadding);
  }
  */

  /*
  @Override
  protected final String buildDeserializedString(ByteArrayWrapper wrapper) {
    Charset charset = charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }
  */

}
