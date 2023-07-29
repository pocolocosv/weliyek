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
package weliyek.ketza.util.array;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.base.input.DeserializingSubfieldHandlerCore;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.basic.aggregator.AggregatorReadingCore;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceDefinition;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceReading;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberDeserializing;

public abstract class DynamicSequenceDeserializingCore<
                        T,
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQ extends DeserializingRuntime<XB>,
                        XQC extends ReadingRuntimeControl<XB,XBC,XQ>,
                        XR extends DeserializingResult<T>,
                        XO extends DynamicSequenceDeserializing<T,XS,XQ,XR,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XOC extends DynamicSequenceDeserializingCore<
                                        T,XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,
                                        ZT,ZXS,ZXO,ZXD,
                                        VXS,VXO,VXD,
                                        DC>,
                        XD extends DynamicSequenceDefinition<T,XO,?,?,?>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        ZT extends Number,
                        ZXS extends OperationSettings,
                        ZXO extends NumberDeserializing<ZT,ZXS,?,?,ZXD>,
                        ZXD extends NumberDefinition<ZT,?>,
                        VXS extends VariableLengthSettings,
                        VXO extends VariableSizeSequenceReading<T,VXS,?,?,VXD>,
                        VXD extends VariableSizeSequenceDefinition<T,VXO>,
                        DC extends DynamicSequenceDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                        ?,?,?,?,?,?,?,?,
                                        ZT,ZXS,ZXO,ZXD,?,?,?,?,
                                        VXS,VXO,VXD,?,?,?,?,
                                        ?,DC>>
    extends AggregatorReadingCore<T, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXB, DC>
    implements DynamicSequenceDeserializing<T, XS, XQ, XR, XD, ZT, ZXO, ZXD, VXO, VXD>
{

  private DeserializingSubfieldHandlerCore<ZT,ZXS,ZXD,ZXO,T,XBC,XD,XO>
                        sizeReadFieldHandler;
  private DeserializingSubfieldHandlerCore<T,VXS,VXD,VXO,T,XBC,XD,XO>
                        varseqReadFieldHandler;

  protected DynamicSequenceDeserializingCore(
    int index,
    XS settings,
    AXB parentBytestream,
    DeserializingFieldCore<T,?,XD,?,?,?> packetfieldCore,
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
  public DeserializingSubfieldHandler<ZT, ZXD, ZXO> size() {
    return this.sizeReadFieldHandler.asSubfield();
  }

  @Override
  public DeserializingSubfieldHandler<T, VXD, VXO> variableSequence() {
    return this.varseqReadFieldHandler.asSubfield();
  }

}
