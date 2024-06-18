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

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;
import weliyek.util.array.WkSerdeDtreeByteArrayWriter;

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
                        WkSerdeStringFromBytesDefinitionCoreSimplified<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,? extends SYD,? extends YD>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        SYS, SYO, SYD>
{

  public WkSerdeStringFromBytesWriterCoreSimplified(
    int index,
    String serializable,
    YS settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<?,?,?,?,?,?,?,?> writerFieldCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,? extends SYD,? extends YD> definitionCore,
    YO operationBody) {
    super(
        index,
        serializable,
        settings,
        parentBytestream,
        writerFieldCore,
        definitionCore,
        operationBody);
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
