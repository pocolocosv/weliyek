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

public abstract class WkSrlzStructDefinitionFrameLeafNodeCore<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<?,?,?>,
                        XR extends WkDecodingResultSrlzPacketOperationData<T>,
                        XD extends WkSrlzStructDefinitionFrameLeafNode<T,XO>,
                        XO extends WkSrlzInputPacketDecoderFrameLeafNode<T,XS,?,XR,XD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<?,?,?>,
                        YR extends WkEncodingResultSrlzPacketOperationData,
                        YD extends WkSrlzStructDefinitionFrameLeafNode<T,?>,
                        YO extends WkSrlzOutputPacketEncoderFrameLeafNode<T,YS,?,YR,YD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        D extends WkSrlzStructDefinitionFrameLeafNode<T,XO>,
                        DC extends WkSrlzStructDefinitionFrameLeafNodeCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends WkSrlzStructDefinitionFrameNodeCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
    implements WkSrlzStructDefinitionFrameLeafNode<T, XO>
{

  public static final char LABEL_SEPARATOR = ',';

  final WkSzReadEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory;
  final WkSzWriteEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory;

  protected WkSrlzStructDefinitionFrameLeafNodeCore(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<T,XS,XD,DC,XO,AXB> readingOpFactory,
    WkSzReadEngineFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    Function<AYB, YQC> txRuntimeFactory,
    Function<YO, YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<T,YS,YD,DC,YO,AYB> writingOpFactory,
    WkSzWriteEngineFactory<T, ? super YQC, ? super YO> txSerializerFactory,
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
  protected String rxTargetName() {
    return super.rxTargetName() + LABEL_SEPARATOR + rxSerializerFactory.name();
  }

  @Override
  protected String txTargetName() {
    return super.txTargetName() + LABEL_SEPARATOR + txSerializerFactory.name();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?,?,?>> subfields() {
    return Collections.emptyList();
  }

}
