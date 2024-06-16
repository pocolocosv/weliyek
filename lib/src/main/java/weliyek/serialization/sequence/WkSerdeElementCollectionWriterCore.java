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
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;

public abstract class WkSerdeElementCollectionWriterCore<
                        T extends Collection<ET>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQ extends WkSerdeDtreeOperationOutputRuntimeSequenceCommon<YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<YB,YBC,YQ>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeElementCollectionDefinition<T,?,YO,ET,?>,
                        YDC extends WkSerdeElementCollectionDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,?,?,
                                        YS,YB,YBC,YQC,YR,YD,?,YO,YOC,AYBC,
                                        ET,?,?,?,EYS,EYD,EYO,? extends EYD,? extends YD,?>,
                        YO extends WkSerdeElementCollectionWriter<T,YS,YQ,YR,YD,ET,EYD,EYO>,
                        YOC extends WkSerdeElementCollectionWriterCore<
                                        T,YS,YB,YBC,YQ,YQC,YR,YD,YDC,YO,?,AYBC,ET,EYS,EYD,EYO>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        ET,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>>
        extends WkSerdeDtreeAggregatorMsgWriterCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YDC, YO, YOC, AYBC>
        implements WkSerdeElementCollectionWriter<T, YS, YQ, YR, YD, ET, EYD, EYO>
{

  private final List<ET> serializableAsList;

  protected WkSerdeElementCollectionWriterCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<?,?,?,?,?,?,?,?> dataFieldCore,
    YDC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, dataFieldCore, definitionCore, operationBody);
    this.serializableAsList = WkSerdeElementCollectionWriter.collectionToList(serializable);
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
  public final Optional<WkSerdeDtreeMsgOutputField<ET, EYD, EYO>> elements() {
    return Optional.ofNullable(getSubfieldpacketFor(definitionCore().elementComponent).asPacket());
  }

}
