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
package weliyek.amat.basic.string;

import java.nio.charset.Charset;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.ketza.util.array.ByteArrayDefinition;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.ByteArrayWriting;

public abstract class StringFromBytesWritingCore<
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQ extends SerializingRuntime<YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,YQ>,
                        YR extends SerializingResult,
                        YO extends StringFromBytesWriting<YS,YQ,YR,YD,SYD,SYO>,
                        YOC extends StringFromBytesWritingCore<YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYB,SYS,SYO,SYD,DC>,
                        YD extends StringFromBytesDefinition<?,YO,? extends SYD>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        SYS extends OperationSettings,
                        SYO extends ByteArrayWriting<SYS,?,?,SYD>,
                        SYD extends ByteArrayDefinition<?>,
                        DC extends StringFromBytesCore<
                                      ?,?,?,?,?,?,?,?,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYB,
                                      ?,?,?,SYS,SYO,SYD,?,?,DC>>
    extends StringFromPrimitiveWritingCore<
                        YS,YB,YBC,YQ,YQC,YR,YO,YOC,YD,AYB,ByteArrayWrapper,SYS,SYO,SYD,DC>
    implements StringFromBytesWriting<YS, YQ, YR, YD, SYD, SYO>
{

  protected StringFromBytesWritingCore(
    int index,
    String serializable,
    YS settings,
    AYB parentBytestream,
    SerializingFieldCore<String,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(
        index,
        serializable,
        settings,
        parentBytestream,
        packetHandlerCore,
        definitionCore,
        operationBody);
  }

  @Override
  public final SerializingSubfieldHandler<ByteArrayWrapper, SYD, SYO> bytes() {
    return primitiveArray();
  }

  @Override
  public final Charset charset() {
    return definition().charset();
  }

}
