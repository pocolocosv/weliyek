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
package weliyek.serialization;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class WkSerdeDtreePrimitiveStructDefinitionCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<?,?,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        XDC extends WkSerdeDtreePrimitiveStructDefinitionCore<
                                      T,XS,XQC,XR,XD,?,XO,XOC,AXBC,?,?,?,?,?,?,?,?,? extends XD,?>,
                        XO extends WkSerdeDtreeMsgReader<T,XS,?,XR,XD>,
                        XOC extends WkSerdeDtreePrimitiveMsgReaderCore<
                                      T,XS,?,XQC,XR,XO,?,XD,XDC,AXBC>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<?,?,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        YDC extends WkSerdeDtreePrimitiveStructDefinitionCore<
                                      T,?,?,?,?,?,?,?,?,YS,YQC,YR,YD,?,YO,YOC,AYBC,? extends YD,?>,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,?,YR,YD>,
                        YOC extends WkSerdeDtreeMsgWriterCore<T,YS,?,YQC,YR,YO,?,YD,YDC,AYBC>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreeStructDefinition<T>,
                        DC extends WkSerdeDtreePrimitiveStructDefinitionCore<
                                      T,XS,XQC,XR,XD,XDC,XO,XOC,AXBC,YS,YQC,YR,YD,YDC,YO,YOC,AYBC,D,?>>
    extends WkSerdeDtreeStructDefinitionCore<
                        T, XS, XQC, XR, XD, XDC, XO, XOC, AXBC,
                        YS, YQC, YR, YD, YDC, YO, YOC, AYBC, D, DC>
{

  public static final char LABEL_SEPARATOR = ',';

  final WkSerdeDtreeNodeDataDecoderEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory;
  final WkSerdeDtreeNodeDataEncoderEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory;

  protected WkSerdeDtreePrimitiveStructDefinitionCore(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    Function<AXBC,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSerdeDtreeMsgPrimitiveReaderFactory<XS,XDC,XOC,AXBC> readingOpFactory,
    WkSerdeDtreeNodeDataDecoderEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    Function<AYBC, YQC> txRuntimeFactory,
    BiFunction<YO, T, YR> txResultFactory,
    WkSerdeDtreeMsgPrimitiveWriterCoreFactory<T,YS,YDC,YOC,AYBC> writingOpFactory,
    WkSerdeDtreeNodeDataEncoderEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          definitionBody,
          serializableClass);
    this.rxSerializerFactory = Objects.requireNonNull(rxSerializerFactory);
    this.txSerializerFactory = Objects.requireNonNull(txSerializerFactory);
  }

  @Override
  protected void onInitialization() {
    // Nothing to do.
  }

  @Override
  protected String serializableClassName() {
    return super.serializableClassName() + LABEL_SEPARATOR + rxSerializerFactory.name();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return Collections.emptyList();
  }

}
