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

import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDtreeNodeLeafDataWriterCore;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzOutputBytestreamBase;

public abstract class WkSerdeDtreeGenericPrimitiveArrayWriterCore<
                        Y extends WkPrimitiveArrayBase<?,?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkSequenceEncodingRuntimeSrlzPacketOperationData<?>,
                        YQC extends WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<?,?,YQ>,
                        YR extends WkResultSrlzPacketOperationData<Y>,
                        YO extends WkSerdeDtreeGenericPrimitiveArrayWriter<Y,YS,YQ,YR,YD>,
                        YOC extends WkSerdeDtreeGenericPrimitiveArrayWriterCore<Y,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkSerdeDtreeGenericPrimitiveArrayDefinition<Y>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        DC extends WkSerdeDtreeGenericPrimitiveArrayDefinitionCore<Y,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,? extends YD,DC>>
        extends WkSerdeDtreeNodeLeafDataWriterCore<Y, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
        implements WkSerdeDtreeGenericPrimitiveArrayWriter<Y, YS, YQ, YR, YD>
{

  private final int requestedLength;

  protected WkSerdeDtreeGenericPrimitiveArrayWriterCore(
    int index,
    Y serializable,
    YS settings,
    AYB parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<Y,?,YD,?,?,?> serializingfieldCore,
    DC definitionCore,
    YO operationBody) {
    super(
          index,
          serializable,
          settings,
          parentBytestream,
          serializingfieldCore,
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
