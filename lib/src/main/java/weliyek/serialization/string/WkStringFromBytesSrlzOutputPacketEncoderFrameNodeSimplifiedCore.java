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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.util.array.WkByteArraySrlzOutputPacketEncoderFrameNode;
import weliyek.util.array.WkSerdeDTreeByteArrayDefinition;

public class WkStringFromBytesSrlzOutputPacketEncoderFrameNodeSimplifiedCore<
                        YS extends WkSettingsSrlzPacketOperationData,
                        YO extends WkStringFromBytesSrlzOutputPacketEncoderFrameNode<
                                      YS,
                                      WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                      WkResultSrlzPacketOperationData<String>,
                                      YD,SYD,SYO>,
                        YD extends WkStringFromBytesSrlzStructDefinitionFrameNode<?,YO,? extends SYD>,
                        SYS extends WkSettingsSrlzPacketOperationData,
                        SYO extends WkByteArraySrlzOutputPacketEncoderFrameNode<SYS,?,?,SYD>,
                        SYD extends WkSerdeDTreeByteArrayDefinition>
    extends WkStringFromBytesSrlzOutputPacketEncoderFrameNodeCore<
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkResultSrlzPacketOperationData<String>,
                        YO,
                        WkStringFromBytesSrlzOutputPacketEncoderFrameNodeSimplifiedCore<YS,YO,YD,SYS,SYO,SYD>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        SYS, SYO, SYD,
                        WkStringFromBytesSrlzStructDefinitionFrameNodeSimplifiedCore<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,?,?>>
{

  public WkStringFromBytesSrlzOutputPacketEncoderFrameNodeSimplifiedCore(
    int index,
    String serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<String,?,YD,?,?,?> packetHandlerCore,
    WkStringFromBytesSrlzStructDefinitionFrameNodeSimplifiedCore<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,?,?> definitionCore,
    YO operationBody) {
    super(
        index,
        serializable,
        settings,
        parentBytestream,
        packetHandlerCore,
        definitionCore, operationBody);
  }


  @Override
  protected void onStringFromPrimitiveWritingInitialization() {
    // Nothing to do.
  }
  /*
  @Override
  protected void onStringFromPrimitiveWritingInitialization() {
    if(definitionCore().onWritingOpStart().isPresent()) {
      definitionCore().onWritingOpStart().get().accept(this);;
    }
  }
  */

  @Override
  protected WkStringFromBytesSrlzOutputPacketEncoderFrameNodeSimplifiedCore<YS,YO,YD,SYS,SYO,SYD> getThis() {
    return this;
  }

}
