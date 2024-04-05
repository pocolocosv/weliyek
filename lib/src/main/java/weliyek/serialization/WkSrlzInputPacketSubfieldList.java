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

public class WkSrlzInputPacketSubfieldList<
                        X,
                        XBC extends WkSzInputBytestreamBase<?>,
                        XD extends WkSerdeDtreeAggregatorDefinition<X>,
                        XO extends WkSerdeDtreeAggregatorReader<X,?,? extends WkDecodingRuntimeSrlzPacketOperationData<?>,?,XD>>
        extends WkSrlzPacketSubfieldList<
                        WkSrlzInputPacketSubfieldFrameNode<?,?,?>,
                        WkSrlzInputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,XD,XO>,
                        WkSerdeDtreeAggregatorReaderCore<?,?,?,?,?,?,?,XD,XO,?,?,?>>
{

    WkSrlzInputPacketSubfieldList(
      List<WkSrlzInputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,XD,XO>> deserializingSubfieldHandlers) {
      super(deserializingSubfieldHandlers);
    }

    @SuppressWarnings("unchecked")
    <ST,
     SXS extends WkSettingsSrlzPacketOperationData,
     SXD extends WkSerdeDtreeNodeStructDefinition<ST>,
     SXO extends WkSerdeDtreeNodeDataReader<ST,SXS,?,?,SXD>>
    WkSrlzInputPacketSubfieldFrameNodeCore<ST,SXS,SXD,SXO,X,XBC,XD,XO> findSubfieldpacket(
      WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,X,XBC,XD,XO,?,?,?,?,?,?,? extends SXD,? extends XD> searchedSubcomponent) {
      for (WkSrlzInputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,XD,XO> deserializingSubfieldHandler : this) {
        WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,?,XD,XO,?,?,?,?,?,?,?,?> subcomponentHandler = deserializingSubfieldHandler.subcomponentHandlerCore();
        if (subcomponentHandler.equals(searchedSubcomponent)) {
          return (WkSrlzInputPacketSubfieldFrameNodeCore<ST,SXS,SXD,SXO,X,XBC,XD,XO>) deserializingSubfieldHandler;
        }
      }
      throw new RuntimeException(); // Should never happen.
    }

}
