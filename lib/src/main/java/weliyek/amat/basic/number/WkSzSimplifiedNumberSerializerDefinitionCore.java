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

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.WkSzBasicReadingRuntime;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.WkSzReadingRuntimeControl;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.WkSzBasicWritingRuntime;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.WkSzWritingResult;
import weliyek.amat.base.output.WkSzWritingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzWritingRuntimeControl;
import weliyek.amat.basic.serializer.InputDeserializerFactory;
import weliyek.amat.basic.serializer.OutputSerializerFactory;
import weliyek.serialization.base.WkSzStructComponentCoreBase;

public final class WkSzSimplifiedNumberSerializerDefinitionCore<
                        T extends Number,
                        XO extends WkSzNumberReader<
                                      T,
                                      WkSzOperationSettings,
                                      WkSzReadingRuntime<WkSzInputBytestream>,
                                      WkSzReadingResult<T>,
                                      D>,
                        YO extends WkSzNumberWriter<
                                      T,
                                      WkSzOperationSettings,
                                      WkSzWritingRuntime<WkSzOutputBytestream>,
                                      WkSzWritingResult,
                                      D>,
                        D extends WkSzNumberDefinition<T,XO>>
    extends WkSzNumberDefinitionCore<
                        T,
                        WkSzOperationSettings,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        D, XO,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        D, YO,
                        WkSzOutputBytestreamBase<?>,
                        D,
                        WkSzSimplifiedNumberSerializerDefinitionCore<T,XO,YO,D>>
{

  public WkSzSimplifiedNumberSerializerDefinitionCore(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    PacketInputFieldReadingFactory<
      T, WkSzOperationSettings, D, WkSzSimplifiedNumberSerializerDefinitionCore<T,XO,YO,D>,
      XO, WkSzInputBytestreamBase<?>> rxOpFactory,
    InputDeserializerFactory<
      T, ? super WkSzReadingRuntimeControl<WkSzInputBytestream, WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
      WkSzReadingRuntime<WkSzInputBytestream>>, ? super XO> rxSerializerFactory,
    PacketOutputFieldWritingFactory<
      T, WkSzOperationSettings, D, WkSzSimplifiedNumberSerializerDefinitionCore<T,XO,YO,D>,
      YO, WkSzOutputBytestreamBase<?>> txOpFactory,
    OutputSerializerFactory<
      T, ? super WkSzWritingRuntimeControl<WkSzOutputBytestream, WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
      WkSzWritingRuntime<WkSzOutputBytestream>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          WkSzBasicReadingRuntime::new,
          BasicReadingResult::new,
          rxOpFactory,
          rxSerializerFactory,
          WkSzBasicWritingRuntime::new,
          BasicWritingResult::empty,
          txOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected WkSzSimplifiedNumberSerializerDefinitionCore<T,XO,YO,D> getThis() {
    return this;
  }

}
