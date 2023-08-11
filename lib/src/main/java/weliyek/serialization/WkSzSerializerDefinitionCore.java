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

public abstract class WkSzSerializerDefinitionCore<
                        T,
                        XS extends WkSzOperationSettings,
                        XQC extends WkSzReadingRuntimeControl<?,?,?>,
                        XR extends WkSzReadingResult<T>,
                        XD extends WkSzDefinition<T,XO>,
                        XO extends WkSzPacketReaderOperation<T,XS,?,XR,XD>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSzOperationSettings,
                        YQC extends WkSzWritingRuntimeControl<?,?,?>,
                        YR extends WkSzWritingResult,
                        YD extends WkSzDefinition<T,?>,
                        YO extends WkSzPacketWriterOperation<T,YS,?,YR,YD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        D extends WkSzDefinition<T,XO>,
                        DC extends WkSzSerializerDefinitionCore<T,XS,XQC,XR,XD,XO,AXB,YS,YQC,YR,YD,YO,AYB,D,?>>
    extends WkSzDefinitionCore<T, XS, XQC, XR, XD, XO, AXB, YS, YQC, YR, YD, YO, AYB, D, DC>
    implements WkSzSerializerDefinition<T, XO>
{

  public static final char LABEL_SEPARATOR = ',';

  final InputDeserializerFactory<T, ? super XQC, ? super XO> rxSerializerFactory;
  final OutputSerializerFactory<T, ? super YQC, ? super YO> txSerializerFactory;

  protected WkSzSerializerDefinitionCore(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    PacketInputFieldReadingFactory<T,XS,XD,DC,XO,AXB> readingOpFactory,
    InputDeserializerFactory<T, ? super XQC, ? super XO> rxSerializerFactory,
    Function<AYB, YQC> txRuntimeFactory,
    Function<YO, YR> txResultFactory,
    PacketOutputFieldWritingFactory<T,YS,YD,DC,YO,AYB> writingOpFactory,
    OutputSerializerFactory<T, ? super YQC, ? super YO> txSerializerFactory,
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
  public List<WkSzStructSubcomponent<?,?,?>> subfields() {
    return Collections.emptyList();
  }

}
