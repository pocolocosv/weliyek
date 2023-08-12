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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSzByteArrayWriter;
import weliyek.util.array.WkSzByteArrayDefinition;

public abstract class StringFromBytesWritingCore<
                        YS extends WkSzOperationSettings,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkSzWritingRuntime<YB>,
                        YQC extends WkSzWritingRuntimeControl<YB,YBC,YQ>,
                        YR extends WkSzWritingResult,
                        YO extends WkSzStringFromBytesWriter<YS,YQ,YR,YD,SYD,SYO>,
                        YOC extends StringFromBytesWritingCore<YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYB,SYS,SYO,SYD,DC>,
                        YD extends WkSzStringFromBytesDefinition<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        SYS extends WkSzOperationSettings,
                        SYO extends WkSzByteArrayWriter<SYS,?,?,SYD>,
                        SYD extends WkSzByteArrayDefinition<?>,
                        DC extends WkSzStringFromBytesDefinitionCore<
                                      ?,?,?,?,?,?,?,?,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYB,
                                      ?,?,?,SYS,SYO,SYD,?,?,DC>>
    extends StringFromPrimitiveWritingCore<
                        YS,YB,YBC,YQ,YQC,YR,YO,YOC,YD,AYB,WkByteArray,SYS,SYO,SYD,DC>
    implements WkSzStringFromBytesWriter<YS, YQ, YR, YD, SYD, SYO>
{

  protected StringFromBytesWritingCore(
    int index,
    String serializable,
    YS settings,
    AYB parentBytestream,
    WkSzPacketWriterFieldCore<String,?,YD,?,?,?> packetHandlerCore,
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
  public final WkSzPacketWriterSubfield<WkByteArray, SYD, SYO> bytes() {
    return primitiveArray();
  }

  @Override
  public final Charset charset() {
    return definition().charset();
  }

}
