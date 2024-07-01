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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class WkSerdeDtreePrimitiveMsgWriterCore<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<?>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<?,?,YQ>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,YQ,YR,YD>,
                        YOC extends WkSerdeDtreePrimitiveMsgWriterCore<
                                      T,YS,YQ,YQC,YR,YO,?,YD,YDC,AYB>,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        YDC extends WkSerdeDtreePrimitiveStructDefinitionCore<
                                      T,?,?,?,?,?,?,?,?,YS,YQC,YR,YD,?,YO,YOC,AYB,? extends YD,?>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>>
    extends WkSerdeDtreeMsgWriterCore<
                        T, YS, YQ, YQC, YR, YO, YOC, YD, YDC, AYB>
{

    protected WkSerdeDtreePrimitiveMsgWriterEngine<T, ? super YQC, ? super YO> rule;

    protected WkSerdeDtreePrimitiveMsgWriterCore(
        int index,
        WkSerdeDtreeMsgOutputFieldCore<T,YS,?,?,AYB,?,?,?> msgFieldCore,
        YDC definitionCore,
        YO operationBody) {
        super(
            index,
            msgFieldCore,
            definitionCore,
            operationBody);
    }

    @Override
    protected final Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> onProcessingBytestream() {
        if (this.rule.isDone()) {
          // Protect against calls after completing.
          return Optional.empty();
        }

        this.rule.processBytestream();
        if (this.rule.isDone()) {
          this.completeOperation();
          return Optional.of(body());
        }
        // No subfield has changed in this call.
        return Optional.empty();
    }

    @Override
    protected void onStart() {
      this.rule = definitionCore().txSerializerFactory
                                  .newEngine(getRuntimeControl(), body());
      this.rule.start();
      onSerializingOperationInitialization();
    }

    protected abstract void onSerializingOperationInitialization();

    @Override
    public List<WkSerdeDtreeMsgOutputField<?,?,?>> subfields() {
      return Collections.emptyList();
    }

}
