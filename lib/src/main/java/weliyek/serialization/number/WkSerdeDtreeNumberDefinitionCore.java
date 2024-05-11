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

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgPrimitiveReaderFactory;
import weliyek.serialization.WkSerdeDtreeMsgPrimitiveWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataDecoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataEncoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreePrimitiveStructDefinitionCore;

public abstract class WkSerdeDtreeNumberDefinitionCore<
                        T extends Number,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<?,?,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeNumberStructDefinition<T>,
                        XDC extends WkSerdeDtreeNumberDefinitionCore<T,XS,XQC,XR,XD,?,XO,XOC,AXB,?,?,?,?,?,?,?,?,? extends XD,?>,
                        XO extends WkSerdeDtreeNumberMsgReader<T,XS,?,XR,XD>,
                        XOC extends WkSerdeDtreeNumberMsgReaderCore<T,XS,?,XQC,XR,XO,?,XD,XDC,AXB>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<?,?,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreeNumberStructDefinition<T>,
                        YDC extends WkSerdeDtreeNumberDefinitionCore<T,?,?,?,?,?,?,?,?,YS,YQC,YR,YD,?,YO,YOC,AYB,? extends YD,?>,
                        YO extends WkSerdeDtreeNumberMsgWriter<T,YS,?,YR,YD>,
                        YOC extends WkSerdeDtreeNumberMsgWriterCore<T,YS,?,YQC,YR,YO,?,YD,YDC,AYB>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreeNumberStructDefinition<T>,
                        DC extends WkSerdeDtreeNumberDefinitionCore<
                                        T,XS,XQC,XR,XD,XDC,XO,XOC,AXB,YS,YQC,YR,YD,YDC,YO,YOC,AYB,D,?>>
    extends WkSerdeDtreePrimitiveStructDefinitionCore<
                        T, XS, XQC, XR, XD, XDC, XO, XOC, AXB, YS, YQC, YR, YD, YDC, YO, YOC, AYB, D, DC>
    implements WkSerdeDtreeNumberStructDefinition<T>
{

  protected WkSerdeDtreeNumberDefinitionCore(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSerdeDtreeMsgPrimitiveReaderFactory<XS,XDC,XOC,AXB> readingOpFactory,
    WkSerdeDtreeNodeDataDecoderEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    Function<AYB,YQC> txRuntimeFactory,
    BiFunction<YO,T,YR> txResultFactory,
    WkSerdeDtreeMsgPrimitiveWriterCoreFactory<T,YS,YDC,YOC,AYB> writingOpFactory,
    WkSerdeDtreeNodeDataEncoderEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory,
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
