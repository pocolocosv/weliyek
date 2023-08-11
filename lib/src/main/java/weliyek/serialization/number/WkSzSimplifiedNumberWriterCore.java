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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;

public class WkSzSimplifiedNumberWriterCore<
                        Y extends Number,
                        YO extends WkSzNumberWriter<
                                        Y,
                                        WkSzOperationSettings,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD>,
                        YD extends WkSzNumberDefinition<Y,?>>
    extends WkSzNumberWriterCore<
                        Y,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YO,
                        WkSzSimplifiedNumberWriterCore<Y,YO,YD>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        WkSzSimplifiedNumberSerializerDefinitionCore<Y,?,YO,YD>>
{

  WkSzSimplifiedNumberWriterCore(
    int index,
    Y serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<Y,?,YD,?,?,?> packetHandlerCore,
    WkSzSimplifiedNumberSerializerDefinitionCore<Y,?,YO,YD> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  public long expectedBytes() {
    return 0;
  }

  @Override
  protected WkSzSimplifiedNumberWriterCore<Y,YO,YD> getThis() {
    return this;
  }

  @Override
  protected void onSerializingOperationInitialization() {
    // Nothing to do.
  }

}
