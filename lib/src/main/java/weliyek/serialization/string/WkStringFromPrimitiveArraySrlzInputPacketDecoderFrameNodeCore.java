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
package weliyek.serialization.string;

import weliyek.serialization.WkAggregatorSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.util.array.WkPrimitiveArraySrlzInputPacketDecoderFrameNode;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkPrimitiveArraySrlzStructDefinitionFrameNode;

public abstract class WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<
                        XS extends WkSzOperationSettings,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQ extends WkSzReadingRuntime<XB>,
                        XQC extends WkSzReadingRuntimeControl<XB,XBC,XQ>,
                        XR extends WkSzReadingResult<String>,
                        XO extends WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNode<XS,XQ,XR,XD,SX,SXD,SXO>,
                        XOC extends WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNodeCore<XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,SX,SXS,SXO,SXD,DC>,
                        XD extends WkStringFromPrimitiveArraySrlzStructDefinitionFrameNode<XO,?,? extends SXD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        SX extends WkPrimitiveArray<?,?>,
                        SXS extends WkSzOperationSettings,
                        SXO extends WkPrimitiveArraySrlzInputPacketDecoderFrameNode<SX,SXS,?,?,SXD>,
                        SXD extends WkPrimitiveArraySrlzStructDefinitionFrameNode<SX, SXO>,
                        DC extends WkSzStringFromPrimitiveDefinitionCore<XS,XB,XBC,XQC,XR,XO,XD,AXB,?,?,?,?,?,?,?,?,SX,SXS,SXO,SXD,?,?,?,?,?,DC>>
       extends WkAggregatorSrlzInputPacketDecoderFrameNodeCore<String, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXB, DC>
       implements WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNode<XS, XQ, XR, XD, SX, SXD, SXO>
{

  private WkSrlzInputPacketSubfieldFrameNodeCore<SX,SXS,SXD,SXO,String,XBC,XD,XO>
                    primitiveArraySubfieldpacket;

  protected WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNodeCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<String,?,XD,?,?,?> deserializingfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, deserializingfieldCore, definitionCore, operationBody);
  }

  @Override
  protected final String onFullReadingCompletion() {
    String aggregatedStr = definitionCore().primitiveArrayDeserializingStringAggregator.apply(body());
    return aggregatedStr;
  }

  /*
  private String buildDeserializedString(SX wrapper) {
    int paddingLen = countPaddingElements(wrapper);
    int strLen = wrapper.getLength() - paddingLen;
    return newStringFromWrapper(wrapper, strLen);
  }

  protected abstract String newStringFromWrapper(SX wrapper, int lenWithoutPadding);

  private int countPaddingElements(SX wrapper) {
    int padding  = effectivePrimitiveArrayPadding.intValue();
    ContigousIntsCounter paddingCounter = new ContigousIntsCounter(padding);
    wrapper.iterateAsIntsBackwardsWhileTrue(paddingCounter);
    return paddingCounter.count();
  }
  */

  protected SX getPrimitiveArray() {
    if ( ! WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNode.isPrimitiveArrayReady(body())) {
      throw new IllegalArgumentException();
    }
    return primitiveArray().field().get().firstOperation().get()
                           .result().get().deserialized().get();
  }

  @Override
  protected void onAggregatorReadingInitialization() {
    primitiveArraySubfieldpacket = getSubfieldpacketFor(definitionCore().primitiveArraySubcomponent);
    onStringFromPrimitiveReadingInitialization();
  }

  protected abstract void onStringFromPrimitiveReadingInitialization();

  @Override
  public WkSrlzInputPacketSubfieldFrameNode<SX, SXD, SXO> primitiveArray() {
    return this.primitiveArraySubfieldpacket;
  }

}