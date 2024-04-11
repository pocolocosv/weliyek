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

import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorReaderCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayReader;

public abstract class WkSerdeStringFromPrimitiveArrayReaderCore<
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQ extends WkSerdeDtreeOperationInputRuntime<XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,XQ>,
                        XR extends WkSerdeDtreeOperationResult<String>,
                        XO extends WkSerdeStringFromPrimitiveArrayReader<XS,XQ,XR,XD,SX,SXD,SXO>,
                        XOC extends WkSerdeStringFromPrimitiveArrayReaderCore<XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,SX,SXS,SXO,SXD,DC>,
                        XD extends WkSerdeStringFromPrimitiveArrayDefinition<XO,?,? extends SXD>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        SX extends WkPrimitiveArray<?,?>,
                        SXS extends WkSerdeDtreeOperationSettings,
                        SXO extends WkSerdeDtreePrimitiveArrayReader<SX,SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreePrimitiveArrayDefinition<SX>,
                        DC extends WkSerdeStringFromPrimitiveArrayDefinitionCore<XS,XB,XBC,XQC,XR,XO,XD,AXB,?,?,?,?,?,?,?,?,SX,SXS,SXO,SXD,?,?,?,?,?,DC>>
       extends WkSerdeDtreeAggregatorReaderCore<String, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXB, DC>
       implements WkSerdeStringFromPrimitiveArrayReader<XS, XQ, XR, XD, SX, SXD, SXO>
{

  private WkSrlzInputPacketSubfieldFrameNodeCore<SX,SXS,SXD,SXO,String,XBC,XD,XO>
                    primitiveArraySubfieldpacket;

  protected WkSerdeStringFromPrimitiveArrayReaderCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSerdeDtreeNodeDataInputComponentCore<String,?,XD,?,?,?> deserializingfieldCore,
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
    if ( ! WkSerdeStringFromPrimitiveArrayReader.isPrimitiveArrayReady(body())) {
      throw new IllegalArgumentException();
    }
    return primitiveArray().get().firstOperation().get()
                           .result().get().serializable().get();
  }

  @Override
  protected void onAggregatorReadingInitialization() {
    primitiveArraySubfieldpacket = getSubfieldpacketFor(definitionCore().primitiveArraySubcomponent);
    onStringFromPrimitiveReadingInitialization();
  }

  protected abstract void onStringFromPrimitiveReadingInitialization();

  @Override
  public Optional<WkSerdeDtreeNodeDataInputComponent<SX, SXD, SXO>> primitiveArray() {
    return this.primitiveArraySubfieldpacket.field();
  }

}
