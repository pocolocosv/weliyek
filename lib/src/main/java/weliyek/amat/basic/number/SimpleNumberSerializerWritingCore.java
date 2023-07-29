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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;

public class SimpleNumberSerializerWritingCore<
                        Y extends Number,
                        YO extends NumberSerializing<
                                        Y,
                                        OperationSettings,
                                        SerializingRuntime<OutputBytestream>,
                                        SerializingResult,
                                        YD>,
                        YD extends NumberDefinition<Y,?>>
    extends NumberWritingCore<
                        Y,
                        OperationSettings,
                        SerializingRuntime<OutputBytestream>,
                        WritingRuntimeControl<
                          OutputBytestream,
                          OutputBytestreamGeneralBase<? extends OutputBytestream>,
                          SerializingRuntime<OutputBytestream>>,
                        SerializingResult,
                        YO,
                        SimpleNumberSerializerWritingCore<Y,YO,YD>,
                        YD,
                        OutputBytestreamGeneralBase<?>,
                        SimplifiedNumberSerializerCore<Y,?,YO,YD>>
{

  SimpleNumberSerializerWritingCore(
    int index,
    Y serializable,
    OperationSettings settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    SerializingFieldCore<Y,?,YD,?,?,?> packetHandlerCore,
    SimplifiedNumberSerializerCore<Y,?,YO,YD> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  public long expectedBytes() {
    return 0;
  }

  @Override
  protected SimpleNumberSerializerWritingCore<Y,YO,YD> getThis() {
    return this;
  }

  @Override
  protected void onSerializingOperationInitialization() {
    // Nothing to do.
  }

}
