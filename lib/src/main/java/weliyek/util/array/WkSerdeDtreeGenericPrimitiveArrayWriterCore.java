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
package weliyek.util.array;

import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreePrimitiveMsgWriterCore;

public abstract class WkSerdeDtreeGenericPrimitiveArrayWriterCore<
                        Y extends WkPrimitiveArray<?,?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntimeSequenceCommon<?>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<?,?,YQ>,
                        YR extends WkSerdeDtreeOperationResult<Y>,
                        YO extends WkSerdeDtreePrimitiveArrayWriter<Y,YS,YQ,YR,YD>,
                        YOC extends WkSerdeDtreeGenericPrimitiveArrayWriterCore<Y,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkSerdeDtreePrimitiveArrayDefinition<Y>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        DC extends WkSerdeDtreeGenericPrimitiveArrayDefinitionCore<
                                      Y,?,?,?,?,?,?,?,?,YS,YQC,YR,YD,?,YO,YOC,AYB,? extends YD,?>>
        extends WkSerdeDtreePrimitiveMsgWriterCore<Y, YS, YQ, YQC, YR, YO, YOC, YD, DC, AYB>
        implements WkSerdeDtreePrimitiveArrayWriter<Y, YS, YQ, YR, YD>
{

  private final int requestedLength;

  protected WkSerdeDtreeGenericPrimitiveArrayWriterCore(
    int index,
    Y serializable,
    YS settings,
    AYB parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<?,?,?,?,?,?,?,?> msgFieldCore,
    DC definitionCore,
    YO operationBody) {
    super(
          index,
          settings,
          parentBytestream,
          serializable,
          msgFieldCore,
          definitionCore,
          operationBody);
    this.requestedLength = definitionCore.txRequestedLengthEvaluator.retriveLength(
                                serializable, settings, definition());
  }

  @Override
  public int getRequestedLength() {
    return this.requestedLength;
  }

}
