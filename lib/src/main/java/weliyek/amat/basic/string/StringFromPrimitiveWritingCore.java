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
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.base.output.SerializingSubfieldHandlerCore;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.aggregator.AggregatorWritingCore;
import weliyek.ketza.util.array.PrimitiveArrayDefinition;
import weliyek.ketza.util.array.PrimitiveArrayWrapper;
import weliyek.ketza.util.array.PrimitiveArrayWriting;

public abstract class StringFromPrimitiveWritingCore<
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQ extends SerializingRuntime<YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,YQ>,
                        YR extends SerializingResult,
                        YO extends StringFromPrimitiveWriting<YS,YQ,YR,YD,SY,SYD,SYO>,
                        YOC extends StringFromPrimitiveWritingCore<YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYB,SY,SYS,SYO,SYD,DC>,
                        YD extends StringFromPrimitiveDefinition<?,YO,? extends SYD>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        SY extends PrimitiveArrayWrapper<?,?>,
                        SYS extends OperationSettings,
                        SYO extends PrimitiveArrayWriting<SY,SYS,?,?,SYD>,
                        SYD extends PrimitiveArrayDefinition<SY,?>,
                        DC extends StringFromPrimitiveCore<?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YD,AYB,SY,?,?,?,SYS,SYO,SYD,?,?,DC>>
        extends AggregatorWritingCore<String, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYB, DC>
        implements StringFromPrimitiveWriting<YS, YQ, YR, YD, SY, SYD, SYO>
{

  protected SerializingSubfieldHandlerCore<SY,SYS,SYD,SYO,String,YBC,YD,YO> primitiveArraySubfieldpacket;

  protected StringFromPrimitiveWritingCore(
    int index,
    String serializable,
    YS settings,
    AYB parentBytestream,
    SerializingFieldCore<String,?,YD,?,?,?> packetHandlerCore,
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
  public SerializingSubfieldHandler<SY,SYD,SYO> primitiveArray() {
    return primitiveArraySubfieldpacket.asSubfield();
  }

}
