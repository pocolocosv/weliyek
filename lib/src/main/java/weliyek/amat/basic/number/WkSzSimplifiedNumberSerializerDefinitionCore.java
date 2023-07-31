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

import weliyek.amat.base.WkSzStructComponentCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.BasicReadingRuntime;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.BasicWritingRuntime;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.serializer.InputDeserializerFactory;
import weliyek.amat.basic.serializer.OutputSerializerFactory;

public final class WkSzSimplifiedNumberSerializerDefinitionCore<
                        T extends Number,
                        XO extends WkSzNumberReader<
                                      T,
                                      OperationSettings,
                                      DeserializingRuntime<InputBytestream>,
                                      DeserializingResult<T>,
                                      D>,
                        YO extends WkSzNumberWriter<
                                      T,
                                      OperationSettings,
                                      SerializingRuntime<OutputBytestream>,
                                      SerializingResult,
                                      D>,
                        D extends WkSzNumberDefinition<T,XO>>
    extends WkSzNumberDefinitionCore<
                        T,
                        OperationSettings,
                        ReadingRuntimeControl<
                          InputBytestream,
                          InputBytestreamGeneralBase<? extends InputBytestream>,
                          DeserializingRuntime<InputBytestream>>,
                        DeserializingResult<T>,
                        D, XO,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,
                        WritingRuntimeControl<
                          OutputBytestream,
                          OutputBytestreamGeneralBase<? extends OutputBytestream>,
                          SerializingRuntime<OutputBytestream>>,
                        SerializingResult,
                        D, YO,
                        OutputBytestreamGeneralBase<?>,
                        D,
                        WkSzSimplifiedNumberSerializerDefinitionCore<T,XO,YO,D>>
{

  public WkSzSimplifiedNumberSerializerDefinitionCore(
    WkSzStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    PacketInputFieldReadingFactory<
      T, OperationSettings, D, WkSzSimplifiedNumberSerializerDefinitionCore<T,XO,YO,D>,
      XO, InputBytestreamGeneralBase<?>> rxOpFactory,
    InputDeserializerFactory<
      T, ? super ReadingRuntimeControl<InputBytestream, InputBytestreamGeneralBase<? extends InputBytestream>,
      DeserializingRuntime<InputBytestream>>, ? super XO> rxSerializerFactory,
    PacketOutputFieldWritingFactory<
      T, OperationSettings, D, WkSzSimplifiedNumberSerializerDefinitionCore<T,XO,YO,D>,
      YO, OutputBytestreamGeneralBase<?>> txOpFactory,
    OutputSerializerFactory<
      T, ? super WritingRuntimeControl<OutputBytestream, OutputBytestreamGeneralBase<? extends OutputBytestream>,
      SerializingRuntime<OutputBytestream>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          BasicReadingRuntime::new,
          BasicReadingResult::new,
          rxOpFactory,
          rxSerializerFactory,
          BasicWritingRuntime::new,
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
