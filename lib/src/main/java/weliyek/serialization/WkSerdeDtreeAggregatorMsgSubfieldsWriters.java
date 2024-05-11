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

public class WkSerdeDtreeAggregatorMsgSubfieldsWriters<
                        AT,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        AYO extends WkSerdeDtreeAggregatorMsgWriter<AT,?,?,?,?>>
        extends WkSerdeDtreeAggregatorMsgSubfields<
                        WkSerdeDtreeStructSubfieldCore<?,AT,?,?,?,?,?,?,?,?,AYBC,AYO,?>,
                        WkSerdeDtreeMsgOutputField<?,?,?>,
                        WkSerdeDtreeMsgOutputSubfieldCore<?,?,?,?,?,?,?>,
                        WkSerdeDtreeAggregatorMsgWriterCore<AT,?,?,AYBC,?,?,?,?,?,AYO,?,?>>
{

  protected WkSerdeDtreeAggregatorMsgSubfieldsWriters(
    WkSerdeDtreeAggregatorMsgWriterCore<AT,?,?,AYBC,?,?,?,?,?,AYO,?,?> ownerAggregator) {
    super(ownerAggregator);
  }

  @Override
  WkSerdeDtreeMsgOutputSubfieldCore<?,?,?,?,?,?,?>
      newAndActivatedDataSubfieldCore(
        WkSerdeDtreeStructSubfieldCore<?,AT,?,?,?,?,?,?,?,?,AYBC,AYO,?> currentStructCore) {
    return currentStructCore.newWriterSubfieldCore(ownerAggregator);
  }

  @Override
  boolean isStructSubfieldListEmpty() {
    return this.ownerAggregator.definitionCore().structSubfieldCoreList.asRoCoreList().isEmpty();
  }

  @Override
  ListIterator<? extends WkSerdeDtreeStructSubfieldCore<?, AT, ?, ?, ?, ?, ?, ?, ?, ?, AYBC, AYO, ?>>
      getStructSubfiedIter() {
    return this.ownerAggregator.definitionCore().structSubfieldCoreList.structIterator();
  }

}
