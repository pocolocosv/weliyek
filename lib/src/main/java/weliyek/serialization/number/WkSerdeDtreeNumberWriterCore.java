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
package weliyek.serialization.number;

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeLeafDataWriterCore;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;

public abstract class WkSerdeDtreeNumberWriterCore<
                        T extends Number,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<?>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<?,?,YQ>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YO extends WkSerdeDtreeNumberWriter<T,YS,YQ,YR,YD>,
                        YOC extends WkSerdeDtreeNumberWriterCore<T,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkSerdeDtreeNumberDefinition<T>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        DC extends WkSerdeDtreeNumberDefinitionCore<T,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,? extends YD,DC>>
    extends WkSerdeDtreeNodeLeafDataWriterCore<T, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
    implements WkSerdeDtreeNumberWriter<T, YS, YQ, YR, YD>
{

  protected WkSerdeDtreeNumberWriterCore(
    int index,
    T serializable,
    YS settings,
    AYB parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(
        index,
        serializable,
        settings,
        parentBytestream,
        packetHandlerCore,
        definitionCore,
        operationBody);
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
