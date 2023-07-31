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
import java.util.List;

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.base.output.WkSzPacketWriterSubfieldCore;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriterCore;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;
import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;

public abstract class CollectionAndElementsFieldSerializerCore<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQ extends SequenceWritingRuntime<YB>,
                        YQC extends SequenceWritingRuntimeControl<YB,YBC,YQ>,
                        YR extends SerializingResult,
                        YD extends WkSzCollectionAndElementsDefinition<T,?,YO,ET,?>,
                        YO extends CollectionAndElementsFieldSerializer<T,YS,YQ,YR,YD,ET,EYD,EYO>,
                        YOC extends CollectionAndElementsFieldSerializerCore<T,YS,YB,YBC,YQ,YQC,YR,YD,YO,?,AYBC,ET,EYS,EYD,EYO,DC>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        ET,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        DC extends CollectionAndElementsFieldDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,YO,AYBC,
                                        ET,?,?,?,EYS,EYD,EYO,?,?,DC>>
        extends WkSzAggregatorWriterCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYBC, DC>
        implements CollectionAndElementsFieldSerializer<T, YS, YQ, YR, YD, ET, EYD, EYO>
{

  private final List<ET> serializableAsList;

  private WkSzPacketWriterSubfieldCore<ET,EYS,EYD,EYO,T,YBC,YD,YO> elementPacketSubfield;

  protected CollectionAndElementsFieldSerializerCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    WkSzPacketWriterFieldCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
    this.serializableAsList = CollectionAndElementsFieldSerializer.collectionToList(serializable);
    this.elementPacketSubfield = getSubfieldpacketFor(definitionCore().elementComponent);
  }

  @Override
  public final List<ET> serializableAsList() {
    return this.serializableAsList;
  }

  @Override
  protected final void onAggregatorInitialization() {
    onCollectionWritingInitialization();
  }

  protected abstract void onCollectionWritingInitialization();

  @Override
  public final WkSzPacketWriterSubfield<ET,EYD,EYO> element() {
    return this.elementPacketSubfield.asSubfield();
  }

}
