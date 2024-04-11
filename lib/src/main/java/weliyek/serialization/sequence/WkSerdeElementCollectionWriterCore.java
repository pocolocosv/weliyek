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
package weliyek.serialization.sequence;

import java.util.Collection;
import java.util.List;

import weliyek.serialization.WkSerdeDtreeAggregatorWriterCore;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataWriter;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinition;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;

public abstract class WkSerdeElementCollectionWriterCore<
                        T extends Collection<ET>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQ extends WkSerdeDtreeOperationOutputRuntimeSequenceCommon<YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<YB,YBC,YQ>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeElementCollectionDefinition<T,?,YO,ET,?>,
                        YO extends WkSerdeElementCollectionWriter<T,YS,YQ,YR,YD,ET,EYD,EYO>,
                        YOC extends WkSerdeElementCollectionWriterCore<T,YS,YB,YBC,YQ,YQC,YR,YD,YO,?,AYBC,ET,EYS,EYD,EYO,DC>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        ET,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeNodeDataWriter<ET,EYS,?,?,EYD>,
                        DC extends WkSerdeElementCollectionDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,YO,AYBC,
                                        ET,?,?,?,EYS,EYD,EYO,?,?,DC>>
        extends WkSerdeDtreeAggregatorWriterCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYBC, DC>
        implements WkSerdeElementCollectionWriter<T, YS, YQ, YR, YD, ET, EYD, EYO>
{

  private final List<ET> serializableAsList;

  private WkSrlzOutputPacketSubfieldFrameNodeCore<ET,EYS,EYD,EYO,T,YBC,YD,YO> elementPacketSubfield;

  protected WkSerdeElementCollectionWriterCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
    this.serializableAsList = WkSerdeElementCollectionWriter.collectionToList(serializable);
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
  public final WkSrlzOutputPacketSubfieldFrameNode<ET,EYD,EYO> elements() {
    return this.elementPacketSubfield.asSubfield();
  }

}
