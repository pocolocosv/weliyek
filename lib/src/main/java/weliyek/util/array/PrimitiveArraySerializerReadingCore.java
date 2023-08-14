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

import weliyek.serialization.WkPrimitiveArraySrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameLeafNodeCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzSequenceReadingRuntime;
import weliyek.serialization.WkSzSequenceReadingRuntimeControl;

public abstract class PrimitiveArraySerializerReadingCore<
                        X extends WkPrimitiveArray<?,?>,
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzSequenceReadingRuntime<?>,
                        XQC extends WkSzSequenceReadingRuntimeControl<?,?,XQ>,
                        XR extends WkSzReadingResult<X>,
                        XO extends WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode<X,XS,XQ,XR,XD>,
                        XOC extends PrimitiveArraySerializerReadingCore<X,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<X,XO>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        DC extends PrimitiveArraySerializerCore<X,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,? extends XD,DC>>
        extends WkSrlzInputPacketDecoderFrameLeafNodeCore<X, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
        implements WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode<X, XS, XQ, XR, XD>
{

  private final int requestedLength;

  protected PrimitiveArraySerializerReadingCore(
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
