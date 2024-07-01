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

import java.nio.charset.Charset;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;
import weliyek.util.array.WkSerdeDtreeByteArrayWriter;

public abstract class WkSerdeStringFromBytesWriterCore<
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,YQ>,
                        YR extends WkSerdeDtreeOperationResult<String>,
                        YO extends WkSerdeStringFromBytesWriter<YS,YQ,YR,YD,SYD,SYO>,
                        YOC extends WkSerdeStringFromBytesWriterCore<
                                        YS,YB,YBC,YQ,YQC,YR,YO,?,YD,YDC,AYB,SYS,SYO,SYD>,
                        YD extends WkSerdeStringFromBytesDefinition<?,YO,? extends SYD>,
                        YDC extends WkSerdeStringFromBytesDefinitionCore<
                                        ?,?,?,?,?,?,?,?,?,?,
                                        YS,YB,YBC,YQC,YR,YO,YOC,YD,?,AYB,
                                        ?,?,?,SYS,SYO,SYD,? extends SYD,? extends YD,?>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        SYS extends WkSerdeDtreeOperationSettings,
                        SYO extends WkSerdeDtreeByteArrayWriter<SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreeByteArrayDefinition>
    extends WkSerdeStringFromPrimitiveArrayWriterCore<
                        YS,YB,YBC,YQ,YQC,YR,YO,YOC,YD,YDC,AYB,WkByteArray,SYS,SYO,SYD>
    implements WkSerdeStringFromBytesWriter<YS, YQ, YR, YD, SYD, SYO>
{

  protected WkSerdeStringFromBytesWriterCore(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<String,YS,?,?,AYB,?,?,?> writerFieldCore,
    YDC definitionCore,
    YO operationBody) {
    super(
        index,
        writerFieldCore,
        definitionCore,
        operationBody);
  }

  @Override
  public final Optional<WkSerdeDtreeMsgOutputField<WkByteArray, SYD, SYO>> bytes() {
    return primitiveArray();
  }

  @Override
  public final Charset charset() {
    return definition().charset();
  }

}
