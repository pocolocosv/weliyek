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

import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreePrimitiveMsgWriterCore;

public abstract class WkSerdeDtreeNumberMsgWriterCore<
                        T extends Number,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<?>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<?,?,YQ>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YO extends WkSerdeDtreeNumberMsgWriter<T,YS,YQ,YR,YD>,
                        YOC extends WkSerdeDtreeNumberMsgWriterCore<T,YS,YQ,YQC,YR,YO,?,YD,YDC,AYB>,
                        YD extends WkSerdeDtreeNumberStructDefinition<T>,
                        YDC extends WkSerdeDtreeNumberDefinitionCore<T,?,?,?,?,?,?,?,?,YS,YQC,YR,YD,?,YO,YOC,AYB,? extends YD,?>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>>
    extends WkSerdeDtreePrimitiveMsgWriterCore<T, YS, YQ, YQC, YR, YO, YOC, YD, YDC, AYB>
    implements WkSerdeDtreeNumberMsgWriter<T, YS, YQ, YR, YD>
{

  protected WkSerdeDtreeNumberMsgWriterCore(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<T,YS,?,?,AYB,?,?,?> msgFieldCore,
    YDC definitionCore,
    YO operationBody) {
    super(
        index,
        msgFieldCore,
        definitionCore,
        operationBody);
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
