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

import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzBasicReadingResult;
import weliyek.serialization.WkSzBasicReadingRuntime;
import weliyek.serialization.WkSzBasicWritingResult;
import weliyek.serialization.WkSzBasicWritingRuntime;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.util.array.WkByteArraySrlzInputPacketDecoderFrameNode;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkByteArraySrlzOutputPacketEncoderFrameNode;
import weliyek.util.array.WkByteArraySrlzStructDefinitionFrameNode;

public class SimplifiedStringFromBytesCore<
                        XS extends WkSzOperationSettings,
                        XO extends WkStringFromBytesSrlzInputPacketDecoderFrameNode<
                                        XS,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<String>,
                                        XD,
                                        SXD,
                                        SXO>,
                        XD extends WkStringFromBytesSrlzStructDefinitionFrameNode<XO,?,? extends SXD>,
                        YS extends WkSzOperationSettings,
                        YO extends WkStringFromBytesSrlzOutputPacketEncoderFrameNode<
                                        YS,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,
                                        SYD,
                                        SYO>,
                        YD extends WkStringFromBytesSrlzStructDefinitionFrameNode<?,YO,? extends SYD>,
                        SXS extends WkSzOperationSettings,
                        SXO extends WkByteArraySrlzInputPacketDecoderFrameNode<SXS,?,?,SXD>,
                        SXD extends WkByteArraySrlzStructDefinitionFrameNode<SXO>,
                        SYS extends WkSzOperationSettings,
                        SYO extends WkByteArraySrlzOutputPacketEncoderFrameNode<SYS,?,?,SYD>,
                        SYD extends WkByteArraySrlzStructDefinitionFrameNode<?>,
                        SD extends WkByteArraySrlzStructDefinitionFrameNode<SXO>,
                        D extends WkStringFromBytesSrlzStructDefinitionFrameNode<XO,YO,SD>>
    extends WkStringFromBytesSrlzStructDefinitionFrameNodeCore<
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<String>,
                        XO, XD,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YO, YD,
                        WkSzOutputBytestreamBase<?>,
                        SXS,
                        SXO, SXD,
                        SYS,
                        SYO, SYD,
                        SD,
                        D,
                        SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>>
{

  protected SimplifiedStringFromBytesCore(
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    WkSzPacketReaderOperationCoreFactory<
      String,XS,XD,SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>,
      XO,WkSzInputBytestreamBase<?>>
        readingOpFactory,
    WkSzPacketWriterOperationCoreFactory<
      String,YS,YD,SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>,
      YO,WkSzOutputBytestreamBase<?>>
        writingOpFactory,
    String bytesLabel,
    WkOperationSettingsFactory<XO, SXS> bytesDeserializingSettingsFactory,
    Function<XO,String> bytesDeserializingStringAggregator,
    WkOperationSettingsFactory<YO, SYS> bytesSerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<WkByteArray,SYD,String,YO> bytesSerializingDisaggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      WkByteArray,SXS,SXD,SXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
      SYS,SYD,SYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,SD>
        bytesDefinitionFactory,
    D definitionBody) {
    super(
          defaultCharset,
          componentCore,
          WkSzBasicReadingRuntime::new,
          WkSzBasicReadingResult::new,
          readingOpFactory,
          WkSzBasicWritingRuntime::new,
          WkSzBasicWritingResult::empty,
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
  protected SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>
  getThis() {
    return this;
  }

}
