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
package weliyek.amat.basic.number;

import java.util.function.BiFunction;
import java.util.function.Function;

import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.serializer.InputDeserializerFactory;
import weliyek.amat.basic.serializer.OutputSerializerFactory;
import weliyek.amat.basic.serializer.SerializerDefinitionCore;

public abstract class NumberSerializerCore<
                        T extends Number,
                        XS extends OperationSettings,
                        XQC extends ReadingRuntimeControl<?,?,?>,
                        XR extends DeserializingResult<T>,
                        XD extends NumberDefinition<T,XO>,
                        XO extends NumberDeserializing<T,XS,?,XR,XD>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YQC extends WritingRuntimeControl<?,?,?>,
                        YR extends SerializingResult,
                        YD extends NumberDefinition<T,?>,
                        YO extends NumberSerializing<T,YS,?,YR,YD>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        D extends NumberDefinition<T,XO>,
                        DC extends NumberSerializerCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends SerializerDefinitionCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
    implements NumberDefinition<T, XO>
{

  protected NumberSerializerCore(
    ComponentSegmentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    PacketInputFieldReadingFactory<T,XS,XD,DC,XO,AXB> readingOpFactory,
    InputDeserializerFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    Function<AYB,YQC> txRuntimeFactory,
    Function<YO,YR> txResultFactory,
    PacketOutputFieldWritingFactory<T,YS,YD,DC,YO,AYB> writingOpFactory,
    OutputSerializerFactory<T, ? super YQC, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          rxSerializerFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
  }

}
