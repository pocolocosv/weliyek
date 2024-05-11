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

import java.util.ListIterator;

public class WkSerdeDtreeAggregatorMsgSubfieldsReaders<
                        AT,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        AXO extends WkSerdeDtreeAggregatorMsgReader<AT,?,? extends WkSerdeDtreeOperationInputRuntime<?>,?,?>>
        extends WkSerdeDtreeAggregatorMsgSubfields<
                        WkSerdeDtreeStructSubfieldCore<?,AT,?,?,?,AXBC,AXO,?,?,?,?,?,?>,
                        WkSerdeDtreeMsgInputField<?,?,?>,
                        WkSerdeDtreeMsgInputSubfieldCore<?,?,?,?,?,?,?>,
                        WkSerdeDtreeAggregatorMsgReaderCore<AT,?,?,AXBC,?,?,?,?,?,AXO,?,?>>
{

  protected WkSerdeDtreeAggregatorMsgSubfieldsReaders(
    WkSerdeDtreeAggregatorMsgReaderCore<AT,?,?,AXBC,?,?,?,?,?,AXO,?,?> readerAggregator) {
    super(readerAggregator);
  }

  @Override
  WkSerdeDtreeMsgInputSubfieldCore<?,?,?,?,?,?,?>
      newAndActivatedDataSubfieldCore(
        WkSerdeDtreeStructSubfieldCore<?,AT,?,?,?,AXBC,AXO,?,?,?,?,?,?> structCore) {
    return structCore.newReaderSubfieldCore(ownerAggregator);
  }

  @Override
  boolean isStructSubfieldListEmpty() {
    return this.ownerAggregator.definitionCore().structSubfieldCoreList.asRoCoreList().isEmpty();
  }

  @Override
  ListIterator<? extends WkSerdeDtreeStructSubfieldCore<?, AT, ?, ?, ?, AXBC, AXO, ?, ?, ?, ?, ?, ?>>
      getStructSubfiedIter() {
    return this.ownerAggregator.definitionCore().structSubfieldCoreList.structIterator();
  }

}
