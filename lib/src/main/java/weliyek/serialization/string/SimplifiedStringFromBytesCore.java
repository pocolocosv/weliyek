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
import weliyek.serialization.OperationSubsegmentSettingsFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.ProtocolDefinitionFactory;
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
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.util.array.ByteArrayReading;
import weliyek.serialization.util.array.ByteArrayWrapper;
import weliyek.serialization.util.array.ByteArrayWriting;
import weliyek.serialization.util.array.WkSzByteArrayDefinition;

public class SimplifiedStringFromBytesCore<
                        XS extends WkSzOperationSettings,
                        XO extends WkSzStringFromBytesReader<
                                        XS,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<String>,
                                        XD,
                                        SXD,
                                        SXO>,
                        XD extends WkSzStringFromBytesDefinition<XO,?,? extends SXD>,
                        YS extends WkSzOperationSettings,
                        YO extends WkSzStringFromBytesWriter<
                                        YS,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,
                                        SYD,
                                        SYO>,
                        YD extends WkSzStringFromBytesDefinition<?,YO,? extends SYD>,
                        SXS extends WkSzOperationSettings,
                        SXO extends ByteArrayReading<SXS,?,?,SXD>,
                        SXD extends WkSzByteArrayDefinition<SXO>,
                        SYS extends WkSzOperationSettings,
                        SYO extends ByteArrayWriting<SYS,?,?,SYD>,
                        SYD extends WkSzByteArrayDefinition<?>,
                        SD extends WkSzByteArrayDefinition<SXO>,
                        D extends WkSzStringFromBytesDefinition<XO,YO,SD>>
    extends WkSzStringFromBytesDefinitionCore<
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
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    WkSzPacketReaderOperationCoreFactory<
      String,XS,XD,SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>,
      XO,WkSzInputBytestreamBase<?>>
        readingOpFactory,
    WkSzPacketWriterOperationCoreFactory<
      String,YS,YD,SimplifiedStringFromBytesCore<XS,XO,XD,YS,YO,YD,SXS,SXO,SXD,SYS,SYO,SYD,SD,D>,
      YO,WkSzOutputBytestreamBase<?>>
        writingOpFactory,
    String bytesLabel,
    OperationSubsegmentSettingsFactory<XO, SXS> bytesDeserializingSettingsFactory,
    Function<XO,String> bytesDeserializingStringAggregator,
    OperationSubsegmentSettingsFactory<YO, SYS> bytesSerializingSettingsFactory,
    WkSzPacketWriteDisaggregator<ByteArrayWrapper,SYD,String,YO> bytesSerializingDisaggregator,
    ProtocolDefinitionFactory<
      ByteArrayWrapper,SXS,SXD,SXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
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
