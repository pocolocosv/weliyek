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
package weliyek.ketza.util.array;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;
import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;
import weliyek.amat.basic.serializer.PrimitiveArraySerializerDefinition;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerWriter;
import weliyek.amat.basic.serializer.WkSzSerializerWriterCore;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public abstract class PrimitiveArraySerializerWritingCore<
                        Y extends PrimitiveArrayWrapperBase<?,?>,
                        YS extends OperationSettings,
                        YQ extends SequenceWritingRuntime<?>,
                        YQC extends SequenceWritingRuntimeControl<?,?,YQ>,
                        YR extends SerializingResult,
                        YO extends WkSzPrimitiveArraySerializerWriter<Y,YS,YQ,YR,YD>,
                        YOC extends PrimitiveArraySerializerWritingCore<Y,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends PrimitiveArraySerializerDefinition<Y,?>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        DC extends PrimitiveArraySerializerCore<Y,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,? extends YD,DC>>
        extends WkSzSerializerWriterCore<Y, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
        implements WkSzPrimitiveArraySerializerWriter<Y, YS, YQ, YR, YD>
{

  private final int requestedLength;

  protected PrimitiveArraySerializerWritingCore(
    int index,
    Y serializable,
    YS settings,
    AYB parentBytestream,
    WkSzPacketWriterFieldCore<Y,?,YD,?,?,?> serializingfieldCore,
    DC definitionCore,
    YO operationBody) {
    super(
          index,
          serializable,
          settings,
          parentBytestream,
          serializingfieldCore,
          definitionCore,
          operationBody);
    this.requestedLength = definitionCore.txRequestedLengthEvaluator.retriveLength(
                                serializable, settings, definition());
  }

  @Override
  public int getRequestedLength() {
    return this.requestedLength;
  }

}
