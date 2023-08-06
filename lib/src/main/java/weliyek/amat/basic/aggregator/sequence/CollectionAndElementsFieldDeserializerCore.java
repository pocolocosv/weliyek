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
package weliyek.amat.basic.aggregator.sequence;

import java.util.Collection;

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.WkSzPacketReaderSubfieldCore;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.amat.basic.aggregator.WkSzAggregatorReaderCore;
import weliyek.amat.basic.sequence.CollectionAndElementsFieldDeserializer;
import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;

public abstract class CollectionAndElementsFieldDeserializerCore<
                        T extends Collection<ET>,
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQ extends SequenceReadingRuntime<XB>,
                        XQC extends SequenceReadingRuntimeControl<XB,XBC,XQ>,
                        XR extends DeserializingResult<T>,
                        XD extends WkSzCollectionAndElementsDefinition<T,XO,?,ET,?>,
                        XO extends CollectionAndElementsFieldDeserializer<T,XS,XQ,XR,XD,ET,EXD,EXO>,
                        XOC extends CollectionAndElementsFieldDeserializerCore<
                                        T,XS,XB,XBC,XQ,XQC,XR,XD,XO,?,AXBC,ET,EXS,EXD,EXO,DC>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        DC extends CollectionAndElementsFieldDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XO,AXBC,
                                        ?,?,?,?,?,?,?,?,
                                        ET,EXS,EXD,EXO,
                                        ?,?,?,?,?,DC>>
        extends WkSzAggregatorReaderCore<T, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXBC, DC>
        implements CollectionAndElementsFieldDeserializer<T, XS, XQ, XR, XD, ET, EXD, EXO>
{

  private WkSzPacketReaderSubfieldCore<ET,EXS,EXD,EXO,T,XBC,XD,XO> elementPacketSubfield;

  protected CollectionAndElementsFieldDeserializerCore(
    int index,
    XS settings,
    AXBC parentBytestream,
    WkSzPacketReaderFieldCore<T,?,XD,?,?,?> packetfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
    this.elementPacketSubfield = getSubfieldpacketFor(definitionCore().elementComponent);
  }

  @Override
  protected final void onAggregatorReadingInitialization() {
    onCollectionReadingInitialization();
  }

  protected abstract void onCollectionReadingInitialization();

  @Override
  protected final T onFullReadingCompletion() {
    return definitionCore().collectionSerializingFactory.apply(this.body());
  }

  @Override
  protected final void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  public final WkSzPacketReaderSubfield<ET, EXD, EXO> element() {
    return this.elementPacketSubfield.asSubfield();
  }

}