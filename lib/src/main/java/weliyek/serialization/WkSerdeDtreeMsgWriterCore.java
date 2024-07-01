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

import java.util.Optional;

public abstract class WkSerdeDtreeMsgWriterCore<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<?>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<?,?,YQ>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,YQ,YR,YD>,
                        YOC extends WkSerdeDtreeMsgWriterCore<
                                      T,YS,YQ,YQC,YR,YO,?,YD,YDC,AYBC>,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        YDC extends WkSerdeDtreeStructDefinitionCore<
                                      T,?,?,?,?,?,?,?,?,YS,YQC,YR,YD,?,YO,YOC,AYBC,? extends YD,?>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>>
        extends WkSerdeDtreeMsgOperationCore<
                        YS, YQ, YQC, YR, YD, YDC, YO, YOC,
                        WkSerdeDtreeMsgOutputFieldCore<T,YS,?,?,AYBC,?,?,?>>
        implements WkSerdeDtreeMsgWriter<T, YS, YQ, YR, YD>
{

    private final YQC runtime;
    private final T serializable;

    protected WkSerdeDtreeMsgWriterCore(
      int index,
      WkSerdeDtreeMsgOutputFieldCore<T,YS,?,?,AYBC,?,?,?> writerFieldCore,
      YDC definitionCore,
      YO operationBody) {
      super(
            index,
            writerFieldCore,
            definitionCore,
            operationBody);
        this.runtime = definitionCore().txRuntimeFactory().apply(writerFieldCore.parentBytestream());
        this.serializable = packetFieldCore().serializable(index());
        definitionCore().onSerializerCreation(this);
    }

    @Override
    protected YS
    extractSettingsFrom(WkSerdeDtreeMsgOutputFieldCore<T, YS, ?, ?, AYBC, ?, ?, ?> msgFieldCore) {
      return packetFieldCore().newSettings(index());
    }

    @Override
    public T serializable() {
      return this.serializable;
    }

    @Override
    protected void onBeforeOperationCompletion() {
      // Nothing to do
    }

    @Override
    protected YR onCompletion() {
      return definitionCore().txResultFactory().apply(body(), serializable());
    }

    @Override
    protected void onAfterOperationCompletion() {
      // Nothing to do
    }

    @Override
    protected final Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> processBytestream() {
      return super.processBytestream();
    }

    @Override
    public final YQC getRuntimeControl() {
      return this.runtime;
    }

    @Override
    protected String label() {
      return definitionCore().label();
    }

    @Override
    public WkSerdeDtreeMsgOutputField<?,?,?> parentField() {
      return (WkSerdeDtreeMsgOutputField<?, ?, ?>) super.parentField();
    }

}
