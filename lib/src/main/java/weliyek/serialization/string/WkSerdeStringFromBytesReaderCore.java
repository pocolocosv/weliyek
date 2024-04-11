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
package weliyek.serialization.string;

import java.nio.charset.Charset;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;
import weliyek.util.array.WkSerdeDtreeByteArrayReader;

public abstract class WkSerdeStringFromBytesReaderCore<
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQ extends WkSerdeDtreeOperationInputRuntime<XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,XQ>,
                        XR extends WkSerdeDtreeOperationResult<String>,
                        XO extends WkSerdeStringFromBytesReader<XS,XQ,XR,XD,SXD,SXO>,
                        XOC extends WkSerdeStringFromBytesReaderCore<XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,SXS,SXO,SXD,DC>,
                        XD extends WkSerdeStringFromBytesDefinition<XO,?,? extends SXD>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        SXS extends WkSerdeDtreeOperationSettings,
                        SXO extends WkSerdeDtreeByteArrayReader<SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreeByteArrayDefinition,
                        DC extends WkSerdeStringFromBytesDefinitionCore<
                                      XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                      ?,?,?,?,?,?,?,?,
                                      SXS,SXO,SXD,?,?,?,?,?,DC>>
    extends WkSerdeStringFromPrimitiveArrayReaderCore<
                        XS, XB, XBC, XQ, XQC, XR, XO, XOC, XD, AXB,
                        WkByteArray, SXS, SXO, SXD, DC>
    implements WkSerdeStringFromBytesReader<XS, XQ, XR, XD, SXD, SXO>
{

  protected WkSerdeStringFromBytesReaderCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSerdeDtreeNodeDataInputComponentCore<String,?,XD,?,?,?> packetField,
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
  public final Optional<WkSerdeDtreeNodeDataInputComponent<WkByteArray, SXD, SXO>> bytes() {
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
