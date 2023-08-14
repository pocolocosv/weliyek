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

import weliyek.serialization.WkSzReadEngineFactory;
import weliyek.serialization.WkSzWriteEngineFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSrlzStructDefinitionFrameLeafNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntimeControl;

public abstract class WkNumberSrlzStructDefinitionFrameNodeCore<
                        T extends Number,
                        XS extends WkSzOperationSettings,
                        XQC extends WkSzReadingRuntimeControl<?,?,?>,
                        XR extends WkSzReadingResult<T>,
                        XD extends WkNumberSrlzStructDefinitionFrameLeafNode<T,XO>,
                        XO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<T,XS,?,XR,XD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSzOperationSettings,
                        YQC extends WkSzWritingRuntimeControl<?,?,?>,
                        YR extends WkSzWritingResult,
                        YD extends WkNumberSrlzStructDefinitionFrameLeafNode<T,?>,
                        YO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<T,YS,?,YR,YD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        D extends WkNumberSrlzStructDefinitionFrameLeafNode<T,XO>,
                        DC extends WkNumberSrlzStructDefinitionFrameNodeCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends WkSrlzStructDefinitionFrameLeafNodeCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
    implements WkNumberSrlzStructDefinitionFrameLeafNode<T, XO>
{

  protected WkNumberSrlzStructDefinitionFrameNodeCore(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<T,XS,XD,DC,XO,AXB> readingOpFactory,
    WkSzReadEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    Function<AYB,YQC> txRuntimeFactory,
    Function<YO,YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<T,YS,YD,DC,YO,AYB> writingOpFactory,
    WkSzWriteEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory,
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
