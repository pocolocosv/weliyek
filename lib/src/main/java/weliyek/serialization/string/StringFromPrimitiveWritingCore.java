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

import weliyek.serialization.WkSzAggregatorWriterCore;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzPacketWriterSubfieldCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.util.array.PrimitiveArrayWrapper;
import weliyek.util.array.PrimitiveArrayWriting;
import weliyek.util.array.WkSzPrimitiveArrayDefinition;

public abstract class StringFromPrimitiveWritingCore<
                        YS extends WkSzOperationSettings,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkSzWritingRuntime<YB>,
                        YQC extends WkSzWritingRuntimeControl<YB,YBC,YQ>,
                        YR extends WkSzWritingResult,
                        YO extends WkSzStringFromPrimitiveWriter<YS,YQ,YR,YD,SY,SYD,SYO>,
                        YOC extends StringFromPrimitiveWritingCore<YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYB,SY,SYS,SYO,SYD,DC>,
                        YD extends WkSzStringFromPrimitiveDefinition<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        SY extends PrimitiveArrayWrapper<?,?>,
                        SYS extends WkSzOperationSettings,
                        SYO extends PrimitiveArrayWriting<SY,SYS,?,?,SYD>,
                        SYD extends WkSzPrimitiveArrayDefinition<SY,?>,
                        DC extends WkSzStringFromPrimitiveDefinitionCore<?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YD,AYB,SY,?,?,?,SYS,SYO,SYD,?,?,DC>>
        extends WkSzAggregatorWriterCore<String, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYB, DC>
        implements WkSzStringFromPrimitiveWriter<YS, YQ, YR, YD, SY, SYD, SYO>
{

  protected WkSzPacketWriterSubfieldCore<SY,SYS,SYD,SYO,String,YBC,YD,YO> primitiveArraySubfieldpacket;

  protected StringFromPrimitiveWritingCore(
    int index,
    String serializable,
    YS settings,
    AYB parentBytestream,
    WkSzPacketWriterFieldCore<String,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected void onAggregatorInitialization() {
    primitiveArraySubfieldpacket = getSubfieldpacketFor(definitionCore().primitiveArraySubcomponent);
    onStringFromPrimitiveWritingInitialization();
  }

  protected abstract void onStringFromPrimitiveWritingInitialization();

  @Override
  public WkSzPacketWriterSubfield<SY,SYD,SYO> primitiveArray() {
    return primitiveArraySubfieldpacket.asSubfield();
  }

}
