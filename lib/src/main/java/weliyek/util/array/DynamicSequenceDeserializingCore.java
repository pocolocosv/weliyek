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

import weliyek.serialization.WkSzAggregatorReaderCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzPacketReaderSubfieldCore;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;
import weliyek.serialization.sequence.VariableSizeSequenceReading;
import weliyek.serialization.sequence.WkSzVariableSizeSequenceDefinition;

public abstract class DynamicSequenceDeserializingCore<
                        T,
                        XS extends WkSzOperationSettings,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQ extends WkSzReadingRuntime<XB>,
                        XQC extends WkSzReadingRuntimeControl<XB,XBC,XQ>,
                        XR extends WkSzReadingResult<T>,
                        XO extends DynamicSequenceDeserializing<T,XS,XQ,XR,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XOC extends DynamicSequenceDeserializingCore<
                                        T,XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,
                                        ZT,ZXS,ZXO,ZXD,
                                        VXS,VXO,VXD,
                                        DC>,
                        XD extends WkSzDynamicSequenceDefinition<T,XO,?,?,?>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        ZT extends Number,
                        ZXS extends WkSzOperationSettings,
                        ZXO extends WkSzNumberReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,?>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VXO extends VariableSizeSequenceReading<T,VXS,?,?,VXD>,
                        VXD extends WkSzVariableSizeSequenceDefinition<T,VXO>,
                        DC extends DynamicSequenceDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                        ?,?,?,?,?,?,?,?,
                                        ZT,ZXS,ZXO,ZXD,?,?,?,?,
                                        VXS,VXO,VXD,?,?,?,?,
                                        ?,DC>>
    extends WkSzAggregatorReaderCore<T, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXB, DC>
    implements DynamicSequenceDeserializing<T, XS, XQ, XR, XD, ZT, ZXO, ZXD, VXO, VXD>
{

  private WkSzPacketReaderSubfieldCore<ZT,ZXS,ZXD,ZXO,T,XBC,XD,XO>
                        sizeReadFieldHandler;
  private WkSzPacketReaderSubfieldCore<T,VXS,VXD,VXO,T,XBC,XD,XO>
                        varseqReadFieldHandler;

  protected DynamicSequenceDeserializingCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSzPacketReaderFieldCore<T,?,XD,?,?,?> packetfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
    this.sizeReadFieldHandler = getSubfieldpacketFor(definitionCore().sizeComponent);
    this.varseqReadFieldHandler = getSubfieldpacketFor(definitionCore().varseqComponent);
  }

  @Override
  protected void onAggregatorReadingInitialization() {
  }

  @Override
  public WkSzPacketReaderSubfield<ZT, ZXD, ZXO> size() {
    return this.sizeReadFieldHandler.asSubfield();
  }

  @Override
  public WkSzPacketReaderSubfield<T, VXD, VXO> variableSequence() {
    return this.varseqReadFieldHandler.asSubfield();
  }

}
