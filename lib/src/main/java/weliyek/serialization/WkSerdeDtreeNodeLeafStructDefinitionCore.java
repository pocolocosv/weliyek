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

public abstract class WkSerdeDtreeNodeLeafStructDefinitionCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<?,?,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeNodeStructDefinition<T>,
                        XO extends WkSerdeDtreeNodeDataReader<T,XS,?,XR,XD>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<?,?,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreeNodeStructDefinition<T>,
                        YO extends WkSerdeDtreeNodeDataWriter<T,YS,?,YR,YD>,
                        AYB extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreeNodeStructDefinition<T>,
                        DC extends WkSerdeDtreeNodeLeafStructDefinitionCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends WkSerdeDtreeNodeStructDefinitionCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
{

  public static final char LABEL_SEPARATOR = ',';

  final WkSerdeDtreeNodeDataDecoderEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory;
  final WkSerdeDtreeNodeDataEncoderEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory;

  protected WkSerdeDtreeNodeLeafStructDefinitionCore(
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<T,XS,XD,DC,XO,AXB> readingOpFactory,
    WkSerdeDtreeNodeDataDecoderEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    Function<AYB, YQC> txRuntimeFactory,
    BiFunction<YO, T, YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<T,YS,YD,DC,YO,AYB> writingOpFactory,
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
  public List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> subfields() {
    return Collections.emptyList();
  }

}
