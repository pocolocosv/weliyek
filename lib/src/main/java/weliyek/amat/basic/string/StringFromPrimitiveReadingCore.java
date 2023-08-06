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
package weliyek.amat.basic.string;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.WkSzPacketReaderSubfieldCore;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.basic.aggregator.WkSzAggregatorReaderCore;
import weliyek.ketza.util.array.WkSzPrimitiveArrayDefinition;
import weliyek.serialization.bytestream.InputBytestream;
import weliyek.serialization.bytestream.InputBytestreamGeneralBase;
import weliyek.ketza.util.array.PrimitiveArrayReading;
import weliyek.ketza.util.array.PrimitiveArrayWrapper;

public abstract class StringFromPrimitiveReadingCore<
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQ extends DeserializingRuntime<XB>,
                        XQC extends ReadingRuntimeControl<XB,XBC,XQ>,
                        XR extends DeserializingResult<String>,
                        XO extends WkSzStringFromPrimitiveReader<XS,XQ,XR,XD,SX,SXD,SXO>,
                        XOC extends StringFromPrimitiveReadingCore<XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,SX,SXS,SXO,SXD,DC>,
                        XD extends WkSzStringFromPrimitiveDefinition<XO,?,? extends SXD>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        SX extends PrimitiveArrayWrapper<?,?>,
                        SXS extends OperationSettings,
                        SXO extends PrimitiveArrayReading<SX,SXS,?,?,SXD>,
                        SXD extends WkSzPrimitiveArrayDefinition<SX, SXO>,
                        DC extends WkSzStringFromPrimitiveDefinitionCore<XS,XB,XBC,XQC,XR,XO,XD,AXB,?,?,?,?,?,?,?,?,SX,SXS,SXO,SXD,?,?,?,?,?,DC>>
       extends WkSzAggregatorReaderCore<String, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXB, DC>
       implements WkSzStringFromPrimitiveReader<XS, XQ, XR, XD, SX, SXD, SXO>
{

  private WkSzPacketReaderSubfieldCore<SX,SXS,SXD,SXO,String,XBC,XD,XO>
                    primitiveArraySubfieldpacket;

  protected StringFromPrimitiveReadingCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSzPacketReaderFieldCore<String,?,XD,?,?,?> deserializingfieldCore,
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
    if ( ! WkSzStringFromPrimitiveReader.isPrimitiveArrayReady(body())) {
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
  public WkSzPacketReaderSubfield<SX, SXD, SXO> primitiveArray() {
    return this.primitiveArraySubfieldpacket;
  }

}
