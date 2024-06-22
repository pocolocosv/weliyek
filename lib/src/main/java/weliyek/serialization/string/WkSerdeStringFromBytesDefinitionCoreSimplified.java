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

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.function.Function;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationResultBasic;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;
import weliyek.util.array.WkSerdeDtreeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeByteArrayWriter;

public class WkSerdeStringFromBytesDefinitionCoreSimplified<
                        XS extends WkSerdeDtreeOperationSettings,
                        XO extends WkSerdeStringFromBytesReader<
                                        XS,
                                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<String>,
                                        XD,
                                        SXD,
                                        SXO>,
                        XD extends WkSerdeStringFromBytesDefinition<XO,?,? extends SXD>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YO extends WkSerdeStringFromBytesWriter<
                                        YS,
                                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<String>,
                                        YD,
                                        SYD,
                                        SYO>,
                        YD extends WkSerdeStringFromBytesDefinition<?,YO,? extends SYD>,
                        SXS extends WkSerdeDtreeOperationSettings,
                        SXO extends WkSerdeDtreeByteArrayReader<SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreeByteArrayDefinition,
                        SYS extends WkSerdeDtreeOperationSettings,
                        SYO extends WkSerdeDtreeByteArrayWriter<SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreeByteArrayDefinition,
                        SD extends WkSerdeDtreeByteArrayDefinition,
                        D extends WkSerdeStringFromBytesDefinition<XO,YO,SD>>
    extends WkSerdeStringFromBytesDefinitionCore<
                        XS,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<String>,
                        XO,
                        WkSerdeStringFromBytesReaderCoreSimplified<XS,XO,XD,SXS,SXO,SXD>,
                        XD,
                        WkSerdeStringFromBytesDefinitionCoreSimplified<XS,XO,XD,?,?,?,SXS,SXO,SXD,?,?,?,? extends SXD,? extends XD>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        YS,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
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
                        SXS,
                        SXO, SXD,
                        SYS,
                        SYO, SYD,
                        SD,
                        D,
                        WkSerdeStringFromBytesDefinitionCoreSimplified<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>>
{

  protected WkSerdeStringFromBytesDefinitionCoreSimplified(
    Charset defaultCharset,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<
      XS,
      WkSerdeStringFromBytesDefinitionCoreSimplified<XS,XO,XD,?,?,?,SXS,SXO,SXD,?,?,?,? extends SXD,? extends XD>,
      WkSerdeStringFromBytesReaderCoreSimplified<XS,XO,XD,SXS,SXO,SXD>,
      WkSerdeDtreeBytestreamInputBase<?>>
        readingOpFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<
      String,
      YS,
      WkSerdeStringFromBytesDefinitionCoreSimplified<?,?,?,YS,YO,YD,?,?,?,SYS,SYO,SYD,? extends SYD,? extends YD>,
      WkSerdeStringFromBytesWriterCoreSimplified<YS,YO,YD,SYS,SYO,SYD>,
      WkSerdeDtreeBytestreamOutputBase<?>>
        writingOpFactory,
    String bytesLabel,
    WkOperationSettingsFactory<XO, SXS> bytesDeserializingSettingsFactory,
    Function<XO,String> bytesDeserializingStringAggregator,
    WkOperationSettingsFactory<YO, SYS> bytesSerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<WkByteArray,SYD,String,YO> bytesSerializingDisaggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      WkByteArray,SXS,SXO,
      WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
      SYS,SYO,
      WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
      SD> bytesDefinitionFactory,
    D definitionBody) {
    super(
          defaultCharset,
          componentCore,
          WkSerdeDtreeOperationInputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          readingOpFactory,
          WkSerdeDtreeOperationOutputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          writingOpFactory,
          bytesLabel,
          Optional.empty(),
          bytesDeserializingSettingsFactory,
          bytesDeserializingStringAggregator,
          Optional.empty(),
          bytesSerializingSettingsFactory,
          bytesSerializingDisaggregator,
          false,
          bytesDefinitionFactory,
          definitionBody);
  }

  @Override
  protected WkSerdeStringFromBytesDefinitionCoreSimplified<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>
  getThis() {
    return this;
  }

}
