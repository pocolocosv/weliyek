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

import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;

public final class WkSimplifiedCollectionAndElementsSrlzOutputPacketEncoderFrameNodeCore<
                        T extends Collection<ET>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YD extends WkCollectionAndElementsSrlzStructDefinitionFrameNode<T,?,YO,ET,?>,
                        YO extends WkCollectionAndElementsSrlzOutputPacketEncoderFrameNode<
                                        T,
                                        YS,
                                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkEncodingResultSrlzPacketOperationData,
                                        YD,
                                        ET,EYD,EYO>,
                        ET,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>>
    extends WkCollectionAndElementsSrlzOutputPacketEncoderFrameNodeCore<
                        T,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkEncodingResultSrlzPacketOperationData,
                        YD,
                        YO,
                        WkSimplifiedCollectionAndElementsSrlzOutputPacketEncoderFrameNodeCore<T,YS,YD,YO,ET,EYS,EYD,EYO>,
                        WkSzOutputBytestreamBase<?>,
                        ET,
                        EYS,
                        EYD,
                        EYO,
                        WkSimplifiedCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
                          T,?,?,?,YS,YD,YO,
                          ET,?,?,?,EYS,EYD,EYO,?,?>>
{

  WkSimplifiedCollectionAndElementsSrlzOutputPacketEncoderFrameNodeCore(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> packetHandlerCore,
    WkSimplifiedCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
      T,?,?,?,YS,YD,YO,ET,?,?,?,EYS,EYD,EYO,?,?> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected void onCollectionWritingInitialization() {
    // Nothing to do.

  }

  @Override
  protected WkSimplifiedCollectionAndElementsSrlzOutputPacketEncoderFrameNodeCore<T,YS,YD,YO,ET,EYS,EYD,EYO> getThis() {
    return this;
  }

}
