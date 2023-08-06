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
package weliyek.amat.basic.string;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.ketza.util.array.WkSzByteArrayDefinition;
import weliyek.ketza.util.array.ByteArrayWriting;

public class SimpleStringFromBytesWritingCore<
                        YS extends OperationSettings,
                        YO extends WkSzStringFromBytesWriter<
                                      YS,
                                      SerializingRuntime<WkSzOutputBytestream>,
                                      SerializingResult,
                                      YD,SYD,SYO>,
                        YD extends WkSzStringFromBytesDefinition<?,YO,? extends SYD>,
                        SYS extends OperationSettings,
                        SYO extends ByteArrayWriting<SYS,?,?,SYD>,
                        SYD extends WkSzByteArrayDefinition<?>>
    extends StringFromBytesWritingCore<
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        SerializingRuntime<WkSzOutputBytestream>,
                        WritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          SerializingRuntime<WkSzOutputBytestream>>,
                        SerializingResult,
                        YO,
                        SimpleStringFromBytesWritingCore<YS,YO,YD,SYS,SYO,SYD>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        SYS, SYO, SYD,
                        SimplifiedStringFromBytesCore<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,?,?>>
{

  public SimpleStringFromBytesWritingCore(
    int index,
    String serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<String,?,YD,?,?,?> packetHandlerCore,
    SimplifiedStringFromBytesCore<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,?,?> definitionCore,
    YO operationBody) {
    super(
        index,
        serializable,
        settings,
        parentBytestream,
        packetHandlerCore,
        definitionCore, operationBody);
  }


  @Override
  protected void onStringFromPrimitiveWritingInitialization() {
    // Nothing to do.
  }
  /*
  @Override
  protected void onStringFromPrimitiveWritingInitialization() {
    if(definitionCore().onWritingOpStart().isPresent()) {
      definitionCore().onWritingOpStart().get().accept(this);;
    }
  }
  */

  @Override
  protected SimpleStringFromBytesWritingCore<YS,YO,YD,SYS,SYO,SYD> getThis() {
    return this;
  }

}
