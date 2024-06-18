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
package weliyek.serialization;

import java.util.List;
import java.util.Optional;

public abstract class WkSerdeDtreeAggregatorMsgWriterCore<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,YQ>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        YDC extends WkSerdeDtreeAggregatorStructDefinitionCore<
                                      T,?,?,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,?,YO,YOC,AYB,? extends YD,?>,
                        YO extends WkSerdeDtreeAggregatorMsgWriter<T,YS,YQ,YR,YD>,
                        YOC extends WkSerdeDtreeAggregatorMsgWriterCore<
                                      T,YS,YB,YBC,YQ,YQC,YR,YD,YDC,YO,?,AYB>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>>
        extends WkSerdeDtreeMsgWriterCore<
                        T, YS, YQ, YQC, YR, YO, YOC, YD, YDC, AYB>
        implements WkSerdeDtreeAggregatorMsgWriter<T, YS, YQ, YR, YD>
{

  final WkSerdeDtreeAggregatorMsgSubfieldsWriters<T,YBC,YO> writingSubfields;

  protected WkSerdeDtreeAggregatorMsgWriterCore(
    int index,
    T serializable,
    YS settings,
    AYB parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<?,?,?,?,?,?,?,?> writerFieldCore,
    YDC definitionCore,
    YO operationBody) {
    super(index, settings, parentBytestream, serializable, writerFieldCore, definitionCore, operationBody);
    this.writingSubfields = new WkSerdeDtreeAggregatorMsgSubfieldsWriters<>(this);
  }

  @Override
  public List<WkSerdeDtreeMsgOutputField<?,?,?>> subfields() {
    return this.writingSubfields.asSubfieldList();
  }

  @Override
  protected void onStart() {
    onAggregatorInitialization();
  }

  protected abstract void onAggregatorInitialization();

  @Override
  protected Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> onProcessingBytestream() {
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> latestSubfield = this.writingSubfields.processBytestream();
    if (this.writingSubfields.isCompleted()) {
      this.completeOperation();
    }
    return latestSubfield;
  }

  @SuppressWarnings("unchecked")
  public <SY,
          SYS extends WkSerdeDtreeOperationSettings,
          SYD extends WkSerdeDtreeStructDefinition<SY>,
          SYO extends WkSerdeDtreeMsgWriter<SY,SYS,?,?,SYD>>
  WkSerdeDtreeMsgOutputSubfieldCore<SY,SYS,SYD,SYO,T,YBC,YO> getSubfieldpacketFor(
    WkSerdeDtreeStructSubfieldCore<SY,T,?,?,?,?,?,SYS,SYD,SYO,?,?,? extends SYD> structField) {
    return (WkSerdeDtreeMsgOutputSubfieldCore<SY,SYS,SYD,SYO,T,YBC,YO>) this.writingSubfields.getMsgSubfield(structField);
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
