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
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDtreeNodeDataReaderDecoderCore;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.sequence.WkSerdeDtreeSequenceReader;

public abstract class WkSerdeDtreeGenericPrimitiveArrayReaderCore<
                        X extends WkPrimitiveArray<?,?>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQ extends WkSequenceDecodingRuntimeSrlzPacketOperationData<?>,
                        XQC extends WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<?,?,XQ>,
                        XR extends WkResultSrlzPacketOperationData<X>,
                        XO extends WkSerdeDtreePrimitiveArrayReader<X,XS,XQ,XR,XD>,
                        XOC extends WkSerdeDtreeGenericPrimitiveArrayReaderCore<X,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends WkSerdeDtreePrimitiveArrayDefinition<X>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        DC extends WkSerdeDtreeGenericPrimitiveArrayDefinitionCore<X,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,? extends XD,DC>>
        extends WkSerdeDtreeNodeDataReaderDecoderCore<X, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
        implements WkSerdeDtreePrimitiveArrayReader<X, XS, XQ, XR, XD>,
        WkSerdeDtreeSequenceReader<X, XS, XQ, XR, XD>
{

  private final int requestedLength;

  protected WkSerdeDtreeGenericPrimitiveArrayReaderCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<X,?,XD,?,?,?> deserializingfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, deserializingfieldCore, definitionCore, operationBody);
    this.requestedLength = definitionCore.rxRequestedLengthEvaluator.applyAsInt(settings, definition());
  }

  @Override
  public int getRequestedLength() {
    return this.requestedLength;
  }

}
