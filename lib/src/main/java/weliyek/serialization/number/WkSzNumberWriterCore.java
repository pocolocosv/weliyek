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
package weliyek.serialization.number;

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzSerializerWriterCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;

public abstract class WkSzNumberWriterCore<
                        T extends Number,
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YQC extends WkSzWritingRuntimeControl<?,?,YQ>,
                        YR extends WkSzWritingResult,
                        YO extends WkSzNumberWriter<T,YS,YQ,YR,YD>,
                        YOC extends WkSzNumberWriterCore<T,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkSzNumberDefinition<T,?>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        DC extends WkSzNumberDefinitionCore<T,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,? extends YD,DC>>
    extends WkSzSerializerWriterCore<T, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
    implements WkSzNumberWriter<T, YS, YQ, YR, YD>
{

  protected WkSzNumberWriterCore(
    int index,
    T serializable,
    YS settings,
    AYB parentBytestream,
    WkSzPacketWriterFieldCore<T,?,YD,?,?,?> packetHandlerCore,
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
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}