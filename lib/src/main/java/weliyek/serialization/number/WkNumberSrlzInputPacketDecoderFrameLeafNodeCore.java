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

import weliyek.serialization.WkSrlzInputPacketDecoderFrameLeafNodeCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;

public abstract class WkNumberSrlzInputPacketDecoderFrameLeafNodeCore<
                        X extends Number,
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XQC extends WkSzReadingRuntimeControl<?,?,XQ>,
                        XR extends WkSzReadingResult<X>,
                        XO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<X,XS,XQ,XR,XD>,
                        XOC extends WkNumberSrlzInputPacketDecoderFrameLeafNodeCore<X,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends WkNumberSrlzStructDefinitionFrameLeafNode<X,XO>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        DC extends WkNumberSrlzStructDefinitionFrameNodeCore<X,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,? extends XD,DC>>
    extends WkSrlzInputPacketDecoderFrameLeafNodeCore<X, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
    implements WkNumberSrlzInputPacketDecoderFrameLeafNode<X, XS, XQ, XR, XD>
{

  protected WkNumberSrlzInputPacketDecoderFrameLeafNodeCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<X, ?, XD, ?, ?, ?> packetField,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetField, definitionCore, operationBody);
  }

  @Override
  protected void onSerializerFullReadingCompletion(X deserialized) {
    // Nothing to do.
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
