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

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.util.array.WkByteArraySrlzInputPacketDecoderFrameNode;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkByteArraySrlzStructDefinitionFrameNode;

public abstract class WkStringFromBytesSrlzInputPacketDecoderFrameNodeCore<
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<XB>,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,XQ>,
                        XR extends WkDecodingResultSrlzPacketOperationData<String>,
                        XO extends WkStringFromBytesSrlzInputPacketDecoderFrameNode<XS,XQ,XR,XD,SXD,SXO>,
                        XOC extends WkStringFromBytesSrlzInputPacketDecoderFrameNodeCore<XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,SXS,SXO,SXD,DC>,
                        XD extends WkStringFromBytesSrlzStructDefinitionFrameNode<XO,?,? extends SXD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        SXS extends WkSettingsSrlzPacketOperationData,
                        SXO extends WkByteArraySrlzInputPacketDecoderFrameNode<SXS,?,?,SXD>,
                        SXD extends WkByteArraySrlzStructDefinitionFrameNode,
                        DC extends WkStringFromBytesSrlzStructDefinitionFrameNodeCore<
                                      XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                      ?,?,?,?,?,?,?,?,
                                      SXS,SXO,SXD,?,?,?,?,?,DC>>
    extends WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<
                        XS, XB, XBC, XQ, XQC, XR, XO, XOC, XD, AXB,
                        WkByteArray, SXS, SXO, SXD, DC>
    implements WkStringFromBytesSrlzInputPacketDecoderFrameNode<XS, XQ, XR, XD, SXD, SXO>
{

  protected WkStringFromBytesSrlzInputPacketDecoderFrameNodeCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<String,?,XD,?,?,?> packetField,
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
  public final WkSrlzInputPacketSubfieldFrameNode<WkByteArray, SXD, SXO> bytes() {
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
