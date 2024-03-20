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
package weliyek.serialization;

import java.util.List;

public class WkSrlzOutputPacketSubfieldList<
                        Y,
                        YBC extends WkSzOutputBytestreamBase<?>,
                        YD extends WkAggregatorSrlzStructDefinitionFrameNode<Y>,
                        YO extends WkAggregatorSrlzOutputPacketEncoderFrameNode<Y,?,? extends WkEncodingRuntimeSrlzPacketOperationData<?>,?,YD>>
        extends WkSrlzPacketSubfieldList<
                        WkSrlzOutputPacketSubfieldFrameNode<?,?,?>,
                        WkSrlzOutputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,YD,YO>,
                        WkAggregatorSrlzOutputPacketEncoderFrameNodeCore<?,?,?,?,?,?,?,YD,YO,?,?,?>>
{

  WkSrlzOutputPacketSubfieldList(
    List<WkSrlzOutputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,YD,YO>> subfieldHandlers) {
    super(subfieldHandlers);
  }

  @SuppressWarnings("unchecked")
  <SY,
   SYS extends WkSettingsSrlzPacketOperationData,
   SYD extends WkSerdeDTreeNodeStructDefinition<SY>,
   SYO extends WkSerdeDTreeNodeDataWriter<SY,SYS,?,?,SYD>>
  WkSrlzOutputPacketSubfieldFrameNodeCore<SY,SYS,SYD,SYO,Y,YBC,YD,YO> findSubfieldpacket(
    WkSrlzStructSubcomponentFrameNodeCore<SY,?,?,?,Y,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD> searchedSubcomponent) {
    for (WkSrlzOutputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,YD,YO> serializingSubfieldHandler : this) {
      WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?,?,?,YD,YO,?,? extends YD> subcomponentHandler = serializingSubfieldHandler.subcomponentHandlerCore();
      if (subcomponentHandler.equals(searchedSubcomponent)) {
        return (WkSrlzOutputPacketSubfieldFrameNodeCore<SY,SYS,SYD,SYO,Y,YBC,YD,YO>) serializingSubfieldHandler;
      }
    }
    throw new RuntimeException(); // Should never happen.
  }

}
