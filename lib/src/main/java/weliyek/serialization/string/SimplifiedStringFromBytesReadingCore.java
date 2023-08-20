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

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.util.array.WkByteArraySrlzInputPacketDecoderFrameNode;
import weliyek.util.array.WkByteArraySrlzStructDefinitionFrameNode;

public class SimplifiedStringFromBytesReadingCore<
                        XS extends WkSettingsSrlzPacketOperationData,
                        XO extends WkStringFromBytesSrlzInputPacketDecoderFrameNode<
                                      XS,
                                      WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                      WkDecodingResultSrlzPacketOperationData<String>,
                                      XD,SXD,SXO>,
                        XD extends WkStringFromBytesSrlzStructDefinitionFrameNode<XO,?,? extends SXD>,
                        SXS extends WkSettingsSrlzPacketOperationData,
                        SXO extends WkByteArraySrlzInputPacketDecoderFrameNode<SXS,?,?,SXD>,
                        SXD extends WkByteArraySrlzStructDefinitionFrameNode>
    extends WkStringFromBytesSrlzInputPacketDecoderFrameNodeCore<
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkDecodingResultSrlzPacketOperationData<String>,
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
    WkSrlzInputPacketFieldFrameNodeCore<String,?,XD,?,?,?> packetfieldCore,
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
