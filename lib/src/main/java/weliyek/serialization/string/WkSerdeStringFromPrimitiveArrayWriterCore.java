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
package weliyek.serialization.string;

import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayWriter;

public abstract class WkSerdeStringFromPrimitiveArrayWriterCore<
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,YQ>,
                        YR extends WkSerdeDtreeOperationResult<String>,
                        YO extends WkSerdeStringFromPrimitiveArrayWriter<YS,YQ,YR,YD,SY,SYD,SYO>,
                        YOC extends WkSerdeStringFromPrimitiveArrayWriterCore<
                                        YS,YB,YBC,YQ,YQC,YR,YO,?,YD,YDC,AYB,SY,SYS,SYO,SYD>,
                        YD extends WkSerdeStringFromPrimitiveArrayDefinition<?,YO,? extends SYD>,
                        YDC extends WkSerdeStringFromPrimitiveArrayDefinitionCore<
                                        ?,?,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YOC,YD,?,AYB,SY,?,?,?,SYS,SYO,SYD,? extends SYD,? extends YD,?>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        SY extends WkPrimitiveArray<?,?>,
                        SYS extends WkSerdeDtreeOperationSettings,
                        SYO extends WkSerdeDtreePrimitiveArrayWriter<SY,SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreePrimitiveArrayDefinition<SY>>
        extends WkSerdeDtreeAggregatorMsgWriterCore<String, YS, YB, YBC, YQ, YQC, YR, YD, YDC, YO, YOC, AYB>
        implements WkSerdeStringFromPrimitiveArrayWriter<YS, YQ, YR, YD, SY, SYD, SYO>
{

  protected WkSerdeStringFromPrimitiveArrayWriterCore(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<String,YS,?,?,AYB,?,?,?> writerFieldCore,
    YDC definitionCore,
    YO operationBody) {
    super(index, writerFieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onAggregatorInitialization() {
    onStringFromPrimitiveWritingInitialization();
  }

  protected abstract void onStringFromPrimitiveWritingInitialization();

  @Override
  public Optional<WkSerdeDtreeMsgOutputField<SY, SYD, SYO>> primitiveArray() {
    return Optional.ofNullable(getSubfieldpacketFor(definitionCore().primitiveArraySubcomponent).asPacket());
  }

}
