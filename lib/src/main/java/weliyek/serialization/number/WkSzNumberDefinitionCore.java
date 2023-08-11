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

import java.util.function.BiFunction;
import java.util.function.Function;

import weliyek.serialization.InputDeserializerFactory;
import weliyek.serialization.OutputSerializerFactory;
import weliyek.serialization.PacketInputFieldReadingFactory;
import weliyek.serialization.PacketOutputFieldWritingFactory;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzSerializerDefinitionCore;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntimeControl;

public abstract class WkSzNumberDefinitionCore<
                        T extends Number,
                        XS extends WkSzOperationSettings,
                        XQC extends WkSzReadingRuntimeControl<?,?,?>,
                        XR extends WkSzReadingResult<T>,
                        XD extends WkSzNumberDefinition<T,XO>,
                        XO extends WkSzNumberReader<T,XS,?,XR,XD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSzOperationSettings,
                        YQC extends WkSzWritingRuntimeControl<?,?,?>,
                        YR extends WkSzWritingResult,
                        YD extends WkSzNumberDefinition<T,?>,
                        YO extends WkSzNumberWriter<T,YS,?,YR,YD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        D extends WkSzNumberDefinition<T,XO>,
                        DC extends WkSzNumberDefinitionCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends WkSzSerializerDefinitionCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
    implements WkSzNumberDefinition<T, XO>
{

  protected WkSzNumberDefinitionCore(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
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
