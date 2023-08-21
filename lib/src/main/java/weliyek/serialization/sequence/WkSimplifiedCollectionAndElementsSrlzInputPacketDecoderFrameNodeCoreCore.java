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
package weliyek.serialization.sequence;

import java.util.Collection;

import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;

public final class WkSimplifiedCollectionAndElementsSrlzInputPacketDecoderFrameNodeCoreCore<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XD extends WkCollectionAndElementsSrlzStructDefinitionFrameNode<T,XO,?,ET,?>,
                        XO extends WkCollectionAndElementsSrlzInputPacketDecoderFrameNode<
                                        T,
                                        XS,
                                        WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        XD,
                                        ET,EXD,EXO>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSrlzStructDefinitionFrameNode<ET>,
                        EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>>
    extends WkCollectionAndElementsSrlzInputPacketDecoderFrameNodeCore<
                        T,
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        XD,
                        XO,
                        WkSimplifiedCollectionAndElementsSrlzInputPacketDecoderFrameNodeCoreCore<T,XS,XD,XO,ET,EXS,EXD,EXO>,
                        WkSzInputBytestreamBase<?>,
                        ET,
                        EXS,
                        EXD,
                        EXO,
                        WkSimplifiedCollectionAndElementsSrlzStructDefinitionFrameNodeCore<T,XS,XD,XO,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,?>>
{

  WkSimplifiedCollectionAndElementsSrlzInputPacketDecoderFrameNodeCoreCore(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<T,?,XD,?,?,?> packetfieldCore,
    WkSimplifiedCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
      T,XS,XD,XO,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,?> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onCollectionReadingInitialization() {
    // Nothing to do.
  }

  @Override
  protected WkSimplifiedCollectionAndElementsSrlzInputPacketDecoderFrameNodeCoreCore<T,XS,XD,XO,ET,EXS,EXD,EXO> getThis() {
    return this;
  }

}
