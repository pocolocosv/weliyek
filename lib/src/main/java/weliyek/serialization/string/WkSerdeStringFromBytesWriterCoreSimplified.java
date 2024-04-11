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
package weliyek.serialization.string;

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.util.array.WkSerdeDtreeByteArrayWriter;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;

public class WkSerdeStringFromBytesWriterCoreSimplified<
                        YS extends WkSerdeDtreeOperationSettings,
                        YO extends WkSerdeStringFromBytesWriter<
                                      YS,
                                      WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                      WkSerdeDtreeOperationResult<String>,
                                      YD,SYD,SYO>,
                        YD extends WkSerdeStringFromBytesDefinition<?,YO,? extends SYD>,
                        SYS extends WkSerdeDtreeOperationSettings,
                        SYO extends WkSerdeDtreeByteArrayWriter<SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreeByteArrayDefinition>
    extends WkSerdeStringFromBytesWriterCore<
                        YS,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<String>,
                        YO,
                        WkSerdeStringFromBytesWriterCoreSimplified<YS,YO,YD,SYS,SYO,SYD>,
                        YD,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        SYS, SYO, SYD,
                        WkSerdeStringFromBytesDefinitionCoreSimplified<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,?,?>>
{

  public WkSerdeStringFromBytesWriterCoreSimplified(
    int index,
    String serializable,
    YS settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<String,?,YD,?,?,?> packetHandlerCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,?,?> definitionCore,
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
  protected WkSerdeStringFromBytesWriterCoreSimplified<YS,YO,YD,SYS,SYO,SYD> getThis() {
    return this;
  }

}
